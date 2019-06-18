import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI {

	JFrame jf;
	ProbSim ps;
	PokerTable pt;
	Image tableImg;
	
	public GUI() {
		
		jf=new JFrame();
		ProbSim ps=new ProbSim(4);
		pt=new PokerTable(ps);
		
		jf.setLocation(0,0);
		jf.setSize(new Dimension(3000,1200));
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		jf.add(pt,BorderLayout.CENTER);
		
		pt.repaint();
		jf.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		//System.out.println(" Click on the Cards to set up known cards, click run and the propbability\n of winning for each player is printed in the consol");
		new GUI();
	}
	
	
	public PokerTable getPoker() {
		return pt;
	}
	public void repaint() {
		pt.repaint();
	}
	
}
