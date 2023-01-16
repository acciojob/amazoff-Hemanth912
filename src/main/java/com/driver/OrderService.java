/*package com.driver;

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

}*/
package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository rep;

    public void addorder(Order order) {
        // TODO Auto-generated method stub
        rep.addorder(order);

    }

    public void addpartner(String partnerId) {
        // TODO Auto-generated method stub
        rep.addpartner(partnerId);

    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        // TODO Auto-generated method stub
        rep.addOrderPartnerPair(orderId,partnerId);

    }

    public Order getOrderById(String orderId) {
        // TODO Auto-generated method stub
        return rep.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        // TODO Auto-generated method stub
        return rep.getPartnerById(partnerId);

    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        return rep.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        return rep.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        // TODO Auto-generated method stub
        return rep.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        // TODO Auto-generated method stub
        return rep.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        // TODO Auto-generated method stub
        return rep.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        // TODO Auto-generated method stub
        return rep.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        // TODO Auto-generated method stub
        rep.deletePartnerById(partnerId);

    }

    public void deleteOrderById(String orderId) {
        // TODO Auto-generated method stub
        rep.deleteOrderById(orderId);

    }



}
