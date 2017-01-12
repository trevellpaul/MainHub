package IMS;


public class Furniture extends Items {
	int itemSize;
	
	public Furniture(int ID, boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, int newSize){
		super(ID, needPorusware,  hadPorusware, itemLocation, name);
		SetSize(newSize);
		
	}
	
	public int ReturnSize(){
		return itemSize;
	}
	
	public void SetSize(int newSize){
		itemSize = newSize;
	}
}
