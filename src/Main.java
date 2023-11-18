import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;

        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("select * from department");

            while (rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("name"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeConnection();
            DB.closeStatement(st);
        }

    }
}