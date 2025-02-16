package game.engine.weapons.factory;
import game.engine.weapons.WeaponRegistry;
import java.util.*;
import game.engine.dataloader.DataLoader;
import java.io.IOException;
public class WeaponFactory {
private final HashMap<Integer, WeaponRegistry> weaponShop;

public WeaponFactory() throws IOException{
	this.weaponShop = DataLoader.readWeaponRegistry();
	
	}

public HashMap<Integer, WeaponRegistry> getWeaponShop() {
	return weaponShop;
}
}
