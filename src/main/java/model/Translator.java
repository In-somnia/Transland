package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translator {

    public static String FIRST_NAME_KEY = "firstname";

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private boolean isTranslator;
    private String city;
    private String cell;
    private String email;
    private String password;
    private Education education;
    private String experience;
    private String topics;
    private String info;

    public void setIsTranslator(boolean isTranslator) {
        this.isTranslator = isTranslator;
    }
}
