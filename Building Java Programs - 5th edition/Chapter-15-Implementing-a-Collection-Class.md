## Chapter 15: Implementing a Collection Class

### This file contains my implementation for all exercises of the Chapter 15: Implementing a Collection Class.

#### BJP5 Exercise 15.5: runningTotal
```
public ArrayIntList runningTotal() {
    ArrayIntList result = new ArrayIntList(size);
    int sum = 0;
    for (int i = 0; i < size; i++) {
        sum += elementData[i];
        result.add(sum);
    }
    return result;
}
```
#### BJP5 Exercise 15.7: isPairwiseSorted
```
public boolean isPairwiseSorted() {
    int runs = size / 2;
    for (int i = 0; i < runs; i++) {
        if (elementData[2 * i] > elementData[2 * i + 1]) {
            return false;
        }
    }
    return true;
}
```
#### BJP5 Exercise 15.10: longestSortedSequence
```
public void removeFront(int num) {
    for (int i = 0; i < num; i++) {
        elementData[i] = elementData[num + i];
    }
    
    size -= num;
}

public void removeAll(int value) {
    for (int i = 0; i < size; i++) {
        if (elementData[i] == value) {
            remove(i);
            i--;
        }
    }
}
```
#### BJP5 Exercise 15.12: removeFront
```
public void removeFront(int num) {
    for (int i = 0; i < num; i++) {
        elementData[i] = elementData[num + i];
    }
    
    size -= num;
}

public void removeAll(int value) {
    for (int i = 0; i < size; i++) {
        if (elementData[i] == value) {
            remove(i);
            i--;
        }
    }
}
```
#### BJP5 Exercise 15.13: removeAll
```
public void removeAll(int value) {
    for (int i = 0; i < size; i++) {
        if (elementData[i] == value) {
            remove(i);
            i--;
        }
    }
}
```
#### BJP5 Exercise 15.14: printInversions
```
public void printInversions() {
    for (int i = 0; i < size; i++) {
        int num = elementData[i];
        
        for (int j = i; j < size; j++) {
            if (num > elementData[j]) {
                System.out.println("(" + num + ", " + elementData[j] + ")");
            }
        }
    }
}
```
#### BJP5 Exercise 15.15: mirror
```
public void mirror() {
    for (int i = size - 1; i >= 0; i--) {
        add(elementData[i]);
    }
}
```
#### BJP5 Exercise 15.17: stretch
```
public void stretch(int n) {
    if (n <= 0) {
        clear();
    } else {
        int oldSize = size;
        
        for (int i = 0; i < oldSize; i++) {
            
            for (int j = 1; j < n; j++) {
                add(n * i + j, elementData[n * i]);
            }
        }
    }
}
```
#### BJP5 Exercise 15.22: fromCounts
```
public ArrayIntList(int[] elementData, int size) {
        this.elementData = elementData;
        this.size = size;
}

private ArrayIntList  fromCounts() {
        int arrSize = 0;
        for (int i = 0; i < elementData.length; i+=2) {
            arrSize = arrSize+this.elementData[i];
        }
        int s  = 0;
        int[] elementData = new int[arrSize];
        ArrayIntList  array = new ArrayIntList (elementData, arrSize);
        for (int i = 0; i < elementData.length; i += 2) {
            for (int j = 1; j <= this.elementData[i]; j++) {
                array.elementData[s] = this.elementData[i+1];
                s++;
            }           
        }
        return array;
        
    }
```
