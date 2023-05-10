package lampachat_client;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MsgInWindow implements Runnable {

    private Thread self;
    private JHome form;
    private String userID;

    public MsgInWindow(JHome form, String userID) {
        self = new Thread(this);
        this.form = form;
        this.userID = userID;
        self.start();
    }

    @Override
    public void run() {
        Database db = new Database();
        while (JHome.contact == userID) {
            System.out.print("");
            while (form.msgCount <= db.ReadMessageCount(userID)) {
                //String s=Database.Read(userID);
                String s[] = db.ReadiDl(userID, JHome.msgCount);
                form.msgIN(s);
                JHome.msgCount++;
                //break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MsgInWindow.class.getName()).log(Level.INFO, ex.getMessage());

            }
        }
    }

}
