package noob.pwr;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import java.util.ArrayList;
import java.util.List;


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
					Order order = new Order();
					Message message1 = new Message();
					message1.setMessage("Hello World");
		            message1.setStatus(Message.HELLO);
		            
		            Message message2 = new Message();
		            message2.setMessage("Hello World");
		            message2.setStatus(Message.HELLO);
		              
		            kSession.insert(message1);
		            kSession.insert(message2);
		            kSession.fireAllRules();

		        } catch (Throwable t) {
		            t.printStackTrace();
		        }
			}
		});
		btnHello.setBounds(10, 11, 89, 23);
		getContentPane().add(btnHello);
		
		
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
	
	public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
	
	
	


}