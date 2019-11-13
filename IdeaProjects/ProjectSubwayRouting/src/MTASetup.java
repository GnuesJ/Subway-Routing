import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class MTASetup {
    private StationMap stationMap;
    private static final String mtaHomeLink = "http://web.mta.info/nyct/service/";
    private Map<String, String> checkWithoutSpace;

    //Contructor
    public MTASetup()
    {
        stationMap = new StationMap();
        //setup();
        checkWithoutSpace = new HashMap<>();
    }


    //Operator
    public void setup()
    {
//        Set<String> excludeSet = new HashSet<>();
//        try
//        {
//            File excludeFile = new File("excludeNames.txt");
//            Scanner scan = new Scanner(excludeFile);
//            while(scan.hasNext())
//            {
//                String tempString = scan.nextLine();
//                excludeSet.add(tempString);
//            }
//
//        }
//        catch(FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
        List<String> subwayLinks = openMTAHomePage();
        openSubwayLinks(subwayLinks);
        System.out.println("\n\n\n\n" + "Successful" + "\n\n\n\n");

//        try
//        {
////            Document doc = Jsoup.connect("http://web.mta.info/nyct/service/").get();
////
////            //Elements allElement = doc.select("div.roundCorners.push-1.span-72.pad-10").getElementsByTag("a[href]");
////            Elements allElement = doc.select("div.roundCorners.push-1.span-72.pad-10").select("a[href]");
////            //Elements allElement = doc.getE;
////
////            List<String> tempList = new ArrayList<String>();
////
////            int counter = 0;
////            for(Element tempElement: allElement)
////            {
////                //tempElement.select("height.37");
////
////                String stringtemp = tempElement.attr("href");
////                if(!stringtemp.equals("sline.htm"))
////                {
////                    String tempbuilder = "http://web.mta.info/nyct/service/" + stringtemp;
////                    tempList.add(tempbuilder);
////                    System.out.println(tempList.get(counter));
////                    counter++;
////                }
////
////            }
//////span[class=emphasized]
////            for(String weblink: tempList)
////            {
////                doc = Jsoup.connect(weblink).get();
////
////                //allElement = doc.getElementsByTag("tr");
////                //allElement = doc.getElementsByTag("")
////                //allElement = doc.select("tr[height]");
////                allElement = doc.select("span[class=emphasized]");
////                System.out.println("\n" + "\n");
////
////                for(Element elementtempp: allElement)
////                {
////
////                    String stringStation = elementtempp.ownText();
////                    if(!stringStation.equals("") && !stringStation.equals("/") && !excludeSet.contains(stringStation))
////                    {
////                        if(stringStation.contains(" /"))
////                        {
////                            stringStation = stringStation.substring(0,stringStation.indexOf(" /"));
////                            System.out.println(stringStation);
////                        }
////                        else if(stringStation.contains("/"))
////                        {
////                            stringStation = stringStation.substring(0,stringStation.indexOf("/"));
////                            System.out.println(stringStation);
////                        }
////                        else
////                        {
////                            System.out.println(stringStation);
////
////                        }
////
////                    }
////
////                }
////
////            }
////            //System.out.println(allElement);
////            System.out.println("\n");
////            /*for(Element element: allElement)
////            {
////                System.out.println(element.ownText());
////            }*/
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }

    }

    private List<String> openMTAHomePage()
    {
        List<String> tempList = new ArrayList<String>();
        try
        {
            Document doc = Jsoup.connect(mtaHomeLink).get();

            //Elements allElement = doc.select("div.roundCorners.push-1.span-72.pad-10").getElementsByTag("a[href]");
            Elements allElement = doc.select("div.roundCorners.push-1.span-72.pad-10").select("a[href]");
            //Elements allElement = doc.getE;


            int counter = 0;
            for(Element tempElement: allElement)
            {
                //tempElement.select("height.37");

                String stringTemp = tempElement.attr("href");
                if(!stringTemp.equals("sline.htm")) //&& !stringTemp.equals("lline.htm")
                {
                    //String tempbuilder = "http://web.mta.info/nyct/service/" + stringtemp;
                    tempList.add("http://web.mta.info/nyct/service/" + stringTemp);
                    System.out.println(tempList.get(counter));
                    counter++;
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return tempList;
    }

    private void openSubwayLinks(List<String> subwayLinks)
    {
        int count = 0;
        Set<String> excludeSet = new HashSet<>();
        try
        {
            File excludeFile = new File("excludeNames.txt");
            Scanner scan = new Scanner(excludeFile);
            while(scan.hasNext())
            {
                String tempString = scan.nextLine().toLowerCase();
                excludeSet.add(tempString);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        for(String weblink: subwayLinks) {
            try {
                Document doc = Jsoup.connect(weblink).get();

                //////
                Subway tempSubway = new Subway(weblink);
                Routing tempRouting = new Routing(tempSubway);
                Route tempRoute = new Route();
                Route headRoute = tempRoute;
                /////

                //allElement = doc.getElementsByTag("tr");
                //allElement = doc.getElementsByTag("")
                //allElement = doc.select("tr[height]");
                //Elements allElement = doc.select("span[class=emphasized]");
                System.out.println("\n" + "\n");
                Elements allTables = doc.getElementsByTag("table");
                Elements allElement;
                if(!tempSubway.getSubwayName().equals("A Line"))
                {
                    Element lineTable = allTables.last(); 		  //last table
                    allElement = lineTable.select("td:nth-of-type(3)");
                    //allElement = allTables.select("td:nth-of-type(3)");
                }
                else
                {
                    //Elements tempElements = doc.select("tbody");
                    allElement = doc.select("td:nth-of-type(3)");
                }
                //Elements allTables = doc.getElementsByTag("table");
                //Element lineTable = allTables.select("td:nth-of-type(3)");

                //Elements allElement = allTables.select("td:nth-of-type(3)");
                //allElement = allElement.select("p").remove();
                //allElement = allElement.removeAttr("p");

                for (Element elementtempp : allElement)
                {
                    Elements elements =elementtempp.select("p").remove();
//                    StringBuilder sbuilder = new StringBuilder("");
//                    for(Element e: elements)
//                    {
//                        sbuilder.append(e.text());
//                    }
//                    System.out.println("//////////////////" + sbuilder);
                    //while(elementtempp.hasClass("p"))
                    //System.out.println("///////////");
                    //System.out.println(elementtempp);
                    String stringStation = elementtempp.text().toLowerCase();
                    if (!stringStation.equals("") && !stringStation.equals("/") && !excludeSet.contains(stringStation))
                    {
                        if(stringStation.equals("Court Sq Only line is ADA-accessible".toLowerCase()))
                        {
                            stringStation = "court sq";
                        }
                        if(stringStation.equals("Flushing- Main Street / Roosevelt Avenue".toLowerCase()))
                        {
                            stringStation = "Flushing-Main Street / Roosevelt Avenue".toLowerCase();
                        }
//                        if (stringStation.contains(" /")) {
//                            stringStation = stringStation.substring(0, stringStation.indexOf(" /"));
//
//                            System.out.println(stringStation);
//                        } else if (stringStation.contains("/")) {
//                            stringStation = stringStation.substring(0, stringStation.indexOf("/"));
//                            System.out.println(stringStation);
//                        } else {
//                            System.out.println(stringStation);
//
//                        }

                    Station tempStation = new Station();
                        String stationStationReverse = null;

                    String stringWithoutSpace = stringStation.replace(" ", "");
                        if(stringStation.contains("/"))
                        {
                            stationStationReverse = stringWithoutSpace.substring(stringWithoutSpace.indexOf('/') +1) + '/' + stringWithoutSpace.substring(0, stringWithoutSpace.indexOf('/'));
                        }
                    if(checkWithoutSpace.containsKey(stringWithoutSpace))
                    {
                        stringStation = checkWithoutSpace.get(stringWithoutSpace);
                    }
                    else
                    {
                        if(stationStationReverse != null)
                        {
                            if(checkWithoutSpace.containsKey(stationStationReverse))
                            {
                                stringStation = checkWithoutSpace.get(stationStationReverse);
                            }
                        }
                    }

                    boolean skipStation = false;

                    if(stationMap.hasStation(stringStation))
                    {

                        tempStation = stationMap.getStation(stringStation);
                        if(tempStation.getSubways().contains(tempSubway))
                        {
                            skipStation = true;
                        }
                    }
                    else
                    {
                        checkWithoutSpace.put(stringWithoutSpace, stringStation);
                        ++count;
                        tempStation = new Station(stringStation);
                        stationMap.addStation(tempStation);
                    }
//                    if(tempStation.getStationName().equals("/"))
//                    {
//                        System.out.println("@@@@@@@@@@@@@@@@@@@" + "\n\n\n");
//                        System.out.println(stringStation);
//                        System.out.println(tempStation.getStationName());
//                        System.out.println("@@@" + "\n\n\n");
//                    }

                        if (!skipStation) {
                            System.out.println("–-------------------------" + stringStation);
                            tempStation.addsubway(tempSubway);
                            Route temp = setRoute(tempStation, tempRoute);
                            tempRoute.setNext(temp);
                            tempRoute = tempRoute.getNext();
                            tempRouting.addRoute(tempRoute);
                        }
                    }

                    //special case for Two Line


                }

                if(tempSubway.getSubwayName().equals("Two Line"))
                {
                    allElement =allTables.select("td:nth-of-type(2)");
                    for (Element elementtempp : allElement)
                    {
                        Elements elements =elementtempp.select("p").remove();
//
                        String stringStation = elementtempp.text().toLowerCase();
                        if (!stringStation.equals("") && !stringStation.equals("/") && !excludeSet.contains(stringStation))
                        {
                            if(stringStation.equals("Court Sq Only line is ADA-accessible".toLowerCase()))
                            {
                                stringStation = "court sq";
                            }
                            if(stringStation.equals("Flushing- Main Street / Roosevelt Avenue".toLowerCase()))
                            {
                                stringStation = "Flushing-Main Street / Roosevelt Avenue".toLowerCase();
                            }
//

                            Station tempStation = new Station();
                            String stringWithoutSpace = stringStation.replace(" ", "");
                            if(checkWithoutSpace.containsKey(stringWithoutSpace))
                            {
                                stringStation = checkWithoutSpace.get(stringWithoutSpace);
                            }
                            if(stationMap.hasStation(stringStation))
                            {
                                tempStation = stationMap.getStation(stringStation);
                            }
                            else
                            {
                                checkWithoutSpace.put(stringWithoutSpace, stringStation);
                                ++count;
                                tempStation = new Station(stringStation);
                                stationMap.addStation(tempStation);
                            }
                            System.out.println("–-------------------------" + stringStation);

                            tempStation.addsubway(tempSubway);
                            Route temp = setRoute(tempStation, tempRoute);
                            tempRoute.setNext(temp);
                            tempRoute = tempRoute.getNext();
                            tempRouting.addRoute(tempRoute);
                        }



                    }
                }



                headRoute = headRoute.getNext();
                headRoute.setPrev(null);
                tempSubway.setRouting(tempRouting);
                System.out.println(tempSubway.getSubwayName());
//                if(tempSubway.getSubwayName().equals("Z Line"))
//                {
////                    for(Map.Entry<Station, Route> e: tempSubway.getRouting().getRoutes().entrySet())
////                    {
////                        if(e.getKey().getStationName()==null)
////                        {
////                            System.out.println("NULL SPACEEEEE");
////                        }
////                        else
////                        System.out.println(e.getKey().getStationName());
////                    }
////                    headRoute = headRoute.getNext();
////                    Station stationn = headRoute.getStation();
////                    System.out.println(stationn.getStationName());
////                    headRoute = headRoute.getNext();
//                    while(headRoute.getNext().getNext()!=null)
//                    {
//                        System.out.println(headRoute.getStation().getStationName());
//                        headRoute = headRoute.getNext();
//                    }
//                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            //System.out.println(allElement);
            System.out.println("\n");
            /*for(Element element: allElement)
            {
                System.out.println(element.ownText());
            }*/

//        }
//        catch(FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
        }
        System.out.println(count + "Station added");
    }

//    private Station setStation(String stationName)
//    {
//        Station tempStation = stationMap.getStation(stationName);
//        if(tempStation == null)
//        {
//            tempStation = new Station(stationName);
//        }
//        return tempStation;
//    }

//    private void setSubway()
//    {
//
//    }

    private Route setRoute(Station station, Route route)
    {
        Route temp = new Route(station);
        temp.setPrev(route);
        return temp;
    }

    private void setRouting()
    {

    }

    //Getter
    public StationMap getStationMap()
    {
        return stationMap;
    }

    private void stationCorrection()
    {
        Station fixStation = stationMap.getStation("/");
        for(Subway fixSubway: fixStation.getSubways())
        {
            if(fixSubway.getSubwayName().equals("N Line"))
            {
                System.out.println("N Line found---------------------");
                if(fixSubway.getRouting().getRoutes().containsKey("/"))
                {
                    System.out.println("/ found--------------------");
                }
            }
        }
    }
}


/*
inside setup

 */