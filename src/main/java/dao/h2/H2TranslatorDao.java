package dao.h2;

import dao.TranslatorDao;
import model.Education;
import model.EducationForm;
import model.Translator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class H2TranslatorDao implements TranslatorDao {

    private DataSource dataSource;

    public H2TranslatorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Translator get(long id) {

        Translator translator = null;

        String preparedQuery = "SELECT * FROM Translator WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    translator = new Translator();
                    translator.setId(rs.getLong(1));
                    translator.setFirstName(rs.getString(2));
                    translator.setLastName(rs.getString(3));
                    translator.setPatronymic(rs.getString(4));
                    translator.setIsTranslator(rs.getBoolean(5));
                    translator.setCity(rs.getString(6));
                    translator.setCell(rs.getString(7));
                    translator.setEmail(rs.getString(8));

                    PreparedStatement innerPreparedStatement = connection.prepareStatement(
                            "SELECT * FROM Education " +
                                    "WHERE id=?");
                    innerPreparedStatement.setLong(1, rs.getLong(9));
                    ResultSet innerResultSet = innerPreparedStatement.executeQuery();

                    if (innerResultSet != null) {
                        while (innerResultSet.next()) {
                            Education education = new Education();
                            education.setId(innerResultSet.getLong(1));
                            education.setUniversity(innerResultSet.getString(2));
                            education.setDepartment(innerResultSet.getString(3));
                            education.setEducationType(EducationForm.valueOf(innerResultSet.getString(4)));
                            education.setGraduationYear(innerResultSet.getInt(5));

                            translator.setEducation(education);
                        }
                    }
                    translator.setPassword(rs.getString(10));
                    translator.setExperience(rs.getString(11));
                    translator.setInfo(rs.getString(12));
                    translator.setIsRemoved(rs.getBoolean(13));
                }
            }

        } catch (SQLException e) {
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
        return translator;
    }

    @Override
    public long create(Translator translator) {
        String preparedQuery = "INSERT INTO Education (university, department, education_type, graduation_date) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, translator.getEducation().getUniversity());
            preparedStatement.setString(2, translator.getEducation().getDepartment());
            preparedStatement.setObject(3, translator.getEducation().getEducationType().toString());
            preparedStatement.setInt(4, translator.getEducation().getGraduationYear());

            preparedStatement.executeUpdate();

            String preparedInnerQuery = "INSERT INTO Translator(first_name, last_name, patronymic, is_translator, city, cell, " +
                    "email, password, education_id, experience, info, isRemoved) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (ResultSet rs = preparedStatement.getGeneratedKeys();
                 PreparedStatement preparedInnerStatement = connection.prepareStatement(preparedInnerQuery,
                         PreparedStatement.RETURN_GENERATED_KEYS)){
                if (rs.next()) {
                    preparedInnerStatement.setObject(1, translator.getFirstName());
                    preparedInnerStatement.setObject(2, translator.getLastName());
                    preparedInnerStatement.setObject(3, translator.getPatronymic());
                    preparedInnerStatement.setObject(4, translator.isTranslator());
                    preparedInnerStatement.setObject(5, translator.getCity());
                    preparedInnerStatement.setObject(6, translator.getCell());
                    preparedInnerStatement.setObject(7, translator.getEmail());
                    preparedInnerStatement.setObject(8, translator.getPassword());
                    preparedInnerStatement.setObject(9, rs.getLong(1));
                    preparedInnerStatement.setObject(10, translator.getExperience());
                    preparedInnerStatement.setObject(11, translator.getInfo());
                    preparedInnerStatement.setObject(12, translator.isRemoved());
                    preparedInnerStatement.executeUpdate();
                }
                ResultSet generatedKeys = preparedInnerStatement.getGeneratedKeys();
                if (generatedKeys.next())
                    translator.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
        return translator.getId();
    }

    @Override
    public boolean checkIsRemoved(long id) {
        boolean result = false;
        String preparedQuery = "SELECT isRemoved FROM Translator WHERE id=?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                result = rs.getBoolean(1);
            }
        } catch(SQLException e) {
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
        return result;
    }

    public boolean removePage(long id) {
        boolean param = true;
        updateIsRemovedInDB(id, param);
        return true;
    }
    public boolean restorePage(long id) {
        boolean param = false;
        updateIsRemovedInDB(id, param);
        return false;
    }

    private void updateIsRemovedInDB(long id, boolean param) {
        String preparedQuery = "UPDATE Translator SET isRemoved=? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setBoolean(1, param);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.err.println("SQLException message:" + e.getMessage());
            System.err.println("SQLException SQL state:" + e.getSQLState());
            System.err.println("SQLException SQL error code:" + e.getErrorCode());
        }
    }

    @Override
    public List<Translator> getAll() {
        return null;
    }

}
