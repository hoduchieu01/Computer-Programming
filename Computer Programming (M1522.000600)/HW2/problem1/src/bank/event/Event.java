package bank.event;

public abstract class Event {
    @Override
    public String toString() {
        return toCustomString();
    }
    public abstract String toCustomString();
}

