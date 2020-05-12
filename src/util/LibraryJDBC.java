package util;

import java.sql.*;

public class LibraryJDBC {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/mylibrary";
    private static final String userName = "root";
    private static final String password = "1702";
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(jdbcUrl,userName,password);
    }

    public static void main(String[] args){
        Connection conn = null;
        try{
            conn = LibraryJDBC.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            if(conn!=null){
                try{
                    conn.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
