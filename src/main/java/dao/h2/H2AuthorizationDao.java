package dao.h2;

import dao.AuthorizationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2AuthorizationDao implements AuthorizationDao {
    static final Logger LOG = LoggerFactory.getLogger(H2AuthorizationDao.class);

    private DataSource dataSource;

    public H2AuthorizationDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Checks if the user's login exists in a database and whether it matches with the password
     * the user has typed in the password field
     * @param email is the login received from the authorization form
     * @param password is the password received from the authorization form
     * @return the id of an authorized user
     */
    @Override
    public long checkCredentials(String email, String password) {
        String preparedQuery = "SELECT password, id FROM Translator WHERE email=?";
        long result = -1;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null)
            {
                while(rs.next())
                {
                    if(rs.getString(1).equals(password))
                    {
                        result = rs.getLong(2);
                    }
                }
            }

        } catch(SQLException e)
        {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        return result;
    }
}
