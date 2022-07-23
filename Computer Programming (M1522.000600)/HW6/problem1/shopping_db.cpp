#include "shopping_db.h"
#include <queue>

ShoppingDB::ShoppingDB() {

}

void ShoppingDB::add_product(std::string name, int price){
    products.push_back(new Product(name, price));
}

bool ShoppingDB::edit_product(std::string name, int price){
    if(price <= 0)
        return false;
    int pos = -1;
    for(int i = 0; i < products.size(); i++){
        if(products.at(i)->name == name){
            pos = i;
            break;
        }
    }
    if(pos == -1)
        return false;
    else{
        products.at(pos)->price = price;
        return true;
    }
}

std::vector<Product*> ShoppingDB::get(){
    return products;
}

// problem 1-2
void ShoppingDB::add_user(std::string username, std::string password, bool premium){
    if(premium){
        users.push_back(new PremiumUser(username, password));
    }
    else{
        users.push_back(new NormalUser(username, password));
    }
}

User* ShoppingDB::get_user(std::string username, std::string password){
    for(int i = 0; i < users.size(); i++){
        if(users.at(i)->name == username){
            if(users.at(i)->get_password() == password){
                return users.at(i);
            }
            else{
                return nullptr;
            }
        }
    }
    return nullptr;
}


// problem 1.3
struct compare{
    bool operator()(std::pair<int, int> &a, std::pair<int, int> &b) {
        return (a.first == b.first) ? (a.second > b.second) : (a.first < b.first);
    };
};

std::vector<Product*> ShoppingDB::get_product_recommend_premium(User* user){
    std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>, compare> pq;
    for(int i = 0; i < users.size(); i++)
        if (user != users.at(i)){
            pq.push(std::pair<int , int>(user->getSimilarity(users.at(i)), i));
        }
    std::vector<Product*> recommend_product;
    while (!pq.empty()){
        User* u = users.at(pq.top().second);
        pq.pop();
        if (!u->purchase_history.empty()){
            Product* product = u->purchase_history.at(u->purchase_history.size()-1);
            bool is_add = true;
            for (int j = 0; j < recommend_product.size(); j++) {
                if (product == recommend_product.at(j)) {
                    is_add = false;
                }
            }
            if (is_add == true) {
                recommend_product.push_back(product);
            }
            if (recommend_product.size() == 3) break;
        }
    }
    return recommend_product;
}

