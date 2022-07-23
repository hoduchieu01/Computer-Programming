package course;

public class Bidding {
    public int courseId;
    public int mileage;
    public Bidding(int courseId, int mileage){
        this.courseId = courseId;
        this.mileage = mileage;
    }


    public static final String titleFormat = "%15s\t%15s";
    public static final String bidFormat = "%10d\t\t->%10d";
    @Override
    public String toString() {
        return String.format(bidFormat, mileage,courseId);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Bidding){
            return courseId == ((Bidding) obj).courseId && mileage == ((Bidding) obj).mileage;
        }else{
            return false;
        }
    }
}
