#ifndef PROBLEM1_H
#define PROBLEM1_H

#include <iostream>
#include <string>
#include <cmath>

struct Complex {
    Complex();
    int real, imag;
};

// TODO : 1.1
std::ostream& operator<<(std::ostream& os, const Complex& c);

struct CSI {
    CSI();
    ~CSI();

    int packet_length() const;
    void print(std::ostream& os = std::cout) const;

    Complex ** data;
    int num_packets;
    int num_channel;
    int num_subcarrier;
};

// TODO : 1.2 ~ 1.5
void read_csi(const char* filename, CSI* csi);
double** decode_csi(CSI* csi);
double* get_med(double** decoded_csi, int num_packets, int packet_length);
double breathing_interval(double** decoded_csi, int num_packets);

#endif //PROBLEM1_H
