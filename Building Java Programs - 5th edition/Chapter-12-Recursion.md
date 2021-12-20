## Chapter 12: Recursion

### This file contains my implementation for all exercises of the Chapter 12: Recursion.

#### BJP5 Exercise 12.1: starString
```
public static String starString(int n){
    if (n < 0) {
        throw new IllegalArgumentException();
    } else if (n == 0) {
        return "*";
    } else {
        return starString(n - 1) + starString(n - 1);
    }
}
```
#### BJP5 Exercise 12.2: writeNums
```
public static void writeNums(int n){
    if (n < 1) {
        throw new IllegalArgumentException();
    } else if (n == 1) {
        System.out.print("1");
    } else {
        writeNums(n - 1);
        System.out.print(", " + n);
    }
}
```
#### BJP5 Exercise 12.3: writeSequence
```
public static void writeSequence(int n){
    if (n < 1) {
        throw new IllegalArgumentException();
    } else if (n == 1) {
        System.out.print("1 ");
    } else {
        System.out.print((n + 1) / 2 + " ");
        if (n != 2) {
            writeSequence(n - 2);
        }
        System.out.print((n + 1) / 2 + " ");
    }
}
```
#### BJP5 Exercise 12.6: writeSquares
```
public void writeSquares(int n) {
    if(n < 1)
        throw new IllegalArgumentException();
        
    if(n == 1) {
        System.out.print(1);
        return;
    }
    
    if(n % 2 == 0) {
        writeSquares(n-1);
        System.out.print(", " + n * n);
    } else {
        System.out.print(n * n + ", ");
        writeSquares(n-1);
    }
}
```
#### BJP5 Exercise 12.7: writeChars
```
public static void writeChars(int n) {
    if (n < 1) {
        throw new IllegalArgumentException();
    }
    else if(n == 1){
        System.out.print("*");
    }
    else if(n == 2){
        System.out.print("**");
    }
    else{
        System.out.print("<");
        writeChars(n-2);
        System.out.print(">");
    }
}
```
#### BJP5 Exercise 12.8: multiplyEvens
```
public static int multiplyEvens(int n){
    if (n <= 0) {
        throw new IllegalArgumentException();
    } else if (n == 1) {
        return 2;
    } else {
        return 2 * n * multiplyEvens(n-1);
    }
}
```
#### BJP5 Exercise 12.9: sumTo
```
public static double sumTo(int n){
    if (n < 0) {
        throw new IllegalArgumentException();
    } else if (n == 0) {
        return 0.0;
    } else {
        return 1.0 / n + sumTo(n - 1);
    }
}
```
#### BJP5 Exercise 12.10: digitMatch
```
public static int digitMatch(int a, int b) {
    if (a < 0 || b < 0) {
        throw new IllegalArgumentException();
    } else if (a == 0 && b == 0) {
        return 1;
    } else {
        int result = 0;
        if (a % 10 == b % 10) {
            result++;
        }
        if (a / 10 == 0 || b / 10 == 0) {
            return result;
        }
        return result + digitMatch(a / 10, b / 10);
    }    
}
```
#### BJP5 Exercise 12.11: repeat
```
public static String repeat(String s, int n){
    if(n < 0) 
        throw new IllegalArgumentException();
    if(n == 0) return "";
    return s + repeat(s, n - 1);
}
```
#### BJP5 Exercise 12.12: isReverse
```
public static boolean isReverse(String a, String b) {
    if (a.length() == 0 && b.length() == 0) {
        return true;
    } else if (a.length() == b.length()) {
        int length = b.length();
        char letter1 = Character.toUpperCase(a.charAt(0));
        char letter2 = Character.toUpperCase(b.charAt(length - 1));
        if (letter1 == letter2) {
            return isReverse(a.substring(1), b.substring(0, length - 1));
        } else {
            return false;
        }
    }
    return false;
}
```
#### BJP5 Exercise 12.13: indexOf
```
public static int indexOf(String a, String b) {
    if (a.length() == b.length()) {
        if (a.equals(b)) {
            return 0;
        } 
    } else if (a.length() >= b.length()) {
        int length = b.length();
        if (a.substring(0, length).equals(b)) {
            return 0;
        } else {
            int result = 1 + indexOf(a.substring(1), b);
            if (result != 0) {
                return result;
            }
        }
    }
    return -1;
}
```
#### BJP5 Exercise 12.14: dedup
```
  public static String dedup(String str){ 
        if (str.length()==0 || str.length()==1)    
            return str;
        else {
            if (str.charAt(0)==str.charAt(1))      
                return dedup(str.substring(1));
            else                                    
                return str.charAt(0)+dedup(str.substring(1));
        }
    }
```
#### BJP5 Exercise 12.15: vowelsToEnd
```
  public static String vowelsToEnd(String str){ 
        if (str.length() == 0 || str.length() == 1)
            return str;
        else if(str.charAt(0) == 'o' || str.charAt(0) == 'u' || str.charAt(0) == 'i' 
                || str.charAt(0) == 'a' || str.charAt(0) == 'e' )
        return vowelsToEnd(str.substring(1, str.length())) + str.charAt(0);           
        else
            return str.charAt(0) + vowelsToEnd(str.substring(1, str.length()));
 }
```
#### BJP5 Exercise 12.16: evenDigits
```
public static int evenDigits(int n){
    int res = 0;
    int cnt = 1;
    if(n < 0){
    n *= -1;
    while(n > 0){
          int digit = n % 10;
          if(digit % 2 == 0){
              res += digit * cnt;
              cnt *= 10;
          }
          n = n / 10;
    }
    res *= -1;
    }
    else{
        while(n > 0){
          int digit = n % 10;
          if(digit % 2 == 0){
              res += digit * cnt;
              cnt *= 10;
          }
          n = n / 10;
    }
    }
    return res;
}
```
#### BJP5 Exercise 12.20: waysToClimb
```
public static void waysToClimb(int n) {
    waysToClimb(n, 0, "[");
}

private static void waysToClimb(int n, int position, String result) {
    if (n == position) {
        int index = result.lastIndexOf(",");
        if (index != -1) {
            result = result.substring(0, index) + "]";
            System.out.println(result);
        }
    } else if (n > position) {
        waysToClimb(n, position + 1, result + "1, ");
        waysToClimb(n, position + 2, result + "2, ");
    }
}
```
#### BJP5 Exercise 12.21: countBinary
```
public void countBinary(int n) {
    String num = "";
    countBinary(n, num);
}

public void countBinary(int n, String num) {
    if(n == 0) {
        System.out.println(num);
        return;
    }
    countBinary(n - 1, num + "0");
    countBinary(n - 1, num + "1");
}
```
#### BJP5 Exercise 12.22: subsets
```
public void subsets(List<String> list) {
    subsets(list, 0, list.size());
}

public void subsets(List<String> list, int start, int rem) {
    if(rem == 0) {
        System.out.println(list);
        return;
    }
    
    subsets(list, start + 1, rem - 1);
    String str = list.remove(start);
    subsets(list, start, rem - 1);
    list.add(start, str);
}
```
#### BJP5 Exercise 12.23: maxSum
```
public static int maxSum(List<Integer> list, int limit) {
    if (list.size() == 0 || limit <= 0) {
        return 0;
    } else {
        int[] max = {0};
        maxSum(list, limit, 0, max);
        return max[0];
    }
}

public static void maxSum(List<Integer> list, int limit, int sum, int[] max) {
    if (sum > max[0] && sum <= limit) {
        max[0] = sum;
    }
    if (list.size() != 0) {
        int num = list.remove(0);
        maxSum(list, limit, sum + num, max);
        maxSum(list, limit, sum, max);
        list.add(num);
    }
}
```
#### BJP5 Exercise 12.24: printSquares
```
public static void printSquares(int n) {
    printSquares(n, 1, new TreeSet<Integer>());
}

private static void printSquares(int n, int index, Set<Integer> squares) {
    if (n == 0) {
        printHelper(squares);
    } else if (n > 0) {
        int root = (int) Math.sqrt(n);
        for (int i = index; i <= root; i++) {
            if (n - i * i >= 0) {
                squares.add(i);
                printSquares(n - i * i, i + 1, squares);
                squares.remove(i);
            } 
        }
    }
}
```
