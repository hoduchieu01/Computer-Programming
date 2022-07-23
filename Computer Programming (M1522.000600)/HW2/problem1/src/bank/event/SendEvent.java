package bank.event;

public class SendEvent extends Event {
    @Override
    public String toCustomString() {
        return "SEND";
    }
}
