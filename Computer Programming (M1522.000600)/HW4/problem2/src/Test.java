import server.Server;
import course.Bidding;
import course.Course;
import utils.ErrorCode;
import utils.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Problem2_1TestCase();
        Problem2_2TestCase();
        Problem2_3TestCase();
        resetUserDirs();
    }

    static void printOX(String prompt, boolean condition) {
        if (condition) {
            System.out.println("------" + prompt + "O");
        } else {
            System.out.println("------" + prompt + "X");
        }
    }

    static void Problem2_1TestCase() {
        /*
         * The first parameter Map<String, Object> is assumed to not include options other than "name", "dept", "ay".
         * The second parameter sortBy is assumed to be null, "id", "name", "dept", "ay" and nothing else.
         */
        println("Problem 2.1.");
        Server server = new Server();

        List<Course> searchResult = server.search(new HashMap<>(), null);
        printOX("2.1.1 search entire courses : ", checkCourseListWithIDArray(searchResult, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));

        searchResult = server.search(new HashMap<>(), "id");
        printOX("2.1.2 search sort by id : ", checkCourseListWithIDArray(searchResult, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));

        searchResult = server.search(new HashMap<>(), "name");
        printOX("2.1.3 search sort by name : ", checkCourseListWithIDArray(searchResult, new int[]{7, 9, 1, 10, 3, 8, 5, 6, 11, 4, 2, 12}));

        searchResult = server.search(new HashMap<>(), "dept");
        printOX("2.1.4 search sort by department : ", checkCourseListWithIDArray(searchResult, new int[]{12, 6, 9, 3, 5, 8, 1, 2, 10, 11, 4, 7}));

        searchResult = server.search(new HashMap<>(), "ay");
        printOX("2.1.5 search sort by academic year : ", checkCourseListWithIDArray(searchResult, new int[]{1, 4, 8, 3, 6, 9, 2, 5, 7, 10, 11, 12}));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Programming");

        searchResult = server.search(map, null);
        printOX("2.1.6 search by name : ", checkCourseListWithIDArray(searchResult, new int[]{3}));

        map = new HashMap<String, Object>();
        map.put("ay", 3);
        searchResult = server.search(map, null);
        printOX("2.1.7 search by academic year : ", checkCourseListWithIDArray(searchResult, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));

        map = new HashMap<String, Object>();
        map.put("dept", "Western History");
        searchResult = server.search(map, null);
        printOX("2.1.8 search by department : ", checkCourseListWithIDArray(searchResult, new int[]{7}));

        map = new HashMap<String, Object>();
        map.put("name", "Materials Introduction");
        searchResult = server.search(map, null);
        printOX("2.1.9 search by name with keywords order mixed : ", checkCourseListWithIDArray(searchResult, new int[]{11}));

        map = new HashMap<String, Object>();
        map.put("name", "Studio 23");
        searchResult = server.search(map, null);
        printOX("2.1.10 search by name with one of keywords nonexistent : ", checkCourseListWithIDArray(searchResult, new int[]{}));

        map = new HashMap<String, Object>();
        map.put("dept", "Mathematical Sciences");
        map.put("name", "Differential");
        searchResult = server.search(map, null);
        printOX("2.1.11 search by both department and name : ", checkCourseListWithIDArray(searchResult, new int[]{2}));

        map = new HashMap<String, Object>();
        map.put("dept", "Crafts and Design");
        map.put("name", "Research and Thinking Design");
        map.put("ay", 3);
        searchResult = server.search(map, null);
        printOX("2.1.12 search by academic year, department and name : ", checkCourseListWithIDArray(searchResult, new int[]{5}));

        map = new HashMap<String, Object>();
        map.put("dept", "Mathematical Sciences");
        searchResult = server.search(map, "name");
        printOX("2.1.13 search by department, and sort by name : ", checkCourseListWithIDArray(searchResult, new int[]{10, 2}));

        map = new HashMap<String, Object>();
        map.put("ay", 2);
        searchResult = server.search(map, "dept");
        printOX("2.1.14 search by academic year, and sort by department : ", checkCourseListWithIDArray(searchResult, new int[]{6, 9, 3, 8, 1, 4}));
    }

    static void Problem2_2TestCase() {
        println("Problem 2.2.");
        Server server = new Server();
        resetUserDirs();
        int status;
        Pair<Integer, List<Bidding>> bidResult;

        bidResult = server.retrieveBids("2010-22221");
        printOX("2.2.1 retrieve bidding without any bidding in current execution : ",
                checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 8, 9, 10}, new int[]{17, 18, 18, 18}));

        bidResult = server.retrieveBids("2010-29991");
        printOX("2.2.2 retrieve bidding with wrong user id : ",
                bidResult.key == ErrorCode.USERID_NOT_FOUND && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{}, new int[]{}));

        status = server.bid(7, 7, "2012-22221");
        bidResult = server.retrieveBids("2012-22221");
        printOX("2.2.3 bidding once and checking bidding status : ",
                status == ErrorCode.SUCCESS && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 3, 4, 5, 7}, new int[]{8, 17, 16, 12, 7}));

        status = server.bid(7, 7, "2020-99999");
        bidResult = server.retrieveBids("2020-99999");
        printOX("2.2.4 bidding on wrong id and checking bidding status from that id : ",
                status == ErrorCode.USERID_NOT_FOUND && bidResult.key == ErrorCode.USERID_NOT_FOUND && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{}, new int[]{}));

        status = server.bid(15, 7, "2012-22221");
        bidResult = server.retrieveBids("2012-22221");
        printOX("2.2.5 attempt bidding to nonexistent course id and checking bidding status : ",
                status == ErrorCode.NO_COURSE_ID && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 3, 4, 5, 7}, new int[]{8, 17, 16, 12, 7}));

        status = server.bid(1, -3, "2012-22221");
        bidResult = server.retrieveBids("2012-22221");
        printOX("2.2.6 attempt bidding negative mileage and checking bidding status : ",
                status == ErrorCode.NEGATIVE_MILEAGE && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 3, 4, 5, 7}, new int[]{8, 17, 16, 12, 7}));

        status = server.bid(1, 36, "2012-22221");
        bidResult = server.retrieveBids("2012-22221");
        printOX("2.2.7 attempt bidding larger than max mileage per course and checking bidding status : ",
                status == ErrorCode.OVER_MAX_COURSE_MILEAGE && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 3, 4, 5, 7}, new int[]{8, 17, 16, 12, 7}));

        status = server.bid(1, 12, "2010-22221");
        bidResult = server.retrieveBids("2010-22221");
        printOX("2.2.8 attempt bidding larger than max mileage and checking bidding status : ",
                status == ErrorCode.OVER_MAX_MILEAGE && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 8, 9, 10}, new int[]{17, 18, 18, 18}));

        status = server.bid(9, 5, "2010-22221");
        bidResult = server.retrieveBids("2010-22221");
        printOX("2.2.9 modifying mileage of already existing bid course : ",
                status == ErrorCode.SUCCESS && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 8, 9, 10}, new int[]{17, 18, 5, 18}));

        status = server.bid(7, 14, "2010-22221");
        status = server.bid(11, 1, "2010-22221");
        bidResult = server.retrieveBids("2010-22221");
        printOX("2.2.10 check if bid method can prevent slight increase from the max mileage : ",
                status == ErrorCode.OVER_MAX_MILEAGE && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{2, 7, 8, 9, 10}, new int[]{17, 14, 18, 5, 18}));

        status = server.bid(2, 0, "2010-22221");
        bidResult = server.retrieveBids("2010-22221");
        printOX("2.2.11 check if bidding 0 mileage to already existing course cancels the course : ",
                status == ErrorCode.SUCCESS && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{7, 8, 9, 10}, new int[]{14, 18, 5, 18}));

        status = server.bid(5, 0, "2010-22221");
        bidResult = server.retrieveBids("2010-22221");
        printOX("2.2.12 check if bidding 0 mileage to a new course does nothing : ",
                status == ErrorCode.SUCCESS && bidResult.key == ErrorCode.SUCCESS && checkUnorderedBiddingListWithIDArray(bidResult.value, new int[]{7, 8, 9, 10}, new int[]{14, 18, 5, 18}));
    }

    static void Problem2_3TestCase() {
        println("Problem 2.3.");
        Server server = new Server();
        resetUserDirs();
        Pair<Integer, List<Course>> confirmed;
        println("confirmBids without any bidding in current execution");
        if (server.confirmBids()) {
            confirmed = server.retrieveRegisteredCourse("2010-22221");
            printOX("2.3.1", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{2, 8, 9, 10}));

            confirmed = server.retrieveRegisteredCourse("2012-22221");
            printOX("2.3.2", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{2, 4, 5}));

            confirmed = server.retrieveRegisteredCourse("2018-22233");
            printOX("2.3.3", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{1, 8, 9, 10}));

            confirmed = server.retrieveRegisteredCourse("2019-12344");
            printOX("2.3.4", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{3}));

            confirmed = server.retrieveRegisteredCourse("2019-26633");
            printOX("2.3.5", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{3, 8}));

            confirmed = server.retrieveRegisteredCourse("2019-45211");
            printOX("2.3.6", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{3, 4, 11}));

            confirmed = server.retrieveRegisteredCourse("2019-45677");
            printOX("2.3.7", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{}));
        } else {
            System.out.println("failed confirmation");
        }
        resetUserDirs();
        server.bid(3, 11, "2019-12344");
        server.bid(7, 8, "2019-12344");
        server.bid(8, 8, "2019-12344");
        server.bid(9, 8, "2019-12344");
        server.bid(10, 8, "2019-12344");
        server.bid(11, 8, "2019-12344");
        server.bid(12, 8, "2019-12344");
        server.bid(2, 13, "2019-12344");
        server.bid(5, 18, "2019-26633");
        server.bid(6, 19, "2019-26633");
        server.bid(8, 18, "2019-26633");
        server.bid(11, 10, "2019-26633");
        server.bid(7, 18, "2012-22221");
        server.bid(3, 17, "2019-45677");
        println("confirmBids with bidding in current execution");
        if (server.confirmBids()) {
            confirmed = server.retrieveRegisteredCourse("2010-22221");
            printOX("2.3.8", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{2, 8, 9, 10}));

            confirmed = server.retrieveRegisteredCourse("2012-22221");
            printOX("2.3.9", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{2, 4, 5, 7}));

            confirmed = server.retrieveRegisteredCourse("2018-22233");
            printOX("2.3.10", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{1, 8, 9, 10}));

            confirmed = server.retrieveRegisteredCourse("2019-12344");
            printOX("2.3.11", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{2, 7, 8, 9, 10, 11, 12}));

            confirmed = server.retrieveRegisteredCourse("2019-26633");
            printOX("2.3.12", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{3, 5, 8, 11}));

            confirmed = server.retrieveRegisteredCourse("2019-45211");
            printOX("2.3.13", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{3, 4, 11}));

            confirmed = server.retrieveRegisteredCourse("2019-45677");
            printOX("2.3.14", checkUnorderedCourseListWithIDArray(confirmed.value, new int[]{3}));
        } else {
            System.out.println("failed confirmation");
        }
    }

    static void println(Object o) {
        System.out.println(o);
    }

    static boolean checkCourseListWithIDArray(List<Course> courses, int[] idarray) {
        if (idarray.length != courses.size()) {
            return false;
        }
        if (courses != null) {
            int index = 0;
            for (Course course : courses) {
                if (course.courseId != idarray[index]) {
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    static boolean checkUnorderedCourseListWithIDArray(List<Course> courses, int[] idarray) {
        if (idarray.length != courses.size()) {
            return false;
        }
        Collections.sort(courses, new courseComparator());
        if (courses != null) {
            int index = 0;
            for (Course course : courses) {
                if (course.courseId != idarray[index]) {
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    static boolean checkUnorderedBiddingListWithIDArray(List<Bidding> biddings, int[] idarray, int[] mileagearray) {
        if (idarray.length != biddings.size()) {
            return false;
        }
        Collections.sort(biddings, new biddingComparator());
        if (mileagearray.length != biddings.size()) {
            return false;
        }
        int index = 0;
        for (Bidding bidding : biddings) {
            if (bidding.courseId != idarray[index] || bidding.mileage != mileagearray[index]) {
                return false;
            }
            index++;
        }
        return true;
    }

    static <T> List<String> maptoString(List<T> objList) {
        List<String> out = new ArrayList<>();
        for (T t : objList) {
            out.add(t.toString());
        }
        return out;
    }

    static public void printCourseStrings(List<Course> courses) {
        List<String> strings = maptoString(courses);
        if (strings != null) {
            println(String.format(Course.titleFormat,
                    "ID", "College", "Dept", "Degree", "A.Y.", "Course Title",
                    "Credits", "Location", "Instructor", "Quota"));
            printStrings(strings);
        }
        println("");
    }

    static public void printBiddingStrings(List<Bidding> courses) {
        List<String> strings = maptoString(courses);
        if (strings != null) {
            println(String.format(Bidding.titleFormat, "Mileage", "Course ID"));
            printStrings(strings);
        }
        println("");
    }

    static public void printStrings(List<String> strings) {
        if (strings != null) {
            for (String string : strings) {
                println(string);
            }
        }
    }

    static void resetUserDirs() {
        List<String> userIDList = getUserIDList();
        try {
            for (String userID : userIDList) {
                String userDir = "data/Users/";
                String backupDir = "data/Users_backup/";
                String bidPath = userDir + userID + "/bid.txt";
                String bidBackupPath = backupDir + userID + "/bid.txt";
                File userDirFile = new File(userDir + userID);
                if (userDirFile.isDirectory()) {
                    for (File file : userDirFile.listFiles())
                        if (!file.isDirectory())
                            file.delete();
                }
                fileCopyOverWrite(bidBackupPath, bidPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> getUserIDList() {
        String userDir = "data/Users/";
        File userDirFile = new File(userDir);
        String[] userIDs = userDirFile.list();
        List<String> result = new ArrayList<>();
        if (userIDs != null) {
            for (String userid : userIDs) {
                if (userid.matches("\\d{4}-\\d{5}")) {
                    result.add(userid);
                }
            }
        }
        return result;
    }

    static void fileCopyOverWrite(String fromPath, String toPath) throws IOException {
        Path from = Paths.get(fromPath);
        Path to = Paths.get(toPath);
        if (Files.exists(from)) {
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}

class biddingComparator implements Comparator<Bidding> {
    @Override
    public int compare(Bidding o1, Bidding o2) {
        return Integer.compare(o1.courseId, o2.courseId);
    }
}

class courseComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return Integer.compare(o1.courseId, o2.courseId);
    }
}
