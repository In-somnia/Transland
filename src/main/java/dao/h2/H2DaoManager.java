package dao.h2;

import dao.DaoManager;
import javax.sql.DataSource;


public class H2DaoManager implements DaoManager {
    private DataSource dataSource;

    public H2DaoManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public H2TranslatorDao getTranslatorDao() {
        return new H2TranslatorDao(dataSource);
    }

    @Override
    public H2AuthorizationDao getAuthorizationDao() {
        return new H2AuthorizationDao(dataSource);
    }
}