import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;




public class CardChanger extends JFrame implements ActionListener{
		
	// Gives the user the ability to change cards on the poker table. 
	// Opens a JFrame to choose a suit and value, and a button "finished" to finalize the choice
	// the integer "location" is used to indicate which card was clicked that needs to be changed 
	// and is passed in to the cardchanger object as it is created.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Card.SUIT> suits;
	private JComboBox<Card.VALUE> vals;
	private JButton finished;
	PokerTable pt;
	int location;
	
	public CardChanger(PokerTable pt,int loc) {
		Font tr = new Font("TimesRoman", Font.PLAIN, 30);
		suits=new JComboBox<Card.SUIT>(Card.SUIT.values());
		suits.setFont(tr);
		
		vals = new JComboBox<Card.VALUE>(Card.VALUE.values());
		this.location=loc;
		vals.setFont(tr);
		
		finished = new JButton("Change");
		finished.setFont(tr);
		finished.addActionListener(this);
		
		this.pt=pt;
		
		this.setLocation(pt.getWidth()/2, pt.getHeight()/2);
		this.setSize(new Dimension(pt.getWidth()/6,pt.getHeight()/5));
		this.setLayout(new BorderLayout());
		this.add(suits,BorderLayout.EAST);
		this.add(vals,BorderLayout.WEST);
		this.add(finished,BorderLayout.CENTER);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(finished.getActionCommand())) {
			
			System.out.println("Button Clicked");
			Card c=new Card((Card.VALUE)vals.getSelectedItem(),(Card.SUIT)suits.getSelectedItem());
			
			if(location>=0) {
			//deal the card
			
			pt.getProbSim().getDeck().dealCard(c, pt.getProbSim().getPlayers().getPlayer(location/2));
			
			System.out.println(pt.getProbSim().getDeck().getDeck());
			System.out.println(c);
			System.out.println(pt.getProbSim().getDeck().getDeck().contains(c));
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			
			
			else {
				
				//deal the next commoncard
				for(int i=0;i<5;i++) {
					if(pt.getProbSim().common.getCommon()[i]==null) {
					pt.getProbSim().getDeck().dealCommonCard(pt.getProbSim().getCommon(), c, i);
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
					break;
					}
				}
				
			
			}
			System.out.println(pt.getProbSim().getDeck().getDeck().size());
			pt.repaint();
			
		}
		
	}
	
	
	
	
}
