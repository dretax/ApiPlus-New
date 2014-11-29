package team.ApiPlus.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import team.ApiPlus.API.Effect.Effect;
import team.ApiPlus.Util.Utils;

/** EffectManager of API+, used to registering and building new Effects
 * @author Atlan1
 * @version 1.0
 */
public class EffectManager {
	private static EffectManager instance;
	private Map<String, Class<? extends Effect>> effecttypes = new HashMap<String, Class<? extends Effect>>();
	
	private EffectManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Effect Manager.");
	}
	
	
	/** Method used to build a new effect instance from a registered type.
	 * @param type The type to use
	 * @param args The arguments to be passed to the constructor
	 * @return The build Effect
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public Effect buildEffect(String type, Object...args) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Class<? extends Effect> t = this.getEffectType(type);
		Constructor<? extends Effect> con = t.getConstructor(Object[].class);
		try{
			Effect e = con.newInstance(new Object[]{args});
			return e;
		}catch(InvocationTargetException e){
			Utils.info(e.getCause()+"; "+e.getTargetException()+", "+type);
		}
		return null;
	}
	
	/**
	 * Method used to register Effect Type to API+.
	 * @param name String name of Type.
	 * @param c Class of Type.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public boolean registerEffectType(String name, Class<? extends Effect> c) {
		if(checkEffectType(name)) return false;
		else {
			effecttypes.put(name, c);
			return true;
		}
	}
	
	/**
	 * Method used to unregister Effect Type from API+.
	 * @param name String name of Type.
	 * @return
	 */
	public boolean unregisterEffectType(String name) {
		if(checkEffectType(name)) {
			effecttypes.remove(name);
			return true;
		} else return false;
	}
	
	/**
	 * Method used to check if Type name is already registered.
	 * @param name String name of Type to check.
	 * @return boolean True if already taken, false it not.
	 */
	public boolean checkEffectType(String name) {
		if(effecttypes.containsKey(name)) return true;
		else return false;
	}
	
	/**
	 * Method used to check if a class is already registered under a id.
	 * @param e Class to check
	 * @return boolean True if already registered, false if not.
	 */
	public boolean checkEffectName(Class<? extends Effect> e) {
		if(effecttypes.containsValue(e)) return true;
		else return false;
	}
	
	/**
	 * Method used to get Effect Type by name.
	 * @param name String name of Type to find.
	 * @return Effect Returns found Effect, null if none found.
	 */
	public Class<? extends Effect> getEffectType(String name) {
		if(checkEffectType(name)) return effecttypes.get(name);
		else return null;
	}
	
	/**
	 * Method used to get Effect Name by type.
	 * @param clazz Class of name to find
	 * @return Effect Returns found Name, null if none found.
	 */
	public String getEffectName(Class<? extends Effect> clazz) {
		if(checkEffectName(clazz)) return (String) Utils.getKey(this.effecttypes, clazz);
		else return null;
	}
	
	/**
	 * Method used for getting an Instance of the API's EffectManger, Only one instance is allowed.
	 * @return EffectManger Instance of the API's EffectManger.
	 */
	public static EffectManager getInstance() {
		if(instance == null) instance = new EffectManager();
		return instance;
	}
}
