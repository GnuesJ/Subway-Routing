

import com.sun.jdi.InvalidTypeException;

import java.util.*;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.InputMismatchException;


public class GPS {
    private StationMap map;
    private Map<Station, GPSStationIndex> adjMatrix;


    //Constructor
    public GPS(StationMap map)
    {
        adjMatrix = new HashMap<>();
        this.map = map;
        setAdjMatrix();
    }

    //Operator
    public void displayAllStations()
    {
        try
        {
            for(Map.Entry<String, Station> e: map.getStations().entrySet())
            {
                if(e.getKey().equals("/"))
                {
                    System.out.println("=======\n" + e.getValue().getStationName());
                    for(Subway e2: e.getValue().getSubways() )
                    {
                        System.out.println(e2.getSubwayName() + "\n=========");
                    }
                }
                else
                {
                System.out.println(e.getKey());

                }
            }



            System.out.println("\n\n\n\n");

            Subway tempSubway;

            boolean found = false;
            for(Map.Entry<String, Station> e: map.getStations().entrySet())
            {

                for(Subway e2: e.getValue().getSubways())
                {
                    if(e2.getSubwayName().equals("A Line"))
                    {
                        for(Map.Entry<Station, Route> e3: e2.getRouting().getRoutes().entrySet())
                        {
                            validNodelist(e3.getValue());
                            found = true;
                            break;
                        }
                        break;
                    }

                }
                if(found) break;
            }
        }
        catch(NoSuchElementException e)
        {
            e.getMessage();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void validNodelist(Route route)
    {
        while(route.getPrev()!=null)
        {
            route = route.getPrev();
        }
        int count  =0;
        while(route!=null)
        {
            System.out.println(route.getStation().getStationName());
            route = route.getNext();
            ++count;
        }
        System.out.println(count);
    }

    public void input()
    {
        Scanner scan = new Scanner(System.in);
        boolean manual = true;
        while(true)
        {
            try
            {
                System.out.println("Press 1 for manual input \nPress 2 for system to generate randomly");
                int input = scan.nextInt();
                if(input == 1)
                {
                    break;
                }
                else if(input == 2)
                {
                    manual = false;
                    break;
                }
                else {
                    System.out.println("Invalid Input, try again");
                }
                if(scan.hasNextLine())
                {
                    scan.nextLine();
                }
            }
            catch(InputMismatchException e)
            {
                e.getStackTrace();
                System.out.println("Invalid Input, try again");
                scan.nextLine();
            }
        }
        if(scan.hasNextLine())
        {
            scan.nextLine();
        }
        //String currentStation = "rockaway park / beach 116 street", destinationStation = "65 street / broadway"; //Court Sq-23 St/ 44 Drive
        //String currentStation = "rockaway boulevard / liberty avenue", destinationStation = "65 street / broadway";
        if(manual)
        {
            String currentStation, destinationStation;
            do {
                System.out.println("\nCopy paste from the stations above\nEx:broad street / wall street\nEnter Current Station");
                currentStation = scan.nextLine();
            }
            while (!validStation(currentStation));

            do {
                System.out.println("Enter Destination Station");
                destinationStation = scan.nextLine();
            }
            while (!validStation(destinationStation));
            scan.close();

//        GPSOptimalList optimalList = getpath(map.getStation(currentStation), map.getStation(destinationStation));
            //List<Station> optimalList = getpath(map.getStation(currentStation), map.getStation(destinationStation), setVisited);
            //displayResult(optimalList);
            getShortestPath(map.getStation(currentStation), map.getStation(destinationStation));
        }

        else {
            generateRandomInput();
        }

        //checking subway routes
//        Route tempRoute = new Route();
//        for(Station e: map.getStationsSet())
//        {
//            boolean found = false;
//            for(Subway s: e.getSubways())
//            {
//                if(s.getSubwayName().equals("E Line"))
//                {
//                    for(Route r: s.getRouting().getRoutes().values())
//                    {
//                        tempRoute = r;
//                        found = true;
//                        break;
//                    }
//                    break;
//                }
//            }
//            if(found) break;
//        }
//        while(tempRoute.getPrev() !=null)
//        {
//            tempRoute = tempRoute.getPrev();
//        }
//
//        while(tempRoute!=null)
//        {
//            System.out.println(tempRoute.getStation().getStationName());
//            tempRoute = tempRoute.getNext();
//        }


        //checking all stations connected to station
//        for(Station s: adjMatrix.get(map.getStation("34 Street-Penn Station / 8 Avenue")).getStations())
//        {
//            System.out.println(s.getStationName());
//        }
    }

    private void generateRandomInput()
    {
        int errorCounter = 0;
        int totalRuns = 0;

        Scanner scan = new Scanner(System.in);
        while(true)
        {
            try{
                System.out.println("Enter the number of runs");
                totalRuns = scan.nextInt();
                scan.close();
                break;
            }
            catch(InputMismatchException e)
            {
                e.getStackTrace();
                System.out.println("Invalid input, try again\n");
                scan.nextLine();
            }
        }
        System.out.println("\n\n");

        Map<Station,Integer> invalidStations = new HashMap<>();
        while(totalRuns>0) {
            --totalRuns;
//            try {
//                Thread.sleep(250);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Scanner scan = new Scanner(System.in);
//            System.out.println("Enter \"exit\" to stop the loop else enter any other key");
//            String userInput = scan.nextLine();
//            if(userInput.toLowerCase().equals("exit"))
//            {
//                break;
//            }

            Collection<Station> collection = map.getStationsSet();
            Random randomNumber = new Random();
            int sStation = randomNumber.nextInt(collection.size());
            int eStation = randomNumber.nextInt(collection.size());
            System.out.println("S: " + sStation + "   E " + eStation);
            while (sStation == eStation || sStation == 0 || eStation == 0) {
                sStation = randomNumber.nextInt(collection.size());
                eStation = randomNumber.nextInt(collection.size());
                System.out.println("S " + sStation + "  E " + eStation);
            }
            int counter = 0;
            Station startStation = null;
            Station endStation = null;
            boolean foundStart = false;
            boolean foundEnd = false;
            for (Station e : map.getStationsSet())
            {
                ++counter;
                if (counter == sStation) {
                    startStation = e;
                    foundStart = true;
                } else if (counter == eStation) {
                    endStation = e;
                    foundEnd = true;
                }
                if (foundStart == true && foundEnd == true) break;
            }
            int number = 0;
            if (startStation != null && endStation != null)
            {
                number = getShortestPath(startStation, endStation);
            }
            errorCounter+=number;
            if(number == 1)
            {
                if(!invalidStations.containsKey(startStation))
                {
                    invalidStations.put(startStation, 1);
                }
                else
                {
                    invalidStations.put(startStation,invalidStations.get(startStation)+1);
                }
                if(!invalidStations.containsKey(endStation))
                {
                    invalidStations.put(endStation, 1);
                }
                else
                {
                    invalidStations.put(endStation,invalidStations.get(endStation)+1);
                }
            }
            System.out.println("(" + startStation.getStationName() + ")   to   (" + endStation.getStationName() + ")\n\n\n");

        }

        /* DEBUG for stations that are not working with getShortestPath
        System.out.println("\n\n" + errorCounter + " total impossible path");
        for(Station e: invalidStations.keySet())
        {
            System.out.println(e.getStationName() + "  " + invalidStations.get(e));
        }
        */
    }


    private boolean validStation(String stationName)
    {
        if(!map.getStations().containsKey(stationName))
        {
            System.out.println("\nStation Not Found");
        }
        return map.getStations().containsKey(stationName);
    }

    //function
//    private Set<Station, Subway> findPath(Station curStation, Station finStation)
//    {
//        GPS
//    }

    private void setAdjMatrix()
    {
//        for(Station beginStation: map.getStations().values())
//        {
//            System.out.println("X-AXIS " + beginStation.getStationName());
//            adjMatrix.put(beginStation, new GPSStationIndex(beginStation,map.getStations()));
//        }
        for(Station beginStation: map.getStationsSet())
        {
//            if(beginStation == map.getStation("23 Street / 8 Avenue"))
//            System.out.println("X-AXIS " + beginStation.getStationName() + "\n\n\n");
//            else System.out.println("SKIPP \n\n\n\n");
            adjMatrix.put(beginStation, new GPSStationIndex(beginStation,map.getStationsSet()));
        }
    }

    private List<Station> getpath(Station currentStation, Station destinationStation, Set<Station> visited)
    {

//        System.out.println("==");
//        if(!hasVisited.containsKey(currentStation)){
//            Set<Station> set= new HashSet<>();
//        hasVisited.put(currentStation, set);
//        }
        //GPSOptimalList result = new GPSOptimalList(currentStation,0);
        List<Station> result = new LinkedList<>();
        result.add(currentStation);
        if(currentStation == destinationStation) return result;
        //Deque<Station> inque = new ArrayDeque<>();
//        GPSOptimalList temp1 = null;
//        GPSOptimalList temp2 = null;
        List<Station> temp1 = null;
        List<Station> temp2 = null;
        int count = 0;
        for(Station e: adjMatrix.get(currentStation).getStations())
        {
//            if(hasVisited.get(currentStation) == null || !(hasVisited.get(currentStation).contains(e)))
//            {
//                    Set<Station> set = hasVisited.get(currentStation);
//                if(set.isEmpty())
//                {
//                    Set<Station> setTemp = new HashSet<>();
//                    setTemp.add(e);
//                    hasVisited.put(currentStation,setTemp);
//                }
//                else {
//                    set.add(e);
//                }
//                if(temp2 == null)
//                {
//                    temp2 = getpath(e,destinationStation);
//                }
//                else
//                {
//                    temp1 = getpath(e,destinationStation);
//                    if(temp1 != null && temp2.getSteps() > temp1.getSteps())
//                    {
//                        temp2 = temp1;
//                    }
//                }
//            }

//            GPSSubwayIndex subwayIndex = adjMatrix.get(currentStation).getGPSSubwayIndex(e);
//            GPSSubwayIndex oppositeSubwayIndex = adjMatrix.get(e).getGPSSubwayIndex(currentStation);
//            if(!hasVisited2.contains(subwayIndex) && !hasVisited2.contains(oppositeSubwayIndex))
//            {
//                System.out.println(++count);
//                hasVisited2.add(subwayIndex);
//                hasVisited2.add(oppositeSubwayIndex);
//                if(temp2 == null)
//                {
//                    temp2 = getpath(e,destinationStation);
//                }
//                else
//                {
//                    temp1 = getpath(e,destinationStation);
////                    int inttemp = temp1.size();
//                    //if(temp1 != null && temp2.getSteps() > temp1.getSteps())
//                    if(temp1 != null && temp2.size() > temp1.size())
//                    {
//                        temp2 = temp1;
//                    }
//                }
//            }
            if(!visited.contains(e))
            {
                System.out.println(++count);
                Set<Station> visitedd = new HashSet<>();
                visitedd.addAll(visited);
                if(temp2 == null)
                {
                    temp2 = getpath(e,destinationStation, visitedd);
                }
                else
                {
                    temp1 = getpath(e,destinationStation, visitedd);
//                    int inttemp = temp1.size();
                    //if(temp1 != null && temp2.getSteps() > temp1.getSteps())
                    if(temp1 != null && temp2.size() > temp1.size())
                    {
                        temp2 = temp1;
                    }
                }
            }
        }

        if(temp2 != null)
        {
//            result.addList(temp2.getStations());
//            result.addSteps(temp2.getSteps()+1);
            result.addAll(temp2);
            return result;
        }
        else return null;
    }

    private int getShortestPath(Station beginStation, Station endStation)
    {
        Map<Station, Integer> allStations = new HashMap<>();
        Deque<Station> deque = new ArrayDeque<>();
        Deque<GPSStationNode> nodeDeque= new ArrayDeque<>();
        allStations.put(beginStation, 0);
//        for(Station e: adjMatrix.get(beginStation).getStations())
//        {
//            deque.push(e);
//        }

        //deque.push(beginStation);
        deque.addLast(beginStation);

        GPSStationNode beginStationNode = new GPSStationNode(beginStation);
        GPSStationNode endStationNode = new GPSStationNode(endStation);
        //nodeDeque.push(beginStationNode);
        nodeDeque.addLast(beginStationNode);

        Map<Station, GPSStationNode> mapGPSStationNode = new HashMap<>();
        mapGPSStationNode.put(beginStation,beginStationNode);
        mapGPSStationNode.put(endStation,endStationNode);

        boolean foundSolution = false;
        int countNodes = 2;

        while(!deque.isEmpty())
        {
            Station currentStation = deque.removeFirst();
            GPSStationNode currentNode = nodeDeque.removeFirst();
            ++countNodes;
            for(Station neighbor: adjMatrix.get(currentStation).getStations())
            {
                if(neighbor == endStation)
                {
                    if (!allStations.containsKey(neighbor))
                    {
                        allStations.put(neighbor, allStations.get(currentStation) + 1);
                        endStationNode.setPrev(currentNode);
                        System.out.println("----------------FOUND " + allStations.get(neighbor) + " Steps");
                        foundSolution = true;
                    }
                    else if(allStations.get(currentStation) + 1 < allStations.get(neighbor))
                    {
                        allStations.put(neighbor,allStations.get(currentStation) + 1);
                        endStationNode.setPrev(currentNode);
                        System.out.println("----------------FOUND BETTER SOLUTION " +allStations.get(neighbor) + " Steps");
                    }
                }
                else if(!allStations.containsKey(neighbor))
                {
                    allStations.put(neighbor, allStations.get(currentStation)+1);
                    //deque.push(neighbor);
                    deque.addLast(neighbor);
                    GPSStationNode neighborNode = new GPSStationNode(neighbor);
                    neighborNode.setPrev(currentNode);
                    //nodeDeque.push(neighborNode);
                    nodeDeque.addLast(neighborNode);
                    mapGPSStationNode.put(neighbor,neighborNode);
                    System.out.println("Visited node: " +neighbor.getStationName());
                }
                else if(allStations.get(currentStation) + 1 < allStations.get(neighbor))
                {
                    allStations.put(neighbor, allStations.get(currentStation) +1);
                    //deque.push(neighbor);
                    deque.addLast(neighbor);
                    GPSStationNode temp = mapGPSStationNode.get(neighbor);
                    temp.setPrev(currentNode);
                    //nodeDeque.push(temp);
                    nodeDeque.addLast(temp);

                }
            }
            //if(allStations.containsKey(endStation)) break;
            if(foundSolution) break;
        }
        if(allStations.containsKey(endStation))
        {
            System.out.println("\n\n\n" +countNodes + " Nodes Visited");
        System.out.println((allStations.get(endStation) + 1) + " Steps\n");
            displayGPSNodeResult(endStationNode,null, null);
            System.out.println("\n");
            return 0;
        }
        else
        {
            System.out.println("impossible");
            return 1;
        }
    }

    private void displayResult(GPSOptimalList gpsOptimalList)
    {
        if(gpsOptimalList == null)
        {
            System.out.println("Empty");
        }
        else {
            for (Station e : gpsOptimalList.getStations()) {
                System.out.println(e.getStationName());
            }
            System.out.println(gpsOptimalList.getSteps());
        }
    }
    private void displayResult(List<Station> list)
    {
        if(list == null)
        {
            System.out.println("empty");
        }
        else
        {
            int count = 0;
            for(Station e: list)
            {
                ++count;
                System.out.println(e.getStationName());
            }
            System.out.println(count);
        }
    }

//    public int displayGPSNodeResult(GPSStationNode endStation, Subway subway)
//    {
//        int numberOfTransfer = 0;
//        if(endStation == null)
//        {
//            if(subway != null)
//            {
//                System.out.println("1. Take: " + subway.getSubwayName());
//                return ++numberOfTransfer;
//            }
//        }
//        boolean subwayTransfer = false;
//        Subway tempSubway = subway;
//        if(endStation.getPrev() != null)
//        {
//            if(subway != null)
//            {
//                if(!endStation.getPrev().getStation().getSubways().contains(subway))
//                {
//                    for (Subway e : endStation.getStation().getSubways()) {
//                        if (endStation.getPrev().getStation().getSubways().contains(e))
//                        {
//                            tempSubway = e;
//                            subwayTransfer = true;
//                            ++numberOfTransfer;
//
//                        }
//                    }
//                }
//                else
//                {
//                    //System.out.println(subway.getSubwayName());
//                }
//            }
//            else {
//                for (Subway e : endStation.getStation().getSubways()) {
//                    if (endStation.getPrev().getStation().getSubways().contains(e))
//                    {
//                        tempSubway = e;
//
//                    }
//                }
//            }
//        }
//        numberOfTransfer += displayGPSNodeResult(endStation.getPrev(), tempSubway);
//        System.out.println("   - " + endStation.getStation().getStationName());
//        if(subwayTransfer)
//        {
//            System.out.print(numberOfTransfer + ". Transfer to: " + subway.getSubwayName() + "  at Station: ");
//        }
//        return numberOfTransfer;
//    }

    //use by displayGPSNodeResult(endNode, null, null);
    public int displayGPSNodeResult(GPSStationNode currentStation, GPSStationNode nextStation, Subway subway)
    {
        int numberOfSteps = 0;
        Subway tempSubway = subway;
        boolean transferred = false;

        if(currentStation == null)
        {
                ++numberOfSteps;
                System.out.println(numberOfSteps + ". Take to Subway: " + subway.getSubwayName() + "\n  At Station: " + nextStation.getStation().getStationName());
                System.out.println("   - " + nextStation.getStation().getStationName());
                return numberOfSteps;
        }

        if(nextStation == null || subway == null)
        {
            for(Subway e: currentStation.getPrev().getStation().getSubways())
            {
                if(currentStation.getStation().getSubways().contains(e))
                {
//                    tempSubway = e;
//                    if(e.getSubwayName().contains("EXPRESS")) break;
                    Route tempRoute = e.getRouting().getRoutes().get(currentStation.getPrev().getStation());
                    if(tempRoute.getNext() != null && tempRoute.getNext().getStation() == currentStation.getStation())
                    {
                        tempSubway = e;
                        if(e.getSubwayName().contains("EXPRESS")) break;
                    }
                    else if(tempRoute.getPrev() != null && tempRoute.getPrev().getStation() == currentStation.getStation())
                    {
                        tempSubway = e;
                        if(e.getSubwayName().contains("EXPRESS")) break;
                    }
                }
            }
            return displayGPSNodeResult(currentStation.getPrev(),currentStation, tempSubway);

        }

//        if(currentStation.getPrev() == null)
//        {
//            //numberOfSteps = displayGPSNodeResult(currentStation.getPrev(),currentStation, tempSubway);
//        }
        else if(currentStation.getPrev() != null && !currentStation.getPrev().getStation().getSubways().contains(subway))
        {
            for(Subway e: currentStation.getPrev().getStation().getSubways())
            {
                if(currentStation.getStation().getSubways().contains(e))
                {
//                    tempSubway = e;
//                    transferred = true;
//                    if(e.getSubwayName().contains("EXPRESS")) break;
                    Route tempRoute = e.getRouting().getRoutes().get(currentStation.getPrev().getStation());
                    if(tempRoute.getNext() != null && tempRoute.getNext().getStation() == currentStation.getStation())
                    {
                        tempSubway = e;
                        transferred = true;
                        if(e.getSubwayName().contains("EXPRESS")) break;
                    }
                    else if(tempRoute.getPrev() != null && tempRoute.getPrev().getStation() == currentStation.getStation())
                    {
                        tempSubway = e;
                        transferred = true;
                        if(e.getSubwayName().contains("EXPRESS")) break;
                    }
                }
            }
            ++numberOfSteps;
        }




        //if transfer is found
        if(transferred)
        {
            numberOfSteps += displayGPSNodeResult(currentStation.getPrev(),currentStation,tempSubway);
            System.out.println(numberOfSteps +". Transfer Subway: " + subway.getSubwayName() + "\n  At Station: " + currentStation.getStation().getStationName());
            System.out.println("   - " + nextStation.getStation().getStationName());
            return numberOfSteps;
        }

        numberOfSteps += displayGPSNodeResult(currentStation.getPrev(),currentStation,tempSubway);
        System.out.println("   - " + nextStation.getStation().getStationName());
        return numberOfSteps;


//        int numberOfTransfer = 0;
//        if(nextStation == null)
//        {
//            return numberOfTransfer;
//        }
//        if(currentStation == null)
//        {
//            if(subway != null)
//            {
//                System.out.println("1. Take: " + subway.getSubwayName());
//                return ++numberOfTransfer;
//            }
//        }
//        boolean subwayTransfer = false;
//        Subway tempSubway = subway;
//
//        if(nextStation != null)
//        {
//            if(!currentStation.getStation().getSubways().contains(subway))  // doesnt contain the same subway
//            {
//                for(Subway e: currentStation.getStation().getSubways())
//                {
//                    if(nextStation.getStation().getSubways().contains(e))  // if it contains the same subway for the new staitons
//                    {
//                        tempSubway = e;
//
//                    }
//                }
//                ++numberOfTransfer;
//                subwayTransfer = true;
//            }
//        }
//        else
//        {
//            for(Subway e: currentStation.getStation().getSubways())
//            {
//                if(currentStation.getPrev().getStation().getSubways().contains(e))
//                {
//                    tempSubway = e;
//                }
//            }
//        }
//
//
//        numberOfTransfer += displayGPSNodeResult(currentStation.getPrev(),currentStation, tempSubway);
//        System.out.println("   - " + currentStation.getStation().getStationName());
//        if(subwayTransfer)
//        {
//            System.out.println("   -" + nextStation.getStation().getSubways())
//            System.out.println(numberOfTransfer + ". Transfer to: " + subway.getSubwayName() + "  at Station: ");
//        }
//        return numberOfTransfer;
////        if(currentStation.getPrev() != null)
////        {
////            if(subway != null)
////            {
////                if(!currentStation.getPrev().getStation().getSubways().contains(subway))
////                {
////                    for (Subway e : currentStation.getStation().getSubways()) {
////                        if (currentStation.getPrev().getStation().getSubways().contains(e))
////                        {
////                            tempSubway = e;
////                            subwayTransfer = true;
////                            ++numberOfTransfer;
////
////                        }
////                    }
////                }
////                else
////                {
////                    //System.out.println(subway.getSubwayName());
////                }
////            }
////            else {
////                for (Subway e : endStation.getStation().getSubways()) {
////                    if (endStation.getPrev().getStation().getSubways().contains(e))
////                    {
////                        tempSubway = e;
////
////                    }
////                }
////            }
////        }
    }

//    //check valid Station to Station
//    private boolean checkRoute(Station beginStation, Station endStation)
//    {
//        for(Subway subway: beginStation.getSubways())
//        {
//            if(subway.getRouting().containsStation(beginStation))
//            {
//                Route tempRoute = subway.getRouting().getRoutes().get(beginStation);
//                if(tempRoute.getNext() != null && tempRoute.getNext().getStation() == endStation) return true;
//                tempRoute = subway.getRouting().getRoutes().get(beginStation);
//                if(tempRoute.getPrev() != null && tempRoute.getPrev().getStation() == endStation) return true;
//            }
//        }
//        return false;
//    }

}
