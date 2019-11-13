import java.util.Set;
import java.util.HashSet;

public class Station{
    private String stationName;
    private Set<Subway> subways;
    private Station next;
    private Station prev;


    //Constructor
    public Station()
    {
        stationName = null;
        subways = new HashSet<>();
        next = null;
        prev = null;
    }

    public Station(String stationName)
    {
        this.stationName = stationName;
        this.subways = new HashSet<>();
        next = null;
        prev = null;
    }

    public Station(String stationName, Station next, Station prev)
    {
        this.stationName = stationName;
        this.subways = null;
        this.next = next;
        this.prev = prev;
    }

    public Station(String stationName, Subway subway, Station next, Station prev)
    {
        this.stationName = stationName;
        this.subways.add(subway);
        this.next = next;
        this.prev = prev;
    }

    public Station(String stationName, HashSet<Subway> subways, Station next, Station prev)
    {
        this.stationName = stationName;
        this.subways = subways;
        this.next = next;
        this.prev = prev;
    }


    //getter
    public String getStationName(){return stationName;}

    public Set<Subway> getSubways(){return subways;}

    public Station getNext(){return next;}

    //mutator
    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public void setSubways(HashSet<Subway> subways)
    {
        this.subways = subways;
    }

    public void addsubway(Subway subway)
    {
        this.subways.add(subway);
    }

    public void setNext(Station next)
    {
        this.next = next;
    }

    public void setPrev(Station prev) {this.prev = prev;}
}
