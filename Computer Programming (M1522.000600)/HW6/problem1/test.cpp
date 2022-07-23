#include <iostream>
#include <sstream>
#include <fstream>
#include <algorithm>
#include "shopping_db.h"
#include "admin_ui.h"
#include "client_ui.h"

#define TEST_DIRPATH "test/"

void print_OX(std::string test_name, bool is_correct) {
    std::cout << test_name << " : " << (is_correct ? "O" : "X") << std::endl;
}

bool is_space(char ch) {
    return ch == ' ' || ch == '\n' || ch == '\r';
}

void remove_space(std::string& str) {
    str.erase(std::remove_if(str.begin(), str.end(), is_space), str.end());
}

void clear_ostream_as_ostringstream(std::ostream& os) {
    std::ostringstream& oss = dynamic_cast<std::ostringstream&>(os);
    oss.str("");
    oss.clear();
}

bool compare_output(UI& ui, std::string out_filename) {
    std::ifstream ifs(TEST_DIRPATH + out_filename);
    std::ostringstream oss_answer;
    oss_answer << ifs.rdbuf();
    std::string output_answer = oss_answer.str();

    std::ostringstream& oss = dynamic_cast<std::ostringstream&>(ui.get_os());
    std::string output_app = oss.str();

    remove_space(output_answer);
    remove_space(output_app);

    return output_app == output_answer;
}

void test1(AdminUI& admin_ui) {
#if MAIN
    std::cout << std::endl << "========== Test 1 ==========" << std::endl;
#endif
#if TEST
    clear_ostream_as_ostringstream(admin_ui.get_os());
#endif

    admin_ui.add_product("tissue", 2000);
    admin_ui.add_product("chair", 20000);
    admin_ui.add_product("desk", 50000);
    admin_ui.list_products();
    admin_ui.edit_product("tissue", 3000);
    admin_ui.list_products();

#if TEST
    bool is_correct = compare_output(admin_ui, "1.out");
    print_OX("Test 1", is_correct);
#endif
}

void test2(ClientUI& client_ui) {
#if MAIN
    std::cout << std::endl << "========== Test 2 ==========" << std::endl;
#endif
#if TEST
    clear_ostream_as_ostringstream(client_ui.get_os());
#endif

    client_ui.signup("Youngki", "hcslab", true);
    client_ui.signup("Doil", "csi", false);
    client_ui.login("Youngki", "abc");
    client_ui.login("Youngki", "hcslab");
    client_ui.add_to_cart("tissue");
    client_ui.add_to_cart("chair");
    client_ui.add_to_cart("desk");
    client_ui.list_cart_products();
    client_ui.buy_all_in_cart();
    client_ui.login("Doil", "csi");
    client_ui.logout();
    client_ui.add_to_cart("chair");
    client_ui.login("Doil", "csi");
    client_ui.buy("chair");
    client_ui.logout();
    client_ui.logout();

#if TEST
    bool is_correct = compare_output(client_ui, "2.out");
    print_OX("Test 2", is_correct);
#endif
}

void test3(ClientUI& client_ui) {
#if MAIN
    std::cout << std::endl << "========== Test 3 ==========" << std::endl;
#endif
#if TEST
    clear_ostream_as_ostringstream(client_ui.get_os());
#endif

    client_ui.signup("HyunA", "give", false);
    client_ui.signup("Kichang", "me", false);
    client_ui.signup("Hyunwoo", "jonggang", false);

    client_ui.login("HyunA", "give");
    client_ui.buy("chair");
    client_ui.buy("chair");
    client_ui.buy("chair");
    client_ui.buy("desk");
    client_ui.logout();

    client_ui.login("Hyunwoo", "jonggang");
    client_ui.buy("chair");
    client_ui.buy("tissue");
    client_ui.recommend_products();
    client_ui.logout();
    
    client_ui.login("Youngki", "hcslab");
    client_ui.recommend_products();
    client_ui.logout();

#if TEST
    bool is_correct = compare_output(client_ui, "3.out");
    print_OX("Test 3", is_correct);
#endif
}

int main() {
#if MAIN
    std::ostream& os = std::cout;
#endif
#if TEST
    std::ostringstream os;
#endif
    ShoppingDB db;
    AdminUI admin_ui(db, os);
    ClientUI client_ui(db, os);
    test1(admin_ui);
    test2(client_ui);
    test3(client_ui);
}
