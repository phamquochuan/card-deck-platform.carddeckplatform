package utils.droppableLayouts;

import java.io.Serializable;
import java.util.AbstractList;

import logic.client.Game;

import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import carddeckplatform.game.gameEnvironment.GameEnvironment;
import client.gui.entities.Droppable;
import utils.Card;
import utils.Point;

public abstract class DroppableLayout implements Serializable {
	public enum Orientation {
		HORIZONTAL, VERTICAL
	}

	protected Orientation orientation;
	protected Droppable droppable;
	AnimationRunnable animationRunnable = null;

	public abstract void rearrange(int index, float width, float height);

	public DroppableLayout(Droppable droppable) {
		this.orientation = orientation;
		this.droppable = droppable;
	}
	protected float[][] shift(float[][] animationArgs,float x,float y){
		for (int i = 0; i < animationArgs[0].length; i++) {
			animationArgs[0][i]=animationArgs[0][i]+x;
			animationArgs[1][i]=animationArgs[1][i]+y;
		}
		return animationArgs;
		
		
	}
	
	
	
	
	protected float[][] normalizePosition(float[][] animationArgs, float width,
			float height) {
		float sumX = 0;
		float sumY = 0;
		int length = animationArgs[0].length;
		// ma
//		for (int i = 0; i < length; i++) {
//			sumX += animationArgs[0][i];
//			sumY += animationArgs[1][i];
//		}
		// making accumulative array
		for (int i = 1; i < length; i++) {
			animationArgs[0][i] = animationArgs[0][i] + animationArgs[0][i - 1];
			// animationArgs[1][i]=animationArgs[1][i]+animationArgs[1][i-1];
		}

		if (sumX != 0)
			for (int i = 0; i < length; i++) {
				animationArgs[0][i] = (animationArgs[0][i] / sumX) * width;
						
				// if(sumY!=0)
				// animationArgs[1][i]=animationArgs[1][i]/sumY*height+droppable.getY();
			}
		
		for (int i = 0; i < animationArgs[0].length; i++) {
			System.out.println("blablaba:"+animationArgs[0][i]);
		}
		return animationArgs;

	}

	class AnimationRunnable implements Runnable {

		public AnimationRunnable(float[][] animationArgs,
				AbstractList<Card> abstractList, long duration) {
			this.animationArgs = animationArgs;
			this.abstractList = abstractList;
			this.duration = duration;
		}

		public void stopAnimation() {
			running = false;

		}

		boolean running = true;
		float[][] animationArgs;
		AbstractList<Card> abstractList;
		long duration;

		@Override
		public void run() {
			// save the initial value
			Interpolator animateInterpolator = new AccelerateDecelerateInterpolator();
			System.out.println("rearrange:" + animationArgs[0].length);
			float[] totalDx = new float[animationArgs[0].length];
			float[] totalDy = new float[animationArgs[0].length];
			float[] totalDAngle = new float[animationArgs[0].length];
			float[][] reserved = new float[3][animationArgs[0].length];

			for (int i = 0; i < totalDx.length; i++) {
				totalDx[i] = animationArgs[0][i] - abstractList.get(i).getX();
				totalDy[i] = animationArgs[1][i] - abstractList.get(i).getY();
				totalDAngle[i] = animationArgs[2][i]
						- abstractList.get(i).getAngle();

				Card card = abstractList.get(i);
				reserved[0][i] = card.getX();
				reserved[1][i] = card.getY();
				reserved[2][i] = card.getAngle();
			}

			long startTime = System.currentTimeMillis();
			long endTime = startTime + duration;
			long curTime = System.currentTimeMillis();

			float percentTime = (float) (curTime - startTime)
					/ (float) (endTime - startTime);
			float percentDistance = animateInterpolator
					.getInterpolation(percentTime);

			float[] curDx = new float[animationArgs[0].length];
			float[] curDy = new float[animationArgs[0].length];
			float[] curDAngle = new float[animationArgs[0].length];
			for (int i = 0; i < totalDx.length; i++) {
				curDx[i] = percentDistance * totalDx[i];
				curDy[i] = percentDistance * totalDy[i];
				curDAngle[i] = percentDistance * totalDAngle[i];

			}
			for (int i = 0; i < totalDx.length; i++) {
				abstractList.get(i).setLocation(reserved[0][i] + curDx[i],
						reserved[1][i] + curDy[i]);
				abstractList.get(i).setAngle(reserved[2][i] + curDAngle[i]);
			}
			while (percentTime < 1.0 && running) {
				curTime = System.currentTimeMillis();
				percentTime = (float) (curTime - startTime)
						/ (float) (endTime - startTime);
				percentDistance = animateInterpolator
						.getInterpolation(percentTime);

				for (int i = 0; i < totalDx.length; i++) {
					curDx[i] = percentDistance * totalDx[i];
					curDy[i] = percentDistance * totalDy[i];
					curDAngle[i] = percentDistance * totalDAngle[i];
					Card card = abstractList.get(i);
					card.setLocation(reserved[0][i] + curDx[i], reserved[1][i]
							+ curDy[i]);
					card.setAngle(reserved[2][i] + curDAngle[i]);
				}

			}
			for (int i = 0; i < animationArgs[0].length; i++) {
				Card card = abstractList.get(i);
				card.setLocation(animationArgs[0][i], animationArgs[1][i]);
				card.setAngle(animationArgs[2][i]);
			}
		}

	}

	public void stopAnimation() {

		if (this.animationRunnable != null)
			animationRunnable.stopAnimation();
	}

	public void animate(AbstractList<Card> abstractList,
			float[][] animationArgs, long duration) {

		if (animationRunnable != null)
			animationRunnable.stopAnimation();

		this.animationRunnable = new AnimationRunnable(animationArgs,
				abstractList, duration);
		GameEnvironment.get().getHandler().post(animationRunnable);

	}
}