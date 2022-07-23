#ifndef PROBLEM1_CLIENT_UI_H
#define PROBLEM1_CLIENT_UI_H

#include <string>
#include "ui.h"
#include "shopping_db.h"

class ClientUI : public UI {
    public:
        ClientUI(ShoppingDB &db, std::ostream& os);
        void signup(std::string username, std::string password, bool premium);
        void login(std::string username, std::string password);
        void logout();
        void add_to_cart(std::string product_name);
        void list_cart_products();
        void buy(std::string product_name);
        void buy_all_in_cart();
        void recommend_products();
    private:
        User* current_user;
};

#endif //PROBLEM1_CLIENT_UI_H
