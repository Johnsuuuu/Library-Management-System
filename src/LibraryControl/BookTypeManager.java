package LibraryControl;

import java.sql.*;
import LibraryBean.BookTypeBean;
import util.*;
import java.util.List;
import java.util.ArrayList;

public class BookTypeManager {

    public boolean addBookType(String booktypename,String booktypedescription){
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            conn.setAutoCommit(false);
            String sql="insert into beanbooktype(booktypename,booktypedescription) values(?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, booktypename);
            pst.setString(2, booktypedescription);
            pst.execute();
            pst.close();
            conn.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.rollback();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return false;

    }

    public boolean updateBookType(String booktypeid, String booktypename,String booktypedescription){
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            conn.setAutoCommit(false);
            String sql="update beanbooktype set booktypename=?,booktypedescription=? where booktypeid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, booktypename);
            pst.setString(2, booktypedescription);
            pst.setInt(3,Integer.parseInt(booktypeid));
            pst.execute();
            pst.close();
            conn.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.rollback();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }


    public boolean deleteBookType(String booktypeid){
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            conn.setAutoCommit(false);
            String sql="delete from beanbooktype where booktypeid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(booktypeid));
            pst.execute();
            pst.close();
            conn.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.rollback();
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }



    public List<BookTypeBean> loadSelectedBookType(String booktypename){
        List<BookTypeBean> result = new ArrayList<BookTypeBean>();
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            String sql = "select * from beanbooktype where booktypename=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, booktypename);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BookTypeBean b = new BookTypeBean();
                b.setBookTypeId(rs.getInt(1));
                b.setBookTypeName(rs.getString(2));
                b.setBookTypeDescription(rs.getString(3));
                result.add(b);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }


    public List<BookTypeBean> loadAllBookType(){
        List<BookTypeBean> result = new ArrayList<BookTypeBean>();
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            String sql = "select * from beanbooktype";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BookTypeBean b = new BookTypeBean();
                b.setBookTypeId(rs.getInt(1));
                b.setBookTypeName(rs.getString(2));
                b.setBookTypeDescription(rs.getString(3));
                result.add(b);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }




    public String[][] ListToArray(List<BookTypeBean> list){
        String[][] array = new String[list.size()][3];
        for(int i = 0; i < list.size(); i++){
            BookTypeBean b = list.get(i);
            array[i][0] = String.valueOf(b.getBookTypeId());
            array[i][1] = b.getBookTypeName();
            array[i][2] = b.getBookTypeDescription();
        }

        return array;
    }







}
