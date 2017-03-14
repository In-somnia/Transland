package dao;


import model.Translator;

import java.util.List;


public interface TranslatorDao {
    long create(Translator translator);
    Translator get(String email);
    void remove(long id);
    List<Translator> getAll();
}
