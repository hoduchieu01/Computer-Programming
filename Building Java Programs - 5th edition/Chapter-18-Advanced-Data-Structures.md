## Chapter 18: Advanced Data Structures

### This file contains my implementation for all exercises of the Chapter 18: Advanced Data Structures.

#### BJP5 Exercise 18.1: addAllHashIntSet
```
public void addAll(HashIntSet other) {
    Node[] other_data = other.elementData;
    for (Node node : other_data) {
	while(node != null) {
	    add(node.data);
	    node = node.next;
	}
    }
}
```
#### BJP5 Exercise 18.2: containsAllHashIntSet
```
public boolean containsAll(HashIntSet other) {
    for (Node front : other.elementData) {
	Node current = front;
	while(current != null) {
	    if (!contains(current.data)) 
		return false;
	    
	    current = current.next;
	}
    }
    return true;
}
```
#### BJP5 Exercise 18.3: equalsHashIntSet
```
public boolean equals(HashIntSet other) {
    return containsAll(other) && size == other.size;
}



public boolean containsAll(HashIntSet other) {
    for (Node front : other.elementData) {
	Node current = front;
	while(current != null) {
	    if (!contains(current.data)) 
		return false;
	    
	    current = current.next;
	}
    }
    return true;
}
```
#### BJP5 Exercise 18.4: removeAllHashIntSet
```
public void removeAll(HashIntSet other) {
    for (Node front : other.elementData) {
	Node current = front;
	while(current != null) {
	    if (contains(current.data))
		remove(current.data);

	    current = current.next;
	}
    }
}
```
#### BJP5 Exercise 18.5: retainAllHashIntSet
```
public void retainAll(HashIntSet other) {
    for (Node front : elementData) {
	Node current = front;
	while (current != null) {
	    if (!other.contains(current.data))
		remove(current.data);

	    current = current.next;
	}
    }
}
```
#### BJP5 Exercise 18.6: toArrayHashIntSet
```
public int[] toArray() {
    int[] res = new int[size];
    int i= 0;
    for (Node front : elementData) {
	Node current = front;
	while(current != null) {
	    res[i++] = current.data;
	    current = current.next;
	}
    }
    return res;
}
```
#### BJP5 Exercise 18.7: toStringHashIntSet
```
public String toString() {
    int[] array = toArray();
    String res = "[";

    for (int i=0; i<size; i++) {
      res += array[i];
      if (i < size -1)
          res += ", ";
    }
    res += "]";
    return res;
}

public int[] toArray() {
    int[] res = new int[size];
    int i= 0;
    for (Node front : elementData) {
	Node current = front;
	while(current != null) {
	    res[i++] = current.data;
	    current = current.next;
	}
    }
    return res;
}
```
#### BJP5 Exercise 18.8: descending
```
import java.util.*;

public void descending(int[] x) {
    if (x.length == 0)
	return;
    
    Comparator<Integer> cmp;
    cmp = new Comparator<Integer>() {
	    public int compare(Integer e1, Integer e2) {
		  return e2 - e1;
	    }
	  };
    
    Queue<Integer> q = new PriorityQueue<Integer>(x.length, cmp);
    for (int i: x) {
	    q.add(i);
    }

    int i=0;
    while (!q.isEmpty()) {
	    x[i++] = q.poll();
    }
}
```
#### BJP5 Exercise 18.9: kthSmallest
```
public int kthSmallest(PriorityQueue<Integer> pq, int k) {
    if (k <= 0 || k > pq.size())
	    throw new IllegalArgumentException();

    Queue<Integer> q = new LinkedList<Integer>();
    int kthValue = 0;
    int i=0;
    while(!pq.isEmpty()) {
    int element = pq.remove();
    if ( ++i == k)
        kthValue = element;
    q.add(element);
      }

      while(!q.isEmpty())
    pq.add(q.remove());

    return kthValue;
}
```
#### BJP5 Exercise 18.10: isConsecutive
```

public boolean isConsecutive(PriorityQueue<Integer> pq) {
    if (pq.isEmpty())
	    return true;

    Queue<Integer> queueInteger = new LinkedList<Integer>();
    boolean isConsecutive = true;
    while (!pq.isEmpty()) {
    int element = pq.remove();
    if (isConsecutive && !pq.isEmpty() && element != pq.peek() - 1) {
        isConsecutive = false;
    }
    queueInteger.add(element);
    }
    while(!queueInteger.isEmpty())
	  pq.add(queueInteger.remove());
    return isConsecutive;
}
```
#### BJP5 Exercise 18.11: removeDuplicates
```
public void removeDuplicates(PriorityQueue<Integer> pq) {
    Queue<Integer> queueInteger = new LinkedList<Integer>();
    int old = Integer.MAX_VALUE;
    while(!pq.isEmpty()) {
    int element = pq.remove();
    if (element != old) {
        queueInteger.add(element);
    }
	  old = element;
    }
    while(!queueInteger.isEmpty())
	pq.add(queueInteger.remove());
    
}
```
#### BJP5 Exercise 18.12: stutter
```
public void stutter(PriorityQueue<Integer> pq) {
    Queue<Integer> queueInteger = new LinkedList<Integer>();
    while(!pq.isEmpty()) 
	  queueInteger.add(pq.remove());
  
    while(!queueInteger.isEmpty()) {
      int element = queueInteger.remove();
      pq.add(element);
      pq.add(element);
    }
}
```
#### BJP5 Exercise 18.13: fillGaps
```
public static void fillGaps(PriorityQueue<Integer> priorityQueue){
    int n=priorityQueue.size();
    int a[]=new int[n];
    int k=0;
    Iterator<Integer> temp=priorityQueue.iterator();
    while(temp.hasNext()){
        a[k]=temp.next();
        k++;
    }
    int maxx=a[0];
    int minn=a[0];
    for(int i=1;i<n;i++){
        if(a[i]>maxx)
            maxx=a[i];
        if(a[i]<minn)
            minn=a[i];
    }
    for(int i=minn+1;i<maxx;i++){
        int flag=0;
        for(int j=0;j<n;j++){
            if(i==a[j]){
                flag=1;
                break;
            }
        }
        if(flag==0){
            priorityQueue.add(i);
        }
    }
}
```
#### BJP5 Exercise 18.14: toArrayHeapIntPriorityQueue
```
public int[] toArray() {
    int[] res = new int[size];
    for (int i=0; i<size; i++) {
	res[i] = elementData[i+1];
    }
    return res;
}
```
#### BJP5 Exercise 18.15: toStringHeapIntPriorityQueue
```
public String toString() {
    String res = "[";
    for (int i=1; i<=size; i++) {
	    res += elementData[i];
      if (i < size) {
          res += ", ";
      }
    }
    res += "]";
    return res;
}
```
#### BJP5 Exercise 18.16: mergeHeapIntPriorityQueue
```
public void merge(HeapIntPriorityQueue hq) {
    for (int i=1; i<= hq.size; i++) {
	add(hq.elementData[i]);
    }
}
```
