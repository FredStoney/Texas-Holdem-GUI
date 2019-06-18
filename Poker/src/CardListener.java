
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class CardListener implements MouseListener {
	//uses rectangles for button clicking
	
	PokerTable pt;
	ArrayList<Rectangle> rects=new ArrayList<Rectangle>();
	Rectangle common;
	Rectangle play;
	Rectangle clear;
	Rectangle down;
	Rectangle up;
	Boolean old;
	ProbSim copy;
	double [] wins; // 
	public CardListener(PokerTable pt) {
		this.pt=pt;
		old=true;
		copy=null;
		wins=new double[pt.getProbSim().getNumber()];
		System.out.println(pt.getProbSim().getNumber());
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		updateRect();
		
		for(int i=0;i<rects.size();i++) {
			if(rects.get(i).contains(arg0.getPoint())) {
				//System.out.println("Card"+(i+1) + "clicked");
				new CardChanger(pt,i);
				old=true;
			}
		}
		if(common.contains(arg0.getPoint())) {
			new CardChanger(pt,-1);
			old=true;
		}
		
		//contains the code for running the probability simulations
		if(play.contains(arg0.getPoint())){
			
			//checks if the probsim needs to be updated
			if(old) {
				 copy=pt.getProbSim().copy();
				 System.out.println(copy.getDeck().getDeck().size());
				old=false;
			}
			
			wins=new double[pt.getProbSim().numberOfPlayers];
			
			TimerTask task = new TimerTask() {
				//long t0=System.currentTimeMillis();
				int n=0;
				
			      @Override
				public void run() {
			    	  
			    	  //if (System.currentTimeMillis() - t0 > 10000) {
			    	  if(n>15000) {
		                  cancel();
		                  pt.repaint();
		              
		      			pt.setWins(wins);
		      			wins=new double[pt.getProbSim().numberOfPlayers];
		              } else {
		                 
		              n++;
			    	  pt.setProbSim(copy);
						System.out.println(pt.getProbSim().getDeck().getDeck().size());
						pt.getProbSim().start();
						
						//System.out.println("TEST" +pt.getProbSim().getCommon());

						
						
						int s=pt.getProbSim().getPlayers().findWinner();
						if(s!=-1) {
							System.out.println(" winner " + s);
							wins[s-1]++;
						}
				if(n%500==0) {
			       pt.repaint();
			       pt.setWins(wins);
				}
			      }
			      }
			    };
			    
			    Timer timer = new Timer();
			    
			    timer.scheduleAtFixedRate(task, 0,1);
			    
			   
			
			
			
		}
		if(clear.contains(arg0.getPoint())) {
			old=true;
			pt.setProbSim(new ProbSim(pt.getProbSim().getNumber()));
			pt.repaint();
		}
		int nop=pt.getProbSim().numberOfPlayers;
		
		if (up.contains(arg0.getPoint())) {
			if (nop<8) {
				pt.setProbSim(new ProbSim(nop+1));
				
				old=true;
				System.out.println("player up");
			}
			
		}
		else if (down.contains(arg0.getPoint())) {
			if (nop>2) {
				pt.setProbSim(new ProbSim(nop-1));
				
				old=true;
				System.out.println("player down");
			}
		}
		pt.repaint();
	}

	
	private void updateRect() {
		rects.clear();
		int nop=pt.getProbSim().numberOfPlayers;
		Rectangle r0=new Rectangle(pt.getWidth()*2/20, pt.getHeight()*5/(20), pt.getWidth()/20,pt.getHeight()/7);
		Rectangle r1=new Rectangle(pt.getWidth()*3/20, pt.getHeight()*5/(20), pt.getWidth()/20,pt.getHeight()/7);
		Rectangle r2=new Rectangle(pt.getWidth()*2/20, pt.getHeight()*10/(20), pt.getWidth()/20,pt.getHeight()/7);
		Rectangle r3=new Rectangle(pt.getWidth()*3/20, pt.getHeight()*10/(20), pt.getWidth()/20,pt.getHeight()/7);
		rects.add(r0);
		rects.add(r1);
		rects.add(r2);
		rects.add(r3);
		if (nop>=3) {
			Rectangle r4=new Rectangle(pt.getWidth()*5/20, pt.getHeight()*13/(20), pt.getWidth()/20,pt.getHeight()/7);
			Rectangle r5=new Rectangle(pt.getWidth()*6/20, pt.getHeight()*13/(20), pt.getWidth()/20,pt.getHeight()/7);
			rects.add(r4);
			rects.add(r5);
		}
		if (nop>=4) {
		Rectangle r6=new Rectangle(pt.getWidth()*9/20, pt.getHeight()*13/(20), pt.getWidth()/20,pt.getHeight()/7);
		Rectangle r7=new Rectangle(pt.getWidth()*10/20, pt.getHeight()*13/(20), pt.getWidth()/20,pt.getHeight()/7);
		rects.add(r6);
		rects.add(r7);
		}
		if (nop>=5) {
		Rectangle r8=new Rectangle(pt.getWidth()*13/20, pt.getHeight()*13/(20), pt.getWidth()/20,pt.getHeight()/7);
		Rectangle r9=new Rectangle(pt.getWidth()*14/20, pt.getHeight()*13/(20), pt.getWidth()/20,pt.getHeight()/7);
		rects.add(r8);
		rects.add(r9);
		}
		if(nop>=6) {
			Rectangle r10=new Rectangle(pt.getWidth()*16/20, pt.getHeight()*11/(20), pt.getWidth()/20,pt.getHeight()/7);
			Rectangle r11=new Rectangle(pt.getWidth()*17/20, pt.getHeight()*11/(20), pt.getWidth()/20,pt.getHeight()/7);
			rects.add(r10);
			rects.add(r11);
		}
		if(nop>=7) {
			Rectangle r12=new Rectangle(pt.getWidth()*16/20, pt.getHeight()*7/(20), pt.getWidth()/20,pt.getHeight()/7);
			Rectangle r13=new Rectangle(pt.getWidth()*17/20, pt.getHeight()*7/(20), pt.getWidth()/20,pt.getHeight()/7);
			rects.add(r12);
			rects.add(r13);
		}
		if (nop==8) {
			Rectangle r14=new Rectangle(pt.getWidth()*13/20, pt.getHeight()*4/(20), pt.getWidth()/20,pt.getHeight()/7);
			Rectangle r15=new Rectangle(pt.getWidth()*14/20, pt.getHeight()*4/(20), pt.getWidth()/20,pt.getHeight()/7);
			rects.add(r14);
			rects.add(r15);
		}
		
		
		common=new Rectangle(pt.getWidth()*8/20,pt.getHeight()*2/5,pt.getWidth()*5/20,pt.getHeight()/7);
		clear=new Rectangle(pt.getWidth()*9/20, pt.getHeight()/50, pt.getWidth()/8, pt.getHeight()/7);
		play=new Rectangle(pt.getWidth()*9/20, pt.getHeight()/6, pt.getWidth()/8, pt.getHeight()/7);
		up  = new Rectangle(pt.getWidth()* 13/20, pt.getHeight()/50, pt.getWidth()/8, pt.getHeight()/7);
		down = new Rectangle(pt.getWidth()* 16/20, pt.getHeight()/50, pt.getWidth()/8, pt.getHeight()/7);
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
