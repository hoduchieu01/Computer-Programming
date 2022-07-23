#include <string>

bool is_palindrome(std::string s) {
    // TODO: problem 1.1
    int left = 0;
    int right = s.size() - 1;
    while (right > left){
        if(s[left++] != s[right--])
            return false;
    }
    return true;
}