package dao.h2;

import dao.AuthorizationDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2AuthorizationDao implements AuthorizationDao {

    private DataSource dataSource;

    public H2AuthorizationDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long checkCredentials(String email, String password) {
        String preparedQuery = "SELECT password, id FROM Translator WHERE email=?";
        long result = 0;
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
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
        return result;
    }
}
