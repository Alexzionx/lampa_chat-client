package lampachat_client;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database extends org.sqlite.JDBC {

    private static final String DRIVER_NAME = "org.sqlite.JDBC";
    private static final String DATABASE_FILE = "base/database.db";
    private static final String DATABASE_FILESQL = "jdbc:sqlite:" + DATABASE_FILE;

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_FILESQL);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return conn;
    }

    public void createNewTableMessages(String table) {
        System.out.println("->createNewTableMessages(" + table + ")");
        if (table.isEmpty()) {
            System.out.println("->createNewTableMessages(" + table + ") BREADKL");
            return;
        }
        String sql = "CREATE TABLE IF NOT EXISTS " + table + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	from_user text NOT NULL,\n"
                + "	to_user text NOT NULL,\n"
                + "	message text NOT NULL,\n"
                + "     time text NOT NULL\n"
                + ");";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, "->createNewTableMessages(" + table + ")-" + e.getMessage());
        }
    }

    public void createNewTableContacts() {
        System.out.println("->createNewTableContacts");
        String sql = "CREATE TABLE IF NOT EXISTS Contacts (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	userName text NOT NULL\n"
                + ");";
        String sql2 = "DELETE FROM Contacts";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.execute(sql2);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
    }

    public void createNewTableIdentification() {
        System.out.println("->createNewTableIdentification");
        String s = "CREATE TABLE IF NOT EXISTS identification (id integer PRIMARY KEY,login text NOT NULL,password text NOT NULL)";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(s);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
    }

    public void insert(String table, String from_user, String to_user, String message, String time) {
        System.out.println("->insert");
        String sql = "INSERT INTO " + table + "(from_user,to_user,message,time) VALUES(?,?,?,?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, from_user);
            pstmt.setString(2, to_user);
            pstmt.setString(3, message);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
    }

    public void addContact(String contact) {
        System.out.println("->addContact(" + contact + ")");
        if (contact.isEmpty()) {
            System.out.println("->addContact(" + contact + ") BREAK");
            return;
        }
        //CREATE TABLE IF NOT EXISTS Contacts (id integer PRIMARY KEY,from_user text NOT NULL)
        //INSERT INTO Contacts (from_user) VALUES("for")
        String sql = "INSERT INTO contacts(userName) VALUES(?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contact);
            pstmt.executeUpdate();
            createNewTableMessages(contact);
            System.out.println("done");
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }

    }

    public String Read(String table) {
        System.out.println("->Read");
        String s = "SELECT * FROM " + table;
        String s2 = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                String from_user = rs.getString(2);
                String to_user = rs.getString(3);
                String message = rs.getString(4);
                s2 = s2 + from_user + to_user + message + "\n";
                System.out.printf("%s. %s - %s \n", from_user, to_user, message);
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return s2;
    }

    public String ReadiD(String table, int id) {
        System.out.println("->ReadiD");
        String s = "SELECT * FROM " + table + " where id=" + id;
        String s2 = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            String from_user = rs.getString(2);
            String to_user = rs.getString(3);
            String message = rs.getString(4);
            s2 = s2 + from_user + to_user + message;
            System.out.printf("%s. %s - %s \n", from_user, to_user, message);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return s2;
    }

    public String[] ReadiDl(String table, int id) {
        System.out.println("->ReadiDl");
        String s = "SELECT * FROM " + table + " where id=" + id;
        String s2[] = {"err", "err", "err", "err"};
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            String from_user = rs.getString(2);
            String to_user = rs.getString(3);
            String message = rs.getString(4);
            String time = rs.getString(5);
            return new String[]{from_user, to_user, message, time};
            //System.out.printf("%s. %s - %s \n", from_user, to_user, message);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return s2;
    }

    public String ReadContactList(int i) {
        System.out.println("->ReadContactList");
        String s = "SELECT * FROM Contacts WHERE id=" + i;
        String s2 = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                String username = rs.getString(2);
                s2 = username;
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return s2;
    }
    //SELECT max(id) FROM Contacts

    public int ReadContactCount() {
        System.out.println("->ReadContactCount");
        String s = "SELECT max(id) FROM Contacts";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                //int username = rs.getInt(1);
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return 0;
    }

    public int ReadMessageCount(String table) {
        System.out.println("->ReadMessageCount(" + table + ")");
        String s = "SELECT max(id) FROM " + table;
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, "->ReadMessageCount(" + table + ")-" + e.getMessage());
        }
        return 0;
    }

    public String ReadLogin() {
        System.out.println("->ReadLogin");
        String s = "SELECT login FROM identification";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            return rs.getString(1);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
            return null;
        }
    }

    public String ReadPassword() {
        System.out.println("->ReadPassword");
        String s = "SELECT password FROM identification";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            return rs.getString(1);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
        return null;
    }

    public void deleteTable(String table) {
        System.out.println("->deleteTable");
        String s = "DROP TABLE IF EXISTS -table-";
        s = s.replaceAll("-table-", table);
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(s);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
    }

    public void setLoginAndPass(String login, String pass) {
        System.out.println("->setLoginAndPass");
        String s1 = "CREATE TABLE IF NOT EXISTS identification (id integer PRIMARY KEY,login text NOT NULL,password text NOT NULL)";
        String s2 = "DELETE FROM identification";
        String s3 = "INSERT INTO identification(login,password) VALUES(\"-login-\",\"-password-\")";
        s3 = s3.replaceAll("-login-", login);
        s3 = s3.replaceAll("-password-", pass);
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(s1);
            stmt.execute(s2);
            stmt.execute(s3);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
        }
    }

    public String getTime() {
        System.out.println("->getTime");
        return ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
                .format(DateTimeFormatter.ofPattern("MM.dd.yyy, hh.mm.ss"));
    }

    public boolean driverStatus() {
        try {
            Class.forName(DRIVER_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, "->driverStatus is fall" + e.getMessage());
            return false;
        }
    }

    public String findInbase(String name) {
        System.out.println("->findInbase");
        String s = "SELECT * FROM 'persons' where name='" + name + "'";
        String findString = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            findString += "ID                  ";
            findString += "NAME                ";
            findString += "AGE                 \n";
            findString += "-------------------+-------------------+--------------------\n";
            while (rs.next()) {
                for (int i = 1; i < 4; i++) {
                    findString += rs.getString(i);
                    for (int j = 0; j < 20 - rs.getString(i).length(); j++) {
                        findString += " ";
                    }
                }
                findString += "\n";
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
            findString = e.getMessage();
        }
        return findString;
    }

    public String findInbase(int age) {
        System.out.println("->findInbase");
        String s = "SELECT * FROM 'persons' where age=" + age + "";
        String findString = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            findString += "ID                  ";
            findString += "NAME                ";
            findString += "AGE                 \n";
            findString += "-------------------+-------------------+--------------------\n";
            while (rs.next()) {
                for (int i = 1; i < 4; i++) {
                    findString += rs.getString(i);
                    for (int j = 0; j < 20 - rs.getString(i).length(); j++) {
                        findString += " ";
                    }

                }
                findString += "\n";
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
            findString = e.getMessage();
        }
        return findString;
    }

    public String showall() {
        System.out.println("->showall");
        String s = "SELECT * FROM 'persons'";
        String findString = "";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(s);
            findString += "ID                  ";
            findString += "NAME                ";
            findString += "AGE                 \n";
            findString += "-------------------+-------------------+--------------------\n";
            while (rs.next()) {
                for (int i = 1; i < 4; i++) {
                    findString += rs.getString(i);
                    for (int j = 0; j < 20 - rs.getString(i).length(); j++) {
                        findString += " ";
                    }
                }
                findString += "\n";
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, e.getMessage());
            findString = e.getMessage();
        }
        return findString;
    }

    public void deletefile() {
        System.out.println("->deletefile");
        File f = new File(DATABASE_FILE);
        f.delete();
        createNewTableContacts();
        createNewTableIdentification();
    }

    public void checkDB() {
        System.out.println("->checkDB");
        String s = "SELECT * FROM identification";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            //ResultSet rs = stmt.executeQuery(s);
            //return rs.getString(1);
            stmt.execute(s);
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.INFO, "create new database file" + e.getMessage());
            deletefile();
        }
    }

}
/*
------------
удалить всё в таблице demo
DELETE FROM demo WHERE id>0
UPDATE identification set login="-login-",password="-password-" WHERE id=1
SELECT max(id) FROM Contacts
------------

 */
