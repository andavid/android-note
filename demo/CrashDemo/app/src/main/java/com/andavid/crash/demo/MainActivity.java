package com.andavid.crash.demo;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.btn_crash).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d("test crash");
        "crash".substring(10);
      }
    });
  }
}
