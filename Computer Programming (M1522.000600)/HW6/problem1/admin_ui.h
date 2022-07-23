#ifndef PROBLEM1_ADMIN_UI_H
#define PROBLEM1_ADMIN_UI_H

#include <string>
#include <iostream>
#include "ui.h"
#include "shopping_db.h"

class AdminUI : public UI {
public:
    AdminUI(ShoppingDB &db, std::ostream& os);
    void add_product(std::string name, int price);
    void edit_product(std::string name, int price);
    void list_products();
};

#endif //PROBLEM1_ADMIN_UI_H
