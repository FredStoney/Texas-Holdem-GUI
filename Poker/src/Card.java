
public class Card implements Comparable<Card>{

	public enum VALUE{
		Two(2),
		Three(3),
		Four(4),
		Five(5),
		Six(6),
		Seven(7),
		Eight(8),
		Nine(9),
		Ten(10),
		Jack(11),
		Queen(12),
		King(13),
		Ace(14);
		
		private int val;

		private VALUE(int v) {
			this.val=v;
		}
		
		public int getValue() {
			return val;
		}
	}

	public enum SUIT{

		Spades,
		Hearts,
		Diamonds,
		Clubs

	}
	
	private SUIT suit;
	private VALUE value;

	public SUIT getSuit() {
		return suit;
	}
	public VALUE getVALUE() {
		return value;
	}
	public Card(VALUE v , SUIT s) {
		this.value=v;
		this.suit=s;
	}
	public String toString() {
		return value + "_of_ " + suit;
	}

	
	public String stringer() {
	//returns a string that is helpful in loading the cards image
		String svalue;
		if(this.value.getValue()<=10) {

			svalue=value.getValue()+"";
		}
		else {
			svalue=value+"";
		}
		String ssuit=suit+"";
		return svalue.toLowerCase() + "_of_" + ssuit.toLowerCase();
	}
	public String getImg() {
		return  this.stringer() + ".png";
	}

	//compares values of the cards
	@Override
	public int compareTo(Card o) {


		int n=getVALUE().val;
		int n2=(o.getVALUE().val);
		if(n>n2)return 1;
		if(n2>n)return -1;

		return 0;
	}
	//equal if suit and value are the same
	public boolean equals(Object o) {
		if(o instanceof Card) {
			Card c = (Card ) o;
			if(c.getVALUE().equals(this.getVALUE())) {
				if(c.getSuit().equals(this.getSuit())) {
					return true;
				}
			}
		}
		return false;

	}
	public Card copy() {
		Card c=new Card(this.value,this.suit);
		return c;
	}

}
