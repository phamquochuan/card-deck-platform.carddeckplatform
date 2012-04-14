package client.dataBase;

import java.util.ArrayList;
import java.util.HashMap;

import war.War;
import logic.client.Game;


public class ClientDataBase {
	private HashMap<String, Game> games;
	
	
	//-------Singleton implementation--------//
	private static class DataBaseHolder
	{
		private final static ClientDataBase dataBase=new ClientDataBase();
	}
			
					
	/**
	 * get Client Data base instance
	 */
	public static ClientDataBase getDataBase(){
		return DataBaseHolder.dataBase;
	}
	private ClientDataBase() {
		games = new HashMap<String, Game>();
		War war = new War();
		games.put(war.toString(), war);
		
	}
	/**
	 * factory
	 */
	public Game getGame(String gameId){
		return games.get(gameId);
	}
	
	public void addGame(String gameId){
		
	}
	
	public ArrayList<String> getGamesNames(){
		ArrayList<String> gameNames = new ArrayList<String>();
		for(String gameName : games.keySet()){
			gameNames.add(gameName);
		}
		return gameNames;
	}	
}
