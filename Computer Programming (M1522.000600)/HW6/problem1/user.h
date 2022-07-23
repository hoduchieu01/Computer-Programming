#ifndef PROBLEM1_USER_H
#define PROBLEM1_USER_H

#include <string>
#include <vector>
#include "product.h"

class User {
public:
    bool premium;
    User(std::string name, std::string password);
    const std::string name;
    std::string get_password();
    std::vector<Product*> purchase_history;
    void add_purchase_history(Product* product);
    std::vector<Product*> user_cart;
    int getSimilarity(User* user);
private:
    std::string password;
};

class NormalUser : public User {
public:
    NormalUser(std::string name, std::string password);
};

class PremiumUser : public User {
public:
    PremiumUser(std::string name, std::string password);
};

#endif //PROBLEM1_USER_H
