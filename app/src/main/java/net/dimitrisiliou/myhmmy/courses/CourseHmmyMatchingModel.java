package net.dimitrisiliou.myhmmy.courses;

public class CourseHmmyMatchingModel {
    private String id;
    private String title;
    private String semester;
    private String hours;
    private String lab;
    private String tutor;


    public CourseHmmyMatchingModel(String id, String title, String semester, String hours, String lab, String tutor) {
        this.id = id;
        this.title = title;
        this.semester = semester;
        this.hours = hours;
        this.lab = lab;
        this.tutor = tutor;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }



}
