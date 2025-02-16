package game.engine.weapons;

public class VolleySpreadCannon extends Weapon {
	public final static int WEAPON_CODE=3;
	private final int minRange;
	private final int maxRange;
	public int getMinRange() {
		return minRange;
	}
	public int getMaxRange() {
		return maxRange;
	}
public VolleySpreadCannon(int baseDamage, int minRange, int maxRange){
	super(baseDamage);
	this.minRange = minRange;
	this.maxRange = maxRange;
	
	
}
@Override
public int getDamage() {
	// TODO Auto-generated method stub
	return 0;
}

}
