## Chapter 16: LinkedLists

### This file contains my implementation for all exercises of the Chapter 16: LinkedLists.

#### BJP5 Exercise 16.1: set
```
public void set(int index, int value) {
    ListNode current = front;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    current.data = value;
}
```
#### BJP5 Exercise 16.2: min
```
public int min() {
    if (front == null) {
        throw new NoSuchElementException();
    } else {
        int min = front.data;
        ListNode current = front.next;
        
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        
        return min;
    }
}
```
#### BJP5 Exercise 16.3: isSorted
```
public boolean isSorted() {
    ListNode pre = front;
    
    if (front == null) {
        return true;
    }
    
    while (pre.next != null) {
        ListNode current = pre.next;
        if (current.data < pre.data) {
            return false;
        }
        pre = current;
    }
    
    return true;
}
```
#### BJP5 Exercise 16.4: lastIndexOf
```
public int lastIndexOf(int value) {
    ListNode current = front;
    int index = -1;
    int i = 0;
    
    while (current != null) {
        if (current.data == value) {
            index = i;
        }
        
        i++;
        current = current.next;
    }
    
    return index;
}
```
#### BJP5 Exercise 16.5: countDuplicates
```
public int countDuplicates() {
    int sum = 0;
    ListNode current = front;
    
    if (front == null) {
        return 0;
    }
    
    while (current.next != null) {
        if (current.data == current.next.data) {
            sum++;
        }
        current = current.next;
    }
    
    return sum;
}
```
#### BJP5 Exercise 16.6: hasTwoConsecutive
```
public boolean hasTwoConsecutive() {
    ListNode current = front;
    
    if (front == null) {
        return false;
    } else {
    
        while (current.next != null) {
            if (current.data + 1 == current.next.data) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
}
```
#### BJP5 Exercise 16.7: deleteBack
```
public int deleteBack() {
    ListNode current = front;
    
    if (current == null) {
        throw new NoSuchElementException();
    } else if (current.next == null) {
        int value = current.data;
        front = null;
        return value;
    } else {
    
        while (current.next.next != null) {
            current = current.next;
        }
        
        int value = current.next.data;
        current.next = null;
        return value;
    }
}
```
#### BJP5 Exercise 16.8: switchPairs
```
public void switchPairs() {
    ListNode current = front;
    
    while (current != null && current.next != null) {
        int temp = current.data;
        current.data = current.next.data;
        current.next.data = temp;
        current = current.next.next;
    }
}
```
#### BJP5 Exercise 16.9: stutter
```
public void stutter() {
    ListNode current = front;
    
    while (current != null) {
        current.next = new ListNode(current.data, current.next);
        current = current.next.next;
    }
}
```
#### BJP5 Exercise 16.10: stretch
```
public void  stretch(int copies) {
    if (copies <= 0) {
        front = null;
    } else {
        ListNode current = front;
        
        while (current != null) {
            int value = current.data;
            
            for (int i = 1; i < copies; i++) {
                current.next = new ListNode(value, current.next);
                current = current.next;
            }
            
            current = current.next;
        }
    }
}
```
#### BJP5 Exercise 16.11: compress
```
public void compress(int factor) {
    ListNode current = front;
    
    while (current != null) {
    
        for (int i = 1; i < factor; i++) {
            if (current.next != null) {
                current.data += current.next.data;
                current.next = current.next.next;
            } 
        }
        
        current = current.next;
    }
}
```
#### BJP5 Exercise 16.12: split
```
public void split() {
    ListNode current = front;
    
    if (current != null) {
    
        while (current.next != null) {
        
            if (current.next.data < 0) {
                ListNode temp = current.next.next;
                current.next.next = front;
                front = current.next;
                current.next = temp;
            } else {
                current = current.next;
            }
        }
        
    }
    
}
```
#### BJP5 Exercise 16.13: transferFrom
```
public void transferFrom(LinkedIntList list) {
    if (front == null) {
        front = list.front;
    } else {
        ListNode current = front;
        
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = list.front;
    }
    
    list.front = null;
}
```
#### BJP5 Exercise 16.14: removeAll
```
public void removeAll(int value) {

    while (front != null && front.data == value) {
        front = front.next;
    }
    
    ListNode current = front;
    
    while (current != null && current.next != null) {
    
        if (current.next.data == value) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
        
    } 
}
```
#### BJP5 Exercise 16.15: equals
```
public boolean equals2(LinkedIntList list) {
    ListNode current1 = front;
    ListNode current2 = list.front; 
    
    if (current1 == null && current2 == null) {
        return true;
    } else if (current1 != null && current2 != null) {
    
        while (current1 != null && current2 != null) {
        
            if (current1.data != current2.data) {
                return false;
            } else {
                current1 = current1.next;
                current2 = current2.next;
            }
            
        }
        
        return current1 == null && current2 == null;
    }
    
    return false;
}
```
#### BJP5 Exercise 16.16: removeEvens
```
public LinkedIntList removeEvens() {
    LinkedIntList result = new LinkedIntList();
    
    if (front != null) {
        result.front = front;
        front = front.next;
        result.front.next = null;
        ListNode currentResult = result.front;
        ListNode current = front;
        
        while (current != null && current.next != null) {
            currentResult.next = current.next;
            currentResult = currentResult.next;
            current.next = current.next.next;
            
            if (currentResult != null) {
                currentResult.next = null;
            }
            
            current = current.next;           
        }
        
    }
    
    return result;
}
```
#### BJP5 Exercise 16.17: removeRange
```
public void removeRange(int startIndex, int endIndex) {
    if (startIndex < 0 || endIndex < 0) {
        throw new IllegalArgumentException();
    } else {
        int index = 0;
        ListNode current = front;
        ListNode start = front;
        ListNode end = null;
        
        while (current != null) {
        
            if (index + 1 == startIndex) {
                start = current;
            } else if (index == endIndex) {
                end = current;
            }
            
            current = current.next;
            index++;
        }
        
        if (startIndex == 0) {
            front = end.next;
        } else {
            start.next = end.next;
        }
        
    }
}
```
#### BJP5 Exercise 16.18: doubleList
```
public void doubleList() {
    if (front != null) {
        ListNode end = front;
        int size = 1;
        
        while (end.next != null) {
            size++;
            end = end.next;
        }
        
        ListNode current = front;
        
        while (size > 0) {
            size--;
            end.next = new ListNode(current.data);
            current = current.next;
            end = end.next;
        }
        
    }
}
```
#### BJP5 Exercise 16.19: rotate
```
public void rotate() {
    if (front != null && front.next != null) {
        ListNode firstNode = front;
        front = front.next;
        firstNode.next = null;
        ListNode current = front;
        
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = firstNode;
    }
}
```
#### BJP5 Exercise 16.20: shift
```
public void shift() {
    if (front != null) {
        int size = 1;
        ListNode current = front;
        
        while (current.next != null) {
            current = current.next;
            size++;
        }
        
        ListNode end = current;
        current = front;
        
        for (int i = 0; i < size / 2; i++) {
            end.next = current.next;
            end = end.next;
            current.next = current.next.next;
            current = current.next;
        }
        
        end.next = null;
    }
}
```
#### BJP5 Exercise 16.21: surroundWith
```
  public void surroundWith(int x, int y) {
            ListNode node = front;
            ListNode prev = null;
            while (node != null) {
                  if (node.data == x) {
                        ListNode n1 = new ListNode(y);
                        ListNode n2 = new ListNode(y);
                        n1.next = node;
                        if (prev == null) {
                              front = n1;
                        } else {
                              prev.next = n1;
                        }
                        n2.next = node.next;
                        node.next = n2;
                        node = node.next;
                  }
                  prev = node;
                  node = node.next;

            }

      }
```
#### BJP5 Exercise 16.22: reverse
```
public void reverse() {
    LinkedIntList list = new LinkedIntList();
    
    if (front != null) {
        ListNode current = front.next;
        front.next = null;
        list.front = front;
        
        while (current != null) {
            ListNode temp = current;
            current = current.next;
            temp.next = list.front;
            list.front = temp;
        }
        
    }
    
    front = list.front;
}
```
