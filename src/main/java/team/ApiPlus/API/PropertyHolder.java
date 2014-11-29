package team.ApiPlus.API;

import java.util.Map;

/** PropertyHolder that contains and manages properties of a class
 * @author Atlan1
 * @version 1.0
 */
public interface PropertyHolder {

	public Object getProperty(String id);
	public void setProperty(String id, Object property);
	public void addProperty(String id, Object property);
	public void removeProperty(String id);
	public void editProperty(String id, Object property);
	public Map<String, Object> getProperties();
	public void setProperties(Map<String, Object> properties);
	
}
