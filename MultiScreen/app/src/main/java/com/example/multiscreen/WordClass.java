package com.example.multiscreen;

public class WordClass {


    private static final int NO_IMAGE_PROVIDED=-1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId=NO_IMAGE_PROVIDED;
    private int maudio;

    public WordClass(String de,String miwok,int audio){
        mDefaultTranslation=de;
        mMiwokTranslation=miwok;
        maudio=audio;
    }

    public WordClass(String de,String miwok,int image,int audio){
        mDefaultTranslation=de;
        mMiwokTranslation=miwok;
        mImageResourceId=image;
        maudio=audio;
    }

    public String getDefault(){
        return mDefaultTranslation;
    }

    public String getMiwok(){
        return mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
        if(mImageResourceId==-1)return false;
        return true;
    }

    public int getAudioResourceId(){
        return maudio;
    }

//  You can use log statements to figure out the state of any Java object. The easiest way to print out the
//  contents of a Java object is to provide an implementation of toString() method.
//  The purpose of this method is to represent the whole object as a string, usually for debugging purposes.
//  “Generate a method (Getters, Setters, Constructors, toString, etc..)”.
//  On Windows, the keyboard shortcut is ALT + Insert. On Mac, the keyboard shortcut is CMD + N.
    @Override
    public String toString() {
        return "WordClass{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", maudio=" + maudio +
                '}';
    }
}
