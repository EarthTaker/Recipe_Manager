package com.mycompany.recipemanager;

import java.util.ArrayList;

/**
 * The AppTesting class represents the test construction center that pulls in
 * the required objects from the Recipe Manager to populate their values for
 * testing purposes.
 *
 * @author Talka
 */
public class AppTesting {

    private static RecipeBox recipeBoxObj;
    private static Recipe recipeObj;
    private static Ingredient ingredientObj;

    /**
     * AppTesting Constructor - Creates a Recipe Box with Recipes and
     * Ingredients. Also saves at least 1 Recipe and Ingredient to represent
     * "currently constructed" objects.
     *
     * @param toggle - Enables secondary toggle to select Application Testing OR
     * Unit Testing
     */
    public AppTesting(boolean toggle) {

        makeNewRecipeBox();

        makeNewRecipe(0);

        makeNewIngredient(0);

        makeNewIngredient(1);

        makeNewIngredient(2);

        //Make Recipe to represent "currently constructed" recipe.
        makeNewRecipe(1);

        makeNewIngredient(2);

        makeNewIngredient(3);

        makeNewIngredient(4);

        if (toggle) {

            //Save Recipe Box as Test data 
            RecipeManager.setRecipeBoxObj(recipeBoxObj);

            //Set recipe as "currently constructed" recipe.
            RecipeManager.setRecipeObj(recipeObj);

            //Also set this ingredient as "currently constructed" ingredient.
            RecipeManager.setIngredientObj(ingredientObj);
        }

    }

    /**
     * Creates a new Recipe Box for testing.
     *
     * @return - Returns a recipe box for Unit Testing.
     */
    public static RecipeBox makeNewRecipeBox() {

        recipeBoxObj = new RecipeBox();
        recipeBoxObj.setRecipeBoxName("Totally Radical Recipe Box");
        recipeBoxObj.setListOfRecipes(new ArrayList<>());

        return recipeBoxObj;

    }

    /**
     * Creates a new recipe for testing.
     *
     * @param toggle - Selects the recipe to create.
     * @return - A Recipe that has a name, servings, total recipe calories, and
     * an empty ingredient list.
     */
    public static Recipe makeNewRecipe(int toggle) {

        recipeObj = new Recipe();

        ArrayList recipeInstructions = new ArrayList<String>();

        switch (toggle) {

            case 0 -> {
                recipeObj.setRecipeName("Fruit Salad");
                recipeObj.setServings(89);
                recipeObj.setTotalRecipeCalories(4554);
                recipeObj.setRecipeIngredients(new ArrayList<>());

                recipeInstructions.add("Discover your no match for the Fruit Salaaad.");
                recipeInstructions.add("Go on a journey of self discovery.");
                recipeInstructions.add("Find the inner strength you knew you had all along.");
                recipeInstructions.add("Find out the Fruit Salaaad killed your family.");
                recipeInstructions.add("Stir Fruit Salaaad at 3000 rpm until dead or thoroughly mixed.");

                recipeObj.setRecipeInstructions(recipeInstructions);

                //Add recipe to recipe box
                recipeBoxObj.getListOfRecipes().add(recipeObj);

            }

            case 1 -> {
                recipeObj.setRecipeName("Strawberry Pie");
                recipeObj.setServings(34);
                recipeObj.setTotalRecipeCalories(444);
                recipeObj.setRecipeIngredients(new ArrayList<>());

                recipeInstructions.add("Discover your no match for the pie.");
                recipeInstructions.add("Go on a journey of self discovery.");
                recipeInstructions.add("Find the inner strength you knew you had all along.");
                recipeInstructions.add("Find out the pie killed your family.");
                recipeInstructions.add("Bake pie at 3000 degrees until dead or moist at least.");

                recipeObj.setRecipeInstructions(recipeInstructions);

            }
        }
        return recipeObj;
    }

    /**
     * Creates a new ingredient for testing.
     *
     * @param toggle - Selects the ingredient to create.
     * @return - Returns ingredient object populated with a name, ingredient
     * amount, number of calories, total calories, and a unit of measurement.
     */
    public static Ingredient makeNewIngredient(int toggle) {

        ingredientObj = new Ingredient();

        switch (toggle) {
            case 0 -> {
                ingredientObj.setNameOfIngredient("Kiwis");
                ingredientObj.setIngredientAmount(30);
                ingredientObj.setNumberCaloriesPerUnit(300);
                ingredientObj.setTotalCalories(3000);
                ingredientObj.setUnitMeasurement("g -Gram");

                //Add ingredient to recipe.
                recipeObj.getRecipeIngredients().add(ingredientObj);
            }

            case 1 -> {
                ingredientObj.setNameOfIngredient("Dough");
                ingredientObj.setIngredientAmount(16);
                ingredientObj.setNumberCaloriesPerUnit(400);
                ingredientObj.setTotalCalories(4000);
                ingredientObj.setUnitMeasurement("fl oz -Fluid Ounce");

                //Add ingredient to recipe.
                recipeObj.getRecipeIngredients().add(ingredientObj);
            }

            case 2 -> {
                ingredientObj.setNameOfIngredient("Sugar");
                ingredientObj.setIngredientAmount(40);
                ingredientObj.setNumberCaloriesPerUnit(500);
                ingredientObj.setTotalCalories(6000);
                ingredientObj.setUnitMeasurement("g -Gram");

                //Add ingredient to recipe.
                recipeObj.getRecipeIngredients().add(ingredientObj);
            }

            case 3 -> {
                ingredientObj.setNameOfIngredient("Blueberries");
                ingredientObj.setIngredientAmount(1);
                ingredientObj.setNumberCaloriesPerUnit(100);
                ingredientObj.setTotalCalories(1000);
                ingredientObj.setUnitMeasurement("cup -Cup");

                //Add ingredient to recipe.
                recipeObj.getRecipeIngredients().add(ingredientObj);
            }

            case 4 -> {
                ingredientObj.setNameOfIngredient("Strawberries");
                ingredientObj.setIngredientAmount(2);
                ingredientObj.setNumberCaloriesPerUnit(200);
                ingredientObj.setTotalCalories(2000);
                ingredientObj.setUnitMeasurement("fl oz -Fluid Ounce");

                //Add ingredient to recipe.
                recipeObj.getRecipeIngredients().add(ingredientObj);
            }

            case 5 -> {
                ingredientObj.setNameOfIngredient("Potatoes");
                ingredientObj.setIngredientAmount(5);
                ingredientObj.setNumberCaloriesPerUnit(300);
                ingredientObj.setTotalCalories(1500);
                ingredientObj.setUnitMeasurement("cup -Cup");

            }
        }

        return ingredientObj;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public static Recipe getRecipeObj() {
        return recipeObj;
    }

    public static void setRecipeObj(Recipe recipe) {
        recipeObj = recipe;
    }

    public static RecipeBox getRecipeBoxObj() {
        return recipeBoxObj;
    }

    public static void setRecipeBoxObj(RecipeBox recipeBox) {
        recipeBoxObj = recipeBox;
    }

    public static Ingredient getIngredientObj() {
        return ingredientObj;
    }

    public static void setIngredientObj(Ingredient ingredient) {
        ingredientObj = ingredient;
    }

    // </editor-fold>
}
