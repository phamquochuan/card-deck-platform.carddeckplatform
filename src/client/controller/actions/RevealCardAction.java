package client.controller.actions;

import logic.card.CardLogic;
import logic.client.GameLogic;
import logic.client.Player;
import carddeckplatform.game.TableView;

public class RevealCardAction extends Action{
	
	private Player player;
	private CardLogic card;
	
	public RevealCardAction( Player player, CardLogic card) {
		this.player=player;
		this.card=card;
		
	}

	@Override
	public void incoming() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outgoing() {
		// TODO Auto-generated method stub
		
	}

}
