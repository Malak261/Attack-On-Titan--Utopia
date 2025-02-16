package game.engine.weapons;

public class WeaponRegistry {
private final int code;
private final int price;
private final String name;
private final int damage;
private final int minRange;
private final int maxRange;
public int getCode() {
	return code;
}
public int getPrice() {
	return price;
}
public String getName() {
	return name;
}
public int getDamage() {
	return damage;
}
public int getMinRange() {
	return minRange;
}
public int getMaxRange() {
	return maxRange;
}

public WeaponRegistry(int code, int price){
	this.code = code;
	this.price = price;
	this.name = "";
	this.damage = 0;
	this.minRange =0;
	this.maxRange= 0;
}

public WeaponRegistry(int code, int price, int damage, String name){
	this.code = code;
	this.price = price;
	this.name = name;
	this.damage = damage;
	this.minRange=0;
	this.maxRange= 0;
}

public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange){
	this.code = code;
	this.price = price;
	this.name = name;
	this.damage = damage;
	this.minRange= minRange;
	this.maxRange= maxRange;
}
}
