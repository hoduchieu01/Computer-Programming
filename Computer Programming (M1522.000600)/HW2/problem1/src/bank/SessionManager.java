package bank;

public class SessionManager {

    public static Session generateSession(String id, String password, Bank bank) {
        if(bank == null){
            return null;
        }
        String sessionKey = bank.generateSessionKey(id,password);
        if(sessionKey == null){
            return null;
        }
        return new Session(sessionKey,bank);
    }

    public static void expireSession(Session session){
        //TODO: Problem 1.2
        session.expireSession();
    }
}