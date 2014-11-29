package team.ApiPlus.API.Effect;

import team.ApiPlus.API.Effect.Effect;
import team.ApiPlus.API.Effect.Default.BreakEffect;
import team.ApiPlus.API.Effect.Default.BurnEffect;
import team.ApiPlus.API.Effect.Default.ExplosionEffect;
import team.ApiPlus.API.Effect.Default.LightningEffect;
import team.ApiPlus.API.Effect.Default.MoveEffect;
import team.ApiPlus.API.Effect.Default.ParticleEffect;
import team.ApiPlus.API.Effect.Default.PlaceEffect;
import team.ApiPlus.API.Effect.Default.PotionEffect;
import team.ApiPlus.API.Effect.Default.SpawnEffect;
import team.ApiPlus.Manager.EffectManager;

/** Used to better switch between different Effects
 * @author Atlan1
 *
 */
public enum EffectType{

	EXPLOSION(ExplosionEffect.class),
	LIGHTNING(LightningEffect.class),
	POTION(PotionEffect.class),
	MOVE(MoveEffect.class),
	PLACE(PlaceEffect.class),
	BREAK(BreakEffect.class),
	SPAWN(SpawnEffect.class),
	BURN(BurnEffect.class),
	PARTICLE(ParticleEffect.class),
	CUSTOM();
	
	private Class<? extends Effect> clazz  = null;
	
	private EffectType(Class<? extends Effect> c){
		clazz = c;
	}
	
	private EffectType(){}
	
	public Class<? extends Effect> getEffectClass(){
		return clazz;
	}
	
	public String getEffectName(){
		return EffectManager.getInstance().getEffectName(getEffectClass());
	}
	
	public static EffectType getType(Class<? extends Effect> c){
		for(EffectType t : values())
			if(t.getEffectClass().equals(c))
				return t;
		return CUSTOM;
	}
	
}