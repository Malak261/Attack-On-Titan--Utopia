package game.engine.dataloader;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataLoader {
private static final String TITANS_FILE_NAME = "titans.csv";
private static final String WEAPONS_FILE_NAME = "weapons.csv";


public String getTitans_File_Name(){
	return TITANS_FILE_NAME;
}
public String getWeapons_File_Name(){
	return WEAPONS_FILE_NAME;
}


public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException{
	 BufferedReader br = new BufferedReader(new FileReader(TITANS_FILE_NAME));
    String line;
    HashMap<Integer, TitanRegistry> hash=new HashMap<Integer, TitanRegistry>();
    while ((line = br.readLine()) != null) {
        String[] fields = line.split(",");
        int code=Integer.parseInt(fields[0]);
        int baseHealth=Integer.parseInt(fields[1]);
        int baseDamage=Integer.parseInt(fields[2]);
        int heightInMeters=Integer.parseInt(fields[3]);
        int speed=Integer.parseInt(fields[4]);
        int resourcesValue=Integer.parseInt(fields[5]);
        int dangerLevel=Integer.parseInt(fields[6]);
        TitanRegistry object= new TitanRegistry(code,baseHealth,baseDamage,heightInMeters,speed,resourcesValue,dangerLevel);
        hash.put(code,object);
    }
	return hash;
}


public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException{
	BufferedReader b = new BufferedReader(new FileReader(WEAPONS_FILE_NAME)) ;
    String line;
    HashMap<Integer, WeaponRegistry> h=new HashMap<Integer, WeaponRegistry>();
    while ((line = b.readLine()) != null) {
        String[] fields = line.split(",");
        if(fields.length ==4){
        	int code=Integer.parseInt(fields[0]);
        	int price=Integer.parseInt(fields[1]);
        	int damage=Integer.parseInt(fields[2]);
        	String name=fields[3];
        	WeaponRegistry ob=new WeaponRegistry(code,price,damage,name);
        	h.put(code, ob);
        }
        else if(fields.length==6){
        	int code=Integer.parseInt(fields[0]);
        	int price=Integer.parseInt(fields[1]);
        	int damage=Integer.parseInt(fields[2]);
        	String name=fields[3];
        	int minRange=Integer.parseInt(fields[4]);
        	int maxRange=Integer.parseInt(fields[5]);
        	WeaponRegistry ob=new WeaponRegistry(code,price,damage,name,minRange,maxRange);
        	h.put(code, ob);
    }
	
    }
    return h;
}

}


