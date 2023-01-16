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
        String min = deliveryTime.substring(i+1);
        this.deliveryTime = Integer.parseInt(hr)*60+Integer.parseInt(min);

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
