package game.engine;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.WeaponFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Battle {
	
	
private final static int[][] PHASES_APPROACHING_TITANS = {{1,1,1,2,1,3,4},{2,2,2,1,3,3,4},{4,4,4,4,4,4,4}};
private final int WALL_BASE_HEALTH=1000;

private int numberOfTurns;
private int resourcesGathered;
private BattlePhase battlePhase;
private int numberOfTitansPerTurn=1;
private int score;
private int titanSpawnDistance;

private final WeaponFactory weaponFactory;
private final  HashMap<Integer, TitanRegistry> titansArchives;
private final ArrayList<Titan> approachingTitans;
private final PriorityQueue<Lane> lanes;
private final  ArrayList<Lane> originalLanes;

public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane) throws IOException{
	this.numberOfTurns=numberOfTurns;
	this.score=score;
	this.titanSpawnDistance=titanSpawnDistance;
	this.resourcesGathered=initialResourcesPerLane*initialNumOfLanes;
	BattlePhase battlePhase = BattlePhase.EARLY;
	
	weaponFactory=new WeaponFactory();
	titansArchives=DataLoader.readTitanRegistry();
	approachingTitans=new ArrayList<Titan>();
	
	lanes=new PriorityQueue<Lane>();
	originalLanes=new ArrayList<Lane>();
	initializeLanes(initialNumOfLanes);
	
	
}

public PriorityQueue<Lane> getLanes(){
	return lanes;
}
public ArrayList<Titan> getApproachingTitans() {
	return approachingTitans;
}


public ArrayList<Lane> getOriginalLanes() {
	return originalLanes;
}
public int[][] getPhases_Approaching_Titans(){
	return PHASES_APPROACHING_TITANS;
}
public int getWall_Base_Health(){
	return WALL_BASE_HEALTH;
}
public int getNumberOfTurns(){
	return numberOfTurns;
}
public void setNumberOfTurns(int value){
	numberOfTurns=value;
}
public int getResourcesGathered(){
	return resourcesGathered;
}
public void setResourcesGathered(int value){
	resourcesGathered=value;
}
public BattlePhase getBattlePhase(){
	return battlePhase;
}
public void setBattlePhase(BattlePhase value){
	battlePhase=value;
}
public int getNumberOfTitansPerTurn(){
	return numberOfTitansPerTurn;
}
public void setNumberOfTitansPerTurn(int value){
	numberOfTitansPerTurn=value;
}
public int getScore(){
	return score;
}
public void setScore(int value){
	score=value;
}
public int getTitanSpawnDistance(){
	return titanSpawnDistance;
}
public void setTitanSpawnDistance(int value){
	titanSpawnDistance=value;
}
private void initializeLanes(int numOfLanes){
	 for(int i=0;i<numOfLanes;i++){
		 Wall w=new Wall(WALL_BASE_HEALTH);
		 Lane l=new Lane(w);
		 originalLanes.add(l);
		 lanes.add(l);
	 }
	
	
}
}
