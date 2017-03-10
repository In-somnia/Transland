package dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean validate(String name)
    {
        boolean result = false;
        Pattern pattern = Pattern.compile("[А-Я][а-я]{1,49}");
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches())
        {
            result = true;
        }
        return result;
    }
}
