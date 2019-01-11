import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
 * DO NOT MODIFY ANYTHING IN THIS INTERFACE
 */
interface PrintableObject {
    void printOn(Printer printer);
}

interface Printer {
    /*
     * TODO:
     * Add signatures of methods here in order to implement
     * Double Dispatch
     */

    void printText(Text txt);

    void printSphere(Sphere sphere);

    void printCube(Cube cube);
}

/*
 * TODO
 * Add your own classes
 */

class PrinterInkjet implements Printer {

    @Override
    public void printText(Text txt) {
        System.out.println("printerinkjet printed text");
    }

    @Override
    public void printSphere(Sphere sphere) {
        System.out.println("printerinkjet cannot print sphere");
    }

    @Override
    public void printCube(Cube cube) {
        System.out.println("printerinkjet cannot print cube");
    }
}

class Printer3D implements Printer {

    @Override
    public void printText(Text txt) {
        System.out.println("printer3d printed text");
    }

    @Override
    public void printSphere(Sphere sphere) {
        System.out.println("printer3d printed sphere");
    }

    @Override
    public void printCube(Cube cube) {
        System.out.println("printer3d printed cube");
    }
}

class Text implements PrintableObject {

    @Override
    public void printOn(Printer printer) {
        printer.printText(this);
    }
}

class Sphere implements PrintableObject {

    @Override
    public void printOn(Printer printer) {
        printer.printSphere(this);
    }
}

class Cube implements PrintableObject {

    @Override
    public void printOn(Printer printer) {
        printer.printCube(this);
    }
}


/*
 * DO NOT MODIFY ANYTHING IN THIS CLASS
 */
class Client {

    /**
     * Prints all objects on each of the printers.
     */
    void printAllEverywhere(PrintableObject[] objects, Printer[] printers) {
        for (int i = 0; i < objects.length; i++) {
            PrintableObject obj = objects[i];
            for (int j = 0; j < printers.length; j++) {
                Printer printer = printers[j];

                obj.printOn(printer);
                // must work for any printer or object !
            }
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        /*
         * TODO:
         * Read INPUT and parse it
         */
        Scanner scan = new Scanner(System.in);
        Printer[] printers = null;
        PrintableObject[] printable = null;
        int i = 0;
        while (i < 4) {
            if (scan.hasNextInt()) {
                int size = scan.nextInt();
                if (i == 0) {
                    printers = new Printer[size];
                } else {
                    printable = new PrintableObject[size];
                }
                scan.nextLine();
                i++;
                continue;
            }
            String line = scan.nextLine();
            Scanner lineScan = new Scanner(line);
            lineScan.useDelimiter(" ");
            int count = 0;
            while (lineScan.hasNext()) {
                String part = lineScan.next();
                if (part.equals("printerinkjet")) {
                    PrinterInkjet printer = new PrinterInkjet();
                    if (printers != null) {
                        printers[count] = printer;
                    }
                } else if (part.equals("printer3d")) {
                    Printer3D printer = new Printer3D();
                    if (printers != null) {
                        printers[count] = printer;
                    }
                } else if (part.equals("text")) {
                    Text text = new Text();
                    if (printable != null) {
                        printable[count] = text;
                    }
                } else if (part.equals("sphere")) {
                    Sphere sphere = new Sphere();
                    if (printable != null) {
                        printable[count] = sphere;
                    }
                } else if (part.equals("cube")) {
                    Cube cube = new Cube();
                    if (printable != null) {
                        printable[count] = cube;
                    }
                }
                count++;
            }
            i++;
        }
        scan.close();

        /*
         * TODO:
         * Instantiate Client and call printAllEverywhere
         */

        new Client().printAllEverywhere(printable, printers);
    }
}