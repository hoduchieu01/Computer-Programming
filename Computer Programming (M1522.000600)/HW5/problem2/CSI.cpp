#include "CSI.h"
#include <sstream>
#include <fstream>
Complex::Complex(): real(0), imag(0) {}

CSI::CSI(): data(nullptr), num_packets(0), num_channel(0), num_subcarrier(0) {}

CSI::~CSI() {
    if(data) {
        for(int i = 0 ; i < num_packets; i++) {
            delete[] data[i];
        }
        delete[] data;
    }
}

int CSI::packet_length() const {
    return num_channel * num_subcarrier;
}

void CSI::print(std::ostream& os) const {
    for (int i = 0; i < num_packets; i++) {
        for (int j = 0; j < packet_length(); j++) {
            os << data[i][j] << ' ';
        }
        os << std::endl;
    }
}

std::ostream& operator<<(std::ostream &os, const Complex &c) {
    // TODO: problem 2.1
    // Printing Complex Values
    // Implement to print the object of the custom struct Complex in a designated format.
    // The output should be a + bi if the imaginary part is greater or equal to 0, and a-bi otherwise.
    if(c.imag >= 0){
        return os << c.real << "+" << c.imag << "i";
    }
    else{
        return os << c.real << "-" << -c.imag << "i";
    }
}

void read_csi(const char* filename, CSI* csi) {
    // TODO: problem 2.2
    // read the input file containing raw CSI values and updates CSI object based on a description below
    // csi pointer is valid
    // first three lines - number of packets delivered, number of channels, and subcarriers
    // csi object passed as the parameter has a member attribute, data to store a 2D  array of Complex objects
    // csi -> data[num_packets][num_channels * num_subcarriers]
    int line_index = 0;
    std::string str;
    std::ifstream my_file(filename);
    while (getline(my_file, str)) {
        int value = std::stoi(str);
        if (line_index == 0)
            csi->num_packets = value;
        else if(line_index == 1)
            csi->num_channel = value;
        else if(line_index == 2)
            csi->num_subcarrier = value;
        else if(line_index == 3) {
            csi->data = new Complex *[csi->num_packets];
            for (int i = 0; i < csi->num_packets; i++) {
                csi->data[i] = new Complex[csi->packet_length()];
            }
        }
        if(line_index >= 3){
            getline(my_file, str);
            int value2 = std::stoi(str);
            Complex complex;
            complex.real = value;
            complex.imag = value2;
            int channel_index = (line_index-3) % csi->num_channel;
            int sc_index = ((line_index-3) / csi->num_channel) % csi->num_subcarrier;
            int packets_index = ((line_index-3) / csi->num_channel) / csi->num_subcarrier;
            //std::cout<< packets_index << " " << sc_index << " " << channel_index << std::endl;
            csi->data[packets_index][(csi->num_subcarrier) * channel_index + sc_index] = complex;
        }
        line_index ++;
    }
    my_file.close();
}

double** decode_csi(CSI* csi) {
    // TODO: problem 2.3
    // It returns amplitude values that are converted from the provided csi object.
    double** amplitude_values = new double*[csi->num_packets];
    for(int i = 0; i < csi->num_packets; i++){
        amplitude_values[i] = new double[csi->packet_length()];
        for(int j = 0; j < csi->packet_length(); j++){
            int x = csi->data[i][j].real;
            int y = csi->data[i][j].imag;
            amplitude_values[i][j] = (double) sqrt((double)x * x + (double)y * y);
        }
    }
    return amplitude_values;
}

double* get_med(double** decoded_csi, int num_packets, int packet_length) {
    // TODO: problem 2.4
    double* median = new double[num_packets];
    for(int i = 0; i < num_packets; i++){
        double* row = new double[packet_length];
        for(int j = 0; j < packet_length; j++){
            row[j] = decoded_csi[i][j];
        }
        // sort
        for(int k = 0; k < packet_length; k++){
            for(int l = k + 1; l < packet_length; l++){
                if(row[k] > row[l]){
                    double tmp = row[k];
                    row[k] = row[l];
                    row[l] = tmp;
                }
            }
        }
        if(packet_length % 2 == 0){
            median[i] = (double) (row[packet_length/2] + row[packet_length/2 - 1]) / 2.0;
        }
        else{
            median[i] = (double) row[packet_length/2];
        }
    }
    return median;
}

double breathing_interval(double** decoded_csi, int num_packets) {
    // TODO: problem 2.5
    int peak = -1;
    double sum_interval = 0;
    int count = 0;
    for(int i = 0; i < num_packets; i++) {
        //  std::cout << decoded_csi[i][0] << " ";
        int l = (i>2) ? (i-2) : 0;
        int r = (i<num_packets - 3) ? (i+2) : num_packets - 1;
        // std::cout << l << " " << r << std::endl;
        bool isPeak = true;
        for(int j = l; j <= r; j++){
            if(j != i){
                if(decoded_csi[i][0] <= decoded_csi[j][0] + 1e-10){
                    isPeak = false;
                }
            }
        }
        if (isPeak){
            if (peak != -1){
                sum_interval += i - peak;
            }
            peak = i;
            count++;
        }
    }
    if(count == 0 || count == 1) return num_packets;
    return (double) sum_interval / (count - 1);
}