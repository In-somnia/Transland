package dao;


public interface AuthorizationDao {
    boolean checkCredentials(String email, String password);
}
