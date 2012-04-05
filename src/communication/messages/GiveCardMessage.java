package communication.messages;

import client.controller.ClientController;
import logic.host.Host;
import carddeckplatform.game.TableView;

import communication.server.ServerMessageHandler;
import communication.server.ServerTask;

public class GiveCardMessage extends Message{

	public String from;
	public String to;
	public int cardId;
	public boolean isRevealed;
	
	@Override
	public void serverAction(ServerMessageHandler serverMessageHandler, Host host, ServerTask serverTask) {
		// TODO send only to 'to'.
		
	}

	@Override
	public void clientAction(ClientController controller) {
//		tableView.addDraggable(card,isRevealed,cardId);
		
	}

}
