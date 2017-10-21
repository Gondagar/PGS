package pl.well.entity;


public class Lesson {

    int id;
    String theme;
    String place;
    String feedback;
    String date;
    String time;


    public Lesson(String theme, String place, String feedback, String data) {
        this.theme = theme;
        this.place = place;
        this.feedback = feedback;
        this.date = data;

    }

    public Lesson(int id, String theme, String place, String feedback, String date, String time) {
        this.id = id;
        this.theme = theme;
        this.place = place;
        this.feedback = feedback;
        this.date = date;
        this.time = time;
    }

    public Lesson(int id, String theme, String place, String feedback, String data) {
        this.id = id;
        this.theme = theme;
        this.place = place;
        this.feedback = feedback;
        this.date = data;
    }

    public int getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getPlace() {
        return place;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
