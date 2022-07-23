package security.key;

public class BankKeyPair {
    public BankPublicKey enckey; public BankSecretKey deckey;
    public BankKeyPair(BankPublicKey key, BankSecretKey value){
        this.enckey = key;
        this.deckey = value;
    }
}
