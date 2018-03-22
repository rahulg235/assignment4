package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Rahul Gupta>
 * <rg43226>
 * Wed 9-10:30
 * Kaajol Dhana
 * krd985
 * Wed 9-10:30
 * Slip days used: <0>
 * Fall 2016
 */
//import org.omg.CORBA.DynAnyPackage.Invalid;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.*;
/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {
    static Scanner kb;  // scanner connected to keyboard input, or input file
    private static String inputFile;    // input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;  // if test specified, holds all console output
    private static String myPackage;    // package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;    // if you want to restore output to console
    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name,
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }
        /* Do not alter the code above for your submission. */
        /* Write your code below. */

        // System.out.println("GLHF");
        System.out.print("critters> ");
        while(true && kb.hasNextLine())
        {
            System.out.flush();
            String line = kb.nextLine();
            if(line.equals("quit"))
            {
                break;
            }
            if(line.equals("show"))
            {
                System.out.println();
                Critter.displayWorld();
                System.out.print("critters> ");
                continue;
            }
            String[] words = line.split("\\s+");
            if(words[0].equals("step"))
            {
                if(words.length>2)
                {
                    System.out.println("error processing: " + line);
                    System.out.print("critters> ");
                    continue;
                }
                if(words.length==1)
                {
                    Critter.worldTimeStep();
                    System.out.print("critters> ");
                    continue;
                }
                try
                {
                    int numberOfSteps = Integer.parseInt(words[1]);
                    while (numberOfSteps > 0) {
                        Critter.worldTimeStep();
                        numberOfSteps--;
                    }
                    System.out.print("critters> ");
                    continue;
                }
                catch(Exception e)
                {
                    System.out.println("error process: " + line);
                    System.out.print("critters> ");
                    continue;
                }
            }
            if(words[0].equals("seed"))
            {
                if(words.length!=2)
                {
                    System.out.println("error processing: " + line);
                    System.out.print("critters> ");
                    continue;
                }
                try
                {
                    int seed = Integer.parseInt(words[1]);
                    Critter.setSeed(seed);
                    System.out.print("critters> ");
                    continue;
                }
                catch(Exception e)
                {
                    System.out.println("error process: " + line);
                    System.out.print("critters> ");
                    continue;
                }
            }
            if(words[0].equals("make"))
            {
                if(words.length==2) {
                    try
                    {
                        String prefixed = "assignment4." + words[1];
                        Critter.makeCritter(prefixed);
                        System.out.print("critters> ");
                        continue;
                    }
                    catch (InvalidCritterException e)
                    {
                        System.out.println("error process: " + line);
                        System.out.print("critters> ");
                        continue;
                    }
                }
                else if(words.length==3)
                {
                    try
                    {
                        int seed = Integer.parseInt(words[2]);
                        while(seed>0)
                        {
                            try
                            {
                                String prefixed = "assignment4." + words[1];
                                Critter.makeCritter(prefixed);
                                seed--;
                            }
                            catch (InvalidCritterException e)                                                                      //Thrown if invalid critter class
                            {
                                System.out.println("error process: " + line);
                                break;
                            }
                        }
                        System.out.print("critters> ");
                        continue;
                    }
                    catch(Exception e)                                                                                              //Checks if words[2] is int
                    {
                        System.out.println("error process: " + line);
                        System.out.print("critters> ");
                        continue;
                    }
                }
                else
                {
                    System.out.println("error process: " + line);
                    System.out.print("critters> ");
                    continue;
                }
            }
            if(words[0].equals("stats"))
            {
                if(words.length!=2)
                {
                    System.out.println("error processing: " + line);
                    System.out.print("critters>" );
                    continue;
                }
                try
                {
                    String prefixed = "assignment4." + words[1];
                    java.util.List<Critter> theInstances = Critter.getInstances(prefixed);
                    //System.out.println(theInstances.size() + " total " + words[1]);
                    Class newCritterClass = Class.forName(prefixed);
                    Critter newCritter = (Critter) newCritterClass.newInstance();
                    List<Critter> instanceList = Critter.getInstances(prefixed);
                    Method s = newCritter.getClass().getMethod("runStats", List.class);
                    s.invoke(null, instanceList);
                    //System.out.flush();
                    System.out.print("critters>" );                                                                                                                //1000 total Craigs
                    continue;
                }
                catch (InvalidCritterException e)
                {
                    System.out.println("error process: " + line);
                    System.out.print("critters> ");
                    continue;
                }
                catch(Exception e)
                {
                    System.out.println("error process: " + line);
                    System.out.print("critters> ");
                    continue;
                }
            }
            if(!words[0].equals("quit") && !words[0].equals("show") && !words[0].equals("step") && !words[0].equals("seed") && !words[0].equals("make") && !words[0].equals("stats"))
            {
                System.out.println("invalid command: " + line);
                System.out.print("critters> ");
                continue;
            }
        }
        /* Write your code above */
        System.out.flush();
    }
}