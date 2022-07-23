#ifndef PROBLEM1_UI_H
#define PROBLEM1_UI_H

#include <sstream>
#include <iostream>
#include "shopping_db.h"

class UI {
public:
    UI(ShoppingDB &db, std::ostream& os);
    std::ostream& get_os() const;
protected:
    std::ostream& os;
    ShoppingDB &db;
};

#endif //PROBLEM1_UI_H
