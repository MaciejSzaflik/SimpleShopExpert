package noob.pwr;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class MainGui extends JFrame {
	
	KieServices ks;
	KieContainer kContainer;
 	KieSession kSession;
	
	public MainGui() {
		getContentPane().setLayout(null);
		initializeSession();
		JButton btnHello = new JButton("Hello");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
			            Item[] items = {};
			            Item[] it2 = {new Item(ProductName.Bakery,-1)};
			            Order test1 = new Order(items);
			            Order test2 = new Order(it2);
			            
			            kSession.insert(test1);	
			            kSession.insert(test2);
			            kSession.fireAllRules();
			        } catch (Throwable t) {
			            t.printStackTrace();
			        }
			}
		});
		btnHello.setBounds(10, 11, 89, 23);
		getContentPane().add(btnHello);
		
		this.setBounds(0,0,200,200);
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