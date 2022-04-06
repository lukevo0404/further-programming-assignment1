import java.util.Calendar;

public class Course {
    private String id;
    private String name;
    private int credit;
    int unique;

    public Course(){
        this.id = "default";
        this.name = "default";
        this.credit = 0;
    }

    public Course(String id, String name, int credit){
        this.id = id;
        this.unique = Integer.parseInt(id.replaceAll("[^\\d.]", ""));
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
    public int hashCode() {
        return unique;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (unique != other.unique)
            return false;
        return true;
    }

    @Override
    public String toString() {

        return
                "Course [id: " + id + ", " + "name: " + name + ", " + "credit: " + credit + "]";
    }
}
