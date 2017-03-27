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
	public MainGui() {
		getContentPane().setLayout(null);
		
		JButton btnHello = new JButton("Hello");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
		            // load up the knowledge base
			        KieServices ks = KieServices.Factory.get();
		    	    KieContainer kContainer = ks.getKieClasspathContainer();
		        	KieSession kSession = kContainer.newKieSession("ksession-rules");

		            // go !
		            Message message = new Message();
		            message.setMessage("Hello World");
		            message.setStatus(Message.HELLO);
		            kSession.insert(message);
		            kSession.fireAllRules();
		        } catch (Throwable t) {
		            t.printStackTrace();
		        }
			}
		});
		btnHello.setBounds(10, 11, 89, 23);
		getContentPane().add(btnHello);
		
		
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