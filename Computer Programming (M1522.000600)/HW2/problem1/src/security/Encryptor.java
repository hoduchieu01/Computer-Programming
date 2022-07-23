package security;
import security.key.*;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

public class Encryptor {

    // You do not need to use these methods for your implementation.

    public static BankKeyPair publicKeyGen(){
        try{
            SecureRandom secureRandom  = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(
                    2048, secureRandom);
            KeyPair keypair = keyPairGenerator.generateKeyPair();
            PrivateKey secretkey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            String publickeystr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String secretkeystr = Base64.getEncoder().encodeToString(secretkey.getEncoded());
            return new BankKeyPair(new BankPublicKey(publickeystr),new BankSecretKey(secretkeystr));
        }
            catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
    public static final String samplePlaintext = randomUniqueStringGen();

    public static String encrypt(String plaintext, BankPublicKey key){
        try {
            byte[] publickeybytes = Base64.getDecoder().decode(key.value);
            PublicKey publickey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publickeybytes));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publickey);
            byte[] output = cipher.doFinal(plaintext.getBytes());
            return new String(output, "ISO-8859-1");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String encrypt(String plaintext, BankSymmetricKey key){
        return sha256Hex(key.value);
    }
    public static String encrypt(String plaintext, BankKey key){
        try {
            if (key instanceof BankPublicKey) {
                byte[] publickeybytes = Base64.getDecoder().decode(key.value);
                PublicKey publickey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publickeybytes));
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publickey);
                byte[] output = cipher.doFinal(plaintext.getBytes());
                return new String(output, "ISO-8859-1");
            } else {
                return sha256Hex(key.value);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String ciphertext, BankKey key){
        try {
            if (key instanceof BankSecretKey) {
                byte[] secretkeybytes = Base64.getDecoder().decode(key.value);
                PrivateKey privatekey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(secretkeybytes));
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privatekey);
                return new String(cipher.doFinal(ciphertext.getBytes("ISO-8859-1")));
            } else {
                if(ciphertext.equals(sha256Hex(key.value))){
                    return samplePlaintext;
                }
                else{
                    return null;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String ciphertext, BankSecretKey key){
        try {
            byte[] secretkeybytes = Base64.getDecoder().decode(key.value);
            PrivateKey privatekey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(secretkeybytes));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privatekey);
            return new String(cipher.doFinal(ciphertext.getBytes("ISO-8859-1")));
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";

    }
    public static String decrypt(String ciphertext, BankSymmetricKey key){
        if(ciphertext.equals(sha256Hex(key.value))){
            return samplePlaintext;
        }
        else{
            return "";
        }
    }
    private static String sha256Hex(java.lang.String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            return bytes2Hex(md.digest());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
    private static java.lang.String bytes2Hex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(byte b:bytes){
            builder.append(java.lang.String.format("%02x",b));
        }
        return builder.toString();
    }
    public static String randomUniqueStringGen(){
        return UUID.randomUUID().toString();
    }
}

