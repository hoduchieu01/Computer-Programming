package bank.event;

public class ReceiveEvent extends Event {
    @Override
    public String toCustomString() {
        return "RECEIVE";
    }
}
