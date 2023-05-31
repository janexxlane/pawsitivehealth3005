package com.example.pawsitivehealth3005;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "DBEintrag")
public class DailyEintrag {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ////////////////////////MEDIKAMENTE
    public String getMediNameString() {
        return mediNameString;
    }

    public void setMediNameString(String mediNameString) {
        this.mediNameString = mediNameString;
    }

    public int getMgAngabe() {
        return mgAngabe;
    }

    public void setMgAngabe(int mgAngabe) {
        this.mgAngabe = mgAngabe;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked2() {
        return isChecked2;
    }

    public void setIsChecked2(boolean checked2) {
        isChecked2 = checked2;
    }

    public boolean isChecked3() {
        return isChecked3;
    }

    public void setIsChecked3(boolean checked3) {
        isChecked3 = checked3;
    }

    private String mediNameString;
    private int mgAngabe;
    private boolean isChecked;
    private boolean isChecked2;
    private boolean isChecked3;

    /////////////////////////////ARZTTERMINE


    private String diagnoseString;

    public void setDiagnoseString(String diagnoseString) {
        this.diagnoseString = diagnoseString;
    }

    public void setArztNameString(String arztNameString) {
        this.arztNameString = arztNameString;
    }

    public void setArztNummerString(String arztNummerString) {
        this.arztNummerString = arztNummerString;
    }

    private String arztNameString;
    private String arztNummerString;


    public void setSpaziergangName(String spaziergangName) {
        this.spaziergangName = spaziergangName;
    }

    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getSpaziergangName() {
        return spaziergangName;
    }

    public String getStepCount() {
        return stepCount;
    }

    /////////////////////////////SPAZIERGÄNGE
    private String spaziergangName;
    private String stepCount;



    //////////////////////////////Datum heute

    public void setNotizenEssen(String notizenEssen) {
        this.notizenEssen = notizenEssen;
    }

    public void setNotizenAusscheidung(String notizenAusscheidung) {
        this.notizenAusscheidung = notizenAusscheidung;
    }


    public void setDatumAnzeigeString(String datumAnzeigeString) {
        DatumAnzeigeString = datumAnzeigeString;
    }

    private String notizenEssen;
    private String notizenAusscheidung;


    private byte[] imageBytesE;

    public byte[] getImageBytesE() {
        return imageBytesE;
    }

    public void setImageBytesE(byte[] imageBytes) {
        this.imageBytesE = imageBytes;
    }

    private byte[] imageBytesA;

    public byte[] getImageBytesA() {
        return imageBytesA;
    }

    public void setImageBytesA(byte[] imageBytes) {
        this.imageBytesE = imageBytes;
    }
    public String getDiagnoseString() {
        return diagnoseString;
    }

    public String getArztNameString() {
        return arztNameString;
    }

    public String getArztNummerString() {
        return arztNummerString;
    }

    public String getNotizenEssen() {
        return notizenEssen;
    }

    public String getNotizenAusscheidung() {
        return notizenAusscheidung;
    }


    public String getDatumAnzeigeString() {
        return DatumAnzeigeString;
    }

    private String DatumAnzeigeString;

}


