package lampachat_client;

public class MsgFormat {

    private String from;
    private String to;
    private String message;

    public MsgFormat(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getMsg() {
        return ("\u001B[31m" + from + ":" + "\u001B[0m" + "/n" + message);
    }

}
