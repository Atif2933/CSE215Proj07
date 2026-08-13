package org.atf2933;

import java.time.LocalDate;

public  class vehicleDet { //for key vehicle details
    private String vehicleName;
    private String brand;
    private LocalDate registrationDate;
    public String tires;


    public vehicleDet(){ //non-parameter contructor
        registrationDate = LocalDate.of(2024,3,16);
        brand = "UNKNOWN";
        vehicleName = "UNKNOWN";
        tires = "UNKNOWN";

    }
    public vehicleDet(String vehicleName, String brand, LocalDate registrationDate, String tires){//parameterized constructor
        this.vehicleName=vehicleName;
        this.brand=brand;
        this.registrationDate=registrationDate;
        this.tires=tires;
    }

    public  void setRegistrationDate(LocalDate registrationDate){ //get-set method for registration dates
        this.registrationDate = registrationDate;
    }
    public LocalDate getRegistrationDate(){
        return registrationDate;
    }
    public String getVehicleName(){
        return vehicleName;
    } //get-set methods for car model and brand
    public String getBrand(){
        return brand;
    }
}
