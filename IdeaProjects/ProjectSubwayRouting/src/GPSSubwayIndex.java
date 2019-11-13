import java.util.List;
import java.util.ArrayList;

public class GPSSubwayIndex {
    private List<Subway> subways;
    private int steps;
    private Station beginStation;
    private Station endStation;

    public GPSSubwayIndex(){
        subways = null;
        beginStation = null;
        endStation = null;

    }


    public GPSSubwayIndex(Station beginStation, Station endStation)
    {
        subways = new ArrayList<>();
        steps = 99;
        this.beginStation = beginStation;
        this.endStation = beginStation;
        addSubways(beginStation,endStation);
    }

    //Getter
    public List<Subway> getSubways()
    {
        return subways;
    }
    public int getSteps()
    {
        return steps;
    }


    //Operator
    public void addSubways(Station beginStation, Station endStation)
    {
        for(Subway subway: beginStation.getSubways())
        {
            int resultSteps = 0;
            Route tempRoute = subway.getRouting().getRoutes().get(beginStation);
            Route endRoute = subway.getRouting().getRoutes().get(endStation);
            while(tempRoute!=null)
            {
                if(tempRoute == endRoute)
                {
                    break;
                }
                tempRoute = tempRoute.getNext();
                ++resultSteps;
            }
            if(tempRoute == null)
            {
                resultSteps = 0;
                tempRoute = subway.getRouting().getRoutes().get(beginStation);
                while(tempRoute!=null)
                {
                    if(tempRoute == endRoute) break;
                    tempRoute = tempRoute.getPrev();
                    ++resultSteps;
                }
            }
            if(steps > resultSteps)
            {
                subways = new ArrayList<>();
                subways.add(subway);
            }
            if(steps == resultSteps)
            {
                subways.add(subway);
            }
            //System.out.println(subway.getSubwayName() + "added");
        }
        //System.out.println("\n\n\n\n");
    }
}
