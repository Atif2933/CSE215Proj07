package org.atf2933;

public class Economy { //CONCEPT FOR LAB PROJECT.

   public String name;
   public int price;
   public String quality;

    public Economy(){
       name = "UNDEFINED";
       price = 0;
       quality = "NULL";
    }

    public Economy(String model, int price,String quality) {
        this.name=name;
        this.price = price;
        this.quality=quality;
    }

    Economy goodTire = new Economy("Toyo Grip Wide", 5020,"Best");
    Economy medTire = new Economy("Yokohama 2FE Balanced",4320,"Average");
    Economy badTire = new Economy("Company Stocks Non-Grip",2050,"Bad");
    Economy oil1 = new Economy("45",6060,"Average");
    Economy oil2 = new Economy("86",3045,"Bad");
    Economy brakeFluid = new Economy("Stock BrakeFluid", 1000,"Average");
    Economy transmissionFluid = new Economy("Stock Transmission Fluid", 1500,"Average");

}
