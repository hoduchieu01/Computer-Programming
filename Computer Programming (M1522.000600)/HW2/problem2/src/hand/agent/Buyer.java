package hand.agent;

public class Buyer extends Agent {

    public Buyer(double maximumPrice) {
        super(maximumPrice);
    }

    @Override
    public boolean willTransact(double price) {
        //TODO: Problem 2.1
        if(price <= expectedPrice && !hadTransaction){
            return true;
        }
        return false;
    }

    @Override
    public void reflect() {
        //TODO: Problem 2.1
        // If it made a transaction that day
        if (hadTransaction) {
            // decrement expectedPrice field of Agent class by the value of adjustment field.
            expectedPrice -= adjustment;
            // After decrement of expectedPrice, increase adjustment by 5
            adjustment += 5;
            // If adjustment exceed the adjustmentLimit, set adjustment equal to adjustmentLimit
            if (adjustment > adjustmentLimit) adjustment = adjustmentLimit;
        }
        // if there was no transaction,
        else {
            //decrement expectedPrice by adjustment.
            expectedPrice += adjustment;
            // if adjusted expectedPrice > priceLimit, set expectedPrice equal to priceLimit
            if (expectedPrice > priceLimit) {
                expectedPrice = priceLimit;
            }
            // if expectedPrice is increased by adjustment (not set to the priceLimit) decrement adjustment by 5
            else {
                adjustment -= 5;
            }
            // if the decreased adjustment is less than 0, set the adjustment to 0
            if (adjustment < 0) adjustment = 0;
        }
        hadTransaction = false;
    }
}
