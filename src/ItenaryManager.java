import java.util.*;

public class ItenaryManager
{
    private Map<String, Order> orderTable = new HashMap<>();

    private static ItenaryManager ourInstance = new ItenaryManager();

    public static ItenaryManager getInstance()
    {
        return ourInstance;
    }

    private ItenaryManager()
    {

    }

    public void loadOrders(Map<String, String> orderMap)
    {
        Iterator<String> itr = orderMap.keySet().iterator();
        while (itr.hasNext())
        {
            String orderId = itr.next();
            String destination = orderMap.get(orderId);
            orderTable.put(orderId, new Order(orderId, destination));
        }
    }

    public Itenary generateItenary(String orderId)
    {
        Itenary itenary = null;
        if(!orderTable.containsKey(orderId))
        {
            System.out.println("order: " + orderId + ", flightNumber: not scheduled");
            return itenary;
        }

        Order order = orderTable.get(orderId);
        List<Flight> possibleFlights = getFlightsByDestination(order.getDestination());

        if(possibleFlights == null)
        {
            System.out.println("order: " + orderId + ", No availble flight found");
            return itenary;
        }

        for(Flight flight : possibleFlights)
        {
            if (flight.isAvailable())
            {
                itenary = new Itenary(orderId, flight.getFlightId(), flight.getDeparture(),
                flight.getArrival(), flight.getDay());
                flight.reduceAvailableBoxs();
                break;
            }
        }

        if(itenary == null)
        {
            System.out.println("order: " + orderId + ", No availble flight found");
            return itenary;
        }

        System.out.println("order: " + orderId + ", flightNumber: " + itenary.getFlightId() + ", departure:" +
        itenary.getDeparture() + ", arrival: " + itenary.getArrival() + ", day: " + itenary.getDate());
        return itenary;
    }

    private List<Flight> getFlightsByDestination(String destination)
    {
        FlightManager flightManager = FlightManager.getInstance();
        Map<String, List<Flight>> flightMap = flightManager.getDestinationLookUpMap();
        return flightMap.get(destination);
    }
}
