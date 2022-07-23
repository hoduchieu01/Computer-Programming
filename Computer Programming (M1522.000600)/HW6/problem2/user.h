//
// Created by hoduc on 12/17/2021.
//
#ifndef PROBLEM2_USER_H
#define PROBLEM2_USER_H

#include <string>
#include <vector>

class User{
public:
    std::string id;
    User(std::string id, std::string password);
    bool check_auth(std::string password);
private:
    std::string password;
};

#endif //PROBLEM2_USER_H
