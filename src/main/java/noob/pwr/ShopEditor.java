package noob.pwr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JComboBox;

public class ShopEditor extends JFrame {

	JCheckBox washHandsPossible;
	JCheckBox haveCooler;

	private JTextArea textArea;
	private JTextArea textArea_1;

	private JComboBox<Integer> comboBox;
	private JButton button;
	private JButton btnWybierz;
	
	public void SetFromShop(Shop shop) {
		washHandsPossible.setSelected(TripleState.GetBool(shop.washHandsPossible));
		haveCooler.setSelected(TripleState.GetBool(shop.haveCooler));

	}
	
	public void SetShop(Shop shop)
	{
		shop.washHandsPossible = TripleState.FromBool(washHandsPossible.isSelected());
		shop.haveCooler = TripleState.FromBool(haveCooler.isSelected());
	}
	
	
	public ShopEditor() {
		getContentPane().setLayout(null);
		this.setBounds(30, 30, 870, 256);
		
		Warehouse warehouse = MainGui.MainGuiInstance().warehouse;
		
		washHandsPossible = new JCheckBox("Czy kierowca mo¿e umyæ rêce po roz³adunku - miêso");
		washHandsPossible.setBounds(6, 59, 326, 23);
		getContentPane().add(washHandsPossible);
		
		haveCooler = new JCheckBox("Czy posiada ch³odnie - miêso");
		haveCooler.setBounds(6, 33, 449, 23);
		getContentPane().add(haveCooler);
		
		
		
		JButton btnNewButton = new JButton("Zweryfikuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGui.MainGuiInstance().shopList.get(GetSelectedShop()).ClearCheck();
				SetShop(MainGui.MainGuiInstance().shopList.get(GetSelectedShop()));
				MainGui.MainGuiInstance().VerifyShop(GetSelectedShop());
				ShowRestrictedProducts();
			}
		});
		btnNewButton.setBounds(10, 151, 339, 56);
		getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(499, 74, 345, 138);
		getContentPane().add(textArea);
		
		JLabel lblZakazaneProdukty = new JLabel("Zakazane produkty");
		lblZakazaneProdukty.setBounds(495, 52, 151, 14);
		getContentPane().add(lblZakazaneProdukty);
		
		
		btnWybierz = new JButton("Wybierz");
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetFromShop(MainGui.MainGuiInstance().shopList.get(GetSelectedShop()));
			}
		});
		btnWybierz.setBounds(495, 7, 89, 23);
		getContentPane().add(btnWybierz);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(594, 8, 250, 23);
		getContentPane().add(comboBox);
		
		for (int i = 0; i< MainGui.MainGuiInstance().shopList.size();i++) {
			comboBox.addItem(i);
		}
		
		
		button = new JButton("Zatwierdz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetShop(MainGui.MainGuiInstance().shopList.get(GetSelectedShop()));
			}
		});
		button.setBounds(310, 7, 179, 23);
		getContentPane().add(button);
		
		
		//System.setOut(printStream);
	}
	
	public int GetSelectedShop()
	{
		return Integer.parseInt(comboBox.getSelectedItem().toString());
	}
	
	
	public void ShowRestrictedProducts()
	{
		textArea.setText("Can buy meat:"+ MainGui.MainGuiInstance().shopList.get(GetSelectedShop()).deliveryOfFoodPossible);
	}
}
