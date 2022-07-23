#include "admin_ui.h"

AdminUI::AdminUI(ShoppingDB &db, std::ostream& os): UI(db, os) { }

void AdminUI::add_product(std::string name, int price) {
    // TODO: For problem 1-1
    if(price <= 0){
        os << "ADMIN_UI: Invalid price." << std::endl;
    }
    else{
        db.add_product(name, price);
        os << "ADMIN_UI: " + name + " is added to the database." << std::endl;
    }
}

void AdminUI::edit_product(std::string name, int price) {
    // TODO: For problem 1-1
    if(price <= 0){
        os << "ADMIN_UI: Invalid price." << std::endl;
    }
    else if(db.edit_product(name, price) == false){
        os << "ADMIN_UI: Invalid product name." << std::endl;
    }
    else{
        db.edit_product(name, price);
        os << "ADMIN_UI: " + name + " is modified from the database." << std::endl;
    }
}

void AdminUI::list_products() {
    // TODO: For problem 1-1
    std::vector<Product*> products = db.get();
    if(products.size() == 0)
        os << "ADMIN_UI: Products: []" << std::endl;
    else{
        os << "ADMIN_UI: Products: [";
        for(int i = 0; i < products.size() - 1; i++){
            os << "(" << products.at(i)->name << ", " << products.at(i)->price << "), ";
        }
        os << "(" << products.at(products.size() - 1)->name << ", " << products.at(products.size() - 1)->price << ")]" << std::endl;
    }
}