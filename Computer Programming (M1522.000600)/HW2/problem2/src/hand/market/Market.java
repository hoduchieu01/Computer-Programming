package hand.market;

import hand.agent.Buyer;
import hand.agent.Seller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Pair<K,V> {
    public K key;
    public V value;
    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class Market {
    public ArrayList<Buyer> buyers;
    public ArrayList<Seller> sellers;

    public Market(int nb, ArrayList<Double> fb, int ns, ArrayList<Double> fs) {
        buyers = createBuyers(nb, fb);
        sellers = createSellers(ns, fs);
    }

    private double polynomial(double x, ArrayList<Double> f){
        double res = 0;
        for (int i = 0; i < f.size(); i++){
            res += (double) f.get(i) * Math.pow(x, i);
        }
        return res;
    }

    private ArrayList<Buyer> createBuyers(int n, ArrayList<Double> f) {
        //TODO: Problem 2.3
        ArrayList<Buyer> arrayListBuyer = new ArrayList<Buyer>();
        for(int i = 0; i < n; i++){
            arrayListBuyer.add(i, new Buyer(polynomial((double) (i+1)/n, f)));
        }
        return arrayListBuyer;
    }

    private ArrayList<Seller> createSellers(int n, ArrayList<Double> f) {
        //TODO: Problem 2.3
        ArrayList<Seller> arrayListSeller = new ArrayList<>();
        for (int i = 0; i < n; i++){
            arrayListSeller.add(i, new Seller(polynomial((double) (i+1)/n, f)));
        }
        return arrayListSeller;
    }

    private ArrayList<Pair<Seller, Buyer>> matchedPairs(int day, int round) {
        ArrayList<Seller> shuffledSellers = new ArrayList<>(sellers);
        ArrayList<Buyer> shuffledBuyers = new ArrayList<>(buyers);
        Collections.shuffle(shuffledSellers, new Random(71 * day + 43 * round + 7));
        Collections.shuffle(shuffledBuyers, new Random(67 * day + 29 * round + 11));
        ArrayList<Pair<Seller, Buyer>> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledBuyers.size(); i++) {
            if (i < shuffledSellers.size()) {
                pairs.add(new Pair<>(shuffledSellers.get(i), shuffledBuyers.get(i)));
            }
        }
        return pairs;
    }

    public double simulate() {
        //TODO: Problem 2.2 and 2.3
        int numberTransactions = 0;
        double sum = 0;
        for (int day = 1; day <= 3000; day++) { // do not change this line
            for (int round = 1; round <= 5; round++) { // do not change this line
                ArrayList<Pair<Seller, Buyer>> pairs = matchedPairs(day, round); // do not change this line
                for(Pair<Seller, Buyer> pair: pairs){
                    Seller seller = pair.key;
                    Buyer buyer = pair.value;
                    if(buyer.willTransact(seller.getExpectedPrice()) && seller.willTransact(buyer.getExpectedPrice())){
                        seller.makeTransaction();
                        buyer.makeTransaction();
                        if(day == 3000){
                            sum += seller.getExpectedPrice();
                            numberTransactions ++;
                        }
                    }
                }
            }
            for(Buyer buyer: buyers){
                buyer.reflect();
            }
            for(Seller seller: sellers){
                seller.reflect();
            }
        }
        return (double)(sum / numberTransactions);
    }
}

