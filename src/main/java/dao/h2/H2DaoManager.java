package dao.h2;

import dao.DaoManager;
import dao.TranslatorDao;

public class H2DaoManager implements DaoManager {
    @Override
    public TranslatorDao getTranslatorDao() {
        return new H2TranslatorDao();
    }
}