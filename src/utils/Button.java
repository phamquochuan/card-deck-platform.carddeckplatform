package utils;

import handlers.ButtonEventsHandler;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import carddeckplatform.game.BitmapHolder;
import client.gui.entities.MetricsConvertion;

public class Button{
	private ButtonEventsHandler handler;
	private String text;
	private String image;
	private Position.Button position;
	private Point scale;
	private Paint paint;
	private transient Shape shape;
	public Button(ButtonEventsHandler handler,Position.Button position,String text) {
		//super(position.getId(), position, new Point(10,13), DroppableLayout.LayoutType.NONE);
		this.handler=handler;
		this.text=text;
		this.image="button";
		this.position=position;
		this.scale=new Point(10, 10);
		this.paint=new Paint();
		paint.setTextSize(20);
		paint.setColor(Color.argb(170, 0, 0, 0));
		//image="button.png";
	}
	public Position.Button getPosition() {
		return position;
	}
	public void setPosition(Position.Button position) {
		this.position = position;
	}
	public void onClick(){
		handler.onClick();
	}
	public void draw(Canvas canvas, Context context){
		Bitmap buttonBitmap=BitmapHolder.get().getBitmap(image);
		Matrix matrix = new Matrix();		 
		Point p= MetricsConvertion.pointRelativeToPx(scale);
		float x=MetricsConvertion.pointRelativeToPx(position.getPoint()).getX();
		float y=MetricsConvertion.pointRelativeToPx(position.getPoint()).getY();
		
		// transformations.		
		matrix.postScale((float)p.getX()/(float)buttonBitmap.getWidth(), (float)p.getY()/(float)buttonBitmap.getHeight());
		matrix.postTranslate(x-p.getX()/2, y-p.getY()/2);
		
		canvas.drawBitmap(buttonBitmap, matrix, null);
		canvas.drawText(text,x,y, paint);
	}

	public float getX() {
		return MetricsConvertion.pointRelativeToPx(position.getPoint()).getX();
	}

	public float getY() {
		return MetricsConvertion.pointRelativeToPx(position.getPoint()).getY();
	}
	
	
	public boolean isContain(float x, float y) {
		return getShape().contains(x, y);
	}
	private Shape getShape() {
		if (shape == null) {
			Point size = MetricsConvertion.pointRelativeToPx(this.scale);
			shape = new Rectangle(getX() - (size.getX() / 2), getY()
					- (size.getY() / 2), size.getX(), size.getY());
		}
		return shape;
	}
	//
	
}