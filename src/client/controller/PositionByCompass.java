package client.controller;

import java.util.List;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class PositionByCompass implements SensorEventListener{
	private Sensor accelerometer;
	private Sensor magnetometer;
	private float[] mvalues;
	private SensorManager mSensorManager;
	private float azimut;
	 float[] mGravity;
	  float[] mGeomagnetic;
	
	public PositionByCompass(Context context) {
		mSensorManager=(SensorManager)(context.getSystemService(Context.SENSOR_SERVICE));		
		accelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		mSensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_UI);
		mSensorManager.registerListener(this,magnetometer, SensorManager.SENSOR_DELAY_UI);

		
	}

	


	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		      mGravity = event.values;
		    if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
		      mGeomagnetic = event.values;
		    if (mGravity != null && mGeomagnetic != null) {
		      float R[] = new float[9];
		      float I[] = new float[9];
		      boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
		      if (success) {
		        float orientation[] = new float[3];
		        SensorManager.getOrientation(R, orientation);
		        azimut = orientation[0]; // orientation contains: azimut, pitch and roll
		        System.out.println(azimut*(180/Math.PI));
		      }
		    }
	}
		    		
	}
