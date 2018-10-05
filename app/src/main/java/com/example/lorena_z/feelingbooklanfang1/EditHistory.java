package com.example.lorena_z.feelingbooklanfang1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EditHistory extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button save_edit;
    TextView text;

    private EditText Year;                                                                          // the EditText which will get the y/m/d/h/m/s separate
    private EditText Month;
    private EditText Day;
    private EditText Hour;
    private EditText Minute;
    private EditText Second;

    Button goHome;                                                                                  // The Button help user go back to main page

    private int check=1;                                                                            // the flag which present the validity of user's enter time

    public static ArrayList<BaseStyle> BList;                                                       // the ArrayList come from ShowHistory which is all history

    public static int len;                                                                          // the index of the emotion that user want to edited
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_history);
        textView = (TextView) findViewById(R.id.orinal_text);
        text = (TextView) findViewById(R.id.text);

        BaseStyle temp = BList.get(len);
        String t = "The orignial text:\nEmotion: "+temp.getEmotion()+"\nDate: "+ temp.getDate()+"\nComment: "+temp.getComment();

        textView.setText(t);                                                                        //The overview of whole info of that particular record


        editText = (EditText) findViewById(R.id.inputcomment);                                      //init
        save_edit = (Button) findViewById(R.id.save_edit);
        goHome = (Button) findViewById(R.id.home);

        Year = (EditText) findViewById(R.id.year);
        Day = (EditText) findViewById(R.id.day);
        Month = (EditText) findViewById(R.id.month);
        Hour = (EditText) findViewById(R.id.hour);
        Minute = (EditText) findViewById(R.id.minute);
        Second = (EditText) findViewById(R.id.second);



                                                            // The init the textView, So if the user doesn't enter, textView still have something in it
                                                                                                    // when use getText(), even user doesn't enter, it will still get original data
        }

    @Override
    protected void onStart(){
        super.onStart();
        show();
    }
    public void show(){
        BaseStyle temp = BList.get(len);
        Year.setText(temp.getDate().substring(0,4));                                                //init the year/month/day/hour/minute/second
        Month.setText(temp.getDate().substring(5,7));
        Day.setText(temp.getDate().substring(8,10));
        Hour.setText(temp.getDate().substring(11,13));
        Minute.setText(temp.getDate().substring(14,16));
        Second.setText(temp.getDate().substring(17,19));

        editText.setText(temp.getComment());
    }
    public void Delete(View view){
        BaseStyle tem = BList.get(len);                                                             //if user want to delete this item. just simply remove it from the whole list
        BList.remove(tem);                                                                          //and store the new list to the file
        SavetoFile(BList);
        Toast.makeText(this,"Delete!",Toast.LENGTH_LONG).show();

        Intent intent=new Intent(this,MainActivity.class);                            //after store, back to main page
        startActivity(intent);


    }

    public void Save(View view){                                                                    //if the user want to edit this item
        BaseStyle temp = BList.get(len);
        String date;
        String emotion = temp.getEmotion();                                                         //user cannot edit emotion. so simply get it directly from original text

        String Y = Year.getText().toString();                                                       //get the input of year/month/day/hour/minute/
        String MM =Month.getText().toString();
        String D = Day.getText().toString();
        String H = Hour.getText().toString();
        String M = Minute.getText().toString();
        String S = Second.getText().toString();


        if (MM.length()==0){MM = "00";}
        if (D.length()==0){D="00";}

        if (Integer.parseInt(MM)<10 && MM.length()==1){ MM = "0"+MM;}                                //build the format of time
        if (Integer.parseInt(D)<10 && D.length()==1){ D = "0"+D;}




        date = (Y + MM + D);


        if (Y.length()!=4 | MM.length() ==0 | D.length() == 0 | H.length() == 0 | M.length() == 0 | S.length() == 0|    //check the valid of time
                !checkYearMonthDay(date)|| Integer.parseInt(H)>24 || Integer.parseInt(H)<1 ||
                Integer.parseInt(M)>59 ||Integer.parseInt(M)<0 || Integer.parseInt(S)>59 ||Integer.parseInt(S)<0
                )
        {
            check = 0;
        }
        else{
            check = 1;
            if (Integer.parseInt(H)<10 && H.length()<2){ H = "0"+H;}                                //if the time is valid, format it
            if (Integer.parseInt(M)<10 && M.length()<2){ M = "0"+M;}
            if (Integer.parseInt(S)<10 && S.length()<2){ S = "0"+S;}

            date = (Y + "-"+MM+"-" + D+"T" + H +":"+M +":"+S);
        }


        if (check == 0){                                                                            //if the time is invalid, show hint
            Toast.makeText(getApplicationContext(), "Please enter a valid Date/Time", Toast.LENGTH_LONG).show();
        }
        else{

            BaseStyle tem = BList.get(len);                                                         //remove the old one

            BList.remove(tem);

            BList.add(new BaseStyle(emotion,editText.getText().toString(),date));                   //get the new one

            SavetoFile(BList);
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);                                                                  //go bake to the main page
        }
    }



    public void Back(View view){                                                                    //back to the main page without any change
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    //lab 174-193
    private void SavetoFile(ArrayList<BaseStyle> bList){                                            //read from the file
        sortClass sort = new sortClass();
        Collections.sort(bList,sort);

        try {
            FileOutputStream fos = openFileOutput("Emotion.txt", Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(bList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    //https://www.cnblogs.com/xdp-gacl/p/3548307.html line 156-172
    private static boolean checkYearMonthDay(String ymd) {                                          //check the date is valid
        if (ymd == null || ymd.length() == 0) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = format.parse(ymd);
            if (!format.format(date).equals(ymd)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}

