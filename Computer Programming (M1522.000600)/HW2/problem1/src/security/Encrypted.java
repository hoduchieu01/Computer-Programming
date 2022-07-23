package security;

import security.key.BankKey;
import security.key.BankPublicKey;
import security.key.BankSecretKey;
import security.key.BankSymmetricKey;

public class Encrypted<T> {

    private T object;
    private String ciphertext;

    public T decrypt(BankSecretKey key){
        String plaintext = Encryptor.decrypt(this.ciphertext,key);
        if(plaintext.equals(Encryptor.samplePlaintext)){
            return object;
        }
        else{
            // Failed to decrypt
            return null;
        }
    }
    public T decrypt(BankSymmetricKey key){
        String plaintext = Encryptor.decrypt(this.ciphertext,key);
        if(plaintext.equals(Encryptor.samplePlaintext)){
            return object;
        }
        else{
            // Failed to decrypt
            return null;
        }
    }

    public Encrypted(T obj, BankPublicKey key){
        this.object = obj;
        this.ciphertext = Encryptor.encrypt(Encryptor.samplePlaintext,key);
    }

    public Encrypted(T obj, BankSymmetricKey key){
        this.object = obj;
        this.ciphertext = Encryptor.encrypt(Encryptor.samplePlaintext,key);
    }
}
