package server;

import course.*;
import user.*;
import utils.Config;
import utils.ErrorCode;
import utils.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Server {
    private final String coursesPath = "data/Courses/2020_Spring/";
    private final String userPath = "data/Users/";

    public List<Course> search(Map<String,Object> searchConditions, String sortCriteria){
        // TODO Problem 2-1
        // implement this method to returns the list of courses matching the search conditions (given in the Map) following the sorting criteria
        List<Course> allCourses = getCoursesInfo();
        List<Course> coursesMatchedSearchConditions = getSearchedCourses(allCourses, searchConditions);
        sortByConditions(coursesMatchedSearchConditions, sortCriteria);
        return coursesMatchedSearchConditions;
    }

    private List<Course> getCoursesInfo(){
        List<Course> allCourses = new ArrayList<>();
        try {
            List<String> colleges = getColleges();
            if (colleges.size() != 0) {
                for (String college : colleges) {
                    List<String> courses = getCourses(college);
                    if (courses.size() != 0) {
                        for (String course : courses) {
                            Course courseInfo = readCourseInfo(college, course);
                            allCourses.add(courseInfo);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
        }
        return allCourses;
    }

    private List<String> getColleges(){
        List<String> colleges = new ArrayList<>();
        File path = new File(coursesPath);
        File[] collegeFiles = path.listFiles();
        if (collegeFiles != null){
            for (File collegeFile:collegeFiles){
                if (collegeFile.isDirectory()) colleges.add(collegeFile.getName());
            }
        }
        return colleges;
    }

    private List<String> getCourses(String college){
        List<String> courses = new ArrayList<>();
        File path = new File(coursesPath + college);
        File[] courseFiles = path.listFiles();
        if (courseFiles != null){
            for (File courseFile:courseFiles){
                if (courseFile.isFile()) courses.add(courseFile.getName());
            }
        }
        return courses;
    }

    private Course readCourseInfo(String college, String courseIdPath) throws IOException {
        Path path = Path.of(coursesPath + college + "/" + courseIdPath);
        String[] courseInfoList = Files.readString(path).split("\\|");
        int courseId = Integer.parseInt(courseIdPath.substring(0, courseIdPath.lastIndexOf('.')));
        String department = courseInfoList[0];
        String academicDegree = courseInfoList[1];
        int academicYear = Integer.parseInt(courseInfoList[2]);
        String courseName = courseInfoList[3];
        int credit = Integer.parseInt(courseInfoList[4]);
        String location = courseInfoList[5];
        String instructor = courseInfoList[6];
        int quota = Integer.parseInt(courseInfoList[7]);
        return new Course (courseId, college, department, academicDegree, academicYear, courseName, credit, location, instructor, quota);
    }

    private List<Course> getSearchedCourses(List<Course> allCourses, Map<String, Object> searchConditions){
        List<Course> searchedCourses = allCourses;
        if (searchConditions == null || searchConditions.isEmpty()){
            return searchedCourses;
        }
        List<String> conditionList = new ArrayList<>(searchConditions.keySet());
        // Search Courses By Academic Year
        if (conditionList.contains("ay")) {
            searchedCourses = searchCoursesByAcademicYear(searchedCourses, (int) searchConditions.get("ay"));
        }
        // Search Courses By Department
        if (conditionList.contains("dept")) {
            searchedCourses = searchCoursesByDepartment(searchedCourses, (String) searchConditions.get("dept"));
        }
        // Search Courses by Name
        if (conditionList.contains("name")) {
            searchedCourses = searchCoursesByName(searchedCourses, (String) searchConditions.get("name"));
        }
        return searchedCourses;
    }

    // For the dept criterion, you should find the courses that have identical values to the search value
    private List<Course> searchCoursesByDepartment(List<Course> courses, String department){
        List<Course> searchedCourse = new ArrayList<>();
        for (Course course:courses){
            if (course.department.equals(department)) searchedCourse.add(course);
        }
        return searchedCourse;
    }

    // For the ay criterion, you should find the courses that have lower or equals to the search value
    private List<Course> searchCoursesByAcademicYear(List<Course> courses, int academicYear){
        List<Course> searchedCourse = new ArrayList<>();
        for (Course course:courses){
            if (course.academicYear <= academicYear) searchedCourse.add(course);
        }
        return searchedCourse;
    }

    // Search Courses by Name
    private List<Course> searchCoursesByName(List<Course> courses, String searchKeyword){
        List<Course> searchedCourse = new ArrayList<>();
        // When the search string is an empty string, you should return an empty list
        if(searchKeyword.length() == 0) return searchedCourse;
        for (Course course:courses){
            if (containsAllKeywords(course, searchKeyword)) searchedCourse.add(course);
        }
        return searchedCourse;
    }

    private boolean containsAllKeywords(Course course, String searchKeyword){
        List<String> courseNameList = Arrays.asList(course.courseName.split(" "));
        List<String> searchKeywordList = Arrays.asList(searchKeyword.split(" "));
        for (String word:searchKeywordList){
            if (!courseNameList.contains(word)) {
                return false;
            }
        }
        return true;
    }

    // The second parameter String sortCriteria describes the sorting criteria.
    // id: Sort by the course id in ascending order
    // name: Sort by the course name in the directory order. If the department names are equal, sort by the course id in ascending order
    // ay: Sort by the academic year in ascending order. If the academic years are equal, sort by the course id in ascending order.
    // if sortCriteria is null sort by the courseId
    private void sortByConditions(List<Course> courses, String sortCriteria){
        if (sortCriteria == null || sortCriteria.equals("") || sortCriteria.equals("id")){
            courses.sort(
                    (Course o1, Course o2) -> {
                        return Integer.compare(o1.courseId, o2.courseId);
                    }
            );
            return;
        }
        if (sortCriteria.equals("name")) courses.sort(
                (Course o1, Course o2) -> {
                    if (o1.courseName.equals(o2.courseName)) return Integer.compare(o1.courseId, o2.courseId);
                    return o1.courseName.compareTo(o2.courseName);
                }
        );
        if (sortCriteria.equals("dept")) courses.sort(
                (Course o1, Course o2) -> {
                    if (o1.department.equals(o2.department)) return Integer.compare(o1.courseId, o2.courseId);
                    return o1.department.compareTo(o2.department);
                }
        );
        if (sortCriteria.equals("ay")) courses.sort(
                (Course o1, Course o2) -> {
                    if (o1.academicYear == o2.academicYear) return Integer.compare(o1.courseId, o2.courseId);
                    return Integer.compare(o1.academicYear, o2.academicYear);
                }
        );
    }

    // The bid method allows a student to place a bid for a course.
    // output: int - errorCode specifying the status of the method call
    public int bid(int courseId, int mileage, String userId){
        // TODO Problem 2-2
        int errorCode = ErrorCode.SUCCESS;
        User user = null;
        try {
            if (!isValidUser(userId)){
                errorCode = Math.min(errorCode, ErrorCode.USERID_NOT_FOUND);
            }
            else {
                user = new User(userId);
                boolean isUserContain = false;
                if (user.biddingList != null && user.biddingList.size() > 0){
                    for (Bidding bidding : user.biddingList) {
                        if (bidding.courseId == courseId) isUserContain = true;
                    }
                }
                if (mileage > Config.MAX_MILEAGE_PER_COURSE) {
                    errorCode = Math.min(errorCode, ErrorCode.OVER_MAX_COURSE_MILEAGE);
                }
                if (mileage < 0){
                    errorCode = Math.min(errorCode, ErrorCode.NEGATIVE_MILEAGE);
                }
                if (!isValidCourse(courseId)){
                    errorCode = Math.min(errorCode, ErrorCode.NO_COURSE_ID);
                }
                if (user.biddingList().size()  + ((mileage > 0 && !isUserContain) ? 1:0) > Config.MAX_COURSE_NUMBER) {
                    errorCode = Math.min(errorCode, ErrorCode.OVER_MAX_COURSE_NUMBER);
                }
                if (user.sumOfBidMileage(courseId, mileage) > Config.MAX_MILEAGE)
                    errorCode = Math.min(errorCode, ErrorCode.OVER_MAX_MILEAGE);
            }
        } catch (IOException e){
            errorCode = Math.min(errorCode, ErrorCode.IO_ERROR);
            return errorCode;
        }
        if (errorCode == ErrorCode.SUCCESS){
            try {
                user.placingBid(courseId, mileage);
            }
            catch (FileNotFoundException e){
            }
            catch (IOException e){
            }
        }
        return errorCode;
    }

    private boolean isValidUser(String userId){
        if (userId == null) return false;
        File userDirectory = new File(userPath);
        File[] subDirectoryList = userDirectory.listFiles(File::isDirectory);
        if (subDirectoryList == null) return false;
        for (int i = 0; i < subDirectoryList.length; i++){
            if (subDirectoryList[i].getName().equals(userId)) return true;
        }
        return false;
    }

    private boolean isValidCourse(int courseId){
        List<Course> courses = getCoursesInfo();
        for (Course course:courses){
            if (course.courseId == courseId) return true;
        }
        return false;
    }

    // This method return Pair<Integer, List<Bidding>: The key is error code and the value is the list of previously placed bids.
    public Pair<Integer,List<Bidding>> retrieveBids(String userId){
        // TODO Problem 2-2
        int errorCode = ErrorCode.SUCCESS;
        List<Bidding> listPlacedBid = new ArrayList<>();
        try {
            if (!isValidUser(userId)) {
                errorCode = ErrorCode.USERID_NOT_FOUND;
                return new Pair<>(ErrorCode.USERID_NOT_FOUND, listPlacedBid);
            }
            else {
                User user = new User(userId);
                listPlacedBid = user.retrieveBids();
            }
        } catch (FileNotFoundException e) {
            errorCode = Math.min(errorCode, ErrorCode.IO_ERROR);
            return new Pair<>(errorCode, listPlacedBid);
        }
        return new Pair<>(errorCode, listPlacedBid);
    }

    // Implement confirmBids method to determine who will be registered for which courses based on the bids placed so far
    // Output: Boolean: false - if IOException is thrown during the execution of the method.
    // Otherwise, True.
    public boolean confirmBids(){
        // TODO Problem 2-3
        try {
            List<User> users = retrieveAllUsers();
            List<Course> courses = new ArrayList<>();
            List<String> colleges = getColleges();
            if (colleges.size() != 0) {
                for (String college : colleges) {
                    List<String> courseList = getCourses(college);
                    if (courseList.size() != 0) {
                        for (String course : courseList) {
                            Course courseInfo = readCourseInfo(college, course);
                            courses.add(courseInfo);
                        }
                    }
                }
            }
            for (User u : users){
                u.registeredCourses = new ArrayList<Course>();
                u.writeConfirmedRegistration();
            }
            registerAllStudents(courses, users);
        } catch (IOException  e){
            return false;
        }
        return true;
    }

    private List<User> retrieveAllUsers() throws IOException{
        List<User> users = new ArrayList<>();
        File userPathFile = new File(userPath);
        File[] userList = userPathFile.listFiles(File::isDirectory);
        if (userList == null) return null;
        for (File userFile:userList){
            if (userFile.isDirectory()) {
                User user = new User(userFile.getName());
                users.add(user);
            }
        }
        return users;
    }

    private void registerAllStudents(List<Course> courses, List<User> users) throws IOException{
        Map<Course, List<Pair<User, Integer>>> courseBiddingMap = new HashMap<>();
        for (Course course:courses){
            courseBiddingMap.put(course, getAllBidding(users, course));
        }
        for (Course course:courseBiddingMap.keySet()){
            // System.out.print("Course " + course.courseId + " ");
            if (course.quota < courseBiddingMap.get(course).size()){
                for (int i = 0; i < course.quota; i++){
                    User user = courseBiddingMap.get(course).get(i).key;
                    user.updateRegistrationStatus(course, true);
                    //  System.out.print(user.userId() + " ");
                }
                for (int i = course.quota; i < courseBiddingMap.get(course).size();i++){
                    User user = courseBiddingMap.get(course).get(i).key;
                    user.updateRegistrationStatus(course, false);
                }
            } else {
                for (int i = 0; i < courseBiddingMap.get(course).size(); i++){
                    User user = courseBiddingMap.get(course).get(i).key;
                    user.updateRegistrationStatus(course, true);
                    // System.out.print(user.userId() + " ");
                }
            }
            // System.out.println();
        }
    }

    private List<Pair<User, Integer>> getAllBidding(List<User> users, Course course){
        List<Pair<User, Integer>> courseBiddingList = new ArrayList<>();
        for (User user:users){
            for (Bidding bidding:user.biddingList()){
                if (bidding.courseId == course.courseId){
                    courseBiddingList.add(new Pair<>(user, bidding.mileage));
                }
            }
        }
        courseBiddingList.sort(new Comparator<Pair<User, Integer>>() {
            @Override
            public int compare(Pair<User, Integer> o1, Pair<User, Integer> o2) {
                if (o1.value == o2.value) {
                    if (o1.key.totalMileageSpent() == o2.key.totalMileageSpent()){
                        return o1.key.studentId().compareTo(o2.key.studentId());
                    }
                    else{
                        return Integer.compare(o1.key.totalMileageSpent(), o2.key.totalMileageSpent());
                    }
                }
                return Integer.compare(o2.value, o1.value);
            }
        });
        return courseBiddingList;
    }

    // Implement this method to return the outputs: Pair<Integer, List<Course>> The key is error code.
    // The value is the list of the Course instances that are confirmed to be registered.
    // The list does not need to be stored.
    public Pair<Integer,List<Course>> retrieveRegisteredCourse(String userId){
        // TODO Problem 2-3
        List<Course> coursesList = new ArrayList<>();
        int errorCode = ErrorCode.SUCCESS;
        try {
            if (!isValidUser(userId)) {
                return new Pair<>(ErrorCode.USERID_NOT_FOUND, coursesList);
            } else {
                User user = new User(userId);
                coursesList = user.retrieveRegisteredCourse();
            }
        } catch (IOException e){
            return new Pair<>(ErrorCode.IO_ERROR, coursesList);
        }
        return new Pair<>(errorCode, coursesList);
    }
}