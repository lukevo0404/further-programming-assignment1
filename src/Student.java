import java.util.Date;

public class Student {
    private String id;
    private Date birthdate;
    private String name;

    public Student(String id, Date birthdate, String name ) {
        this.id = id;
        this.birthdate = birthdate;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [id: " + id + ", birthdate: " + birthdate + ", name:" + name + "]";
    }
}