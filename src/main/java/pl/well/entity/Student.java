package pl.well.entity;


/**
 * Created by serfer on 24.07.16.  implements Comparable<Employee>
 */
public class Student extends User implements Comparable<Student> {

    String role;


    public Student(String name, String surname, String email) {

        this.name = name;
        this.surname = surname;
        this.email = email;


    }

    public Student(String name, String surname, String email, String pass) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;

    }

    public Student(int id, String name, String surname, String email, String pass, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;
        this.role = role;

    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', surname='" + surname + "', email = '" + email + "'";
    }

    @Override
    public int compareTo(Student student) {
        int result = this.id - student.id;

        return result;
    }
}
