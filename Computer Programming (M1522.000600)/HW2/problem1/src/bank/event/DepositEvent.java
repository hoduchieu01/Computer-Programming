package bank.event;

public class DepositEvent extends Event {
    @Override
    public String toCustomString() {
        return "DEPOSIT";
    }
}
