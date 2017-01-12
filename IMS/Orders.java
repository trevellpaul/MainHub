package IMS;
import java.util.ArrayList;
import java.util.List;

public class Orders {
	private int orderID; //the unique identifying number of each order
	private String customerName;
	private ArrayList<Items> orderItems; //the list of objects that are in an order
	private orderStatus currentStatus;

	public Orders(int ID, String name){
		this.orderID = ID;
		customerName = name;
		orderItems = new ArrayList<Items>();
		currentStatus = orderStatus.PENDING;
	}

	public void statusToPending(){
		currentStatus = orderStatus.PENDING;
	}
	public void statusToOutOfStock(){
		currentStatus = orderStatus.OUTOFSTOCK;
	}
	public void statusToFulfilling(){
		currentStatus = orderStatus.FULFILLING;
	}
	public void statusToDispatched(){
		currentStatus = orderStatus.DISPATCHED;
	}
	public void statusToComplete(){
		currentStatus = orderStatus.COMPLETE;
	}

	public orderStatus getStatus(){
		return currentStatus;
	}

	public int getOrderSize() {
		return orderItems.size();
	}

	public void addItem(Items newItem){
		orderItems.add(newItem);
	}

	public void removeItemFromOrder(int itemID){
		for(int i = 0; i<orderItems.size(); i++){
			if(orderItems.get(i).GetItemID()== itemID){

				orderItems.remove(i);
			}
		}
	}

	public int getOrderID() {
		return orderID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Items getAnOrderItem(int itemPosToCheck){
		return orderItems.get(itemPosToCheck);
	}

	public void printOrder(){
		//for each item in this order...
		for (int i = 0; i<orderItems.size(); i++){
			System.out.println(orderItems.get(i).printAllInfo());
		}
	}

	public ArrayList<Items> getOrderItems() {
		return orderItems;
	}
}