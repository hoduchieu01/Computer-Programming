package bank;

public class Session {

    private String sessionKey;
    private Bank bank;
    private boolean valid;
    private int transLimit = 3;
    private int countTrans = 0;

    Session(String sessionKey,Bank bank){
        this.sessionKey = sessionKey;
        this.bank = bank;
        valid = true;
    }

    public boolean deposit(int amount) {
        //TODO: Problem 1.2
        if(valid && countTrans < transLimit){
            countTrans += 1;
            return bank.deposit(sessionKey, amount);
        }
        return false;
    }

    public boolean withdraw(int amount) {
        //TODO: Problem 1.2
        if(valid && countTrans < transLimit){
            countTrans += 1;
            return bank.withdraw(sessionKey, amount);
        }
        return false;
    }

    public boolean transfer(String targetId, int amount) {
        //TODO: Problem 1.2
        if(valid && countTrans < transLimit) {
            countTrans += 1;
            return bank.transfer(sessionKey, targetId, amount);
        }
        return false;
    }

    public void expireSession(){
        valid = false;
    }
}