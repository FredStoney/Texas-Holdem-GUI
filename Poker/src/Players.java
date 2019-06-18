import java.util.ArrayList;
import java.util.Collections;

public class Players {
 private ArrayList <Hand> players = new ArrayList <Hand>();
 
 public void namePlayers() {
	 for(int i=0;i<players.size();i++) {
		 players.get(i).name((i+1));
	 }
 }
 public void addHand(Hand h) {
	 players.add(h);
 }
 public ArrayList<Hand> getPlayers() {
	 return players;
 }
 
 public Hand getPlayer(int i) {
	 if (i<=players.size())return players.get(i);
	 return null;
 }
 public Players(int j) {
	 for (int i=0;i<j;i++) {
		 players.add(new Hand());
	 }
 }
 
 public String toString() {
	 String s = "Players: ";
	 for (Hand h: players) {
	 s=s+ h.toString();
	 }
	 return s;
 }
 public void print() {
	 for (int i=0;i<players.size();i++) {
		 players.get(i).printPocket();
		 }
 }
 public void addCommon(CommonCards c) {
	 for (Hand h:players) {
		 h.setCommon(c);
	 }
 }
 public void evaluate() {
	
	 for (int i=0;i<players.size();i++) {
		Hand hand5= players.get(i);
		
		 System.out.println(hand5.getName()+": "+hand5.evaluate());
		
		 
	 }
	 
 }
 
 public int findWinner() {
	 
	 Players p=this.copy();
	 //System.out.println(p);
	 ArrayList<Hand>copy=p.getPlayers();
	 Collections.sort(copy);
	 
	 Hand hand1=copy.get(copy.size()-1);
	 Hand hand2=copy.get(copy.size()-2);
	 
	 
	 if(hand1.compareTo(hand2)==0) {
		 String s="Split Pot : " + hand1.getName() + " " +hand2.getName();
		 for ( int i=2;i<copy.size();i++) {
				
			 if(copy.get(copy.size()-i).compareTo(copy.get(copy.size()-i-1))==0) {
				 s=s+ " "+copy.get(copy.size()-i-1).getName();
			 }
			 else break;
		 }
		 System.out.println(s);
		 return -1;
	 }
	 else {
		 System.out.println(" WINNER {" +copy.get(copy.size()-1).getName()+"} WINNER ");
		 return copy.get(copy.size()-1).getName();
	 }
 }
 public void profile() {
		for (Hand h:players) {
			h.profile();
		}
	}
public ArrayList<Card> getPockets(){
	 ArrayList<Card> pockets= new ArrayList<Card>();

	 for(Hand h:players) {
		
		 pockets.add(h.getPocket()[0]);
		 pockets.add(h.getPocket()[1]);
		 
	 }
	 return pockets;
 }

 public void createHands() {
	 for(Hand h:players) {
		h.createHand();
	 }
 }
 
 public Players copy() {
	 Players play=new Players(0);
	 for(Hand e:players) {
		 play.addHand(e.copy());
		 
	 }
	 play.namePlayers();
	 return play;
 }
}
