#ifndef PROBLEM1_PRODUCT_H
#define PROBLEM1_PRODUCT_H

#include <string>

struct Product {
    Product(std::string name, int price);
    const std::string name;
    int price;
};

#endif //PROBLEM1_PRODUCT_H
