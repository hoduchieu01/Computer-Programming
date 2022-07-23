
void merge_arrays(int* arr1, int len1, int* arr2, int len2) {
    // TODO: problem 1.3
    // Implement using three pointers 
    int first_pointer = len1 - 1;
    int second_pointer = len2 - 1;
    for(int pointer = len1 + len2 - 1; pointer >= 0; pointer --){
        if(second_pointer < 0)
            break;
        if(first_pointer >= 0 && arr1[first_pointer] > arr2[second_pointer]){
            arr1[pointer] = arr1[first_pointer--];
        }
        else{
            arr1[pointer] = arr2[second_pointer--];
        }
    }
}

