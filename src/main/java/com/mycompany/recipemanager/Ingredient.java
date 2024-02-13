package com.mycompany.recipemanager;

import java.util.ArrayList;

/**
 * The Ingredient class represents an ingredient used in recipes. It includes
 * details such as the ingredient's name, amount, unit of measurement, number of
 * calories per unit, and total calories. The class provides methods for
 * creating, updating, deleting, and displaying ingredients.
 *
 * @author Talka
 */
public class Ingredient {

    //<editor-fold defaultstate="collapsed" desc="Class members">
    //Denote private member variables
    private String nameOfIngredient;
    private float ingredientAmount;
    private String unitMeasurement;
    private int numberCaloriesPerUnit;
    private double totalCalories;

    //Create a set of valid measurement units
    ArrayList<String> measurementUnits = new ArrayList<String>();
    // </editor-fold>

    /**
     * Constructs a new Ingredient with default values and initializes a list of
     * common measurement units.
     */
    public Ingredient() {
              
        this.nameOfIngredient = "";
        this.ingredientAmount = -1;
        this.unitMeasurement = "";
        this.numberCaloriesPerUnit = -1;
        this.totalCalories = -1;

        // Add common measurement units to the set
        measurementUnits.add("tsp -Teaspoon");
        measurementUnits.add("tbsp -Tablespoon");
        measurementUnits.add("fl oz -Fluid Ounce");
        measurementUnits.add("cup -Cup");
        measurementUnits.add("ml -Milliliter");
        measurementUnits.add("g -Gram");
        measurementUnits.add("lbs -Pounds");
    }

