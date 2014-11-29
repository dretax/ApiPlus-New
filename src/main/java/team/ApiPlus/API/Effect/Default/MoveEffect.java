package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.EntityEffect;
import team.ApiPlus.Util.Utils;

/**
 * @author Atlan1
 * @version 1.0
 */
public class MoveEffect implements EntityEffect{

	private EffectTarget et;
	
	private float speed = 0f;
	private String direction;
	
	public MoveEffect(Object...args) {
		this.speed = ((Number) args[0]).floatValue();
		this.direction = (String) args[1];
	}
	
	@Override
	public void performEffect(Object... arguments) {
		Entity e = (Entity) arguments[0];
		Location l = (Location) arguments[1];
		if(direction.equalsIgnoreCase("push")){
			move(l.getDirection(), e, new Vector(speed, speed, speed), true);
		}else if(direction.equalsIgnoreCase("draw")){
			move(l.getDirection(), e, new Vector(speed*-1, speed*-1, speed*-1), true);
		}else if(direction.equalsIgnoreCase("launch")){
			move(e.getVelocity(), e, new Vector(0, speed, 0), false);
		}else if(direction.equalsIgnoreCase("random")){
			int x = Utils.getRandomInteger(-1, 1);
			int y = Utils.getRandomInteger(-1, 1);
			int z = Utils.getRandomInteger(-1, 1);
			move(e.getVelocity(), e, new Vector(x*speed,y*speed, z*speed), true);
		}
	}
	
	private static void move(Vector dir, Entity e, Vector factor, boolean multi){
		if(multi)
			dir.multiply(factor);
		else
			dir.add(factor);
		e.setVelocity(dir);
	}
	
	
//	private static void setMove(final Vector dir, Entity e, float speed, boolean x,boolean y ,boolean z){
//		Vector direction = dir.clone();
//		if(x)direction.setX(speed);
//		if(y)direction.setY(speed);
//		if(z)direction.setZ(speed);
//		e.setVelocity(dir);
//	}

	@Override
	public EffectTarget getEffectTarget() {
		return et;
	}

	@Override
	public void setEffectTarget(EffectTarget et) {
		this.et = et;
	}
}
