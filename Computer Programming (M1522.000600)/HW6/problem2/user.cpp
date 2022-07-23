//
// Created by hoduc on 12/17/2021.
//
#include "user.h"

User::User(std::string id, std::string password): id(id), password(password) {

}

bool User::check_auth(std::string password){
    unsigned int password_size = this->password.size();
    if (password.size() != password_size)
        return false;
    for (unsigned int i = 0; i < password_size; ++i)
        if (tolower(this->password[i]) != tolower(password[i]))
            return false;
    return true;
}
