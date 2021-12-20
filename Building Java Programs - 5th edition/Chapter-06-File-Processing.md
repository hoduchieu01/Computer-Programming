## Chapter 6: File Processing

### This file contains my implementation for all exercises of the Chapter 6: File Processing.

#### BJP5 Exercise 6.1: boyGirl
```
public static void boyGirl(Scanner console) {
    int boy = 0;
    int bSum = 0;
    int girl = 0;
    int gSum = 0;
    int i = 2;
    
    while (console.hasNext()) {
        console.next();
        if ((i / 2) % 2 != 0) {
            bSum += console.nextInt();
            boy++;
        } else {
            gSum += console.nextInt();
            girl++;
        }
        i += 2;
    }
    
    System.out.println(boy + " boys, " + girl + " girls");
    System.out.println("Difference between boys' and girls' sums: " + Math.abs(bSum - gSum));
}
```
#### BJP5 Exercise 6.2: evenNumbers
```
public static void evenNumbers(Scanner console) {
    int totalNum = 0;
    int sum = 0;
    int evenNum = 0;
    
    while (console.hasNextInt()) {
        int num = console.nextInt();
        totalNum++;
        sum += num;
        if (num % 2 == 0) {
            evenNum++;
        }
    }
    
    System.out.printf("%d numbers, sum = %d\n", totalNum, sum);
    System.out.printf("%d evens (%.2f%%)\n", evenNum, 100.0 * evenNum / totalNum);
}
```
#### BJP5 Exercise 6.3: negativeSum
```
public static boolean negativeSum(Scanner console) {
    int sum = 0;
    int count = 0;
    
    while (console.hasNextInt()) {
        int num = console.nextInt();
        sum += num;
        count += 1;
        if(sum < 0) break;
    }
    if(sum < 0){
        System.out.println(sum + " after " + count + " steps");
        return true;
    }
    else{
        System.out.println("no negative sum");
        return false;
    }
    
}
```
#### BJP5 Exercise 6.4: countCoins
```
public void countCoins(Scanner sc) {
    double totalCents = 0;
    
    while(sc.hasNext()) {
        int amount = sc.nextInt();
        String type = sc.next().toLowerCase();
        
        if(type.equals("pennies")) {
            totalCents += amount;
        } else if(type.equals("nickels")) {
            totalCents += 5 * amount;
        } else if(type.equals("dimes")) {
            totalCents += 10 * amount;
        } else if(type.equals("quarters")) {
            totalCents += 25 * amount;
        }
    }
    
    System.out.println("Total money: $" + 
        String.format("%.2f", totalCents / 100));
}
```
#### BJP5 Exercise 6.5: collapseSpaces
```
public void collapseSpaces(Scanner sc) {
    while(sc.hasNextLine()) {
        String line = sc.nextLine();
        Scanner linesc = new Scanner(line);
        
        while(linesc.hasNext())
            System.out.print(linesc.next() + " ");
        
        System.out.println();
    }
}
```
#### BJP5 Exercise 6.6: readEntireFile
```
public String readEntireFile(Scanner sc) {
    StringBuilder text = new StringBuilder();
    
    while(sc.hasNextLine()) {
        text.append(sc.nextLine() + "\n");
    }
    
    return text.toString();
}
```
#### BJP5 Exercise 6.7: flipLines
```
public void flipLines(Scanner sc) {
    while(sc.hasNextLine()) {
        String temp = sc.nextLine();
        
        if(!sc.hasNextLine()) {
            System.out.println(temp);
            break;
        }
        
        System.out.println(sc.nextLine());
        System.out.println(temp);
    }
}
```
#### BJP5 Exercise 6.8: doubleSpace
```
public void doubleSpace(Scanner sc, PrintStream ps) {
    while(sc.hasNextLine()) {
        ps.println(sc.nextLine());
        ps.println();
    }
}
```
#### BJP5 Exercise 6.9: wordWrap
```
public void wordWrap(Scanner sc) {
    while(sc.hasNextLine()) {
        String line = sc.nextLine();
        
        while(line.length() > 60) {
            System.out.println(line.substring(0, 60));
            line = line.substring(60);
        }
        
        System.out.println(line);
    }
}
```
#### BJP5 Exercise 6.10: wordWrap2
```
public void wordWrap2(Scanner sc, PrintStream ps) {
    int maxLineLength = 60;
    
    while(sc.hasNextLine()) {
        String line = sc.nextLine();
        
        while(line.length() > maxLineLength) {
            ps.println(line.substring(0, 60));
            line = line.substring(60);
        }
        
        ps.println(line);
    }
}
```
#### BJP5 Exercise 6.11: wordWrap3
```
public void wordWrap3(Scanner sc) {
    while(sc.hasNextLine()) {
        String line = sc.nextLine();
        
        while(line.length() > 60) {
            int i = 60;
            
            while(!Character.isWhitespace(line.charAt(i)))
                i--;
            
            System.out.println(line.substring(0, i));
            
            while(i < line.length() && Character.isWhitespace(line.charAt(i)))
                i++;
            
            line = line.substring(i);
        }
        
        System.out.println(line);
    }
}
```
#### BJP5 Exercise 6.12: stripHtmlTags
```
public void stripHtmlTags(Scanner sc) {
    while(sc.hasNextLine()) {
        String htmlLine = sc.nextLine();
        
        boolean print = true;
        
        for(int i = 0; i < htmlLine.length(); i++) {
            if(htmlLine.charAt(i) == '<') {
                print = false;
            } else if(htmlLine.charAt(i) == '>') {
                print = true;
            } else if(print) {
                System.out.print(htmlLine.charAt(i));
            }
        }
        
        System.out.println();
    }
}
```
#### BJP5 Exercise 6.13: stripComments
```
public void stripComments(Scanner sc) {
    boolean multiLineComment = false;
    
    while(sc.hasNextLine()) {
        String line = sc.nextLine();
        
        boolean singleLineComment = false;

        int i = 0;
        
        while(i < line.length()) {
            if(multiLineComment && i <= line.length() - 2 && 
                line.charAt(i) == '*' && line.charAt(i+1) == '/') {
                multiLineComment = false;
                i += 2;
            } else if(!singleLineComment && i <= line.length() - 2 && 
                line.charAt(i) == '/' && line.charAt(i+1) == '*') {
                multiLineComment = true;
                i += 2;
            } else if(!multiLineComment && i <= line.length() - 2 && 
                line.charAt(i) == '/' && line.charAt(i+1) == '/') {
                singleLineComment = true;
                i += 2;
            } else if(singleLineComment || multiLineComment) {
                i++;
            } else {
                System.out.print(line.charAt(i));
                i++;
            }
        }
        
        if(!multiLineComment)
            System.out.println();
    }
}
```
#### BJP5 Exercise 6.14: printDuplicates
```
public void printDuplicates(Scanner sc) {
    while(sc.hasNextLine()) {
        Scanner line = new Scanner(sc.nextLine());
        String current = "";
        int count = 0;
        
        while(line.hasNext()) {
            String next = line.next();
            
            if(next.equals(current)) {
                count++;
            } else {
                if(count > 1)
                    System.out.print(current + "*" + count + " ");
                
                current = next;
                count = 1;
            }
        }
        
        if(count > 1)
            System.out.print(current + "*" + count);
        
        System.out.println();
    }
}
```
#### BJP5 Exercise 6.15: coinFlip
```
public void coinFlip(Scanner sc) {
    while(sc.hasNextLine()) {
        Scanner line = new Scanner(sc.nextLine());
        int h = 0;
        int t = 0;
        
        while(line.hasNext()) {
            String c = line.next().toLowerCase();
            if(c.equals("h")) {
                h++;
            } else {
                t++;
            }
        }
        
        double percent = (double) (h * 100) / (h + t);
        
        System.out.println(h + " heads (" + String.format("%.1f", percent) + 
            "%)");
        
        if(h > t)
            System.out.println("You win!");
        
        System.out.println();
    }
}
```
#### BJP5 Exercise 6.16: mostCommonNames
```
public int mostCommonNames(Scanner sc) {
    int unique = 0;
    
    while(sc.hasNextLine()) {
        Scanner line = new Scanner(sc.nextLine());
        unique++;
        int maxCount = 1;
        String maxName = line.next();
        int count = 1;
        String prevName = maxName;
        
        while(line.hasNext()) {
            String currentName = line.next();
            
            if(currentName.equals(prevName)) {
                count++;
            } else {
                if(count > maxCount) {
                    maxCount = count;
                    maxName = prevName;
                }
                
                unique++;
                count = 1;
                prevName = currentName;
            }
        }
        
        if(count > maxCount)
            maxName = prevName;
        
        System.out.println("Most common: " + maxName);
    }
    
    return unique;
}
```
#### BJP5 Exercise 6.17: inputStats
```
public void inputStats(Scanner sc) {
    String longestLine = "";
    int lineNum = 0;
    
    while(sc.hasNextLine()) {
        lineNum++;
        String line = sc.nextLine();
        Scanner lineSc = new Scanner(line);
        int count = 0;
        int longestToken = 0;
        
        while(lineSc.hasNext()) {
            String token = lineSc.next();
            count++;
            
            if(token.length() > longestToken)
                longestToken = token.length();
        }
        
        System.out.println("Line " + lineNum + " has " + count + 
            " tokens (longest = " + longestToken + ")");
        
        if(line.length() > longestLine.length())
            longestLine = line;
    }
    
    System.out.println("Longest line: " + longestLine);
}
```
#### BJP5 Exercise 6.18: plusScores
```
public void plusScores(Scanner sc) {
    while(sc.hasNextLine()) {
        String name = sc.nextLine();
        String scores = sc.nextLine();
        int plus = 0;
        int minus = 0;
        
        for(int i = 0; i < scores.length(); i++) {
            if(scores.charAt(i) == '+') {
                plus++;
            } else {
                minus++;
            }
        }
        
        double percent = (double) (plus * 100) / (plus + minus);
        System.out.println(name + ": " + String.format("%.1f", percent) + 
            "% plus");
    }
}
```
#### BJP5 Exercise 6.19: leetSpeak
```
public void leetSpeak(Scanner input, PrintStream output) {
    while(input.hasNextLine()) {
        String line = input.nextLine();
        StringBuilder leetLine = new StringBuilder();
        
        for(int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if(Character.isWhitespace(c)) {
                leetLine.append(c);
            } else {
                if(i == 0 || 
                    (i > 0 && Character.isWhitespace(line.charAt(i - 1))) )
                    leetLine.append('(');
                
                if(c == 'o') {
                    leetLine.append('0');
                } else if(c == 'l') {
                    leetLine.append('1');
                } else if(c == 'e') {
                    leetLine.append('3');
                } else if(c == 'a') {
                    leetLine.append('4');
                } else if(c == 't') {
                    leetLine.append('7');
                } else if(c == 's' && (i == line.length() - 1 || 
                    Character.isWhitespace(line.charAt(i + 1)))) {
                    leetLine.append('Z');
                } else {
                    leetLine.append(c);
                }
                
                if(i == line.length() - 1 || 
                    Character.isWhitespace(line.charAt(i + 1)))
                    leetLine.append(')');
            }
        }
        
        output.println(leetLine);
    }
}
```
#### BJP5 Exercise 6.20: pigLatin
```
 public static void pigLatin(Scanner sc){
        while(sc.hasNextLine()){
            String sentence = sc.nextLine();
            String words[] = sentence.split(" ");
            for(int i = 0;i < words.length; i++){
                String w = words[i];
                w = w.toLowerCase();
                char ch = w.charAt(0);
                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                    words[i] = w+"yay";
                    
                }
                else{
                    w = w.substring(1,w.length());
                    words[i] = w + ch + "ay";
                }
                System.out.print(words[i]+" ");
            }
            System.out.println();
        }
        sc.close();
    }
```
