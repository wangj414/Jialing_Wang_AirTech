package util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.script.*;

public class OrdersReader
{
    private ScriptEngine engine;
    public final String RELATIVE_PATH = "/coding-assigment-orders.json";
    private List<String> orderIds;

    public OrdersReader()
    {
        setUp();
    }


    public void setUp()
    {
        engine = new ScriptEngineManager().getEngineByName("javascript");
        orderIds = new ArrayList<>();
    }

    public Map<String, String> importOrders()
    {
        String json = "";
        String root = System.getProperty("user.dir");
        String filePath = root + RELATIVE_PATH;
        Map<String, String> orderMap = new HashMap<>();

        try
        {
            String row;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            if((row = reader.readLine()) != null)
            {
                json += row;
            }

            while((row = reader.readLine()) != null)
            {
                json += "\n" + row;
            }

            String scriptString = "Java.asJSONCompatible(" + json + ")";
            Object resultObj = engine.eval (scriptString);
            Map<String, Object> map = (Map<String, Object>) resultObj;

            Iterator<String> itr = map.keySet().iterator();
            while (itr.hasNext())
            {
                String orderId = itr.next();
                Map<String, Object> value = (Map<String, Object>) map.get(orderId);

                String destination = (String) value.get("destination");
                orderMap .put(orderId, destination);
                orderIds.add(orderId);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }
        return orderMap;
    }

    public List<String> getOrderIds()
    {
        return orderIds;
    }
}
