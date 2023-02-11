package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class AppServer
{
    public static void main( String[] args ) throws IOException {

//      network
        ServerSocket ss = new ServerSocket(5000);
        Socket incoming = ss.accept();

        Scanner in = new Scanner(incoming.getInputStream());
        PrintWriter out = new PrintWriter(incoming.getOutputStream(), true);




        Scanner userInput = new Scanner(System.in);
        File tables = new File("C:\\Users\\Hamza Helal Dubh\\Desktop\\CPCS305LAPTesting\\src\\tables.txt");
        File reservations = new File("C:\\Users\\Hamza Helal Dubh\\Desktop\\CPCS305LAPTesting\\src\\reaervations.txt");
        FileWriter writer = new FileWriter(reservations);
        BufferedWriter insert = new BufferedWriter(writer);

        Scanner retrieve = new Scanner(tables);

        int no_of_table = 30;

        // reservation info
        String customer_name;
        String date;
        String time;
        int reservationID = 0;
        String phoneNumber = null;

        ArrayList<String> reservationData;



        String choice = null;
        while(true){

            out.println("<<<<<<<<<<<<<<<...>>>>>>>>>>>>>>>");
            out.println("1: Add new reservation");
            out.println("2: Check all reservations");
            out.println("3: Find a reservation");
            out.println("4: Exit");
            out.println("Enter your choice: ");
            out.println("<<<<<<<<<<<<<<<...>>>>>>>>>>>>>>>");

            out.println("END");

            choice = in.nextLine();

            if (choice.matches("1")){

                // adding reservation to files

                out.println("Enter your name: ");
                customer_name = in.nextLine();
                out.println("Enter Date (dd/mm/yyyy): ");
                date = in.next();
                out.println("Enter Time in 12h format: ");
                time = in.next();
                out.println("Enter your phone Number");
                phoneNumber = in.next();
                System.out.println();
                if(addReservation(no_of_table, insert, customer_name, date, time, reservationID, phoneNumber)){
                    out.println("your reservation is completed successfully");
                    no_of_table -= 1;

                }
                else{
                    out.println("something went wrong, please try again later");
                }
            }
            else if (choice.matches("2")){

                reservationData = listReservations(reservations);

                out.println(reservationData);
            }
            else if(choice.matches("3")){

                //delete a reservation by id
                out.println("Enter your phoneNumber to get Reservation Infotmation");
                phoneNumber = in.next();

                String theReservation = getReservationByPhoneNumber(reservations, phoneNumber);
                out.println(theReservation);

            } else if (choice.matches("4")) {

                out.println("THANK YOU FOR USING OUR SYSTEM");
                insert.close();
                System.exit(0);

            }
        }
    }

    public static boolean addReservation(int no_of_tables, BufferedWriter insertData, String customer_name, String date, String time, int reservationID, String phoneNumber) throws IOException {

        if(no_of_tables > 0){

            insertData.write((reservationID+1) + " : " + phoneNumber + " : " + customer_name + " : " + date + " : " + time + "\n");
            insertData.flush();

            System.out.println("<<< Your table was reserved successfully >>>");
            return true;
        }
        else {
            System.out.println("!!! Sorry there is no enough tables, you can be on the waiting list or have a take away order !!!");
            return false;
        }
    }
    public static ArrayList listReservations(File reservations) throws FileNotFoundException {
        Scanner readData = new Scanner(reservations);
        ArrayList<String> reservationData = new ArrayList<String>();
        while (readData.hasNext()){

            reservationData.add(readData.nextLine());

        }
        return reservationData;
    }
    public static String getReservationByPhoneNumber(File reservations, String phoneNumber) throws FileNotFoundException {

        Scanner findReservation = new Scanner(reservations);
        String result = null;
        String temp = null;
        while(findReservation.hasNext()){

            temp = findReservation.nextLine();

            if (temp.contains(phoneNumber)) {
                result = temp;
            }
        }
        System.out.println(result);
        findReservation.close();
        return result;

    }

}
