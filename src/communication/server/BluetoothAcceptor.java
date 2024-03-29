package communication.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.bluetooth.BluetoothSocket;
import carddeckplatform.game.gameEnvironment.GameEnvironment;

import communication.link.ObjectInputStreamWithDelegateClassLoader;
import communication.link.Streams;

public class BluetoothAcceptor implements Acceptor {
	public BluetoothAcceptor(){
	}
	
	@Override
	public Streams accept() {
		// TODO Auto-generated method stub
		BluetoothSocket clientSocket;
		try {
			//clientSocket = serverSocket.accept();
			clientSocket = GameEnvironment.get().getBluetoothInfo().getServerSocket(GameEnvironment.get().getBluetoothInfo().getCurrentServerSocketIndex()).accept();
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStreamWithDelegateClassLoader(clientSocket.getInputStream());
			GameEnvironment.get().getBluetoothInfo().increaseCurrentServerSocketIndex();
			
			return new Streams(out, in);
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
