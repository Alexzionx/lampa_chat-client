package lampachat_client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Options {

    private Properties p = new Properties();

    private final int default_port1 = 30305;
    private final int default_port2 = default_port1 + 1;
    private final String default_server_adress = "localhost";
    private final boolean default_sound_notifications = true;
    private final String default_lang = "English";

    private static String server_adress = "localhost";
    private static int port1 = 30305;
    private static int port2 = 30306;
    private static boolean sound_notifications = true;
    private static String lang = "English";

    public void readOptions() {
        try {
            p.load(new FileInputStream(new File("config/options.properties")));
            server_adress = p.getProperty("server_adress");
            port1 = Integer.parseInt(p.getProperty("server_port_1"));
            port2 = Integer.parseInt(p.getProperty("server_port_2"));
            sound_notifications = Boolean.parseBoolean(p.getProperty("sounds"));
            lang = p.getProperty("lang");
            System.out.println("-++------Options");
            System.out.println("-++------server_adress-" + server_adress);
            System.out.println("-++------port1-" + port1);
            System.out.println("-++------port2-" + port2);
            System.out.println("-++------sound_notifications-" + sound_notifications);
            System.out.println("-++------lang-" + lang);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, "Config file \"config/options.properties\" not found!");
            Logger.getLogger(Options.class.getName()).log(Level.INFO, "Creating default config file!");
            Logger.getLogger(Options.class.getName()).log(Level.INFO, ex.getMessage());
            setDefaultConfigFile();
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, ex.getMessage());

        }
    }

    public void setOptionsInFile(String serverAdress, int port1, int port2, boolean sounds, String lang) throws IOException {
        String str = "# ---Properties file for Lampa_chat Client---\n"
                + "#\n"
                + "#\n"
                + "#\n"
                + "server_adress = " + serverAdress + "\n"
                + "server_port_1 = " + port1 + "\n"
                + "server_port_2 = " + port2 + "\n"
                + "sounds = " + sounds + "\n"
                + "lang = " + lang;
        Path path = Paths.get("config/options.properties");
        Files.writeString(path, str, StandardCharsets.UTF_8);

    }

    public void createDirectoryStructure() {
        try {
            String baseFolder = "base";
            Files.createDirectory(Paths.get(baseFolder));
        } catch (FileAlreadyExistsException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, ex.getMessage());
        }
        try {
            String configFolder = "config";
            Files.createDirectory(Paths.get(configFolder));
        } catch (FileAlreadyExistsException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, ex.getMessage());
        }
    }

    public void setDefaultConfigFile() {
        try {
            BufferedWriter writer = null;
            String str = "# ---Properties file for Lampa_chat Client---\n"
                    + "#\n"
                    + "#\n"
                    + "#\n"
                    + "server_adress = " + getServer_adress() + "\n"
                    + "server_port_1 = " + port1 + "\n"
                    + "server_port_2 = " + port2 + "\n"
                    + "sounds = " + isSound_notifications() + "\n"
                    + "lang = " + lang;
            writer = new BufferedWriter(new FileWriter("config/options.properties"));
            writer.write(str);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, ex.getMessage());
        }
    }

    public static int getPort1() {
        return port1;
    }

    public static int getPort2() {
        return port2;
    }

    public static String getServer_adress() {
        return server_adress;
    }

    public static boolean isSound_notifications() {
        return sound_notifications;
    }

    public static String getLang() {
        return lang;
    }

    public int getDefault_port1() {
        return default_port1;
    }

    public int getDefault_port2() {
        return default_port2;
    }

    public String getDefault_server_adress() {
        return default_server_adress;
    }

    public boolean isDefault_sound_notifications() {
        return default_sound_notifications;
    }

    public String getDefault_lang() {
        return default_lang;
    }

}
