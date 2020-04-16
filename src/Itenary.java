public class Itenary
{
    String orderId;
    String flightId;
    String departure;
    String arrival;
    String date;

    public Itenary(String orderId, String flightId, String departure, String arrival, String date)
    {
        this.orderId = orderId;
        this.flightId = flightId;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
    }

    public String getOrderId()
    {
        return orderId;
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

    public String getDate()
    {
        return date;
    }
}
