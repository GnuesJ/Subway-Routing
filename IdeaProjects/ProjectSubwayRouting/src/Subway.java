import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class Subway {
    private static final String[] htmls = new String[]{"http://web.mta.info/nyct/service/oneline.htm",
    "http://web.mta.info/nyct/service/twoline.htm",
    "http://web.mta.info/nyct/service/threelin.htm",
    "http://web.mta.info/nyct/service/fourline.htm",
    "http://web.mta.info/nyct/service/fiveline.htm",
    "http://web.mta.info/nyct/service/sixline.htm",
    "http://web.mta.info/nyct/service/6d.htm",
    "http://web.mta.info/nyct/service/sevenlin.htm",
    "http://web.mta.info/nyct/service/7d.htm",
    "http://web.mta.info/nyct/service/aline.htm",
    "http://web.mta.info/nyct/service/bline.htm",
    "http://web.mta.info/nyct/service/cline.htm",
    "http://web.mta.info/nyct/service/dline.htm",
    "http://web.mta.info/nyct/service/eline.htm",
    "http://web.mta.info/nyct/service/fline.htm",
    "http://web.mta.info/nyct/service/gline.htm",
    "http://web.mta.info/nyct/service/jline.htm",
    "http://web.mta.info/nyct/service/lline.htm",
    "http://web.mta.info/nyct/service/mline.htm",
    "http://web.mta.info/nyct/service/nline.htm",
    "http://web.mta.info/nyct/service/qline.htm",
    "http://web.mta.info/nyct/service/rline.htm",
    "http://web.mta.info/nyct/service/wline.htm",
    "http://web.mta.info/nyct/service/zline.htm"};
    private static final String[] subwayLines = new String[]{
            "One Line",
            "Two Line",
            "Three Line",
            "Four Line",
            "Five Line",
            "Six Line",
            "Six Line EXPRESS",
            "Seven Line",
            "Seven Line EXPRESS",
            "A Line",
            "B Line",
            "C Line",
            "D Line",
            "E Line",
            "F Line",
            "G Line",
            "J Line",
            "L Line",
            "M Line",
            "N Line",
            "Q Line",
            "R Line",
            "W Line",
            "Z Line",};


    private String subwayHTML;
    private String subwayName;
    private Routing routing;


    //Constructor
    public Subway()
    {
        this.subwayHTML = null;
        this.subwayName = null;
        this.routing = null;
    }

    public Subway(String subwayHTML)
    {
        this.subwayHTML = subwayHTML;
        try {
            for (int i = 0; i < subwayHTML.length(); i++) {
                if (this.subwayHTML.equals(htmls[i])) {
                    subwayName = subwayLines[i];
                    break;
                }
            }
            routing = null;
        }
        catch(NoSuchElementException e)
        {
            e.getMessage();
        }
    }

    public Subway(String subwayHTML, Routing routing)
    {
        this.subwayHTML = subwayHTML;
        for(int i =0; i<subwayHTML.length(); i++)
        {
            if(this.subwayHTML.equals(htmls[i]))
            {
                subwayName = subwayLines[i];
                break;
            }
        }
        this.routing = routing;
    }

    //getter
    public String getSubwayHTML(){return subwayHTML;}
    public String getSubwayName(){return subwayName;}
    public Routing getRouting(){return routing;}

    //mutator
    public void setSubwayHTML(String subwayHTML)
    {
        this.subwayHTML = subwayHTML;
        for(int i =0; i<subwayHTML.length(); i++)
        {
            if(this.subwayHTML.equals(htmls[i]))
            {
                subwayName = subwayLines[i];
                break;
            }
        }
    }
    public void setRouting(Routing routing)
    {
        this.routing = routing;
    }
}
