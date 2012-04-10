package client.controller.actions;

import server.controller.actions.SendToAllExceptMe;
import communication.link.ServerConnection;
import communication.messages.Message;

import logic.client.GameLogic;
import carddeckplatform.game.TableView;

public class DraggableMotionAction extends ClientAction {

	private String username;
	private int cardId;
	private int x; 
	private int y;
	
	public DraggableMotionAction( String username, int cardId, int x, int y) {
		this.username = username;
		this.cardId = cardId;
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void incoming() {
		// TODO Auto-generated method stub
		gui.draggableMotion(username, cardId, x, y);
	}

	@Override
	public void outgoing() {
		// TODO Auto-generated method stub
		ServerConnection.getConnection().getMessageSender().sendMessage(new Message(this));
	}

}
