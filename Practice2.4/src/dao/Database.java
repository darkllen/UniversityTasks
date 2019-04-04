package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static javax.swing.UIManager.getInt;

/**
 * Created by user on 21.11.2017.
 */


public class Database {

    private static   Statement statement;

    public Database(Statement statement) {
        this.statement = statement;
    }

    /**
     *
     * @return bytes of last string
     * @throws SQLException
     */
    public byte[] getStr () throws SQLException {
        ResultSet r  = statement.executeQuery("SELECT `num` from str where id="+1);
        int n = 0;
        while (r.next()){
            n = r.getInt("num");
        }
        String query = "SELECT `str_code` from str where num ="+n;
        ResultSet rs = statement.executeQuery(query);

        ArrayList<Byte> arrayList = new ArrayList<>();

        while (rs.next()){
            arrayList.add(rs.getByte("str_code"));
        }

        byte[] u = new byte[arrayList.size()];
        for (int i =0;i<arrayList.size();i++){
            u[i] = arrayList.get(i);
        }
        return u;
    }

    /**
     * set new string as a bytes[]
     * @param bytes
     * @throws SQLException
     */
    public void updateLastStr(byte[] bytes) throws SQLException {
        for (int i = 0;i<bytes.length;i++){
            if (statement.executeQuery("SELECT id from str where id="+(i+1)).next()){
                String query = "update str Set `str_code` = \"" +bytes[i]+ "\", `num`=\""+bytes.length+"\" where id =" + (i+1);
                statement.executeUpdate(query);
            } else {
                String query = "insert into `str` (`str_code`, `num`) values ("+bytes[i]+","+bytes.length+")";
                statement.execute(query);
            }

        }


    }




}
