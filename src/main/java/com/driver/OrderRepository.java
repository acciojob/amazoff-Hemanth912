package com.driver;


import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;

@Repository

public class OrderRepository {

/*
    private Map<String,Order> orderDb;
    private Map<String,DeliveryPartner> partnerDb;
    private Map<String,List<String>> pairDb;


    public OrderRepository(Map<String, Order> orderDb, Map<String, DeliveryPartner> partnerDb, Map<String, List<String>> pairDb) {
        this.orderDb = new HashMap<>();
        this.partnerDb = new HashMap<>();
        this.pairDb = new HashMap<>();
    }
*/
private Map<String,Order> ordermap;

    private Map<String,DeliveryPartner> deliverypartnermap;

    private Map<String, List<String>> pairmap;


    public OrderRepository(Map<String, Order> ordermap, Map<String, List<String>> deliverypartnermap) {
        this.ordermap = new HashMap<>();
        this.deliverypartnermap = new HashMap<>();
        this.pairmap=new HashMap<>();

    }

    public void addOrderToDb(Order order)
    {
        ordermap.put(order.getId(), order);
    }

    public void addPatnerToDb(String partnerId)
    {
        /*DeliveryPartner d = new DeliveryPartner(partnerId);
        partnerDb.put(partnerId,d);*/
        DeliveryPartner d=new DeliveryPartner(partnerId);
        deliverypartnermap.put(partnerId, d);
    }

    public void addPartnerToOrder(String orderId, String partnerId)
    {
       /* if(pairDb.containsKey(partnerId))
        {
            List<String> list = pairDb.get(partnerId);
            list.add(orderId);
        }
        else {
            List<String> orders = new ArrayList<>();
            orders.add(orderId);
            pairDb.put(partnerId, orders);
        }
        increasecount(partnerId);*/
        if(pairmap.containsKey(partnerId))
        {
            List<String> list=pairmap.get(partnerId);
            list.add(orderId);
        }
        else
        {
            List<String> list=new ArrayList<>();
            list.add(orderId);
            pairmap.put(partnerId, list);
        }
        increasecount(partnerId);

    }

    private void increasecount(String partnerId) {
        // TODO Auto-generated method stub
        /*DeliveryPartner d=null;
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
        }*/
        DeliveryPartner d=null;
        if(deliverypartnermap.containsKey(partnerId))
        {
            d=deliverypartnermap.get(partnerId);
            d.setNumberOfOrders(d.getNumberOfOrders()+1);
        }
        else
        {
            d=new DeliveryPartner(partnerId);
            d.setNumberOfOrders(1);
            deliverypartnermap.put(partnerId, d);

        }

    }

    public Order getOrderFromDb(String orderId){
       /* if(orderDb.containsKey(orderId))
            return orderDb.get(orderId);
        else
            return null;*/
        if(ordermap.containsKey(orderId))
        {
            return ordermap.get(orderId);
        }
        else
        {
            return null;
        }
    }

    public DeliveryPartner getPartnerFromDb(String partnerId)
    {
        /*if(partnerDb.containsKey(partnerId))
            return partnerDb.get(partnerId);
        else
            return null;*/
        if(deliverypartnermap.containsKey(partnerId))
        {
            return deliverypartnermap.get(partnerId);
        }
        else
        {
            return null;
        }
    }

    public int numOfOrderForPartner(String partnerId)
    {
        /*DeliveryPartner d = null;
        if(partnerDb.containsKey(partnerId))
        {
            d=partnerDb.get(partnerId);
        }
        return d.getNumberOfOrders();*/
        DeliveryPartner d=null;
        if(deliverypartnermap.containsKey(partnerId))
        {
            d=deliverypartnermap.get(partnerId);
        }
        return d.getNumberOfOrders();
    }

    public List<String> OrdersForPartner(String partnerId)
    {
       /* List<String> orders = null;
        if(pairDb.containsKey(partnerId))
        {
            orders=pairDb.get(partnerId);
        }
        return orders;*/
        List<String> list=null;
        if(pairmap.containsKey(partnerId))
        {
            list=pairmap.get(partnerId);
        }
        return list;
    }

    public List<String> getAllOrders()
    {
       /* List<String> allOrders = new ArrayList<>();
        for(Map.Entry<String, Order>entry:orderDb.entrySet())
        {
            allOrders.add(entry.getKey());
        }
        return allOrders;*/
        List<String> list=new ArrayList<>();
        for(Map.Entry<String, Order>entry:ordermap.entrySet())
        {
            list.add(entry.getKey());
        }
        return list;
    }

    public int orderUnassigned()
    {
        /*int count=0;
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
        return count;*/
        int count=0;
        HashSet<String> map=new HashSet<>();
        for(List<String> a:pairmap.values())
        {
            List<String> list=a;
            for(String s: list)
            {
                map.add(s);
            }
        }
        for(String s:ordermap.keySet())
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
       /* int i=0;
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
        return count;*/
        int count=0;
        int i=0;
        String s="";
        while(time.charAt(i)!=':')
        {
            s+=time.charAt(i);
            i++;
        }
        time=time.substring(i+1);
        int t=Integer.parseInt(s)+Integer.parseInt(time);
        if(!pairmap.containsKey(partnerId))
        {
            return 0;
        }
        for(String a:pairmap.get(partnerId))
        {
            if(ordermap.containsKey(a))
            {
                Order q=ordermap.get(a);
                if(q.getDeliveryTime()>t)
                {
                    count++;
                }
            }
            else
            {
                return 0;
            }
        }
        return count;
    }

    public String lastDeliverOrder(String partnerId)
    {
        /*int max=0;
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

        return hr+":"+min;*/
        int max=0;
        for(String a:pairmap.get(partnerId))
        {
            Order order=ordermap.get(a);
            if(order.getDeliveryTime()>max)
            {
                max=order.getDeliveryTime();
            }
        }
        String hour=max/60+"";
        String min=max%60+"";

        return hour+":"+min;
    }

    public void deletePartnerById(String partnerId)
    {
        /*partnerDb.remove(partnerID);
        for(String s:pairDb.get(partnerID))
        {
            if(orderDb.containsKey(s))
                orderDb.remove(s);
        }
        pairDb.remove(partnerID);*/
        deliverypartnermap.remove(partnerId);
        for(String s:pairmap.get(partnerId))
        {
            if(ordermap.containsKey(s))
            {
                ordermap.remove(s);
            }
        }
        pairmap.remove(partnerId);

    }

    public void deleteOrderById(String orderId)
    {
        /*orderDb.remove(orderID);
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
        }*/
        for(String l:pairmap.keySet())
        {
            List<String> list=pairmap.get(l);
            for(String a:list)
            {
                if(a.equals(orderId))
                {
                    list.remove(orderId);
                    pairmap.put(l, list);
                    break;
                }
            }
        }
        ordermap.remove(orderId);
    }

}
