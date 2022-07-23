#include "TestHelper.h"
#include <algorithm>

namespace TestHelper {
    bool verify(std::string name, std::string lhs, std::string rhs) {
        std::ifstream ifs_lhs(lhs);
        if(ifs_lhs.is_open()) {
            std::ostringstream oss_lhs;
            std::copy(std::istreambuf_iterator<char>(ifs_lhs),
                      std::istreambuf_iterator<char>(),
                      std::ostreambuf_iterator<char>(oss_lhs));
            return verify(name, oss_lhs, rhs);
        } else {
            std::cout << "X" << std::endl;
            return false;
        }
    }

    bool verify(std::string name, std::ostringstream& oss_lhs, std::string rhs) {
        std::ifstream ifs_rhs(rhs);
        if(ifs_rhs.is_open()) {
            std::ostringstream oss_rhs;
            std::copy(std::istreambuf_iterator<char>(ifs_rhs),
                      std::istreambuf_iterator<char>(),
                      std::ostreambuf_iterator<char>(oss_rhs));
            return verify(name, oss_lhs, oss_rhs);
        } else {
            std::cout << "X" << std::endl;
            return false;
        }
    }

    bool verify(std::string name, std::ostringstream& oss_lhs, std::ostringstream& oss_rhs) {
        std::stringstream ss_lhs(oss_lhs.str());
        std::stringstream ss_rhs(oss_rhs.str());
//        std::cout << oss_lhs.str() << " vs " << oss_rhs.str() << std::endl;

        std::string lhs,rhs;
        bool result = ss_lhs.str().length() && ss_rhs.str().length();

        while(std::getline(ss_lhs, lhs)) {
            std::getline(ss_rhs, rhs);
            lhs.erase(std::remove(lhs.begin(), lhs.end(), '\r'), lhs.end());
            rhs.erase(std::remove(rhs.begin(), rhs.end(), '\r'), rhs.end());

            if(lhs != rhs) {
                result = false;
                break;
            }
        };

        if(result) {
            std::cout << "O" << std::endl;
        } else {
            std::cout << "X" << std::endl;
        }

        oss_lhs.str("");
        oss_lhs.clear();
        oss_rhs.str("");
        oss_rhs.clear();
        return result;
    }
}