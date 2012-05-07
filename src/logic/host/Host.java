package logic.host;


import java.util.ArrayList;
import java.util.Stack;

import client.gui.entities.GuiPlayer;
import utils.Player;
import utils.Position;
import communication.actions.Turn;
import communication.messages.Message;
import communication.server.ConnectionsManager;
import logic.client.Game;




public class Host implements Runnable{
	
	private final int maxPlayers=4;
	//public static ArrayList<Player> playersInfo = new ArrayList<Player>();	
	
	private Stack<Position.Player> availablePositions;
	private static Game game;

	//synchronized private boolean startGameFlag=false;
	int playersRdy=0;
	

			
	public Host(Game game) {	
		availablePositions=new Stack<Position.Player>();
		availablePositions.add(Position.Player.RIGHT);
		availablePositions.add(Position.Player.LEFT);
		availablePositions.add(Position.Player.TOP);
		availablePositions.add(Position.Player.BOTTOM);
		
		Host.game=game;
		
	}
	public static void addPlayer(Player playerInfo){
		//playersInfo.add(playerInfo);
		game.addPlayer(playerInfo);
	}
	public static Position.Player nextInTurn(){
		return game.nextInTurn();
	}
	public static void playerLost(Position.Player position){
		game.playerLost(position);
	}
	public void waitForPlayers(){
		
		while(ConnectionsManager.getConnectionsManager().getNumberOfConnections()<game.minPlayers()){
			ConnectionsManager.getConnectionsManager().connectPlayer(availablePositions.pop(),game.toString(),game.getPlayers());
	    }
		//TODO send to GUI enable start game button
		
		
	}
	
	@Override
	public void run() {

		waitForPlayers();
		System.out.println("got all players");
		game.initiate();		
		
		System.out.println("game initiated");
		game.dealCards();		
		
		System.out.println("cards dealt");
		// send the turn action if the game is turned base card game.
		try{
			ConnectionsManager.getConnectionsManager().sendToAll(new Message(new Turn(game.nextInTurn())));
		}catch(Exception e){			
		}
	}
	

	
}
