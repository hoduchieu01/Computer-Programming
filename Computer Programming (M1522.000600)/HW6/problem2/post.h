//
// Created by hoduc on 12/17/2021.
//

#ifndef PROBLEM2_POST_H
#define PROBLEM2_POST_H

#include <string>
#include <vector>
#include <algorithm>
#include <filesystem>
#include <fstream>
#include "config.h"

class Post{
public:
    std::string title;
    std::vector<std::string> content;
    int id;
    std::string user;
    std::string time;
    Post();
    void write();
    void print(std::ostream& os);
    int occurrences(std::vector<std::string> keyword_list);
    int content_length();
    void print_title(std::ostream& os);
};

#endif //PROBLEM2_POST_H
