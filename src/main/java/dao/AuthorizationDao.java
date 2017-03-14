package dao;


public interface AuthorizationDao {
    long checkCredentials(String email, String password);
}
