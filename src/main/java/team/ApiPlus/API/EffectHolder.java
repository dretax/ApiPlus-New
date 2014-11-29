package team.ApiPlus.API;

import java.util.List;

import team.ApiPlus.API.Effect.Effect;

/** Indicates that the implementing class holds effects
 * @author Atlan1
 * @version 1.0
 */
public interface EffectHolder {

	
	/** Method used to receive the effects
	 * @return A list of all hold effects
	 */
	public List<Effect> getEffects();
	
	
	/** Method used to set the effects
	 * @param effects The effects to set to
	 */
	public void setEffects(List<Effect> effects);
	
	
	/** Method used to perform the hold effects with the given arguments
	 * @param args Arguments
	 */
	public  void performEffects(Object... args);
}
