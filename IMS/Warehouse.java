package IMS;
import java.util.ArrayList;


public class Warehouse {
	//stores every item currently in the warehouse
	private ArrayList<Items> inventory;

	public Warehouse(){
		inventory = new ArrayList<Items>();
	}

	//gnomes
	public void AddItem(int ID, boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, String newColour){
		Gnomes gnome = new Gnomes(ID, needPorusware, hadPorusware, itemLocation, name, newColour);
		inventory.add(gnome);
	}

	//furniture
	public void AddItem(int ID, boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, int newSize){
		Furniture furniture = new Furniture(ID, needPorusware, hadPorusware, itemLocation, name, newSize);
		inventory.add(furniture);
	}

	//powertools
	public void AddItem(int ID, boolean needPorusware, boolean hadPorusware, String name, int newPower, int[] itemLocation){
		Powertools powertools = new Powertools(ID, needPorusware, hadPorusware, itemLocation, name, newPower);
		inventory.add(powertools);
	}

	public void AddItemWhole(Items item){
		inventory.add(item);
	}
	
	public boolean RemoveItem(int itemID){
		int i = 0;

		while (i<inventory.size()){
			if (inventory.get(i).GetItemID() == itemID){
				inventory.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}

	public int[] FindLocation (int itemID){
		int[] i = new int[2];
		i[0] = 1;
		i[1] = 2;
		return i;
	}

	public ArrayList<Items> getInventory() {
		return inventory;
	}

	public int getInventorySize() {
		return inventory.size();
	}

	public void setInventory(ArrayList<Items> inventory) {
		this.inventory = inventory;
	}

	public Items FindItem(int itemLoc){
		return inventory.get(itemLoc);
	}
	
	public int getItemLocation(int itemID){
		int i = 0;
		int toReturn = -1;

		while (i<inventory.size()){
			if (inventory.get(i).GetItemID() == itemID){
				toReturn = i;
				return toReturn;
				//break;
			}
			i++;
		}
		return toReturn;
	}
	//The travelling salesman problem code here:
}