    /**
     * Prompts the user to create a new ingredient by providing necessary
     * details. Assigns the provided details to the ingredient.
     */
    //<editor-fold defaultstate="collapsed" desc="Create Ingredient">
    public void createIngredient() {

        // <editor-fold defaultstate="collapsed" desc=" Assign Ingredient's Name">
        while (this.nameOfIngredient.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("");
            System.out.println("To create an ingredient, you must first provide its name: ");
            System.out.println("");
            System.out.println("----------------------------------------------------------");

            //Grab user input and make sure it is a type OF string
            UserInputValidation.validateUserFeedback();

            //Ensure the user cannot enter an empty string as the ingredient's name.
            if (UserInputValidation.userResponse.length() > 0) {

                //Assign ingredient name.
                this.setNameOfIngredient(UserInputValidation.userResponse);

            } else {

                //Inform user of the ingredient name's requirements.
                System.out.println("-----------------------------------------------------------");
                System.out.println("");
                System.out.println("Invalid input. Ingredient Name's length must be at least 1.");
                System.out.println("");
                System.out.println("-----------------------------------------------------------");
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Assign Ingredient Amount ">
        //Assign new ingredient an amount.
        while (this.ingredientAmount <= 0) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("How much of the ingredient: " + this.getNameOfIngredient() + ", is required?");
            System.out.println("");
            System.out.println("-------------------------------------------------------------------------------");

            //Grab user input and make sure it is a type OF float
            UserInputValidation.validateUserFloat();

            //If the ingredient amount is greater than 0
            if (UserInputValidation.userFloat > 0) {

                //Assign ingredient amount.
                this.setIngredientAmount(UserInputValidation.userFloat);

            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Assign Unit of Measurement ">
        //Assign new ingredient a measurement
        while (this.unitMeasurement.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("What are you measuring the Ingredient Amount: " + this.getIngredientAmount() + ", as?");
            System.out.println("");
            System.out.println("Select from the list of measurement units below: ");
            System.out.println("-------------------------------------------------");

            //Display all units of measurement as ordered list
            displayMeasurements(1);

            System.out.println("");
            System.out.println("-------------------------------------------------------------------------------");

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserOption();

            //If the user's option is inclusively between 1 and the total number of Unit measurements available
            if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= measurementUnits.size()) {

                //Iterate through all available units of measurement to assign a unit of measurement where the index is equal to the user's input
                for (String measurementUnit : measurementUnits) {

                    if (UserInputValidation.userOption == (measurementUnits.indexOf(measurementUnit) + 1)) {

                        //Assign units of measurement to ingredient amount.
                        this.setUnitMeasurement(measurementUnit);
                    }
                }

            } else {

                System.out.println("---------------------------------------------------------");
                System.out.println("");
                System.out.println("Please select from the list of example measurement units.");
                System.out.println("");
                System.out.println("---------------------------------------------------------");

            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Assign Number of Calories per Unit">
        //Assign new ingredient a number of calories per unit
        while (this.numberCaloriesPerUnit <= 0) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("");
            System.out.println("How many calories would it be for each?");
            System.out.println("");
            System.out.println("----------------------------------------------------------------------");

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserOption();

            //If the number of calories per unit is greater than zero
            if (UserInputValidation.userOption > 0) {

                //Assign the number of calories per unit of ingredient.
                this.setNumberCaloriesPerUnit(UserInputValidation.userOption);

            }
        }
        // </editor-fold>

        //Calculate the total number of calories for this Ingredient
        this.totalCalories = calculateTotalCalories(this.getIngredientAmount(), this.getNumberCaloriesPerUnit());

        if (this.totalCalories > 0) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("");
            System.out.println("Ingredient: " + this.getNameOfIngredient() + " was created! Returning to Ingredient Menu.");
            System.out.println("");
            System.out.println("-------------------------------------------------------------");

        }
    }
    // </editor-fold>

    /**
     * Deletes the current ingredient and returns a Boolean indicating the
     * success of the operation.
     *
     * @return true if the ingredient is successfully deleted, false otherwise
     */
    //<editor-fold defaultstate="collapsed" desc="Delete Ingredient">
    public boolean deleteIngredient() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Are you sure you want to delete Ingredient: " + this.getNameOfIngredient() + "?");
        System.out.println("");
        System.out.println("Enter: 1 for Yes, 2 for No.");
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserOption();

        //Delete Ingredient
        if (UserInputValidation.userOption == 1) {

            System.out.println("---------------------------------");
            System.out.println("");
            System.out.println("Ingredient deleted. Returning to Ingredient Menu.");
            System.out.println("");
            System.out.println("---------------------------------");

            return true;

        } else {

            System.out.println("---------------------------------");
            System.out.println("");
            System.out.println("Ingredient not deleted. Returning to Ingredient Menu.");
            System.out.println("");
            System.out.println("---------------------------------");

            return false;
        }
    }
    // </editor-fold>

    /**
     * Allows the user to update specific attributes of the ingredient, such as
     * name, amount, and unit of measurement.
     */
    //<editor-fold defaultstate="collapsed" desc="Update Ingredient">
    public void updateIngredient() {

        //Create boolean to control when updating is complete.
        boolean updatingIngredient = true;

        //Create while loop to contain updating process.
        while (updatingIngredient) {

            // <editor-fold defaultstate="collapsed" desc="Update Ingredient Menu Display">
            //Determine what the user wants to update.
            System.out.println("=================================================================================");
            System.out.println("");
            System.out.println("For Ingredient: " + this.getNameOfIngredient() + ", which values would you like to update?");
            System.out.println("");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Option 1: Name");
            System.out.println("");
            System.out.print("Option 2: Ingredient Amount - " + this.getIngredientAmount() + " ");

            //Display Ingredient Unit Of Measurement Abbreviation for Option 2
            this.displayMeasurements(3);

            System.out.println("");
            System.out.println("");
            System.out.print("Option 3: Unit of Measurement - ");

            //Display Ingredient Unit Of Measurement Abbreviation for Option 3
            this.displayMeasurements(4);

            System.out.println("");
            System.out.println("");
            System.out.print("Option 4: Number of Calories per ");

            //Display Ingredient Unit Of Measurement Name for Option 4
            this.displayMeasurements(2);

            System.out.print(": " + this.getNumberCaloriesPerUnit());

            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("-- Ingredient's Total Calories - " + this.getTotalCalories() + " --");
            System.out.println("");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Option 5: Exit");
            System.out.println("=================================================================================");
            // </editor-fold>

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserMenuOption();

            //Create boolean to control update loops
            boolean userEditing = true;

            //Switch based on user's selection
            switch (UserInputValidation.userOption) {

                //Update Name
                case 1 -> {

                    while (userEditing) {

                        System.out.println("--------------------------------------------");
                        System.out.println("");
                        System.out.println("What would you like the Ingredient's name to be?");
                        System.out.println("");
                        System.out.println("--------------------------------------------");

                        UserInputValidation.validateUserFeedback();

                        //If the user input returning is not an empty string.
                        if (!UserInputValidation.userResponse.equalsIgnoreCase("")) {

                            //Set Ingredient name
                            this.setNameOfIngredient(UserInputValidation.userResponse);

                            //Leave while loop
                            userEditing = false;
                        }
                    }
                }

                //Update Ingredient Amount
                case 2 -> {

                    while (userEditing) {

                        System.out.println("--------------------------------------------");
                        System.out.println("");
                        System.out.println("How much of the Ingredient should be used?");
                        System.out.println("");
                        System.out.println("--------------------------------------------");

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserOption();

                        //Ensure user's input is at least 1
                        if (UserInputValidation.userOption >= 1) {

                            //Set new Ingredient Amount 
                            this.setIngredientAmount(UserInputValidation.userOption);

                            //Re-Calculate Total number of calories for Ingredient
                            calculateTotalCalories(this.getIngredientAmount(), this.getNumberCaloriesPerUnit());

                            //Leave while loop
                            userEditing = false;

                        }
                    }
                }

                //Update Unit of Measurement
                case 3 -> {

                    while (userEditing) {
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("");
                        System.out.println("What will the Ingredient's amount: " + this.getIngredientAmount() + ", be measured as?");
                        System.out.println("");
                        System.out.println("Select from the list of measurement units below: ");
                        System.out.println("-------------------------------------------------");

                        //Display all units of measurement as ordered list
                        displayMeasurements(1);

                        System.out.println("");
                        System.out.println("");
                        System.out.print("Current Unit of Measurement: ");

                        //Display Ingredient Unit Of Measurement Name
                        this.displayMeasurements(2);

                        System.out.println("");
                        System.out.println("");
                        System.out.println("-------------------------------------------------------------------------------");

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserOption();

                        //If the user's option is inclusively between 1 and the total number of Unit measurements available
                        if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= measurementUnits.size()) {

                            //Iterate through all available units of measurement to assign a unit of measurement where the index is equal to the user's input
                            for (String measurementUnit : measurementUnits) {

                                if (UserInputValidation.userOption == (measurementUnits.indexOf(measurementUnit) + 1)) {

                                    //Assign units of measurement to ingredient amount.
                                    this.setUnitMeasurement(measurementUnit);

                                    //Exit loop back to update.
                                    userEditing = false;
                                }
                            }
                        }
                    }
                }

                //Update Number of Calories per Unit of Measurement
                case 4 -> {

                    while (userEditing) {

                        System.out.println("---------------------------------------");
                        System.out.println("");
                        System.out.print("How many calories per ");

                        //Display full name without plural check
                        this.displayMeasurements(5);

                        System.out.print(" should the Ingredient be?");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("---------------------------------------");

                        //Validate user input
                        UserInputValidation.validateUserOption();

                        //Ensure per unit of ingredient is at least greater than zero.
                        if (UserInputValidation.userOption > 0) {

                            //Set Calories per unit of Ingredient
                            this.setNumberCaloriesPerUnit(UserInputValidation.userOption);

                            //Re-Calculate Total number of calories for Ingredient
                            calculateTotalCalories(this.getIngredientAmount(), this.getNumberCaloriesPerUnit());

                            //Leave while loop
                            userEditing = false;

                        }
                    }
                }

                //Exit
                case 5 -> {

                    //Disable while loops to return
                    updatingIngredient = false;
                    userEditing = false;

                }
            }
        }
    }
    // </editor-fold>

