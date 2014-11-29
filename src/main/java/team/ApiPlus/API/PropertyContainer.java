package team.ApiPlus.API;

import java.util.HashMap;
import java.util.Map;

/** This class is used for storing data easier, just create an object and you can use all easy to understand methods
 * @author Atlan1
 * @version 1.0
 */
public class PropertyContainer implements PropertyHolder{

	private Map<String, Object> properties = new HashMap<String, Object>();

	/**Constuctor initializes the properties field.
	 * @param props a Map<String, Object> containing the properties
	 */
	public PropertyContainer(Map<String, Object> props) {
		setProperties(props);
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
