//
// Created by hoduc on 12/17/2021.
//
#include "data.h"
#include <queue>

void Data::update_vector_users(){
    for (const auto &entry : std::filesystem::directory_iterator(SERVER_STORAGE_DIR)) {
        std::string id = entry.path().filename().string();
        std::string path = entry.path().string().append("/password.txt");
        std::string password;
        if (!std::filesystem::exists(path)) continue;
        std::ifstream ifs(path);
        std::getline(ifs, password);
        ifs.close();
        users.push_back(new User(id, password));
    }
};

User* Data::get_user(std::string id, std::string password){
    for (int i = 0; i < users.size(); i++){
        if (id == users.at(i)->id){
            if (users.at(i)->check_auth(password))
                return users.at(i);
            else return nullptr;
        }
    }
    return nullptr;
};

void Data::update_vector_posts(){
    post_id_max = -1;

    for (auto &entry : std::filesystem::directory_iterator(SERVER_STORAGE_DIR)) {
        std::string user = entry.path().filename().string();
        std::filesystem::path postDir = std::filesystem::path(entry.path().string() + "/post/");
        if (!std::filesystem::exists(postDir)) continue;
        for (auto &entry : std::filesystem::directory_iterator(postDir.string())){
            Post* post = new Post();
            post->id = std::stoi(entry.path().stem().string());
            post->user = user;
            std::ifstream ifs(entry.path().string());
            getline(ifs, post->time);
            getline(ifs, post->title);
            std::string content_line;
            getline(ifs, content_line);
            while (getline(ifs, content_line)){
                post->content.push_back(content_line);
            }
            post_id_max = (post_id_max < post->id) ? post->id : post_id_max;
            posts.push_back(post);
            ifs.close();
        }
    }
}

// problem 3
struct post_comparator{
    bool operator()(Post* &a, Post* &b) {
        return a->time < b->time;
    };
};

std::vector<Post*> Data::get_recommend(User* user, int n){
    std::priority_queue<Post*, std::vector<Post*>, post_comparator> recommend_posts;
    std::vector<std::string> list_friend = get_friends(user->id);
    for (std::string friend_name : list_friend){
        std::vector<Post*> friend_posts = get_posts(friend_name);
        for (int i = 0; i < friend_posts.size(); i++){
            recommend_posts.push(friend_posts.at(i));
        }
    }
    std::vector<Post*> recommend_n_posts;
    while (n>0){
        n--;
        if (recommend_posts.empty()) break;
        recommend_n_posts.push_back(recommend_posts.top());
        recommend_posts.pop();
    }
    return recommend_n_posts;
}

std::vector<Post*> Data::get_posts(std::string username){
    std::vector<Post*> user_posts;
    for (int i = 0; i < posts.size(); i++){
        if (posts.at(i)->user == username) user_posts.push_back(posts.at(i));
    }
    return user_posts;
}

std::vector<std::string> Data::get_friends(std::string username){
    std::vector<std::string> list_friends;
    std::string path = SERVER_STORAGE_DIR + username + "/friend.txt";
    if (std::filesystem::exists(path)){
        std::string friend_name;
        std::ifstream ifs(path);
        while (getline(ifs, friend_name)){
            list_friends.push_back(friend_name);
        }
        ifs.close();
    }
    return list_friends;
}

struct search_comparator{
    bool operator()(std::pair<int, Post*> a, std::pair<int, Post*> b) {
        return (a.first == b.first) ? (a.second->content_length() < b.second->content_length()) : (a.first < b.first);
    };
};

std::vector<Post*> Data::get_search(std::vector<std::string> keyword_list){
    std::priority_queue<std::pair<int, Post*>, std::vector<std::pair<int, Post*>>, search_comparator> searched_posts;

    for (Post*post : posts){
        searched_posts.push(std::pair<int, Post*>(post->occurrences(keyword_list), post));
    }
    std::vector<Post*> searched_10_posts;
    int count = 0;
    while (count < 10){
        count++;
        if (searched_posts.empty()) break;
        searched_10_posts.push_back(searched_posts.top().second);
        searched_posts.pop();
    }
    return searched_10_posts;
}