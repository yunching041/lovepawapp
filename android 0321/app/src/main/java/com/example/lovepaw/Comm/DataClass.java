package com.example.lovepaw.Comm;

public class DataClass {

    private String dataTitle;
    private String dataContent;
    private String dataType;
    private String dataImage;

    public String getDataTitle() {
        return dataTitle;
    }
    public String getDataContent() {
        return dataContent;
    }
    public String getDataType() {
        return dataType;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataTitle,String dataContent,  String dataType, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataContent = dataContent;
        this.dataType = dataType;
        this.dataImage = dataImage;
    }

    public DataClass(){

    }
}

