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

public class TruckEditor extends JFrame {

	JCheckBox cleanState;
	JCheckBox conditionState;
	JCheckBox materialsUsedValidForFood;
	JCheckBox airRotationPossible;
	JCheckBox truckerHaveCertificat;
	JCheckBox coolingAgregate;
	JCheckBox usedOnlyForFood;
	private JCheckBox wasPreCooled;
	private JCheckBox wholeTruckTheSameTemperature;
	private JCheckBox pyroPermission;
	private JCheckBox truckerPyroCertificat;
	private JCheckBox truckerCanUseFireEstinguiser;
	private JCheckBox packageForPyro;
	private JCheckBox haveFireEstingisher;
	private JCheckBox truckHaveStoppers;
	private JCheckBox haveTorch;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JCheckBox fireInstrucion;
	private JCheckBox pyroDocumentation;
	private JButton btnWybierz;
	private JCheckBox pyroInsurance;
	private JCheckBox pyroMarked;
	private JCheckBox onlyPyro;
	private JCheckBox checkBox;
	private JCheckBox chckbxPrzewoziTylkoywno;
	private JComboBox<Integer> comboBox;
	private JButton button;
	
	public void SetFromTruck(Truck truck) {
		cleanState.setSelected(TripleState.GetBool(truck.cleanState));
		conditionState.setSelected(TripleState.GetBool(truck.conditionState));
		materialsUsedValidForFood.setSelected(TripleState.GetBool(truck.materialsUsedValidForFood));
		airRotationPossible.setSelected(TripleState.GetBool(truck.airRotationPossible));
		truckerHaveCertificat.setSelected(TripleState.GetBool(truck.truckerHaveCertificat));
		coolingAgregate.setSelected(TripleState.GetBool(truck.coolingAgregate));
		usedOnlyForFood.setSelected(TripleState.GetBool(truck.usedOnlyForFood));
		wasPreCooled.setSelected(TripleState.GetBool(truck.wasPreCooled));
		wholeTruckTheSameTemperature.setSelected(TripleState.GetBool(truck.pyroPermission));
		pyroPermission.setSelected(TripleState.GetBool(truck.pyroPermission));
		truckerPyroCertificat.setSelected(TripleState.GetBool(truck.truckerPyroCertificat));
		truckerCanUseFireEstinguiser.setSelected(TripleState.GetBool(truck.truckerCanUseFireEstinguiser));
		packageForPyro.setSelected(TripleState.GetBool(truck.packageForPyro));
		haveFireEstingisher.setSelected(TripleState.GetBool(truck.haveFireEstingisher));
		truckHaveStoppers.setSelected(TripleState.GetBool(truck.truckHaveStoppers));
		haveTorch.setSelected(TripleState.GetBool(truck.haveTorch));
		fireInstrucion.setSelected(TripleState.GetBool(truck.fireInstrucion));
		pyroDocumentation.setSelected(TripleState.GetBool(truck.pyroInsurance));
		pyroInsurance.setSelected(TripleState.GetBool(truck.pyroInsurance));
		pyroMarked.setSelected(TripleState.GetBool(truck.pyroMarked));
		onlyPyro.setSelected(TripleState.GetBool(truck.onlyPyro));
	}
	
