package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class SocialMediaDAO {

public Account addAccount(Account account){
    Connection connection = ConnectionUtil.getConnection();
    try {
        //Write SQL logic here. When inserting, you only need to define the departure_city and arrival_city
        //values (two columns total!)
        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        //write preparedStatement's setString and setInt methods here.
        preparedStatement.setString(1, account.username);
        preparedStatement.setString(2, account.password);


        preparedStatement.executeUpdate();
        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        if(pkeyResultSet.next()){
            int generated_flight_id = (int) pkeyResultSet.getLong(1);
            return new Account(generated_flight_id, account.getUsername(), account.getPassword());
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}
}