package bank;

import bank.event.*;

class BankAccount {
    private Event[] events = new Event[maxEvents];
    final static int maxEvents = 100;

    // Initialize id, password, and balance attributes with the given parameter values
    private String id;
    private String password;
    private int balance;

    // Initialize current Event
    private int currentEvent = 0;

    BankAccount(String id, String password, int balance) {
        //TODO: Problem 1.1
        // Construct the BankAccount  object
        this.id = id;
        this.password = password;
        this.balance = balance;
    }


    boolean authenticate(String password) {
        //TODO: Problem 1.1
        // Check if the account's password is equal to the given password
        // Return true if and only if the password strings are equal
        return this.password.equals(password);
    }

    void deposit(int amount) {
        //TODO: Problem 1.1
        // Add the amount to the balance
        // Add a DepositEvent to the events array
        this.balance += amount;
        events[currentEvent++] = new DepositEvent();
    }

    boolean withdraw(int amount) {
        //TODO: Problem 1.1
        // Check if the balance is larger than or equal to the amount. If yes, subtract the amount from the balance
        // Add a WithdrawEvent object to the events array
        // Otherwise return false
        if(this.balance >= amount){
            this.balance -= amount;
            events[currentEvent++] = new WithdrawEvent();
            return true;
        }
        return false;
    }

    void receive(int amount) {
        //TODO: Problem 1.1
        // Add a balance by the amount, add a ReceiveEvent object to the events array
        this.balance += amount;
        events[currentEvent ++] = new ReceiveEvent();
    }

    boolean send(int amount) {
        //TODO: Problem 1.1
        // Check if the balance is larger than or equal to the amount. If yes, subtract the amount from the balance
        // Add the Send Event object to the events array and return true
        // Otherwise return false
        if(this.balance >= amount){
            this.balance -= amount;
            events[currentEvent ++] = new SendEvent();
            return true;
        }
        return false;
    }

    Event[] getEvents(){
        Event[] transactionsEvent = new Event[currentEvent];
        for(int i = 0; i < currentEvent; i++){
            transactionsEvent[i] = events[i];
        }
        return transactionsEvent;
    }

    int getBalance(){
        return this.balance;
    }

}
