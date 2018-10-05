package com.example.lorena_z.feelingbooklanfang1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ShowCount extends AppCompatActivity {
    TextView textView;
    Button back;
    ArrayList<BaseStyle> BaseList;

    HashMap<String, Integer> EMAP;                                                                  //the hashmap store the count of each emotion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_count);
        textView = (TextView) findViewById(R.id.textView2);
        back = (Button) findViewById(R.id.button2);
    }

    @Override
    protected void onStart(){
        super.onStart();
        show_his();                                                                                 //read all history record

        count();                                                                                    //count all emotion
        show_count();                                                                               //show the count
    }

    public void show_count(){                                                                       //print the count
        //String msg = ("Love: "+ EMAP.get("love") +"\nJoy: "+joy+"\nSurprise: "+surprise+"\nAnger: "+anger+"\nSadness: "+sadness+"\nFear: "+fear);
        String msg = ("Love"+ EMAP.get("love")+"\nJoy: "+ EMAP.get("joy")+"\nSurprise: "+ EMAP.get("surprise")+"\nAnger: "+ EMAP.get("anger")+ "\nSadness: "+ EMAP.get("sadness")+"\nFear: "+ EMAP.get("fear"));
        textView.setText(msg);

    }

    public void setDefault(){                                                                       //init all emotion count with xxx.count=0
        EMAP.put("love",0);
        EMAP.put("joy",0);
        EMAP.put("surprise",0);
        EMAP.put("anger",0);
        EMAP.put("sadness",0);
        EMAP.put("fear",0);

    }

    public void count(){
        EMAP = new HashMap<String, Integer>();                                                      //count the emotion
        setDefault();


        for(int i =0; i < BaseList.size();i++){
            BaseStyle temp = BaseList.get(i);
            String key = temp.getEmotion();
            Integer value = EMAP.get(key);

            EMAP.put(key,1+value);
        }

    }

    public void GoBack(View view){                                                                  //go back to main page
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

//lab 85-101
    public void show_his() {                                                                        //read from file
        try{
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
        } }
}
