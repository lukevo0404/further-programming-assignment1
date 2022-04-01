import java.util.Calendar;
import java.util.Date;

public class Student {
    private String id;
    private Date birthdate;
    private String name;

    public Student(){
        Calendar cal = Calendar.getInstance();
        cal.set(1998, 1, 1);

        this.id = "default";
        this.birthdate = cal.getTime();
        this.name = "default";
    }

    public Student(String id, int year, int month, int date, String name ) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        this.birthdate = cal.getTime();

        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getBirthdate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthdate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        return date + "/" + month + "/" + year;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthdate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);

        return
                "Student [id: " + id + ", " + "name: " + name + getBirthdate()+ "]";
    }
}