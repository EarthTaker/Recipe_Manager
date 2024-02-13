package com.mycompany.recipemanager;

import static com.mycompany.recipemanager.StateValidation.*;
import java.util.Objects;

/**
 *
 * @author Talka
 */
public class RecipeManager {

    //<editor-fold defaultstate="collapsed" desc="Class members">
    //Class Members
    static boolean isActive = true;
    static boolean isSubMenu = false;
    static boolean isSecondSubMenu = false;
    static boolean stateChange = false;
    static boolean displayingRecipe;

    //Reference Variables
    private static RecipeBox recipeBoxObj;
    private static Recipe recipeObj;
    private static Ingredient ingredientObj;
    // </editor-fold>

    //Application entry point.
    public static void main(String[] args) {

        //Create test data AND assign it to the class reference variables
        new AppTesting(true);
        
        //Create User Validation
        UserInputValidation.initalize();

        //Set isActive to true to "start" the application
        while (isActive) {

            //Display Main Menu and Options
            System.out.println("--------------------------------------");
            System.out.println("  Welcome to the Recipe Box Manager!  ");
            System.out.println("--------------------------------------");
            System.out.println("");
            System.out.println("");
            System.out.println("Please select from the options below:");
            System.out.println("--------------------------------------");
            System.out.println("Option 1: Recipe Boxes");
            System.out.println("Option 2: Recipes");
            System.out.println("Option 3: Ingredients");
            System.out.println("Option 4: Exit");
            System.out.println("--------------------------------------");
            System.out.println("");

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserMenuOption();

            // Create pathing to various parts of application
            switch (UserInputValidation.userOption) {

                // <editor-fold defaultstate="collapsed" desc="Branch for Recipe Box interaction and creation.">
                case 1 -> {

                    //<editor-fold defaultstate="collapsed" desc="Recipe Box Menu & Validation">
                    // Setup while loop for sub-menu.
                    isSubMenu = true;

                    while (isSubMenu) {

                        //Display menu
                        System.out.println("");
                        System.out.println("--------------------------------------");
                        System.out.println("          Recipe Box Menu             ");
                        System.out.println("--------------------------------------");
                        System.out.println("");

                        if (Objects.isNull(recipeBoxObj)) {
                            System.out.println("Option 1: Create Recipe Box.");
                            System.out.println("");
                        }

                        if (!Objects.isNull(recipeBoxObj)) {
                            System.out.println("Option 2: Update a Recipe Box's name.");
                            System.out.println("");

                            System.out.println("Option 3: Delete Recipe Box: " + recipeBoxObj.getRecipeBoxName() + ".");
                            System.out.println("");
                        }

                        System.out.println("Option 4: Exit");
                        System.out.println("--------------------------------------");
                        System.out.println("");

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserMenuOption();

                        //Option 1: Create Recipe Box - Available IF user does NOT currently have a recipe box created. 
                        if (UserInputValidation.userOption == 1 && !Objects.isNull(recipeBoxObj)) {

                            //Set user feedback to exit back to main menu.
                            UserInputValidation.userOption = 0;

                        }

                        //Option 2: Update Recipe Box Name - Available IF user currently has a recipe box created.
                        if ((UserInputValidation.userOption == 2 || UserInputValidation.userOption == 3) && Objects.isNull(recipeBoxObj)) {

                            //Set user feedback to exit back to main menu.
                            UserInputValidation.userOption = 0;

                        }

                        //Option 3: Delete Recipe Box - Available IF user currently has a recipe box created.
                        if (UserInputValidation.userOption == 3 && Objects.isNull(recipeBoxObj)) {

                            //Set user feedback to exit back to main menu.
                            UserInputValidation.userOption = 0;

                        }
                        // </editor-fold>

                        switch (UserInputValidation.userOption) {

                            //<editor-fold defaultstate="collapsed" desc="Create Recipe Box">
                            case 1 -> {

                                //Create a new Recipe Box Object
                                recipeBoxObj = new RecipeBox();
                                recipeBoxObj.createRecipeBox();
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Re-name Recipe Box">
                            case 2 -> {

                                recipeBoxObj.renameRecipeBox();
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Delete Recipe Box">
                            case 3 -> {

                                //Delete recipe
                                stateChange = recipeBoxObj.deleteRecipeBox();

                                if (stateChange) {

                                    //Clean up with Garbage collector.
                                    recipeBoxObj = null;
                                }
                            }
                            // </editor-fold>

                            //Exit
                            case 4 -> {

                                //Exiting to Recipe Manager Main-Menu.
                                MenuDisplays.appDisplays(1);

                                //Get out of sub menu and back into main menu.
                                isSubMenu = false;
                            }
                        }
                    }
                }
                // </editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Branch for Recipe Interaction and Creation.">
                case 2 -> {

                    //<editor-fold defaultstate="collapsed" desc="Recipe Menu Display & Validation">
                    //Setup while loop for sub-menu.
                    isSubMenu = true;

                    while (isSubMenu) {
                        System.out.println("");
                        System.out.println("--------------------------------------");
                        System.out.println("           Recipe Menu                ");
                        System.out.println("--------------------------------------");
                        System.out.println("Option 1: Create Recipe.");
                        System.out.println("");

                        //Option 2: Add Recipe to Recipe Box - Available IF both a recipe box and a recipe exist. 
                        if (!Objects.isNull(recipeBoxObj) && !Objects.isNull(recipeObj)) {
                            System.out.println("Option 2: Add Recipe to Recipe Box.");
                            System.out.println("");
                        }

                        //Option 3: Recipe Update Menu - Available IF a recipe box exists OR a recipe exists.
                        if ((recipeBoxHasRecipes(recipeBoxObj)) || !Objects.isNull(recipeObj)) {
                            System.out.println("Option 3: Recipe Update Menu");
                            System.out.println("");

                        }

                        //Option 4: Recipe Displays - Available IF a recipe box exists and has recipes.
                        if (recipeBoxHasRecipes(recipeBoxObj)) {
                            System.out.println("Option 4: Recipe Displays");
                            System.out.println("");
                        }

                        System.out.println("Option 5: Exit");
                        System.out.println("--------------------------------------");
                        System.out.println("");

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserMenuOption();

                        //Option 2: Add Recipe to Recipe Box - Prevent user access IF either a recipe Box doesn't exist OR there is NOT currently constructed recipe.
                        if (UserInputValidation.userOption == 2 && (Objects.isNull(recipeBoxObj) || Objects.isNull(recipeObj))) {

                            //Reset user option
                            UserInputValidation.userOption = 0;
                        }

                        //Option 3: Recipe Update Menu - Prevent user access IF the recipe box does NOT have recipes AND a recipe does NOT currently exist
                        if (UserInputValidation.userOption == 3 && (!recipeBoxHasRecipes(recipeBoxObj) && Objects.isNull(recipeObj))) {

                            //Reset user option
                            UserInputValidation.userOption = 0;
                        }

                        //Option 4: Recipe Display - Prevent user acess IF recipe box has zero recipes OR recipe box doesn't exist.
                        if (UserInputValidation.userOption == 4 && (!recipeBoxHasRecipes(recipeBoxObj) || Objects.isNull(recipeBoxObj))) {

                            //Reset user option
                            UserInputValidation.userOption = 0;
                        }
                        // </editor-fold>

                        switch (UserInputValidation.userOption) {

                            //<editor-fold defaultstate="collapsed" desc="Create Recipe">
                            case 1 -> {

                                //Alert user of deletion if a recipe currently exists. 
                                if (!Objects.isNull(recipeObj)) {
                                    System.out.println("--------------------------------------------------------------------------------------------------------");
                                    System.out.println("");
                                    System.out.println("Warning! If you attempt to create another Recipe before adding it to a Recipe Box,");
                                    System.out.println("the current Recipe: " + recipeObj.getRecipeName() + ", will be deleted.");
                                    System.out.println("");
                                    System.out.println("Continue? 1 - Yes / 2 - No");
                                    System.out.println("");
                                    System.out.println("--------------------------------------------------------------------------------------------------------");

                                    //Grab user input and make sure it is a numerical value.
                                    UserInputValidation.validateUserMenuOption();

                                    if (UserInputValidation.userOption == 1) {

                                        //Create a new Recipe
                                        recipeObj = new Recipe();
                                        recipeObj.createRecipe();

                                    } else {

                                        //Exiting to Recipe Menu.
                                        MenuDisplays.appDisplays(2);
                                    }

                                } else {
                                    //Create a new Recipe
                                    recipeObj = new Recipe();
                                    recipeObj.createRecipe();
                                }

                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Add Recipe to Recipe Box">
                            case 2 -> {

                                //Add recipe to recipe box
                                stateChange = recipeBoxObj.addRecipeToRecipeBox(recipeObj);

                                if (stateChange) {
                                    //Clear recipe object for new recipe.
                                    recipeObj = null;

                                    //Exit while loop back to main menu.
                                    isSecondSubMenu = false;
                                }
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Recipe Update Menu">
                            case 3 -> {

                                //<editor-fold defaultstate="collapsed" desc="Recipe Update Menu (Menu & Validation)">
                                //Setup while loop for secondary sub menu. 
                                isSecondSubMenu = true;

                                while (isSecondSubMenu) {
                                    System.out.println("");
                                    System.out.println("----------------------------------------------------------");
                                    System.out.println("-----------------Recipe Update Menu-----------------------");
                                    System.out.println("----------------------------------------------------------");
                                    System.out.println("");

                                    //Option 1: Remove Recipe from Recipe Box & Option 2: Update a Saved Recipe - Available IF the recipe box exists and has recipes.
                                    if (recipeBoxHasRecipes(recipeBoxObj)) {
                                        System.out.println("Option 1: Remove Recipe from Recipe Box.");
                                        System.out.println("");
                                        System.out.println("Option 2: Update a Saved Recipe");
                                        System.out.println("");
                                    }

                                    //Option 3: Delete Recipe / Option 2: Update "currently constructed" recipe - Available IF a recipe is currently constructed.
                                    if (!Objects.isNull(recipeObj)) {

                                        System.out.println("Option 3: Update Recipe: " + recipeObj.getRecipeName());
                                        System.out.println("");
                                        System.out.println("Option 4: Delete Recipe");
                                        System.out.println("");
                                    }

                                    System.out.println("Option 5: Exit");
                                    System.out.println("");
                                    System.out.println("----------------------------------------------------------");

                                    //Grab user input and make sure it is a numerical value.
                                    UserInputValidation.validateUserMenuOption();

                                    //Option 1: Remove Recipe from Recipe Box / Option 2: Update a Saved Recipe - Prevent user acess IF the recipe box exists but doesn't have recipes OR IF the recipe box doesn't exist.
                                    if ((UserInputValidation.userOption == 1 || UserInputValidation.userOption == 2) && !recipeBoxHasRecipes(recipeBoxObj)) {

                                        //Reset user option
                                        UserInputValidation.userOption = 0;

                                    }

                                    //Option 3: Update Recipe - Prevent user acess IF there is not a currently constructed recipe.
                                    if (UserInputValidation.userOption == 3 && Objects.isNull(recipeObj)) {

                                        //Reset user option
                                        UserInputValidation.userOption = 0;

                                    }

                                    //Option 4: Delete Recipe - Prevent user access IF there is NOT a currently constructed recipe 
                                    if (UserInputValidation.userOption == 4 && Objects.isNull(recipeObj)) {

                                        //Reset user option
                                        UserInputValidation.userOption = 0;
                                    }
                                    // </editor-fold>

                                    switch (UserInputValidation.userOption) {

                                        //<editor-fold defaultstate="collapsed" desc="Remove Recipe from Recipe Box">
                                        case 1 -> {

                                            stateChange = recipeBoxObj.removeRecipeFromRecipeBox();

                                            if (stateChange) {

                                                //Get out of secondary sub menu and back into sub menu.
                                                isSecondSubMenu = false;
                                            }
                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Update Saved Recipe">
                                        case 2 -> {

                                            recipeBoxObj.updateRecipeBoxRecipe();
                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Update 'Recently Constructed' Recipe">
                                        case 3 -> {

                                            //Begin update process
                                            recipeObj.updateRecipe();

                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Delete Recipe">
                                        case 4 -> {
                                            stateChange = recipeObj.deleteRecipe();

                                            //If the recipe was deleted.
                                            if (stateChange) {

                                                //Clean up with Garbage collector.
                                                recipeObj = null;

                                                //Leave sub loop
                                                isSecondSubMenu = false;

                                            } else {

                                                //Exiting to Recipe Menu.
                                                MenuDisplays.appDisplays(2);
                                            }
                                        }
                                        // </editor-fold>

                                        //Exit
                                        case 5 -> {

                                            //Exiting to Recipe Menu.
                                            MenuDisplays.appDisplays(2);

                                            //Get out of secondary sub menu and back into sub menu.
                                            isSecondSubMenu = false;
                                        }
                                    }
                                }
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Recipe Display Sub-Menu">
                            case 4 -> {

                                //<editor-fold defaultstate="collapsed" desc="Recipe Display Sub-Menu Display & Validation">
                                //Setup while loop for secondary sub menu. 
                                isSecondSubMenu = true;

                                while (isSecondSubMenu) {

                                    System.out.println("----------------------------------------------------------");
                                    System.out.println("-----------------Recipe Display Sub-Menu------------------");
                                    System.out.println("----------------------------------------------------------");
                                    System.out.println("");
                                    System.out.println("Option 1: Display a Recipe");
                                    System.out.println("");
                                    System.out.println("Option 2: Display all Recipes");
                                    System.out.println("");
                                    System.out.println("Option 3: Exit");
                                    System.out.println("");
                                    System.out.println("----------------------------------------------------------");

                                    //Grab user input and make sure it is a numerical value.
                                    UserInputValidation.validateUserMenuOption();
                                    // </editor-fold>

                                    switch (UserInputValidation.userOption) {

                                        //<editor-fold defaultstate="collapsed" desc="Display (1) Recipe">
                                        case 1 -> {

                                            //Enable while loop below.
                                            displayingRecipe = true;

                                            while (displayingRecipe) {

                                                //Display (1) recipe
                                                recipeBoxObj.displayRecipeBoxRecipes(2);

                                                //Display another recipe?
                                                MenuDisplays.appDisplays(4);

                                                //Grab user input and make sure it is a numerical value.
                                                UserInputValidation.validateUserOption();

                                                //If user entered 2 for No.
                                                if (UserInputValidation.userOption == 2) {

                                                    //Return to Recipe Display Sub-Menu
                                                    displayingRecipe = false;

                                                }
                                            }
                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Display All Recipes in Recipe Box">
                                        case 2 -> {

                                            //Display Recipe Box w/ Header & Recipes
                                            recipeBoxObj.displayRecipeBoxRecipes(3);

                                            //Return to Recipe Display Sub-Menu
                                            displayingRecipe = false;

                                        }
                                        // </editor-fold>

                                        //Exit
                                        case 3 -> {

                                            //Exiting to Recipe Menu.
                                            MenuDisplays.appDisplays(2);

                                            //Get out of second sub menu.
                                            isSecondSubMenu = false;
                                        }
                                    }
                                }
                            }
                            // </editor-fold>

                            //Exit
                            case 5 -> {

                                //Exiting to Recipe Manager Main-Menu.
                                MenuDisplays.appDisplays(1);

                                //Get out of sub menu and back into main menu.
                                isSubMenu = false;
                            }
                        }
                    }

                }
                // </editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Branch for Ingredient Interaction and Creation.">
                case 3 -> {

                    //<editor-fold defaultstate="collapsed" desc="Ingredient Menu & Validation">
                    //Setup do while loop for sub-menu.
                    isSubMenu = true;

                    do {

                        //Ingredient Menu
                        MenuDisplays.appDisplays(15);

                        //Grab user input and make sure it is a numerical value.
                        UserInputValidation.validateUserMenuOption();

                        // Prevent access to "Add Ingredient to Recipe" if the ingredient or recipe do not exist
                        if (UserInputValidation.userOption == 2 && !ingredientAndRecipeExist(ingredientObj, recipeObj)) {
                            UserInputValidation.userOption = 0;
                        }

                        // Prevent access to "Delete Ingredient" if there is no currently constructed ingredient
                        if (UserInputValidation.userOption == 3 && Objects.isNull(ingredientObj)) {
                            UserInputValidation.userOption = 0;
                        }

                        // Prevent access to "Ingredient Update Menu"
                        // if there are no recipes with ingredients AND an ingredient is NOT active
                        if (UserInputValidation.userOption == 4 && (Objects.isNull(ingredientObj) && !recipeHasIngredients(recipeObj) && !recipeBoxHasRecipeIngredients(recipeBoxObj))) {
                            UserInputValidation.userOption = 0;
                        }

                        //Prevent access to "Ingredient Displays"
                        // if the recipe box doesn't have recipes OR if the recipe box has recipes but none of those recipes have an ingredient
                        if (UserInputValidation.userOption == 5 && !recipeBoxHasRecipeIngredients(recipeBoxObj)) {
                            UserInputValidation.userOption = 0;
                        }
                        // </editor-fold>

                        switch (UserInputValidation.userOption) {

                            //<editor-fold defaultstate="collapsed" desc="Create Ingredient">
                            case 1 -> {

                                //Alert user of deletion if an ingredient currently exists. 
                                if (!Objects.isNull(ingredientObj)) {

                                    //Warning Ingredient Exists
                                    MenuDisplays.appDisplays(14);

                                    //Grab user input and make sure it is a numerical value.
                                    UserInputValidation.validateUserOption();

                                    if (UserInputValidation.userOption == 1) {

                                        //Create a new ingredient.
                                        ingredientObj = new Ingredient();
                                        ingredientObj.createIngredient();

                                    } else {

                                        //Exiting to Ingredient Menu.
                                        MenuDisplays.appDisplays(5);
                                    }

                                } else {

                                    //Create a new ingredient.
                                    ingredientObj = new Ingredient();
                                    ingredientObj.createIngredient();
                                }
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Add Ingredient to Recipe">
                            case 2 -> {

                                stateChange = recipeObj.addIngredientToRecipe(ingredientObj);

                                if (stateChange) {

                                    //Clear ingredient object for new ingredient.
                                    ingredientObj = null;

                                }
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Delete Ingredient">
                            case 3 -> {
                                stateChange = ingredientObj.deleteIngredient();

                                if (stateChange) {

                                    //Clean up with Garbage collector.
                                    ingredientObj = null;

                                }
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Ingredient Update Menu">
                            case 4 -> {

                                //<editor-fold defaultstate="collapsed" desc="Ingredient Update Menu (Menu & Validation)">
                                //Setup while loop for secondary sub menu. 
                                isSecondSubMenu = true;

                                while (isSecondSubMenu) {

                                    //Ingredient Update Menu
                                    MenuDisplays.appDisplays(12);

                                    //Grab user input and make sure it is a numerical value.
                                    UserInputValidation.validateUserMenuOption();

                                    // Prevent access to "Update a Recipe's Ingredient" - if the current recipe or saved recipes do not have any ingredients
                                    if (UserInputValidation.userOption == 1 && (!recipeHasIngredients(recipeObj) && !recipeBoxHasRecipeIngredients(recipeBoxObj))) {
                                        UserInputValidation.userOption = 0;
                                    }

                                    //Prevent acess to "Remove Ingredient from Saved Recipe" - if the recipe box does NOT have at least 1 recipe with at least one ingredient
                                    if (UserInputValidation.userOption == 2 && !recipeBoxHasRecipeIngredients(recipeBoxObj)) {
                                        UserInputValidation.userOption = 0;
                                    }

                                    // Prevent access to "Update Ingredient" if there is no currently constructed ingredient
                                    if (UserInputValidation.userOption == 3 && Objects.isNull(ingredientObj)) {
                                        UserInputValidation.userOption = 0;
                                    }

                                    // Prevent access to "Add Ingredient to Recipe Box recipe" if the ingredient does not exist
                                    // and there are no recipes with ingredients in the recipe box
                                    if (UserInputValidation.userOption == 4 && (Objects.isNull(ingredientObj) || !recipeHasIngredients(recipeObj))) {
                                        UserInputValidation.userOption = 0;
                                    }
                                    // </editor-fold>

                                    switch (UserInputValidation.userOption) {

                                        //<editor-fold defaultstate="collapsed" desc="Update a Recipe's Ingredient">
                                        case 1 -> {

                                            updateRecipeIngredient();

                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Remove Ingredient from Saved Recipe">
                                        case 2 -> {

                                            removeIngredientFromSavedRecipe();
                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Update "Recently Constructed" Ingredient">
                                        case 3 -> {

                                            //Begin update process
                                            ingredientObj.updateIngredient();
                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Add Ingredient to Saved Recipe">
                                        case 4 -> {

                                            stateChange = addIngredientToSavedRecipe();

                                            if (stateChange) {

                                                //Clear ingredient object for new ingredient.
                                                ingredientObj = null;

                                            } else {

                                                //Ingredient not added to Recipe
                                                MenuDisplays.appDisplays(9);

                                            }
                                        }
                                        // </editor-fold>

                                        //Exit
                                        case 5 -> {

                                            //Exiting to Ingredient Menu.
                                            MenuDisplays.appDisplays(5);

                                            //Get out of secondary sub menu and back into sub menu.
                                            isSecondSubMenu = false;
                                        }
                                    }
                                }
                            }
                            // </editor-fold>

                            //<editor-fold defaultstate="collapsed" desc="Ingredient Display Menu">
                            case 5 -> {

                                //<editor-fold defaultstate="collapsed" desc="Ingredient Display Menu & Validation">
                                //Setup while loop for secondary sub menu. 
                                isSecondSubMenu = true;

                                while (isSecondSubMenu) {

                                    //Ingredient Display Menu
                                    MenuDisplays.appDisplays(13);

                                    //Grab user input and make sure it is a numerical value.
                                    UserInputValidation.validateUserMenuOption();
                                    // </editor-fold>

                                    switch (UserInputValidation.userOption) {

                                        //<editor-fold defaultstate="collapsed" desc="Display (1) Ingredient from a Recipe">
                                        case 1 -> {

                                            displayIngredientFromRecipe(0);
                                        }
                                        // </editor-fold>

                                        //<editor-fold defaultstate="collapsed" desc="Display All Ingredients from a Recipe">
                                        case 2 -> {

                                            displayIngredientFromRecipe(1);
                                        }
                                        // </editor-fold>

                                        //Exit
                                        case 3 -> {

                                            //Exit to main menu text.
                                            MenuDisplays.appDisplays(1);

                                            //Get out of sub menu and back into main menu.
                                            isSecondSubMenu = false;
                                        }
                                    }
                                }
                            }
                            // </editor-fold>

                            //Exit
                            case 6 -> {
                                //Exit to main menu text.
                                MenuDisplays.appDisplays(1);

                                //Get out of sub menu and back into main menu.
                                isSubMenu = false;
                            }
                        }

                    } while (isSubMenu);
                } // </editor-fold>

                //Option 4: Exit application.
                case 4 -> {
                    System.out.println("--------------------");
                    System.out.println("");
                    System.out.println("Exiting Application.");
                    System.out.println("");
                    System.out.println("--------------------");

                    UserInputValidation.scnr.close();

                    System.exit(0);
                }
            }
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">

    /**
     * Displays ingredients from a selected recipe based on the given toggle.
     * Allows the user to view ingredients before returning to the Ingredient
     * menu.
     *
     * @param toggle Determines the type of display: 0 - Display a single
     * recipe's ingredient. 1 - Display all ingredients of a recipe.
     */
    private static void displayIngredientFromRecipe(int toggle) {

        boolean displayingIngredient = true;

        //Allow user to view ingredients before being sent back to Ingredient menu.
        while (displayingIngredient) {

            //Show Recipe Box recipes and get User selection
            int recipeCount = selectRecipeFromRecipeBox();

            //Ensure User's input is between 1 and the total number of recipes counted.
            if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= recipeCount) {

                //For every recipe in the recipe box
                for (Recipe recipe : recipeBoxObj.getListOfRecipes()) {

                    //If the recipe's index within the recipe box matches the user's input.
                    if (UserInputValidation.userOption == recipeBoxObj.getListOfRecipes().indexOf(recipe) + 1) {

                        switch (toggle) {

                            //Diplay a recipe's ingredient
                            case 0 -> {

                                //Get the size of the ingredient list
                                final int ingredientCount = recipe.getRecipeIngredients().size();

                                System.out.println("------------------------------------------------------------------------------------");
                                System.out.println("");
                                System.out.println("Select from the list below to diplay Recipe: " + recipe.getRecipeName() + "'s Ingredient detail:");
                                System.out.println("");
                                System.out.println("------------------------------------------------------------------------------------");

                                //Display recipe ingredients ordered list
                                recipe.displayRecipe(3);

                                System.out.println("------------------------------------------------------------------------------------");

                                //Grab user input and make sure it is a numerical value.
                                UserInputValidation.validateUserOption();

                                //Ensure User's input is between 1 and the total number of ingredients counted.
                                if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= ingredientCount) {

                                    for (Ingredient ing : recipe.getRecipeIngredients()) {

                                        //If the ingredient's index within the recipe matches the user's input.
                                        if (UserInputValidation.userOption == recipe.getRecipeIngredients().indexOf(ing) + 1) {

                                            //Display entire ingredient
                                            ing.displayIngredient(1);

                                            //Display another ingredient
                                            MenuDisplays.appDisplays(16);

                                            break;
                                        }
                                    }
                                }
                            }

                            //Display a recipe's ingredients
                            case 1 -> {

                                //Display recipe with ordered list of ingredient names
                                recipe.displayRecipe(6);

                                //Display another set of ingredients
                                MenuDisplays.appDisplays(8);

                                break;

                            }
                        }

                        break;

                    }
                }

                //Grab user input and make sure it is a numerical value.
                UserInputValidation.validateUserOption();

                //If user selects no or any other invalid value.
                if (UserInputValidation.userOption != 1) {

                    //Exiting to Ingredient Display Menu.
                    MenuDisplays.appDisplays(7);

                    //Revert while loop boolean to exit.
                    displayingIngredient = false;

                    break;

                }
            } else {

                //Invalid response - Return to Ingredient Display Menu
                MenuDisplays.appDisplays(10);

                //Revert while loop boolean to exit.
                displayingIngredient = false;
            }
        }
    }

    /**
     * Provides options to update ingredients in either the currently
     * constructed recipe or a saved recipe in the Recipe Box.
     */
    private static void updateRecipeIngredient() {

        if (recipeHasIngredients(recipeObj) && recipeBoxHasRecipeIngredients(recipeBoxObj)) {

            //If currently constructed recipe has ingredients AND the recipe box has a recipe with at least 1 ingredient
            System.out.println("===============================");
            System.out.println("Which would you like to update?");
            System.out.println("===============================");
            System.out.println("");
            System.out.println("Option 1: Update Currently Constructed Recipe");
            System.out.println("");
            System.out.println("Option 2: Update Saved Recipe");
            System.out.println("");
            System.out.println("Option 3: Exit");
            System.out.println("===============================");

            //Grab user input and make sure it is a numerical value.
            UserInputValidation.validateUserOption();

            switch (UserInputValidation.userOption) {
                case 1 -> {
                    updateCCRecipeIngredient();
                }

                case 2 -> {
                    updateSavedRecipeBoxRecipeIngredient();
                }

                case 3 -> {
                    //Exiting to Ingredient Update Menu
                    MenuDisplays.appDisplays(6);
                }

                default -> {
                    //Invalid Response. Return to Ingredient Update Menu
                    MenuDisplays.appDisplays(11);
                }
            }

        } else if (recipeHasIngredients(recipeObj)) {

            updateCCRecipeIngredient();

        } else if (recipeBoxHasRecipeIngredients(recipeBoxObj)) {

            updateSavedRecipeBoxRecipeIngredient();
        }
    }

    /**
     * Displays recipes from the Recipe Box and prompts the user to select one.
     * It returns the total number of recipes in the Recipe Box for validation.
     *
     * @return The number of recipes in the Recipe Box.
     */
    private static int selectRecipeFromRecipeBox() {

        //Create int to store the total number of recipes within the recipe box
        int recipeCount = recipeBoxObj.getListOfRecipes().size();

        //Display the recipe box's list of recipes with the intent on displaying the recipe's ingredients.
        recipeBoxObj.displayRecipeBoxRecipes(4);

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserOption();

        return recipeCount;
    }

    /**
     * Displays ingredients of a given recipe and prompts the user to select
     * one. It returns the total number of ingredients in the recipe for
     * validation.
     *
     * @param recipeObj The recipe from which ingredients are to be selected.
     * @return The number of ingredients in the given recipe.
     */
    private static int selectIngredientFromRecipe(Recipe recipeObj) {

        //Create int to store the total number of ingredients within the recipe
        int ingredientCount = recipeObj.getRecipeIngredients().size();

        //Show all ingredients in the recipe for update
        recipeObj.displayRecipe(2);

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserOption();

        return ingredientCount;
    }

    /**
     * Allows the user to update ingredients of a saved recipe in the Recipe
     * Box. The user selects a recipe and then chooses an ingredient to update.
     */
    private static void updateSavedRecipeBoxRecipeIngredient() {

        //Show Recipe Box recipes and get User selection
        int recipeCount = selectRecipeFromRecipeBox();

        //Validate the user's input.
        if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= recipeCount) {

            //For every recipe in the recipe box
            for (Recipe recipe : recipeBoxObj.getListOfRecipes()) {

                //If a recipe's index within the list of recipe box recipes matches the user's input.
                if (UserInputValidation.userOption == recipeBoxObj.getListOfRecipes().indexOf(recipe) + 1) {

                    //Show an the recipe's ingredients for selection
                    int ingredientCount = selectIngredientFromRecipe(recipe);

                    //Validate the user's input.
                    if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= ingredientCount) {

                        //For every ingredient in the recipe
                        for (Ingredient ingredient : recipe.getRecipeIngredients()) {

                            //If a recipe's index within the list of recipes matches the user's input.
                            if (UserInputValidation.userOption == recipe.getRecipeIngredients().indexOf(ingredient) + 1) {

                                ingredient.updateIngredient();

                                break;
                            }
                        }

                    } else {

                        //Invalid Response. Return to Ingredient Update Menu
                        MenuDisplays.appDisplays(11);

                        break;
                    }
                }
            }
        }
    }

    /**
     * Updates an ingredient in the currently constructed recipe. The user
     * selects an ingredient from the recipe to update.
     */
    private static void updateCCRecipeIngredient() {

        int ingredientCount = selectIngredientFromRecipe(recipeObj);

        //Validate the user's input.
        if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= ingredientCount) {

            //For every ingredient in the recipe
            for (Ingredient ingredient : recipeObj.getRecipeIngredients()) {

                //If a recipe's index within the list of recipes matches the user's input.
                if (UserInputValidation.userOption == recipeObj.getRecipeIngredients().indexOf(ingredient) + 1) {

                    ingredient.updateIngredient();

                    break;
                }
            }

        } else {

            //Invalid Response. Return to Ingredient Update Menu
            MenuDisplays.appDisplays(11);

        }
    }

    /**
     * Removes an ingredient from a saved recipe in the Recipe Box. The user
     * selects a recipe and then chooses an ingredient to remove.
     */
    private static void removeIngredientFromSavedRecipe() {

        //Show Recipe Box recipes and get User selection
        int recipeCount = selectRecipeFromRecipeBox();

        //Validate the user's input.
        if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= recipeCount) {

            //For every recipe in the recipe box
            for (Recipe recipe : recipeBoxObj.getListOfRecipes()) {

                //If a recipe's index within the list of recipes matches the user's input.
                if (UserInputValidation.userOption == recipeBoxObj.getListOfRecipes().indexOf(recipe) + 1) {

                    //Call to the remove ingredient from recipe method.
                    recipe.removeIngredientfromRecipe();

                    break;
                }
            }
        }
    }

    /**
     * Adds an ingredient to a selected recipe in the Recipe Box. Prompts the
     * user to choose a recipe and then adds the specified ingredient.
     *
     * @return true if the ingredient is added successfully, false otherwise.
     */
    private static boolean addIngredientToSavedRecipe() {

        //Create int to store the total number of recipes within the recipe box
        final int recipeCount = recipeBoxObj.getListOfRecipes().size();

        //Request information from the user.
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Select from the list of Recipes to add Ingredient: " + ingredientObj.getNameOfIngredient() + ", to its Ingredient List.");
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------");

        //Display ordered list of the recipe box's recipes.
        recipeBoxObj.displayRecipeBoxRecipes(1);

        //Visually contain all recipes in list.
        System.out.println("---------------------------------------------------------------------");

        //Grab user input and make sure it is a numerical value.
        UserInputValidation.validateUserOption();

        //Validate the user's input.
        if (UserInputValidation.userOption != 0 && UserInputValidation.userOption <= recipeCount) {

            //For every recipe in the recipe box
            for (Recipe recipe : recipeBoxObj.getListOfRecipes()) {

                //If a recipe's index within the list of recipes matches the user's input.
                if (UserInputValidation.userOption == recipeBoxObj.getListOfRecipes().indexOf(recipe) + 1) {

                    recipe.addIngredientToRecipe(ingredientObj);

                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Ingredient: " + ingredientObj.getNameOfIngredient() + " added to Recipe: " + recipe.getRecipeName() + ".");
                    System.out.println("");
                    System.out.println("Recipe Box: " + recipeBoxObj.getRecipeBoxName() + " updated. Returning to the Ingredient Menu.");
                    System.out.println("-------------------------------------------------------------");

                    return true;
                }
            }

        } else {

            //Ingredient not added to Recipe
            MenuDisplays.appDisplays(9);

            return false;
        }

        return false;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    static Recipe getRecipeObj() {
        return recipeObj;
    }

    static void setRecipeObj(Recipe recipe) {
        recipeObj = recipe;
    }

    static RecipeBox getRecipeBoxObj() {
        return recipeBoxObj;
    }

    static void setRecipeBoxObj(RecipeBox recipeBox) {
        recipeBoxObj = recipeBox;
    }

    static Ingredient getIngredientObj() {
        return ingredientObj;
    }

    static void setIngredientObj(Ingredient ingredient) {
        ingredientObj = ingredient;
    }

    // </editor-fold>
}
