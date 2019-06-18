import java.util.ArrayList;
import java.util.Collections;




public class Hand implements Comparable<Hand>{
	public enum handVal {
		HighCard,
		Pair,
		TwoPair,
		ThreeOfAKind,
		Straight,
		Flush,
		FullHouse,
		FourOfAKind,
		StraightFlush
	}
	private handVal val;
	private HighCards highcards;
	private Card.VALUE primary;
	private Card.VALUE secondary;

	private int name; 
	private ArrayList<Card> hand;//all 7 cards

	private  Card[] pocket;//2 hidden cards
	private CommonCards common; // 5 shared cards




	public Hand() {
		hand=new ArrayList<Card>();
		primary=null;
		secondary=null;
		highcards=new HighCards();
		common=new CommonCards();
		pocket = new Card[2];

	}

	public handVal evaluate() {
		val=evaluate(hand);
		return val;
	}

	public ArrayList<Card> getHand(){
		return hand;
	}
	public String toString() {
		String s= name+": " ;
		for (Card c: hand) {
			if(c!=null) {
				s=s + c.toString()+" | ";
			}
			else {
				s=s+ "null | ";
			}
		}

		return s;

	}
	public void add(Card c) {
		hand.add(c);
	}
	public void addAll(Hand h) {
		for (Card c: h.getHand()) {
			hand.add(c);
		}
	}
	public handVal evaluate(ArrayList<Card> list) {
		if(checkStraightFlush(list)) return handVal.StraightFlush;
		else if(checkFours(list)) return handVal.FourOfAKind;
		else if(checkFullHouse(list))return handVal.FullHouse;
		else if(checkFlush(list)!=null) return handVal.Flush;
		else if(checkStraight(list)) return handVal.Straight;
		else if(checkThrees(list)) return handVal.ThreeOfAKind;
		else if(checkTwoPair(list)) return handVal.TwoPair;
		else if(checkPair(list)) return handVal.Pair;
		else {highcards=new HighCards(highestx(5, list));
		return handVal.HighCard;
		}
	}
	public boolean checkPair(ArrayList <Card> list) {
		int count=0;
		for(Card.VALUE val:Card.VALUE.values()) {
			for(Card c:list) {
				if(c.getVALUE().equals(val)) {
					count++;
					if(count==2) {
						primary=val;
						highcards=new HighCards(highestx(3,list,val));
						return true;
					}
				}
			}
			count=0;
		}
		return false;
	}
	public boolean checkTwoPair(ArrayList <Card> list) {
		int count=0;
		int pairCount=0;
		
		for(Card.VALUE val:Card.VALUE.values()) {
			for(Card c:list) {
				if(c.getVALUE().equals(val)) {
					count++;
					if(count==2&&pairCount==0) {
						
						secondary=val;
						pairCount++;
						break;
					}
					if(count==2&&pairCount==1) {
						pairCount++;
						primary=val;
						break;
					}
					if(count==2&&pairCount==2) {
						
							
							secondary=primary;
							primary=val;
						
						
					}
					
				}
			}
			count=0;
		}
		if(pairCount>=2) {
			highcards=new HighCards(highestx(1,list,primary,secondary));
			//System.out.println(highcards.getHigh().get(0));
			return true;
		}
		secondary=null;
		primary=null;
		return false;

	}
	public boolean checkThrees(ArrayList <Card> list) {
		int count=0;
		for(Card.VALUE val:Card.VALUE.values()) {
			for(Card c:list) {
				if(c.getVALUE().equals(val)) {
					count++;
					if(count==3) {
						primary=val;
						highcards=new HighCards(highestx(2,list,val));
						return true;
					}
				}
			}
			count=0;
		}
		return false;
	}
	public boolean checkStraight(ArrayList <Card> list) {
		ArrayList<Card.VALUE> vals=new ArrayList<Card.VALUE>();
		for(Card c:list) {
			vals.add(c.getVALUE());
		}
		int count=0;
		for(Card.VALUE e:Card.VALUE.values()) {
			
				if(vals.contains(e)) {
				count++;
				
				if(count==4&& e.equals(Card.VALUE.Five)) {
					if(vals.contains(Card.VALUE.Ace)) {
						primary=e;
						
					}
				}
				if(count>=5) {
				primary=e;
				}
				
			}
				if(!(vals.contains(e))) {
					count=0;
				}
		}
		if(primary!=null) {
			return true;
		}
		return false;
	}
	public Card.SUIT checkFlush(ArrayList <Card> list) {
		int count=0;
		highcards.clear();
		for(Card.SUIT s:Card.SUIT.values()) {
			for (Card c:list) {
				if(c.getSuit().equals(s)) {
					count++;
					highcards.add(c);

				}
			}
			if(count>=5) {
				return s;
			}
			highcards.clear();
			count=0;
		}
		return null;
	}
	public boolean checkFullHouse(ArrayList <Card> list) {
		int count=0;

		for(Card.VALUE val:Card.VALUE.values()) {
			for (Card c: list) {
				if(c.getVALUE().equals(val)) {
					count++;

				}


			}
			if(count==3) {
				primary=val;
			}
			if(count==2) {
				if(secondary!=null) {
					secondary=val;
				}
				if(primary!=null&&val.compareTo(primary)<0) {
					primary=val;
				}
			}
			if(secondary!=null&&primary!=null) {
				return true;
			}
			count=0;
		}
		secondary=null;
		primary=null;
		return false;

	}
	public boolean checkFours(ArrayList <Card> list) {
		int count=0;
		for(Card.VALUE val:Card.VALUE.values()) {
			for(Card c:list) {
				if(c.getVALUE().equals(val)) {
					count++;
					if(count==4) {
						primary=val;
						highcards=new HighCards(highestx(1,list,val));
						return true;
					}
				}
			}
			count=0;
		}
		return false;
	}
	public boolean checkStraightFlush(ArrayList <Card> list) {
		Collections.sort(list);
		Card.SUIT suit=checkFlush(list);
		if(suit!=null) {
			int count=0;
		for(Card.VALUE  val : Card.VALUE.values()) {
			if(list.contains(new Card(val,suit))) {
				count++;
				
				if(count==4&& val.equals(Card.VALUE.Five)) {
					if(list.contains(new Card(Card.VALUE.Ace,suit))) {
						primary=val;
					}
				}
				if(count>=5) {
					primary=val;
				}
			}
			if(!(list.contains(new Card(val,suit)))) {
				count=0;
			}
			
			
		}
		}
		if(primary!=null) {
			return true;
		}
		return false;
	}
	
