package security;

import bank.Bank;
import bank.MobileApp;
import security.key.*;
import security.method.*;

public class Protocol {
    final Bank bank;
    public Protocol(Bank bank) {
        this.bank = bank;
    }

    public static void handshake(MobileApp mobileApp, Bank bank){
        BankPublicKey publicKey = bank.getPublicKey();
        Encrypted<BankSymmetricKey> encryptedKey = mobileApp.sendSymKey(publicKey);
        bank.fetchSymKey(encryptedKey, mobileApp.getAppId());
    }

    public static boolean communicate(Deposit deposit, MobileApp mobileApp, Bank bank, int amount) {
        Encrypted<Message> messageEnc = mobileApp.deposit(amount);
        Encrypted<Boolean> response = bank.processRequest(messageEnc,mobileApp.getAppId());
        if(response == null){
            return false;
        }
        return mobileApp.processResponse(response);
    }
    public static boolean communicate(Withdraw withdraw, MobileApp mobileApp, Bank bank, int amount) {
        Encrypted<Message> messageEnc = mobileApp.withdraw(amount);
        Encrypted<Boolean> response = bank.processRequest(messageEnc,mobileApp.getAppId());
        if(response == null){
            return false;
        }
        return mobileApp.processResponse(response);
    }
}

