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
            String sql = "select * from book order by id desc limit 3";
            List<Book> list = new LinkedList<>();
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            Book book;
            while (rs.next()) {
                book = new Book(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setDatePublished(rs.getDate("date_published"));
                list.add(book);
            }

            return list;

        } catch (Exception e) {
            System.out.println("Error BookDao.getUltimos: " + e.getMessage());
            return null;
        }
    }

    public List<Book> getAll(){

        try {
            String sql = "select * from book order by id desc";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Book> list = new LinkedList<>();
            Book book;
            while (rs.next()) {
                book = new Book(rs.getInt("id"));
                book.setDatePublished(rs.getDate("date_published"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                //book.set(rs.getString("detalle"));
                list.add(book);
            }
            return list;

        } catch (SQLException e) {
            System.out.println("Error BookDao.getAll: " + e.getMessage());
            return null;
        }
    }
}
