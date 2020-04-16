public class Order
{
    private String orderId;
    private String destination;

    public Order(String orderId, String destination)
    {
        this.orderId = orderId;
        this.destination = destination;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public String getDestination()
    {
        return destination;
    }
}
