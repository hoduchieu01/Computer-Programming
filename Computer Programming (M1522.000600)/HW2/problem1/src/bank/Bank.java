package bank;

import bank.event.*;
import security.*;
import security.key.*;

import java.util.HashMap;

public class Bank {
    private int numAccounts = 0;
    final static int maxAccounts = 100;
    private BankAccount[] accounts = new BankAccount[maxAccounts];
    private String[] ids = new String[maxAccounts];

    public void createAccount(String id, String password) {
        createAccount(id, password, 0);
    }

    public void createAccount(String id, String password, int initBalance) {
        //TODO: Problem 1.1

        // Create a BankAccount object with the given account id, password and initial balance

        int accountID = numAccounts;
        accounts[accountID] = new BankAccount(id, password, initBalance);
        ids[accountID] = id;
        numAccounts += 1;
    }

    public boolean deposit(String id, String password, int amount) {
        //TODO: Problem 1.1
        BankAccount clientAccount = this.find(id);
        if(clientAccount == null) return false;

        if(clientAccount.authenticate(password)){
            clientAccount.deposit(amount);
            return true;
        }
        return false;
        // authenticate the client with the id and password
        // if the authentication is not successful, do nothing and return false
        // if the authentication is successful, add the amount to the balance and return true.
        // Use the deposit method of the BankAccount class
    }

    public boolean withdraw(String id, String password, int amount) {
        //TODO: Problem 1.1
        BankAccount clientAccount = this.find(id);
        if(clientAccount == null) return false;

        if(clientAccount.authenticate(password)){
            clientAccount.withdraw(amount);
            return true;
        }
        return false;
    }

    public boolean transfer(String sourceId, String password, String targetId, int amount) {
        //TODO: Problem 1.1

        BankAccount sourceAccount = this.find(sourceId);
        BankAccount targetAccount = this.find(targetId);
        // if there is no account with the given sourceId and targetId do nothing and return false
        if(sourceAccount == null || targetAccount == null) return false;
        // authentication the source account with the sourceId and password
        // if authentication is not successful, do nothing and return false
        // if authentication is successful, transfer the amount to the targetId's account
        // return false if the amount is the larger than the balance of the source account
        // use the send and receive method of the Bank Account class
        if(sourceAccount.authenticate(password)){
            if(sourceAccount.send(amount)){
                targetAccount.receive(amount);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public Event[] getEvents(String id, String password) {
        //TODO: Problem 1.1

        BankAccount clientAccount = find(id);
        if(clientAccount == null)
            return null;
        if(!clientAccount.authenticate(password))
            return null;
        return clientAccount.getEvents();
    }

    public int getBalance(String id, String password) {
        //TODO: Problem 1.1
        BankAccount clientAccount = find(id);
        if(clientAccount == null)
            return -1;
        if(clientAccount.authenticate(password)){
            return clientAccount.getBalance();
        }
        return -1;
    }


    private static String randomUniqueStringGen(){
        return Encryptor.randomUniqueStringGen();
    }
    private BankAccount find(String id) {
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)){return accounts[i];};
        }
        return null;
    }
    final static int maxSessionKey = 100;
    int numSessionKey = 0;
    String[] sessionKeyArr = new String[maxSessionKey];
    BankAccount[] bankAccountmap = new BankAccount[maxSessionKey];
    String generateSessionKey(String id, String password){
        BankAccount account = find(id);
        if(account == null || !account.authenticate(password)){
            return null;
        }
        String sessionkey = randomUniqueStringGen();
        sessionKeyArr[numSessionKey] = sessionkey;
        bankAccountmap[numSessionKey] = account;
        numSessionKey += 1;
        return sessionkey;
    }
    BankAccount getAccount(String sessionkey){
        for(int i = 0 ;i < numSessionKey; i++){
            if(sessionKeyArr[i] != null && sessionKeyArr[i].equals(sessionkey)){
                return bankAccountmap[i];
            }
        }
        return null;
    }

    boolean deposit(String sessionkey, int amount) {
        //TODO: Problem 1.2
        BankAccount clientAccount = getAccount(sessionkey);
        if(clientAccount == null) return false;
        clientAccount.deposit(amount);
        return true;
    }

    boolean withdraw(String sessionkey, int amount) {
        //TODO: Problem 1.2
        BankAccount clientAccount = getAccount(sessionkey);
        if(clientAccount == null) return false;
        return clientAccount.withdraw(amount);
    }

    boolean transfer(String sessionkey, String targetId, int amount) {
        //TODO: Problem 1.2
        BankAccount sourceAccount = getAccount(sessionkey);
        BankAccount targetAccount = find(targetId);
        if(sourceAccount == null || targetAccount == null) return false;
        if(sourceAccount.send(amount)){
            targetAccount.receive(amount);
            return true;
        }
        return false;
    }

    private BankSecretKey secretKey;
    public BankPublicKey getPublicKey(){
        BankKeyPair keypair = Encryptor.publicKeyGen(); // generates two keys : BankPublicKey, BankSecretKey
        secretKey = keypair.deckey; // stores BankSecretKey internally
        return keypair.enckey;
    }

    int maxHandshakes = 10000;
    int numSymmetrickeys = 0;
    BankSymmetricKey[] bankSymmetricKeys = new BankSymmetricKey[maxHandshakes];
    String[] AppIds = new String[maxHandshakes];

    public int getAppIdIndex(String AppId){
        for(int i=0; i<numSymmetrickeys; i++){
            if(AppIds[i].equals(AppId)){
                return i;
            }
        }
        return -1;
    }

    private HashMap<String, BankSymmetricKey> symKeyHashMap = new HashMap<String, BankSymmetricKey>();

    public void fetchSymKey(Encrypted<BankSymmetricKey> encryptedKey, String AppId){
        //TODO: Problem 1.3
        if(encryptedKey == null){
            return;
        }
        BankSymmetricKey symKey = encryptedKey.decrypt(secretKey);
        if(symKey == null) return;
        symKeyHashMap.put(AppId, symKey);
    }

    public Encrypted<Boolean> processRequest(Encrypted<Message> messageEnc, String AppId) {
        //TODO: Problem 1.3
        if(symKeyHashMap.containsKey(AppId) == false || messageEnc == null)
            return null;

        BankSymmetricKey symKey = symKeyHashMap.get(AppId);
        Message message = messageEnc.decrypt(symKey);
        if(message == null){
            return null;
        }
        boolean res = false;
        if(message.getRequestType().equals("deposit")){
            res = deposit(message.getId(), message.getPassword(), message.getAmount());
        }
        if(message.getRequestType().equals("withdraw"))
            res = withdraw(message.getId(), message.getPassword(), message.getAmount());
        return new Encrypted<>(res, symKey);
    }
}