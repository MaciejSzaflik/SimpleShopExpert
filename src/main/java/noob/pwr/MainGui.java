package noob.pwr;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
 	
 	Warehouse warehouse;
 	public List<Order> ordersList;
 	public List<Shop> shopList;
 	public List<Truck> truckList;
	
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
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(285, 531, 48, 37);
		getContentPane().add(formattedTextField);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(514, 438, 392, 130);
		getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		
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
		
		JLabel lblFinalResult = new JLabel("Final result:");
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
	
	private void CreateButtons()
	{
		JButton btnHello = new JButton("Verify and Fill Orders");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 	kSession.setGlobal("warehouse", warehouse);
					 	
			            kSession.insert(warehouse);
					 	for(int i = ordersList.size()-1;i>= 0;i--)
					 	{
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
		
		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.setBounds(178, 483, 158, 37);
		getContentPane().add(btnAddOrder);
		
		JButton btnRemoveOrder = new JButton("Remove Order");
		btnRemoveOrder.setBounds(178, 531, 103, 37);
		getContentPane().add(btnRemoveOrder);
		
		
		JButton btnOrganizeTrucks = new JButton("Organize Trucks");
		btnOrganizeTrucks.setBounds(346, 483, 158, 37);
		getContentPane().add(btnOrganizeTrucks);
		
		JButton btnShowLogs = new JButton("Show Logs");
		btnShowLogs.setBounds(10, 531, 103, 37);
		getContentPane().add(btnShowLogs);
		
		JButton btnCheckTrucks = new JButton("Check Trucks and Shops");
		btnCheckTrucks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {

				 	for(Map.Entry<Integer, Truck> entry : warehouse.fleet.entrySet())
				 	{
				 		kSession.insert(entry.getValue());
				 	}
				 	
				 	for(int i = 0;i<shopList.size();i++)
				 	{
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
		
	}
	
	private void CreatePanels()
	{
		warehouseStatePane = new JScrollPane();
		warehouseStatePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		warehouseStatePane.setBounds(10, 50, 158, 422);
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
		
		for(int i = 0;i<15;i++)
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
			itemValue.setBackground(Color.yellow);
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
		default:
			return Color.gray;
		}
	}
	
	
	public void initializeSession()
	{
		ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
     	kSession = kContainer.newKieSession("ksession-rules");
     	
	}

	private static MainGui instance;

	public static void main(String[] args) {
		MainGui.instance = new MainGui();
		MainGui.instance.setVisible(true);
	}
}