import hand.agent.Buyer;
import hand.agent.Seller;
import hand.market.Market;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        testSubproblemA();
        testSubproblemB();
        testSubproblemC();
    }

    static void testSubproblemA() {
        System.out.println("<Test of sub-problem (a)>");
        Buyer buyer = new Buyer(200);
        Seller seller = new Seller(100);
        System.out.println("Should be false: " + buyer.willTransact(250));
        System.out.println("Should be true: " + buyer.willTransact(200));
        System.out.println("Should be true: " + seller.willTransact(100));
        System.out.println("Should be false: " + seller.willTransact(50));
        buyer.makeTransaction(); buyer.reflect();
        System.out.println("Should be 190.0: " + buyer.getExpectedPrice());
        seller.makeTransaction(); seller.reflect();
        System.out.println("Should be 110.0: " + seller.getExpectedPrice());
        for(int i=0;i<10;i++) buyer.reflect();
        System.out.println("Should be 200.0: " + buyer.getExpectedPrice());
        for(int i=0;i<10;i++) seller.reflect();
        System.out.println("Should be 100.0: " + seller.getExpectedPrice());
    }

    static void testSubproblemB() {
        System.out.println("<Test of sub-problem (b)>");
        Market market = new Market(0, null, 0, null);
        market.buyers = new ArrayList<>();
        for(int i=0;i<1000;i++) market.buyers.add(new Buyer(i * 5));
        market.sellers = new ArrayList<>();
        for(int i=0;i<1000;i++) market.sellers.add(new Seller(i * 5));
        market.simulate();
        System.out.println("Should be 0.0 : " + market.buyers.get(0).getExpectedPrice());
        System.out.println("Should be 2405.0 : " + market.buyers.get(500).getExpectedPrice());
        System.out.println("Should be 2480.0 : " + market.buyers.get(999).getExpectedPrice());
        System.out.println("Should be 2515.0 : " + market.sellers.get(0).getExpectedPrice());
        System.out.println("Should be 2600.0 : " + market.sellers.get(500).getExpectedPrice());
        System.out.println("Should be 2480.0 : " + market.buyers.get(999).getExpectedPrice());
    }

    static void testSubproblemC() {
        System.out.println("<Test of sub-problem (c)>");
        ArrayList<Double> fb = new ArrayList<>();
        fb.add(1000.0); fb.add(2000.0);
        ArrayList<Double> fs = new ArrayList<>();
        fs.add(2000.0); fs.add(1000.0);
        Market market = new Market(1000, fb, 1000, fs);
        double price = market.simulate();
        System.out.printf("Should be 2292.58824 : %.5f", price);
    }
}
