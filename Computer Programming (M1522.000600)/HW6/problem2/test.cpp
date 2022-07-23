#include <iostream>
#include <sstream>
#include <string>
#include <filesystem>
#include <fstream>
#include <algorithm>
#include "app.h"

namespace fs = std::filesystem;

void print_OX(std::string prompt, bool condition) {
    std::cout << prompt << " : " << (condition ? "O" : "X") << std::endl;
}

bool is_space(char ch) {
    return ch == ' ' || ch == '\n' || ch == '\r';
}

void test(std::string test_name, fs::path input, fs::path output) {
    std::ifstream ifs(input);

    std::string line;

    std::ostringstream oss;

    App app(ifs, oss);
    app.run();

    std::string output_app = oss.str();

    std::ifstream ifs_answer(output);
    std::string output_answer((std::istreambuf_iterator<char>(ifs_answer)), (std::istreambuf_iterator<char>()));

    output_app.erase(std::remove_if(output_app.begin(), output_app.end(), is_space), output_app.end());
    output_answer.erase(std::remove_if(output_answer.begin(), output_answer.end(), is_space), output_answer.end());

    bool is_correct = output_app == output_answer;
    print_OX(test_name, is_correct);
}

int main() {
    test("Test 1 - Login O", "test/1_1.in", "test/1_1.out");
    test("Test 1 - Login X", "test/1_2.in", "test/1_2.out");
    test("Test 2", "test/2_post.in", "test/2.out");
    test("Test 3", "test/3_post.in", "test/3.out");
    test("Test 4", "test/4_post.in", "test/4.out");
}
