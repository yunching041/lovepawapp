 package com.example.lovepaw.Mouse;

public class DataClass4 {

    private String dataImage4;
    private String dataDemand3;
    private String datapetname3;
    private String datavariety3;
    private String datalocation3;
    private String datasalary3;
    private String datadetail3;

    public String getDataDemand3() {
        return dataDemand3;
    }
    public String getDataImage4() {
        return dataImage4;
    }

    public String getDataname3() {
        return datapetname3;
    }

    public String getDatavariety3() {
        return datavariety3;
    }

    public String getDatalocation3() {
        return datalocation3;
    }

    public String getDatasalary3() {
        return datasalary3;
    }
    public String getDatadetail3() {
        return datadetail3;
    }
    public DataClass4(String dataImage2, String dataDemand, String datapetname, String datavariety, String datalocation, String datasalary, String datadetail) {
        this.dataDemand3 = dataDemand;
        this.datapetname3 = datapetname;
        this.datavariety3 = datavariety;
        this.datalocation3 = datalocation;
        this.datasalary3 = datasalary;
        this.datadetail3 = datadetail;
        this.dataImage4 = dataImage2;
    }
    public DataClass4(){

    }
}


