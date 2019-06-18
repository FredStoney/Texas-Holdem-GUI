import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class PokerTable extends JPanel {

	BufferedImage pokerTableImage;
	
	ProbSim ps;
	double wins[];
	
	public PokerTable(ProbSim ps) {
		
		
		loadImage();
		this.ps=ps;
		this.addMouseListener(new CardListener(this));
		//this.addMouseListener(new PlayerListener(this));
		
		
	}
	
	
	public void loadImage() {
		try {
			//System.out.println("PokerTable.jpg");
			pokerTableImage=(BufferedImage) imgLoader("PokerTable.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ProbSim getProbSim() {
		return ps;
	}
	public void setProbSim(ProbSim ps) {
		this.ps=ps.copy();
	}

	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2d = (Graphics2D) g;
		 
	        if(pokerTableImage != null){
	        	
	            g2d.drawImage(pokerTableImage, 0, 0, getWidth(),getHeight(),this);
	            ArrayList<Card> a= this.ps.getPlayers().getPockets();
	            for(int i=0;i<a.size();i++) {
	            	drawCard(i,a.get(i),g);
	            }
	            
	            drawCommon(g2d);
	            drawPlay(g2d);
	            drawClear(g2d);
	            drawPlayerButtons(g2d);
	            drawWins(g2d);
	 }
}
	 
	 public void drawCard(int cardnumber,Card c,Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 BufferedImage cardImage;
		 
		try {
			if(c!=null) {
			cardImage = (BufferedImage) imgLoader(c.getImg());
			}
			else cardImage = (BufferedImage) imgLoader("CardBack.png");
			
			if(cardnumber==0) {
				//this.add(j,getWidth()*2/20,getHeight()*5/20);
				g2d.drawImage(cardImage, getWidth()*2/20, getHeight()*5/20, getWidth()/20,getHeight()/7,this);
				
				//g2d.fillRect( getWidth()*2/20, getHeight()*5/(20), getWidth()/20,getHeight()/7);
			}
			else if(cardnumber==1) {
				g2d.drawImage(cardImage, (int) (getWidth()*3/20), getHeight()*5/20, getWidth()/20,getHeight()/7,this);
				
			}
			else if(cardnumber==2) {
				g2d.drawImage(cardImage, getWidth()*2/20, getHeight()/(2), getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==3) {
				g2d.drawImage(cardImage, (int) (getWidth()*3/20), getHeight()/(2), getWidth()/20,getHeight()/7,this);
			}
			
			else if(cardnumber==4) {
				g2d.drawImage(cardImage, (int) (getWidth()*5/20), getHeight()*13/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==5) {
				g2d.drawImage(cardImage, (int) (getWidth()*6/20), getHeight()*13/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==6) {
				g2d.drawImage(cardImage, (int) (getWidth()*9/20), getHeight()*13/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==7) {
				g2d.drawImage(cardImage, (int) (getWidth()/2), getHeight()*13/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==8) {
				g2d.drawImage(cardImage, (int) (getWidth()*13/20), getHeight()*13/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==9) {
				g2d.drawImage(cardImage, (int) (getWidth()*14/20), getHeight()*13/20, getWidth()/20,getHeight()/7,this);
				
			}
			else if(cardnumber==10) {
				g2d.drawImage(cardImage, (int) (getWidth()*16/20), getHeight()*11/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==11) {
				g2d.drawImage(cardImage, (int) (getWidth()*17/20), getHeight()*11/20, getWidth()/20,getHeight()/7,this);
				
			}
			else if(cardnumber==12) {
				g2d.drawImage(cardImage, (int) (getWidth()*16/20), getHeight()*7/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==13) {
				g2d.drawImage(cardImage, (int) (getWidth()*17/20), getHeight()*7/20, getWidth()/20,getHeight()/7,this);
				
			}
			else if(cardnumber==14) {
				g2d.drawImage(cardImage, (int) (getWidth()*13/20), getHeight()*4/20, getWidth()/20,getHeight()/7,this);
			}
			else if(cardnumber==15) {
				g2d.drawImage(cardImage, (int) (getWidth()*14/20), getHeight()*4/20, getWidth()/20,getHeight()/7,this);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		
		 
		 
	 }
	 
	 public void drawCommon(Graphics2D g2d) {
		 
		 Card[] l=ps.getCommon().getCommon();
		// System.out.println("Test drawcommon "+ ps.getCommon());
		 for(int i=0;i<l.length;i++) {
			 
			drawCommonCard(g2d,i,l[i]);
		 
		 }
		 
	 }
	 public void drawCommonCard(Graphics2D g2d,int i,Card c) {
		 BufferedImage bf1;
		 try {
			 if(c!=null) {
		bf1=(BufferedImage) imgLoader(c.getImg());
			 }
			 else bf1=(BufferedImage)imgLoader("CardBack.png");
		 if(i==4) {
			 g2d.drawImage(bf1,getWidth()*12/20,getHeight()*2/5,getWidth()/20,getHeight()/7,this);
			
		 }
		 else if(i==3) {
			 g2d.drawImage(bf1,getWidth()*11/20,getHeight()*2/5,getWidth()/20,getHeight()/7,this);
		 }
		 else if(i==2) {
			 g2d.drawImage(bf1,getWidth()/2,getHeight()*2/5,getWidth()/20,getHeight()/7,this);
			 
		 }
		 else if(i==1) {
			 g2d.drawImage(bf1,getWidth()*9/20,getHeight()*2/5,getWidth()/20,getHeight()/7,this);
		 }
		 else if(i==0) {
			 
			 g2d.drawImage(bf1,getWidth()*8/20,getHeight()*2/5,getWidth()/20,getHeight()/7,this);
		 }
		 
		 }catch(IOException e) {
			 
		 }
	 }
	 
	 public static Image imgLoader(String s) throws IOException {
			s="Cardimgs/"+s;
			//System.out.println(s);
			return ImageIO.read(new File(s));
			
			
		}

	 
	 
	




public void drawPlay(Graphics g2d) {
	g2d.fillRect(getWidth()*9/20, getHeight()/6, getWidth()/8, getHeight()/7);
	g2d.setColor(Color.white);
	g2d.setFont(new Font("TimesRoman", Font.PLAIN, getWidth()/50));
	g2d.drawString("Play",getWidth()*10/20, getHeight()/4);
}


public void drawClear(Graphics g2d) {
	g2d.setColor(Color.CYAN);
	g2d.fillRect(getWidth()*9/20, getHeight()/50, getWidth()/8, getHeight()/7);
	g2d.setColor(Color.black);
	g2d.setFont(new Font("TimesRoman", Font.PLAIN, getWidth()/50));
	g2d.drawString("Clear",getWidth()*10/20, getHeight()/10);
}

public void drawPlayerButtons(Graphics g2d) {
	g2d.setColor(Color.white);
	g2d.fillRect(getWidth()*13/20, getHeight()/50, getWidth()/8, getHeight()/7);
	g2d.fillRect(getWidth()* 16/20, getHeight()/50, getWidth()/8, getHeight()/7);
	g2d.setColor(Color.black);
	g2d.drawString("Player up", getWidth()*27/40, getHeight()*5/50);
	g2d.drawString("Player down", getWidth()*65/80, getHeight()*5/50);
	
}

public void  setWins(double [] wins) {
	this.wins=wins;
}
public void drawWins(Graphics2D g2d) {
	// TODO Auto-generated method stub
	int n=0;
	if(wins!=null) {
		
		for (int i = 0 ; i < wins.length ; i ++) {
			n+= wins[i];
		}
	
	for (int i = 0 ; i < wins.length ; i ++) {
		
		switch (i) {
		
		case 0:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()/20, getHeight()/7);
			break;
		case 1:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()/20, getHeight()*6/7);
			break;
		case 2:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()*5/20, getHeight()*27 /28);
			break;
		case 3:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()*10/20, getHeight()*27/28);
			break;
		case 4:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()*15/20, getHeight()*27/28);
			break;
		case 5:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()*19/20, getHeight()*6/7);
			break;
		case 6:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()*19/20, getHeight()*2/7);
			break;
		case 7:
			g2d.drawString(String.valueOf(Math.round(wins[i]/n*100.0))+"%", getWidth()*15/20, getHeight()*2/7);
			break;
	
		}
		//Math.round(wins[i]/n*100.0)/100.0;
	}
	}
}
	
}