	//helper functions for getting the highcards of the hands
	public  ArrayList<Card> highestx(int x,ArrayList<Card> h) {
		ArrayList<Card> highest=new ArrayList<Card>();
		int size=h.size();
		Collections.sort(h);
		if(size<x) {
			return null;
		}
		for(int i=size-1;i>size-x-1;i--) {
			highest.add(h.get(i));
		}
		return highest;
	}
	public  ArrayList<Card> highestx(int x,ArrayList<Card> h,Card.VALUE val) {
		ArrayList<Card> highest=new ArrayList<Card>();
		int size=h.size();
		Collections.sort(h);
		if(size<x) {
			return null;
		}
		for(int i=size-1;i>size-x-1;i--) {
			if(h.get(i).getVALUE().equals(val)) {
				x++;

			}
			else highest.add(h.get(i));
		}
		return highest;
	}
	public  ArrayList<Card> highestx(int x,ArrayList<Card> h,Card.VALUE val,Card.VALUE val2) {
		ArrayList<Card> highest=new ArrayList<Card>();
		int size=h.size();
		Collections.sort(h);
		if(size<x) {
			return null;
		}
		for(int i=size-1;i>size-x-1;i--) {
			if(h.get(i).getVALUE().equals(val)||h.get(i).getVALUE().equals(val2)) {
				x++;

			}
			else highest.add(h.get(i));
		}
		return highest;
	}


	public Card.VALUE getPrimary() {
		return primary;
	}
	public Card.VALUE getSecondary(){
		return secondary;
	}
	public HighCards getHighcards(){
		return highcards;
	}
	public handVal getVal(){
		return val;
	}

	@Override
	public int compareTo(Hand h) {
		
		//return the better hand class (two-pair, flush etc.)
		int handvalue=this.getVal().compareTo(h.getVal());
		
		if(handvalue!=0) {
			return handvalue;
		}
		
		//if same class return which has the higher "primary" card
		if(this.getPrimary()!=null && h.getPrimary()!=null) {
			
			int p = this.getPrimary().getValue();
			int p2=(h.getPrimary().getValue());

			if(p>p2) {
				return 1;
			}
			if(p2>p) {
				return -1;
			}
		}
		// same for secondary
		if(this.getSecondary()!=null && h.getSecondary()!=null) {
			int s = this.getSecondary().getValue();
			int s2=(h.getSecondary().getValue());

			if(s>s2) {
				return 1;
			}
			if(s2>s) {
				return -1;
			}
		}
		// if still equal compare the highcards
		int high=getHighcards().compareTo(h.getHighcards());
	
		return high;

		

	}
	public void name(int s) {
		name =s;
	}
	public int getName() {
		return name;
	}
	public void profile() {
		System.out.println(this.name+"{");
		System.out.println(this.val);
		System.out.println(this.primary);
		System.out.println(this.secondary);
		System.out.println(this.highcards);
		System.out.println("}");
	}

	public CommonCards getCommon() {
		return common;
	}
	public void setCommon(CommonCards c) {
		common= c;
	}
	public void setPocket(Card[] c) {
		pocket=c;
	}
	public Card[] getPocket(){
		return pocket;
	}
	public void createHand() {
		if(!(hand.contains(pocket[0])))
		hand.add(pocket[0]);
		if(!(hand.contains(pocket[1])));
		hand.add(pocket[1]);
		hand.add(common.getCommon()[0]);
		hand.add(common.getCommon()[1]);
		hand.add(common.getCommon()[2]);
		hand.add(common.getCommon()[3]);
		hand.add(common.getCommon()[4]);

	}
	public void addPocket(Card c) {
		if(pocket[0]==null) {
			pocket[0]=c;
		}
		else if(pocket[1]==null) {
			pocket[1]=c;
		}
	}

	public Hand copy() {
		Hand h=new Hand();
		h.primary=this.primary;
		h.secondary=this.secondary;
		h.highcards=this.highcards;
		h.val=this.val;
		h.common= this.common.copy();
		h.pocket=copy(this.pocket);
		h.hand= copy(this.hand);
		return h;
	}
	private static Card [] copy(Card []a) {
		Card[] b=new Card[a.length];
		for(int i=0;i<a.length;i++) {
			if(a[i]==null) {
				b[i]=null;
			}
			else
				b[i]=a[i].copy();
		}
		return b;
	}
	private static ArrayList<Card> copy(ArrayList<Card > a){
		ArrayList<Card> b=new ArrayList<Card>();
		for(int i=0;i<a.size();i++) {

			b.add(a.get(0).copy());
		}
		return b;
	}
	public void printPocket() {
	System.out.println(name + " : " +pocket[0] + " | "+ pocket[1]);
		
	}

}
