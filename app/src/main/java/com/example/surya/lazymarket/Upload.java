package com.example.surya.lazymarket;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mWhatsappNo;
    private String mImageUrl;
    private String memailId;
    private String mUploaderPic;
    private String mKey;
    private String mDescription;
    private String mDate;
    private String mUsername;

    public Upload(){
        //empty constructor
    }

    public Upload(String whatsappNo, String imageUrl, String emailId,String uploaderPic,String description,String date,String username){
        if(whatsappNo.trim().equals("")){
            whatsappNo = "No Contact";
        }
        mWhatsappNo = whatsappNo;
        mImageUrl = imageUrl;
        memailId=emailId;
        mUploaderPic=uploaderPic;
        mDescription=description;
        mDate = date;
        mUsername=username;
    }

    public String getWhatsappNo(){
        return mWhatsappNo;
    }

    public void setWhatsappNo(String whatsappNo){
        mWhatsappNo=whatsappNo;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl){
        mImageUrl=imageUrl;
    }

    public String getEmailId(){
        return memailId;
    }

    public void setEmailId(String emailId){
        memailId=emailId;
    }

    public String getUploaderPic(){
        return mUploaderPic;
    }

    public void setUploaderPic(String uploaderPic){
        mUploaderPic=uploaderPic;
    }

    public String getDescription(){
        return mDescription;
    }

    public void setDescription(String description){
        mDescription=description;
    }

    public String getDate(){
        return mDate;
    }

    public void setDate(String date){
        mDate=date;
    }

    public String getUsername(){
        return mUsername;
    }

    public void setUsername(String username){
        mUsername=username;
    }

    @Exclude
    public String getKey(){
        return mKey;
    }

    @Exclude
    public void setKey(String key){
        mKey=key;
    }

}

