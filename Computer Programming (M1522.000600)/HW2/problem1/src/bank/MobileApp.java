package bank;

import security.key.BankPublicKey;
import security.key.BankSymmetricKey;
import security.*;

public class MobileApp {

    private String randomUniqueStringGen(){
        return Encryptor.randomUniqueStringGen();
    }
    private final String AppId = randomUniqueStringGen();
    public String getAppId() {
        return AppId;
    }

    String id, password;
    public MobileApp(String id, String password){
        this.id = id;
        this.password = password;
    }

    BankSymmetricKey symKey;

    public Encrypted<BankSymmetricKey> sendSymKey(BankPublicKey publickey){
        //TODO: Problem 1.3
        symKey = new BankSymmetricKey(randomUniqueStringGen());
        Encrypted<BankSymmetricKey> message = new Encrypted<BankSymmetricKey>(symKey, publickey);
        return message;
    }

    public Encrypted<Message> deposit(int amount){
        //TODO: Problem 1.3
        Message depositMessage = new Message("deposit", id, password, amount);
        Encrypted<Message> message = new Encrypted<Message>(depositMessage, symKey);
        return message;
    }

    public Encrypted<Message> withdraw(int amount){
        //TODO: Problem 1.3
        Message withdrawMessage = new Message("withdraw", id, password, amount);
        Encrypted<Message> message = new Encrypted<Message>(withdrawMessage, symKey);
        return message;
    }

    public boolean processResponse(Encrypted<Boolean> obj) {
        //TODO: Problem 1.3
        if(obj == null)
            return false;
        Boolean response = obj.decrypt(symKey);
        if(response == null)
            return false;
        return response;
    }
}

