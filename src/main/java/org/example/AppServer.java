package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

        String url = "jdbc:mysql://localhost:3306/app";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();


            // reservation info
            String customer_name;
            String date;
            String time;
            int reservationID = 0;
            int phoneNumber = 0;

            ArrayList<String> reservationData;



            String choice = null;
            while(true){

                out.println("<<<<<<<<<<<<<<<...>>>>>>>>>>>>>>>");
                out.println("1: Add new reservation");
                out.println("2: Check all reservations");
                out.println("3: delete a reservation");
                out.println("4: Get reservation Info");
                out.println("5: Exit");
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
                    phoneNumber = in.nextInt();
                    System.out.println();
                    statement.executeUpdate("INSERT INTO `reservations` (`name`, `date`, `time`, `phone`) VALUES ('" + customer_name + "', '" + date + "', '" + time + "', '" + phoneNumber + "');");
                }
                else if (choice.matches("2")){
                    ResultSet resultSet = statement.executeQuery("select * from reservations");
                    while (resultSet.next()) {
                        out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3) + " " + resultSet.getInt(4));
                    }

                }
                else if(choice.matches("3")){

                    //delete a reservation by phone number
                    out.println("Enter your phoneNumber to delete reservation ");
                    phoneNumber = in.nextInt();
                    statement.executeUpdate("Delete from reservations where phone = " + phoneNumber + "");

                    // GET reservation info by phone
                } else if (choice.matches("4")) {

                    out.println("Enter your phoneNumber to get reservation info");
                    phoneNumber = in.nextInt();
                    ResultSet resultSet = statement.executeQuery("select * from reservations where phone = " + phoneNumber + "");
                    while (resultSet.next()) {
                        out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3) + " " + resultSet.getInt(4));
                    }



                } else if (choice.matches("5")) {

                    out.println("THANK YOU FOR USING OUR SYSTEM");
                    connection.close();
                    System.exit(0);

                }
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }

}