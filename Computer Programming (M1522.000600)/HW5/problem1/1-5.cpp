
bool bibimbap_change(int* bills, int N) {
    // TODO: problem 1.5
    // bills[i] is either 5, 10, 25
    int fiveBills = 0;
    int tenBills = 0;
    for(int i = 0; i < N; i++){
        if(bills[i] == 5) fiveBills ++;
        else if(bills[i] == 10){
            if(fiveBills == 0) return false;
            tenBills ++;
            fiveBills --;
        }
        else{
            if(fiveBills > 0 && tenBills > 0){
                tenBills --;
                fiveBills --;
            }
            else if(fiveBills >= 3){
                fiveBills -= 3;
            }
            else{
                return false;
            }
        }
    }
    return true;
}

