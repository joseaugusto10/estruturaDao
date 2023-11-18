package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    //criação de objeto de conexão com banco de dados do JDBC
    private static Connection conn = null;

    //Método para conectar o banco de dados

    public static Connection getConnection(){
       try {
           if (conn == null) {
               Properties props = loadProperties();
               String url = props.getProperty("dburl");
               conn = DriverManager.getConnection(url, props);
           }
       } catch (SQLException e){
           throw new DbException(e.getMessage());
       }
        return conn;
    }


    //Método para fechar conexão
    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    //Método para carregar os dados do arquivo db.properties
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

}//Chave da classe
