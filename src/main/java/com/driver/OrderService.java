package com.driver;

import org.mapstruct.AfterMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service

public class OrderService {

    @Autowired
    OrderRepository orderepository;

    public void addOrder(Order order)
    {
        orderepository.addOrderToDb(order);
    }
    public void addPartner(String partnerId)
    {
        orderepository.addPatnerToDb(partnerId);
    }

    public void pairPartnertoOrder(String orderId,String partnerId)
    {
        orderepository.addPartnerToOrder(orderId,partnerId);
    }

    public Order getOrder(String orderId)
    {
        return orderepository.getOrderFromDb(orderId);
    }

    public DeliveryPartner getPartner(String partnerId)
    {
        return orderepository.getPartnerFromDb(partnerId);
    }

    public int orderCountForPartner(String partnerId)
    {
        return orderepository.numOfOrderForPartner(partnerId);
    }

    public List<String> ordersForPartner(String partnerId)
    {
        return orderepository.OrdersForPartner(partnerId);
    }

    public List<String> getAllOrders()
    {
        return orderepository.getAllOrders();
    }

    public int numofOrderUnassigned()
    {
        return orderepository.orderUnassigned();
    }
    public int orderLeftUndfelivered(String time,String id)
    {
        return orderepository.orderleftundelivered(time,id);
    }

    public String lastOrderDeliverd(String id)
    {
        return orderepository.lastDeliverOrder(id);
    }
    public void deletePartner(String partnerId)
    {
        orderepository.deletePartnerById(partnerId);
    }
    public void deleteOrder(String orderId)
    {
        orderepository.deleteOrderById(orderId);
    }

}
