package com.example.rwfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //SharedPreferences文件名
    private final static String SharedPreferencesFileName="config";
    //键
    private final static String Key_UserName="UserName";//用户名
    private final static String Key_LoginDate="LoginDate";//登录时间
    private final static String Key_UserType="UserType";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences=getSharedPreferences(SharedPreferencesFileName,
                MODE_PRIVATE);
        editor=preferences.edit();
        Button btnWrite=(Button)findViewById(R.id.Write);
        Button btnRead=(Button)findViewById(R.id.Read);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());

                editor.putString(Key_UserName, "冯萌，2017011148");
                editor.putString(Key_LoginDate, strLoginDate);
                editor.putInt(Key_UserType, 1);

                editor.apply();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = preferences.getString(Key_UserName, null);
                String strLoginDate = preferences.getString(Key_LoginDate, null);
                int nUserType=preferences.getInt(Key_UserType,0);
                if (strUserName != null && strLoginDate != null)
                    Toast.makeText(MainActivity.this, "用户名，学号:" + strUserName + "登录时间:" + strLoginDate+"用户类型:"+nUserType, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
            }
        });


    }
}
