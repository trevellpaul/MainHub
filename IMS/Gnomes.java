package IMS;


public class Gnomes extends Items implements IsFragile {

	private String colour;
	
	public Gnomes(int ID, boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, String newColour){
		super(ID, needPorusware,  hadPorusware, itemLocation, name);
		SetColour(newColour);
	}
	
	public void SpecialStorage(){
		System.out.println("Must be upright, fragile");
	}
	
	//bubblewrap, padding
	public void HowIsHandled(){
		System.out.println("Deliver with care, package with bubble wrap");
	}
	
	public String GetColour(){
		return colour;
	}
	
	public void SetColour(String newColour){
		colour = newColour;
	}
}
