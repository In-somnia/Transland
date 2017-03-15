package dao;


import model.Translator;

import java.util.List;


public interface TranslatorDao {
    long create(Translator translator);
    Translator get(long id);
    boolean checkIsRemoved(long id);
    boolean removePage(long id);
    boolean restorePage(long id);
    List<Translator> getAll();
    void editTranslatorData(Translator translator);
}
