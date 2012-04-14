package war.actions;

import communication.link.ServerConnection;
import communication.messages.Message;

import war.War;
import client.controller.ClientController;
import client.controller.actions.ClientAction;
import logic.card.CardLogic;
import logic.client.GameLogic;
import logic.client.LogicDroppable;
import logic.client.Player;
import carddeckplatform.game.GameStatus;
import carddeckplatform.game.TableView;

public class RoundWinnerAction extends ClientAction{
	String winner;
	public RoundWinnerAction(String winner) {
		this.winner=winner;
	}

	
	@Override
	public void incoming() {
		if (winner.equals(GameStatus.username)){
			//i'm the winner
			for (LogicDroppable droppable : War.getDroppables()){
				for (CardLogic card : droppable.getCards()){
					card.setOwner(GameStatus.username);
					ClientController.getController().addCard(card);
				}
			}
		}
		for (LogicDroppable droppable : War.getDroppables()){
			if (droppable.getType().equals(LogicDroppable.Type.PUBLIC)){
				droppable.getCards().clear();
			}
		}
		
	}

	/**
	 * 
	 */
	@Override
	public void outgoing() {
		ServerConnection.getConnection().getMessageSender().send(new Message(this));
		
	}

}
