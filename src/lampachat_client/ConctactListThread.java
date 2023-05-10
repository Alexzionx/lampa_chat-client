package lampachat_client;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConctactListThread implements Runnable {

    private Thread self;
    private JHome form;

    public ConctactListThread(JHome home) {
        this.form = home;
        self = new Thread(this);
        self.start();
    }

    @Override
    public void run() {
        Database db = new Database();
        while (true) {
            System.out.print("");
            if (JHome.contactLIstCount < db.ReadContactCount()) {
                ArrayList<String> list = new ArrayList<>();
                JHome.contactLIstCount = db.ReadContactCount();
                for (int i = 1; i <= JHome.contactLIstCount; i++) {
                    list.add(db.ReadContactList(i));
                }
                form.setContactList(list);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConctactListThread.class.getName()).log(Level.INFO, ex.getMessage());

            }
        }
    }
}
