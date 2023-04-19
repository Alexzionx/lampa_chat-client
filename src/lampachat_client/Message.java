package lampachat_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.Protocol_v1;

public class Message {

    private database database = new database();

    public Message() {
        System.out.println("Message >Message()- IP-" + Options.getServer_adress() + " Port_1-" + Options.getPort1() + " Port_2-" + Options.getPort2());

    }

    public Protocol_v1 loginMessage() {
        System.out.println("Message >loginMessage()");
        return new Protocol_v1(0, database.ReadLogin(), database.ReadPassword(), "login", true);
    }

    public boolean sendMessege(String toUserName, String messageText) {
        System.out.println("Message >sendMessege()");
        try (Socket sc = new Socket(Options.getServer_adress(), Options.getPort1())) {
            ObjectOutputStream writer = new ObjectOutputStream(sc.getOutputStream());
            writer.writeObject(this.loginMessage());
            writer.flush();
            Protocol_v1 newMessage = new Protocol_v1(0, database.ReadLogin(), toUserName, messageText);
            System.out.println("Message >sendMessege()> 2");
            writer.writeObject(newMessage);
            Thread.sleep(1000);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
        }
        System.out.println("Message >sendMessege()> Done");
        return true;
    }

    public boolean sendServiceLogin(String login, String pass) {
        System.out.println("Message > sendServiceLogin()");
        try (Socket sc = new Socket(Options.getServer_adress(), Options.getPort1())) {
            ObjectOutputStream writer = new ObjectOutputStream(sc.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(sc.getInputStream());
            Protocol_v1 newMessage = new Protocol_v1(0, login, pass, "singIn", true);
            System.out.println("send login ip-" + Options.getServer_adress() + " port-" + Options.getPort1());
            writer.writeObject(newMessage);
            Protocol_v1 mas = (Protocol_v1) reader.readObject();
            switch (mas.getMessage()) {
                case "login is correct":
                    System.out.println("login is correct");
                    String[] friendListFromServer = mas.getFrom_user().split(" ");
                    ArrayList<String> friendList = new ArrayList<>();
                    for (int i = 1; i < database.ReadContactCount(); i++) {
                        friendList.add(database.ReadContactList(i));
                    }
                    for (String friendName : friendListFromServer) {
                        if (!friendList.contains(friendName)) {
                            database.addContact(friendName);
                        }
                    }
                    database.setLoginAndPass(login, pass);
                    break;
                case "login is not correct":
                    System.out.println("login is not correct");
                    return false;
                default:
                    System.out.println(mas.getMessage());
                    return false;
            }
        } catch (IOException ex) {
            System.out.println("Message > sendServiceLogin()>" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Message > sendServiceLogin()>" + ex);
        }
        return true;
    }

    public String sendServiceSingUp(String login, String pass) {
        System.out.println("Message > sendServiceSingUp()");
        try (Socket sc = new Socket(Options.getServer_adress(), Options.getPort1())) {
            ObjectOutputStream writer = new ObjectOutputStream(sc.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(sc.getInputStream());
            Protocol_v1 newMessage = new Protocol_v1(0, login, pass, "singUp", true);
            System.out.println("send singup ip-" + Options.getServer_adress() + " port-" + Options.getPort1());
            writer.writeObject(newMessage);
            Protocol_v1 mas = (Protocol_v1) reader.readObject();
            if (mas.getMessage().equals("You are registered!")) {
                database.setLoginAndPass(login, pass);
                return "You are registered!\n\nPlease RESTART app!";
            } else {
                return mas.getMessage();
            }
        } catch (IOException ex) {
            System.out.println("Message > sendServiceSingUp()>" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Message > sendServiceSingUp()>" + ex);
        }
        System.out.println("Message > sendServiceSingUp() -END");
        return null;
    }

    public String sendServiceMessage(String to_user, String message) {
        System.out.println("Message > sendServiceMessage()");
        try (Socket sc = new Socket(Options.getServer_adress(), Options.getPort1())) {
            ObjectOutputStream writer = new ObjectOutputStream(sc.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(sc.getInputStream());
            writer.writeObject(this.loginMessage());
            Protocol_v1 newMessage = new Protocol_v1(0, database.ReadLogin(), to_user, message, true);
            System.out.println("send ServiceMessage ip-" + Options.getServer_adress() + " port-" + Options.getPort1());
            writer.writeObject(newMessage);
            Protocol_v1 mas = (Protocol_v1) reader.readObject();
            return mas.getMessage();
        } catch (IOException ex) {
            System.out.println("Message > sendServiceMessage()>" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Message > sendServiceMessage()>" + ex);
        }
        return null;
    }

}
