package communication.link;

import java.io.BufferedReader;
import java.util.Observable;
import java.util.Observer;

//import com.google.gson.Gson;

import communication.entities.Server;
import communication.messages.Message;

//import communication.messages.MessageDictionary;

public abstract class Receiver implements Runnable {
	public abstract boolean closeStream();
	//public void reg(Observer observer){
	//	this.addObserver(observer);
	//	startListen();
	//}

	public abstract void initializeMode();
	
	//protected abstract void startListen();
	
//	public static Message unParseMessage(String str){
//		Gson gson = new Gson();
//		MessageContainer mc = gson.fromJson(str , MessageContainer.class);
//		return MessageDictionary.getMessage(mc.className, mc.classJson);
//	}
}

