package team.ApiPlus.API.Effect.Default;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.EntityEffect;

/**
 * @author Atlan1
 * @version 1.0
 */
public class PotionEffect implements EntityEffect{

	private EffectTarget et;
	private int id = 0;
	private int duration = 0;
	private int strength = 0;
	
	
	public PotionEffect(Object...args) {
		this.id = (Integer) args[0];
		this.duration = (Integer) args[1];
		this.strength = (Integer) args[2];
	}

	@Override
	public void performEffect(Object... arguments) {
		LivingEntity le = (LivingEntity)arguments[0];
		org.bukkit.potion.PotionEffect pe = new org.bukkit.potion.PotionEffect(PotionEffectType.getById(id), duration, strength);
		le.addPotionEffect(pe);
	}
	
	@Override
	public EffectTarget getEffectTarget() {
		return et;
	}

	@Override
	public void setEffectTarget(EffectTarget et) {
		this.et = et;
	}
	
}