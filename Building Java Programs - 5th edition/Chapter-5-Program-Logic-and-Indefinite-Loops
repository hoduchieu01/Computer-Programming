## Chapter 5: Program Logic and Indefinite Loops
### This file contains my implementation for all exercises of the Chapter 5: Program Logic and Indefinite Loops

#### BJP5 Exercise 5.1: showTwos
```
public static void showTwos(int n){
    System.out.print(n + " = ");
    while(n % 2 == 0){
        System.out.print("2 * ");
        n = n / 2;
    }
    System.out.print(n);
}
```
#### BJP5 Exercise 5.2: gcd
```
public static int gcd(int a, int b) {

    while (b != 0) {
        int c = a % b;
        a = b;
        b = c;
    }
    
    return Math.abs(a);
}
```
#### BJP5 Exercise 5.3: toBinary
```
public static String toBinary(int n){
    String res = "";
    res = n % 2 + res;
    n = n / 2;
    
    while (n != 0){
        res = n % 2 + res;
        n = n / 2;
    }
    return res;
}
```
#### BJP5 Exercise 5.4: randomX
```
public static void randomX(){
    Random random = new Random();
    int n;
    n = random.nextInt(16) + 5;
    for(int i = 0; i < n; i++) System.out.print("x");
    System.out.println();
    while(n < 16){
        n = random.nextInt(16) + 5;
        for(int i = 0; i < n; i++) System.out.print("x");
        System.out.println();
    }
}
```
#### BJP5 Exercise 5.6: makeGuesses
```
public void makeGuesses() {
    int count = 0;
    Random r = new Random();
    int guess;
    
    do {
        guess = 1 + r.nextInt(50);
        count++;
        System.out.println("guess = " + guess);
    } while(guess < 48);
    
    System.out.println("total guesses = " + count);
}
```
#### BJP5 Exercise 5.7: diceSum
```
public static void diceSum(){
    System.out.print("Desired dice sum: ");
    Scanner console = new Scanner(System.in);
    int dice = console.nextInt();
    int a;
    int b;
    Random random = new Random();
    do{
      a = 1 + random.nextInt(6);
      b = 1 + random.nextInt(6);
      int sum = a + b;
      System.out.println(a + " and " + b + " = "  + sum);
    }while(a + b != dice);
}
```
#### BJP5 Exercise 5.8: randomWalk
```
public static void randomWalk() {
      int position = 0;
      int max = 0;
      System.out.println("position = " + position);
      Random random = new Random();
      while (position != 3 && position != -3) {
            int step = random.nextInt(2);
            if (step == 0) {
                  position++;
            } else {
                  position--;
            }
            System.out.println("position = " + position);
            if (position > max) {
                  max = position;
            }
      }
      System.out.println("max position = " + max);

}
```
#### BJP5 Exercise 5.9: printFactors
```
public void printFactors(int n){
    for(int i = 1; i < n; i++){
        if(n % i == 0){
            System.out.print(i + " and ");
        }
    }
    System.out.print(n);
}
```
#### BJP5 Exercise 5.10: hopscotch
```
public void hopscotch(int n) {
    int current = 1;
    int total = 3 * n + 1;
    
    while(current < total) {
        if((current - 1) % 3 == 0) {
            System.out.println("   " + current);
            current++;
        } else {
            System.out.println(current + "     " + (current + 1));
            current += 2;
        }
    }
    
    System.out.println("   " + current);
}
```
#### BJP5 Exercise 5.11: threeHeads
```
public void threeHeads() {
    Random r = new Random();
    int count = 0;
    while(count < 3) {
        boolean head = r.nextBoolean();
        if(head) {
            System.out.print("H ");
            count++;
        } else {
            System.out.print("T ");
            count = 0;
        }
    }
    System.out.println("\nThree heads in a row!");
}
```
#### BJP5 Exercise 5.12: printAverage
```
public void printAverage(Scanner console) {
    System.out.print("Type a number: ");
    int num = console.nextInt();
    
    if(num < 0)
        return;
    
    int sum = 0;
    int count = 0;
    
    while(num >= 0) {
        sum += num;
        count++;
        System.out.print("Type a number: ");
        num = console.nextInt();
    }
    
    System.out.println("Average was " + ((double) sum / count));
}
```
#### BJP5 Exercise 5.13: consecutive
```
public boolean consecutive(int a, int b, int c) {
    int max = Math.max(a, Math.max(b, c));
    int min = Math.min(a, Math.min(b, c));
    int mid = a + b + c - max - min;
    
    return max == mid + 1 && mid == min + 1;
}
```
#### BJP5 Exercise 5.14: hasMidpoint
```
public static boolean hasMidpoint(int a, int b, int c){
    int max = Math.max(a, Math.max(b,c));
    int min = Math.min(a, Math.min(b,c));
    int mid = a + b + c - max - min;
    return mid == (max + min) / 2.0;
}
```
#### BJP5 Exercise 5.15: dominant
```
public static boolean dominant(int a, int b, int c){
    return a > b + c || b > c + a || c > a + b;
}
```
#### BJP5 Exercise 5.16: anglePairs
```
public static boolean anglePairs(int a, int b, int c) {
    if (a + b == 90) {
        if (a + c == 180) {
            return true;
        } else if (b + c == 180) {
            return true;
        }
        return false;
    } else if (a + c == 90) {
        if (b + c == 180) {
            return true;
        } else if (a + b == 180) {
            return true;
        }
        return false;
    } else if (b + c == 90) {
        if (a + c == 180) {
            return true;
        } else if (a + b == 180) {
            return true;
        }
        return false;
    }
    return false;
}
```
#### BJP5 Exercise 5.17: monthApart
```
public static boolean monthApart(int month1, int day1, int month2, int day2) {
    if (Math.abs(month1 - month2) > 1) {
        return true;
    } else if (Math.abs(month1 - month2) == 1) {
        if (month1 < month2 && day1 <= day2) {
            return true;
        } else if (month1 > month2 && day1 >= day2) {
            return true;
        }
        return false;
    } else { // month1 = month2
        return false;
    }
}
```
#### BJP5 Exercise 5.18: digitSum
```
public static int digitSum(int n){
    int sum = 0;
    if (n < 0) n *= -1;
    while(n > 0){
        sum += n % 10;
        n = n / 10;
    }
    return sum;
}
```
#### BJP5 Exercise 5.19: firstDigit
```
public static int firstDigit(int n){
    if(n < 0) n *= -1;
    int ans = n;
    while(n >= 10){
        n = n / 10;
        ans = n;
    }
    return ans;
}
```
#### BJP5 Exercise 5.20: digitRange
```
public static int digitRange(int n){
    if(n < 0) n *= -1;
    if(n == 0) return 1;
    int max = 0;
    int min = 9;
    while(n > 0){
        int r = n % 10;
        if(r > max) max = r;
        if(r < min) min = r;
        n = n / 10;
    }
    return max - min + 1;
    
}
```
#### BJP5 Exercise 5.21: swapDigitPairs
```
public static int swapDigitPairs(int number) {
    int result = 0;
    int place = 1;
    while (number > 9) { // the terminating condition is that there are at least two digits remaining
        result += place * 10 * (number % 10); // swap the last digit to the tenth place
        /*
        the loop consumes digits from the right hand side of the number, 
        so the odd/even processing distinction is gracefully handled
        */
        number /= 10; // truncate the number
        result += place * (number % 10); // swap the tenth digit to the single digit place
        number /= 10; // truncate again
        place *= 100; // loop logic deals with two digits per iteration
    }
    return result + place * number;
}
```
#### BJP5 Exercise 5.22: allDigitsOdd
```
public static boolean allDigitsOdd(int n){
    if( n < 0) n *= -1;
    if(n == 0) return false;
    while (n > 0){
        if((n % 10) % 2 == 0) return false;
        n = n / 10;
    }
    return true;
}
```
#### BJP5 Exercise 5.23: hasAnOddDigit
```
public static boolean hasAnOddDigit(int n){
    if(n < 0) n *= -1;
    if(n == 0) return false;
    while(n > 0){
        if((n % 10) % 2 != 0) return true;
        n = n / 10;
    }
    return false;
}
```
#### BJP5 Exercise 5.24: isAllVowels
```
public static boolean isAllVowels(String s){
   s = s.toLowerCase();
   for(int i = 0; i < s.length(); i++){
      char c = s.charAt(i);
      if(c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u')
          return false;
   }
    return true;
}
```
#### BJP5 Exercise 5.25: charsSorted
```
public static boolean charsSorted(String s){
    for(int i = 0; i < s.length() - 1; i++){
        if(s.charAt(i) > s.charAt(i + 1))
           return false;
    }
    return true;
}
```
