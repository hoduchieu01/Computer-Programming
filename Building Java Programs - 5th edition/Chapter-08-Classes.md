## Chapter 8: Classes

### This file contains my implementation for all exercises of the Chapter 8: Classes.

#### BJP5 Exercise 8.1: quadrantPoint
```
public int quadrant() {
    if(this.y > 0 && this.x > 0) {
        return 1;
    } else if(this.x < 0 && this.y > 0) {
        return 2;
    } else if(this.x < 0 && this.y < 0) {
        return 3;
    } else if(this.x > 0 && this.y < 0) {
        return 4;
    } else {
        return 0;
    }
}
```
#### BJP5 Exercise 8.2: flipPoint
```
public void flip(){
    int negateX = -this.x;
    int negateY = -this.y;
    this.x = negateY;
    this.y = negateX;
}
```
#### BJP5 Exercise 8.3: manhattanDistancePoint
```
public int manhattanDistance(Point other){
    return Math.abs(other.x - this.x) + Math.abs(other.y - this.y);
}
```
#### BJP5 Exercise 8.4: isVerticalPoint
```
public boolean isVertical(Point other){
    return this.x == other.x;
}
```
#### BJP5 Exercise 8.5: slopePoint
```
public double slope(Point other) throws IllegalArgumentException{
      if(this.x == other.x)
        throw new IllegalArgumentException();
    return (double) (other.y - this.y) / (other.x - this.x);
}
```
#### BJP5 Exercise 8.6: isCollinearPoint
```
public boolean isCollinear(Point p1, Point p2) {
    if(this.x == p1.x || this.x == p2.x)
        return this.x == p1.x && this.x == p2.x;
        
    double m1 = ((double) (this.y - p1.y)) / (this.x - p1.x);
    double m2 = ((double) (this.y - p2.y)) / (this.x - p2.x);
    
    if(m1 == m2)
        return true;
        
    return false;
}
```
#### BJP5 Exercise 8.7: addTimeSpan
```
public void add (TimeSpan span){
    minutes += span.minutes;
    hours += span.hours + this.minutes / 60;
    minutes %= 60;
    
}
```
#### BJP5 Exercise 8.8: subtractTimeSpan
```
 public void subtract(TimeSpan span) {
        int min1 = this.hours * 60 + this.minutes;
        int min2 = span.hours * 60 + span.minutes;
        int diff = min1 - min2;
        this.hours = diff / 60;
        this.minutes = diff % 60;
    }
```
#### BJP5 Exercise 8.9: scaleTimeSpan
```
public void scale(int factor){
    this.hours *= factor;
    this.minutes *= factor;
    this.hours += this.minutes / 60;
    this.minutes %= 60;
}
```
#### BJP5 Exercise 8.10: clearStock
```
public void clear(){
    this.totalShares = 0;
    this.totalCost = 0;
}
```
#### BJP5 Exercise 8.11: transactionFeeBankAccount
```
public boolean transactionFee(double fee) {
    double deduction = 0.0;
    
    for (int i = 1; i <= transactions; i++) {
        deduction += i * fee;
    }
    
    if (deduction < balance) {
        balance -= deduction;
        return true;
    }
    
    balance = 0.0;
    return false;
}
```
#### BJP5 Exercise 8.12: toStringBankAccount
```
public String toString() {
    if (balance > 0) {
        return String.format("%s, $%.2f", name, balance);
    } else  if (balance < 0) {
        return String.format("%s, -$%.2f", name, -balance);
    } else {
        return name + ", $0.00";
    }
}
```
#### BJP5 Exercise 8.13: transferBankAccount
```
public void transfer(BankAccount account, double transfer) {
    if (transfer >= 5) {
        this.balance -= 5;
        if (this.balance >= transfer) {
            account.balance += transfer;
            this.balance -= transfer;
        } else if (this.balance - 5 < transfer) {
            account.balance += this.balance;
            this.balance = 0;
        } 
        this.transactions++;
        account.transactions++;
    }
}
```
#### BJP5 Exercise 8.14: classLine
```
public class Line{
    private Point p1;
    private Point p2;
    
    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    public Point getP1(){
        return p1;
    }
    public Point getP2(){
        return p2;
    }
    public String toString(){
        return "[" + "(" + (int) p1.getX() + ", " + (int) p1.getY() + "), (" + (int)p2.getX() 
            + ", " + (int)p2.getY() +")]";
    }
}
```
#### BJP5 Exercise 8.15: getSlopeLine
```
public double getSlope(){
    double x1 = p1.getX();
    double x2 = p2.getX();
    double y1 = p1.getY();
    double y2 = p2.getY();
    if(y1 == y2) return 0;
    else if (x2 == x1){
       throw new IllegalStateException();
    }
    else{
        return (double) (y2-y1) / (x2-x1);
    }
}
```
#### BJP5 Exercise 8.16: constructorLine
```
public Line(int x1, int y1, int x2, int y2){
     p1 = new Point(x1,y1);
     p2 = new Point(x2,y2);
}
```
#### BJP5 Exercise 8.17: isCollinearLine
```
public boolean isCollinear(Point p){
    double slope1 = (double)(p1.getY() - p.getY()) / (p1.getX() - p.getX());
    double slope2 = (double)(p2.getY() - p.getY()) / (p2.getX() - p.getX());
    return slope1 == slope2;
}
```
#### BJP5 Exercise 8.18: classRectangle
```
public class Rectangle{
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Rectangle(int x, int y , int width, int height){
         if(width < 0 || height < 0)
             throw new IllegalArgumentException();
         else{
             this.width = width;
             this.height = height;
     
         }
        this.x = x;
        this.y = y;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String toString(){
        return "Rectangle" + "[" + "x=" + x + ",y=" + y + ",width=" + width + ",height=" + height+"]";
    }
}
```
#### BJP5 Exercise 8.19: constructorRectangle
```
public Rectangle(Point p, int width, int height){
     this(p.getX(), p.getY(), width, height);
}
```
#### BJP5 Exercise 8.20: containsRectangle
```
public boolean contains(int x, int y){
    return (x <= this.x + width && x >= this.x) && (y <= this.y + height && y >= this.y);
}
public boolean contains(Point p){
    return (p.getX() <= this.x + width && p.getX() >= this.x) && (p.getY() <= this.y + height && 
                                                                  p.getY() >= this.y);
}
```
#### BJP5 Exercise 8.21: unionRectangle
```
public Rectangle union(Rectangle rect){
    int newX = Math.min(this.getX(), rect.getX());
    int newY = Math.min(this.getY(), rect.getY());
    int newWidth = Math.max(rect.getX() + rect.getWidth() - newX,
        this.getX() + this.getWidth() - newX);
    int newHeight = Math.max(rect.getY() + rect.getHeight() - newY,
        this.getY() + this.getHeight() - newY);
    return new Rectangle(newX, newY, newWidth, newHeight);
}
```
#### BJP5 Exercise 8.22: intersectionRectangle
```
public Rectangle intersection(Rectangle rect) {
    int left = Math.max(this.x, rect.x);
    int top = Math.max(this.y, rect.y);
    int right = Math.min(this.x + this.width, rect.x + rect.width);
    int bottom = Math.min(this.y + this.height, rect.y + rect.height);
    int width = Math.max(0, right - left);
    int height = Math.max(0, bottom - top);
        
    return new Rectangle(left, top, width, height);
}
```
#### BJP5 Exercise 8.23: containsOtherRectangle
```
public boolean contains(Rectangle rect) {
		return (rect.x >= x && (rect.x + rect.width) <= (x + width)) &&
			(rect.y >= y && (rect.y + rect.height) <= (y + height));
	}
```
