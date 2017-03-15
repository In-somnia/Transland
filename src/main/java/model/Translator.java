package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translator {

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private boolean isTranslator;
    private String city;
    private String cell;
    private String email;
    private Education education;
    private String password;

    private String experience;
    private String info;
    private boolean isRemoved;

    public void setIsTranslator(boolean isTranslator) {
        this.isTranslator = isTranslator;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
}
