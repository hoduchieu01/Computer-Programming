#include <iostream>
#include <iterator>
#include <vector>
#include "header.h"

template <typename T>
void check_array(const T* a, const std::vector<T> b, int N) {
    if (!a || b.size() != N) {
        std::cout << "Failed" << std::endl;
        return;
    }
    for (std::size_t i = 0; i < N; ++i) {
        if (a[i] != b.at(i)) {
            std::cout << "Failed" << std::endl;
            return;
        }
    }
    std::cout << "Passed" << std::endl;
}

template <typename T>
void check_value(const T a, const T b) {
    if (a == b) {
        std::cout << "Passed" << std::endl;
    } else {
        std::cout << "Failed" << std::endl;
    }
}

int main() {
    // Test more with your own test cases

    // 1.1
    std::cout << "1-1" << std::endl;
    check_value(is_palindrome("aabb"), false);

    // 1.2
    std::cout << "1-2" << std::endl;
    check_value(hamming_distance(1,4), 2);

    // 1.3
    std::cout << "1-3" << std::endl;
    int arr1[] ={1,3,5,0,0};
    int arr2[] = {3,5};
    merge_arrays(arr1, 3, arr2, 2);
    check_array(arr1, {1,3,3,5,5}, sizeof(arr1)/sizeof(int));

    // 1.4
    std::cout << "1-4" << std::endl;
    int* res = pascal_triangle(4);
    check_array(res, {1,3,3,1}, 4);
    delete[] res;


    // 1.5
    std::cout << "1-5" << std::endl;
    int bills[] = {5,5,5,10,20};
    check_value(bibimbap_change(bills, sizeof(bills)/sizeof(int)), true);
    return 0;
}
