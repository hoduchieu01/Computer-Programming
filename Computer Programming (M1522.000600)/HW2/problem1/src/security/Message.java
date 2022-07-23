package security;

public class Message {
    private String id;
    private String password;
    private String requestType;
    private int amount;

    public Message(String requestType, String id, String password, int amount){
        this.requestType = requestType;
        this.id = id;
        this.password = password;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRequestType() {
        return requestType;
    }
}
