import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisplayTrainsRequestHandler extends Handler {
    DatabaseConnector db;
    DisplayTrainsRequest displayTrainsRequest;
    ObjectOutputStream oos;
    DisplayTrainsRequestHandler(DatabaseConnector db, DisplayTrainsRequest displayTrainsRequest, ObjectOutputStream oos)
    {
        this.db=db;
        this.displayTrainsRequest=displayTrainsRequest;
        this.oos=oos;
    }

    @Override
    void sendQuery() throws IOException {
        System.out.println("Inside Handler's get response method");
        String source=displayTrainsRequest.getSource();
        String dest=displayTrainsRequest.getDest();
        String sDate=displayTrainsRequest.getsDate();
        //converting the string date into the date datatype of mySQL
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtf2=  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        sDate= LocalDate.parse(sDate,dtf).format(dtf2);

        //create a query to find the trains between source and destination
        String query1 = "select x.*, y.Days_Running from\n" +
                "(select a.Train_ID, a.Train_Name, b.Arrival, a.Departure, a.Day_No from \n" +
                "(select * from route_info\n" +
                "where Station=\"" + source + "\") as a\n" +
                "join \n" +
                "(select * from route_info\n" +
                "where Station=\"" + dest + "\") as b\n" +
                "where a.Train_ID = b.Train_ID) as x\n" +
                "join \n" +
                "basic_train_info as y\n" +
                "on x.Train_ID = y.Train_ID;\n" +
                "\n";
        //create query to find total seats in each class
        String query2="select Sleeper_Coaches,Sleeper_Seats,FirstAC_Coaches,FirstAC_Seats,SecondAC_Coaches,SecondAC_Seats,ThirdAC_Coaches,ThirdAC_Seats from basic_train_info where Train_ID=\""+"xxxxx"+"\";";
        // create query to find booked seats between the given stations for a particular train on a particular day
        String query3="select count(Booking_ID) from Booking_Info where Booking_Status<>'Cancelled' and Booking_ID in(select distinct Booking_ID from vacancy_info where Train_ID=\""+"xxxxx"+"\"Station_No in (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station_No between (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+source+"\") and (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+dest+"\") and Date=\""+sDate+"\"and Seat_No like 'SL%'));";
        String query4="select count(Booking_ID) from Booking_Info where Booking_Status<>'Cancelled' and Booking_ID in(select distinct Booking_ID from vacancy_info where Train_ID=\""+"xxxxx"+"\"Station_No in (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station_No between (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+source+"\") and (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+dest+"\") and Date=\""+sDate+"\"and Seat_No like '1A%'));";
        String query5="select count(Booking_ID) from Booking_Info where Booking_Status<>'Cancelled' and Booking_ID in(select distinct Booking_ID from vacancy_info where Train_ID=\""+"xxxxx"+"\"Station_No in (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station_No between (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+source+"\") and (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+dest+"\") and Date=\""+sDate+"\"and Seat_No like '2A%'));";
        String query6="select count(Booking_ID) from Booking_Info where Booking_Status<>'Cancelled' and Booking_ID in(select distinct Booking_ID from vacancy_info where Train_ID=\""+"xxxxx"+"\"Station_No in (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station_No between (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+source+"\") and (select Station_No from route_info where Train_ID=\""+"xxxxx"+"\" and Station=\""+dest+"\") and Date=\""+sDate+"\"and Seat_No like '3A%'));";
        String query7="select Added_Till,Cancelled_Till from basic_train_info where Train_ID=\""+"xxxxx"+"\";";
        DisplayTrainsResponse displayTrainsResponse=db.DisplayTrains(query1,query2,query3,query4,query5,query6,query7,sDate,source,dest);
        Server.SendResponse(oos,displayTrainsResponse);
    }
    }

