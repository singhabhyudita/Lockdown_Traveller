import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveSeatsRequestHandler extends Handler {
    RemoveSeatsRequest removeSeatsRequest;
   Connection connection;
    ObjectOutputStream oos;
    public RemoveSeatsRequestHandler(Connection connection, RemoveSeatsRequest removeSeatsRequest,ObjectOutputStream oos) {
        this.connection=connection;
        this.removeSeatsRequest=removeSeatsRequest;
        this.oos=oos;
    }

    @Override
    void sendQuery() {
        String Train_ID=removeSeatsRequest.getTrain_ID();
        String coach=removeSeatsRequest.getCoach()+"_Seats";
        int numOfSeats=removeSeatsRequest.getNumOfSeats();
        String query1="update Basic_Train_Info set " + coach + " = " + coach + "-? where Train_ID=?;";
        RemoveSeatsResponse removeSeatsResponse=removeSeats(query1,numOfSeats,Train_ID,coach);
        Server.SendResponse(oos,removeSeatsResponse);
    }
    public RemoveSeatsResponse removeSeats(String query1,int numOfSeats,String Train_ID,String coach)
    {
        String response;
        PreparedStatement preparedStatement;
        int c=0;
        try {
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1,numOfSeats);
            preparedStatement.setString(2,Train_ID);
            c=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(c!=0){response="success";}
        else {response="failure";}
        return new RemoveSeatsResponse(response);
    }
}
