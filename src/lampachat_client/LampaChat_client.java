package lampachat_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LampaChat_client {

    public static void main(String[] args) {
        Options opt = new Options();
        opt.createDirectoryStructure();
        opt.readOptions();

        database database = new database();
        database.checkDB();
        JHome home = new JHome();
        home.setEnabled(true);
        home.setVisible(true);
        MsgThread msgThread = new MsgThread(home);
        msgThread.start();
    }

}
