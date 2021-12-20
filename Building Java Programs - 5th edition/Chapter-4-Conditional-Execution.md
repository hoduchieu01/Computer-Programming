## Chapter 4: Conditional Execution
### This file contains my implementation for all exercises of the Chapter 4: Conditional Execution

#### BJP5 Exercise 4.1: fractionSum
```
public static double fractionSum(int n){
    double sum = 0.0;
    for(int i = 1; i <= n; i++){
        sum += (double) 1/i;
    }
    return sum;
}
```
#### BJP5 Exercise 4.2: repl
```
public static String repl(String s, int numberRepetitions){
    String ans = "";
    for(int i = 1; i <= numberRepetitions; i++){
        ans += s;
    }
    return ans;
}
```
#### BJP5 Exercise 4.3: season
```
public static String season(int month, int day){
    String season = "";
    if((month == 3 && day <= 15) || (month == 12 && day >=16) || month == 1 || month == 2){
        season += "Winter";
    }
    else if((month == 6 && day >= 16) || month == 7 || month == 8 || (month == 9 && day <=15))
    {
        season += "Summer";
    }
    else if((month == 12 && day <= 15) || (month == 9 && day >=16) || month == 10 || month == 11)
        season += "Fall";
    else season += "Spring";
    return season;
}
```
#### BJP5 Exercise 4.4: daysInMonth
```
public static int daysInMonth(int month){
    if(month == 2) return 28;
    else if(month == 4 || month == 6 || month == 9 || month == 11) return 30;
    else
        return 31;
}
```
#### BJP5 Exercise 4.5: pow
```
public static int pow(int a, int b){
    int res = 1;
    for(int i = 1; i <= b; i++)
        res *= a;
    return res;
}
```
#### BJP5 Exercise 4.6: printRange
```
public static void printRange(int start, int end){
    if(start > end){
        for(int i = start; i >= end; i --)
        System.out.print(i + " ");
    }
    for(int i = start; i <= end; i ++)
        System.out.print(i + " ");
}
```
#### BJP5 Exercise 4.7: xo
```
public static void xo(int x) {
		int a = 0;
        int z = x -1;
        for(int i = 0; i < x;i++){
            for(int j =0; j < x ; j++){
                if(a == j || j == z) System.out.print('x');
                else System.out.print('o');
            }
            System.out.println();
            a ++;
            z --;
        }
}
```
#### BJP5 Exercise 4.8: smallestLargest
```
public static void smallestLargest(){
    System.out.print("How many numbers do you want to enter? ");
    Scanner console = new Scanner(System.in);
    int scan = console.nextInt();
    int i = 0;
    int smallest = 99999999;
    int largest = -99999999;
    while (scan > 0){
        scan --;
        i ++;
        System.out.print("Number " + i + ": ");
        int number = console.nextInt();
        if(smallest >= number) smallest = number;
        if(largest <= number) largest = number;
    }
    System.out.println("Smallest = " + smallest);
    System.out.println("Largest = " + largest);
}
```
#### BJP5 Exercise 4.9: evenSumMax
```
public static void evenSum(){
    System.out.print("how many integers? ");
    Scanner console = new Scanner(System.in);
    int integers = console.nextInt();
    int evenMax = -9999998;
    int evenSum = 0;
    while (integers > 0){
        integers --;
        System.out.print("next integer? ");
        int x = console.nextInt();
        if(x % 2 == 0){
            evenSum += x;
            if(x >= evenMax) evenMax = x;
        }
    }
    System.out.println("even sum = " + evenSum);
    System.out.println("even max = " + evenMax);
}
```
#### BJP5 Exercise 4.10: printGPA
```
public static void printGPA() {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter a student record: ");
		String name = console.next();
		int number = console.nextInt();
		
		int sum = 0;
		for(int j = 1; j <= number; j++) {
			int points = console.nextInt();
			sum += points;
		}
		double average = (double) sum / number;		
		System.out.println(name + "'s grade is " + average);
}
```
#### BJP5 Exercise 4.11: longestName
```
public static void longestName(Scanner console, int n){
    int longest = 0;
    int i = 0;
    String maxString = "";
    boolean isTie = false;
    while(n > 0){
        i++;
        n--;
        System.out.print("name #" + i + "? ");
        String name = console.nextLine();
        int size = name.length();
        if(size == longest){
            isTie = true;
        }
        else if(size > longest) {
            longest = size;
            maxString = name;
            isTie = false;
        }
    }
    maxString = maxString.toUpperCase();
    char x = maxString.charAt(0);
    maxString = maxString.toLowerCase();     
    System.out.println(x + maxString.substring(1) + "'s name is longest");
    if(isTie) System.out.println("(There was a tie!)");
}
```
#### BJP5 Exercise 4.12: printTriangleType
```
public static void printTriangleType(int a, int b, int c){
    if(a == b && b == c)
        System.out.println("equilateral");
    else if((a != b && b == c) || (b != a && a == c) || (b != c && a == b))
        System.out.println("isosceles");
    else
        System.out.println("scalene");
}
```
#### BJP5 Exercise 4.13: average
```
public static double average(int a, int b){
    return (a+b) / 2.0;
}
```
#### BJP5 Exercise 4.14: pow2
```
public static double pow2(double base, int exponent) {
    double result = 1.0;
    
    if (exponent >= 0) {
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
    } else {
        for (int i = exponent; i < 0; i++) {
            result *= 1 / base;
        }
    }
    return result;
}
```
#### BJP5 Exercise 4.15: getGrade
```
public static double getGrade(int score) {
    if (score < 0 || score > 100) {
        throw new IllegalArgumentException();
    } else {
        if (score < 60) {
            return 0.0;
        } else if (score <= 62) {
            return 0.7;
        } else if (score <= 94) {
            double grade = 0.8;
            
            for(int i = 63; i < score; i++){
                grade += 0.1;
            }
            
            return grade;
        } else {
            return 4.0;
        }
    }
}
```
#### BJP5 Exercise 4.16: printPalindrome
```
public static void printPalindrome(Scanner console){
    System.out.print("Type one or more words: ");
    String s = console.nextLine();
    String x = s.toUpperCase();
    boolean isPalindrome = true;
    for(int i = 0; i < x.length() / 2; i++){
        if(x.charAt(i) != x.charAt(s.length() - 1 - i))
        {
            isPalindrome = false;
            break;
        }
    }
    if(isPalindrome) System.out.println(s + " is a palindrome!");
    else{
        System.out.println(s + " is not a palindrome.");
    }   
}
```
#### BJP5 Exercise 4.17: stutter
```
public static String stutter (String s){
    String ans = "";
    for(int i = 0; i < s.length(); i++){
        ans = ans + s.charAt(i) + s.charAt(i); 
    }
    return ans;
}
```
#### BJP5 Exercise 4.18: wordCount
```
public static int wordCount(String s){
        int numWords = 0;
        char firstChar = s.charAt(0);
        if(firstChar != ' ' && firstChar != '\t' && firstChar != '\n'){
            numWords ++;
        }
        for(int i = 0; i < s.length(); i ++){
            char secondChar = s.charAt(i);
            if(firstChar == ' ' && secondChar != ' ' && secondChar != '\n' && secondChar != '\t'){
                numWords ++;
            }
            else if(firstChar == '\n' && secondChar != ' ' && secondChar != '\n' && secondChar != '\t'){
                numWords ++;
            }
            else if(firstChar == '\t' && secondChar != ' ' && secondChar != '\n' && secondChar != '\t'){
                numWords ++;
            }
            firstChar = secondChar;
        }
        return numWords;
    }
```
#### BJP5 Exercise 4.19: quadrant
```
public static int quadrant(double x, double y) {
    if (x > 0 && y > 0) {
        return 1;
    } else if (x < 0 && y > 0) {
        return 2;
    } else if (x < 0 && y < 0) {
        return 3;
    } else if (x > 0 && y < 0) {
        return 4;
    } else {
        return 0;
    }
}
```
#### BJP5 Exercise 4.20: numUnique
```
public static int numUnique(int a, int b, int c) {
    int count = 0;
    
    if (a != b) {
        count++;
    }
    
    if (b != c) {
        count++;
    }
    
    if (a != c) {
        count++;
    }
    
    if (a == b && b == c) {
        count++;
    }
    
    return count;
}
```
#### BJP5 Exercise 4.21: perfectNumbers
```
public static void perfectNumbers(int max) {
    String numbers = "";
    
    for (int i = 1; i <= max; i++) {
        int sum = 0;
        
        for (int j = 1; j < i; j++) {
            if (i % j == 0) {
                sum += j;
            }
        }
        
        if (sum == i) {
            numbers += i + " ";
        }
    }
    
    System.out.println("Perfect numbers up to " + max + ": " + numbers);
}
```
#### BJP5 Exercise 4.22: printAcronym
```
public static void printAcronym(String s){
    String[] arrayString = s.split(" ");
    for(int i = 0; i < arrayString.length; i ++){
        if(arrayString[i].equals("of")) 
          arrayString[i] = arrayString[i].toLowerCase();
        else if (arrayString[i].equals("the")) 
          arrayString[i] = arrayString[i].toLowerCase();
        else
        arrayString[i] = arrayString[i].toUpperCase();
    }
    for(int i = 0; i < arrayString.length; i++){
        System.out.print(arrayString[i].charAt(0));
    }
}
```
