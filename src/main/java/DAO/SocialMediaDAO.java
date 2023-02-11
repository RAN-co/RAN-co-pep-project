package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

public class SocialMediaDAO {

public Account addAccount(Account account){
    Connection connection = ConnectionUtil.getConnection();
    try {
        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, account.username);
        preparedStatement.setString(2, account.password);

        preparedStatement.executeUpdate();
        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        if(pkeyResultSet.next()){
            int generated_account_id = (int) pkeyResultSet.getLong(1);
            return new Account(generated_account_id, account.getUsername(), account.getPassword());
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}

public Account userAuthorization(String username, String password) {
    Connection connection = ConnectionUtil.getConnection();
    try {

        String sql = "SELECT * FROM account WHERE username, password VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Account account = new Account(rs.getString("username"),
                    rs.getString("password"));
            return account;
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return null;
}
/*
public Message addNewMessage(Message message){
    Connection connection = ConnectionUtil.getConnection();
    try {
        
        String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) values(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, message.posted_by);
        preparedStatement.setString(2, message.message_text);
        preparedStatement.setLong(3, message.time_posted_epoch);

        preparedStatement.executeUpdate();
        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        if(pkeyResultSet.next()){
            int generated_message_id = (int) pkeyResultSet.getLong(1);
            return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}
*/
public List<Message> getAllMessages(){
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try {
        //Write SQL logic here
        String sql = "SELECT * FROM message";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
            messages.add(message);
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;
}

public Message getMessageById(int message_id){
    Connection connection = ConnectionUtil.getConnection();
    try {

        String sql = "SELECT * FROM message WHERE message_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, message_id);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
            return message;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}



}