package com.example.mint.applicationtest;

/**
 * Created by Mint on 8/2/2017.
 */

public class Data {
    private String mText1;
    private  String mText2;
    private  String mIcon;

    public Data (String text1,String text2,String icon)  {
        this.mIcon = icon;
        this.mText1 = text1;
        this.mText2 = text2;

    }
    public  String getmText1(){
        return  mText1;
    }
    public  void  setmText1(String mText1){
        this.mText1 = mText1;
    }
    public  String getmText2(){
        return  mText2;
    }
    public void  setmText2( String mText2m){
        this.mText2 =mText2;
    }
    public  String getmIcon(){
        return  mIcon;
    }
    public  void  setmlcon( String mIcon){
        this.mIcon = mIcon;
    }
}
