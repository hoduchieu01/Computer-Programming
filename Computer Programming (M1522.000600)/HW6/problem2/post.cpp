//
// Created by hoduc on 12/17/2021.
//

#include "post.h"
Post::Post(){

}

void Post::write(){
    std::string path = SERVER_STORAGE_DIR;
    std::ofstream ofs(path + user + "/post/" + std::to_string(id) + ".txt");
    ofs << time << std::endl;
    ofs << title << std::endl;
    ofs << std::endl;
    int count = 0;
    for (std::string& line:content){
        ofs << line;
        if (count != content.size()-1) ofs << std::endl;
        count++;
    }
    ofs.close();
}

void Post::print(std::ostream& os){
    os << "-----------------------------------" << std::endl;
    os << "id: " << id << std::endl;
    os << "created at: " << time << std::endl;
    os << "title: " << title << std::endl;
    os << "content:\n";
    int count = 0;
    for (int i = 0; i < content.size(); i++){
        os << content.at(i);
        if (count != content.size()-1) os << std::endl;
        count++;
    }
}


int Post::occurrences(std::vector<std::string> keyword_list){
    std::string post_content = title + " ";
    for(int i = 0; i < content.size(); i++){
        post_content += content.at(i) + " ";
    }
    std::vector<std::string> word_list;
    post_content += " ";
    int space_pos = -1;
    for (int i=0; i<post_content.size(); i++){
        if (post_content.at(i) == ' ' || post_content.at(i) == '\t' || post_content.at(i) == '\n'){
            if (i > space_pos + 1){
                word_list.push_back(post_content.substr(space_pos+1, i-space_pos-1));
            }
            space_pos = i;
        }
    }
    int count = 0;
    for (std::string word : word_list){
        for (std::string keyword : keyword_list){
            if (word == keyword) {
                count++; break;
            }
        }
    }
    return count;
}

int get_length(std::string line){
    int space_pos = -1, count = 0;
    line += " ";
    for (int i=0; i<line.size(); i++){
        if (line.at(i) == ' ' || line.at(i) == '\t' || line.at(i) == '\n'){
            if (i > space_pos + 1){
                count++;
            }
            space_pos = i;
        }
    }
    return count;
}

int Post::content_length() {
    int result = 0;
    for (std::string content_line : content){
        result += get_length(content_line);
    }
    return result;
}

void Post::print_title(std::ostream &os) {
    os << "id: " << id << ", ";
    os << "created at: " << time << ", ";
    os << "title: " << title;
}

