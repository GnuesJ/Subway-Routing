import java.util.List;
import java.util.LinkedList;

public class GPSOptimalList {
    private List<Station> stations;
    private int steps;

    //Constructor
    public GPSOptimalList()
    {
        stations = new LinkedList<>();
    }
    public GPSOptimalList(Station station , int steps)
    {
        this.stations = new LinkedList<>();
        stations.add(station);
        this.steps = steps;
    }


    //Mutator
    public void addList(List<Station> stations)
    {
        this.stations.addAll(stations);
    }
    public void addSteps(int steps)
    {
        this.steps+= steps;
    }

    //getter
    public List<Station> getStations()
    {
        return stations;
    }
    public int getSteps()
    {
        return steps;
    }
}
