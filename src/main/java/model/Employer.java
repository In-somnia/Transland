package model;


import lombok.Data;

@Data
public class Employer {

    public static String FIRST_NAME_KEY = "firstname";

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private boolean isTranslator;
    private String city;
    private String email;
    private String firm;
    private String cell;
    private String password;
}
