package org.atf2933;
import java.util.*;
import java.time.LocalDate; //for using real time to check the registration renewal

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
     static void main(String[] args) {
        //calling vehicledet for car name,brand and date
        vehicleDet v = new vehicleDet("Silvia S15", "Nissan", LocalDate.of(2026, 3, 16), "Toyo F15");
        vehicleDet v2 = new vehicleDet("MR-S", "Toyota", LocalDate.of(2023, 9, 12), "Bridgestone 210X");
        MaintenanceTask[] m1 = new MaintenanceTask[5]; //since maintenance task is abstract, we declare an array of that class then call the child class
        MaintenanceTask[] m2 = new MaintenanceTask[5];
        m1[0] = new MileageTask("Engine Oil Change", 5000); //polymorphism
        m1[1] = new MileageTask("Brake Fluid Change", 8000); //polymorphism
        m1[2] = new MileageTask("Transmission Fluid Change", 12000);
        m1[3] = new MileageTask("Tire Change", 20000);
        m1[4] = new TimeTask(); //polymorphism
        m2[0] = new MileageTask("Engine Oil Change", 5000);
        m2[1] = new MileageTask("Brake Fluid Change", 8000);
        m2[2] = new MileageTask("Transmission Fluid Change", 12000);
        m2[3] = new MileageTask("Tire Change", 20000);
        m2[4] = new TimeTask();

         for (MaintenanceTask maintenanceTask : m1) { //enhanced loop to set odometer reading and tires to every task

             maintenanceTask.setMileageKilometers(48868);
             maintenanceTask.setRegistrationDate(LocalDate.of(2026, 3, 16));
             maintenanceTask.tires = "Toyo F15";
         }
        for (int i = 0; i < m2.length; i++) { //normal loop to set odometer reading and tires to second car's task

            m2[i].setMileageKilometers(69210);
            m2[i].setRegistrationDate(LocalDate.of(2023, 9, 12));
            m2[i].tires = "Bridgestone 210X";
        }


        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Atif's vehicle checker!");


        System.out.println("Car Brand   Car Model            Last Updated Mileage(in km)   Last Updated Registration Date   Tires");
        System.out.println("=========   =========            ===========================   ==============================   =====");
        System.out.println(v.getBrand() + "   " + v.getVehicleName() + "               " + m1[0].getMileageKilometers() + "                                 " + m1[0].getRegistrationDate() + "      " + m1[0].tires);
        System.out.println(v2.getBrand() + "   " + v2.getVehicleName() + "               " + m2[0].getMileageKilometers() + "                                 " + m2[0].getRegistrationDate() + "      " + m2[0].tires);

     do { //switch case for selecting car1 or car2
         System.out.println("Enter Car number: ");
         int choice = scan.nextInt();


         switch (choice) {
             case 1: { //nested swtich-case, here 1 checks for oils and fluids and 2 for registration
                 System.out.println("Enter choice: ");
                 int choice1 = scan.nextInt();
                 switch (choice1) {
                     case 1: {
                         System.out.println("Checking for Oil and Fluid!");
                         System.out.println("Enter current Mileage: ");
                         scan.nextLine();
                         int currentMileage = scan.nextInt();
                         for (int l = 0; l < m1.length - 1; l++) {
                             m1[l].setCurrentMileageKilometers(currentMileage);
                             System.out.println(m1[l].taskName + " " + m1[l].isDue());
                             if(m1[l].isDue()){
                                 System.out.println("Do you want to initiate "+m1[l].taskName+"? 1 for yes 2 for no"+" (Last Updated: "+m1[l].getMileageKilometers());
                                 int choice3 = scan.nextInt();
                                 switch (choice3) { //another inner switch-case to check client's choice 1 calls resetCount() and 2 updates the main odometer reading but never resets taskwise mileage
                                     case 1: {
                                         m1[l].resetCounter();
                                         m1[4].setMileageKilometers(currentMileage);
                                         break;
                                     }
                                     case 2: {
                                         System.out.println("Not successful!");
                                         m1[4].setMileageKilometers(currentMileage);
                                         break;
                                     }
                                     default : {
                                         System.out.println("Invalid Choice!");
                                     }
                                 }
                             } else{

                             }

                         }
                         break;
                     }
                     case 2: {
                         System.out.println("Checking for registration!");
                         System.out.println(m1[4].isDue());
                         if (m1[4].isDue()) {
                             System.out.println("Registration is DUE! Do you want to renew? 1 for yes 2 for no");
                             int choice2 = scan.nextInt();
                             if (choice2 == 1) {
                                 m1[4].resetCounter();
                             } else {
                                 System.out.println("Not successful!");


                             }
                         }
                         break;
                     }
                     default: {
                         System.out.println("Invalid Choice!");

                     }
                 }

                 break;
             }
             case 2: { //for car num 2
                 System.out.println("Enter choice: ");
                 int choice1 = scan.nextInt();
                 switch (choice1) {
                     case 1: {
                         System.out.println("Checking for Oil and Fluid!");
                         System.out.println("Enter current Mileage: ");
                         scan.nextLine();
                         int currentMileage = scan.nextInt();
                         for (int l = 0; l < m2.length - 1; l++) {
                             m2[l].setCurrentMileageKilometers(currentMileage);
                             System.out.println(m2[l].taskName + " " + m2[l].isDue());
                             if(m2[l].isDue()){
                                 System.out.println("Do you want to initiate "+m2[l].taskName+"? 1 for yes 2 for no"+" (Last Updated: "+m2[l].getCurrentMileageKilometers());
                                 int choice3 = scan.nextInt();
                                 switch (choice3) {
                                     case 1: {
                                         m2[l].resetCounter();
                                         m2[4].setMileageKilometers(currentMileage);
                                         break;
                                     }
                                     case 2: {
                                         System.out.println("Not successful!");
                                         m2[4].setMileageKilometers(currentMileage);

                                         break;
                                     }
                                     default : {
                                         System.out.println("Invalid Choice!");
                                     }
                                 }
                             } else{
                                 continue;
                             }

                         }
                         break;
                     }
                     case 2: {
                         System.out.println("Checking for registration!");
                         System.out.println(m2[4].isDue());
                         if (m2[4].isDue()) {
                             System.out.println("Registration is DUE! Do you want to renew? 1 for yes 2 for no");
                             int choice2 = scan.nextInt();
                             if (choice2 == 1) {
                                 m2[4].resetCounter();
                             } else {
                                 System.out.println("Not successful!");
                             }
                         }
                         break;
                     }
                     default: {
                         System.out.println("Invalid Choice!");
                     }
                     break;
                 }
                 break;

             }
             default: {
                 System.out.println("Invalid Choice!");
             }
         }
         //shows updated table
         System.out.println("Car Brand   Car Model            Last Updated Mileage(in km)   Last Updated Registration Date   Tires");
         System.out.println("=========   =========            ===========================   ==============================   =====");
         System.out.println(v.getBrand() + "   " + v.getVehicleName() + "               " + m1[4].getMileageKilometers() + "                                 " + m1[4].getRegistrationDate() + "      " + m1[0].tires);
         System.out.println(v2.getBrand() + "   " + v2.getVehicleName() + "               " + m2[4].getMileageKilometers() + "                                 " + m2[4].getRegistrationDate() + "      " + m2[0].tires);
     }while (true);



    }
}

