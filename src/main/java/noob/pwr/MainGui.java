package noob.pwr;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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


public class MainGui extends JFrame {
	
	private static final Random RANDOM = new Random();
	
	JScrollPane warehouseStatePane;
	JScrollPane orderStatePane;
	
	JPanel warehousePanel;
	JPanel orderStatePanel;
	
	KieServices ks;
	KieContainer kContainer;
 	KieSession kSession;
 	
 	Warehouse warehouse;
 	public List<Order> ordersList;
 	public List<Shop> shopList;
	
	public MainGui() {
        SetLookAndFeel();
		
		getContentPane().setLayout(null);
		initializeSession();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(633, 50, 393, 329);
		getContentPane().add(panel);
		
		CreatePanels();
		CreateButtons();
		CreateLabels();
		CreateTextField();
		
		
		this.setBounds(0,0,1052,695);
		
		CreateTestWarehouse();
		CreateTestShops();
		CreateTestOrders();
	}
	
	private void CreateTextField()
	{
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(401, 531, 123, 37);
		getContentPane().add(formattedTextField);
	}
	
	private void CreateLabels()
	{
		JLabel lblWarehouse = new JLabel("Warehouse state:");
		lblWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWarehouse.setBounds(10, 11, 247, 28);
		getContentPane().add(lblWarehouse);
		
		JLabel lblOrdersState = new JLabel("Orders state:");
		lblOrdersState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrdersState.setBounds(277, 11, 247, 28);
		getContentPane().add(lblOrdersState);
	}
	
	private void CreateButtons()
	{
		JButton btnHello = new JButton("Verify and Fill Orders");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 	for(Order order : ordersList)
					 	{
					 		kSession.insert(order);	
					 	}
			            kSession.insert(warehouse);
			            kSession.fireAllRules();
			            UpdateWarehouseUI();
			        } catch (Throwable t) {
			            t.printStackTrace();
			        }
			}
		});
		btnHello.setBounds(10, 483, 247, 37);
		getContentPane().add(btnHello);
		
		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.setBounds(277, 483, 247, 37);
		getContentPane().add(btnAddOrder);
		
		JButton btnRemoveOrder = new JButton("Remove Order");
		btnRemoveOrder.setBounds(277, 531, 123, 37);
		getContentPane().add(btnRemoveOrder);
	}
	
	private void CreatePanels()
	{
		warehouseStatePane = new JScrollPane();
		warehouseStatePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		warehouseStatePane.setBounds(10, 50, 247, 422);
		getContentPane().add(warehouseStatePane);
		
		warehousePanel = new JPanel();
		warehouseStatePane.setViewportView(warehousePanel);
		warehousePanel.setLayout(new BoxLayout(warehousePanel, BoxLayout.Y_AXIS));
		
		orderStatePane = new JScrollPane();
		orderStatePane.setBounds(277, 50, 247, 422);
		getContentPane().add(orderStatePane);
		
		orderStatePanel = new JPanel();
		orderStatePane.setViewportView(orderStatePanel);
		orderStatePanel.setLayout(new BoxLayout(orderStatePanel, BoxLayout.Y_AXIS));
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
		for(int i = 0;i<10;i++)
		{
			shopList.add(new Shop(getSpiral(i/2.0f)));
		}
	}
	
	private Position2D getSpiral(float value)
	{
		float r = 0.75f + 0.13f*value;
		return new Position2D((float)(r*Math.cos(value)),(float)(r*Math.sin(value)));
	}
	
	private void CreateTestOrders()
	{
		ordersList = new ArrayList<Order>();
		
		for(int i = 0;i<10;i++)
		{
			Item[] items = {new Item(GetRandomProduct(),GetRandomInt(10,20)),new Item(GetRandomProduct(),GetRandomInt(10,20))};
			ordersList.add(new Order(items,shopList.get(i).id));
		}
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