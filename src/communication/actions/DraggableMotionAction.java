package communication.actions;

import utils.Point;
import client.controller.ClientController;
import client.gui.entities.MetricsConvertion;

public class DraggableMotionAction implements Action {

	private String username;
	private int cardId;
	private float x; 
	private float y;
	
	public DraggableMotionAction( String username, int cardId, float x, float y) {
		this.username = username;
		this.cardId = cardId;
		this.x = x;
		this.y = y;	
	}


	@Override
	public void execute() {
		Point p = new Point(x,y);
		p = MetricsConvertion.pointRelativeToPx(p);
		ClientController.get().getGui().draggableMotion(username, cardId, p.getX(), p.getY());
		
	}

}
