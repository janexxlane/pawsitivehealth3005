package com.example.pawsitivehealth3005.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "ArztpraxisTabelle")
public class ArztpraxisEntity {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;


    private String diagnoseString;
    private String arztNameString;
    private String arztNummerString;


//public ArztpraxisEntity(String diagnoseString, String arztNameString, String arztNummerString){
    //this.diagnoseString=diagnoseString;
    //this.arztNameString= arztNameString;
    //this.arztNummerString=arztNummerString;

//}


    public void setDiagnoseString(String diagnoseString) {
        this.diagnoseString = diagnoseString;
    }

    public void setArztNameString(String arztNameString) {
        this.arztNameString = arztNameString;
    }

    public void setArztNummerString(String arztNummerString) {this.arztNummerString = arztNummerString;}

    public String getDiagnoseString() {
        return diagnoseString;
    }

    public String getArztNameString() {
        return arztNameString;
    }

    public String getArztNummerString() {
        return arztNummerString;
    }



}
