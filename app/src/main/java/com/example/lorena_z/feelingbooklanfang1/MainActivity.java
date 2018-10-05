package com.example.lorena_z.feelingbooklanfang1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import com.example.lorena_z.feelingbooklanfang1.BaseStyle;

public class MainActivity extends AppCompatActivity {
    private EditText textView;
    private Button Love;                                                                            //the emotion button
    private Button Joy;
    private Button Surprise;
    private Button Anger;
    private Button Sadness;
    private Button Fear;
    private Button ViewCount;
    private Button ViewHistory;
    private int num;
    EditText CommentInput;
    String emotion;
    ArrayList<BaseStyle> BaseList = new ArrayList<BaseStyle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCount = (Button) findViewById(R.id.count);
        ViewHistory = (Button) findViewById(R.id.history);
        CommentInput = (EditText) findViewById(R.id.CommentInput);
        }

    @Override
    protected void onResume(){
        super.onResume();
        show();                                                                                     //before any operate, read from the file
    }

    public String GetTime(){                                                                        //get the time
        Time t=new Time();
        t.setToNow();
        int year = t.year;
        int month = t.month+1;
        int day = t.monthDay;
        int hour = t.hour;
        int minute = t.minute;
        int second = t.second;

        String M; String D; String H; String m; String S;


        if (month<10) {M = "0"+Integer.toString(month);} else { M = Integer.toString(month);}       //format the time in iso8601 Date&Time format
        if (day<10) { D = "0"+Integer.toString(day);} else { D = Integer.toString(day);}
        if (hour<10) { H = "0"+Integer.toString(hour);} else { H = Integer.toString(hour);}
        if (minute<10) { m = "0"+Integer.toString(minute);} else { m = Integer.toString(minute);}
        if (second<10) { S = "0"+Integer.toString(second);} else { S = Integer.toString(second);}

        return (year+"-"+M+"-"+D+"T"+H+":"+m+":"+S);
    }
    public void Joy(View view){                                                                     //each button will save with different emotion
        String date = GetTime();
        emotion = "joy";
        BaseList.add(new BaseStyle(emotion,CommentInput.getText().toString(),date));
        saveInfo();
    }
    public void Sadness(View view){
        //Limit();
        String date = GetTime();
        emotion = "sadness";
        BaseList.add(new BaseStyle(emotion,CommentInput.getText().toString(),date));
        saveInfo();
    }
    public void Anger(View view){
        //Limit();
        String date = GetTime();
        emotion = "anger";
        BaseList.add(new BaseStyle(emotion,CommentInput.getText().toString(),date));
        saveInfo();
    }
    public void Surprise(View view){
        //Limit();
        String date = GetTime();
        emotion = "surprise";
        BaseList.add(new BaseStyle(emotion,CommentInput.getText().toString(),date));
        saveInfo();
    }
    public void Fear(View view){
        //Limit();
        String date = GetTime();
        emotion = "fear";
        BaseList.add(new BaseStyle(emotion,CommentInput.getText().toString(),date));
        saveInfo();
    }

    public void Love(View view){
        //Limit();
        String date = GetTime();
        emotion = "love";
        BaseList.add(new BaseStyle(emotion,CommentInput.getText().toString(),date));
        saveInfo();
    }

    public void DisplayHistory(View view){                                                          //go to history page
        Intent intent=new Intent(this,ShowHistory.class);
        startActivity(intent);
    }

    public void DisplayCount(View view){                                                            //go the count page
        Intent intent = new Intent(this,ShowCount.class);
        startActivity(intent);
    }

    //lab line:185-202
    public void show() {
        try{                                                                                        //read from the file
            BaseList = new ArrayList<BaseStyle>();
            FileInputStream fis = openFileInput("Emotion.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<BaseStyle>>(){}.getType();
            BaseList = gson.fromJson(in,listType);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            BaseList = new ArrayList<BaseStyle>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //lab 100-121
    public void saveInfo(){                                                                         //save into file
        sortClass sort = new sortClass();
        Collections.sort(BaseList,sort);

        try {
            FileOutputStream fos = openFileOutput("Emotion.txt", Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(BaseList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

        Toast.makeText(this,"Saved!",Toast.LENGTH_LONG).show();
        CommentInput.setText("");
    }


}


