package IMS;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserInput extends JFrame{
	boolean guiEnabled;
	boolean runSystem;
	InventorySystem system;
	Scanner scanner;

	public UserInput (boolean runGUI){
		scanner = new Scanner(System.in);
		system = new InventorySystem();
		guiEnabled = runGUI;
		runSystem = true;
	}

	public void runGUI(){
		// Set some default values
		setTitle("NB Gardens IMS System");
		setSize(220, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set the container and layout
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());

		// Add a panel
		// Want to add components to a panel rather than a frame - for reuse
		JPanel panel1 = new JPanel();
		container.add(panel1);

		// Add a button to the panel
		JButton viewAllOrdersButton = new JButton("View All Orders");
		JButton viewAnOrderButton = new JButton("View An Order");
		JButton addAnOrderButton = new JButton("Add An Order");
		JButton addGnomeButton = new JButton("Add Gnome");
		JButton addFurnitureButton = new JButton("Add Furniture");
		JButton addPowertoolsButton = new JButton("Add Powertools");
		JButton updateOrderStatusButton = new JButton("Update Order Status");
		JButton addItemToOrderButton = new JButton("Add Item to Order");
		JButton deleteItemFromOrderButton = new JButton("Delete Item From Order");
		JButton deleteItemFromIMSButton = new JButton("Delete Item From IMS");
		JButton travellingSalesmanButton = new JButton("Travelling Salesman on an Order");
		JButton exitButton = new JButton("Exit");

		//listeners

		viewAllOrdersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel1, system.printAllOrdersGUI(), "View All Orders:", JOptionPane.PLAIN_MESSAGE);
			}
		});
		viewAnOrderButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(
						"Enter the Order ID of the order you wish to view:\n"
						);
				try {
					int temp = Integer.parseInt(s);
					int loc = system.getOrderLoc(temp);
					Orders order = system.printAnOrderGUI(loc);
					String itemsFromOrder = "";
					for (int i = 0; i<order.getOrderItems().size(); i++){
						Items tempItem = order.getOrderItems().get(i);
						itemsFromOrder = itemsFromOrder + "\nName: " + tempItem.GetName()+" ID: "+tempItem.GetItemID()+" Requires Porousware: "+tempItem.IsRequiresPorousware()+" Has Had Porousware: "+tempItem.IsHasHadPorousware();
					}

					if (order.getOrderID() == temp){
						JOptionPane.showMessageDialog(panel1, "Order ID: "+order.getOrderID() +"\nCustomer Name: "+ order.getCustomerName() +"\nSize of Order: "+ order.getOrderSize() +"\nOrder Status: "+ order.getStatus() +"\nItems From Order:"+ itemsFromOrder);
					}

				}
				catch(InputMismatchException error1){
					JOptionPane.showMessageDialog(panel1, "\nPlease only input valid order numbers for the order number field.");
				}
			}
		});
		addAnOrderButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = (String)JOptionPane.showInputDialog(
						"Please type the name of the person whose order wish to create:\n"
						);
				try {
					system.addOrderToIMS(result);
					JOptionPane.showMessageDialog(panel1, "The order "+result+" has been successfully added.");
				}
				catch(InputMismatchException error1){
					JOptionPane.showMessageDialog(panel1, "\nPlease only input valid order numbers for the order number field.");
				}
			}
		});
		addGnomeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String)JOptionPane.showInputDialog(
						"Please type the name of the gnome you wish to create:\n"
						);

				Object[] options = {"Yes",
				"No"};
				boolean needsPware = false;
				int doesneedsPware = JOptionPane.showOptionDialog(panel1,
						"Does the item need porousware applied?",
						"Click the correct button.",
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(doesneedsPware == 0){
					needsPware = true;
				}

				boolean hasHadPware = false;
				int hasActuallyHadPware = JOptionPane.showOptionDialog(panel1,
						"Has the item already had porousware applied to it?",
						"Click the correct button.",
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(hasActuallyHadPware == 0){
					hasHadPware = true;
				}

				String colour = (String)JOptionPane.showInputDialog(
						"Please type the colour of the gnome you wish to add:\n"
						);

				String temp2 = (String)JOptionPane.showInputDialog(
						"Please enter the X co-ordinate for the item:\n"
						);

				int xcoord = Integer.parseInt(temp2);

				String temp3 = (String)JOptionPane.showInputDialog(
						"Please enter the Y co-ordinate for the item:\n"
						);

				int ycoord = Integer.parseInt(temp3);

				int[] itemLocation = new int[2];
				itemLocation[0] = xcoord;
				itemLocation[1] = ycoord;
				int id = system.addWarehouseGnome(needsPware, hasHadPware, itemLocation, name, colour);
				JOptionPane.showMessageDialog(panel1, "The gnome has been successfully added to the IMS with an order ID of "+id);
			}
		});

		addFurnitureButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String)JOptionPane.showInputDialog(
						"Please type the name of the furniture you wish to create:\n"
						);
				Object[] options = {"Yes",
				"No"};
				boolean needsPware = false;
				int doesneedsPware = JOptionPane.showOptionDialog(panel1,
						"Does the item need porousware applied?",
						"Click the correct button.",
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(doesneedsPware == 0){
					needsPware = true;
				}

				boolean hasHadPware = false;
				int hasActuallyHadPware = JOptionPane.showOptionDialog(panel1,
						"Has the item already had porousware applied to it?",
						"Click the correct button.",
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(hasActuallyHadPware == 0){
					hasHadPware = true;
				}

				String itemSize = (String)JOptionPane.showInputDialog(
						"Enter the size of the item (in numbers):\n"
						);
				int size = Integer.parseInt(itemSize);

				String temp2 = (String)JOptionPane.showInputDialog(
						"Please enter the X co-ordinate for the item:\n"
						);

				int xcoord = Integer.parseInt(temp2);

				String temp3 = (String)JOptionPane.showInputDialog(
						"Please enter the Y co-ordinate for the item:\n"
						);

				int ycoord = Integer.parseInt(temp3);

				int[] itemLocation = new int[2];
				itemLocation[0] = xcoord;
				itemLocation[1] = ycoord;
				int id = system.addWarehouseFurniture(needsPware, hasHadPware, itemLocation, name, size);
				JOptionPane.showMessageDialog(panel1, "The furniture has been successfully added to the IMS with an order ID of "+id);	
			}
		});

		addPowertoolsButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String)JOptionPane.showInputDialog(
						"Please type the name of the powertool you wish to create:\n"
						);
				Object[] options = {"Yes",
				"No"};
				boolean needsPware = false;
				int doesneedsPware = JOptionPane.showOptionDialog(panel1,
						"Does the item need porousware applied?",
						"Click the correct button.",
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(doesneedsPware == 0){
					needsPware = true;
				}

				boolean hasHadPware = false;
				int hasActuallyHadPware = JOptionPane.showOptionDialog(panel1,
						"Has the item already had porousware applied to it?",
						"Click the correct button.",
						JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
				if(hasActuallyHadPware == 0){
					hasHadPware = true;
				}

				String power = (String)JOptionPane.showInputDialog(
						"Enter the power of the item (in numbers):\n"
						);
				int itemPower = Integer.parseInt(power);

				String temp2 = (String)JOptionPane.showInputDialog(
						"Please enter the X co-ordinate for the item:\n"
						);

				int xcoord = Integer.parseInt(temp2);

				String temp3 = (String)JOptionPane.showInputDialog(
						"Please enter the Y co-ordinate for the item:\n"
						);

				int ycoord = Integer.parseInt(temp3);

				int[] itemLocation = new int[2];
				itemLocation[0] = xcoord;
				itemLocation[1] = ycoord;
				int id = system.addWarehousePowertool(needsPware, hasHadPware, name, itemPower, itemLocation);
				JOptionPane.showMessageDialog(panel1, "The powertool has been successfully added to the IMS with an order ID of "+id);	
			}
		});

		addItemToOrderButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {

				String cID = (String)JOptionPane.showInputDialog(
						"Enter the ID of the person to add to:\n"
						);
				int customerID = Integer.parseInt(cID);

				String iID = (String)JOptionPane.showInputDialog(
						"Enter the ID of the item to add to the person:\n"
						);
				int itemID = Integer.parseInt(iID);
				boolean methodResult = system.addOrderItem(customerID, itemID);
				if (methodResult == true){
					JOptionPane.showMessageDialog(panel1, "\nAn item has successfully been added to the order!");
				}
				else {
					JOptionPane.showMessageDialog(panel1, "The item ID provided either doesn't exist or has already been used!\n"+
							"Therefore, no changes to the system have been made.");
				}
			}
		});

		updateOrderStatusButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(
						"Enter the Order ID of the order you wish to view:\n"
						);
				try {
					int orderID = Integer.parseInt(s);
					int loc = system.getOrderLoc(orderID);
					Orders order = system.printAnOrderGUI(loc);
					String itemsFromOrder = "";
					for (int i = 0; i<order.getOrderItems().size(); i++){
						Items tempItem = order.getOrderItems().get(i);
						itemsFromOrder = itemsFromOrder + "\nName: " + tempItem.GetName()+" ID: "+tempItem.GetItemID()+" Requires Porousware: "+tempItem.IsRequiresPorousware()+" Has Had Porousware: "+tempItem.IsHasHadPorousware();
					}

					if (order.getOrderID() == orderID){


						Object[] possibilities = {"PENDING", "OUTOFSTOCK", "FULFILLING", "DISPATCHED", "COMPLETE"};
						String newState = (String)JOptionPane.showInputDialog(
								panel1,
								"Choose the status for the order:\n",
								"Status Selection",
								JOptionPane.PLAIN_MESSAGE,
								null, possibilities,
								"ham");

						if (newState.equals("PENDING")){
							system.statusToPending(orderID);
							JOptionPane.showMessageDialog(panel1, "\nAn order has been changed to PENDING!");
						}
						else if (newState.equals("OUTOFSTOCK")){
							system.statusToOutOfStock(orderID);
							JOptionPane.showMessageDialog(panel1, "\nAn order has been changed to OUTOFSTOCK!");
						}
						else if (newState.equals("FULFILLING")){
							system.statusToFulfilling(orderID);
							JOptionPane.showMessageDialog(panel1, "\nAn order has been changed to FULFILLING!");
						}
						else if (newState.equals("DISPATCHED")){
							system.statusToDispatched(orderID);
							JOptionPane.showMessageDialog(panel1, "\nAn order has been changed to DISPATCHED!");
						}
						else if (newState.equals("COMPLETE")){
							system.statusToComplete(orderID);
							JOptionPane.showMessageDialog(panel1, "\nAn order has been changed to COMPLETE!");		
						}
					}
				}
				catch(InputMismatchException error1){
					JOptionPane.showMessageDialog(panel1, "\nPlease only input valid order numbers for the order number field.");
				}
			}
		});

		deleteItemFromOrderButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(
						"Enter the Order ID of the order you wish to view:\n"
						);
				try {
					int orderID = Integer.parseInt(s);
					int loc = system.getOrderLoc(orderID);
					Orders order = system.printAnOrderGUI(loc);
					String itemsFromOrder = "";
					for (int i = 0; i<order.getOrderItems().size(); i++){
						Items tempItem = order.getOrderItems().get(i);
						itemsFromOrder = itemsFromOrder + "\nName: " + tempItem.GetName()+" ID: "+tempItem.GetItemID()+" Requires Porousware: "+tempItem.IsRequiresPorousware()+" Has Had Porousware: "+tempItem.IsHasHadPorousware();
					}

					if (order.getOrderID() == orderID){
						String itemIDtoRemove = (String)JOptionPane.showInputDialog(panel1, "Order ID: "+order.getOrderID() +"\nCustomer Name: "+ order.getCustomerName() +"\nSize of Order: "+ order.getOrderSize() +"\nOrder Status: "+ order.getStatus() +"\nItems From Order:"+ itemsFromOrder+"\n\n Please choose the ItemID of the order you want to remove from this order:");
						int itemID = Integer.parseInt(itemIDtoRemove);
						boolean result = system.removeOrderItem(orderID, itemID);
						if (result == true){
							JOptionPane.showMessageDialog(panel1, "\nAn item has successfully been removed from the order!");
						}
						else {
							JOptionPane.showMessageDialog(panel1, "The ID's provided either doesn't exist or has already been used!\n"+
									"Therefore, no changes to the system have been made.");
						}
					}

				}
				catch(InputMismatchException error1){
					JOptionPane.showMessageDialog(panel1, "\nPlease only input valid order numbers for the order number field.");
				}
			}
		});

		deleteItemFromIMSButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {

				String itemIDtoRemove = (String)JOptionPane.showInputDialog(panel1, "Please choose the ItemID of the order you want to remove from the IMS:");
				int itemID = Integer.parseInt(itemIDtoRemove);
				boolean result = system.removeWarehouseItem(itemID);
				if (result == true){
					JOptionPane.showMessageDialog(panel1, "\nAn item has successfully been removed from the IMS!");
				}
				else {
					JOptionPane.showMessageDialog(panel1, "The item ID provided is invalid.!\n"+
							"Therefore, no changes to the system have been made.");
				}
			}
		});

		travellingSalesmanButton.addActionListener(new ActionListener() {
			//get order ID by pop-up
			@Override
			public void actionPerformed(ActionEvent e) {

				String s = (String)JOptionPane.showInputDialog(
						"Choose the order to do a travelling salesman on:\n"
						);
				try {
					int orderID = Integer.parseInt(s);
					int loc = system.getOrderLoc(orderID);
					Orders order = system.printAnOrderGUI(loc);
					String itemsFromOrder = "";
					for (int i = 0; i<order.getOrderItems().size(); i++){
						Items tempItem = order.getOrderItems().get(i);
						itemsFromOrder = itemsFromOrder + "\nName: " + tempItem.GetName()+" ID: "+tempItem.GetItemID()+" Requires Porousware: "+tempItem.IsRequiresPorousware()+" Has Had Porousware: "+tempItem.IsHasHadPorousware();
					}

					if (order.getOrderID() == orderID){
						system.travellingSalesman(system.getOrderLoc(orderID));
						JOptionPane.showMessageDialog( panel1,
								"The results have been outputted to the command line."
								);
					}
				}
				catch(InputMismatchException error1){
					JOptionPane.showMessageDialog(panel1, "\nPlease only input valid order numbers for the order number field.");
				}


			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runSystem = false;
				System.out.println("Thank you for using the IMS!");
				System.exit(0);
			}
		});	

		panel1.add(viewAllOrdersButton);
		panel1.add(viewAnOrderButton);
		panel1.add(addAnOrderButton);
		panel1.add(addGnomeButton);
		panel1.add(addFurnitureButton);
		panel1.add(addPowertoolsButton);
		panel1.add(addItemToOrderButton);
		panel1.add(updateOrderStatusButton);
		panel1.add(deleteItemFromOrderButton);
		panel1.add(deleteItemFromIMSButton);
		panel1.add(travellingSalesmanButton);
		panel1.add(exitButton);
	}

	public void runCommandLine(){
		//main program loop below;
		while (true){

			System.out.println("\nInput your next command:");
			String input = scanner.nextLine();

			if(input.equals("help")){
				help();
			}

			else if(input.equals("addAnOrder")){
				addAnOrder();	
			}

			else if(input.equals("deleteAnOrder")){
				deleteAnOrder();
			}

			else if(input.equals("viewAllOrders")){
				system.printAllOrders();
			}

			else if(input.equals("viewAnOrder")){
				viewAnOrder();
			}

			else if(input.equals("addGnomeToIMS")){
				addGnomeToIMS();
			}

			else if(input.equals("addFurnitureToIMS")){
				addFurnitureToIMS();
			}

			else if(input.equals("addPowertoolToIMS")){
				addPowertoolsToIMS();
			}

			else if(input.equals("addItemToOrder")){
				addItemToOrder();
			}

			else if(input.equals("viewOrderStatus")){
				system.printAllOrdersStatus();
			}

			else if(input.equals("updateOrderStatus")){
				updateOrderStatus();
			}
			//System.out.println("'deleteItemFromOrder' = removes an item from an order.");
			else if(input.equals("deleteItemFromOrder")){
				deleteItemFromOrder();
			}

			//System.out.println("'deleteItemFromIMS' = removes an item from the IMS");
			else if(input.equals("deleteItemFromIMS")){
				deleteItemFromIMS();
			}

			else if(input.equals("travellingSalesman")){
				travellingSalesman();
			}

			else if(input.equals("exit")){
				System.out.println("Thank you for using the IMS.");
				break;
			}
			else{
				System.out.println("Invalid command. Type 'help' for a list of commands.");	
			}
		}
	}

	//methods below are called by the command line method.
	private void deleteAnOrder(){
		System.out.println("\n> Please type the orderID of the order you wish to delete:");
		try {
			int newInput = scanner.nextInt();
			system.removeOrderFromIMS(newInput);
			scanner.nextLine();

		}catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the order number.");
		}
	}

	private void addAnOrder(){
		System.out.println("\n> Please type the name of the person whose order wish to create:");
		String result = scanner.nextLine();
		system.addOrderToIMS(result);
		System.out.println("A new order has been added with a name of "+result+".");

	}

	private void travellingSalesman(){

		System.out.println("\n> Please type the orderNumber of the order you want to travelling salesman on:");
		try {
			int newInput = scanner.nextInt();
			System.out.println("\nBeginning the travelling salesman problem on Order "+newInput+"...");
			if (system.getOrderLoc(newInput) != -1){
				system.travellingSalesman(system.getOrderLoc(newInput));
			}
			else{
				System.out.println("\n Invalid order ID submitted.");
			}
		}
		catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the order number.");
		}
	}

	private void help(){
		System.out.println("'addAnOrder' = Add a new Order to the IMS.");
		System.out.println("'viewAllOrders' = return a list of all orders in the IMS.");
		System.out.println("'viewAnOrder' = find information about an exact order.");
		System.out.println("'deleteAnOrder' = delete an order.");
		System.out.println("'updateOrderStatus' = change the status of an order");
		System.out.println("'viewOrderStatus' = find information about an exact order.");

		System.out.println("\n'addGnomeToIMS' = add a gnome to the IMS.");
		System.out.println("'addFurnitureToIMS' = add furniture to the IMS.");
		System.out.println("'addPowertoolToIMS' = add a powertool to the IMS.");
		System.out.println("'addItemToOrder' = adds an item to an order.");
		System.out.println("'deleteItemFromOrder' = removes an item from an order.");
		System.out.println("'deleteItemFromIMS' = removes an item from the IMS");
		System.out.println("'travellingSalesman' = runs a travelling salesman solution.");
		System.out.println("'exit' = exit from the system.");
	}

	private void viewAnOrder(){
		System.out.println("\n> Please type the orderNumber of the order you wish to view:");
		try {
			int newInput = scanner.nextInt();
			scanner.nextLine();
			boolean result = system.printAnOrder(newInput);
			if (result == false){
				System.out.println("\nInvalid order number inputted.");
			}

		}catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the order number.");
		}
	}

	private void addGnomeToIMS(){
		scanner.nextLine();
		System.out.println("\nInput the item's name:");
		String itemName = scanner.nextLine();
		System.out.println("\nInput 'true/false' if the item needs porusware:");
		Boolean needsPware = scanner.nextBoolean();
		System.out.println("\nInput 'true/false' if the item has had porusware:");
		Boolean hadPware = scanner.nextBoolean();
		System.out.println("\nInput the colour of the item:");
		String itemColour = scanner.nextLine();
		scanner.nextLine();
		System.out.println("\nInput the itemLocation's x co-ordinate");
		int xcoord = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\nInput the itemLocation's y co-ordinate");
		int ycoord = scanner.nextInt();
		scanner.nextLine();
		int[] itemLocation = new int[2];
		itemLocation[0] = xcoord;
		itemLocation[1] = ycoord;
		int id = system.addWarehouseGnome(needsPware, hadPware, itemLocation, itemName, itemColour);
		System.out.println("\nA gnome has successfully been added to the IMS with order number: "+id);
	}

	private void addFurnitureToIMS(){
		scanner.nextLine();
		System.out.println("\nInput the item's name:");
		String itemName = scanner.nextLine();
		System.out.println("\nInput 'true/false' if the item needs porusware:");
		Boolean needsPware = scanner.nextBoolean();
		System.out.println("\nInput 'true/false' if the item has had porusware:");
		Boolean hadPware = scanner.nextBoolean();
		scanner.nextLine();
		System.out.println("\nInput the size of the item:");
		int size = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\nInput the itemLocation's x co-ordinate");
		int xcoord = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\nInput the itemLocation's y co-ordinate");
		int ycoord = scanner.nextInt();
		scanner.nextLine();
		int[] itemLocation = new int[2];
		itemLocation[0] = xcoord;
		itemLocation[1] = ycoord;
		int id = system.addWarehouseFurniture(needsPware, hadPware, itemLocation, itemName, size);
		System.out.println("\nFurniture has successfully been added to the IMS with an order ID of "+id);
	}

	private void addPowertoolsToIMS(){
		scanner.nextLine();
		System.out.println("\nInput the item's name:");
		String itemName = scanner.nextLine();
		System.out.println("\nInput 'true/false' if the item needs porusware:");
		Boolean needsPware = scanner.nextBoolean();
		System.out.println("\nInput 'true/false' if the item has had porusware:");
		Boolean hadPware = scanner.nextBoolean();
		System.out.println("\nInput the power wattage of the item:");
		scanner.nextLine();
		int itemPower = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\nInput the itemLocation's x co-ordinate");
		int xcoord = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\nInput the itemLocation's y co-ordinate");
		int ycoord = scanner.nextInt();
		scanner.nextLine();
		int[] itemLocation = new int[2];
		itemLocation[0] = xcoord;
		itemLocation[1] = ycoord;
		int id = system.addWarehousePowertool(needsPware, hadPware, itemName, itemPower, itemLocation);
		System.out.println("\nA powertool has successfully been added to the IMS with an ID of "+id);
	}

	private void addItemToOrder(){
		System.out.println("\n> Please type the orderNumber of the order you wish to add to:");
		int temp1 = -1;
		try {
			int newInput = scanner.nextInt();

			boolean result = system.updateAnOrderAdd(newInput);
			if (result == false){
				System.out.println("\nInvalid order number inputted.");
			}
			if (result == true){
				scanner.nextLine();
				System.out.println("Put an itemID in to add to an order:");

				temp1 = scanner.nextInt();
				scanner.nextLine();

				if (temp1 != -1 && newInput != -1){
					boolean methodResult = system.addOrderItem(newInput, temp1);
					if (methodResult == true){
						System.out.println("\nAn item has successfully been added to the order!");
					}
					else {
						System.out.println("The item ID provided either doesn't exist or has already been used!");
						System.out.println("Therefore, no changes to the system have been made.");
					}
				}
			}
		}
		catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the order number.");
		}
	}

	private void updateOrderStatus(){

		System.out.println("Please input an order number to update:");
		try {
			int newInput = scanner.nextInt();
			boolean result = system.printAnOrder(newInput);
			if (result == false){
				System.out.println("\nInvalid order number inputted.");
			}
			if (result == true){
				scanner.nextLine();
				System.out.println("\nInput a new state out of:");
				System.out.println("PENDING, OUTOFSTOCK, FULFILLING, DISPATCHED, COMPLETE");
				String newState = scanner.nextLine();
				if (newState.equals("PENDING")){
					system.statusToPending(newInput);
				}
				else if (newState.equals("OUTOFSTOCK")){
					system.statusToOutOfStock(newInput);
				}
				else if (newState.equals("FULFILLING")){
					system.statusToFulfilling(newInput);
				}
				else if (newState.equals("DISPATCHED")){
					system.statusToDispatched(newInput);
				}
				else if (newState.equals("COMPLETE")){
					system.statusToComplete(newInput);
				}
			}
		}catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the order number.");
		}
	}

	private void deleteItemFromOrder(){
		System.out.println("Enter the orderID of the order to delete from:");
		try {
			int orderID = scanner.nextInt();
			scanner.nextLine();
			system.printAnOrder(orderID);
			System.out.println("\n\nNow input the itemID to delete from the order(shown above):");
			int itemID = scanner.nextInt();
			scanner.nextLine();

			system.removeOrderItem(orderID, itemID);

		}catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the order number.");
		}
	}

	private void deleteItemFromIMS(){
		System.out.println("Input the itemID of the item you wish to remove from the IMS:");
		try {
			int itemID = scanner.nextInt();
			scanner.nextLine();
			system.removeWarehouseItem(itemID);

		}catch(InputMismatchException error1){
			System.out.println("\nPlease only input numbers for the item ID.");
		}
	}
}