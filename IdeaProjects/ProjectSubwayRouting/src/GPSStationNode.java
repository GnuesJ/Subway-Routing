public class GPSStationNode {
    private Station station;
    private GPSStationNode next;
    private GPSStationNode prev;

    public GPSStationNode()
    {
        station = null;
        next = null;
        prev = null;
    }
    public GPSStationNode(Station station)
    {
        this.station = station;
        next = null;
        prev = null;
    }

    public void setStation(Station station)
    {
        this.station = station;
    }
    public void setNext(GPSStationNode next)
    {
        this.next = next;
    }
    public void setPrev(GPSStationNode prev)
    {
        this.prev = prev;
    }

    public Station getStation()
    {
        return station;
    }
    public GPSStationNode getNext()
    {
        return next;
    }
    public GPSStationNode getPrev()
    {
        return prev;
    }
}
