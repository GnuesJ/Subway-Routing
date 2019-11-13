import java.util.Set;
import java.util.Map;
import java.util.HashMap;


public class Routing {
    private Subway subway;
    private Map<Station,Route> routes = new HashMap<>();

    //Constructor
    public Routing()
    {
        subway = null;
        routes = null;
    }
    public Routing(Subway subway)
    {
        this.subway = subway;
    }
    public Routing(Subway subway, Map<Station,Route> routes)
    {
        this.subway = subway;
        this.routes = routes;
    }
    public Routing(Subway subway, Route route)
    {
        this.subway = subway;
        this.routes.put(route.getStation(),route);
    }


    //getter
    public Map<Station,Route> getRoutes()
    {
        return routes;
    }

    //Mutator
    public void setRoutes(Set<Route> routes)
    {
        Map<Station,Route> tempRoutes = new HashMap<>();
        for(Route e: routes)
        {
            tempRoutes.put(e.getStation(),e);
        }
        this.routes = tempRoutes;
    }
    public void setRoutes(Map<Station,Route> routes)
    {
        this.routes = routes;
    }
    public void addRoute(Route route)
    {
        this.routes.put(route.getStation(), route);
    }


    //Operator
    public boolean containsRoute(Route route)
    {
        for(Map.Entry<Station,Route> e: this.routes.entrySet())
        {
            if(route == e.getValue()) return true;
        }
        return false;
    }
    public boolean containsStation(Station station)
    {
        for(Map.Entry<Station,Route> e: this.routes.entrySet())
        {
            if(station == e.getKey()) return true;
        }
        return false;
    }
}
