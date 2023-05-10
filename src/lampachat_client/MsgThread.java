package lampachat_client;

import java.io.IOException;
import protocol.Protocol_v1;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MsgThread implements Runnable {

    private Thread self;
    private JHome frame;
    private Message message;
    private Database database = new Database();
    private String userName;

    public MsgThread(JHome frame) {
        message = new Message();
        self = new Thread(this);
        this.frame = frame;
    }

    public void start() {
        self.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("");
            System.out.println("MsgThreAD");
            try {
                System.out.println("TRY MsgThread");
                //Message message = new Message();
                //db Database = new Database();

                userName = database.ReadLogin();
                ArrayList<String> friends = new ArrayList<>();
                for (int i = 1; i <= database.ReadContactCount(); i++) {
                    friends.add(database.ReadContactList(i));
                }
                System.out.println("MsgThread> IP-" + Options.getServer_adress() + " Port_1-" + Options.getPort1() + " Port_2-" + Options.getPort2());
                Socket sc = new Socket(Options.getServer_adress(), Options.getPort2());
                System.out.println("MsgThread> USER- " + userName);

                InputStream inputStream = sc.getInputStream();
                ObjectInputStream reader = new ObjectInputStream(inputStream);
                ObjectOutputStream writer = new ObjectOutputStream((sc.getOutputStream()));
                writer.writeObject(message.loginMessage());
                frame.setStatus(true);
                System.out.println("online");
                while (true) {
                    System.out.println("wait");
                    Protocol_v1 mas = (Protocol_v1) reader.readObject();
                    System.out.println("in--");
                    if (mas.getService_message()) {
                        switch (mas.getMessage()) {
                            case "new_contact":
                                System.out.println("new_contact");
                                database.addContact(mas.getFrom_user());
                                break;
                            case "wrong login or password":
                                System.out.println("wrong login or password");
                                new JInfoWindow().setText("Login and Password NOT Correct\nPlease Logout and Login again");
                                //writer.close();///////////////////////////////////////
                                //reader.close();
                                break;
                            default:
                                System.out.println("ERROR service message(" + mas.getMessage() + ")");
                        }
                    } else {
                        String from = mas.getFrom_user();
                        String to = mas.getTo_user();
                        String message_text = mas.getMessage();
                        String time = mas.getTime();
                        System.out.printf("from - %s, to - %s, message - %s \n", from, to, message_text);
                        System.out.println("equal (" + from + "?" + userName + ")");
                        if (from.equals(userName)) {
                            System.out.println("if 1 " + to);
                            database.insert(to, from, to, message_text, time);
                        } else {
                            System.out.println("if 2 " + from);
                            if (!friends.contains(from)) {
                                //if (Database.r) {
                                //дублируется изза медленного обнолвения списка друзейы
                                database.addContact(from);
                                friends.add(from);
                            }
                            database.insert(from, from, to, message_text, time);
                        }

                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
                frame.setStatus(false);
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}
