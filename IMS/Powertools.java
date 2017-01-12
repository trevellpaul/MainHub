package IMS;


public class Powertools extends Items implements IsFragile{
	int power;
	
	public Powertools(int ID, boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, int newPower){
		super(ID, needPorusware,  hadPorusware, itemLocation, name);
		setPower(newPower);
	}
	
	public void SpecialStorage(){
		System.out.println("The tools must be stored in a cool place.");
	}
	
	//bubblewrap, padding
	public void HowIsHandled(){
		System.out.println("Store with care using padding.");
	}
	
	public int getPower(){
		return power;
	}
	
	public void setPower(int newPower){
		power = newPower;
	}
}