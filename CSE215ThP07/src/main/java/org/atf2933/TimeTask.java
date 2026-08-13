package org.atf2933;
import java.time.LocalDate; //using local real-time for resetCount()

public class TimeTask extends MaintenanceTask implements Serviceable{ //overrides resetCount() from interface Servicable
    public TimeTask(){
        super();
    }

    @Override
    public void resetCounter() {
        setRegistrationDate(LocalDate.now());
        System.out.println("Registration updated successfully!");
    }
    @Override
    public boolean isDue() { //checks if registration renewal is due.
        LocalDate currentDate = LocalDate.now();
        if(currentDate.isAfter(getRegistrationDate().plusYears(1))){ //if the current date is exactly a year or before the last updated registration date, it returns true
            return true;
        } else {
            return false;
        }
    }
}
