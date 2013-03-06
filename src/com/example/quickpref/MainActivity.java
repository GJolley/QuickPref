package com.example.quickpref;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Context context = getApplicationContext();
		
		Button mute =(Button)findViewById(R.id.button1);
		mute.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
				audio.setRingerMode(0);
				
				int ringmode = audio.getRingerMode();
				CharSequence text = null;
						
				//assign ring status to the string name
				if (ringmode == 0){
					text = "Ringer Mode Silent";
				} else if (ringmode == 1){
					text = "Ringer Mode Vibrate";
				} else if (ringmode == 2){
					text = "Ringer Mode Normal";
				}
				
				int duration = Toast.LENGTH_LONG;
				
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});
		
		Button light =(Button)findViewById(R.id.button2);
		final Camera camera;
		camera = Camera.open();
		
		light.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			CharSequence text = null;
				
				final Parameters p = camera.getParameters();
				String flashmode = p.getFlashMode();

				if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

					if(flashmode.equals(android.hardware.Camera.Parameters.FLASH_MODE_TORCH)) {
						p.setFlashMode(Parameters.FLASH_MODE_OFF);
						camera.setParameters(p);
						camera.startPreview();
						text = "Light Off";
					} else {
						p.setFlashMode(Parameters.FLASH_MODE_TORCH);
						camera.setParameters(p);
						camera.startPreview();
						text = "Light On";
					}
				
				int duration = Toast.LENGTH_LONG;
				
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			};
			}		
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
