import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;


public class TestingHtml {
    public static void main(String args [])
    {

        MTASetup mtaSetup = new MTASetup();
        mtaSetup.setup();
        GPS gpsTest = new GPS(mtaSetup.getStationMap());
        //gpsTest.displayAllStations();

        gpsTest.input();



    }
}
