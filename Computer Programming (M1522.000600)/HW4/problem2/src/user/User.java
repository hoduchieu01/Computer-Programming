package user;

import course.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class User {

    private static String userDirectoryPath = "data/Users/";
    private String studentId;
    private int totalMileageSpent;
    public List<Bidding> biddingList;
    public List<Course> registeredCourses;

    public String studentId() {
        return studentId;
    }

    public List<Bidding> biddingList() {
        return biddingList;
    }

    public int totalMileageSpent() {
        return totalMileageSpent;
    }

    public int sumOfBidMileage(int courseId, int mileage) {
        int sum = 0;
        for (Bidding bidding:biddingList){
            if (bidding.courseId != courseId) sum += bidding.mileage;
        }
        sum += mileage;
        return sum;
    }

    public User(String studentId) throws FileNotFoundException {
        this.studentId = studentId;
        this.totalMileageSpent = 0;
        this.biddingList = retrieveBids();
        this.registeredCourses = new ArrayList<>();
    }

    public void placingBid(int courseId, int mileage) throws FileNotFoundException, IOException {
        biddingList = retrieveBids();
        List<Bidding> afterBidding = new ArrayList<>();
        for (Bidding bidding : biddingList) {
            if (bidding.courseId != courseId) afterBidding.add(bidding);
        }
        if (mileage != 0) afterBidding.add(new Bidding(courseId, mileage));
        biddingList = afterBidding;
        writeUpdatedBids();
    }

    public List<Bidding> retrieveBids() throws FileNotFoundException {
        List<Bidding> biddingList = new ArrayList<>();
        File bidPath = new File(userDirectoryPath + studentId + "/bid.txt");
        Scanner scanner = new Scanner(bidPath);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] info = line.split("\\|");
            int courseId = Integer.parseInt(info[0]);
            int mileage = Integer.parseInt(info[1]);
            totalMileageSpent += mileage;
            biddingList.add(new Bidding(courseId, mileage));
        }
        scanner.close();
        return biddingList;
    }

    private void writeUpdatedBids() throws IOException {
        FileWriter fileWriter = new FileWriter(userDirectoryPath + studentId + "/bid.txt", false);
        if (biddingList.size() != 0){
            for (Bidding bidding : biddingList) {
                String updatedBid = bidding.courseId + "|" + bidding.mileage;
                if (!biddingList.get(biddingList.size()-1).equals(bidding)) updatedBid += "\n";
                fileWriter.write(updatedBid);
            }
        }
        fileWriter.close();
    }

    public void updateRegistrationStatus(Course course, boolean success) throws IOException{
        File file = new File(userDirectoryPath + studentId + "/registration.txt");
        retrieveBids();
        if (file.isFile()) retrieveRegisteredCourse();
        if (success){
            if (registeredCourses == null){
                registeredCourses = new ArrayList<>();
            }
            registeredCourses.removeIf(regis -> regis.courseId == course.courseId);
            registeredCourses.add(course);
        }
        writeConfirmedRegistration();
    }

    public List<Course> retrieveRegisteredCourse() throws IOException{
        List<Course> courses = new ArrayList<>();
        File userPath = new File(userDirectoryPath + studentId + "/registration.txt");
        Scanner scanner = new Scanner(userPath);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] info = line.split("\\|");
            Course course = new Course(Integer.parseInt(info[0]), info[1], info[2], info[3], Integer.parseInt(info[4]),
                    info[5], Integer.parseInt(info[6]), info[7], info[8], Integer.parseInt(info[9]));
            courses.add(course);
        }
        registeredCourses = courses;
        return courses;
    }

    public void writeConfirmedRegistration() throws IOException {
        FileWriter fileWriter = new FileWriter(userDirectoryPath + studentId + "/registration.txt", false);
        if (registeredCourses != null && registeredCourses.size() != 0){
            for (Course course : registeredCourses) {
                String confirmedRegistration = course.courseId + "|"+ course.college +"|"+ course.department+"|"
                        + course.academicDegree +"|"+ course.academicYear +"|"+ course.courseName
                        + "|"+ course.credit +"|"+course.location +"|"+ course.instructor +"|"+ course.quota;
                if(!registeredCourses.get(registeredCourses.size()-1).equals(course)) confirmedRegistration += "\n";
                fileWriter.write(confirmedRegistration);
            }
        }
        fileWriter.close();
    }
}