	public void SetTruck(Truck truck)
	{
		truck.cleanState = TripleState.FromBool(cleanState.isSelected());
		truck.conditionState = TripleState.FromBool(conditionState.isSelected());
		truck.materialsUsedValidForFood = TripleState.FromBool(materialsUsedValidForFood.isSelected());
		truck.airRotationPossible = TripleState.FromBool(airRotationPossible.isSelected());
		truck.truckerHaveCertificat = TripleState.FromBool(truckerHaveCertificat.isSelected());
		truck.coolingAgregate = TripleState.FromBool(coolingAgregate.isSelected());
		truck.usedOnlyForFood = TripleState.FromBool(usedOnlyForFood.isSelected());
		truck.wasPreCooled = TripleState.FromBool(wasPreCooled.isSelected());
		truck.wholeTruckTheSameTemperature = TripleState.FromBool(wholeTruckTheSameTemperature.isSelected());
		truck.pyroPermission = TripleState.FromBool(pyroPermission.isSelected());
		truck.truckerPyroCertificat = TripleState.FromBool(truckerPyroCertificat.isSelected());
		truck.truckerCanUseFireEstinguiser = TripleState.FromBool(truckerCanUseFireEstinguiser.isSelected());
		truck.packageForPyro = TripleState.FromBool(packageForPyro.isSelected());
		truck.haveFireEstingisher = TripleState.FromBool(haveFireEstingisher.isSelected());
		truck.truckHaveStoppers = TripleState.FromBool(truckHaveStoppers.isSelected());
		truck.haveTorch = TripleState.FromBool(haveTorch.isSelected());
		truck.fireInstrucion = TripleState.FromBool(fireInstrucion.isSelected());
		truck.pyroDocumentation = TripleState.FromBool(pyroDocumentation.isSelected());
		truck.pyroInsurance = TripleState.FromBool(pyroInsurance.isSelected());
		truck.pyroMarked = TripleState.FromBool(pyroMarked.isSelected());
		truck.onlyPyro = TripleState.FromBool(onlyPyro.isSelected());
	}
	
	
	public TruckEditor() {
		getContentPane().setLayout(null);
		this.setBounds(30, 30, 870, 632);
		
		Warehouse warehouse = MainGui.MainGuiInstance().warehouse;
		
		cleanState = new JCheckBox("Czy jest czyste - mi\u0119so");
		cleanState.setBounds(6, 7, 172, 23);
		getContentPane().add(cleanState);
		
		materialsUsedValidForFood = new JCheckBox("Czy jest wewn\u0105trz pokryte materia\u0142em odpowiednim do przewozu \u017Cywno\u015Bci - mi\u0119so");
		materialsUsedValidForFood.setBounds(6, 33, 449, 23);
		getContentPane().add(materialsUsedValidForFood);
		
		airRotationPossible = new JCheckBox("Czy istnieje mo\u017Cliwo\u015B\u0107 wietrzenia");
		airRotationPossible.setBounds(6, 59, 366, 23);
		getContentPane().add(airRotationPossible);
		
		truckerHaveCertificat = new JCheckBox("Czy jest kierowca odby\u0142 szkolenie - mi\u0119so");
		truckerHaveCertificat.setBounds(6, 85, 366, 23);
		getContentPane().add(truckerHaveCertificat);
		
		coolingAgregate = new JCheckBox("Czy posiada ch\u0142odnie - mi\u0119so");
		coolingAgregate.setBounds(6, 111, 366, 23);
		getContentPane().add(coolingAgregate);
		
		pyroPermission = new JCheckBox("Pojazd posiada zezwolenie na transport materia\u0142\u00F3w pirotechnicznych");
		pyroPermission.setBounds(6, 137, 487, 23);
		getContentPane().add(pyroPermission);
		
		truckerPyroCertificat = new JCheckBox("Kierowca zosta\u0142 przeszkolony w przewo\u017Ceniu materia\u0142\u00F3w pirotechnicznych");
		truckerPyroCertificat.setBounds(6, 163, 487, 23);
		getContentPane().add(truckerPyroCertificat);
		
		truckerCanUseFireEstinguiser = new JCheckBox("Kierowca potrafi korzysta\u0107 z ga\u015Bnic i reagowa\u0107 na wypadek po\u017Caru");
		truckerCanUseFireEstinguiser.setBounds(6, 189, 487, 23);
		getContentPane().add(truckerCanUseFireEstinguiser);
		
		packageForPyro = new JCheckBox("Posiadanie odpowiednich opakowa\u0144 do przewozu materia\u0142\u00F3w pirotechnicznych - certyfikowane");
		packageForPyro.setBounds(6, 215, 487, 23);
		getContentPane().add(packageForPyro);
		
		onlyPyro = new JCheckBox("Materia\u0142y pirotechniczne nie s\u0105 przywo\u017Cone z materia\u0142ami \u0142atwopalnymi");
		onlyPyro.setBounds(6, 241, 487, 23);
		getContentPane().add(onlyPyro);
		
		haveFireEstingisher = new JCheckBox("Ci\u0119\u017Car\u00F3wka jest wyposa\u017Cona w ga\u015Bnice chemiczne");
		haveFireEstingisher.setBounds(6, 267, 487, 23);
		getContentPane().add(haveFireEstingisher);
		
		truckHaveStoppers = new JCheckBox("Ci\u0119\u017Car\u00F3wka jest wyposa\u017Cona w kliny");
		truckHaveStoppers.setBounds(6, 293, 487, 23);
		getContentPane().add(truckHaveStoppers);
		
		haveTorch = new JCheckBox("Ci\u0119\u017Car\u00F3wka wyposa\u017Cona jest w latark\u0119 iskrobezpieczn\u0105");
		haveTorch.setBounds(6, 319, 487, 23);
		getContentPane().add(haveTorch);
		
		fireInstrucion = new JCheckBox("Ci\u0119\u017Car\u00F3wka posiada instrukcje HB 76 na temat transportu niebezpiecznych produkt\u00F3w");
		fireInstrucion.setBounds(6, 345, 487, 23);
		getContentPane().add(fireInstrucion);
		
		pyroMarked = new JCheckBox("Ci\u0119\u017Car\u00F3wka odpowiednio oznaczona");
		pyroMarked.setBounds(6, 371, 487, 23);
		getContentPane().add(pyroMarked);
		
		pyroDocumentation = new JCheckBox("Transport jest dokumentowany i zawiera informacje na temat klasyfikacji, ilo\u015Bci i typ\u00F3w przewo\u017Conych fajerwerk\u00F3w");
		pyroDocumentation.setFont(new Font("Tahoma", Font.PLAIN, 8));
		pyroDocumentation.setBounds(6, 393, 487, 23);
		getContentPane().add(pyroDocumentation);
		
		JButton btnNewButton = new JButton("Zweryfikuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
				MainGui.MainGuiInstance().warehouse.fleet.get(GetSelectedTruck()).ClearChecks();
				SetTruck(MainGui.MainGuiInstance().warehouse.fleet.get(GetSelectedTruck()));
				MainGui.MainGuiInstance().VerifyTruck(GetSelectedTruck());
				ShowRestrictedProducts();
			}
		});
		btnNewButton.setBounds(505, 527, 339, 56);
		getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(499, 74, 345, 138);
		getContentPane().add(textArea);
		
		JLabel lblZakazaneProdukty = new JLabel("Zakazane produkty");
		lblZakazaneProdukty.setBounds(495, 52, 151, 14);
		getContentPane().add(lblZakazaneProdukty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(495, 229, 349, 287);
		getContentPane().add(scrollPane);
		
		textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea_1));
		
		pyroInsurance = new JCheckBox("Ci\u0119\u017Car\u00F3wka ma wykupione odpowiednie ubezpieczenie");
		pyroInsurance.setSelected(false);
		pyroInsurance.setBounds(6, 419, 487, 23);
		getContentPane().add(pyroInsurance);
		
		btnWybierz = new JButton("Wybierz");
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetFromTruck(MainGui.MainGuiInstance().warehouse.fleet.get(GetSelectedTruck()));
			}
		});
		btnWybierz.setBounds(495, 7, 89, 23);
		getContentPane().add(btnWybierz);
		
		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(594, 8, 250, 23);
		getContentPane().add(comboBox);
		
		for (Map.Entry<Integer, Truck> entry : warehouse.fleet.entrySet()) {
			comboBox.addItem(entry.getKey());
		}
		
		wasPreCooled = new JCheckBox("Ci\u0119\u017Car\u00F3wka zosta\u0142a uprzednio sch\u0142odzona (ryby, mi\u0119so)");
		wasPreCooled.setSelected(false);
		wasPreCooled.setBounds(6, 445, 487, 23);
		getContentPane().add(wasPreCooled);
		
		wholeTruckTheSameTemperature = new JCheckBox("Ch\u0142odnia zapewnia r\u00F3wnomiern\u0105 temperatur\u0119 na ca\u0142ej swojej powierzchni (ryby, mro\u017Conki)");
		wholeTruckTheSameTemperature.setSelected(false);
		wholeTruckTheSameTemperature.setBounds(6, 471, 487, 23);
		getContentPane().add(wholeTruckTheSameTemperature);
		
		conditionState = new JCheckBox("Ci\u0119\u017Car\u00F3wka jest w stanie dobrym - mi\u0119so");
		conditionState.setBounds(6, 497, 487, 23);
		getContentPane().add(conditionState);
		
		usedOnlyForFood = new JCheckBox("Przewozi tylko \u017Cywno\u015B\u0107 - mi\u0119so");
		usedOnlyForFood.setBounds(6, 523, 487, 23);
		getContentPane().add(usedOnlyForFood);
		
		button = new JButton("Zatwierdz");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetTruck(MainGui.MainGuiInstance().warehouse.fleet.get(GetSelectedTruck()));
			}
		});
		button.setBounds(310, 7, 179, 23);
		getContentPane().add(button);
		
		
		//System.setOut(printStream);
	}
	
	public int GetSelectedTruck()
	{
		return Integer.parseInt(comboBox.getSelectedItem().toString());
	}
	
	
	public void ShowRestrictedProducts()
	{
		StringBuilder sb = new StringBuilder();
		for(ProductName name : MainGui.MainGuiInstance().warehouse.fleet.get(GetSelectedTruck()).whatCannotBeDelivered)
		{
			sb.append(name.toString());
			sb.append("\r\n");
		}
		textArea.setText(sb.toString());
	}
}
