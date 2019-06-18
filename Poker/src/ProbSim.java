

public class ProbSim {

	Players players;
	CommonCards common;
	Deck deck;
	int numberOfPlayers;





	public ProbSim(int numberOfPlayers) {

		this.numberOfPlayers=numberOfPlayers;
		texas(numberOfPlayers);


	}


	private void texas(int y) {
		players = new Players(y);
		players.namePlayers();
		deck= new Deck(true);

		common = new CommonCards();




	}
	public Players getPlayers() {
		return players;
	}
	public CommonCards getCommon() {
		return common;
	}
	public Deck getDeck() {
		return deck;
	}
	public void start() {



		deck.shuffle();
		deck.deal(players);
		deck.dealCommon(common, 5);

		players.addCommon(common);
		players.createHands();
		players.print();

		System.out.println(common.toString());
		System.out.println();
		
		players.evaluate();
		players.profile();
		//players.findWinner();
		System.out.println(deck.getDeck().size());


	}


	public ProbSim copy() {
		ProbSim ps=new ProbSim(this.numberOfPlayers);
		ps.setDeck(this.deck.copy());	
		ps.setPlayers(this.players.copy());
		ps.setCommon(this.common.copy());
		return ps;
	}
	public int getNumber() {
		return this.numberOfPlayers;
	}
	public void setPlayers(Players p) {
		players=p;

	}
	public void setDeck(Deck d) {
		deck=d;
	}
	public void setCommon(CommonCards c) {
		common=c;
	}
}
