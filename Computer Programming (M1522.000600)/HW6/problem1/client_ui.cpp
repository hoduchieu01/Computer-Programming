#include <vector>
#include "client_ui.h"
#include "product.h"
#include "user.h"


ClientUI::ClientUI(ShoppingDB &db, std::ostream& os) : UI(db, os), current_user() { }

void ClientUI::signup(std::string username, std::string password, bool premium) {
    // TODO: For problem 1-2
    db.add_user(username, password, premium);
    os << "CLIENT_UI: " << username << " is signed up." << std::endl;
}

void ClientUI::login(std::string username, std::string password) {
    // TODO: For problem 1-2
    if(current_user != nullptr){
        os << "CLIENT_UI: Please logout first." << std::endl;
    }
    else{
        User* user = db.get_user(username, password);
        if(user != nullptr){
            os << "CLIENT_UI: "<< username << " is logged in." << std::endl;
            current_user = user;
        }
        else{
            os << "CLIENT_UI: Invalid username or password." << std::endl;
        }
    }
}

void ClientUI::logout() {
    // TODO: For problem 1-2
    if(current_user == nullptr){
        os << "CLIENT_UI: There is no logged-in user." << std::endl;
    }
    else{
        os << "CLIENT_UI: " << current_user->name << " is logged out." << std::endl;
        current_user = nullptr;
    }
}

void ClientUI::add_to_cart(std::string product_name) {
    // TODO: For problem 1-2
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }
    std::vector<Product*> products = db.get();
    for(int i = 0; i < products.size(); i++) {
        if (products.at(i)->name == product_name) {
            current_user->user_cart.push_back(products.at(i));
            os << "CLIENT_UI: " << products.at(i)->name << " is added to the cart." << std::endl;
            return;
        }
    }
    os << "CLIENT_UI: Invalid product name." << std::endl;
}

void ClientUI::list_cart_products() {
    // TODO: For problem 1-2.
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }
    if(current_user->user_cart.size() == 0)
        os << "CLIENT_UI: Cart: []" << std::endl;
    else{
        os << "CLIENT_UI: Cart: [";
        for(int i = 0; i < current_user->user_cart.size() - 1; i++){
            int price = current_user->user_cart.at(i)->price;
            if(current_user->premium){
                float premium_price = price * 0.9;
                if(premium_price - (int)(price * 0.9) >= 0.5){
                    price = (int) ((price * 0.9) + 1);
                }
                else {
                    price = (int) (price * 0.9);
                }
            }
            os << "(" << current_user->user_cart.at(i)->name << ", " << price << "), ";
        }
        int price = current_user->user_cart.at(current_user->user_cart.size() - 1) -> price;
        if(current_user->premium){
            float premium_price = price * 0.9;
            if(premium_price - (int)(price * 0.9) >= 0.5){
                price = (int) ((price * 0.9) + 1);
            }
            else {
                price = (int) (price * 0.9);
            }
        }
        os << "(" << current_user->user_cart.at(current_user->user_cart.size() - 1)->name << ", " << price << ")]" << std::endl;
    }

}

void ClientUI::buy_all_in_cart() {
    // TODO: For problem 1-2
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }
    int total_price = 0;
    std::vector<Product*> cart = current_user->user_cart;
    for(int i = 0; i < cart.size(); i ++){
        current_user->purchase_history.push_back(cart.at(i));
        int price = cart.at(i)->price;
        if(current_user->premium){
            float premium_price = price * 0.9;
            if(premium_price - (int)(price * 0.9) >= 0.5){
                price = (int) ((price * 0.9) + 1);
            }
            else {
                price = (int) (price * 0.9);
            }
        }
        total_price += price;
    }
    os << "CLIENT_UI: Cart purchase completed. Total price: " << total_price << "." << std::endl;
    current_user->user_cart.clear();
}

void ClientUI::buy(std::string product_name) {
    // TODO: For problem 1-2
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }
    std::vector<Product*> products = db.get();
    for(int i = 0; i < products.size(); i++){
        if(products.at(i)->name == product_name){
            int price = products.at(i)->price;
            if(current_user->premium){
                float premium_price = price * 0.9;
                if(premium_price - (int)(price * 0.9) >= 0.5){
                    price = (int) ((price * 0.9) + 1);
                }
                else {
                    price = (int) (price * 0.9);
                }
            }
            current_user->add_purchase_history(products.at(i));
            os << "CLIENT_UI: Purchase completed. Price: " << price << "." << std::endl;
            return;
        }
    }
    os << "CLIENT_UI: Invalid product name." << std::endl;
}

void ClientUI::recommend_products() {
    // TODO: For problem 1-3.
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }
    std::vector<Product*> recommend_product;
    if(!current_user->premium){
        std::vector<Product*> purchase_products = current_user->purchase_history;
        for(int i = purchase_products.size() - 1; i >= 0; i--) {
            bool is_add = true;
            for (int j = 0; j < recommend_product.size(); j++) {
                if (purchase_products.at(i) == recommend_product.at(j)) {
                    is_add = false;
                }
            }
            if (is_add == true) {
                recommend_product.push_back(purchase_products.at(i));
            }
            if (recommend_product.size() == 3) break;
        }
        // print recommend products
        if(recommend_product.size() == 0)
            os << "CLIENT_UI: Recommended products: []" << std::endl;
        else{
            os << "CLIENT_UI: Recommended products: [";
            for(int i = 0; i < recommend_product.size() - 1; i++){
                os << "(" << recommend_product.at(i)->name << ", " << recommend_product.at(i)->price << "), ";
            }
            os << "(" << recommend_product.at(recommend_product.size() - 1)->name << ", " << recommend_product.at(recommend_product.size() - 1)->price << ")]" << std::endl;
        }
    }
    else{
        recommend_product = db.get_product_recommend_premium(current_user);
        // print recommend products
        for(int i = 0; i < recommend_product.size(); i++) {
                float premium_price = recommend_product.at(i)->price * 0.9;
                if (premium_price - (int) (recommend_product.at(i)->price * 0.9) >= 0.5) {
                    recommend_product.at(i)->price = (int) ((recommend_product.at(i)->price * 0.9) + 1);
                } else {
                    recommend_product.at(i)->price = (int) (recommend_product.at(i)->price * 0.9);
                }
        }
        if(recommend_product.size() == 0)
            os << "CLIENT_UI: Recommended products: []" << std::endl;
        else{
            os << "CLIENT_UI: Recommended products: [";
            for(int i = 0; i < recommend_product.size() - 1; i++){
                os << "(" << recommend_product.at(i)->name << ", " << recommend_product.at(i)->price << "), ";
            }
            os << "(" << recommend_product.at(recommend_product.size() - 1)->name << ", " << recommend_product.at(recommend_product.size() - 1)->price << ")]" << std::endl;
        }
    }
}


