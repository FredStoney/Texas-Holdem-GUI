

public class CommonCards {


	private Card[] commonCards= new Card[5];

	public Card[] getCommon(){
		return commonCards;
	}

	public void addCard(Card c,int i) {
		commonCards[i]=c;
	}
	public String toString() {
		String s = "CommonCards:  | ";
		for (Card c: commonCards) {
			if(c!=null) {
				s=s+c.toString() + " | ";
			}
			else s=s+"null" + " | ";
		}
		return s;

	}

	public CommonCards copy() {
		CommonCards t=new CommonCards() ;
		for(int i=0;i<5;i++) {
			if(commonCards[i]==null) {
				t.commonCards[i]=null;
			}
			else
				t.commonCards[i]=commonCards[i].copy();
		}
		return t;
	}

}
