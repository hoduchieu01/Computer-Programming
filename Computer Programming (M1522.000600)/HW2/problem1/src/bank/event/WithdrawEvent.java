package bank.event;

public class WithdrawEvent extends Event {
    @Override
    public String toCustomString() {
        return "WITHDRAW";
    }
}
