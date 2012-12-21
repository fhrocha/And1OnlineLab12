package br.com.globalcode.android.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import br.com.globalcode.android.R;
import br.com.globalcode.android.util.Util4Android;

public class MainActivity extends Activity {
	
	private EditText editTextImagePath;
	private ImageView imageView;
	private String imagePath;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		this.imageView = (ImageView) findViewById(R.id.imageView);
		
		this.editTextImagePath = (EditText) findViewById(R.id.editTextImagePath);

		((Button) findViewById(R.id.buttonAsyncTask)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				imagePath = editTextImagePath.getText().toString();
				String[] paths = {imagePath};
				new AsyncLoadImage().execute(paths);
			}
		});

		((Button) findViewById(R.id.buttonWorkThread)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				imagePath = editTextImagePath.getText().toString();
				loadImageByWorkThread();
			}

		});
		
	}

	protected void loadImageByWorkThread() {
		
		Log.d("And1OnlineLab12", "Work Thread Image Path is: " + imagePath);
		
		new Thread() {
			
			@Override
			public void run() {
				
				imageView.post(new Runnable() {
					
					@Override
					public void run() {
						
						Log.d("And1OnlineLab12", "Image Path is: " + imagePath);
						final Drawable imageDrawable = Util4Android.loadImageFromNetwork(imagePath);
						imageView.setImageDrawable(imageDrawable);
					}
				});
			}
		};
		
	}
	
	class AsyncLoadImage extends AsyncTask<String, Void, Drawable> {
		
		@Override
		protected void onPreExecute() {
			
			progressDialog = ProgressDialog.show(MainActivity.this, "Aguarde...", "Loading Image");
		}

		@Override
		protected Drawable doInBackground(String... params) {
			Log.d("And1OnlineLab12", "Image Path is: " + params[0]);
			return Util4Android.loadImageFromNetwork(params[0]);
		}
		
		@Override
		protected void onPostExecute(Drawable result) {
			
			imageView.setImageDrawable(result);
			progressDialog.dismiss();
		}

	}

}
