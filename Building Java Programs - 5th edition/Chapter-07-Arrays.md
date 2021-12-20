## Chapter 7: Arrays

### This file contains my implementation for all exercises of the Chapter 7: Arrays.

#### BJP5 Exercise 7.1: lastIndexOf
```
public static int lastIndexOf(int[] list, int n){
    int lastIndex = -1;
    for(int i = 0; i < list.length; i++){
        if(list[i] == n)
            lastIndex = i;
    }
    return lastIndex;
}
```
#### BJP5 Exercise 7.2: range
```
public static int range(int[] a){
    int max = a[0];
    int min = a[0];
    for(int i = 0; i < a.length; i++){
        if(a[i] > max) max = a[i];
        if(a[i] < min) min = a[i];
    }
    return max - min + 1;
}
```
#### BJP5 Exercise 7.3: countInRange
```
public static int countInRange(int[] arr, int min, int max){
    int count = 0;
    for(int i = 0; i < arr.length; i++){
        if(min <= arr[i] && arr[i] <= max)
            count ++;
    }
    return count;
}
```
#### BJP5 Exercise 7.4: isSorted
```
public static boolean isSorted(double[] list){
    for(int i = 0; i < list.length - 1; i++){
        if(list[i] > list[i+1])
            return false;
    }
    return true;
}
```
#### BJP5 Exercise 7.5: mode
```
public static int mode(int[] list){
    int[] count = new int[102];
    int max = 0;
    int min = list[0];
    for(int i = 0; i < list.length; i++){
        count[list[i]] += 1;
        if(count[list[i]] > max) {
            max = count[list[i]];
            min = list[i];
        }
        if(count[list[i]] == max){
            if(min >= list[i]) min = list[i];
        }
    }
    return min;
    
}
```
#### BJP5 Exercise 7.6: stdev
```
public static double stdev(int[] array) {
    int sum = 0;
    
    for (int i = 0; i < array.length; i++) {
        sum += array[i];
    }
    
    double average = (double)sum / array.length;
    double top = 0;
    
    for (int i = 0; i < array.length; i++) {
        top += Math.pow(array[i] - average, 2);
    }
    
    return Math.sqrt(top / (array.length - 1));
}
```
#### BJP5 Exercise 7.7: kthLargest
```
public static int kthLargest(int k, int[] arr){
    Arrays.sort(arr);
    return arr[arr.length - 1 - k];
}
```
#### BJP5 Exercise 7.8: median
```
public static int median(int[] arr){
    Arrays.sort(arr);
    return arr[arr.length / 2];
}
```
#### BJP5 Exercise 7.9: minGap
```
public int minGap(int[] bids, int price) {
    int bid = -1;
    
    for(int i = 0; i < bids.length; i++) {
        if(price - bids[i] >= 0 && bids[i] > bid)
            bid = bids[i];
    }
    
    return bid;
}
```
#### BJP5 Exercise 7.10: percentEven
```
public static double percentEven(int[] arr){
    if(arr.length == 0) return 0.0;
    int count = 0;
    for(int i = 0; i < arr.length; i++){
        if(arr[i] % 2 == 0){
            count ++;
        }
    }
    return (double) count / arr.length * 100;
}
```
#### BJP5 Exercise 7.11: isUnique
```
public static boolean isUnique(int[] arr){
    Arrays.sort(arr);
    for(int i = 1; i < arr.length; i ++){
        if(arr[i-1] == arr[i])
            return false;
    }
    return true;
}
```
#### BJP5 Exercise 7.12: priceIsRight
```
public int priceIsRight(int[] bids, int price) {
    int bid = -1;
    
    for(int i = 0; i < bids.length; i++) {
        if(price - bids[i] >= 0 && bids[i] > bid)
            bid = bids[i];
    }
    
    return bid;
}
```
#### BJP5 Exercise 7.13: longestSortedSequence
```
public static int longestSortedSequence(int[] arr){
    if(arr.length == 0) return 0;
    int res = 1;
    int count = 1;
    for(int i = 0; i < arr.length - 1; i++){
        if(arr[i] <= arr[i+1]) {
            count ++;
            if(count >= res) res = count;
        }
        else{
            count = 1;
        }
    }
        return res;
}
```
#### BJP5 Exercise 7.14: contains
```
public static boolean contains(int[] list1, int[] list2){
    for(int i = 0; i < list1.length - list2.length + 1; i++){
        for(int j = 0; j < list2.length; j++){
            if(list1[i + j] != list2[j]) break;
            if(j == list2.length - 1) return true;
        }
    }
    return false;
}
```
#### BJP5 Exercise 7.15: collapse
```
public static int[] collapse(int[] array) {
    int length = array.length;
    int[] newArray;
    if (length % 2 == 0) {
        newArray = new int[length / 2];
    } else {
        newArray = new int[length / 2 + 1];
        newArray[length / 2] = array[array.length - 1];
    }
    
    for (int i = 0; i < length / 2; i++) {
        newArray[i] = array[2 * i] + array[2 * i + 1];
    } 
    
    return newArray;
}
```
#### BJP5 Exercise 7.16: append
```
public static int[] append(int[] list1, int[] list2) {
    int[] newList = new int[list1.length + list2.length];
    int index = 0;
    
    for (int i = 0; i < list1.length; i++) {
        newList[index] = list1[i];
        index++;
    }
    
    for (int i = 0; i < list2.length; i++) {
        newList[index] = list2[i];
        index++;
    }
    
    return newList;
}
```
#### BJP5 Exercise 7.17: vowelCount
```
public static int[] vowelCount(String s){
    int[] count = {0,0,0,0,0};
    for(int i = 0; i < s.length(); i ++){
        if(s.charAt(i) == 'a') count[0] ++;
        if(s.charAt(i) == 'e') count[1] ++;
        if(s.charAt(i) == 'i') count[2] ++;
        if(s.charAt(i) == 'o') count[3] ++;
        if(s.charAt(i) == 'u') count[4] ++;
    }
    return count;
}
```
#### BJP5 Exercise 7.18: evenBeforeOdd
```
public static void evenBeforeOdd(int[] arr){
    int fixed = 0;
    for(int i = 0; i < arr.length; i ++){
        if(arr[i] % 2 == 0){
            int tmp = arr[i];
            arr[i] = arr[fixed];
            arr[fixed] = tmp;
            fixed ++;
        }
    }
}
```
#### BJP5 Exercise 7.19: wordLengths
```
public void wordLengths(Scanner sc) {
    int[] counts = new int[80];
    
    while(sc.hasNext()) {
        String str = sc.next();
        counts[str.length() - 1]++;
    }
    
    for(int i = 0; i < counts.length; i++) {
        if(counts[i] > 0) {
            System.out.print((i+1) + ": " + counts[i] + "\t");
            for(int j = 0; j < counts[i]; j++)
                System.out.print("*");
            System.out.println();
        }
    }
}
```
#### BJP5 Exercise 7.20: matrixAdd
```
public static int[][] matrixAdd(int[][] a, int[][] b) {
    if (a.length == 0 || b.length == 0) {
        return new int[0][0];
    } else {
        int[][] result = new int[a.length][a[0].length];
        
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
            
        }
        
        return result;
    }
}
```
