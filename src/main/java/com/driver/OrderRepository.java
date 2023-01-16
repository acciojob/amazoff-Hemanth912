package com.driver;


import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;

@Repository

public class OrderRepository {


    private Map<String,Order> orderDb;
    private Map<String,DeliveryPartner> partnerDb;
    private Map<String,List<String>> pairDb;


    public OrderRepository(Map<String, Order> orderDb, Map<String, DeliveryPartner> partnerDb, Map<String, List<String>> pairDb) {
        this.orderDb = new HashMap<>();
        this.partnerDb = new HashMap<>();
        this.pairDb = new HashMap<>();
    }

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
        if(pairDb.containsKey(partnerId))
        {
            List<String> list = pairDb.get(partnerId);
            list.add(orderId);
        }
        else {
            List<String> orders = new ArrayList<>();
            orders.add(orderId);
            pairDb.put(partnerId, orders);
        }
        increasecount(partnerId);
    }

    private void increasecount(String partnerId) {
        // TODO Auto-generated method stub
        DeliveryPartner d=null;
        if(partnerDb.containsKey(partnerId))
        {
            d=partnerDb.get(partnerId);
            d.setNumberOfOrders(d.getNumberOfOrders()+1);
        }
        else
        {
            d=new DeliveryPartner(partnerId);
            d.setNumberOfOrders(1);
            partnerDb.put(partnerId, d);
        }

    }

    public Order getOrderFromDb(String orderId){
        if(orderDb.containsKey(orderId))
            return orderDb.get(orderId);
        else
            return null;
    }

    public DeliveryPartner getPartnerFromDb(String partnerId)
    {
        if(partnerDb.containsKey(partnerId))
            return partnerDb.get(partnerId);
        else
            return null;
    }

    public int numOfOrderForPartner(String partnerId)
    {
        DeliveryPartner d = null;
        if(partnerDb.containsKey(partnerId))
        {
            d=partnerDb.get(partnerId);
        }
        return d.getNumberOfOrders();
    }

    public List<String> OrdersForPartner(String partnerId)
    {
        List<String> orders = null;
        if(pairDb.containsKey(partnerId))
        {
            orders=pairDb.get(partnerId);
        }
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
        int count=0;
        HashSet<String> map=new HashSet<>();
        for(List<String> a:pairDb.values())
        {
            List<String> list=a;
            for(String s: list)
            {
                map.add(s);
            }
        }
        for(String s:orderDb.keySet())
        {
            if(!map.contains(s))
            {
                count++;
            }
        }
        return count;
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
        if(!pairDb.containsKey(partnerId))
            return 0;

        for(String a:pairDb.get(partnerId))
        {
            if(orderDb.containsKey(a))
            {
                Order o = orderDb.get(a);
                if(o.getDeliveryTime()>t)
                    count++;
            }
            else
                return 0;
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
