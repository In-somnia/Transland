package dao;


import model.Translator;

import java.util.List;


public interface TranslatorDao {
    long create(Translator translator);
    Translator get(long id);
    void remove(Translator translator);
    List<Translator> getAll();
}
