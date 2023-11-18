import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name,Email,BirthDate,BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1,"José Augusto");
            st.setString(2,"jose@gmail.com");
            st.setDate(3,new java.sql.Date(sdf.parse("05/11/1995").getTime()));
            st.setDouble(4,3000.0);
            st.setInt(5,3);

           int rowsAffected = st.executeUpdate();

           if (rowsAffected >0){
               ResultSet rs = st.getGeneratedKeys();
               while (rs.next()){
                   int id = rs.getInt(1);
                   System.out.println("Done! id = " + id);
               }
           }
           else {
               System.out.println("No rown affected!");
           }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}