package com.library.dao;

import com.library.model.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class RequestDao {
    private DbConnection conn;

    public RequestDao(DbConnection conn) {
        this.conn = conn;
    }

    public int insert(Request requestModel) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "insert into request values (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, requestModel.getId());
            preparedStatement.setString(2, format.format(requestModel.getDateRequest()));
            preparedStatement.setString(3, requestModel.getName());
            preparedStatement.setString(4, requestModel.getEmail());
            preparedStatement.setString(5, requestModel.getPhone());
            preparedStatement.setString(6, requestModel.getAddress());
            preparedStatement.setString(7, requestModel.getFile());
            preparedStatement.setInt(8, requestModel.getBook().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int idBook = 0;
            if (rs.next()) {
                idBook = rs.getInt(1);
            }
            return idBook;

        } catch (SQLException e) {
            System.out.println("Error SolicitudDao.insert: " + e.getMessage());
            return 0;
        }
    }

    public List<Request> getAll() {

        try {

            String sql = "select * from request order by id desc";

            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Request> list = new LinkedList<>();
            Request request;
            BookDao bookDao = new BookDao(conn);
            while (rs.next()) {
                request = new Request(rs.getInt("id"));
                request.setDateRequest(rs.getDate("date_request"));
                request.setName(rs.getString("name"));
                request.setEmail(rs.getString("email"));
                request.setPhone(rs.getString("phone"));
                request.setAddress(rs.getString("address"));
                request.setFile(rs.getString("file"));
                request.setBook( bookDao.getById(rs.getInt("idBook")) );
                // Add servidor object to the list
                list.add(request);

            }

            return list;

        } catch (SQLException e) {
            System.out.println("Error RequestDao.getAll: "+e.getMessage());
            return null;
        }
    }
}
