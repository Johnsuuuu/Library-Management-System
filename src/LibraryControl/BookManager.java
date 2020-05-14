package LibraryControl;

import java.sql.*;
import LibraryBean.BookBean;
import util.*;
import java.util.List;
import java.util.ArrayList;


public class BookManager {

    public boolean addBook(String bookname,String author,float price,String bookdescription,int booktypeid){
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            conn.setAutoCommit(false);
            String sql="insert into beanbook(bookname,author,price,booktypeid,bookdescription) values(?,?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, bookname);
            pst.setString(2, author);
            pst.setFloat(3, price);
            pst.setInt(4, booktypeid);
            pst.setString(5, bookdescription);
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

    public List<BookBean> loadSelectedBook(String bookname,String author,String booktype){
        List<BookBean> result = new ArrayList<BookBean>();
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            String sql = "SELECT * FROM beanbook WHERE bookname=? and author=? and booktypeid=(SELECT booktypeid from beanbooktype where booktypename=?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, bookname);
            pst.setString(2, author);
            pst.setString(3, booktype);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BookBean b = new BookBean();
                b.setBookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getFloat(4));
                b.setBookTypeId(rs.getInt(5));
                b.setBookDescription(rs.getString(6));
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


    public List<BookBean> loadAllBook(){
        List<BookBean> result = new ArrayList<BookBean>();
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            String sql = "SELECT * FROM beanbook";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BookBean b = new BookBean();
                b.setBookId(rs.getInt(1));
                b.setBookName(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setPrice(rs.getFloat(4));
                b.setBookTypeId(rs.getInt(5));
                b.setBookDescription(rs.getString(6));
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

    public String[][] ListToArray(List<BookBean> list){
        String[][] array = new String[list.size()][6];
        for(int i = 0; i < list.size(); i++){
            BookBean b = list.get(i);
            array[i][0] = String.valueOf(b.getBookId());
            array[i][1] = b.getBookName();
            array[i][2] = b.getAuthor();
            array[i][3] = String.valueOf(b.getPrice());
            array[i][4] = String.valueOf(b.getBookTypeId());
            array[i][5] = String.valueOf(b.getBookDescription());
        }

        return array;
    }

    public boolean updateBook(String bookid, String bookname,String author, Float price, String booktype, String bookdescription){
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            conn.setAutoCommit(false);
            String sql="update beanbook set bookname=?,author=?,price=?,booktypeid=(select booktypeid from beanbooktype where booktypename=?),bookdescription=? where bookid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, bookname);
            pst.setString(2, author);
            pst.setFloat(3, price);
            pst.setString(4,booktype);
            pst.setString(5,bookdescription);
            pst.setInt(6,Integer.parseInt(bookid));
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

    public boolean deleteBook(String bookid){
        Connection conn=null;
        try{
            conn= LibraryJDBC.getConnection();
            conn.setAutoCommit(false);
            String sql="delete from beanbook where bookid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(bookid));
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




}
