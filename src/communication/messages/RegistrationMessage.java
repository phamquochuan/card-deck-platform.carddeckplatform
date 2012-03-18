package communication.messages;

import carddeckplatform.game.TableView;

import com.google.gson.annotations.SerializedName;
import communication.client.ClientMessageHandler;
import communication.server.ServerMessageHandler;

public class RegistrationMessage extends Message {
	public RegistrationMessage(){
		messageType = "RegistrationMessage";
	}
	
	@SerializedName("clientName")
	public String clientName;
	
	@SerializedName("id")
	public String id;

	@Override
	public void serverAction(ServerMessageHandler serverMessageHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientAction(TableView tableView) {
		// TODO Auto-generated method stub
		
	}
	
}
