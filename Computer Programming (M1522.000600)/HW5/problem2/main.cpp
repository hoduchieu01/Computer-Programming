#include "CSI.h"
#include "TestHelper.h"
#include <cassert>
#include <iomanip>

void print_amp(double** csi_amp, int num_packets, int num_array, std::ostream& os) {
    for (int i = 0; i < num_packets; i++) {
        for (int j = 0; j < num_array; j++) {
            os << csi_amp[i][j] << ' ';
        }
        os << std::endl;
    }
}

void print_med(double* med_arr, int num_packets, std::ostream& os) {
    for (int i = 0; i < num_packets; i++) {
        os  << med_arr[i] << ' ';
    }
    os << std::endl;
}

int main(int argc, char** argv) {
    /* Main code for problem 1
    Implement TODOs in CSI.cpp
    Use print_cout flag to print & test the implementation
    If print_cout is true, main code print results to console.
    Otherwise, verify implementation with outputs in test/ folder.
    */
    bool print_cout = false;
    std::ostringstream oss_lhs;
    std::ostream& os_lhs = print_cout ? std::cout : oss_lhs;
    os_lhs << std::setprecision(3) << std::fixed; // only compare to 3 decimal points

    // test code 2.1
    Complex c;
    os_lhs << c << std::endl;
    if(!print_cout)
        TestHelper::verify("2-1",oss_lhs, "test/test1.out");
    oss_lhs.str("");
    oss_lhs.clear();

    // test code 2.2
    CSI* csi = new CSI;
    read_csi("test/test.in", csi);
    csi->print(os_lhs);
    if(!print_cout)
        TestHelper::verify("2-2",oss_lhs, "test/test2.out");
    oss_lhs.str("");
    oss_lhs.clear();

    // test code 2.3
    double** csi_amp = decode_csi(csi);
    print_amp(csi_amp, csi->num_packets, csi->packet_length(), os_lhs);
    if(!print_cout)
        TestHelper::verify("2-3",oss_lhs, "test/test3.out");
    oss_lhs.str("");
    oss_lhs.clear();

    // test code 2.4
    double* med_arr = get_med(csi_amp, csi->num_packets, csi->packet_length());
    print_med(med_arr, csi->num_packets, os_lhs);
    if(!print_cout)
        TestHelper::verify("2-4",oss_lhs, "test/test4.out");
    oss_lhs.str("");
    oss_lhs.clear();

    // test code 2.5
    double interval = breathing_interval(csi_amp, csi->num_packets);
    os_lhs << interval << std::endl;
    if(!print_cout)
        TestHelper::verify("2-5",oss_lhs, "test/test5.out");
    oss_lhs.str("");
    oss_lhs.clear();


    // clean-up memory
    for(int i = 0; i < csi->num_packets; i++) {
        if(csi_amp[i])
            delete [] csi_amp[i];
    }

    if(csi_amp)
        delete[] csi_amp;
    if(med_arr)
        delete[] med_arr;
    if(csi)
        delete csi;

    return 0;
}