#include "app.h"
App::App(std::istream& is, std::ostream& os): is(is), os(os) {
    // TODO
    data = new Data();
    data->update_vector_users();
    data->update_vector_posts();
    current_user = nullptr;
}

void App::run() {
    // TODO
    User* user = login();
    if (user == nullptr) {
        os << "Failed Authentication.";
    }else{
        current_user = user;
        while (1){
            std::string choose;
            os << "-----------------------------------\n";
            os << current_user->id << "@sns.com\n"
                  "post : Post contents\n"
                  "recommend <number> : recommend <number> interesting posts\n"
                  "search <keyword> : List post entries whose contents contain <keyword>\n"
                  "exit : Terminate this program\n"
                  "-----------------------------------\n";
            os << "Command=" ;
            is >> choose;
            if (choose == "exit") break;
            else if(choose == "post"){
                Post* post = write_post();
                post->write();
            }
            else if(choose == "recommend"){
                int n;
                is >> n;
                std::vector<Post*> recommend_posts = data->get_recommend(current_user, n);
                for (int i = 0; i < recommend_posts.size(); i++){
                    recommend_posts.at(i) -> print(os);
                    os << std::endl;
                }
            }
            else if(choose == "search"){
                std::vector<std::string> keywords_list;
                std::string search_keywords;

                getline(is, search_keywords);
                search_keywords += " ";
                int space_pos = -1;
                for (int i=0; i<search_keywords.size(); i++){
                    if (search_keywords.at(i) == ' ' || search_keywords.at(i) == '\t'){
                        if (i > space_pos + 1){
                            keywords_list.push_back(search_keywords.substr(space_pos+1, i-space_pos-1));
                        }
                        space_pos = i;
                    }
                }
                std::vector<Post*> searched_posts = data->get_search(keywords_list);
                os << "-----------------------------------" << std::endl;

                for(int i = 0; i < searched_posts.size(); i++){
                    searched_posts.at(i)->print_title(os);
                    os << std::endl;
                }


            }
        }
    }
}

User* App::login(){
    std::string id;
    std::string password;
    os << "------ Authentication ------" << std::endl;
    os << "id=";
    is >> id;
    os << "passwd=";
    is >> password;
    return data->get_user(id, password);

}

Post* App::write_post(){
    os << "-----------------------------------" << std::endl;
    os << "New Post" << std::endl;
    os << "* Title=";
    Post* post = new Post();
    is.ignore();
    std::string title;
    getline(is, title);
    post->title = title;
    os <<"* Content" << std::endl;
    std::string line;
    os << ">";
    std::getline(is, line);
    while (!line.empty()){
        post->content.push_back(line);
//        os << line << "|\n";
        os << ">";
        std::getline(is, line);
    }
    time_t rawtime;
    time (&rawtime);
    char buffer[20];
    std::strftime(buffer, 20, "%Y/%m/%d %H:%M:%S", localtime(&rawtime));
    post->time = std::string(buffer);
    post->user = current_user->id;
    post->id = ++data->post_id_max;
    return post;
}

