package com.alibaba.gankio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.gankio.commons.util.AppConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

  private TextView mSkip;
  private ImageView mBg;

  private OkHttpClient client = new OkHttpClient();
  private final static int LOAD_IMG = 1;

  private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what){
        case LOAD_IMG:
          Glide.with(SplashActivity.this)
                  .load(msg.obj.toString())
                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                  .into(mBg);
          break;
      }
    }
  };
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    mSkip = (TextView)findViewById(R.id.fullscreen_content);
    mBg = (ImageView)findViewById(R.id.bg);
    mSkip.setOnClickListener(this);

    getImage();

    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        launch();
      }
    },2000);
  }

  private void launch(){
    String url = AppConfig.getLaunchUrl();
    Intent intent = new Intent(Intent.ACTION_VIEW);
    String scheme = Uri.parse(url).getScheme();
    StringBuilder builder = new StringBuilder();
    if (TextUtils.equals("file", scheme)) {
      intent.putExtra("isLocal", true);
    } else if (!TextUtils.equals("http", scheme) && !TextUtils.equals("https", scheme)) {
      builder.append("http:");
    }
    builder.append(url);

    Uri uri = Uri.parse(builder.toString());
    intent.setData(uri);
    intent.addCategory("com.taobao.android.intent.category.WEEX");
    intent.setPackage(getPackageName());
    startActivity(intent);
    finish();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.fullscreen_content:
        launch();
        break;
    }
  }

  private void getImage(){
    final Request request = new Request.Builder().url("http://gank.io/api/random/data/%E7%A6%8F%E5%88%A9/1").build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Request request, IOException e) {

      }

      @Override
      public void onResponse(Response response) throws IOException {
        if(response.isSuccessful()){

            JSONObject data =  JSON.parseObject(response.body().string());
            boolean error = data.getBoolean("error");
            JSONArray array = data.getJSONArray("results");
            if(!error){
              if(null != array && array.size() >0){
                JSONObject result = (JSONObject)array.get(0);
                mHandler.sendMessage(mHandler.obtainMessage(LOAD_IMG,result.getString("url")));
              }
            }
            Log.e("tag","error: "+data.getBoolean("error")+" results: "+data.getJSONArray("results").size());
        }
      }
    });
  }
}
