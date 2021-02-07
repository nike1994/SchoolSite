package pl.edu.wszib.school.website.model.View;

import java.util.List;

public class ClassModel {
    private int id;
    private int year;
    private String name;
    private List<Integer> subjects_id;

    public ClassModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(List<Integer> subjects_id) {
        this.subjects_id = subjects_id;
    }
}
