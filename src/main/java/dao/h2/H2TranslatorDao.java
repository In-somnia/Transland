package dao.h2;


import dao.TranslatorDao;
import model.Education;
import model.EducationForm;
import model.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class H2TranslatorDao implements TranslatorDao {
    private static final Logger LOG = LoggerFactory.getLogger(H2TranslatorDao.class);
    private DataSource dataSource;
    @SuppressWarnings("WeakerAccess")
    public H2TranslatorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
/**
 *Allows to get a user from database table
 *
 * @param id is used to find out the necessary user
 * @return the object of a Translator class (user model)
**/
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

                    Education education = getEducationFromDb(innerResultSet);
                    translator.setEducation(education);

                    translator.setPassword(rs.getString(10));
                    translator.setExperience(rs.getString(11));
                    translator.setInfo(rs.getString(12));
                    translator.setIsRemoved(rs.getBoolean(13));
                }
            }
        } catch (SQLException e) {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        return translator;
    }

    /**
     * Puts an object of Translator into a database
     * @param translator is the object from which the method gets the necessary parameters
     * @return the id of the added object
    **/
    @Override
    public long create(Translator translator) {
        String preparedQuery = "INSERT INTO Education (university, department, education_type, graduation_date) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, translator.getEducation().getUniversity());
            preparedStatement.setString(2, translator.getEducation().getDepartment());
            preparedStatement.setObject(3, translator.getEducation().getEducationType().getValue());
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
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        return translator.getId();
    }

    /**
     * Checks if the user's page has been deleted
     * @param id defines the user whose page is to be checked
     * @return true if the page has been deleted and vice versa
     */
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
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        return result;
    }

    /**
     * puts the attribute showing that the user's page has been deleted into a database
     * @param id defines the user
     * @return the value of the added attribute (always true)
     */
    public boolean removePage(long id) {
        boolean param = true;
        //noinspection ConstantConditions
        updateIsRemovedInDB(id, param);
        return true;
    }

    /**
     * puts the attribute showing that the user's page has been restored into a database
     * @param id defines the user
     * @return the value of the added attribute (always false)
     */
    public boolean restorePage(long id) {
        boolean param = false;
        //noinspection ConstantConditions
        updateIsRemovedInDB(id, param);
        return false;
    }

    /**
     * Updates the changed profile data in a database
     * @param translator is the object from which the necessary parameters are taken
     */
    public void editTranslatorData(Translator translator) {
        String preparedTranslatorQuery ="UPDATE Translator SET city=?, cell=? WHERE id=?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(preparedTranslatorQuery)) {
            preparedStatement.setString(1, translator.getCity());
            preparedStatement.setString(2, translator.getCell());
            preparedStatement.setLong(3, translator.getId());

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        String preparedEducationQuery ="UPDATE Education SET university=?, department=?, education_type=?, graduation_date=? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedEducationQuery)) {
            preparedStatement.setString(1, translator.getEducation().getUniversity());
            preparedStatement.setString(2, translator.getEducation().getDepartment());
            preparedStatement.setString(3, translator.getEducation().getEducationType().getValue());
            preparedStatement.setInt(4, translator.getEducation().getGraduationYear());
            preparedStatement.setLong(5, translator.getEducation().getId());

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
    }

    /**
     * Allows to get id's of all the users in a database except the user's own id
     * @param myId shows the id to be excluded from the list of returned id's
     * @return the list of id's
     */
    @Override
    public List<Long> getAllButSelfId(long myId) {
        List<Long> translatorIds = new ArrayList<>();
        String query = "SELECT id FROM Translator where id!=?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(query)) {
        preparedstatement.setLong(1, myId);
        ResultSet resultSet = preparedstatement.executeQuery();
            while(resultSet.next()) {
                translatorIds.add(resultSet.getLong(1));
            }
        } catch(SQLException e) {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        return translatorIds;
    }

    /**
     * Counts the number of pages which is necessary to display all search results,
     * taking into account the number of search data on one page
     * @param list takes the list of id's meeting the user's search criteria
     * @return the amount of pages necessary to display all found results
     */
    public long pageCounter(List<Long> list)
    {
        long amountOfPages = 0;

        if (list.size()%4 == 0) {
            amountOfPages = (long) list.size()/4;
        } else if (list.size()%4 > 0) {
            amountOfPages = (long) list.size()/4 + 1;
        }

        if (amountOfPages < 1) {
            amountOfPages = 1;
        }

        return amountOfPages;
    }

    /**
     * Allows to build a single database query from all search criteria the user has selected
     * @param map is the number of separate search parameters and their values
     * @return a list of id's matching the final query
     */
    public List<Long> findColleaguesInDb(Map<Object, Object> map, long myId) {
        List<String> listOfQueries = new ArrayList<>();
        List<Long> listOfIds = new ArrayList<>();

        for (Map.Entry<Object, Object> pair : map.entrySet()) {
            Object key = pair.getKey();
            Object value = pair.getValue();

            String queryParam = key + "=" + value;
            listOfQueries.add(queryParam);
        }

        String querySum = "";

        for (String query : listOfQueries) {
            querySum = querySum + query + " AND ";
        }

        String finalQuery = querySum.trim().substring(0, querySum.length()-5);
        String query = "SELECT id FROM Translator WHERE " + finalQuery;

         try(Connection connection = dataSource.getConnection();
         Statement statement = connection.createStatement()) {
         ResultSet resultSet = statement.executeQuery(query);
             while (resultSet.next()) {
                 if (resultSet.getLong(1) != myId )
                     listOfIds.add(resultSet.getLong(1));
             }
         }catch(SQLException e) {
             LOG.debug("SQLException message:" + e.getMessage());
             LOG.debug("SQLException SQL state:" + e.getSQLState());
             LOG.debug("SQLException SQL error code:" + e.getErrorCode());
         }
        return listOfIds;
    }

    /**
     * Calculates the numbers of users which are to be displayed on the given page,
     * taking into consideration the amount of search data on every page and page number
     * @param resultsPerPage is a constant showing the amount of search data on every page
     * @param pageNumber is the page for which the result is to be calculated
     * @param listOfIds all id's matching the user's search criteria
     * @return models of users to be displayed on the given page, taken from a database
     */
    public List<Translator> getCurrentPageTranslators(int resultsPerPage, int pageNumber, List<Long> listOfIds) {
        List<Translator> currentPageTranslators = new ArrayList<>();

        for (int i = 0; i < resultsPerPage; i++) {
            int colleagueId =  ((resultsPerPage*(pageNumber - 1)) + i);
            if (colleagueId <= listOfIds.size() - 1)
            {
                long queriedId = listOfIds.get(colleagueId);
                Translator translator = get(queriedId);
                currentPageTranslators.add(translator);
            }
        }
       return currentPageTranslators;
    }

    private void updateIsRemovedInDB(long id, boolean param) {
        String preparedQuery = "UPDATE Translator SET isRemoved=? WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery)) {
            preparedStatement.setBoolean(1, param);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
    }

    private Education getEducationFromDb(ResultSet resultSet) {
        Education education = null;

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    education = new Education();
                    education.setId(resultSet.getLong(1));
                    education.setUniversity(resultSet.getString(2));
                    education.setDepartment(resultSet.getString(3));
                    education.setEducationType(EducationForm.valueOf(resultSet.getString(4)));
                    education.setGraduationYear(resultSet.getInt(5));
                }
            }
        } catch (SQLException e) {
            LOG.debug("SQLException message:" + e.getMessage());
            LOG.debug("SQLException SQL state:" + e.getSQLState());
            LOG.debug("SQLException SQL error code:" + e.getErrorCode());
        }
        return education;
    }
}
