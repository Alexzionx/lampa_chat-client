package lampachat_client;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        LampaChat_client la = new LampaChat_client();
        la.start();
    }

}
