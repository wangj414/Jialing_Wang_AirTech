import util.OrdersReader;

import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        FlightManager flightManager = FlightManager.getInstance();
        ItenaryManager itenaryManager = ItenaryManager.getInstance();

        OrdersReader ordersReader = new OrdersReader();
        Map<String, String> orderMap = ordersReader.importOrders();
        List<String> orderIds = ordersReader.getOrderIds();

        itenaryManager.loadOrders(orderMap);

        // tests begins here
        System.out.println("============================= Call getFlight on every flight from 1 to 6 ==========================");
        flightManager.getFlight("1");
        flightManager.getFlight("2");
        flightManager.getFlight("3");
        flightManager.getFlight("4");
        flightManager.getFlight("5");
        flightManager.getFlight("6");

        System.out.println("========================= load itenary for order 1 to 99 ====================================");

        for(String orderId : orderIds)
        {
            itenaryManager.generateItenary(orderId);
        }

        System.out.println("======================== input bad order number ==============================================");
        itenaryManager.generateItenary("order-111");
    }
}
