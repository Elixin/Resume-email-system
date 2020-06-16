package com.example.email.pojo;

import org.springframework.stereotype.Component;

@Component
public class ResumeEmaillPojo {
    private int id;
    private String name;
    private String education;
    private String position;
    private String rusumeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRusumeName() {
        return rusumeName;
    }

    public void setRusumeName(String rusumeName) {
        this.rusumeName = rusumeName;
    }

    @Override
    public String toString() {
        return "ResumeEmaillPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", education='" + education + '\'' +
                ", position='" + position + '\'' +
                ", rusumeName='" + rusumeName + '\'' +
                '}';
    }
}
