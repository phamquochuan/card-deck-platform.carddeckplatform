package utils.droppableLayouts;

import utils.Card;
import utils.Point;
import client.gui.entities.Droppable;
import client.gui.entities.MetricsConvertion;

public class BottomLineLayout extends DroppableLayout {

	public BottomLineLayout(Droppable droppable) {
		super(droppable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rearrange(int index, float width, float height) {
		int numberOfCards = droppable.getCards().size();
		if (numberOfCards == 0)
			return;
		Point newLocation = null;
		System.out.println("BottomLineLayout.rearrange()" + ":" + width + ","
				+ height);
		Point location = new Point(droppable.getX(), droppable.getY());
		float[][] animationArgs = new float[3][numberOfCards];

		// gets the step that each card would move.
		float step = MetricsConvertion.pointRelativeToPx(new Point(3, 0))
				.getX();

		newLocation = new Point((int) (location.getX() - step * numberOfCards
				/ 2), location.getY());

		for (int i = 0; i < numberOfCards; i++) {
			animationArgs[0][i] = newLocation.getX();
			animationArgs[1][i] = newLocation.getY();
			animationArgs[2][i] = 0;
			newLocation.setX(newLocation.getX() + step);
		}
		// newLocation = new Point(0, 0);
		//
		// for (int i = 0; i < numberOfCards; i++) {
		// animationArgs[0][i] = newLocation.getX();
		// animationArgs[1][i] = newLocation.getY();
		// animationArgs[2][i] = 0;
		// newLocation.setX(newLocation.getX() + step);
		// }
		//
		// animate(droppable.getCards(),
		// shift(normalizePosition(animationArgs, width, height),
		// location.getX() - step * numberOfCards / 2,
		// location.getY())
		//
		// , 1000);
		animate(droppable.getCards(), animationArgs, 1000);
	}
}