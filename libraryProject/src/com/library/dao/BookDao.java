package com.library.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.library.model.Book;
public class BookDao {
    private DbConnection conn;

    public BookDao() {
      
    }
    public BookDao(DbConnection conn) {
        this.conn = conn;
    }
	
    public List<Book> getUltimos() {
 
        try {

            List<Book> list = new LinkedList<>();
          	 Book book = new Book("Code clean");
             Book book2 = new Book("Java 8");
             Book book3 = new Book("SQL");
            list.add(book);
            list.add(book2);
            list.add(book3);
                System.out.println(list);
            
            return list;

        } catch (Exception e) {            
            System.out.println("Error BookDao.getUltimos: " + e.getMessage());
            return null;
        }
    }
}
