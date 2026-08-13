package org.atf2933;
import java.time.LocalDate;

public abstract class MaintenanceTask extends vehicleDet implements Serviceable { //implemets interface servicable and overrides resetcounter (needed for polymorphism)

    private int mileageKilometers;
    private int currentMileageKilometers;
    public String taskName;
    public int interval;

    public MaintenanceTask() {
        currentMileageKilometers = 0;
        mileageKilometers = 0;
    }
    public MaintenanceTask(int mileageKilometers, int currentMileageKilometers){
        this.mileageKilometers=mileageKilometers;
        this.currentMileageKilometers = currentMileageKilometers;
    }

    public  void setMileageKilometers(int mileageKilometers) {
        this.mileageKilometers = mileageKilometers;
    }

    public void setCurrentMileageKilometers(int currentMileageKilometers){
        this.currentMileageKilometers=currentMileageKilometers;
    }

    public int getMileageKilometers(){
        return mileageKilometers;
    }
    public int getCurrentMileageKilometers(){
        return currentMileageKilometers;
    }

  public abstract boolean isDue(); //abstract method isDue()
}
