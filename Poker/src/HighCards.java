import java.util.ArrayList;
import java.util.Collections;

public class HighCards implements Comparable<HighCards> {
	private ArrayList<Card> highcards;

	public HighCards(ArrayList<Card> h) {
		highcards=h;
	}
	public HighCards() {
		highcards = new ArrayList<Card>();
	}
	public void add(Card c) {
		highcards.add(c);
	}

	@Override
	public int compareTo(HighCards o) {


		Collections.sort(highcards);
		Collections.sort(o.getHigh());
		for (int i = 0; i < highcards.size(); i++) {
			if(i==o.getHigh().size())break;
			int n=highcards.get(i).compareTo(o.getHigh().get(i));
			if(n!=0) {
				return n;
			}
		}

		return 0;
	}
	public void clear() {
		highcards.clear();
	}
	public String toString() {
		String s="Highcards: ";
		for (Card c:highcards) {
			s=s+c.toString()+" |";
		}
		return s;
	}
	public ArrayList<Card> getHigh(){
		return highcards;
	}
}
