import java.io.Serializable;
import java.util.ArrayList;

public class ViewReroutedTrainsResponse extends Response implements Serializable {
    ArrayList<String>trainID,trainName;
    ArrayList<ArrayList<String>>station,cityCode,arrival,departure;
    ArrayList<ArrayList<Integer>>stationNo,dayNo,distanceCovered;

    public ViewReroutedTrainsResponse(ArrayList<String> trainID, ArrayList<String> trainName, ArrayList<ArrayList<String>> station, ArrayList<ArrayList<String>> cityCode, ArrayList<ArrayList<String>> arrival, ArrayList<ArrayList<String>> departure, ArrayList<ArrayList<Integer>> stationNo, ArrayList<ArrayList<Integer>> dayNo, ArrayList<ArrayList<Integer>> distanceCovered) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.station = station;
        this.cityCode = cityCode;
        this.arrival = arrival;
        this.departure = departure;
        this.stationNo = stationNo;
        this.dayNo = dayNo;
        this.distanceCovered = distanceCovered;
    }

    public ArrayList<String> getTrainID() {
        return trainID;
    }

    public ArrayList<String> getTrainName() {
        return trainName;
    }

    public ArrayList<ArrayList<String>> getStation() {
        return station;
    }

    public ArrayList<ArrayList<String>> getCityCode() {
        return cityCode;
    }

    public ArrayList<ArrayList<String>> getArrival() {
        return arrival;
    }

    public ArrayList<ArrayList<String>> getDeparture() {
        return departure;
    }

    public ArrayList<ArrayList<Integer>> getStationNo() {
        return stationNo;
    }

    public ArrayList<ArrayList<Integer>> getDayNo() {
        return dayNo;
    }

    public ArrayList<ArrayList<Integer>> getDistanceCovered() {
        return distanceCovered;
    }
}

class ReroutedLeaf {
    private final String station, cityCode, arrival, departure;
    private final int dayNo, distance, stationNo;

    ReroutedLeaf(String station, String cityCode, String arrival, String departure, int dayNo, int distance, int stationNo) {
        this.station = station;
        this.cityCode = cityCode;
        this.arrival = arrival;
        this.departure = departure;
        this.dayNo = dayNo;
        this.distance = distance;
        this.stationNo = stationNo;
    }

    public String getStation() {
        return station;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public int getDayNo() {
        return dayNo;
    }

    public int getDistance() {
        return distance;
    }

    public int getStationNo() {
        return stationNo;
    }
}