
int hamming_distance(int x, int y) {
    // TODO: problem 1.2
    int distance = 0;
    int x_xor_y = x ^ y;
    while (x_xor_y != 0){
        if(x_xor_y % 2 == 1) distance ++;
        x_xor_y = x_xor_y >> 1;
    }
    return distance;
}

