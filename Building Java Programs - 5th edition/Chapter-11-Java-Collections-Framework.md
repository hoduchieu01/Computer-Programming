## Chapter 11: Java Collections Framework

### This file contains my implementation for all exercises of the Chapter 11: Java Collections Framework.

#### BJP5 Exercise 11.2: alternate
```
public static List<Integer> alternate(List<Integer> list1, List<Integer> list2) {
    Iterator<Integer> i1 = list1.iterator();
    Iterator<Integer> i2 = list2.iterator();
    List<Integer> result = new ArrayList<Integer>();
    
    while(i1.hasNext() || i2.hasNext()) {
        if (i1.hasNext()) {
            result.add(i1.next());
        }
        
        if (i2.hasNext()) {
            result.add(i2.next());
        }
    }
    
    return result;
}
```
#### BJP5 Exercise 11.3: removeInRange
```
public static void removeInRange(List<Integer> list, int value, int start, int end) {
    Iterator<Integer> i = list.iterator();
    int index = 0;
    
    while (i.hasNext()) {
    
        if (index < end && index >= start) {
            if (i.next() == value) {
                i.remove();
                index--;
                end--;
            }
        } else {
            i.next();
        }
        index++;
    }
}
```
#### BJP5 Exercise 11.6: countUnique
```
public static int countUnique(List<Integer> list) {
    return new HashSet<Integer>(list).size();
}
```
#### BJP5 Exercise 11.7: countCommon
```
public static int countCommon(List<Integer> list, List<Integer> list1) {
    Set<Integer> set = new HashSet<Integer>(list);
    Set<Integer> set1 = new HashSet<Integer>(list1);
    Set<Integer> set3 = new HashSet<Integer>();
    for(int i:set) set3.add(i);
    for(int i:set1) set3.add(i);
    return set.size() + set1.size() - set3.size();
}
```
#### BJP5 Exercise 11.8: maxLength
```
public static int maxLength(Set<String> s){
    if(s.size() == 0) return 0;
    int max = Integer.MIN_VALUE;
    for(String i:s){
        if(i.length() > max) max = i.length();
    }
    return max;
}
```
#### BJP5 Exercise 11.9: hasOdd
```
public static boolean hasOdd(Set<Integer> s){
    if(s.size() == 0) return false;
    for(int i:s){
        if(i % 2 == 1) return true;
    }
    return false;
}
```
#### BJP5 Exercise 11.10: removeEvenLength
```
public void removeEvenLength(Set<String> set) {
    LinkedList<String> res = new LinkedList<String>();
    for(String str: set){
        if(str.length() % 2 == 1)
            res.add(str);
    }
    set.clear();
    set.addAll(res);
}
```
#### BJP5 Exercise 11.12: contains3
```
public boolean contains3(List<String> list){
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for(String s:list){
        if(map.containsKey(s)){
            map.put(s, map.get(s) + 1);
            if(map.get(s) == 3) return true;
        }
        else{
            map.put(s, 1);
        }
    }
    return false;
}
```
#### BJP5 Exercise 11.13: isUnique
```
public boolean isUnique(Map<String, String> map) {
    HashSet<String> set = new HashSet<String>();

    for(String key : map.keySet()) {
        String value = map.get(key);

        if(set.contains(value))
            return false;

        set.add(value);
    }

    return true;
}
```
#### BJP5 Exercise 11.14: intersect
```
public Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
     HashMap<String, Integer> map = new HashMap<String, Integer>();

    for(String key : m1.keySet()) {
        if(m2.containsKey(key) && m1.get(key) == m2.get(key))
            map.put(key, m1.get(key));
    }

    return map;
}
```
#### BJP5 Exercise 11.15: maxOccurrences
```
public static int maxOccurrences(List<Integer> list){
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    int maxOccurrences = 0;

    for(int n : list) {
        if(map.containsKey(n)) {
            map.put(n, map.get(n) + 1);
        } else {
            map.put(n, 1);
        }

        if(map.get(n) > maxOccurrences)
            maxOccurrences = map.get(n);
    }

    return maxOccurrences;
}
```
#### BJP5 Exercise 11.18: reverse
```
public Map<String, Integer> reverse(Map<Integer, String> map) {
    HashMap<String, Integer> res = new HashMap<String, Integer>();

    for(int key : map.keySet()){
        String value = map.get(key);
        if(!res.containsKey(value)){
            res.put(value, key);
        }
    }
    return res;
}
```
#### BJP5 Exercise 11.19: rarest
```
public static int rarest(Map<String, Integer> map){
    HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
    int rarest = Integer.MAX_VALUE;
    int numRarest = Integer.MAX_VALUE;

    for(String key : map.keySet()) {
        int value = map.get(key);

        if(hashmap.containsKey(value)) {
            hashmap.put(value, hashmap.get(value) + 1);
        } else {
            hashmap.put(value, 1);
        }
    }

    for(int key : hashmap.keySet()) {
        int value = hashmap.get(key);

        if(value < numRarest) {
            rarest = key;
            numRarest = value;
        } else if(value == numRarest) {
            rarest = key < rarest ? key : rarest;
        }
    }

    return rarest;
}
```
#### BJP5 Exercise 11.21: pairCounts
```
public static Map<String, Integer> pairCounts(List<String> list){
    Map<String, Integer> map = new HashMap<String, Integer>();
    for(String s:list){
       int i;
       for(i = 0; i < s.length() - 1; i++){
           String str = s.substring(i, i + 2);
           if(map.containsKey(str)){
               map.put(str, map.get(str) + 1);
           }
           else{
               map.put(str, 1);
           }
       }
    }
    return map;
}
```
