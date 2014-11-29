package team.ApiPlus.API.Effect;

/** The effect interface - Implement to create your own effects
 * @author Atlan1
 * @version 1.0
 */
public interface Effect {

	/** Used to receive the effect target of an Effect
	 * @return A Implementation of the EffectTarget interface
	 */
	EffectTarget getEffectTarget();
	
	/**Sets the effect target of this effect
	 * @param et EffectTarget to set to
	 */
	void setEffectTarget(EffectTarget et);
	
	/** Method used to perform this kind of effect using the given arguments
	 * @param arguments Arguments 
	 */
	void performEffect(Object...arguments);
}

