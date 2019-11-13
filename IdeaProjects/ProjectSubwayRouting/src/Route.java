public class Route {
    private Station station;
    private Route next;
    private Route prev;

    //Constructor
    public Route()
    {
        station = null;
        next = null;
        prev = null;
    }
    public Route(Station station)
    {
        this.station = station;
        next = null;
        prev = null;
    }
    public Route(Station station, Route next)
    {
        this.station = station;
        this.next = next;
        prev = null;
    }
    public Route(Station station, Route next, Route prev)
    {
        this.station = station;
        this.next = next;
        this.prev = prev;
    }

    //getter
    public Station getStation(){return station;}
    public Route getNext(){return next;}
    public Route getPrev(){return prev;}

    //mutator
    public void setStation(Station station)
    {
        this.station = station;
    }
    public void setNext(Route next)
    {
        this.next = next;
    }
    public void setPrev(Route prev)
    {
        this.prev = prev;
    }

}
