package game.engine.titans;

import game.engine.interfaces.Attackee;
import game.engine.interfaces.Attacker;
import game.engine.interfaces.Mobil;

public abstract class Titan implements Mobil, Attacker, Attackee,Comparable<Titan>{
private final int baseHealth;
private int currentHealth; 
private final int baseDamage;
private final int heightInMeters;
private int distanceFromBase;
private int speed;
private final int resourcesValue;
private final int dangerLevel;

public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed, int resourcesValue, int dangerLevel){
	this.baseHealth=baseHealth;
	this.currentHealth=baseHealth;
	this.baseDamage=baseDamage;
	this.heightInMeters=heightInMeters;
	this.distanceFromBase=distanceFromBase;
	this.speed=speed;
	this.resourcesValue=resourcesValue;
	this.dangerLevel=dangerLevel;
}


	

public int getBaseHealth(){
	return baseHealth;
}

public int getHeightInMeters(){
	return heightInMeters;
}
public int getDangerLevel(){
	return dangerLevel;
}

@Override
public int getResourcesValue(){
	return resourcesValue;
}

public void setCurrentHealth(int health){
	if (health<0){
		this.currentHealth = 0;
	}
	else{
		this.currentHealth=health;
	}
}
public int getCurrentHealth(){
	return currentHealth;
}
public int getDistance(){
	return distanceFromBase;
}
public void setDistance(int distance){
	if (distance<0){
		this.distanceFromBase=0;
	}
	else{
		this.distanceFromBase=distance;
	}
}
public int getSpeed(){
	return speed;
}
public void setSpeed(int speed){
	this.speed=speed;
}


public int compareTo(Titan o){
	return this.distanceFromBase - o.distanceFromBase; 
}

@Override
public int getDamage(){
	return baseDamage;
}
}
