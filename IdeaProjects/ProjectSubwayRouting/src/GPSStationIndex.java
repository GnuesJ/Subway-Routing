import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class GPSStationIndex {
    private Station beginStation;
    private Map<Station, GPSSubwayIndex> stationIndex;

    public GPSStationIndex(){}

    public GPSStationIndex(Station beginStation, Map<String, Station> stations)
    {
        stationIndex = new HashMap<>();
        this.beginStation = beginStation;
        //Set<Station> staionsss = new HashSet<>();

        for(Station endStation: stations.values())
        {
            if(beginStation != endStation && commonSubway(beginStation, endStation)) {
                stationIndex.put(endStation, new GPSSubwayIndex(beginStation, endStation));
                //System.out.println("Y-AXIS " + "end station " + endStation.getStationName());

            }
        }
    }
    public GPSStationIndex(Station beginStation, Set<Station> stations)
    {
        stationIndex = new HashMap<>();
        this.beginStation = beginStation;
        //Set<Station> staionsss = new HashSet<>();

        for(Station endStation: stations)
        {
            if(beginStation != endStation && checkNext(beginStation, endStation)) {
                stationIndex.put(endStation, new GPSSubwayIndex(beginStation, endStation));
                //System.out.println("Y-AXIS " + "end station " + endStation.getStationName());
//                if(endStation.getStationName().equals("23 Street / 8 Avenue"))
//                    System.out.println("Y-axis added " + endStation.getStationName() + " at " + beginStation.getStationName());
            }
        }
    }

    public Set<Station> getStations()
    {
        return stationIndex.keySet();
    }
    public GPSSubwayIndex getGPSSubwayIndex(Station station)
    {
        try
        {
            return stationIndex.get(station);
        }
        catch(NoSuchElementException e)
        {
            e.getMessage();
        }
        return null;
    }

    private boolean commonSubway(Station beginStation, Station endStation)
    {
        for(Subway subway: beginStation.getSubways())
        {
            if(endStation.getSubways().contains(subway)) return true;
        }
        return false;
    }

    private boolean checkNext(Station beginStation, Station endStation)
    {
        for(Subway subway: beginStation.getSubways())
        {
            if(subway.getRouting().containsStation(beginStation))
            {
                Route tempRoute = subway.getRouting().getRoutes().get(beginStation);
                if(tempRoute.getNext() != null && tempRoute.getNext().getStation() == endStation) return true;
                tempRoute = subway.getRouting().getRoutes().get(beginStation);
                if(tempRoute.getPrev() != null && tempRoute.getPrev().getStation() == endStation) return true;
            }
        }
        return false;
    }
}
