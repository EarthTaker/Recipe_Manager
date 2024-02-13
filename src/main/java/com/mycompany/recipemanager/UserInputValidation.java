package com.mycompany.recipemanager;

import java.util.Scanner;

/**
 * The UserInputValidation class provides methods to validate different types of
 * user inputs in the Recipe Manager application.
 */
public class UserInputValidation {

    //Create variable to hold user options
    public static Integer userOption;

    //Create variable to hold user responses
    public static String userResponse;

    //Create variable to hold user double
    public static double userDouble;

    //Create variable to hold user float
    public static float userFloat;

    //Create scanner to be used throughout all user input validation
    public static Scanner scnr;

    /**
     * Creates an instance of the class and assigns values associated with getting user input.
     */
    public static void initalize() {
        userOption = 0;
        userResponse = "";
        userDouble = 0.0;
        userFloat = 0;

        scnr = new Scanner(System.in);
    }
    
    /**
     * Validates that the user's menu option input is an integer. Informs the
     * user if the input is invalid and sets the userOption to 0 in such cases.
     *
     * @return The user's menu option as an integer.
     */
    public static int validateUserMenuOption() {

        System.out.print("Input: ");

        String userInput = scnr.next();

        if (isInteger(userInput)) {
            userOption = Integer.valueOf(userInput);

        } else {

            //Invalid Menu Option.
            MenuDisplays.appDisplays(0);

            userOption = 0;
        }

        // Consume the remaining newline
        scnr.nextLine();

        return userOption;
    }

    /**
     * Validates the user's input when it's expected to be an integer. Informs
     * the user if the input is invalid and resets userOption to 0.
     *
     * @return The validated user option as an integer.
     */
    public static int validateUserOption() {

        System.out.print("Input: ");

        //Get any input from the user.
        String userInput = scnr.next();

        // Consume the remaining newline
        scnr.nextLine();

        //Catch user input if it is longer than 5 characters and can be parsed as a double
        if (userInput.length() > 5 && isDouble(userInput)) {

            //Inform user of failed input value.
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            System.out.println("The value must be greater than zero and less than 10,000.");
            System.out.println("");
            System.out.println("---------------------------------------------------------");

            //Reset user inputs
            userOption = 0;

            //Parse input to determine its type.
        } else if (isInteger(userInput)) {

            //Update the user's option.
            userOption = Integer.valueOf(userInput);

            if (userOption > 10000) {

                //Inform user of failed input value.
                System.out.println("---------------------------------------------------------");
                System.out.println("");
                System.out.println("The value must be greater than zero and less than 10,000.");
                System.out.println("");
                System.out.println("---------------------------------------------------------");

                //Reset user inputs
                userOption = 0;
            }

        } else {

            //User has attempted to enter a non-numerical value.
            //Inform user of failed input value.
            System.out.println("--------------");
            System.out.println("");
            System.out.println("Invalid Input.");
            System.out.println("");
            System.out.println("--------------");

            //Reset user inputs
            userOption = 0;

        }

        //Return validated input.
        return userOption;
    }

    /**
     * Validates the user's input when a double value is expected. Informs the
     * user if the input is invalid and resets {@code userDouble} to 0.
     *
     * @return The validated user input as a double.
     */
    public static Double validateUserDouble() {

        System.out.print("Input: ");

        String userInput = scnr.next();

        // Consume the remaining newline
        scnr.nextLine();

        if (isDouble(userInput)) {

            userDouble = Double.parseDouble(userInput);

            //If User's input is greater than 5 characters long
            if (userDouble >= 10000) {

                //Inform user of failed input value.
                System.out.println("---------------------------------------------------------");
                System.out.println("");
                System.out.println("The value must be greater than zero and less than 10,000.");
                System.out.println("");
                System.out.println("---------------------------------------------------------");

                //Reset user inputs
                userDouble = 0;
            }

        } else {

            //Inform user of failed input value.
            System.out.println("--------------");
            System.out.println("");
            System.out.println("Invalid Input.");
            System.out.println("");
            System.out.println("--------------");

            userDouble = 0;
        }

        //Return validated input.
        return userDouble;
    }

    /**
     * Validates the user's input when a float value is expected. Informs the
     * user if the input is invalid and resets userFloat to 0.
     *
     * @return The validated user input as a float.
     */
    public static Float validateUserFloat() {

        System.out.print("Input: ");

        String userInput = scnr.next();

        // Consume the remaining newline
        scnr.nextLine();

        if (isFloat(userInput)) {

            userFloat = Float.parseFloat(userInput);

            //If User's input is greater than 5 characters long
            if (userFloat >= 10000) {

                //Inform user of failed input value.
                System.out.println("---------------------------------------------------------");
                System.out.println("");
                System.out.println("The value must be greater than zero and less than 10,000.");
                System.out.println("");
                System.out.println("---------------------------------------------------------");

                //Reset user inputs
                userFloat = 0;
            }

        } else {

            //Inform user of failed input value.
            System.out.println("--------------");
            System.out.println("");
            System.out.println("Invalid Input.");
            System.out.println("");
            System.out.println("--------------");

            userFloat = 0;
        }

        //Return validated input.
        return userFloat;
    }

    /**
     * Validates the user's input when a string is expected. Captures the input
     * and returns it, ensuring it is a valid string.
     *
     * @return The validated user response as a string.
     */
    public static String validateUserFeedback() {

        System.out.print("Input: ");
        String userInput = scnr.nextLine();

        //Prevent user responses larger than 100 characters.
        if (userInput.length() > 100) {

            //Reset user feedback.
            userResponse = "";
        } else {

            //Assign value to global value to use throughout user validations.
            userResponse = userInput;
        }

        //Return validated input.
        return userResponse;
    }

    /**
     * Validates the user's input when a float value is expected. Informs the
     * user if the input is invalid and resets userFloat to 0.
     *
     * @return The validated user input as a float.
     */
    private static boolean isFloat(String userInput) {
        //Attempt to get the value of the user's input
        try {

            //IF the user's input is determined to be a float
            Float.valueOf(userInput);

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a given string can be parsed as an integer.
     *
     * @param userInput The string to check.
     *
     * @return true if the string can be parsed as an integer, false otherwise.
     */
    private static boolean isInteger(String userInput) {

        //Attempt to get the value of the user's input
        try {

            //If the exception is not thrown, return true.
            Integer.valueOf(userInput);

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a given string can be parsed as a double.
     *
     * @param userInput The string to check.
     *
     * @return true if the string can be parsed as a double, false otherwise.
     */
    private static boolean isDouble(String userInput) {

        //Attempt to get the value of the user's input
        try {

            //IF the user's input is determined to be an double
            Double.valueOf(userInput);

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

}
