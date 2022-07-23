import bank.Bank;
import bank.MobileApp;
import bank.Session;
import bank.SessionManager;
import bank.event.*;
import security.Protocol;
import security.method.Deposit;
import security.method.Withdraw;

public class Test {
    public static void main(String[] args) {
        System.out.println("1.1 Test Cases -----------------------------");
        subproblem1();
        System.out.println("1.2 Test Cases -----------------------------");
        subproblem2();
        System.out.println("1.3 Test Cases -----------------------------");
        subproblem3();
    }
    static void subproblem1() {
        Bank bank = new Bank();
        int b1,b2,b3;
        String janePW = "1234asdf";
        String evaPW = "5678ghkj";
        String janeid = "Jane";
        String evaid = "Eva";
        bank.createAccount(janeid, janePW );
        bank.createAccount(evaid, evaPW, 1000);

        String wrongID = "MelloMello";
        String wrongPW = "abcdefg";
        bank.deposit(janeid, janePW, 500);
        bank.deposit(janeid, wrongPW, 3900);
        bank.deposit(wrongID, wrongPW, 2800);
        bank.deposit(evaid, evaPW, 6200);
        bank.deposit(evaid, wrongPW, 3200);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        b3 = bank.getBalance(wrongID,evaPW);
        printOX("1.1.1. deposit, getBalance & their robustness to wrong id and passwd",b1 == 500 && b2 == 7200 && b3 < 0);

        bank.withdraw(janeid, janePW, 450);
        bank.withdraw(janeid, janePW, 600);
        bank.withdraw(janeid, wrongPW, 600);
        bank.withdraw(wrongID, janePW, 300);
        bank.withdraw(evaid, evaPW, 2400);
        bank.withdraw(evaid, wrongPW, 2200);
        bank.withdraw(evaid, evaPW, 78200);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.1.2. withdraw & their robustness to wrong id and passwd",b1 == 50 && b2 == 4800 );
        ;
        bank.deposit(evaid, evaPW, 2341);
        bank.deposit(janeid, janePW, 532);
        bank.withdraw(janeid, janePW, 6623);
        bank.deposit(janeid, janePW, 2220);;
        bank.deposit(evaid, evaPW, 6200);
        bank.withdraw(evaid, evaPW, 2400);
        bank.deposit(janeid, janePW, 5600);
        bank.withdraw(janeid, janePW, 4150);
        bank.withdraw(evaid, evaPW, 257);
        bank.withdraw(janeid, janePW, 452);;
        bank.deposit(evaid, evaPW, 6120);
        bank.withdraw(janeid, janePW, 40);
        bank.withdraw(evaid, evaPW, 10000);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.1.3. deposit + withdraw ",b1 == 3760 && b2 == 6804 );

        bank.transfer(janeid, evaPW, evaid, 300);
        bank.transfer(janeid, janePW, evaid, 652);
        bank.transfer(evaid, evaPW, janeid, 3200);
        bank.transfer(evaid, evaPW, janeid, 310);
        bank.transfer(janeid, janePW, evaid, 310);
        bank.transfer(evaid, wrongPW, janeid, 200);
        bank.transfer(evaid, evaPW, wrongID, 120);
        bank.transfer(janeid, janePW, evaid, 8210);
        bank.transfer(evaid, wrongPW, wrongID, 512);
        bank.transfer(wrongID, wrongPW, janeid, 512);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.1.4. transfer & their robustness to wrong id and passwd",b1 == 6308 && b2 == 4256 );

        bank.withdraw(evaid, evaPW, 230);
        bank.transfer(janeid, janePW, evaid, 520);
        bank.deposit(evaid, evaPW, 2300);
        bank.deposit(janeid, janePW, 5320);
        bank.deposit(evaid, evaPW, 2100);
        bank.withdraw(janeid, janePW, 322);
        bank.deposit(evaid, evaPW, 19);
        bank.transfer(evaid, evaPW, janeid, 3270);
        bank.deposit(janeid, janePW, 777);
        bank.transfer(janeid, janePW, evaid, 9337);
        bank.deposit(janeid, janePW, 555);
        bank.transfer(evaid, evaPW, janeid, 15034);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.1.5. deposit + withdraw + transfer ",b1 == 6051 && b2 == 15032 );


        Event[] events1,events2;
        char d = 'd', w = 'w' ,s='s',r='r';
        events1 = bank.getEvents(janeid, janePW);
        events2 = bank.getEvents(evaid, evaPW);
        printOX("1.1.6. getEvents ",
                compareEvents(events1,new char[]{d,w,d,d,d,w,w,w,s,r,r,s,s,d,w,r,d,s,d}) &&
                        compareEvents(events2,new char[]{d,w,d,d,w,w,d,w,r,s,s,r,w,r,d,d,d,s,r}));
    }
    static void subproblem2() {
        Bank bank = new Bank();
        Bank bank2 = new Bank();
        int b1,b2,b3,b4;
        String janePW = "1234asdf";
        String evaPW = "5678ghkj";
        String janeid = "Jane";
        String evaid = "Eva";
        bank.createAccount(janeid, janePW , 6051);
        bank.createAccount(evaid, evaPW, 15032);

        bank2.createAccount(janeid, janePW , 6051);
        bank2.createAccount(evaid, evaPW, 15032);


        Session janesession1 = SessionManager.generateSession(janeid, janePW, bank);
        Session evasession1 = SessionManager.generateSession(evaid, evaPW, bank);
        Session janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
        Session evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);

