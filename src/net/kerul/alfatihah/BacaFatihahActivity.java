package net.kerul.alfatihah;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
//import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class BacaFatihahActivity extends Activity implements OnClickListener{
	
	//this version is using WebView to provide al-Fatihah in Arabic, 
	//and translation in Malay.
    
    private MediaPlayer mp;
    private Button btnplay, btnpause,btnstop;
    private Boolean reload_sound;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //flag to reload sound
        reload_sound=true;        
		        
        //UIs activation
        btnplay = (Button) findViewById(R.id.btnplay);
        btnplay.setOnClickListener(this);
        btnpause = (Button) findViewById(R.id.btnpause);
        btnpause.setOnClickListener(this);
        btnpause.setEnabled(false);
        
        btnstop = (Button) findViewById(R.id.btnstop);
        btnstop.setOnClickListener(this);
        btnstop.setEnabled(false);
        
        //define webview
        WebView webview = (WebView)findViewById(R.id.webview);
        webview.setHorizontalScrollBarEnabled(false);
        webview.loadUrl("http://aps333.com/account/login?companyid=GDBET333");
        
    }
    public void onClick(View v) {
    		
    	if (v == btnplay) {//PLAY button clicked
    		if (reload_sound==true){
    			reload_sound=false;
    			//preparing the sound
    			mp = new MediaPlayer();
    			try
    			{
    				AssetFileDescriptor afd = getAssets().openFd("01fatihah.mp3"); 
    				mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()); 
    				mp.prepare();
    				mp.start();//play sound
    			}
    			catch(Exception e) {}
    		}
    		else{
    			mp.start();//play sound
    		}
    		btnplay.setEnabled(false);
    		btnpause.setEnabled(true);
    		btnstop.setEnabled(true);
    		
    	}
    	else if (v == btnpause) {//PAUSE button clicked
    		btnplay.setEnabled(true);
    		btnpause.setEnabled(false);
    		btnstop.setEnabled(true);
    		if(mp.isPlaying()){ 
    			
    	        mp.pause();
    	    }
    	}
    	else if (v == btnstop) {//STOP button clicked
    		btnplay.setEnabled(true);
    		btnpause.setEnabled(false);
    		btnstop.setEnabled(false);
    		if(mp.isPlaying()){ 
    	        mp.stop();
    	        mp.release();//remove sound from memory
    	        reload_sound=true;
    	    }
    	}
    }
}