package org.atf2933;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import javax.swing.*;


public class Swing {

     public static void main(String[] args) {
//standard object declaration for VehicleDet class
         vehicleDet v = new vehicleDet("Silvia S15", "Nissan", LocalDate.of(2026,3,16),"Toyo F15");
         //declaring m1,m2 arrays for car1 and car2 because maintenanceClass is an abstract class.
         MaintenanceTask[] m1 = new MaintenanceTask[5];
         vehicleDet v2 = new vehicleDet("MR-S", "Toyota",LocalDate.of(2023,9,12),"Bridgestone 210X");
         MaintenanceTask[] m2 = new MaintenanceTask[5];
         m1[0]  = new MileageTask("Engine Oil Change",5000); //calling the child class with polymorphism
         m1[1]  = new MileageTask("Brake Fluid Change", 8000); //setMileageInKilometer will only be invoked whenever currentmileage-mileage is greater than interval and these update individually
         m1[2]= new MileageTask("Transmission Fluid Change",12000); //so if last engineOilChange was at 48000 kilos, if we dont change the oil this reading will stay at 48k
         m1[3] = new MileageTask("Tire Change",20000); //but if we change other fluid only those fluids reading will update and not engine oil change.
         m1[4] = new TimeTask(); //element number 4 only reserved for the core mileage reading and registration date
         m2[0]  = new MileageTask("Engine Oil Change",5000);
         m2[1]  = new MileageTask("Brake Fluid Change", 8000);
         m2[2]= new MileageTask("Transmission Fluid Change",12000);
         m2[3] = new MileageTask("Tire Change",20000);
         m2[4] = new TimeTask();
         for (MaintenanceTask maintenanceTask : m1) { //enhanced for-loop to set the first cars' core-details

             maintenanceTask.setMileageKilometers(48868);
             maintenanceTask.setRegistrationDate(LocalDate.of(2026, 3, 16));
             maintenanceTask.tires = "Toyo F15";
         }
         for(int i =0; i<m2.length;i++){ //normal for-loop to set the second cars' core-details.

             m2[i].setMileageKilometers(69210);
             m2[i].setRegistrationDate(LocalDate.of(2023,9,12));
             m2[i].tires = "Bridgestone 210X";
         }
         JFrame frame = new JFrame("Vechicle Logger ver.b1"); //creating a new window

         frame.setLayout(new BorderLayout()); //this has a layout and not null so we dont need to set bounds for everything

         frame.setSize(1366,768); //setting the size of the window in standard resolution
         String [] columns = {"Brand","Car Model","Last Updated Odometer Reading","Registration Date","Tires"}; //calling array for filling in JTable
         Object[][] data = {
                 {
                         v.getBrand(),
                         v.getVehicleName(),
                         m1[0].getMileageKilometers(),
                         m1[0].getRegistrationDate(),
                         m1[0].tires
                 },
                 {
                         v2.getBrand(),
                         v2.getVehicleName(),
                         m2[0].getMileageKilometers(),
                         m2[0].getRegistrationDate(),
                         m2[0].tires
                 }
         };
         JTable table = new JTable(data, columns); //invoking JTable
         table.setRowHeight(30);
         table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //Forces the GUI to only be able to select one column or row
         JScrollPane scrollPane = new JScrollPane(table); //allows user to scroll if there are too many entries
         JPanel buttonPanel = new JPanel();
         JButton oilButton =
                 new JButton("Check for Oil and Fluid Changes");
         JButton registrationButton =
                 new JButton("Check for Registration Renewal");
         buttonPanel.add(oilButton); //must needed for the buttons to appear
         buttonPanel.add(registrationButton);
         frame.add(scrollPane, BorderLayout.CENTER);
         frame.add(buttonPanel, BorderLayout.SOUTH);
         oilButton.addActionListener(e -> {

             int selectedRow = table.getSelectedRow(); //first selected row ==0; getSelectedRow() returns the row user selected
             if(selectedRow == -1) //if no row is selected or row 0/[-1] returns an error
             {
                 JOptionPane.showMessageDialog(
                         frame,
                         "Please select a vehicle first!"
                 );
                 return;
             }
             String input = JOptionPane.showInputDialog(
                     frame,
                     "Enter current odometer reading:"
             );
             if(input == null)
                 return;
             int currentMileage = Integer.parseInt(input); //acts like a scanner, scans current odometer reading of selected car
             MaintenanceTask[] selectedTasks; //declaring selectedTask object so it assigns the clicked entry to the actual car m1[]/m2[]
             if(selectedRow == 0)
             {
                 selectedTasks = m1;
             }
             else
             {
                 selectedTasks = m2;
             }
             // Update the current mileage for all mileage tasks
             for(int i = 0; i < 4; i++)
             {
                 selectedTasks[i].setCurrentMileageKilometers(currentMileage);
             }
             // Update the vehicle's displayed mileage
             selectedTasks[4].setMileageKilometers(currentMileage);
            //refereshed value (last updated odometer reading, selected car number, column number)
             table.setValueAt(currentMileage, selectedRow, 2);
             //creating a new window to show if maintenances are due or not
             JFrame maintenanceFrame = new JFrame("Oil and Fluid Maintenance");
             maintenanceFrame.setSize(700,300);
             maintenanceFrame.setLocationRelativeTo(frame);
             maintenanceFrame.setLayout(new GridLayout(4,4,10,10));
             for(int i = 0; i < 4; i++) //scans through selectedTasks[i]
             {
                 JLabel taskLabel = new JLabel(selectedTasks[i].taskName); //each has their own taskName
                 JLabel statusLabel;
                 if(selectedTasks[i].isDue()) //initiating a through label if-else condition to return if maintenances are due or not
                     statusLabel = new JLabel("DUE");
                 else
                     statusLabel = new JLabel("NOT DUE");

                 JLabel mileageLabel = new JLabel( //shows when the last changes happened
                         "Last Updated: " +
                                 selectedTasks[i].getMileageKilometers()
                 );

                 JButton updateButton = new JButton("Update");
                 updateButton.setEnabled(selectedTasks[i].isDue()); //reads isDue() and enables/disables updates
                 final int index = i;
                 updateButton.addActionListener(ev -> {
                     selectedTasks[index].resetCounter();
                     mileageLabel.setText(
                             "Last Updated: " +
                                     selectedTasks[index].getMileageKilometers()
                     );
                     JOptionPane.showMessageDialog(
                             maintenanceFrame,
                             selectedTasks[index].taskName +
                                     " updated successfully!"
                     );

                 });
                 maintenanceFrame.add(taskLabel);
                 maintenanceFrame.add(statusLabel);
                 maintenanceFrame.add(mileageLabel);
                 maintenanceFrame.add(updateButton);
             }

             maintenanceFrame.setVisible(true);

         });
         registrationButton.addActionListener(e -> { //listens for registration check call

             int selectedRow = table.getSelectedRow();

             if(selectedRow == -1)
             {
                 JOptionPane.showMessageDialog(frame,
                         "Please select a vehicle first!");
                 return;
             }
             TimeTask registrationTask; //same as oilchange, declaring an object
             if(selectedRow == 0) //assigning the car numbers through polymorphism
             {
                 registrationTask = (TimeTask) m1[4];
             }
             else
             {
                 registrationTask = (TimeTask) m2[4];
             }
             if(registrationTask.isDue())
             {
                 int choice = JOptionPane.showConfirmDialog(
                         frame,
                         "Registration renewal is due. Do you want to renew?",
                         "Registration Renewal",
                         JOptionPane.YES_NO_OPTION //yes or no option selection if the user wants to renew or not
                 );
                 if(choice == JOptionPane.YES_OPTION)
                 {
                     registrationTask.resetCounter();
                     // Update JTable registration date column
                     table.setValueAt(
                             registrationTask.getRegistrationDate(),
                             selectedRow,
                             3
                     ); //displaying all updated details for car after inputting values
                     JOptionPane.showMessageDialog(
                             frame,
                             "Registration renewed successfully!"
                     );

                 }
                 else
                 {
                     JOptionPane.showMessageDialog(
                             frame,
                             "Registration was not renewed."
                     );
                 }
             }
             else
             {
                 JOptionPane.showMessageDialog(
                         frame,
                         "Registration is not due."
                 );
             }
         });
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);


    }
}