    /**
     * Displays the ingredient's details based on the specified display mode.
     *
     * @param displayMode the mode of display (e.g., full ingredient details,
     * name only, etc.)
     */
    // <editor-fold defaultstate="collapsed" desc="Display Ingredient">
    public void displayIngredient(int displayMode) {

        switch (displayMode) {

            //Display only Ingredient Name
            case 0 -> {
                System.out.println("");
                System.out.println("- " + this.getNameOfIngredient());
                System.out.println("");
            }

            //Display entire ingredient
            case 1 -> {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("--------------------------Ingredient-----------------------------");
                System.out.println("");
                System.out.println("Name: " + this.getNameOfIngredient());
                System.out.println("");
                System.out.print("Amount: " + this.getIngredientAmount() + " ");

                //Display Ingredient Unit Of Measurement Abbreviation
                this.displayMeasurements(3);

                System.out.println("");
                System.out.println("");
                System.out.print("Unit of Measurement: ");

                //Display Ingredient Unit Of Measurement Abbreviation
                this.displayMeasurements(4);

                System.out.println("");
                System.out.println("");
                System.out.print("Number of Calories per ");

                //Display Ingredient Unit Of Measurement Name
                this.displayMeasurements(5);

                System.out.print(": " + this.getNumberCaloriesPerUnit());

                System.out.println("");
                System.out.println("");
                System.out.println("Ingredient's Total Caloric Value: " + this.getTotalCalories());
                System.out.println("");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Helper Methods">
    /**
     * Calculates the total number of calories for the ingredient based on its
     * amount and calories per unit.
     *
     * @param amountOfIngredient The amount of the ingredient.
     * @param caloriesPerUnit The number of calories per unit of the ingredient.
     * @return The total calories for the ingredient.
     */
    private float calculateTotalCalories(float amountOfIngredient, int caloriesPerUnit) {

        //Calculate the total number of calories
        float calculatedTotalCalories = amountOfIngredient * caloriesPerUnit;
        
        //Reset value.
        this.setTotalCalories(calculatedTotalCalories);

        return calculatedTotalCalories;
    }

    /**
     * Displays various formats of measurement units, based on the provided
     * display type.
     *
     * @param displayType The type of display format for measurement units
     * (e.g., full name, abbreviation).
     */
    private void displayMeasurements(int displayType) {

        switch (displayType) {

            //If all units of measurement need to be displayed in an ordered list.
            case 1 -> {

                //For each measurement, separate full name from abbreviation.
                for (String measurement : measurementUnits) {

                    //Formatting: "[abbreviation] -[Full name]" - Create a string array to hold both the abbreviation and full name separately.
                    String[] parts = measurement.split(" -");

                    //Print index of the measurement then the Unit of Measurement's Name and Abbreviation
                    System.out.println((measurementUnits.indexOf(measurement) + 1) + ". " + parts[1] + " (" + parts[0] + ".)");
                }
            }

            //Display "current" ingredient's unit of measurement (Full Name ONLY with plural toggle)
            case 2 -> {

                //Formatting: "[abbreviation] -[Full name]" - Create a string array to hold both the abbreviation and full name separately.
                String[] parts = this.unitMeasurement.split(" -");

                if (this.getNumberCaloriesPerUnit() > 1) {

                    //Print the Unit of Measurement's Name (Plural)
                    System.out.print(parts[1] + "s");

                } else {
                    //Print the Unit of Measurement's Name
                    System.out.print(parts[1]);
                }
            }

            //Display "current" ingredient's unit of measurement (Abbreviation ONLY)
            case 3 -> {

                //Formatting: "[abbreviation] -[Full name]" - Create a string array to hold both the abbreviation and full name separately.
                String[] parts = this.unitMeasurement.split(" -");

                //Print the Unit of Measurement's Abbreviation
                System.out.print("(" + parts[0] + ".)");

            }

            //Display "current" ingredient's unit of measurement (Abbreviation & Full Name)
            case 4 -> {

                //Formatting: "[abbreviation] -[Full name]" - Create a string array to hold both the abbreviation and full name separately.
                String[] parts = this.unitMeasurement.split(" -");

                //Print the Unit of Measurement's Abbreviation
                System.out.print("(" + parts[0] + ".) " + parts[1]);

            }

            //Display "current" ingredient's unit of measurement (Full Name ONLY WITHOUT plural toggle)
            case 5 -> {

                //Formatting: "[abbreviation] -[Full name]" - Create a string array to hold both the abbreviation and full name separately.
                String[] parts = this.unitMeasurement.split(" -");

                //Print the Unit of Measurement's Name
                System.out.print(parts[1]);

            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Accessors & Mutators ">
    // Accessor method for Ingredient's name
    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    // Mutator method for Ingredient's name
    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }

    //Accessor method for the amount of an ingredient.
    public float getIngredientAmount() {
        return ingredientAmount;
    }

    //Mutator method for the amount of an ingredient.
    public void setIngredientAmount(float ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    //Accessor method for the unitMeasurement
    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    //Mutator method for the unitMeasurement
    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    //Accessor method for the numberCaloriesPerUnit
    public int getNumberCaloriesPerUnit() {
        return numberCaloriesPerUnit;
    }

    //Mutator method for the numberCaloresPerUnit
    public void setNumberCaloriesPerUnit(int numberCaloriesPerUnit) {
        this.numberCaloriesPerUnit = numberCaloriesPerUnit;
    }

    //Accessor method for the totalCalories
    public double getTotalCalories() {
        return totalCalories;
    }

    //Mutator method for the totalCalories
    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    // </editor-fold>
}
