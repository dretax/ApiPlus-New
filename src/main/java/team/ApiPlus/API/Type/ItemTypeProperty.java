package team.ApiPlus.API.Type;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;

import team.ApiPlus.API.PropertyHolder;

/**
 * @author Atlan1
 * @version 1.0
 */
public abstract class ItemTypeProperty extends ItemType implements PropertyHolder {

	private Map<String, Object> properties = new HashMap<String, Object>();
	
	public ItemTypeProperty(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}
	
	@Override
	public Object getProperty(String id) {
		return properties.get(id);
	}

	@Override
	public void addProperty(String id, Object property) {
		if(!properties.containsKey(id))
			properties.put(id, property);
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}

	@Override
	public void setProperties(Map<String, Object> properties) {
		this.properties = new HashMap<String, Object>(properties);
	}

	@Override
	public void removeProperty(String id) {
		if(properties.containsKey(id))
			properties.remove(id);
	}

	@Override
	public void editProperty(String id, Object property) {
		if(properties.containsKey(id))
			properties.put(id, property);
	}
	
	@Override
	public void setProperty(String id, Object property) {
		addProperty(id, property);
		editProperty(id, property);
	}

}
