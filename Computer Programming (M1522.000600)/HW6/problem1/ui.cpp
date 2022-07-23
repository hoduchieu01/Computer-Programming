#include "ui.h"

UI::UI(ShoppingDB &db, std::ostream& os): db(db), os(os) {

}

std::ostream & UI::get_os() const {
    return os;
}
