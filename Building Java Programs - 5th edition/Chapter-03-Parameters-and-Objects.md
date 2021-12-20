## Chapter 3: Parameters and Objects

### This file contains my implementation for all exercises of the Chapter 3: Parameters and Objects.

#### BJP5 Exercise 3.1: printNumbers
```
public static void printNumbers(int number){
    for(int i = 1; i <= number; i ++){
        System.out.print("[" + i + "]" + " ");
    }
}
```
#### BJP5 Exercise 3.2: printPowersOf2
```
public static void printPowersOf2(int n){
    int res = 1;
    System.out.print("1 ");
    for(int i = 1; i <= n; i ++){
         res *= 2;
        System.out.print(res + " ");
    }
}
```
#### BJP5 Exercise 3.3: printPowersOfN
```
public static void printPowersOfN(int base, int exponent){
    System.out.print("1 ");
    int res = base;
    for(int i = 1; i <= exponent; i ++){
        
        System.out.print(res + " ");
        res *= base;
    }
}
```
#### BJP5 Exercise 3.4: printSquare
```
public static void printSquare(int min, int max){
    for(int i = min; i <= max; i++) {
        for(int j = i; j <= max; j++) {
            System.out.print(j);
        }
        for(int k = 0; k < i - min; k++) {
            System.out.print(min + k);
        }
        System.out.println();
    }
}
```
#### BJP5 Exercise 3.5: printGrid
```
public static void printGrid(int rows, int cols){
    for(int i = 1; i <= rows; i++){
        int num = i;
        System.out.print(i);
        for(int j = 1; j < cols; j++){
            num += rows;
            System.out.print(", " + num);
        
        }
        System.out.println();
    }
}
```
#### BJP5 Exercise 3.6: largerAbsVal
```
public static int largerAbsVal(int a, int b){
    return Math.max(Math.abs(a), Math.abs(b));
   }
```
#### BJP5 Exercise 3.7: largestAbsVal
```
public static int largestAbsVal(int a, int b, int c){
    return Math.max(Math.max(Math.abs(a), Math.abs(b)), Math.abs(c));
}
```
#### BJP5 Exercise 3.8: quadratic
```
public static void quadratic(int a, int b, int c){
    int delta = b * b - 4 * a * c;
    double first_root = (-b + (double) Math.sqrt(delta))/ (2.0 * a);
    double second_root = (-b - (double) Math.sqrt(delta)) / (2.0 * a);
    System.out.println("First root = " + first_root);
    System.out.println("Second root = " + second_root);
}
```
#### BJP5 Exercise 3.9: lastDigit
```
public static int lastDigit(int n){
    return Math.abs(n % 10);
}
```
#### BJP5 Exercise 3.10: area
```
public static double area(double radius){
    return Math.PI * radius * radius;
}
```
#### BJP5 Exercise 3.11: distance
```
public static double distance(int x1, int y1, int x2, int y2){
    return Math.sqrt((x2-x1) * (x2-x1) + (y2-y1)*(y2-y1));
}
```
#### BJP5 Exercise 3.12: scientific
```
public static double scientific( double base, double exp){
    return base * Math.pow(10, exp);
}
```
#### BJP5 Exercise 3.13: pay
```
public static double pay(double salary, int hours){
    if(hours > 8)
        return salary * 8.0 + salary * (hours - 8) / 2.0 * 3.0;
    return salary * hours;
}
```
#### BJP5 Exercise 3.14: cylinderSurfaceArea
```
public static double cylinderSurfaceArea(double radius, double height){
    return 2.0 * Math.PI * radius * radius + 2.0 * Math.PI * radius * height;
}
```
#### BJP5 Exercise 3.15: sphereVolume
```
public static double sphereVolume(double radius){
    return 4.0 / 3.0 * Math.PI * radius * radius * radius;
}
```
#### BJP5 Exercise 3.16: triangleArea
```
public static double triangleArea(double a, double b, double c){
    double s = (a + b + c) / 2.0;
    return Math.sqrt(s*(s-a)*(s-b)*(s-c));
}
```
#### BJP5 Exercise 3.17: padString
```
public static String padString(String string, int length) {
    if(length == string.length()) {
        return string;
    } 
          int l = (length - string.length());
        String s = "";
        for(int i = 0; i < l; i++) {
            s+=" ";
        }
        return s + string;
}
```
#### BJP5 Exercise 3.18: vertical
```
public static void vertical(String s){
    for (int i = 0; i < s.length(); i++){
        System.out.println(s.charAt(i));
    }
}
```
#### BJP5 Exercise 3.19: printReverse
```
public static void printReverse(String s){
    for(int i = s.length() - 1; i >= 0; i --)
        System.out.print(s.charAt(i));
}
```
#### BJP5 Exercise 3.20: inputBirthday
```
public static void inputBirthday(Scanner console) {
    System.out.print("On what day of the month were you born? ");
    int day = console.nextInt();
    
    System.out.print("What is the name of the month in which you were born? ");
    String month = console.next();
    
    System.out.print("During what year were you born? ");
    int year = console.nextInt();
    
    System.out.println("You were born on " + month + " " + day + ", " + year + ". You're mighty old!");
}
```
#### BJP5 Exercise 3.21: processName
```
public static void processName(Scanner console) {
   System.out.print("Please enter your full name: ");
   String firstName = console.next();
   String lastName = console.next();
   System.out.print("Your name in reverse order is " + lastName + ", " + firstName);
}
```
#### BJP5 Exercise 3.22: TheNameGame
```
public class TheNameGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
       System.out.print("What is your name? ");
        String first = scan.next();
        String last = scan.next();
        String newFirst = first.substring(1);
        String newLast = last.substring(1);

        playGame(first, newFirst);
        System.out.println();
        playGame(last, newLast);
    }

public static void playGame(String name, String nn) {
    System.out.println(name + " " + name + ", " + "bo-B" + nn);
    System.out.println("Banana-fana fo-F" + nn);
    System.out.println("Fee-fi-mo-M" + nn);
    System.out.println(name.toUpperCase() + "!");
  }
}
```
#### BJP5 Exercise 3.23: printIndexed
```
public static void printIndexed(String character){
    String res = "";
    for(int i = 0; i < character.length(); i++){
        res = res + character.charAt(i) + (character.length() - i - 1);
    }
    System.out.println(res);
}
```
