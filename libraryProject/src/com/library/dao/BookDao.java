package com.library.dao;

import com.library.model.Book;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
                book.setDatePublished(rs.getInt("date_published"));
                list.add(book);
            }

            return list;

        } catch (Exception e) {
            System.out.println("Error BookDao.getUltimos: " + e.getMessage());
            return null;
        }
    }

    public List<Book> getAll() {

        try {
            String sql = "select * from book order by id desc";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Book> list = new LinkedList<>();
            Book book;
            while (rs.next()) {
                book = new Book(rs.getInt("id"));
                book.setDatePublished(rs.getInt("date_published"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                list.add(book);
            }
            return list;

        } catch (SQLException e) {
            System.out.println("Error BookDao.getAll: " + e.getMessage());
            return null;
        }
    }

    public Book getById(int idBook) {
        try {
            String sql = "select * from book where id=? ";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idBook);
            ResultSet rs = preparedStatement.executeQuery();
            Book book = new Book(0);
            while (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setDatePublished(rs.getInt("date_published"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
            }
            return book;

        } catch (SQLException e) {
            System.out.println("Error BookDao.getById: " + e.getMessage());
            return null;
        }
    }

    public boolean insert(Book book) {

        String sql = "insert into book values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,book.getId());
            preparedStatement.setString(2,book.getName());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.setInt(4,  book.getDatePublished());
            preparedStatement.setString(5,book.getDescription());
            preparedStatement.setString(6,book.getDetail());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            System.out.println("Error SolicitudDao.insert: " + e.getMessage());
            return false;
        }
        return true;
    }
}
