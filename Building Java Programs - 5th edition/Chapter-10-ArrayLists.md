## Chapter 10: ArrayLists

### This file contains my implementation for all exercises of the Chapter 10: ArrayLists.

#### BJP5 Exercise 10.2: swapPairs
```
public static void swapPairs(ArrayList<String> array) {

    for(int i = 0; i < array.size() / 2; i++){
        String tmp = array.get(2 * i + 1);
        array.set(2*i + 1, array.get(2*i));
        array.set(2*i, tmp);
    }
    
}
```
#### BJP5 Exercise 10.3: removeEvenLength
```
public static void removeEvenLength(ArrayList<String> list){
    for(int i = 0; i < list.size(); i++){
        if(list.get(i).length() % 2 == 0){
            list.remove(i);
            i--;
        }
    }
}
```
#### BJP5 Exercise 10.4: doubleList
```
public static void doubleList(ArrayList<String> list){
    for(int i = 0; i < list.size(); i+= 2){
        list.add(i, list.get(i)); 
    }
}
```
#### BJP5 Exercise 10.6: minToFront
```
public static void minToFront(ArrayList<Integer> list){
    if(list.size() == 0) return;
    int min = list.get(0);
    int position = 0;
    for(int i = 1; i < list.size(); i ++){
        if(min >= list.get(i)){
            min = list.get(i);
            position = i;
        }
    }
    list.remove(position);
    list.add(0, min);
}
```
#### BJP5 Exercise 10.7: removeDuplicates
```
public static void removeDuplicates(ArrayList<String> list){
    for(int i = 1; i < list.size(); i++){
        if(list.get(i-1).equals(list.get(i))){
           list.remove(i);
           i--;
        }
    }
}
```
#### BJP5 Exercise 10.10: removeInRange
```
public static void removeInRange(ArrayList<Integer> list, int elementValue, int startIndex, int endIndex){
    for(int i = startIndex; i < endIndex; i ++){
        if(list.get(i) == elementValue){
            list.remove(i);
            i--;
            endIndex --;
        }
    }
}
```
#### BJP5 Exercise 10.11: stutter
```
public static void stutter(ArrayList<String> array, int k) {
    if (k <= 0) {
        array.clear();
    } else {
        int n = array.size();
        
        for(int i = 0; i < n; i++){
            for(int j = 1; j < k; j++){
                array.add(i * k + j,array.get(i * k));
            }
        }
        
    }
}
```
#### BJP5 Exercise 10.12: markLength4
```
public static void markLength4(ArrayList<String> list){
    if(list.size() == 0) return;
    for(int i = 0; i < list.size(); i++){
        if(list.get(i).length() == 4){
            list.add(i, "****");
            i++;
        }
    }
    
}
```
#### BJP5 Exercise 10.14: removeShorterStrings
```
public static void removeShorterStrings(ArrayList<String> list){
    int n = list.size();
    for(int i = 0; i < n / 2; i++){
        if(list.get(i).length() <= list.get(i + 1).length()){
            list.remove(i);
        }
        else{
            list.remove(i + 1);
        }
    }
}
```
#### BJP5 Exercise 10.15: filterRange
```
public static void filterRange(ArrayList<Integer> list, int min, int max){
    for(int i = 0; i < list.size(); i ++){
        if( min <= list.get(i) && list.get(i) <= max){
            list.remove(i);
            i--;
        }
    }
}
```
#### BJP5 Exercise 10.17: interleave
```
public static void interleave(ArrayList<Integer> a1, ArrayList<Integer> a2) {
   int min =  Math.min(a1.size(), a2.size());
   int i;
   for(i = 0; i < min; i++){
      a1.add(2 * i + 1, a2.get(i));
   }
   if( i < a2.size()){
       for(int j = i; j < a2.size(); j++)
           a1.add(a2.get(j));
   }
}
```
#### BJP5 Exercise 10.18: mirror
```
public static void mirror(ArrayList<String> list){
    for(int i = list.size() - 1; i >= 0; i--){
        list.add(list.get(i));
    }
}
```
#### BJP5 Exercise 10.19: ComparablePoint
```
public int compareTo(Point2D p) {
    double distance1 = Math.sqrt(x * x + y * y);
    double distance2 = Math.sqrt(p.x * p.x + p.y * p.y);
    if (distance1 - distance2 < 0) {
        return -1;
    } else if (distance1 - distance2 > 0) {
        return 1;
    } else {
        return 0;
    }
}
```
#### BJP5 Exercise 10.21: ComparableCalendarDate
```
public int compareTo(CalendarDate date) {
    if (this.year == date.year && this.month == date.month && this.day == date.day) {
        return 0;
    } else if (this.year == date.year && this.month == date.month && this.day > date.day) {
        return 1;
    } else if (this.year == date.year && this.month > date.month) {
        return 1;
    } else if (this.year > date.year) {
        return 1;
    } else {
        return -1;
    }
}
``` 
