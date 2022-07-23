#ifndef PROBLEM2_APP_H
#define PROBLEM2_APP_H

#include "user.h"
#include "post.h"
#include "data.h"

class App {
public:
    App(std::istream& is, std::ostream& os);
    void run();
    Data* data;
    User* current_user;
    User* login();
    Post* write_post();
private:
    std::istream& is;
    std::ostream& os;
};

#endif //PROBLEM2_APP_H
