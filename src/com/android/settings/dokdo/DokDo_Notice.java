package com.android.settings.dokdo;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;

import com.android.settings.R;

public class DokDo_Notice extends Activity {
  final String LOG_TAG = "GraphicsSample";
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dokdo_notice);
    setTitle(R.string.noticeboard_title);
    
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    
    ImageView view = (ImageView) findViewById(R.id.notice_img);
    String urlStr = "https://github.com/DokDo-Project/Profile_Card/blob/master/notice.jpg?raw=true";
    Drawable draw = loadDrawable(urlStr);
    view.setImageDrawable(draw);
  }

  public Drawable loadDrawable(String urlStr) {
    Drawable drawable = null;
    try {
      URL url = new URL(urlStr);
      InputStream is = url.openStream();
      drawable = Drawable.createFromStream(is, "none");
    } catch (Exception e) {
      Log.e(LOG_TAG, "error, in loadDrawable \n" + e.toString());
    }
    return drawable;
  }
}
