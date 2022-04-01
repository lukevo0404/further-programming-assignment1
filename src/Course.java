import java.util.Calendar;

public class Course {
    private String id;
    private String name;
    private int credit;

    public Course(){
        this.id = "default";
        this.name = "default";
        this.credit = 0;
    }

    public Course(String id, String name, int credit){
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    @Override
    public String toString() {

        return
                "Course [id: " + id + ", " + "name: " + name + ", " + "credit: " + credit + "]";
    }
}
