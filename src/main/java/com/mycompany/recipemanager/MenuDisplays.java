package com.mycompany.recipemanager;

import static com.mycompany.recipemanager.StateValidation.*;
import java.util.Objects;

/**
 * The MenuDisplays class is used for displaying various menu options and
 * messages to the user based on different application states and contexts.
 */
public class MenuDisplays {

    /**
     * Displays different types of messages and menus to the user based on the
     * specified toggle value.
     *
     * @param toggle An integer value that determines which set of display
     * instructions to execute.
     *
     * 1: Displays a message indicating exit to the Recipe Manager main menu. 2
     * to 4: Display messages related to recipes. 5 to 11: Display messages
     * related to ingredients.
     */
    public static void appDisplays(int toggle) {

        switch (toggle) {

            //Invalid Recipe Manager Menu Option
            case 0 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Invalid Menu Option.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }

            // Recipe Manager Related Case
            case 1 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Exiting to Recipe Manager Main-Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }

            // Recipe Related Cases
            case 2 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Exiting to Recipe Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 3 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Exiting to Recipe Display Sub-Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 4 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Would you like to display another Recipe?");
                System.out.println("");
                System.out.println("Enter: 1 - Yes, 2 - No");
                System.out.println("--------------------------------------");
            }

            // Ingredient Related Cases
            case 5 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Exiting to Ingredient Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 6 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Exiting to Ingredient Update Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 7 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Exiting to Ingredient Display Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 8 -> {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("Would you like to display another set of Ingredients?");
                System.out.println("");
                System.out.println("Enter: 1 - Yes, 2 - No");
                System.out.println("---------------------------------------------------------------------");
            }
            case 9 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Ingredient not added to Recipe. Returning to the Ingredient Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 10 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Invalid Response. Exiting to Ingredient Display Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }
            case 11 -> {
                System.out.println("--------------------------------------");
                System.out.println("");
                System.out.println("Invalid Response. Exiting to Ingredient Update Menu.");
                System.out.println("");
                System.out.println("--------------------------------------");
            }

            //Ingredient Update Menu
            case 12 -> {

                System.out.println("----------------------------------------------------------");
                System.out.println("-----------------Ingredient Update Menu-------------------");
                System.out.println("----------------------------------------------------------");
                System.out.println("");

                // Option 1: Update a Recipe's Ingredient / Display option if the active recipe has ingredients OR if the recipe box has at least 1 recipe that has at least one ingredient
                if (recipeHasIngredients(RecipeManager.getRecipeObj()) || recipeBoxHasRecipeIngredients(RecipeManager.getRecipeBoxObj())) {
                    System.out.println("Option 1: Update a Recipe Ingredient");
                    System.out.println("");

                }

                //Option 2: Remove Ingredient from Recipe - Display option if the recipe box has at least 1 recipe that has at least one ingredient
                if (recipeBoxHasRecipeIngredients(RecipeManager.getRecipeBoxObj())) {
                    System.out.println("Option 2: Remove Ingredient from Saved Recipe.");
                    System.out.println("");
                }

                // Option 3: Update Ingredient - Available if an ingredient is currently constructed
                if (!Objects.isNull(RecipeManager.getIngredientObj())) {
                    System.out.println("Option 3: Update Ingredient: " + RecipeManager.getIngredientObj().getNameOfIngredient());
                    System.out.println("");
                }

                // Option 4: Add Ingredient to Recipe Box recipe - Available if an ingredient exists and the recipe box has recipes
                if (!Objects.isNull(RecipeManager.getIngredientObj()) && recipeBoxHasRecipes(RecipeManager.getRecipeBoxObj())) {
                    System.out.println("Option 4: Add Ingredient to Recipe Box recipe");
                    System.out.println("");
                }

                System.out.println("Option 5: Exit");
                System.out.println("----------------------------------------------------------");
            }

            //Ingredient Display Menu
            case 13 -> {
                System.out.println("----------------------------------------------------------");
                System.out.println("-----------------Ingredient Display Menu------------------");
                System.out.println("----------------------------------------------------------");
                System.out.println("");
                System.out.println("Option 1: Display (1) Ingredient from a Recipe");
                System.out.println("");
                System.out.println("Option 2: Display All Ingredients from a Recipe");
                System.out.println("");
                System.out.println("Option 3: Exit");
                System.out.println("");
                System.out.println("----------------------------------------------------------");
            }

            //Ingredient create prevention
            case 14 -> {
                System.out.println("--------------------------------------------------------------------------------------------------------");
                System.out.println("");
                System.out.println("Warning! If you attempt to create another Ingredient before adding it to a Recipe,");
                System.out.println("the current Ingredient: " + RecipeManager.getIngredientObj().getNameOfIngredient() + ", will be deleted.");
                System.out.println("");
                System.out.println("Continue? 1 - Yes / 2 - No");
                System.out.println("");
                System.out.println("--------------------------------------------------------------------------------------------------------");
                System.out.println("");
            }
            case 15 -> {
                // Display the Ingredient Sub-Menu header
                System.out.println("");
                System.out.println("--------------------------------------");
                System.out.println("           Ingredient Menu            ");
                System.out.println("--------------------------------------");

                // Option 1: Create Ingredient
                System.out.println("Option 1: Create Ingredient.");
                System.out.println("");

                // Option 2: Add Ingredient to Recipe - Display if an ingredient and a recipe exist but are not saved
                if (ingredientAndRecipeExist(RecipeManager.getIngredientObj(), RecipeManager.getRecipeObj())) {
                    System.out.println("Option 2: Add Ingredient to Recipe");
                    System.out.println("");
                }

                // Option 3: Delete Ingredient - Display if an ingredient is currently constructed
                if (!Objects.isNull(RecipeManager.getIngredientObj())) {
                    System.out.println("Option 3: Delete Ingredient");
                    System.out.println("");
                }

                // Option 4: Ingredient Update Menu - Display if there are recipes with ingredients OR if an ingredient is active
                if (!Objects.isNull(RecipeManager.getIngredientObj()) || (recipeHasIngredients(RecipeManager.getRecipeObj()) || recipeBoxHasRecipeIngredients(RecipeManager.getRecipeBoxObj()))) {
                    System.out.println("Option 4: Ingredient Update Menu");
                    System.out.println("");
                }

                // Option 5: Ingredient Displays - Display if a recipe box exists and has recipes with ingredients
                if (recipeBoxHasRecipeIngredients(RecipeManager.getRecipeBoxObj())) {
                    System.out.println("Option 5: Ingredient Displays");
                    System.out.println("");
                }

                System.out.println("Option 6: Exit");
                System.out.println("--------------------------------------");
            }

            //Display another ingredient question
            case 16 -> {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("");
                System.out.println("Would you like to display another Ingredient?");
                System.out.println("");
                System.out.println("Enter: 1 - Yes, 2 - No");
                System.out.println("---------------------------------------------------------------------");
            }
        }
    }
}
