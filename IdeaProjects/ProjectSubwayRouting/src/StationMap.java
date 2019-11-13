import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


public class StationMap {
    private Map<String, Station> stations;
    private Set<Station> stationsSet;

    //constructor
    public StationMap()
    {
        stations = new HashMap<>();
        stationsSet = new HashSet<>();
    }

    public StationMap(Station station)
    {
        this.stations.put(station.getStationName(),station);
    }

    public StationMap(Set<Station> stations)
    {
        Map<String, Station> tempStations = new HashMap<>();
        for(Station e: stations)
        {
            tempStations.put(e.getStationName(), e);
        }
        this.stations = tempStations;
    }
    public StationMap(Map<String, Station> stations)
    {
        this.stations = stations;
    }


    //Getter
    public Map<String,Station> getStations()
    {
        return stations;
    }
    public Station getStation(String stationName)
    {
        if(hasStation(stationName))
        {
            return stations.get(stationName);
        }
        else return null;
    }
    public Set<Station> getStationsSet()
    {
        return stationsSet;
    }

    //Mutator
    public void addStation(Station station)
    {
        this.stations.put(station.getStationName(), station);
        this.stationsSet.add(station);
    }

    //Operator
    public boolean hasStation(String stationName)
    {
        if(stations == null) return false;
        return stations.containsKey(stationName);
    }
}
