package course;

public class Course {
    public int courseId;
    public String courseName;
    public String college;
    public String department;
    public int academicYear;
    public int credit;
    public String academicDegree;
    public String instructor;
    public int quota;
    public String location;
    public Course (int courseId, String college, String department, String academicDegree, int academicYear,
                   String courseName, int credit, String location, String instructor, int quota){
        this.courseId = courseId;
        this.courseName = courseName;
        this.college = college;
        this.department = department;
        this.academicYear = academicYear;
        this.credit = credit;
        this.academicDegree = academicDegree;
        this.instructor = instructor;
        this.quota = quota;
        this.location = location;
    }
    public static final String  titleFormat = "%5s%20s%40s%13s%8s%50s%10s%15s%30s%8s";
    public static final String courseFormat = "%5d%20s%40s%13s%8d%50s%10d%15s%30s%8d";
    @Override
    public String toString() {

        return String.format(courseFormat,
                courseId,college,department,academicDegree,
                academicYear,courseName, credit,location,instructor,quota);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Course){
            return courseId == ((Course)obj).courseId;
        }else{
            return false;
        }
    }
}
