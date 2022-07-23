//
// Created by hoduc on 12/17/2021.
//

#ifndef PROBLEM2_DATA_H
#define PROBLEM2_DATA_H

#include <string>
#include <vector>
#include <filesystem>
#include <fstream>
#include "user.h"
#include "post.h"
#include "config.h"

class Data{
public:
    std::vector<User*> users;
    std::vector<Post*> posts;
    void update_vector_users();
    User* get_user(std::string id, std::string password);
    void update_vector_posts();
    int post_id_max;
    std::vector<Post*> get_posts(std::string username);
    std::vector<std::string> get_friends(std::string username);
    std::vector<Post*> get_recommend(User* user, int n);
    std::vector<Post*> get_search(std::vector<std::string> keyword_list);
};
#endif //PROBLEM2_DATA_H
