package war.messages;

import server.controller.ServerController;
import war.actions.PutInPublicAction;
import client.controller.ClientController;
import communication.messages.Message;

public class PutInPublicMessage extends Message {

	@Override
	public void serverAction(String connectionId) {
		ServerController.getServerController().putInPublicCommand(connectionId);
		
	}

	@Override
	public void clientAction() {
		ClientController.incomingAPI().incomingCommand(new PutInPublicAction());
		
	}

	

}
