package communication.server;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import carddeckplatform.game.GameStatus;
import communication.messages.Message;

public class ConnectionsManager {

	//-------Singleton implementation--------//
			private static class ConnectionsManagerHolder
			{
				private final static ConnectionsManager connectionsManager=new ConnectionsManager();
			}
			
					
			/**
			 * get Controller instance
			 */
			public static ConnectionsManager getConnectionsManager(){
				return ConnectionsManagerHolder.connectionsManager;
			}
			
	private ArrayList<Connection> connections;
	
	private ConnectionsManager() {
		connections = new ArrayList<Connection>();
	}

		
	//private Connection getConnection(String id){
	//	Connection answer=null;
	//	for (Connection conn : connections){
	//		if (conn.getId().equals(id)){
	//			answer=conn;
	//		}
	//	}
	//	return answer;		
	//}
	
	/**
	 * sendToAll - sends the message to every user.
	 * @param msg
	 */
	public void sendToAll(Message msg){
		for(Connection conn : connections){
			conn.send(msg);
		}
	}
	
	/**
	 * sendToAllExcptMe - sends the message to every user except for the user 'id'.
	 * @param msg
	 * @param id
	 */
	public void sendToAllExcptMe(Message msg , String id){
		for(Connection conn : connections){
			if(!conn.getId().equals(id)){
				conn.send(msg);
			}			
		}
	}
	
	/**
	 * sendTo - sends the message to user 'id'.
	 * @param msg
	 * @param id
	 */
	public void sendTo(Message msg, String id){
		for(Connection serverTask :connections){
			if(serverTask.getId().equals(id)){
				serverTask.send(msg);
				break;
			}
		}
	}
	
	public void connectPlayer(){
		try {	
			ServerSocket serverSocket = new ServerSocket(GameStatus.hostPort);
		
			
			Socket clientSocket;
			System.out.println("Listening to port " + GameStatus.hostPort + " Waiting for messages...");
			
			clientSocket = serverSocket.accept();

			System.out.println("connection request from from " + clientSocket.getRemoteSocketAddress().toString());
			
			final ObjectOutputStream out=new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in=new ObjectInputStream(clientSocket.getInputStream());
			
			Connection serverTask = new Connection(clientSocket.getRemoteSocketAddress().toString(),in, out);
			connections.add(serverTask);
			
		    new Thread(serverTask).start();
		    
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
