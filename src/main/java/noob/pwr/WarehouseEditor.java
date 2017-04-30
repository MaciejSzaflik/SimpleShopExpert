package noob.pwr;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class WarehouseEditor extends JFrame{
	
	JCheckBox chckbxNewCheckBox;
	JCheckBox chckbxIstniejeMoliwoWyczyszczenia;
	JCheckBox chckbxJestPrzewidzianyProgram;
	JCheckBox chckbxJestZabezpieczonypoty;
	JCheckBox chckbxPosiadaPosiadaPrzestrze;
	JCheckBox chckbxPosiadaFederalEmploy;
	JCheckBox chckbxPosiadaTtbPermit;
	JCheckBox chckbxCzyJestWykupiona;
	private JCheckBox chckbxPosiadaLicencjeNa;
	private JCheckBox chckbxCzyMagazynZawiera;
	private JCheckBox chckbxCzyOwietlenieElektryczne;
	private JCheckBox chckbxCzyWszystkieWczniki;
	private JCheckBox chckbxCzyDokumentacjaSieci;
	private JCheckBox chckbxCzyIstniejeCigy;
	private JCheckBox chckbxRybyDzikiePosiadaj;
	private JCheckBox chckbxDrbIJajka;
	private JCheckBox chckbxProduktyZwierzcePochodzenia;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JCheckBox chckbxCzyMateriayPirotechniczne;
	
	public void SetFromWarehouse(Warehouse warehouse) {
		chckbxNewCheckBox.setSelected(TripleState.GetBool(warehouse.isFoodIsolated));
		chckbxIstniejeMoliwoWyczyszczenia.setSelected(TripleState.GetBool(warehouse.isPossibleToClean));
		chckbxJestPrzewidzianyProgram.setSelected(TripleState.GetBool(warehouse.isThereContaminationProgram));
		chckbxJestZabezpieczonypoty.setSelected(TripleState.GetBool(warehouse.isThereSecurity));
		chckbxPosiadaPosiadaPrzestrze.setSelected(TripleState.GetBool(warehouse.designatedLiquorSpace));
		chckbxPosiadaLicencjeNa.setSelected(TripleState.GetBool(warehouse.licenseSaleLiquor));
		chckbxPosiadaFederalEmploy.setSelected(TripleState.GetBool(warehouse.haveFederalEmployerIdentification));
		chckbxPosiadaTtbPermit.setSelected(TripleState.GetBool(warehouse.TTBPermit));
		chckbxCzyJestWykupiona.setSelected(TripleState.GetBool(warehouse.pyroPermit));
		chckbxCzyMagazynZawiera.setSelected(TripleState.GetBool(warehouse.saveFromSparks));
		chckbxCzyOwietlenieElektryczne.setSelected(TripleState.GetBool(warehouse.necEletric));
		chckbxCzyWszystkieWczniki.setSelected(TripleState.GetBool(warehouse.areSwichesOutside));
		chckbxCzyDokumentacjaSieci.setSelected(TripleState.GetBool(warehouse.isElectricDocumented));
		chckbxCzyIstniejeCigy.setSelected(TripleState.GetBool(warehouse.monitoringOfTemperature));
		chckbxRybyDzikiePosiadaj.setSelected(TripleState.GetBool(warehouse.fishCertified));
		chckbxDrbIJajka.setSelected(TripleState.GetBool(warehouse.eggCertified));
		chckbxProduktyZwierzcePochodzenia.setSelected(TripleState.GetBool(warehouse.meatCertified));
		chckbxCzyMateriayPirotechniczne.setSelected(TripleState.GetBool(warehouse.isPyroMarked));
	}
	
	public void SetWarehouse()
	{
		Warehouse warehouse = MainGui.MainGuiInstance().warehouse;
		warehouse.isFoodIsolated = TripleState.FromBool(chckbxNewCheckBox.isSelected());
		warehouse.isPossibleToClean = TripleState.FromBool(chckbxIstniejeMoliwoWyczyszczenia.isSelected());
		warehouse.isThereContaminationProgram = TripleState.FromBool(chckbxJestPrzewidzianyProgram.isSelected());
		warehouse.isThereSecurity = TripleState.FromBool(chckbxJestZabezpieczonypoty.isSelected());
		warehouse.designatedLiquorSpace = TripleState.FromBool(chckbxPosiadaPosiadaPrzestrze.isSelected());
		warehouse.licenseSaleLiquor = TripleState.FromBool(chckbxPosiadaLicencjeNa.isSelected());
		warehouse.haveFederalEmployerIdentification = TripleState.FromBool(chckbxPosiadaFederalEmploy.isSelected());
		warehouse.TTBPermit = TripleState.FromBool(chckbxPosiadaTtbPermit.isSelected());
		warehouse.pyroPermit = TripleState.FromBool(chckbxCzyJestWykupiona.isSelected());
		warehouse.saveFromSparks = TripleState.FromBool(chckbxCzyMagazynZawiera.isSelected());
		warehouse.necEletric = TripleState.FromBool(chckbxCzyOwietlenieElektryczne.isSelected());
		warehouse.areSwichesOutside = TripleState.FromBool(chckbxCzyWszystkieWczniki.isSelected());
		warehouse.isElectricDocumented = TripleState.FromBool(chckbxCzyDokumentacjaSieci.isSelected());
		warehouse.monitoringOfTemperature = TripleState.FromBool(chckbxCzyIstniejeCigy.isSelected());
		warehouse.fishCertified = TripleState.FromBool(chckbxRybyDzikiePosiadaj.isSelected());
		warehouse.eggCertified = TripleState.FromBool(chckbxDrbIJajka.isSelected());
		warehouse.meatCertified = TripleState.FromBool(chckbxProduktyZwierzcePochodzenia.isSelected());
		warehouse.isPyroMarked = TripleState.FromBool(chckbxCzyMateriayPirotechniczne.isSelected());
	}
	
	
	public WarehouseEditor() {
		getContentPane().setLayout(null);
		this.setBounds(30, 30, 870, 540);
		
		Warehouse warehouse = MainGui.MainGuiInstance().warehouse;
		
		chckbxNewCheckBox = new JCheckBox("Mo\u017Ce przechowywa\u0107 \u017Cywno\u015B\u0107 w wyizolowanym pomieszczeniu - mi\u0119so");
		chckbxNewCheckBox.setBounds(6, 7, 437, 23);
		getContentPane().add(chckbxNewCheckBox);
		
		chckbxIstniejeMoliwoWyczyszczenia = new JCheckBox("Istnieje mo\u017Cliwo\u015B\u0107 wyczyszczenia w ka\u017Cdej chwili - mi\u0119so");
		chckbxIstniejeMoliwoWyczyszczenia.setBounds(6, 33, 366, 23);
		getContentPane().add(chckbxIstniejeMoliwoWyczyszczenia);
		
		chckbxJestPrzewidzianyProgram = new JCheckBox("Jest przewidziany program na wypadek ska\u017Cenia - mi\u0119so");
		chckbxJestPrzewidzianyProgram.setBounds(6, 59, 366, 23);
		getContentPane().add(chckbxJestPrzewidzianyProgram);
		
		chckbxJestZabezpieczonypoty = new JCheckBox("Jest zabezpieczony (p\u0142oty, monitoring) - mi\u0119so");
		chckbxJestZabezpieczonypoty.setBounds(6, 85, 366, 23);
		getContentPane().add(chckbxJestZabezpieczonypoty);
		
		chckbxPosiadaPosiadaPrzestrze = new JCheckBox("Posiada posiada przestrze\u0144 na alkohol");
		chckbxPosiadaPosiadaPrzestrze.setBounds(6, 111, 366, 23);
		getContentPane().add(chckbxPosiadaPosiadaPrzestrze);
		
		chckbxPosiadaLicencjeNa = new JCheckBox("Posiada licencje na sprzeda\u017C alkoholu");
		chckbxPosiadaLicencjeNa.setBounds(6, 137, 366, 23);
		getContentPane().add(chckbxPosiadaLicencjeNa);
		
		chckbxPosiadaFederalEmploy = new JCheckBox("Posiada Federal Employ Identifier - alkochol");
		chckbxPosiadaFederalEmploy.setBounds(6, 163, 366, 23);
		getContentPane().add(chckbxPosiadaFederalEmploy);
		
		chckbxPosiadaTtbPermit = new JCheckBox("Posiada TTB Permit - alkohol");
		chckbxPosiadaTtbPermit.setBounds(6, 189, 366, 23);
		getContentPane().add(chckbxPosiadaTtbPermit);
		
		chckbxCzyJestWykupiona = new JCheckBox("Czy jest wykupiona licencja na sprzeda\u017C materia\u0142\u00F3w pirotechnicznych ATF");
		chckbxCzyJestWykupiona.setBounds(6, 215, 386, 23);
		getContentPane().add(chckbxCzyJestWykupiona);
		
		chckbxCzyMagazynZawiera = new JCheckBox("Czy magazyn zawiera urz\u0105dzenia wyzwalaj\u0105ce iskr");
		chckbxCzyMagazynZawiera.setBounds(6, 241, 366, 23);
		getContentPane().add(chckbxCzyMagazynZawiera);
		
		chckbxCzyOwietlenieElektryczne = new JCheckBox("Czy o\u015Bwietlenie elektryczne spe\u0142nia standardy NEC");
		chckbxCzyOwietlenieElektryczne.setBounds(6, 267, 366, 23);
		getContentPane().add(chckbxCzyOwietlenieElektryczne);
		
		chckbxCzyWszystkieWczniki = new JCheckBox("Czy wszystkie w\u0142\u0105czniki s\u0105 zlokalizowane poza magazynem");
		chckbxCzyWszystkieWczniki.setBounds(6, 293, 366, 23);
		getContentPane().add(chckbxCzyWszystkieWczniki);
		
		chckbxCzyDokumentacjaSieci = new JCheckBox("Czy dokumentacja sieci energetycznej jest dost\u0119pna do inspekcji ATF");
		chckbxCzyDokumentacjaSieci.setBounds(6, 319, 366, 23);
		getContentPane().add(chckbxCzyDokumentacjaSieci);
		
		chckbxCzyIstniejeCigy = new JCheckBox("Czy istnieje ci\u0105g\u0142y monitoring temperatury (ryby)");
		chckbxCzyIstniejeCigy.setBounds(6, 345, 366, 23);
		getContentPane().add(chckbxCzyIstniejeCigy);
		
		chckbxRybyDzikiePosiadaj = new JCheckBox(" Ryby dzikie posiadaj\u0105 certyfikaty pochodzenia");
		chckbxRybyDzikiePosiadaj.setBounds(6, 371, 366, 23);
		getContentPane().add(chckbxRybyDzikiePosiadaj);
		
		chckbxDrbIJajka = new JCheckBox("Dr\u00F3b i jajka posiada certyfikaty NPIP");
		chckbxDrbIJajka.setBounds(6, 397, 366, 23);
		getContentPane().add(chckbxDrbIJajka);
		
		chckbxProduktyZwierzcePochodzenia = new JCheckBox("Produkty zwierz\u0119ce pochodzenia zagranicznego s\u0105 odpowiednio certyfikowane");
		chckbxProduktyZwierzcePochodzenia.setBounds(6, 423, 449, 23);
		getContentPane().add(chckbxProduktyZwierzcePochodzenia);
		
		chckbxCzyMateriayPirotechniczne = new JCheckBox("Czy materia\u0142y pirotechniczne s\u0105 dobrze oznaczone");
		chckbxCzyMateriayPirotechniczne.setBounds(6, 445, 449, 23);
		getContentPane().add(chckbxCzyMateriayPirotechniczne);
		
		JButton btnNewButton = new JButton("Zweryfikuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
				MainGui.MainGuiInstance().warehouse.ClearAllChecks();
				SetWarehouse();
				MainGui.MainGuiInstance().VerifyWarehouse();
				ShowRestrictedProducts();
			}
		});
		btnNewButton.setBounds(6, 475, 366, 23);
		getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(495, 33, 345, 268);
		getContentPane().add(textArea);
		
		JLabel lblZakazaneProdukty = new JLabel("Zakazane produkty");
		lblZakazaneProdukty.setBounds(495, 11, 151, 14);
		getContentPane().add(lblZakazaneProdukty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(495, 314, 349, 184);
		getContentPane().add(scrollPane);
		
		textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea_1));
		
		
		System.setOut(printStream);
		
		SetFromWarehouse(warehouse);
	}
	
	public void ShowRestrictedProducts()
	{
		StringBuilder sb = new StringBuilder();
		for(ProductName name : MainGui.MainGuiInstance().warehouse.whatCannotBeSold)
		{
			sb.append(name.toString());
			sb.append("\r\n");
		}
		textArea.setText(sb.toString());
	}
}
