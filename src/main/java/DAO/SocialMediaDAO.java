package DAO;

public class SocialMediaDAO {

public Flight insertFlight(Flight flight){
    Connection connection = ConnectionUtil.getConnection();
    try {
        //Write SQL logic here. When inserting, you only need to define the departure_city and arrival_city
        //values (two columns total!)
        String sql = "INSERT INTO flight (departure_city, arrival_city) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        //write preparedStatement's setString and setInt methods here.
        preparedStatement.setString(1, flight.departure_city);
        preparedStatement.setString(2, flight.arrival_city);


        preparedStatement.executeUpdate();
        ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
        if(pkeyResultSet.next()){
            int generated_flight_id = (int) pkeyResultSet.getLong(1);
            return new Flight(generated_flight_id, flight.getDeparture_city(), flight.getArrival_city());
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}
}