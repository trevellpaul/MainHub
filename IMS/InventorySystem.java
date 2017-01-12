package IMS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventorySystem {
	private List<Orders> orderList;
	private int numOfOrders;
	private int numOfItems;
	private Warehouse warehouse;
	//only used in updateWarehouse
	private Warehouse tempHouse;

	//define warehouse
	public InventorySystem(){
		orderList = new ArrayList<Orders>();
		warehouse = new Warehouse();
		tempHouse = new Warehouse();
		numOfOrders = 0;
		numOfItems = 0;
		populate();
	}

	private void populate(){
		int[] tempA = new int[2];
		//add items to warehouse
		tempA[0] = 1;
		tempA[1] = 2;
		addWarehouseGnome(true, false, (tempA), "Harry potter gnome", "Blue");

		int[] tempB = new int[2];
		tempB[0] = 2;
		tempB[1] = 3;
		addWarehouseGnome(true, true, (tempB), "Draco Malfoy gnome", "Green");

		int[] tempC = new int[2];
		tempC[0] = 4;
		tempC[1] = 5;
		addWarehousePowertool(true, false,  "Hyper Drill 2000", 4, (tempC));

		int[] tempD = new int[2];
		tempD[0] = 9;
		tempD[1] = 7;
		addWarehousePowertool(false, false, "Super Drill 1000", 8, (tempD));

		int[] tempE = new int[2];
		tempE[0] = 3;
		tempE[1] = 10;
		addWarehouseFurniture(true, true, (tempE), "Armchair", 9);

		int[] tempF = new int[2];
		tempF[0] = 10;
		tempF[1] = 10;
		addWarehouseFurniture(true, false, (tempF), "Garden Table", 7);

		int[] tempG = new int[2];
		tempG[0] = 7;
		tempG[1] = 7;
		addWarehousePowertool(false, false, "Chainsaw 3000", 50, (tempG));

		int[] tempH = new int[2];
		tempH[0] = 1;
		tempH[1] = 9;
		addWarehouseFurniture(false, false, (tempH), "Rocking Chair", 2);

		int[] tempI = new int[2];
		tempI[0] = 6;
		tempI[1] = 5;
		addWarehouseGnome(true, true, (tempI), "Darth Vader Gnome", "Black");

		int[] tempJ = new int[2];
		tempJ[0] = 8;
		tempJ[1] = 8;
		addWarehousePowertool(false, false, "Electric Screwdriver", 1, (tempJ));
		//add orders to OrderList
		addOrderToIMS("Simon");
		addOrderToIMS("Trev");
		addOrderToIMS("Gareth");
		//add items to OrderList
		addOrderItem(1,6);
		addOrderItem(1,8);
		addOrderItem(1,1);
		addOrderItem(1,3);
		addOrderItem(1,4);
	//	addOrderItem(1,7);
		addOrderItem(0,9);
	//	addOrderItem(2,2);
		addOrderItem(2,0);
	}

	//called by employee to update the status of the order
	public void checkOutOrder(int orderID){
		Warehouse tempHouse = warehouse;
		// check if the order is completable
		if(isOrderInStock(orderID) == true){
			//remove the items from the warehouse
			//loop through the order
			//for each item in the order, remove 1 stock from warehouse
			orderList.get(getOrderLoc(orderID)).statusToFulfilling();
			warehouse = tempHouse;

		}
	}
	//Checks if the order is completable
	private boolean isOrderInStock(int orderID){
		tempHouse = warehouse;
		int itemLoc = getOrderLoc(orderID);
		boolean toReturn = true;
		//now we have access to the order which has all items
		ArrayList<Items> orderItemsList = orderList.get(itemLoc).getOrderItems();
		//check each item in the order to see if it's in stock
		for(int j = 0; j<orderItemsList.size(); j++){
			//itemID number
			int itemIDno = orderItemsList.get(j).GetItemID();
			int itemLoc1 = tempHouse.getItemLocation(itemIDno);
			//if the itemlocation has a valid location...
			if (itemLoc1 != -1){
				tempHouse.RemoveItem(itemLoc1);
			}
			else{
				//set the stock to out of stock
				orderList.get(j).statusToOutOfStock();
			}
		}


		return toReturn;
	}

	//go via warehouse class
	public int addWarehouseGnome(boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, String newColour){
		warehouse.AddItem(numOfItems, needPorusware, hadPorusware, itemLocation, name, newColour);
		numOfItems++;
		return numOfItems-1;
	}
	public int addWarehouseFurniture(boolean needPorusware, boolean hadPorusware, int[] itemLocation, String name, int newSize){
		warehouse.AddItem(numOfItems, needPorusware, hadPorusware, itemLocation, name, newSize);
		numOfItems++;
		return numOfItems-1;
	}
	public int addWarehousePowertool(boolean needPorusware, boolean hadPorusware, String name, int newPower, int[] itemLocation){
		warehouse.AddItem(numOfItems, needPorusware, hadPorusware, name, newPower, itemLocation);
		numOfItems++;
		return numOfItems-1;
	}
	public void addWarehouseWhole(Items item){
		warehouse.AddItemWhole(item);
	}
	public boolean removeWarehouseItem(int itemID){
		int location = warehouse.getItemLocation(itemID);
		boolean result = warehouse.RemoveItem(location);
		return result;
	}

	//go via warehouse class
	public void addOrderToIMS(String customerName){
		Orders tempOrder = new Orders(numOfOrders, customerName);
		orderList.add(tempOrder);
		numOfOrders++;
	}

	public void removeOrderFromIMS(int orderID){
		int i = 0;

		while (i<orderList.size()){
			if (orderList.get(i).getOrderID() ==orderID){
				orderList.remove(i);
			}
			i++;
		}
	}

	//adds an item to a particular order
	public boolean addOrderItem(int orderID, int itemID){
		int posInOrderArray = getOrderLoc(orderID);
		int itemLoc = warehouse.getItemLocation(itemID);
		if (itemLoc != -1){
			orderList.get(posInOrderArray).addItem(warehouse.FindItem(itemLoc));
			warehouse.RemoveItem(itemLoc);
			return true;
		}
		else{
			return false;
		}
	}

	//go via warehouse class
	public boolean removeOrderItem(int orderID, int itemID){
		int posInOrderArray = getOrderLoc(orderID);

		int j = 0;
		int posInItemArray = -1;
		//for each item in their order...
		while (j<orderList.get(posInOrderArray).getOrderSize()){
			if (orderList.get(posInOrderArray).getAnOrderItem(j).GetItemID() == itemID){
				posInItemArray = j;
			}
			j++;
		}

		if (posInOrderArray != -1 && posInItemArray != -1){
			removeWarehouseItem(itemID);
			orderList.get(posInOrderArray).removeItemFromOrder(posInItemArray);
			return true;
		}
		return false;
	}

	public int[] findPhysicalLocation(int itemID){
		return warehouse.FindLocation(itemID);
	}

	public int orderListSize(){
		return orderList.size();
	}

	public void updateAccounting(){
		System.out.println("Messaged accounting with the order information");
	}	

	//used to find an order in the arraylist of orders
	//(it might not be in the same position in the array is it's ordernumber)
	public int getOrderLoc(int orderID){
		int i = 0;
		int toReturn = -1;

		while (i<orderList.size()){
			if (orderList.get(i).getOrderID() == orderID){
				toReturn = i;
			}
			i++;
		}
		return toReturn;
	}

	//below methods are used by the GUI to work with user inputs
	public void printAllOrders(){
		for (int i = 0; i<orderList.size(); i++){
			System.out.println("\n Order Number: "+orderList.get(i).getOrderID()+" for: "+orderList.get(i).getCustomerName());
			orderList.get(i).printOrder();
		}
	}

	public String printAllOrdersGUI(){
		String toReturn = "";
		for (int i = 0; i<orderList.size(); i++){
			toReturn = toReturn+"\n Order Number: "+orderList.get(i).getOrderID()+" for: "+orderList.get(i).getCustomerName();
		}
		return toReturn;
	}

	public void printAllOrdersStatus(){
		for (int i = 0; i<orderList.size(); i++){
			System.out.println("Order number: "+orderList.get(i).getOrderID()+", and the status is: "+orderList.get(i).getStatus());
		}
	}

	public boolean printAnOrder(int orderID){
		if (getOrderLoc(orderID) != -1){
			orderList.get(getOrderLoc(orderID)).printOrder();
			return true;
		}
		return false;
	}
	public Orders printAnOrderGUI(int orderID){
		if (getOrderLoc(orderID) != -1){
			return orderList.get(getOrderLoc(orderID));
		}
		return null;
	}

	public boolean updateAnOrderAdd(int orderID){
		if (getOrderLoc(orderID) != -1){
			orderList.get(getOrderLoc(orderID));
			return true;
		}
		return false;
	}

	public void travellingSalesman(int orderID){
		int actualPos = getOrderLoc(orderID);
		Orders orderToSolve = orderList.get(actualPos);

		List<int[]> SortedCo = new ArrayList<>();

		for(int i = 0; i<orderToSolve.getOrderSize(); i++){
			SortedCo.add(orderToSolve.getAnOrderItem(i).GetLocation());
		}

		ArrayList<int[]> UsedCo = new ArrayList<>();
		UsedCo.add(orderToSolve.getAnOrderItem(0).GetLocation());
		
		List<int[]> MirroredCo = SortedCo;

		//Start at the 0th node
		for(int i = 0; i<orderToSolve.getOrderSize()-1; i++){
		
			double temp1= 1000000000;

			for(int j = 0; j<orderToSolve.getOrderSize()-1; j++){
				//find out if we have already done this point and if so skip it
				if(check(UsedCo, MirroredCo.get(j))== true){
					continue;
				}
				//work out the distance between this point and each point
				double z = distance(MirroredCo.get(i), MirroredCo.get(j));
				if (z <=temp1 && z != 0){
					temp1 = z;
					Collections.swap(SortedCo, i+1, j);
				}
			}
			UsedCo.add(MirroredCo.get(i));
		}
		
		System.out.println("Go to these co-ords in this order...");
		for(int i = 0; i<SortedCo.size(); i++){
			System.out.println("x: "+SortedCo.get(i)[0]+ "    y: " + SortedCo.get(i)[1]);
		}
	}

	private double distance(int [] point1, int [] point2){
		double distance;
		distance = Math.sqrt(Math.pow((point1[0]- point2[0]),2)+Math.pow((point1[1] - point2[1]),2)); 
		return distance;
	}

	private boolean check(List<int []> listofpoints, int [] point){
		boolean result = false;
		for(int i = 0; i< listofpoints.size(); i++){
		if(listofpoints.get(i)==point){
			result = true;
		}
		
	}
		return result;
	}
	
	public void statusToPending(int orderID){
		int actualPos = getOrderLoc(orderID);
		orderList.get(actualPos).statusToPending();
	}

	public void statusToOutOfStock(int orderID){
		int actualPos = getOrderLoc(orderID);
		orderList.get(actualPos).statusToOutOfStock();;
	}

	public void statusToFulfilling(int orderID){
		int actualPos = getOrderLoc(orderID);
		orderList.get(actualPos).statusToFulfilling();
	}

	public void statusToDispatched(int orderID){
		int actualPos = getOrderLoc(orderID);
		orderList.get(actualPos).statusToDispatched();
	}

	public void statusToComplete(int orderID){
		int actualPos = getOrderLoc(orderID);
		orderList.get(actualPos).statusToComplete();
	}
}