        boolean condition = janesession1 != null && janesession2 != null && evasession1 != null && evasession2 != null;
        printOX("1.2.1. Implementation of 1.1 Done ",
                condition);
        if(condition) {
            evasession2.deposit(445);
            evasession1.deposit(2);
            janesession2.deposit(999);
            janesession2.deposit(266);
            janesession2.deposit(250);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            janesession1.deposit(5210);
            evasession2.deposit(1);
            janesession2.deposit(731);
            janesession1.deposit(1230);
            evasession1.deposit(2210);
            janesession1.deposit(20);
            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            evasession2.deposit(929);
            evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);
            b1 = bank.getBalance(janeid, janePW);
            b2 = bank2.getBalance(janeid, janePW);
            b3 = bank.getBalance(evaid, evaPW);
            b4 = bank2.getBalance(evaid, evaPW);
            printOX("1.2.2. Session deposit + getBalance ", b1 == 12511 && b2 == 8297 && b3 == 17244 && b4 == 16407);

            janesession2.withdraw(8296);
            janesession2.withdraw(24);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            janesession2.withdraw(24700);
            evasession2.withdraw(3);
            evasession2.withdraw(2);
            evasession1.withdraw(7244);
            evasession1 = SessionManager.generateSession(evaid, evaPW, bank);
            evasession1.withdraw(555);
            janesession2.withdraw(1);
            janesession1.withdraw(2);
            evasession2.withdraw(1);
            evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);
            janesession1.withdraw(12000);
            janesession1.withdraw(7800);
            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            b1 = bank.getBalance(janeid, janePW);
            b2 = bank2.getBalance(janeid, janePW);
            b3 = bank.getBalance(evaid, evaPW);
            b4 = bank2.getBalance(evaid, evaPW);
            printOX("1.2.3. Session withdraw ", b1 == 509 && b2 == 0 && b3 == 9445 && b4 == 16401);

            evasession1.transfer(janeid, 670);
            janesession1.transfer(evaid, 6423);
            janesession2.transfer(evaid, 22222);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            janesession1.transfer(evaid, 23);
            janesession2.transfer(evaid, 445);
            evasession1.transfer(janeid, 11);
            evasession1 = SessionManager.generateSession(evaid, evaPW, bank);
            evasession2.transfer(janeid, 11);
            b1 = bank.getBalance(janeid, janePW);
            b2 = bank2.getBalance(janeid, janePW);
            b3 = bank.getBalance(evaid, evaPW);
            b4 = bank2.getBalance(evaid, evaPW);
            printOX("1.2.4. Session transfer ", b1 == 1167 && b2 == 11 && b3 == 8787 && b4 == 16390);

            janesession1.deposit(5210);
            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            janesession2.withdraw(8296);
            janesession2.deposit(266);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            evasession1.transfer(janeid, 670);
            evasession2.deposit(445);
            evasession2.withdraw(3);
            evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);
            janesession1.transfer(evaid, 23);
            janesession2.withdraw(24);
            evasession2.transfer(janeid, 11);
            janesession1.transfer(evaid, 6423);
            janesession2.deposit(731);
            janesession1.withdraw(2);
            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            evasession1.deposit(2210);
            janesession1.withdraw(12000);
            janesession2.withdraw(24700);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            evasession1.deposit(2);
            evasession1 = SessionManager.generateSession(evaid, evaPW, bank);
            janesession2.transfer(evaid, 22222);
            evasession1.withdraw(555);
            evasession2.deposit(1);
            evasession1.transfer(janeid, 11);
            evasession1.withdraw(7244);
            evasession1 = SessionManager.generateSession(evaid, evaPW, bank);
            janesession1.deposit(1230);
            evasession2.withdraw(1);
            evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);
            janesession2.deposit(250);
            janesession2.withdraw(1);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            janesession1.withdraw(7800);
            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            evasession2.deposit(929);
            janesession2.deposit(999);
            janesession2.transfer(evaid, 445);
            janesession1.deposit(20);
            evasession2.withdraw(2);
            b1 = bank.getBalance(janeid, janePW);
            b2 = bank2.getBalance(janeid, janePW);
            b3 = bank.getBalance(evaid, evaPW);
            b4 = bank2.getBalance(evaid, evaPW);
            printOX("1.2.5. Session deposit + withdraw + transfer", b1 == 1860 && b2 == 1798 && b3 == 8965 && b4 == 18193);


            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            evasession1 = SessionManager.generateSession(evaid, evaPW, bank);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);

            janesession1.deposit(5210);
            janesession2.withdraw(8296);
            janesession2.deposit(266);
            evasession1.transfer(janeid, 670);
            evasession2.deposit(445);
            evasession2.withdraw(3);
            janesession1.transfer(evaid, 23);
            janesession2.withdraw(24);
            //janesession2 expired
            evasession2.transfer(janeid, 11);
            //evasession2 expired
            janesession1.transfer(evaid, 6423);
            //janesession1 expired
            janesession2.deposit(731);
            janesession1.withdraw(2);
            evasession1.deposit(2210);
            SessionManager.expireSession(evasession1);
            janesession1.withdraw(12000);
            janesession2.withdraw(24700);
            evasession1.deposit(2);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            janesession2.transfer(evaid, 22222);
            evasession1.withdraw(555);
            evasession2.deposit(1);
            evasession1.transfer(janeid, 11);
            evasession1 = SessionManager.generateSession(evaid,evaPW,bank);
            evasession1.withdraw(7244);
            janesession1.deposit(1230);
            evasession2 = SessionManager.generateSession(evaid, evaPW, bank2);
            evasession2.withdraw(1);
            janesession2.deposit(250);
            SessionManager.expireSession(janesession2);
            janesession2.withdraw(1);
            janesession1 = SessionManager.generateSession(janeid, janePW, bank);
            janesession1.withdraw(7800);
            SessionManager.expireSession(evasession2);
            evasession2.deposit(929);
            janesession2 = SessionManager.generateSession(janeid, janePW, bank2);
            janesession2.deposit(999);
            janesession2.transfer(evaid, 445);
            janesession1.deposit(20);
            evasession2.withdraw(2);
            b1 = bank.getBalance(janeid, janePW);
            b2 = bank2.getBalance(janeid, janePW);
            b3 = bank.getBalance(evaid, evaPW);
            b4 = bank2.getBalance(evaid, evaPW);
            printOX("1.2.6. expire Session", b1 == 1314 && b2 == 2855 && b3 == 9707 && b4 == 19068);
        }
    }
    static void subproblem3() {
        Bank bank = new Bank();
        Integer b1,b2,b3;
        boolean bool1,bool2;
        String janePW = "1234asdf";
        String evaPW = "5678ghkj";
        String janeid = "Jane";
        String evaid = "Eva";
        bank.createAccount(janeid, janePW );
        bank.createAccount(evaid, evaPW, 1000);
        MobileApp jane = new MobileApp(janeid, janePW);
        MobileApp eva = new MobileApp(evaid, evaPW);
        Protocol.handshake(jane,bank);
        Protocol.communicate(new Deposit(),jane, bank,600);
        Protocol.communicate(new Deposit(),jane, bank, 768);
        Protocol.communicate(new Deposit(),jane, bank, 123);
        Protocol.handshake(eva,bank);
        Protocol.communicate(new Deposit(),eva, bank, 928);
        Protocol.communicate(new Deposit(),eva, bank, 1221);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.3.1. deposit with secure connection", b1 == 1491 && b2 == 3149 );

        Protocol.handshake(eva,bank);
        Protocol.handshake(jane,bank);
        Protocol.communicate(new Withdraw(),jane, bank, 491);
        Protocol.communicate(new Withdraw(),eva, bank, 928);
        Protocol.communicate(new Withdraw(),jane, bank, 231);
        Protocol.communicate(new Withdraw(),eva, bank, 21);
        Protocol.communicate(new Withdraw(),eva, bank, 3150);
        Protocol.communicate(new Withdraw(),jane, bank, 1500);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.3.2. withdraw with secure connection", b1 == 769 && b2 == 2200 );

        Protocol.handshake(jane,bank);
        Protocol.handshake(eva,bank);
        Protocol.communicate(new Deposit(),jane, bank, 3900);
        Protocol.communicate(new Deposit(),eva, bank, 5000);
        bank.transfer(janeid, janePW,  evaid,20);
        bank.transfer(evaid, evaPW,  janeid,320);
        bank.transfer(janeid, janePW,  evaid,1100);
        bank.transfer(janeid, janePW,  evaid,1000);
        bank.transfer(evaid, evaPW,  janeid,1925);
        bank.transfer(janeid, janePW,  evaid,62000);
        bank.transfer(evaid, evaPW,  janeid,7076);

        bank.transfer(evaid, evaPW,  janeid,1925);
        bank.transfer(janeid, janePW,  evaid,1000);
        Protocol.communicate(new Withdraw(),eva, bank, 3150);
        bank.transfer(janeid, janePW,  evaid,1000);
        Protocol.communicate(new Withdraw(),jane, bank, 231);
        bank.transfer(janeid, janePW,  evaid,1100);
        b1 = bank.getBalance(janeid,janePW);
        Protocol.communicate(new Withdraw(),jane, bank, 231);
        Protocol.communicate(new Withdraw(),jane, bank, 1500);
        bool1 = bank.transfer(evaid, evaPW,  janeid,7076);
        Protocol.communicate(new Withdraw(),eva, bank, 928);
        bank.transfer(evaid, evaPW, janeid,320);
        Protocol.communicate(new Deposit(),jane, bank, 123);
        Protocol.communicate(new Withdraw(),jane, bank, 491);
        b2 = bank.getBalance(evaid,evaPW);
        Protocol.communicate(new Withdraw(),eva, bank,21);
        Protocol.communicate(new Withdraw(),jane, bank,491);
        Protocol.communicate(new Deposit(),jane, bank,123);
        Protocol.communicate(new Withdraw(),eva, bank,928);
        Protocol.communicate(new Deposit(),jane, bank,768);
        bank.transfer(evaid, evaPW,janeid,1925);
        bank.transfer(janeid, janePW,evaid,20);
        bool2 = bank.transfer(janeid, janePW,evaid,62000);
        Protocol.communicate(new Withdraw(),eva, bank, 21);
        bank.transfer(janeid, janePW, evaid,1100);
        Protocol.communicate(new Deposit(),jane, bank,600);
        Protocol.communicate(new Deposit(),jane, bank,768);
        b1 = bank.getBalance(janeid,janePW);
        b2 = bank.getBalance(evaid,evaPW);
        printOX("1.3.3. deposit and withdraw with secure connection",
                b1 == 4182 &&  b2 == 2077 &&
                        !bool1  && !bool2);

        Event[] events1,events2;
        char d = 'd', w = 'w' ,s='s',r='r';
        events1 = bank.getEvents(janeid,janePW);
        events2 = bank.getEvents(evaid,evaPW);
        printOX("1.3.4. getEvents with secure connection",
                events1 != null && compareEvents(events1,new char[]{d,d,d,w,w,d,s,r,s,s,r,r,s,s,w,s,w,w,r,d,w,w,d,d,r,s,s,d,d}) &&
                        events2 != null && compareEvents(events2,new char[]{d,d,w,w,d,r,s,r,r,s,s,r,w,r,r,w,s,w,w,s,r,w,r}) );

    }
    static void printOX(String prompt,boolean condition){
        if(condition){
            System.out.println("" + prompt + " | O");
        }
        else{
            System.out.println("" + prompt + " | X");
        }
    }
    static void print(Object o){
        System.out.println(o);
    }
    static void print(Event[] events){
        for(Event e : events){
            System.out.println(e);
        }
    }
    static boolean compareEvents(Event[] events,char[] answer ){
        if(events == null){
            return false;
        }
        if(events.length != answer.length){
            return false;
        }
        for(int i = 0; i < events.length; i++){
            switch(answer[i]){
                case 'd':
                    if (!(events[i] instanceof DepositEvent)){
                        return false;
                    }
                    break;
                case 'r':
                    if (!(events[i] instanceof ReceiveEvent)){
                        return false;
                    }
                    break;
                case 's' :
                    if (!(events[i] instanceof SendEvent)){
                        return false;
                    }
                    break;
                case 'w' :
                    if (!(events[i] instanceof WithdrawEvent)){
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
