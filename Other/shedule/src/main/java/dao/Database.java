package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 21.11.2017.
 */


public class Database {

    private static   Statement statement;

    public Database(Statement statement) {
        this.statement = statement;
    }

    public String getLastDate (int id) throws SQLException {
        String query = "SELECT `date` from date WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            return rs.getString("date");
        }
        return null;
    }

    public void updateLastDate(String lastDate, int id) throws SQLException {
        String query = "update date Set `date` = \"" +lastDate+ "\" where id =" + id;
        statement.executeUpdate(query);
    }

    public void insertNewUser (int user_id) throws SQLException {
        String query = "INSERT INTO `users` (`name`) VALUES ('" +user_id + "')";
        statement.execute(query);
    }




}
