import java.util.ArrayList;
import java.util.Collections;


public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>(); 

	//true fills deck with cards
	public Deck(boolean full) {
		if(full) {
			for (Card.SUIT s: Card.SUIT.values()) {
				for ( Card.VALUE v: Card.VALUE.values()) {
					deck.add(new Card(v,s));
				}

			}
		}
	}


	public void shuffle() {
		Collections.shuffle(deck);
	}
	public void dealTopTwo(Hand h) {

		if(h.getPocket()[0]==null) {
			h.getPocket()[0]=deck.get(0);
			deck.remove(0);
		}
		if(h.getPocket()[1]==null) {
			h.getPocket()[1]=deck.get(0);
			deck.remove(0);
		}


	}
	public void deal(Players p) {
		for (Hand h : p.getPlayers()) {
			dealTopTwo(h);
		}
	}
	public void dealCommon(CommonCards c,int j) {
		for (int i=0; i<j;i++) {
			if(c.getCommon()[i]==null) {
				c.getCommon()[i]=deck.get(0);
				deck.remove(0);
			}
		}
	}
	public ArrayList<Card> dealcards( int j) {
		ArrayList<Card> dealt=new ArrayList<Card>();
		for(int i=0;i<j;i++) {
			dealt.add(deck.get(0));
			deck.remove(0);
		}
		return dealt;
	}

	public boolean dealCard(Card c,Hand h) {
		if(deck.contains(c)) {
			if(c==null) {
				try {
					throw new Exception();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("deck has card");
			
			h.addPocket(c);
			deck.remove(c);
			return true;
		}
		return false;
	}
	public void dealCommonCard(CommonCards com,Card car,int i) {
		if(this.deck.contains(car)) {
			com.addCard(car, i);
			deck.remove(car);

		}
	}
	public ArrayList<Card> getDeck(){
		return deck;
	}
	public Deck copy() {
		Deck copy=new Deck(false);

		for(Card e: deck) {
			if(e!=null) {
				copy.getDeck().add(e);
			}
		}
		return copy;
	}
}

