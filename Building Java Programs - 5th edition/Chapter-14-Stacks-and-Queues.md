## Chapter 14: Stacks and Queues

### This file contains my implementation for all exercises of the Chapter 14: Stacks and Queues.

#### BJP5 Exercise 14.1: splitStack
```
public void splitStack(Stack<Integer> s) {
    Queue<Integer> q = new LinkedList<Integer>();
    int numNegatives = 0;
    // move all elements of stack S to queue Q
    while(!s.isEmpty()) {
        if(s.peek() < 0)
            numNegatives++;
        q.add(s.pop());
    }
    
    while(numNegatives > 0){
        if(q.peek() < 0){
            s.push(q.remove());
            numNegatives --;
        }
        else{
            q.add(q.remove());
        }
    }
    
    while(!q.isEmpty()){
        s.push(q.remove());
    }

}
```
#### BJP5 Exercise 14.2: stutter
```
public static void stutter(Stack<Integer> s) {
    Queue<Integer> q = new LinkedList<Integer>();
    
    while (!s.isEmpty()) {
        int num = s.pop();
        q.add(num);
        q.add(num);
    }
    
    while (!q.isEmpty()) {
        s.push(q.remove());
    }
    
     while (!s.isEmpty()) {
        q.add(s.pop());
    }
    
    while (!q.isEmpty()) {
        s.push(q.remove());
    }
   
}
```
#### BJP5 Exercise 14.3: copyStack
```
public Stack<Integer> copyStack(Stack<Integer> s1) {
    Stack<Integer> s2 = new Stack<Integer>();
    Queue<Integer> q = new LinkedList<Integer>();
            
    while(!s1.isEmpty())
        s2.push(s1.pop());
                            
    while(!s2.isEmpty())
        q.add(s2.pop());
                                            
    while(!q.isEmpty()) {
        int n = q.remove();
        s1.push(n);
        s2.push(n);
    }
    
    return s2;
}
```
#### BJP5 Exercise 14.4: collapse
```
public static void collapse(Stack<Integer> s) {
    Queue<Integer> q = new LinkedList<Integer>();
    
    if (s.size() % 2 != 0) {
        q.add(s.pop());
    }
    
    while (!s.isEmpty()) {
        q.add(s.pop() + s.pop());
    }
    
    while (!q.isEmpty()) {
        s.push(q.remove());
    }
    
    while (!s.isEmpty()) {
        q.add(s.pop());
    }
    
    while (!q.isEmpty()) {
        s.push(q.remove());
    }
}
```
#### BJP5 Exercise 14.5: equals
```
public static boolean equals(Stack<Integer> s1, Stack<Integer> s2) {
    Stack<Integer> storage = new Stack<Integer>();
    
    if (s1.size() != s2.size()) {
        return false;
    } else {
        boolean same = true;
        
        while (same && !s1.isEmpty()) {
            int num1 = s1.pop();
            int num2 = s2.pop();
            if (num1 != num2) {
                same = false;
            }
            storage.add(num1);
            storage.add(num2);
        }
        
        while (!storage.isEmpty()) {
            s2.add(storage.pop());
            s1.add(storage.pop());
        }
        
        return same;
    }
}
```
#### BJP5 Exercise 14.6: rearrange
```
public static void rearrange(Queue<Integer> q) {
    Stack<Integer> s = new Stack<Integer>();
    int oldSize = q.size();
    
    for (int i = 0; i < oldSize; i++) {
        int num = q.remove();
        if (num % 2 == 0) {
            s.push(num);
        } else {
            q.add(num);
        }
    }
    
    for (int i = 0; i < 2; i++) {
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
    
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }
}
```
#### BJP5 Exercise 14.7: reverseHalf
```
public void reverseHalf(Queue<Integer> q) {
    Stack<Integer> s = new Stack<Integer>();
    int size = q.size();
            
    for(int i = 0; i < size; i++) {
        if(i % 2 == 1)
            s.push(q.remove());
        else
            q.add(q.remove());
    }
                                                                
    while(!s.isEmpty()) {
        q.add(q.remove());
        q.add(s.pop());
    }
    
    if(size % 2 == 1)
        q.add(q.remove());
}
```
#### BJP5 Exercise 14.8: isPalindrome
```
public static boolean isPalindrome(Queue<Integer> q) {
    Stack<Integer> s = new Stack<Integer>();
    int oldSize = q.size();
    
    for (int i = 0; i < oldSize; i++) {
        int num = q.remove();
        q.add(num);
        s.push(num);
    }
    
    boolean same = true;
    
    for (int i = 0; i < oldSize; i++) {
        int num = q.remove();
        if (num != s.pop()) {
            same = false;
        }
        q.add(num);
    }
    
    return same;
}
```
#### BJP5 Exercise 14.9: switchPairs
```
public static void switchPairs(Stack<Integer> s){
    Queue<Integer> q = new LinkedList<Integer>();
    if(s.size() % 2 == 0){
        while(!s.isEmpty()){
        int x1 = s.pop();
        int x2 = s.pop();
        q.add(x2);
        q.add(x1);
        }
    }
    else{
        int x = s.pop();
        q.add(x);
        while(!s.isEmpty()){
        int x1 = s.pop();
        int x2 = s.pop();
        q.add(x2);
        q.add(x1);
        }
    }
    while(!q.isEmpty()){
        s.add(q.remove());
    }
    while(!s.isEmpty()){
        q.add(s.pop());
    }
    while(!q.isEmpty()){
        s.add(q.remove());
    }
}
```
#### BJP5 Exercise 14.10: isConsecutive
```
public boolean isConsecutive(Stack<Integer> s) {
    if(s.size() < 2)
        return true;
        
    Queue<Integer> q = new LinkedList<Integer>();
    int next = s.pop();
    q.add(next);
    boolean consecutive = true;
    
    while(!s.isEmpty()) {
        int n = s.pop();
        
        if(n + 1 != next)
            consecutive = false;
            
        next = n;
        q.add(n);
    }
    
    while(!q.isEmpty())
        s.push(q.remove());
        
    while(!s.isEmpty())
        q.add(s.pop());
        
    while(!q.isEmpty())
        s.push(q.remove());
        
    return consecutive;
}
```
#### BJP5 Exercise 14.11: reorder
```
public void reorder(Queue<Integer> q) {
    Stack<Integer> s = new Stack<Integer>();
    int size = q.size();
    int count = 0;
    
    for(int i = 0; i < size; i++) {
        int n = q.remove();
        
        if(n < 0) {
            s.push(n);
        } else {
            q.add(n);
            count++;
        }
    }
    
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < count; i++)
        q.add(q.remove());
}
```
#### BJP5 Exercise 14.12: shift
```
public void shift(Stack<Integer> s, int n) {
    Queue<Integer> q = new LinkedList<Integer>();
    int size = s.size();
    
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < size - n; i++)
        s.push(q.remove());
        
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < n; i++)
        q.add(q.remove());
        
    while(!q.isEmpty())
        s.push(q.remove());
}
```
#### BJP5 Exercise 14.13: expunge
```
public void expunge(Stack<Integer> s1) {
    if(s1.size() < 2)
        return;
        
    Stack<Integer> s2 = new Stack<Integer>();
    int ontop = s1.pop();
    s2.add(ontop);
    
    while(!s1.isEmpty()) {
        int n = s1.pop();
        
        if(n >= ontop) {
            s2.add(n);
            ontop = n;
        }
    }
    
    while(!s2.isEmpty())
        s1.add(s2.pop());
}
```
#### BJP5 Exercise 14.14: reverseFirstK
```
public void reverseFirstK(int k, Queue<Integer> q) {
    if(q == null || k > q.size())
        throw new IllegalArgumentException();
        
    if(k <= 0)
        return;
        
    Stack<Integer> s = new Stack<Integer>();
    int size = q.size();
    
    for(int i = 0; i < k; i++)
        s.push(q.remove());
        
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < size - k; i++)
        q.add(q.remove());
}
```
#### BJP5 Exercise 14.15: isSorted
```
public boolean isSorted(Stack<Integer> s1) {
    if(s1.size() < 2)
        return true;
        
    Stack<Integer> s2 = new Stack<Integer>();
    boolean sorted = true;
    int ontop = s1.pop();
    s2.push(ontop);
    
    while(!s1.isEmpty()) {
        int n = s1.pop();
        
        if(n < ontop)
            sorted = false;
            
        ontop = n;
        s2.push(ontop);
    }
    
    while(!s2.isEmpty())
        s1.push(s2.pop());
        
    return sorted;
}
```
#### BJP5 Exercise 14.16: mirror
```
public void mirror(Stack<Integer> s) {
    if(s == null)
        throw new IllegalArgumentException();
        
    Queue<Integer> q = new LinkedList<Integer>();
    int size = s.size();
    
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < size; i++) {
        int n = q.remove();
        s.push(n);
        q.add(n);
    }
    
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < size; i++)
        q.add(q.remove());
        
    while(!q.isEmpty())
        s.push(q.remove());
}
```
#### BJP5 Exercise 14.17: compressDuplicates
```
public void compressDuplicates(Stack<Integer> s) {
    if(s.size() == 0)
        return;
        
    Queue<Integer> q = new LinkedList<Integer>();
    int ontop = s.pop();
    int count = 1;
    
    while(!s.isEmpty()) {
        int n = s.pop();
        
        if(n == ontop) {
            count++;
        } else {
            q.add(ontop);
            q.add(count);
            count = 1;
            ontop = n;
        }
    }
    
    q.add(ontop);
    q.add(count);
    
    while(!q.isEmpty())
        s.push(q.remove());
        
    while(!s.isEmpty())
        q.add(s.pop());
        
    while(!q.isEmpty())
        s.push(q.remove());
}
```
#### BJP5 Exercise 14.18: mirrorHalves
```
public void mirrorHalves(Queue<Integer> q) {
    if(q == null || q.size() % 2 == 1)
        throw new IllegalArgumentException();
        
    Stack<Integer> s = new Stack<Integer>();
    int size = q.size();
    for(int k = 0; k < 2; k++) {
        for(int i = 0; i < size / 2; i++) {
            int n = q.remove();
            q.add(n);
            s.push(n);
        }
        
        while(!s.isEmpty())
            q.add(s.pop());
    }
}
```
#### BJP5 Exercise 14.19: removeMin
```
public int removeMin(Stack<Integer> s) {
    Queue<Integer> q = new LinkedList<Integer>();
    int min = s.peek();
    
    while(!s.isEmpty()) {
        int n = s.pop();
        
        if(n < min)
            min = n;
            
        q.add(n);
    }
    
    while(!q.isEmpty()) {
        int n = q.remove();
        
        if(n > min)
            s.push(n);
    }
    
    while(!s.isEmpty())
        q.add(s.pop());
        
    while(!q.isEmpty())
        s.push(q.remove());
        
    return min;
}
```
#### BJP5 Exercise 14.20: interleave
```
public void interleave(Queue<Integer> q) {
    if(q.size() % 2 != 0)
        throw new IllegalArgumentException();
        
    Stack<Integer> s = new Stack<Integer>();
    int size = q.size();
    
    for(int i = 0; i < size / 2; i++)
        s.push(q.remove());
        
    while(!s.isEmpty())
        q.add(s.pop());
        
    for(int i = 0; i < size / 2; i++)
        s.push(q.remove());
        
    while(!s.isEmpty()) {
        q.add(s.pop());
        q.add(q.remove());
    }
    
    while(!q.isEmpty())
        s.push(q.remove());
        
    while(!s.isEmpty())
        q.add(s.pop());
}
```
#### BJP5 Exercise 14.21: maxToTop
```
public static void maxToTop(Stack<Integer> s){
    Queue<Integer> q = new LinkedList<Integer>();
    int max = s.pop();
    q.add(max);
    while(!s.isEmpty()){
        int x = s.pop();
        if(x >= max) max = x;
        q.add(x);
    }
    s.add(max);
    while(!q.isEmpty()){
        int x = q.remove();
        if(x != max)
            s.add(x);
    }
    while(!s.isEmpty()){
        q.add(s.pop());
    }
    while(!q.isEmpty()){
        s.add(q.remove());
    }
}
```
