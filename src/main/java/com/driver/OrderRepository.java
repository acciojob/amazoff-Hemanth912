package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private Map<String,Order> ordermap;

    private Map<String,DeliveryPartner> deliverypartnermap;

    private Map<String, List<String>> pairmap;


    public OrderRepository(Map<String, Order> ordermap, Map<String, List<String>> deliverypartnermap) {
        this.ordermap = new HashMap<>();
        this.deliverypartnermap = new HashMap<>();
        this.pairmap=new HashMap<>();

    }

    public void addorder(Order order) {
        // TODO Auto-generated method stub
        ordermap.put(order.getId(), order);
    }

    public void addpartner(String partnerId) {
        // TODO Auto-generated method stub
        DeliveryPartner d=new DeliveryPartner(partnerId);
        deliverypartnermap.put(partnerId, d);

    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        // TODO Auto-generated method stub
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

    public Order getOrderById(String orderId) {
        // TODO Auto-generated method stub
        if(ordermap.containsKey(orderId))
        {
            return ordermap.get(orderId);
        }
        else
        {
            return null;
        }
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        // TODO Auto-generated method stub
        if(deliverypartnermap.containsKey(partnerId))
        {
            return deliverypartnermap.get(partnerId);
        }
        else
        {
            return null;
        }

    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        DeliveryPartner d=null;
        if(deliverypartnermap.containsKey(partnerId))
        {
            d=deliverypartnermap.get(partnerId);
        }
        return d.getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        List<String> list=null;
        if(pairmap.containsKey(partnerId))
        {
            list=pairmap.get(partnerId);
        }
        return list;
    }

    public List<String> getAllOrders() {
        // TODO Auto-generated method stub
        List<String> list=new ArrayList<>();
        for(Map.Entry<String, Order>entry:ordermap.entrySet())
        {
            list.add(entry.getKey());
        }
        return list;
    }

    public Integer getCountOfUnassignedOrders() {
        // TODO Auto-generated method stub
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

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        // TODO Auto-generated method stub
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

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
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

    public void deletePartnerById(String partnerId) {
        // TODO Auto-generated method stub
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

    public void deleteOrderById(String orderId) {
        // TODO Auto-generated method stub
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