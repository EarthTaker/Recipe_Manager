package com.mycompany.recipemanager;

import java.util.ArrayList;

/**
 * The Recipe class represents a single recipe, including its name, servings,
 * ingredients, and total calories. It provides methods to create, update,
 * delete, and display recipes, as well as manage ingredients within them.
 *
 * @author Talka
 */
public class Recipe {

    //<editor-fold defaultstate="collapsed" desc="Class members">
    //Private member variables
    private String recipeName;
    private int servings;
    private ArrayList<Ingredient> recipeIngredients;
    private ArrayList<String> recipeInstructions;
    private double totalRecipeCalories;
    private boolean addingRecipeInstructions;
    // </editor-fold>

    /**
     * Constructs a new Recipe with default values.
     */
    public Recipe() {
        recipeName = "";
        servings = 0;
        recipeIngredients = new ArrayList<Ingredient>();
        recipeInstructions = new ArrayList<String>();
        totalRecipeCalories = 0;
    }

    /**
     * Prompts the user to create a new recipe by providing necessary details.
     * Assigns the provided details to the recipe.
     *
     */
    public void createRecipe() {

        // <editor-fold defaultstate="collapsed" desc=" Assign Recipe's Name">
        while (this.getRecipeName().isEmpty()) {
            System.out.println("-----------------------------------------------------");
            System.out.println("");
            System.out.println("To create a Recipe, you must first provide its name: ");
            System.out.println("");
            System.out.println("-----------------------------------------------------");

            //Grab user input and make sure it is a string value.
            UserInputValidation.validateUserFeedback();

            //Ensure the user cannot enter an empty string as the recipe's name.
            if (UserInputValidation.userResponse.length() > 0) {

                //Set recipe's name
                this.setRecipeName(UserInputValidation.userResponse);

            } else {

                //Inform user of the ingredient name's requirements.
                System.out.println("-----------------------------------------------------------");
                System.out.println("");
                System.out.println("Ingredient Name's length must be at least 1.");
                System.out.println("");
                System.out.println("-----------------------------------------------------------");

            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Assign Recipe's servings">
        while (this.getServings() == 0) {
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            System.out.println("What is " + this.getRecipeName() + "'s serving size?");
            System.out.println("");
            System.out.println("---------------------------------------------------------");

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserOption();

            //Ensure recipe has at least 1 serving
            if (UserInputValidation.userOption >= 1) {

                //Assign servings to the new recipe.
                this.setServings(UserInputValidation.userOption);

            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Assign Recipe's total calories">
        while (this.getTotalRecipeCalories() == 0) {
            System.out.println("------------------------------------------------------------");
            System.out.println("");
            System.out.println("How many calories will " + this.getRecipeName() + " be?");
            System.out.println("");
            System.out.println("------------------------------------------------------------");

            //Validate user input: Double
            UserInputValidation.validateUserDouble();

            //Ensure recipe has at least a total number of calories greater than 0.
            if (UserInputValidation.userDouble > 0 && UserInputValidation.userDouble <= 10000) {

                //Assign the total number of calories to the new recipe.
                this.setTotalRecipeCalories(UserInputValidation.userDouble);

            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Create Recipe Instructions">
        while (this.getRecipeInstructions().isEmpty()) {

            //Request information from user.
            System.out.println("--------------------------------------------");
            System.out.println("");
            System.out.println("Would you like to add Recipe Instructions now?");
            System.out.println("");
            System.out.println("1 - Yes, 2 - No");
            System.out.println("--------------------------------------------");

            UserInputValidation.validateUserOption();

            if (UserInputValidation.userOption == 1) {

                //Begin process of creating/adding recipe ingredients
                createRecipeInstructions();

            } else {
                break;
            }
        }
        // </editor-fold>

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Recipe: " + this.getRecipeName() + " created successfully! Returning to Recipe Menu.");
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    /**
     * Deletes the current recipe and returns a Boolean indicating the success
     * of the operation.
     *
     *
     * @return true if the recipe is successfully deleted, false otherwise
     */
    public boolean deleteRecipe() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Are you sure you want to delete Recipe: " + this.getRecipeName() + "?");
        System.out.println("");
        System.out.println("Enter: 1 for Yes, 2 for No.");
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserOption();

        //Delete Recipe
        if (UserInputValidation.userOption == 1) {

            System.out.println("---------------------------------");
            System.out.println("");
            System.out.println("Recipe deleted. Returning to Recipe Menu.");
            System.out.println("");
            System.out.println("---------------------------------");

            return true;

        }
        return false;
    }

    /**
     * Allows the user to update specific attributes of the recipe, such as
     * name, servings, and ingredients.
     *
     *
     */
    public void updateRecipe() {

        //Create boolean to control update loops
        boolean userEditing;

        //Create boolean to control when updating is complete.
        boolean updatingRecipe = true;

        //Create while loop to contain updating process.
        while (updatingRecipe) {

            //Determine what the user wants to update.
            System.out.println("=================================================================================");
            System.out.println("");
            System.out.println("For Recipe: " + this.getRecipeName() + ", which values would you like to update?");
            System.out.println("");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Option 1: Name");
            System.out.println("");
            System.out.println("Option 2: Number of Servings - " + this.getServings());
            System.out.println("");

            //Only show ingredient list if recipe has ingredients.
            if (!this.getRecipeIngredients().isEmpty()) {
                System.out.println("Option 3: Ingredient List");
                System.out.println("");
            }

            System.out.println("Option 4: Total Calories - " + this.getTotalRecipeCalories());
            System.out.println("");

            //Only show if recipe has instructions
            if (!this.getRecipeInstructions().isEmpty()) {
                System.out.println("Option 5: Recipe Instructions");
                System.out.println("");
            }

            System.out.println("------------------------------------------------------------------");
            System.out.println("Option 6: Exit");
            System.out.println("=================================================================================");

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserMenuOption();

            //Ensure user cannot access the recipe's ingredients IF it has NONE.
            if (UserInputValidation.userOption == 3 && this.getRecipeIngredients().isEmpty()) {

                //Reset user option
                UserInputValidation.userOption = 0;
            }

            //Ensure user cannot access the recipe's instructions IF it has NONE.
            if (UserInputValidation.userOption == 5 && this.getRecipeInstructions().isEmpty()) {

                //Reset user option
                UserInputValidation.userOption = 0;
            }

            //Enable while loops to gather user feedback until valid feedback given.
            userEditing = true;

            //Switch based on user's selection
            switch (UserInputValidation.userOption) {

                //Update Name
                case 1 -> {

                    while (userEditing) {

                        System.out.println("--------------------------------------------");
                        System.out.println("");
                        System.out.println("What would you like the recipe's name to be?");
                        System.out.println("");
                        System.out.println("--------------------------------------------");

                        UserInputValidation.validateUserFeedback();

                        //If the user input returning is not an empty string.
                        if (!UserInputValidation.userResponse.equalsIgnoreCase("")) {

                            //Set recipe name
                            this.setRecipeName(UserInputValidation.userResponse);

                            //Leave while loop
                            userEditing = false;
                        }
                    }
                }

                //Update Servings
                case 2 -> {

                    while (userEditing) {

                        System.out.println("--------------------------------------------");
                        System.out.println("");
                        System.out.println("How many servings should the recipe be?");
                        System.out.println("");
                        System.out.println("--------------------------------------------");

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserOption();

                        //Ensure recipe has at least 1 serving
                        if (UserInputValidation.userOption >= 1) {

                            //Update servings 
                            this.setServings(UserInputValidation.userOption);

                            //Leave while loop
                            userEditing = false;

                        }
                    }
                }

                //Recipe Level Ingredient List Options
                case 3 -> {

                    //Enable while loop for Recipe & Ingredient Menu.
                    boolean editingIngredient = true;

                    while (editingIngredient) {

                        System.out.println("--------------------------------------------");
                        System.out.println("-----------Recipe Ingredient List-----------");
                        System.out.println("--------------------------------------------");
                        System.out.println("");
                        System.out.println("Option 1: Display " + this.getRecipeName() + " Ingredients");
                        System.out.println("");
                        System.out.println("Option 2: Remove Ingredient from Recipe");
                        System.out.println("");
                        System.out.println("Option 3: Exit");
                        System.out.println("");
                        System.out.println("--------------------------------------------");

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserMenuOption();

                        switch (UserInputValidation.userOption) {

                            //Display All Ingredients
                            case 1 -> {

                                //Call to the display ingredients method.
                                for (Ingredient ing : this.getRecipeIngredients()) {

                                    //Display full ingredient info
                                    ing.displayIngredient(1);

                                }
                            }

                            //Remove Ingredient from Recipe
                            case 2 -> {

                                //Call to the private removal method.
                                this.removeIngredientfromRecipe();

                                if (this.getRecipeIngredients().isEmpty()) {

                                    //Reset boolean to exit while loop.
                                    editingIngredient = false;
                                }
                            }

                            //Exit
                            case 3 -> {

                                //Reset boolean to exit while loop.
                                editingIngredient = false;

                            }
                        }
                    }
                }

                //Update Total Recipe Calories
                case 4 -> {

                    while (userEditing) {

                        System.out.println("---------------------------------------");
                        System.out.println("");
                        System.out.println("How many calories should the recipe be?");
                        System.out.println("");
                        System.out.println("---------------------------------------");

                        //Validate user input: Double
                        UserInputValidation.validateUserDouble();

                        //Ensure recipe has at least a total number of calories greater than 0 and less than 10,000.
                        if (UserInputValidation.userDouble > 0 && UserInputValidation.userDouble <= 10000) {

                            //Set Calories
                            this.setTotalRecipeCalories(UserInputValidation.userDouble);

                            //Leave while loop
                            userEditing = false;

                        }
                    }
                }

                //Update Recipe Instructions
                case 5 -> {
                    createRecipeInstructions();

                    if (!addingRecipeInstructions) {

                        //Leave while loop.
                        userEditing = false;
                    }
                }

                //Exit
                case 6 -> {

                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Exiting to Recipe Update Menu.");
                    System.out.println("");
                    System.out.println("----------------------------------------------------------------------------");

                    //Disable while loops to return
                    updatingRecipe = false;
                    userEditing = false;

                }
            }
        }
    }

    /**
     * Displays the recipe's details based on the specified display mode.
     *
     * @param displayMode the mode of display (e.g., full recipe details,
     * ingredients list, etc.)
     */
    public void displayRecipe(int displayMode) {

        switch (displayMode) {

            //Display Recipe and Ordered list of Ingredients 
            case 1 -> {
                System.out.println("----------------------------------------------------------");
                System.out.println("Recipe Name: " + this.getRecipeName());
                System.out.println("");
                System.out.println("Number of Servings: " + this.servings);
                System.out.println("");
                System.out.println("Total Calories: " + this.getTotalRecipeCalories());
                System.out.println("");
                if (!this.getRecipeIngredients().isEmpty()) {
                    System.out.println("|------------Ingredients-----------|");

                    this.displayRecipe(3);

                    //Spacing
                    System.out.println("");
                }
            }

            //Display list of recipe ingredient (Update)
            case 2 -> {

                //Request information from the user.
                System.out.println("------------------------------------------------------------------------------------");
                System.out.println("");
                System.out.println("Select an Ingredient from the list below to update it within Recipe: " + this.getRecipeName() + ".");
                System.out.println("");
                System.out.println("------------------------------------------------------------------------------------");

                this.displayRecipe(3);

                System.out.println("------------------------------------------------------------------------------------");
            }

            //Display ordered list of ingredients within a recipe
            case 3 -> {

                for (int i = 1; i <= this.getRecipeIngredients().size(); ++i) {

                    System.out.println("");

                    //Display i using iterative variable, but minus 1 to get ingredient within recipe list.
                    System.out.print(i + ". " + this.getRecipeIngredients().get(i - 1).getNameOfIngredient());
                    System.out.println("");
                }
            }

            //Display un-ordered list of ingredients within a recipe
            case 4 -> {
                for (int i = 1; i <= this.getRecipeIngredients().size(); ++i) {

                    System.out.println("");

                    //Display i using iterative variable, but minus 1 to get ingredient within recipe list.
                    System.out.print(" - " + this.getRecipeIngredients().get(i - 1).getNameOfIngredient());
                    System.out.println("");
                }
            }

            //Display Recipe Instructions menu.
            case 5 -> {
                int stepCounter = 1;

                //Only add to step counter if there are no recipe instructions
                if (!this.getRecipeInstructions().isEmpty()) {
                    stepCounter = this.getRecipeInstructions().size() + 1;

                }

                System.out.println("==================================================");
                System.out.println("");
                System.out.println("Recipe " + this.getRecipeName() + " Instructions");
                System.out.println("");
                System.out.println("==================================================");
                System.out.println("Enter Step " + stepCounter + ":");
                System.out.println("");

                if (!this.getRecipeInstructions().isEmpty()) {

                    System.out.println("Existing Steps: ");
                    System.out.println("--------------------------------------------");

                    //Print out each instruction starting at index zero, but print next index to simulate ordered list.
                    for (int i = 0; i < this.getRecipeInstructions().size(); ++i) {
                        System.out.println((i + 1) + ". " + this.getRecipeInstructions().get(i));
                    }
                    System.out.println("--------------------------------------------");
                }

                System.out.println("");
                System.out.println("==================================================");
                System.out.println("Option 1: Exit");
                System.out.println("==================================================");
            }

            //Display Recipe Ingredients w/o Recipe Details.
            case 6 -> {
                if (!this.getRecipeIngredients().isEmpty()) {
                    System.out.println("|------------Recipe Ingredients-----------|");

                    this.displayRecipe(3);

                    //Spacing
                    System.out.println("");
                    System.out.println("|-----------------------------------------|");
                }
            }

            //Display only Recipe Name
            default -> {
                System.out.println("");
                System.out.print("- " + this.getRecipeName());
                System.out.println("");
            }
        }
    }

    /**
     * Removes a specified ingredient from the recipe.
     *
     */
    public void removeIngredientfromRecipe() {

        //Display information to user
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Input the Ingredient's name from the list below to remove it from Recipe: " + this.getRecipeName());
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------------------");

        //Display each ingredient within recipe.
        this.displayRecipe(4);

        System.out.println("---------------------------------------------------------------------------------------------------");

        //Get User Feedback
        UserInputValidation.validateUserFeedback();

        //Get Recipe Ingredient list size
        final int recipeIngredientCount = this.getRecipeIngredients().size();

        //For each ingredient in the recipe's list of ingredients
        for (Ingredient ing : this.getRecipeIngredients()) {

            //If any ingredient name matches the user's response
            if (ing.getNameOfIngredient().compareToIgnoreCase(UserInputValidation.userResponse) == 0) {

                //Remove ingredient from Recipe's List of Ingredients.
                this.getRecipeIngredients().remove(ing);
                
                //Set new list
                this.setRecipeIngredients(recipeIngredients);

                //Assure user an Ingredient was removed from the list
                if (recipeIngredientCount > this.getRecipeIngredients().size()) {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Ingredient was removed from Recipe: " + this.getRecipeName() + ".");
                    System.out.println("");
                    System.out.println("-------------------------------------------------------------------");

                } else {
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Ingredient was NOT removed from Recipe: " + this.getRecipeName() + ".");
                    System.out.println("");
                    System.out.println("-------------------------------------------------------------------");
                }

                break;
            }
        }
    }

    /**
     * Enables user to create a set of instructions for a recipe.
     *
     */
    private void createRecipeInstructions() {

        addingRecipeInstructions = true;

        while (addingRecipeInstructions) {

            //Show recipe instruction menu
            displayRecipe(5);

            UserInputValidation.validateUserFeedback();

            //If the user's response length is greater than zero AND user's response is NOT 1, then save response.
            if (!UserInputValidation.userResponse.isEmpty() && UserInputValidation.userResponse.compareToIgnoreCase("1") != 0) {

                this.getRecipeInstructions().add(UserInputValidation.userResponse);

                System.out.println("--------------------------------------------");
                System.out.println("Would you like to add another step?");
                System.out.println("");
                System.out.println("1 - Yes, 2 - No");
                System.out.println("--------------------------------------------");

                UserInputValidation.validateUserOption();

                //If user enters 2 to not add another step
                if (UserInputValidation.userOption == 2) {

                    //Exit while loop to stop adding recipe instructions
                    addingRecipeInstructions = false;
                }

            } else {

                //Exit while loop to stop adding recipe instructions
                addingRecipeInstructions = false;
            }
        }
    }

    /**
     * Adds a given ingredient to the recipe if it doesn't already exist.
     *
     * @param ingredientObj the ingredient to be added
     * @return - True - The Ingredient was added to the recipe / False - The
     * Ingredient was NOT added to the recipe
     */
    public boolean addIngredientToRecipe(Ingredient ingredientObj) {

        //Check if recipe already has an ingredient with the same name.
        if (!this.getRecipeIngredients().isEmpty()) {

            //For each ingredient in the recipe, check for duplicates.
            for (Ingredient ingredient : this.getRecipeIngredients()) {

                //If a duplicate is found, then return false
                if (ingredientObj.getNameOfIngredient().equalsIgnoreCase(ingredient.getNameOfIngredient())) {

                    System.out.println("-------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Ingredient: " + ingredientObj.getNameOfIngredient() + " already exists in Recipe: " + this.getRecipeName() + ".");
                    System.out.println("");
                    System.out.println("-------------------------------------------------------------");

                    return false;
                }
            }
        }

        //Request information from the user.
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Add Ingredient: " + ingredientObj.getNameOfIngredient() + " to " + "Recipe: " + this.getRecipeName() + "?");
        System.out.println("");
        System.out.println("Enter: 1 for Yes, 2 for No.");
        System.out.println("");
        System.out.println("---------------------------------------------------------------------");

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserMenuOption();

        if (UserInputValidation.userOption == 1) {

            //Add ingredient to recipe.
            this.getRecipeIngredients().add(ingredientObj);
            
            

            return true;
        }

        return false;
    }

    //<editor-fold defaultstate="collapsed" desc=" Accessors & Mutators ">
    // Accessor for Recipe Name.
    public String getRecipeName() {
        return recipeName;
    }

    // Mutator for Recipe Name.
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    // Accessor for Servings.
    public int getServings() {
        return servings;
    }

    // Mutator for Servings.
    public void setServings(int servings) {
        this.servings = servings;
    }

    // Accessor for Recipe Ingredients.
    public ArrayList<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    // Mutator for Recipe Ingredients.
    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    //Access for Recipe Instructions.
    public ArrayList<String> getRecipeInstructions() {
        return recipeInstructions;
    }

    //Mutator for Recipe Instructions
    public void setRecipeInstructions(ArrayList<String> recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    // Accessor for Total Recipe Calories.
    public double getTotalRecipeCalories() {
        return totalRecipeCalories;
    }

    // Mutator for Total Recipe Calories
    public void setTotalRecipeCalories(double totalRecipeCalories) {
        this.totalRecipeCalories = totalRecipeCalories;
    }
    // </editor-fold>
}
