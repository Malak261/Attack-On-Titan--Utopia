package game.engine.base;
import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
public class Wall implements Attackee {
private final int baseHealth;
private int currentHealth;

public Wall(int baseHealth) {
	this.baseHealth = baseHealth;
	this.currentHealth= baseHealth;
}
public void setCurrentHealth(int currentHealth) {
	if (currentHealth < 0) {
        this.currentHealth = 0; 
    } 
	else {
        this.currentHealth = currentHealth;}
}
public int getBaseHealth() {
	return baseHealth;
}
public int getCurrentHealth() {
	return currentHealth;
}
@Override
public int getResourcesValue() {
	// TODO Auto-generated method stub
	return 0;
}
}
