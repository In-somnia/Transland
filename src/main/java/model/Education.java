package model;


import lombok.Data;

@Data
public class Education {
    private long id;
    private String university;
    private String department;
    private EducationForm educationType;
    private int graduationYear;
}
