package com.mycompany.recipemanager;

import java.util.ArrayList;

/**
 * The RecipeBox class represents a container for storing multiple recipes. It
 * allows operations such as creating, renaming, deleting recipe boxes, and
 * managing recipes within them.
 *
 * @author Talka
 */
public class RecipeBox {

    //<editor-fold defaultstate="collapsed" desc="Class members">
    //Private class members
    private String recipeBoxName;

    private ArrayList<Recipe> listOfRecipes;
    // </editor-fold>

    /**
     * Constructs a new RecipeBox with no name and an empty list of recipes.
     */
    public RecipeBox() {

        //Set Recipe Box name
        recipeBoxName = "";

        //Create a new ArrayList of Recipe Objects
        listOfRecipes = new ArrayList<Recipe>();
    }

    /**
     * Prompts the user to create a recipe box by providing a name. Sets the
     * name of the recipe box based on user input.
     * 
     */
    public void createRecipeBox() {

        System.out.println("");
        System.out.println("---------------------------------------------");
        System.out.println("");
        System.out.println("What would you like to name your Recipe Box?");
        System.out.println("");
        System.out.println("---------------------------------------------");
        System.out.println("");

        //Grab user input and make sure it is a string value.
        UserInputValidation.validateUserFeedback();

        //Set the recipe box's name 
        this.setRecipeBoxName(UserInputValidation.userResponse);

        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Recipe Box: " + this.getRecipeBoxName() + " was created successfully! Returning to Recipe Box Menu.");
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    /**
     * Allows the user to rename an existing recipe box. The new name is set
     * based on user input.
     * 
     */
    public void renameRecipeBox() {
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("What would you like to re-name Recipe Box: " + this.getRecipeBoxName() + " to?");
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");

        //Grab user input and make sure it is a string value.
        UserInputValidation.validateUserFeedback();

        //Ensure user cannot enter an empty value.
        if (UserInputValidation.userResponse.length() > 0) {

            //Set recipe box's name.
            this.setRecipeBoxName(UserInputValidation.userResponse);

            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Recipe Box's name successfully updated to " + this.getRecipeBoxName() + ". Returning to Recipe Box Menu.");
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------");

        } else {

            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Recipe Box's name was not changed. Returning to Recipe Box Menu.");
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------");

        }
    }

    /**
     * Deletes the current recipe box and returns a Boolean indicating the
     * success of the operation.
     * 
     *
     * @return true if the recipe box is successfully deleted, false otherwise
     */
    public boolean deleteRecipeBox() {
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Are you sure you want to delete Recipe Box: " + this.getRecipeBoxName() + "?");
        System.out.println("");
        System.out.println("Continue? 1 - Yes, 2 - No.");
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");

        //Only show error message if recipe box has recipes.
        if (!this.getListOfRecipes().isEmpty()) {

            System.out.println("");
            System.out.println("Please note that if you delete Recipe Box: " + this.getRecipeBoxName() + ", all recipes therein will be lost.");
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------");

        }

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserMenuOption();

        if (UserInputValidation.userOption == 1) {

            System.out.println("---------------------------------");
            System.out.println("");
            System.out.println("Recipe Box deleted. Returning to Recipe Box Menu.");
            System.out.println("");
            System.out.println("---------------------------------");

            return true;

        } else {
            System.out.println("---------------------------------");
            System.out.println("");
            System.out.println("Recipe Box was not deleted. Returning to Recipe Box Menu.");
            System.out.println("");
            System.out.println("---------------------------------");

            return false;
        }
    }

    /**
     * Adds a given recipe to the recipe box if it doesn't already exist.
     *
     * @param recipeObj the recipe to be added to the recipe box
     * @return true if the recipe is successfully added, false otherwise
     */
    public boolean addRecipeToRecipeBox(Recipe recipeObj) {

        //Check if recipe box already has a recipe with the same name.
        if (!this.getListOfRecipes().isEmpty()) {

            //For each recipe in the recipe box, check for duplicates.
            for (Recipe recipe : this.getListOfRecipes()) {

                //If a duplicate is found, then return false
                if (recipe.getRecipeName().equalsIgnoreCase(recipeObj.getRecipeName())) {

                    System.out.println("-------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Recipe: " + recipeObj.getRecipeName() + " already exists in Recipe Box: " + this.getRecipeBoxName() + ". Returning to Recipe Menu.");
                    System.out.println("");
                    System.out.println("-------------------------------------------------------------");

                    return false;
                }
            }
        }

        //Request information from the user.
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Add Recipe: " + recipeObj.getRecipeName() + " to " + "Recipe Box: " + this.getRecipeBoxName() + "?");
        System.out.println("");
        System.out.println("Enter: 1 for Yes, 2 for No.");
        System.out.println("");
        System.out.println("---------------------------------------------------------------------");

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserMenuOption();

        if (UserInputValidation.userOption == 1) {

            //Add recipe to recipe box.
            this.getListOfRecipes().add(recipeObj);

            //Inform user of state.
            System.out.println("-------------------------------------------------------------");
            System.out.println("");
            System.out.println("Recipe added to Recipe Box. Returning to Recipe Menu.");
            System.out.println("");
            System.out.println("-------------------------------------------------------------");

            return true;

        } else {

            //Inform user of state.
            System.out.println("-------------------------------------------------------------");
            System.out.println("");
            System.out.println("Recipe not added to Recipe Box. Returning to Recipe Menu.");
            System.out.println("");
            System.out.println("-------------------------------------------------------------");

            return false;
        }
    }

    /**
     * Removes a specified recipe from the recipe box and returns a Boolean
     * indicating the success of the operation.
     *
     * 
     * @return true if the recipe is successfully removed, false otherwise
     */
    public boolean removeRecipeFromRecipeBox() {
        System.out.println("====================================================================================");
        System.out.println("");
        System.out.println("From the list below, please select the Recipe you wish to remove from Recipe Box: " + this.getRecipeBoxName());
        System.out.println("");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Warning! Removing a recipe from the recipe box will delete it entirely.");
        System.out.println("------------------------------------------------------------------");

        //For each recipe in the Recipe Box's list of recipes
        for (Recipe recipe : this.getListOfRecipes()) {

            //Display the recipe.
            recipe.displayRecipe(0);
            System.out.println("");
        }
        System.out.println("");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Option 1: Exit");
        System.out.println("====================================================================================");

        //Grab user input and make sure it is a string value.
        UserInputValidation.validateUserFeedback();

        //Get current Recipe Box list length
        int recipeBoxListLength = this.getListOfRecipes().size();

        //Go through each recipe in the list and determine if the user's input matches any recipe. 
        for (Recipe recipe : this.getListOfRecipes()) {

            //If the recipe's name equals the user's response (which must return a zero to confirm they are equal.)
            if (recipe.getRecipeName().compareTo(UserInputValidation.userResponse) == 0) {

                //Remove recipe from recipe box list.
                this.getListOfRecipes().remove(recipe);

                //Break out of the for loop
                break;

            }
        }

        //If the list count is now less than it was prior to removal.
        if (recipeBoxListLength > this.getListOfRecipes().size()) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Recipe removed from Recipe Box: " + this.getRecipeBoxName() + " successfully. Returning to Recipe Menu.");
            System.out.println("");
            System.out.println("------------------------------------------------------------------");

            return true;

        } else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Recipe was NOT removed from Recipe Box: " + this.getRecipeBoxName() + ". Returning to Saved Recipe Update Menu.");
            System.out.println("");
            System.out.println("------------------------------------------------------------------");

            return false;

        }
    }

    /**
     * Displays the recipes in the recipe box based on the specified display
     * mode.
     *
     * @param displayToggle the mode of display (e.g., list of recipes, single
     * recipe, etc.)
     */
    public void displayRecipeBoxRecipes(int displayToggle) {

        switch (displayToggle) {

            //Display ordered list of recipes
            case 1 -> {

                for (int i = 1; i <= this.getListOfRecipes().size(); ++i) {

                    System.out.println("");
                    //Display i using iterative variable, but minus 1 to get index of recipe within recipe box list.
                    System.out.print(i + ". " + this.getListOfRecipes().get(i - 1).getRecipeName());
                    System.out.println("");
                }
            }

            //Display (1) recipe
            case 2 -> {

                //Create int to store the total number of recipes within the recipe box
                final int recipeCount = this.getListOfRecipes().size();

                //Request information from the user.
                System.out.println("==================================================================");
                System.out.println("");
                System.out.println("From the list below, please select the recipe you wish to display:");
                System.out.println("");
                System.out.println("------------------------------------------------------------------");

                //Display ordered list of recipes
                this.displayRecipeBoxRecipes(1);

                //Visually contain all recipes in list.
                System.out.println("==================================================================");

                //Grab user input and make sure it is a numerical value.
                UserInputValidation.validateUserOption();

                //Validate the user's input.
                if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= recipeCount) {

                    //For every recipe in the recipe box
                    for (Recipe recipe : this.getListOfRecipes()) {

                        //If a recipe's index within the list of recipes matches the user's input.
                        if (UserInputValidation.userOption == this.getListOfRecipes().indexOf(recipe) + 1) {

                            //Display single recipe with all ingredients
                            recipe.displayRecipe(1);
                        }
                    }
                }

            }

            //Display Ordered list of Recipe Box Recipes
            case 3 -> {
                System.out.println("==========================================");
                System.out.println("");
                System.out.println("Recipe Box: " + this.getRecipeBoxName());
                System.out.println("");
                System.out.println("------------------------------------------");

                this.displayRecipeBoxRecipes(1);

                System.out.println("");
                System.out.println("==========================================");
            }

            //Display Recipe Box recipes for Ingredient Selection
            case 4 -> {
                //Request information from the user.
                System.out.println("====================================================================================");
                System.out.println("");
                System.out.println("Based on the list of Recipe Box: " + this.getRecipeBoxName() + " Recipes below, select one to affect its Ingredients:");
                System.out.println("");
                System.out.println("------------------------------------------------------------------------------------");

                //Display ordered list of the recipe box's recipes.
                this.displayRecipeBoxRecipes(1);

                //Visually contain all recipes in list.
                System.out.println("====================================================================================");
            }
        }
    }

    /**
     * Updates a selected recipe within the recipe box. The selection and
     * updating process is based on user input.
     */
    public void updateRecipeBoxRecipe() {

        //Create int to store the total number of recipes within the recipe box
        final int recipeCount = this.getListOfRecipes().size();

        //Request information from the user.
        System.out.println("====================================================================================");
        System.out.println("");
        System.out.println("From the list below, input the Recipe's number to select it for updates:");
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------");

        //Display ordered list of recipes
        this.displayRecipeBoxRecipes(1);

        //Visually contain all recipes in list.
        System.out.println("====================================================================================");

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserOption();

        //Validate the user's input.
        if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= recipeCount) {

            //For every recipe in the recipe box
            for (Recipe recipe : this.getListOfRecipes()) {

                //If a recipe's index within the list of recipes matches the user's input.
                if (UserInputValidation.userOption == this.getListOfRecipes().indexOf(recipe) + 1) {

                    //Begin update process
                    recipe.updateRecipe();

                } else {
                    System.out.println("");
                    System.out.println("Input does not match any known recipes. Please select a recipe from the provided list.");
                    System.out.println("");

                }
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc=" Accessors & Mutators ">
    /**
     * Sets the name of the recipe box.
     *
     * @param recipeName the new name for the recipe box
     */
    public void setRecipeBoxName(String recipeName) {

        this.recipeBoxName = recipeName;
    }

    /**
     * Gets the name of the recipe box.
     *
     * @return the name of the recipe box
     */
    public String getRecipeBoxName() {

        return this.recipeBoxName;
    }

    /**
     * Gets the list of recipes in the recipe box.
     *
     * @return the list of recipes
     */
    public ArrayList<Recipe> getListOfRecipes() {

        return this.listOfRecipes;

    }

    /**
     * Sets the list of recipes for the recipe box.
     *
     * @param listOfRecipes the list of recipes to be set
     */
    public void setListOfRecipes(ArrayList listOfRecipes) {

        this.listOfRecipes = listOfRecipes;

    }

    // </editor-fold>
}
