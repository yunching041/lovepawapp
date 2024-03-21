 package com.example.lovepaw.Cat;

public class DataClass3 {

    private String dataImage3;
    private String dataDemand2;
    private String datapetname2;
    private String datavariety2;
    private String datalocation2;
    private String datasalary2;
    private String datadetail2;

    public String getDataDemand2() {
        return dataDemand2;
    }
    public String getDataImage3() {
        return dataImage3;
    }

    public String getDataname2() {
        return datapetname2;
    }

    public String getDatavariety2() {
        return datavariety2;
    }

    public String getDatalocation2() {
        return datalocation2;
    }

    public String getDatasalary2() {
        return datasalary2;
    }
    public String getDatadetail2() {
        return datadetail2;
    }
    public DataClass3(String dataImage2, String dataDemand, String datapetname, String datavariety, String datalocation, String datasalary, String datadetail) {
        this.dataDemand2 = dataDemand;
        this.datapetname2 = datapetname;
        this.datavariety2 = datavariety;
        this.datalocation2 = datalocation;
        this.datasalary2 = datasalary;
        this.datadetail2 = datadetail;
        this.dataImage3 = dataImage2;
    }
    public DataClass3(){

    }
}


