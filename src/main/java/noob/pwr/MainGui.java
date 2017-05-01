package noob.pwr;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;

import org.drools.core.WorkingMemory;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;


public class MainGui extends JFrame {
	
	private static final Random RANDOM = new Random();
	
	JScrollPane warehouseStatePane;
	JScrollPane orderStatePane;
	JScrollPane truckStatePane;
	
	JPanel warehousePanel;
	JPanel orderStatePanel;
	JPanel truckStatePanel;
	
	DrawPanel shopPanel;
	
	KieServices ks;
	KieContainer kContainer;
 	KieSession kSession;
 	
 	public Warehouse warehouse;
 	public List<Order> ordersList;
 	public List<Shop> shopList;
 	public List<Truck> truckList;

	private JTextArea textArea;
 	
 	public static MainGui MainGuiInstance()
 	{
 		return MainGui.instance;
 	}
	
	public MainGui() {
        SetLookAndFeel();
		getContentPane().setLayout(null);
		initializeSession();
		CreatePanels();
		
		CreateTestWarehouse();
		CreateTestShops();
		CreateTestOrders();
		CreateTestTrucks();
		
		CreateButtons();
		CreateLabels();
		CreateTextField();
		
		this.setBounds(0,0,930,612);
	}
	
	private void CreateTextField()
	{
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(514, 438, 392, 130);
		getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		JButton btnCreateHarmonogram = new JButton("Create Schedule");
		btnCreateHarmonogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSchedule();
			}
		});
		btnCreateHarmonogram.setBounds(10, 531, 158, 37);
		getContentPane().add(btnCreateHarmonogram);
		
		JButton btnCheckShops = new JButton("Check Shops");
		btnCheckShops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopEditor editor = new ShopEditor ();
				editor.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
				editor.setVisible (true);
			}
		});
		btnCheckShops.setBounds(178, 483, 158, 37);
		getContentPane().add(btnCheckShops);
		System.setOut(printStream);
	}
	
	private void CreateSchedule()
	{
		
		for(Map.Entry<Integer, Truck> entry : warehouse.fleet.entrySet())
	 	{
	 		entry.getValue().currentPosition = warehouse.position;
	 		entry.getValue().whenIdle = new Date();
	 	}
		
		StringBuilder sb = new StringBuilder();
		List<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
		sb.append("----------Start Schedule-------- \r\n");
		List<Order> ordersToFill = new ArrayList<Order>();
	 	for(int i = 0;i< ordersList.size();i++)
	 	{
	 		int shopId = ordersList.get(i).shopId;
	 		if(ordersList.get(i).status == OrderStatus.ReadyToSend)
	 		{
	 			if(shopList.get(shopId).CheckIfCanSellThis(ordersList.get(i)))
	 			{
	 				ordersToFill.add(ordersList.get(i));
	 			}
	 			else
	 				ordersList.get(i).status = OrderStatus.Declined;
	 		}
	 	}
	 	
	 	for(int i = 0;i< ordersToFill.size();i++)
	 	{
	 		Truck truck = TryToFindTruck(ordersToFill.get(i));
	 		if(truck == null)
	 		{
	 			ordersToFill.get(i).status = OrderStatus.NoTruck;
	 			continue;
	 		}
	 		Shop shop = shopList.get(ordersToFill.get(i).shopId);
	 		Date date = truck.whenIdle;
	 		Position2D position = truck.currentPosition;
	 		truck.FullfillOrder(ordersToFill.get(i),shop.position);
	 		truck.status = TruckState.Used;
	 		schedule.add(new ScheduleEntry(date,truck.whenIdle,truck.id,ordersToFill.get(i).id,ordersToFill.get(i).shopId,position,truck.currentPosition));
	 		ordersToFill.get(i).status = OrderStatus.Fullfield;
	 	}
	 	
	 	schedule.sort((a,b) ->  a.compareTo(b));
	 	shopPanel.listLines = new ArrayList<Position2D>();
	 	
	 	for(ScheduleEntry entry : schedule)
	 	{
	 		shopPanel.listLines.add(entry.startPosition);
	 		shopPanel.listLines.add(entry.endPosition);
	 		sb.append(entry);
	 	}
	 	
	 	sb.append("----------End Schedule--------");
	 	
	 	TextWindow textWindow = new TextWindow(sb.toString());
	 	textWindow.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	 	textWindow.setVisible (true);
	 	
	 	shopPanel.repaint();
	 	UpdateOrderUI();
	 	UpdateTruckUI();
	}
	
	private class ScheduleEntry implements Comparable<ScheduleEntry>
	{
		public Date start;
		public Date end;
		public int truckId;
		public int orderId;
		public int shopId;
		
		public Position2D startPosition;
		public Position2D endPosition;
		
		public ScheduleEntry(Date timestampStart,Date timestampEnd, int truckId, int orderId, int shopId, Position2D startPosition, Position2D endPosition){
			this.start = timestampStart;
			this.end = timestampEnd;
			this.truckId = truckId;
			this.orderId = orderId;
			this.shopId = shopId;
			this.startPosition = startPosition;
			this.endPosition = endPosition;
		}
		
		public String toString()
		{
			return String.format("Start: %1$s||End: %2$s||Order: %3$s||To: %4$s||By: %5$s||Distance: %6$s  \r\n", start,end,orderId,shopId,truckId,
					this.startPosition.getManhatanDistance(endPosition));
		}

		@Override
		public int compareTo(ScheduleEntry arg0) {
			return (int) (start.getTime() - arg0.start.getTime());
		}
	 
	}
	
	private Truck TryToFindTruck(Order order)
	{
		int miniumIndex = -1;
		long minimum = Long.MAX_VALUE;
		for(Map.Entry<Integer, Truck> entry : warehouse.fleet.entrySet())
		{
			if(entry.getValue().CanPerformThisOrder(order))
			{
				Position2D position = shopList.get(order.shopId).position;
				if(entry.getValue().GetTimeToTravel(position).getTime() < minimum)
				{
					minimum = entry.getValue().whenIdle.getTime();
					miniumIndex = entry.getValue().id;
				}
			}
		}
		
		if(miniumIndex < 0)
			return null;
		else
			return  warehouse.fleet.get(miniumIndex);
	}
	
	private void CreateLabels()
	{
		JLabel lblWarehouse = new JLabel("Warehouse state:");
		lblWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWarehouse.setBounds(10, 11, 158, 28);
		getContentPane().add(lblWarehouse);
		
		JLabel lblOrdersState = new JLabel("Orders state:");
		lblOrdersState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrdersState.setBounds(178, 11, 158, 28);
		getContentPane().add(lblOrdersState);
		
		JLabel lblFinalResult = new JLabel("Logs");
		lblFinalResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinalResult.setBounds(514, 399, 158, 28);
		getContentPane().add(lblFinalResult);
		
		JLabel lblShopState = new JLabel("Shop state:");
		lblShopState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblShopState.setBounds(514, 11, 158, 28);
		getContentPane().add(lblShopState);
		
		JLabel lblTrucksState = new JLabel("Trucks state:");
		lblTrucksState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrucksState.setBounds(346, 11, 158, 28);
		getContentPane().add(lblTrucksState);
	}
	
	public void VerifyWarehouse()
	{
		initializeSession();
		
		kSession.insert(warehouse);
        kSession.fireAllRules();
	}
	
	public void VerifyTruck(int id)
	{
		initializeSession();
		
		kSession.insert(warehouse.fleet.get(id));
        kSession.fireAllRules();
	}
	
	public void VerifyShop(int id)
	{
		initializeSession();
		
		kSession.insert(shopList.get(id));
        kSession.fireAllRules();
	}
	
	private void CreateButtons()
	{
		JButton btnHello = new JButton("Verify and Fill Orders");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 	textArea.setText("");
						initializeSession();
						warehouse.ClearAllChecks();
						warehouse.RestoreTestData();
					 	kSession.setGlobal("warehouse", warehouse);
					 	
			            kSession.insert(warehouse);
					 	for(int i = ordersList.size()-1;i>= 0;i--)
					 	{
					 		ordersList.get(i).ClearAllChecks();
					 		kSession.insert(ordersList.get(i));	
					 	}
					 	
			            kSession.fireAllRules();
			            UpdateWarehouseUI();
			            UpdateOrderUI();
			        } catch (Throwable t) {
			            t.printStackTrace();
			        }
			}
		});
		btnHello.setBounds(10, 483, 158, 37);
		getContentPane().add(btnHello);
		
		
		JButton btnOrganizeTrucks = new JButton("Organize Trucks");
		btnOrganizeTrucks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TruckEditor editor = new TruckEditor ();
				editor.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
				editor.setVisible (true);
			}
		});
		btnOrganizeTrucks.setBounds(346, 483, 158, 37);
		getContentPane().add(btnOrganizeTrucks);
		
		JButton btnCheckTrucks = new JButton("Check Trucks and Shops");
		btnCheckTrucks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					textArea.setText("");
					initializeSession();
				 	for(Map.Entry<Integer, Truck> entry : warehouse.fleet.entrySet())
				 	{
				 		entry.getValue().ClearChecks();
				 		kSession.insert(entry.getValue());
				 	}
				 	
				 	for(int i = 0;i<shopList.size();i++)
				 	{
				 		shopList.get(i).ClearCheck();
				 		kSession.insert(shopList.get(i));
				 	}
				 	
		            kSession.fireAllRules();
		        } catch (Throwable t) {
		            t.printStackTrace();
		        }
				
			}
		});
		btnCheckTrucks.setBounds(346, 531, 158, 37);
		getContentPane().add(btnCheckTrucks);
		
		JButton btnEditWarehouse = new JButton("Edit Warehouse");
		btnEditWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WarehouseEditor editor = new WarehouseEditor ();
				editor.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
				editor.setVisible (true);
			}
		});
		btnEditWarehouse.setBounds(10, 50, 158, 37);
		getContentPane().add(btnEditWarehouse);
		
	}
	
	private void CreatePanels()
	{
		warehouseStatePane = new JScrollPane();
		warehouseStatePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		warehouseStatePane.setBounds(10, 92, 158, 380);
		getContentPane().add(warehouseStatePane);
		
		warehousePanel = new JPanel();
		warehouseStatePane.setViewportView(warehousePanel);
		warehousePanel.setLayout(new BoxLayout(warehousePanel, BoxLayout.Y_AXIS));
		
		orderStatePane = new JScrollPane();
		orderStatePane.setBounds(178, 50, 158, 422);
		getContentPane().add(orderStatePane);
		
		orderStatePanel = new JPanel();
		orderStatePane.setViewportView(orderStatePanel);
		orderStatePanel.setLayout(new BoxLayout(orderStatePanel, BoxLayout.Y_AXIS));
		
		shopPanel = new DrawPanel();
		shopPanel.setBackground(Color.WHITE);
		shopPanel.setBounds(514, 50, 393, 329);
		getContentPane().add(shopPanel);
		
		
		truckStatePane = new JScrollPane();
		truckStatePane.setBounds(346, 50, 158, 422);
		getContentPane().add(truckStatePane);
		
		truckStatePanel = new JPanel();
		truckStatePane.setViewportView(truckStatePanel);
		truckStatePanel.setLayout(new BoxLayout(truckStatePanel, BoxLayout.Y_AXIS));
	}
	
	private void SetLookAndFeel() {
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	        } catch (InstantiationException ex) {
	        } catch (IllegalAccessException ex) {
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        }		
	}
	
	private void CreateTestShops()
	{
		shopList = new ArrayList<Shop>();
		shopPanel.shopsToDraw = new ArrayList<Position2D>();
		for(int i = 0;i<20;i++)
		{
			shopList.add(new Shop(getSpiral(i*20.0f)));
			shopPanel.shopsToDraw.add(shopList.get(i).position);
		}
		
		shopPanel.repaint();
	}
	
	private Position2D getSpiral(float value)
	{
		float r = 50 + 0.3f*value;
		return new Position2D((float)(r*Math.cos(value)),(float)(r*Math.sin(value)));
	}
	
	private void CreateTestOrders()
	{
		ordersList = new ArrayList<Order>();
		
		for(int i = 0;i<25;i++)
		{
			Item[] items = {new Item(GetRandomProduct(),GetRandomInt(10,25)),new Item(GetRandomProduct(),GetRandomInt(10,25))};
			ordersList.add(new Order(items,shopList.get(i%shopList.size()).id));
		}
		
		this.UpdateOrderUI();
	}
	
	private void CreateTestTrucks()
	{
		this.truckList = new ArrayList<Truck>();
		
		for(int i = 0;i<5;i++)
		{
			truckList.add(new Truck(GetRandomInt(80,200)));
		}
		this.warehouse.addTrucks(this.truckList.toArray(new Truck[0]));
		this.UpdateTruckUI();
	}
	
	private int GetRandomInt(int min, int max)
	{
		return Math.round(min + (float)Math.random()*(max - min));
	}
	
	private ProductName GetRandomProduct()
	{
		return ProductName.values()[RANDOM.nextInt(ProductName.values().length)];
	}

	private void CreateTestWarehouse()
	{
		warehouse = new Warehouse(new Position2D(0,0));
		for(ProductName name : ProductName.values())
		{
			warehouse.addItem(new Item(name,100));
		}
		UpdateWarehouseUI();
		
		shopPanel.warehouse = warehouse.position;
	}
	
	private void UpdateWarehouseUI()
	{
		warehousePanel.removeAll();
		for(ProductName name : ProductName.values())
		{
			int value = 0;
			if(warehouse.items.containsKey(name))
				value = warehouse.items.get(name).getQuanity();
			
			JLabel itemValue = new JLabel(name + " : " + value);
			warehousePanel.add(itemValue);
		}
		warehousePanel.revalidate();
		warehousePanel.repaint();
	}
	
	private void UpdateTruckUI()
	{
		truckStatePanel.removeAll();
		for(Truck truck : this.truckList)
		{
			JLabel itemValue = new JLabel(truck.id + " " + truck.capacity +" : " + truck.status);
			itemValue.setBackground(truck.status == TruckState.Idle?Color.yellow : Color.green);
			itemValue.setOpaque(true);
			truckStatePanel.add(itemValue);
		}
		truckStatePanel.revalidate();
		truckStatePanel.repaint();
	}
	
	private void UpdateOrderUI()
	{
		orderStatePanel.removeAll();
		for(Order order : ordersList)
		{
			JLabel itemValue = new JLabel(order.id + " : " + order.status);
			orderStatePanel.add(itemValue);
			
			itemValue.setBackground(getColorFromOrderStatus(order.status));
			itemValue.setOpaque(true);
		}
		orderStatePanel.revalidate();
		orderStatePanel.repaint();
	}
	
	private Color getColorFromOrderStatus(OrderStatus status)
	{
		switch(status)
		{
		case Accepted:
			return Color.cyan;
		case Declined:
			return Color.red;
		case Fullfield:
			return Color.green;
		case NoItems:
			return Color.red;
		case ReadyToSend:
			return Color.cyan;
		case Send:
			return Color.green;
		case Undecided:
			return Color.yellow;
		case NoTruck:
			return Color.magenta;
		default:
			return Color.gray;
		}
	}
	
	
	public void initializeSession()
	{
		if(kContainer!=null)
			kContainer.dispose();
		if(kSession!=null)
			kSession.destroy();

		
		ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
     	kSession = kContainer.newKieSession("ksession-rules");
     	
	}

	private static MainGui instance;

	public static void main(String[] args) {
		MainGui.instance = new MainGui();
		MainGui.instance.setVisible(true);
		MainGui.instance.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}