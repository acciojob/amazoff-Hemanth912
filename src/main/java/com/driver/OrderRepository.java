package com.driver;


import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;

@Repository

public class OrderRepository {

    HashMap<String,Order> orderDb = new HashMap<>();
    HashMap<String,DeliveryPartner> partnerDb = new HashMap<>();

    HashMap<String,List<String>> pairDb = new HashMap<>();

    public int orderAssigned=0;


    public void addOrderToDb(Order order)
    {
        String key = order.getId();
        orderDb.put(key,order);
    }

    public void addPatnerToDb(String partnerId)
    {
        DeliveryPartner d = new DeliveryPartner(partnerId);
        partnerDb.put(partnerId,d);
    }

    public void addPartnerToOrder(String orderId, String partnerId)
    {
        List<String> orders = new ArrayList<>();
        orders.add(orderId);
        orderAssigned++;
        pairDb.put(partnerId,orders);
    }

    public Order getOrderFromDb(String orderId)
    {
        return orderDb.get(orderId);
    }

    public DeliveryPartner getPartnerFromDb(String partnerId)
    {
        return partnerDb.get(partnerId);
    }

    public int numOfOrderForPartner(String partnerId)
    {
        List<String> orders = pairDb.get(partnerId);
        return orders.size();
    }

    public List<String> OrdersForPartner(String partnerId)
    {
        List<String> orders = pairDb.get(partnerId);
        return orders;
    }

    public List<String> getAllOrders()
    {
        List<String> allOrders = new ArrayList<>();
        for(Map.Entry<String, Order>entry:orderDb.entrySet())
        {
            allOrders.add(entry.getKey());
        }
        return allOrders;
    }

    public int orderUnassigned()
    {
        int count=orderDb.keySet().size();
        return Math.abs(count-orderAssigned);
    }

    public int orderleftundelivered(String time,String partnerId)
    {
        int i=0;
        int count=0;
        String hr="";
        while(time.charAt(i)!=':')
        {
            hr+=time.charAt(i);
            i++;
        }
        String min=time.substring(i+1);

        int t=Integer.parseInt(hr)*60+Integer.parseInt(min);

        for(String a:pairDb.get(partnerId))
        {
            if(orderDb.containsKey(a))
            {
                Order o = orderDb.get(a);
                if(o.getDeliveryTime()>t)
                    count++;
            }
        }
        return count;
    }

    public String lastDeliverOrder(String partnerId)
    {
        int max=0;
        for(String s:pairDb.get(partnerId))
        {
            Order ord = orderDb.get(s);
            if(ord.getDeliveryTime()>max)
            {
                max=ord.getDeliveryTime();
            }
        }
        String hr = max/60+" ";
        String min = max%60+" ";

        return hr+":"+min;
    }

    public void deletePartnerById(String partnerID)
    {
        partnerDb.remove(partnerID);
        for(String s:pairDb.get(partnerID))
        {
            if(orderDb.containsKey(s))
                orderDb.remove(s);
        }
        pairDb.remove(partnerID);
    }

    public void deleteOrderById(String orderID)
    {
        orderDb.remove(orderID);
        for(String l:pairDb.keySet())
        {
            List<String> ord = pairDb.get(l);
            for(String a:ord)
            {
                if(a.equals(orderID))
                {
                    ord.remove(a);
                    pairDb.put(l,ord);
                    break;
                }
            }
        }
    }

}
