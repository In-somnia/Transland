package model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Education {
    private long id;
    private String university;
    private String department;
    private EducationForm educationType;
    private int graduationYear;

}
