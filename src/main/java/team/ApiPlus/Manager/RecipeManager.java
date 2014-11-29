package team.ApiPlus.Manager;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.inventory.SpoutShapelessRecipe;
import org.getspout.spoutapi.material.MaterialData;

import team.ApiPlus.ApiPlus;
import team.ApiPlus.Util.Utils;

/**
 * Recipe Manager class for API+.
 * @author SirTyler, Atlan1
 * @version 1.0
 */
public class RecipeManager {
	
	/**
	 * Enum representing the Type of Recipe.
	 * @author SirTyler, Atlan1
	 */
	public enum RecipeType{
		SHAPED, SHAPELESS, FURNACE;
	}
	
	/**
	 * Method used for adding a recipe to Server.
	 * @param type Enum RecipeType representing the Type of Recipe to create.
	 * @param ingredients List<ItemStack> of Items to use as Ingredients.
	 * @param result ItemStack representing the result of the recipe.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public static boolean addRecipe(RecipeType type, List<ItemStack> ingredients, ItemStack result) {
		if(type.equals(RecipeType.SHAPED)&&ingredients.size()!=9) {
			return false;
		}
		if(type.equals(RecipeType.FURNACE)&&(ingredients.size()==0||ingredients.get(0)==null)) {
			return false;
		}
		boolean useSpout = Utils.containsCustomItems(ingredients);
		switch(type){
			case SHAPED:
				return addShapedRecipe(ingredients, result, useSpout);
			case SHAPELESS:
				return addShapelessRecipe(ingredients, result, useSpout);
			case FURNACE:
				return addFurnaceRecipe(ingredients.get(0), result, useSpout);
		}
		return false;
	}
	
	/**
	 * Method used for adding a recipe to Server.
	 * @param type String representing the desired Type of Recipe.
	 * @param ingredients List<ItemStack> of Items to use as Ingredients.
	 * @param result ItemStack representing the result of recipe.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public static boolean addRecipe(String type, List<ItemStack> ingredients, ItemStack result) {
		return addRecipe(RecipeType.valueOf(type.toUpperCase()), ingredients, result);
	}
	
	/**
	 * Method used for adding a Shaped recipe to Server.
	 * @param ingredients List<ItemStack> representing Ingredients to be used.
	 * @param result ItemStack representing the result of the recipe.
	 * @param spout boolean representing the use of Spout. True means use, False means do not.
	 * @return boolean True if action completed successfully, False if not.
	 */
	private static boolean addShapedRecipe(List<ItemStack> ingredients, ItemStack result, boolean spout){
		try {
			char[] name = {'a','b','c',
						   'd','e','f',
		    			   'g','h','i'};
			int i = 0;
			if(spout){
				SpoutShapedRecipe x = (SpoutShapedRecipe)new SpoutShapedRecipe(result);
				x.shape("abc","def","ghi");
				for(ItemStack item : ingredients) {
					if(item.getTypeId()==0){
						i++;
						continue;
					}
					SpoutItemStack ingred = new SpoutItemStack(item);
					x.setIngredient(name[i], MaterialData.getMaterial(ingred.getTypeId(),(short)ingred.getDurability()));
					i++;
				}
				SpoutManager.getMaterialManager().registerSpoutRecipe(x);
				return true;
			} else{
				ShapedRecipe x = (ShapedRecipe)new ShapedRecipe(result);
				x.shape("abc", "def", "ghi");
				for(ItemStack item : ingredients) {
					if(item.getTypeId()==0){
						i++;
						continue;
					}
					ItemStack ingred = new ItemStack(item);
					x.setIngredient(name[i], ingred.getType());
					i++;
				}
				Bukkit.addRecipe(x);
				return true;
			}
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
	
	/**
	 * Method used for adding a Shapeless recipe to Server.
	 * @param ingredients List<ItemStack> representing Ingredients to be used.
	 * @param result ItemStack representing the result of the recipe.
	 * @param spout boolean representing the use of Spout. True means use, False means do not.
	 * @return boolean True if action completed successfully, False if not.
	 */
	private static boolean addShapelessRecipe(List<ItemStack> ingredients, ItemStack result, boolean spout){
		try {
			if(spout){
				SpoutShapelessRecipe x = new SpoutShapelessRecipe(result);
				for(ItemStack item : ingredients){
					SpoutItemStack ingred = new SpoutItemStack(item);
					x.addIngredient(MaterialData.getMaterial(ingred.getTypeId(),ingred.getDurability()));
				}
				SpoutManager.getMaterialManager().registerSpoutRecipe(x);
				return true;
			}else{
				ShapelessRecipe x = new ShapelessRecipe(result);
				for(ItemStack item : ingredients){
					ItemStack ingred = new ItemStack(item);
					x.addIngredient(ingred.getType());
				}
				Bukkit.addRecipe(x);
				return true;
			}
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
	
	/**
	 * Method used for adding a Furnace recipe to Server.
	 * @param ingredients List<ItemStack> representing Ingredients to be used.
	 * @param result ItemStack representing the result of the recipe.
	 * @param spout boolean representing the use of Spout. True means use, False means do not.
	 * @return boolean True if action completed successfully, False if not.
	 */
	private static boolean addFurnaceRecipe(ItemStack input, ItemStack result, boolean spout){
		try {
			if(spout){
				if(ApiPlus.hooks.containsKey("FurnaceAPI")) {
					//TODO: Error with SpoutFurnaceRecipes
					/*SpoutFurnaceRecipe x = new SpoutFurnaceRecipe(new SpoutItemStack(input), new SpoutItemStack(result));
					SpoutFurnaceRecipes.registerSpoutRecipe(x);
					return true;*/
				}
				return false;
			}else{
				FurnaceRecipe x = new FurnaceRecipe(new ItemStack(result), new ItemStack(input).getType() );
				Bukkit.addRecipe(x);
				return true;
			}
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
}
