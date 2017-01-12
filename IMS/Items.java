package IMS;

public abstract class Items {
	private int itemID;
	private boolean requiresPorousware;
	private boolean hasHadPorousware;
	private int[] location;
	private String itemName;
	
	public Items(int ID, boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name){
	itemID = ID;
	requiresPorousware = needPorusware;
	hasHadPorousware = hadPorusware;
	location = itemLocation;
	itemName = name;
	}
		
	public int GetItemID() {
		return itemID;
	}

	public void SetItemID(int itemID) {
		this.itemID = itemID;
	}

	public boolean IsRequiresPorousware() {
		return requiresPorousware;
	}

	public void SetRequiresPorousware(boolean requiresPorousware) {
		this.requiresPorousware = requiresPorousware;
	}

	public boolean IsHasHadPorousware() {
		return hasHadPorousware;
	}

	public void SetHasHadPorousware(boolean hasHadPorousware) {
		this.hasHadPorousware = hasHadPorousware;
	}

	public int[] GetLocation() {
		return location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}
	
	public String GetName(){
		return itemName;
	}
	
	public String printAllInfo(){
		return "\nItemID: "+itemID +"\nItem Name: "+itemName+ "\nRequires Porousware: "+requiresPorousware+"\nhasHadPorousware: "+hasHadPorousware+"\nLocation: "+location[0]+","+location[1];
	}
}