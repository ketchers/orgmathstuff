package org.mathstuff.dsa.appendix_e;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PrintWriterPlay {

    public PrintWriterPlay() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        String fileName = args[0];
        PrintWriter pr = null;
        Scanner input = null;
        try {
            pr = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + fileName);
        }

        input = new Scanner(System.in);
        System.out.println("Enter some text and use ^D to stop: ");
        while (input.hasNext())
            pr.print(input.nextLine());

        pr.close();
        input.close();
    }

}
