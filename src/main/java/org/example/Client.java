
package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket s = new Socket("127.0.0.1",5000);

        String line = "";

        Scanner in = new Scanner(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        while(true) {
            while(in.hasNextLine()){
                line = in.nextLine();

                if(line.equals("END"))
                    break;
                else
                    System.out.println(line);
            }
            //System.out.println("Out of while loop");
            Scanner userInput = new Scanner(System.in);
            String choice = userInput.nextLine();
            System.out.println("client choose choice : "+ choice);

            out.println(choice);
            if (choice.matches("1")) {
                //Enter your name
                line = in.nextLine();
                System.out.println(line);
                //response
                line = userInput.nextLine();
                out.println(line);

                //Enter date
                line = in.nextLine();
                System.out.println(line);
                //response
                line = userInput.nextLine();
                out.println(line);

                //Enter time
                line = in.nextLine();
                System.out.println(line);
                //response
                line = userInput.nextLine();
                out.println(line);

                //Enter phoneNumber
                line = in.nextLine();
                System.out.println(line);
                //response
                line = userInput.nextLine();
                out.println(line);
                // is complete
                line = in.nextLine();
                System.out.println(line);


            } else if (choice.matches("2")) {

                // receive all reservations data
                line = in.nextLine();
                System.out.println(line);

            } else if (choice.matches("3")) {

                // Enter phoneNumber
                line = in.nextLine();
                System.out.println(line);
                //response
                line = userInput.nextLine();
                out.println(line);
                // results
//                line = in.nextLine();
//                System.out.println(line);

            } else if (choice.matches("4")) {

                // Enter phone number
                line = in.nextLine();
                System.out.println(line);
                // response
                line = userInput.nextLine();
                out.println(line);

            } else if (choice.matches("5")) {

                line = in.nextLine();
                System.exit(0);

            }
        }

    }
}