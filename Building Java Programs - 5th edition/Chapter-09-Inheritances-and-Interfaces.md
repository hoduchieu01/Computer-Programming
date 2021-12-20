## Chapter 9: Inheritances and Interfaces

### This file contains my implementation for all exercises of the Chapter 9: Inheritances and Interfaces.

#### BJP5 Exercise 9.1: Marketer
```
public class Marketer extends Employee {
    public void advertise(){
        System.out.println("Act now, while supplies last!");
    }
    public double getSalary(){
        return super.getSalary() + 10000;
    }
	
}
```
#### BJP5 Exercise 9.2: Janitor
```
public class Janitor extends Employee {
    public int getHours() {
		return super.getHours() * 2;                // 40 hours/week
	}

	public double getSalary() {
		return super.getSalary() - 10000;               // $40,000.00
	}

	public int getVacationDays() {
		return super.getVacationDays() / 2;         // 10 days
	}

	public void clean() {
		System.out.println("Workin' for the man.");
	}
}
```
#### BJP5 Exercise 9.3: HarvardLawyer
```
public class HarvardLawyer extends Lawyer {
	 public double getSalary() {
        return 1.2 * super.getSalary();
    }
    
    public int getVacationDays() {
        return super.getVacationDays() + 3;
    }
    
    public String getVacationForm() {
        String form = super.getVacationForm();
        return form + form + form + form;
    }
}
```
#### BJP5 Exercise 9.4: MonsterTruck
```
public class MonsterTruck extends Truck {
    public void m1(){
        System.out.println("monster 1");
    }
    public void m2(){
        super.m1();
        super.m2();
    }
    public String toString(){
        return "monster " + super.toString();
    }
	
}
```
#### BJP5 Exercise 9.9: MinMaxAccount
```
public class MinMaxAccount extends BankingAccount {
   
    private int min;
    private int max;
    
    public MinMaxAccount(Startup s) {
        super(s);
        min = getBalance();
        max = getBalance();
    }
    
    public void credit(Credit c) {
        super.credit(c);
        update();
    }
    
    public void debit(Debit d) {
        super.debit(d);
        update();
    }
    
    public void update() {
        int balance = getBalance();
        if (balance < min) {
            min = balance;
        } else if (balance > max) {
            max = balance;
        }
    }
    
    public int getMin() {
        return min;
    }
    
    public int getMax() {
        return max;
    }
    
	
}
```
#### BJP5 Exercise 9.10: DiscountBill
```
public class DiscountBill extends GroceryBill {
    
   
    private int discountItem;
    private double discount;
    private boolean preferred;

    public DiscountBill(Employee clerk, boolean preferred) {
        super(clerk);
        this.preferred = preferred;
        discountItem = 0;
        discount = 0.0;
    }
    
    public void add(Item i) {
        super.add(i);
        if (preferred && i.getDiscount() > 0) {
            discountItem++;
            discount += i.getDiscount();
        }
    }
    
    public int getDiscountCount() {
        return discountItem;
    }
    
    public double getDiscountAmount() {
        return discount;
    }
    
    public double getTotal() {
        return super.getTotal() - discount;
    }
    
    public double getDiscountPercent() {
        return discount * 100 / super.getTotal();
    }
    
}
```
#### BJP5 Exercise 9.11: FilteredAccount
```
public class FilteredAccount extends Account {
    private int zeroTrans;
    private int totalTrans;
    
    public FilteredAccount (Client c) {
        super(c);
        zeroTrans = 0;
        totalTrans = 0;
    }
    
    public boolean process(Transaction t) {
        totalTrans++;
        if (t.value() == 0) {
            zeroTrans++; 
            return true;
        } else {
            return super.process(t);
        }
    }

    public double percentFiltered() {
        if (totalTrans == 0) {
            return 0.0;
        }
        return zeroTrans * 100.0 / totalTrans;
    } 
}
```
