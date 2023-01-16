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