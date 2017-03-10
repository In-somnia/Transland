package dao.h2;

import dao.TranslatorDao;
import model.Education;
import model.Translator;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class H2TranslatorDao implements TranslatorDao {

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public Translator get(long id) {

    Translator translator = null;

    String preparedQuery = "SELECT * FROM Translator WHERE id=?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery))
        {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null)
            {
               while(rs.next())
               {
                   translator = new Translator();
                   translator.setId(rs.getLong(1));
                   translator.setFirstName(rs.getString(2));
                   translator.setLastName(rs.getString(3));
                   translator.setPatronymic(rs.getString(4));
                   translator.setIsTranslator(rs.getBoolean(5));
                   translator.setCity(rs.getString(6));
                   translator.setCell(rs.getString(7));
                   translator.setEmail(rs.getString(8));
                   translator.setPassword(rs.getString(9));
                   PreparedStatement innerPreparedStatement = connection.prepareStatement(
                           "SELECT * FROM Education " +
                                   "WHERE id=?");
                   innerPreparedStatement.setLong(1, rs.getLong(10));
                   ResultSet innerResultSet = innerPreparedStatement.executeQuery();

                   if (innerResultSet != null)
                   {
                       while(innerResultSet.next())
                       {
                           Education education = new Education();
                           education.setCountry(innerResultSet.getString(2));
                           education.setCity(innerResultSet.getString(3));
                           education.setUniversity(innerResultSet.getString(4));
                           education.setDepartment(innerResultSet.getString(5));
                           education.setEducationType(innerResultSet.getBoolean(6));
                           education.setGraduationYear(innerResultSet.getInt(7));

                           translator.setEducation(education);
                       }
                   }

                   translator.setExperience(rs.getString(11));
                   translator.setTopics(rs.getString(12));
                   translator.setInfo(rs.getString(13));
               }
            }

        } catch(SQLException e)
        {
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
        return translator;
    }

    @Override
    public long create(Translator translator) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Translator(first_name, last_name, patronymic, is_translator, city, cell, " +
                        "email, password, education_id, experience, topics, info) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setObject(1, translator.getFirstName());
            preparedStatement.setObject(2, translator.getLastName());
            preparedStatement.setObject(3, translator.getPatronymic());
            preparedStatement.setObject(4, translator.isTranslator());
            preparedStatement.setObject(5, translator.getCity());
            preparedStatement.setObject(6, translator.getCell());
            preparedStatement.setObject(7, translator.getEmail());
            preparedStatement.setObject(6, translator.getPassword()); //hash
            preparedStatement.setObject(6, translator.getEducation().getId());
            preparedStatement.setObject(6, translator.getExperience());
            preparedStatement.setObject(6, translator.getTopics());
            preparedStatement.setObject(6, translator.getInfo());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next())
                translator.setId(generatedKeys.getInt(1));

        } catch (SQLException e)
        {
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
        return translator.getId();
    }


    @Override
    public void remove(Translator translator) {

    }

    @Override
    public List<Translator> getAll() {
        return null;
    }

}
