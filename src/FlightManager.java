import java.util.*;

public class FlightManager
{
    Map<String, Flight> flightLookUpMap = new HashMap<>();
    private Map<String, List<Flight>> destinationLookUpMap = new HashMap<>();
    private List<String> flightIds = new ArrayList<>();

    private static FlightManager ourInstance = new FlightManager();

    public static FlightManager getInstance()
    {
        return ourInstance;
    }

    private FlightManager()
    {
        loadFlightMaps();
    }

    public  Flight getFlight(String flightId)
    {
        Flight flight = flightLookUpMap.get(flightId);
        logFlight(flight);
        return flight;
    }

    private void loadFlightMaps()
    {
        loadSingleFlight("1", "YUL", "YYZ", "1");
        loadSingleFlight("2", "YUL", "YYC", "1");
        loadSingleFlight("3", "YUL", "YVR", "1");

        loadSingleFlight("4", "YUL", "YYZ", "2");
        loadSingleFlight("5", "YUL", "YYC", "2");
        loadSingleFlight("6", "YUL", "YVR", "2");
    }

    private void loadSingleFlight(String flightId, String departure, String arrival, String day)
    {
        Flight flight = new Flight(flightId, departure, arrival, day);
        flightLookUpMap.put(flightId, flight);
        flightIds.add(flightId);

        if (destinationLookUpMap.containsKey(arrival))
        {
            destinationLookUpMap.get(arrival).add(flight);
        }
        else
        {
            List<Flight> list = new ArrayList<>();
            list.add(flight);
            destinationLookUpMap.put(arrival, list);
        }
    }

    public Map<String, List<Flight>> getDestinationLookUpMap()
    {
        return destinationLookUpMap;
    }

    private void logFlight(Flight flight)
    {
        System.out.println("Flight " + flight.getFlightId() + ", departure: " + flight.getDeparture() + ", arrival: "
                + flight.getArrival() + ", day: " + flight.getDay());
    }

    // the api to list out the loaded flight schedule on the console
    public void logLoadedFlightSchedule()
    {
        for(String flightId : flightIds)
        {
            Flight flight = getFlight(flightId);
            if (flight.isLoaded())
            {
                logFlight(flight);
            }
        }
    }
}
