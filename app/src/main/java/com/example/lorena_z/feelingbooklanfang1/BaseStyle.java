package com.example.lorena_z.feelingbooklanfang1;

import java.util.Date;

/**
 * The Class implement for track the context and format of the user input
 * emotion : love / joy / anger / surprise / sadness / fear
 * comment : max length 100 / may be empty
 * date : store in iso8601 Date&Time format e.g. 2018-09-01T18:30:00
 */

public class BaseStyle {
    private String date;
    private String emotion;
    private String comment;

    /**
     * The constructor
     * @param emotion is the emotion that the user selected
     * @param comment is the comment that the user entered
     * @param date is the date, might be created by default or might be entered by user
     */
    public BaseStyle(String emotion,String comment, String date){
        this.emotion = emotion;
        this.comment = comment;
        this.date = date;
    }

    public String getEmotion(){return emotion;}                                 //return the emotion of current BaseStyle object
    public String getComment(){return comment;}                                 //return the comment of current BaseStyle object
    public String getDate(){return date;}                                       //return the date of current BaseStyle object

    void setEmotion(String emotion){this.emotion = emotion;}             //change/store the emotion of current BaseStyle object
    void setComment(String comment){this.comment = comment;}             //change/store the comment of current BaseStyle object
    void setDate(String date){this.date = date;}                        //change/store the date of current BaseStyle object
}
