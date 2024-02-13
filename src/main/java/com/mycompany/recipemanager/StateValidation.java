package com.mycompany.recipemanager;

/**
 * Provides state validation methods for RecipeBox, Recipe, and Ingredient
 * objects. Used to centralize and simplify checks on the state of these objects
 * throughout the application.
 */
public class StateValidation {

    /**
     * Checks if a given RecipeBox object has any recipes.
     *
     * @param recipeBoxObj the RecipeBox object to be checked.
     * @return true if the RecipeBox is not null and has recipes, false
     * otherwise.
     */
    public static boolean recipeBoxHasRecipes(RecipeBox recipeBoxObj) {
        return recipeBoxObj != null && !recipeBoxObj.getListOfRecipes().isEmpty();
    }

    /**
     * Checks if any recipe in a given RecipeBox object has ingredients.
     *
     * @param recipeBoxObj the RecipeBox object containing recipes to be
     * checked.
     * @return true if at least one recipe in the RecipeBox has ingredients,
     * false otherwise.
     */
    public static boolean recipeBoxHasRecipeIngredients(RecipeBox recipeBoxObj) {
        if (recipeBoxHasRecipes(recipeBoxObj)) {
            for (Recipe recipe : recipeBoxObj.getListOfRecipes()) {
                if (recipeHasIngredients(recipe)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a given Recipe object has any ingredients.
     *
     * @param recipeObj the Recipe object to be checked.
     * @return true if the Recipe is not null and has ingredients, false
     * otherwise.
     */
    public static boolean recipeHasIngredients(Recipe recipeObj) {
        return recipeObj != null && !recipeObj.getRecipeIngredients().isEmpty();
    }

    /**
     * Checks if both an Ingredient and a Recipe object exist.
     *
     * @param ingredientObj the Ingredient object to be checked.
     * @param recipeObj the Recipe object to be checked.
     * @return true if both Ingredient and Recipe objects are not null, false
     * otherwise.
     */
    public static boolean ingredientAndRecipeExist(Ingredient ingredientObj, Recipe recipeObj) {
        return ingredientObj != null && recipeObj != null;
    }
}
