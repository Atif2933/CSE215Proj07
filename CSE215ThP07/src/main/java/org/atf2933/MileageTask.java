package org.atf2933;

public class MileageTask extends MaintenanceTask implements Serviceable{ //overrides resetCounter() from Servicable

    public MileageTask()
    {
        super(); //initialzes all variables from superclass
        taskName = "UNKNOWN";
        interval = 0;
    }
    public MileageTask(String taskName, int interval){
        super();
        this.taskName = taskName;
        this.interval = interval;
    }
    @Override
    public void resetCounter() { //sets current odometer reading to the old one
        setMileageKilometers(getCurrentMileageKilometers());
        System.out.println(this.taskName+" updated successfully!");
    }
    @Override
    public boolean isDue() {
          return ((getCurrentMileageKilometers()-getMileageKilometers())>=this.interval);
    }
}
