package com.example.lorena_z.feelingbooklanfang1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This Class used to show the emotion list in a listView format
 */
public class ShowHistory extends AppCompatActivity {
    ArrayList<BaseStyle> BaseList = new ArrayList<BaseStyle>();                                                 //An ArrayList in BaseStyle type, used to save the load info

    private ListView OldEmotionList;                                                                    //The ListView object

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        OldEmotionList = (ListView) findViewById(R.id.oldEmotionList);                                  //init the ListView
    }

    @Override
    protected void onStart() {
        super.onStart();                                                                                //inher
        show();                                                                                         //Read context from the Emotion.txt file
        MyAdapter adapter = new MyAdapter(BaseList);                                                    //create the ListView, and show things
        OldEmotionList.setAdapter(adapter);                                                             //Set the OneItemClickListener, so user can click at the context
        OldEmotionList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ShowHistory.this, "this is  = " + i + "th item = " + l,
                        Toast.LENGTH_SHORT).show();
            }

        });

    }
//Lab 58-77
    public void show() {                                                                                //get the BaseList, which is an ArrayList which contain all history context
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
        }
     }
}