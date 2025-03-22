
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Exercise01 (First exercise)
// Files:            Ex1.java
// Semester:         Spring 2025
//
// Author:           Pablo Daniel Jelsky
// Email:            PabloDanielJelsky@Gmail.com
// CS Login:         pablo.jelsky@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   The headers in this file were taken as an example from
//                   https://pages.cs.wisc.edu/~cs302/resources/guides/commenting.html
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Scanner;

/**
 * Ex1 class
 * 
 * @implNote This class is an exercise that should implement the following 4
 *           methods and test them.
 *           1) void unitTransformer()
 *           2) void calcVAT()
 *           3) double rideCost(double, double, double)
 *           4) int seriesNum()
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (Pablo Daniel Jelsky)
 */
public class Ex1 {
    public static void main(String[] args) throws Exception {
        // Gets a length from operator (in inches), transforms it to millimeters and
        // then displays the converted length to output.
        unitTransformer();

        // Gets a length from operator the price before taxes, calculates the product
        // tax, and the product price with tax and then displays both of them to the
        // output. We take for granted that the tax is 17% of the product price.
        calcVAT();

        // We define the three inputs to be fed into the rideCost() method: ride length,
        // petrol price, and distance per liter.
        // They are fed into the rideCost() method and get from it the ride cost in
        // NIS that is displayed to the operator.
        double rideInKilometers = 17;
        double petrolLiterPriceInNis = 6.20;
        double kilometersPerPetrolLiter = 8.50;
        double ridePriceInNis = rideCost(rideInKilometers, petrolLiterPriceInNis, kilometersPerPetrolLiter);
        // Displays at the operator output the inputs and result from the rideCost()
        // method example
        System.out.println(
                "For a ride of " + rideInKilometers + " kilometers, and knowing that the petrol liter price is "
                        + petrolLiterPriceInNis + " NIS and the car does " + kilometersPerPetrolLiter
                        + " kilometers with one petrol liter,\nthen the ride cost is " + ridePriceInNis + " NIS");

        // Gets a series of incresing number from operator and once the input number is
        // not a larger number than previous one, the method returns the sum of the
        // numbers
        // that were entered until the last one.
        int sumOfIncresingNumberSeries = seriesNum();
        // Displays at the operator output the sum of the increasing numbers series
        // done inside the seriesNum() method
        System.out.println("The sum of the increasing numbers series is: " + sumOfIncresingNumberSeries);
    }

    /**
     * unitTransformer method
     * 
     * @implNote Gets a length from operator (in inches), transforms it to
     *           millimeters and then displays the converted length to output.
     *
     * @param () (No parameters)
     * @return (No return value)
     */
    public static void unitTransformer() {
        // Input
        Scanner scanLengthLine = new Scanner(System.in);
        System.out.println("Enter a length in inches to be converted to millimeters");
        double inputLengthInInches = scanLengthLine.nextDouble();

        // Process
        double conversionFactorFromInchesToMillimeters = 25.40;
        double outputLengthInMillimeters = inputLengthInInches * conversionFactorFromInchesToMillimeters;

        // Output
        System.out.println(inputLengthInInches + " inches is equal to " + outputLengthInMillimeters + " millimeters");

        // TODO: I do NOT know why we need to close the Scanner scanLengthLine and
        // scanProductLine objects that in priciple should have been totally isolated
        // from Scanner scanIntegerLine, but if we do NOT remark the following line,
        // the program will CRASH
        // scanLengthLine.close(); // Closes the scanLengthLine object in order not to
        // leak it
    }

    /**
     * calcVAT method
     * 
     * @implNote Gets a length from operator the price before taxes, calculates
     *           the product tax, and the product price with tax and then displays
     *           both of them to the output. We take for granted that the tax
     *           is 17% of the product price.
     *
     * @param () (No parameters)
     * @return (No return value)
     */
    public static void calcVAT() {
        // Input
        Scanner scanProductLine = new Scanner(System.in);
        System.out.println("Enter the product price in NIS - not including tax");

        // Process
        double productPriceBeforeTax = scanProductLine.nextDouble();
        double productTaxFactor = 0.17;
        double productTax = productPriceBeforeTax * productTaxFactor;
        double productPriceAfterTax = productPriceBeforeTax + productTax;

        // Output
        System.out.println("Product price including tax: " + productPriceAfterTax + " NIS, and the tax itself is "
                + productTax + " NIS");

        // TODO: I do NOT know why we need to close the Scanner scanLengthLine and
        // scanProductLine objects that in priciple should have been totally isolated
        // from Scanner scanIntegerLine, but if we do NOT remark the following line,
        // the program will CRASH
        // scanProductLine.close(); // Closes the scanProductLine object in order not to
        // leak it
    }

    /**
     * rideCost method
     * 
     * @implNote rideCost() method takes 3 parameters, ride length, petrol price,
     *           and distance per liter, calculates with this info the ride
     *           cost and returns back to caller.
     *
     * @param (rideInKilometers)         (total distance ride in kilometers)
     * @param (petrolLiterPrice)         (petrol liter price in money units)
     * @param (kilometersPerPetrolLiter) (distance in kilometers, the car does with
     *                                   one liter of petrol)
     * @return (ride price in money units)
     */
    public static double rideCost(double rideInKilometers, double petrolLiterPrice, double kilometersPerPetrolLiter) {
        // Process
        double ridePrice = rideInKilometers * petrolLiterPrice / kilometersPerPetrolLiter;

        // Output
        return ridePrice;
    }

    /**
     * seriesNum method
     * 
     * @implNote Gets a series of incresing number from operator and once the
     *           input number is not a larger number than previous one, the
     *           method returns the sum of the numbers that were entered until
     *           the last one.
     *
     * @param () (No parameters)
     * @return (Sum of the increasing number in the series)
     */
    public static int seriesNum() {
        // Input
        Scanner scanIntegerLine = new Scanner(System.in);
        System.out.println("Enter an integer");
        int seriesNumberInput = scanIntegerLine.nextInt();

        // Process
        int sumOfIncresingNumberSeries = seriesNumberInput;
        // The following definition by decreasing by 1 the last input
        // is done to enter always into the while loop after this
        // first calling to the nextInt()
        int lastEnteredNumber = seriesNumberInput - 1;

        // Input
        System.out.println(
                "Enter an increasing integer (from previous one) to continue summing up, otherwise will stop summing up");
        while ((seriesNumberInput = scanIntegerLine.nextInt()) > lastEnteredNumber) {
            // Process
            lastEnteredNumber = seriesNumberInput;
            sumOfIncresingNumberSeries += seriesNumberInput;
        }

        scanIntegerLine.close(); // Closes the scanIntegerLine object in order not to leak it

        // Output
        return sumOfIncresingNumberSeries;
    }
}
