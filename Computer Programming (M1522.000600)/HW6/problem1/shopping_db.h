#ifndef PROBLEM1_SHOPPING_DB_H
#define PROBLEM1_SHOPPING_DB_H

#include <string>
#include <vector>
#include "user.h"
#include "product.h"

class ShoppingDB {
public:
    ShoppingDB();
    void add_product(std::string name, int price);
    bool edit_product(std::string name, int price);
    std::vector<Product*> get();
    void add_user(std::string username, std::string password, bool premium);
    User* get_user(std::string username, std::string password);
    std::vector<Product*> get_product_recommend_premium(User* user);
private:
    std::vector<User*> users;
    std::vector<Product*> products;
};

#endif //PROBLEM1_SHOPPING_DB_H
