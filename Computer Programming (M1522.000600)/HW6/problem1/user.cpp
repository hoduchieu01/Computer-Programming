#include "user.h"

User::User(std::string name, std::string password): name(name), password(password) {

}

std::string User::get_password() {
    return password;
}

void User::add_purchase_history(Product* product){
    purchase_history.push_back(product);
}

int User::getSimilarity(User* user){
    int purchase_similarity = 0;
    for(int i = 0; i < user->purchase_history.size(); i++){
        for(int j = 0; j < purchase_history.size(); j++){
            if(user->purchase_history.at(i)->name == purchase_history.at(j)->name){
                purchase_similarity ++;
                break;
            }
        }
    }
    return purchase_similarity;
}

NormalUser::NormalUser(std::string name, std::string password): User(name, password)  {
    premium = false;
}

PremiumUser::PremiumUser(std::string name, std::string password): User(name, password) {
    premium = true;
}