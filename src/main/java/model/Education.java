package model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Education {
    private long id;
    private String country;
    private String city;
    private String university;
    private String department;
    private boolean educationType;
    private int graduationYear;
}
