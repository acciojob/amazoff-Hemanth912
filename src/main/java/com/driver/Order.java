package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        int i=0;
        String s="";
        while(deliveryTime.charAt(i)!=':')
        {
            s+=deliveryTime.charAt(i);
            i++;
        }
        deliveryTime=deliveryTime.substring(i+1);
        this.id=id;
        this.deliveryTime=Integer.parseInt(s)*60+Integer.parseInt(deliveryTime);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}