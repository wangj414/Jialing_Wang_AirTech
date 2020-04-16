public class Flight
{
    private String flightId;
    private String departure;
    private String arrival;
    private String day;
    private int availableBoxs;
    public final int CAPACITY = 20;

    public Flight(String flightId, String departure, String arrival, String day)
    {
        this.flightId = flightId;
        this.departure = departure;
        this.arrival = arrival;
        this.day = day;
        this.availableBoxs = CAPACITY;
    }

    public String getFlightId()
    {
        return flightId;
    }

    public String getDeparture()
    {
        return departure;
    }

    public String getArrival()
    {
        return arrival;
    }

    public String getDay()
    {
        return day;
    }

    public void reduceAvailableBoxs()
    {
        this.availableBoxs--;
    }

    public boolean isAvailable()
    {
        return availableBoxs > 0;
    }

    public boolean isLoaded()
    {
        return availableBoxs < CAPACITY;
    }
}
