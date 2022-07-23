
int* pascal_triangle(int N) {
    // TODO: problem 1.4
    int *ans = new int [25];
    for(int i = 0; i < N; i++) {
        ans[i] = 1;
    }
    for(int i = 1; i < N - 1; i++){
        for(int j = i; j > 0; j --){
            ans[j] += ans[j-1];
        }
    }
    return ans;
}