package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        int i=0;
        String hr="";
        while (deliveryTime.charAt(i)!=':')
        {
            hr+=deliveryTime.charAt(i);
            i++;
        }
        deliveryTime = deliveryTime.substring(i+1);
        this.deliveryTime = Integer.parseInt(hr)*60+Integer.parseInt(deliveryTime);

    }


    public Order() {
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
