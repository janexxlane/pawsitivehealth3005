package com.example.pawsitivehealth3005.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "DBEintrag")
public class StammdatenEntity  {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public StammdatenEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }







    //////////////////////////////Datum heute


    private String notizenEssen;
    private String notizenAusscheidung;
    private byte[] imageBytesE;

    private byte[] imageBytesA;

    private String DatumAnzeigeString;
    public void setDatumAnzeigeString(String datumAnzeigeString) {
        DatumAnzeigeString = datumAnzeigeString;
    }
    public void setNotizenEssen(String notizenEssen) {
        this.notizenEssen = notizenEssen;
    }

    public void setNotizenAusscheidung(String notizenAusscheidung) {
        this.notizenAusscheidung = notizenAusscheidung;
    }

    public byte[] getImageBytesE() {
        return imageBytesE;
    }

    public void setImageBytesE(byte[] imageBytes) {
        this.imageBytesE = imageBytes;
    }

    public byte[] getImageBytesA() {
        return imageBytesA;
    }

    public void setImageBytesA(byte[] imageBytes) {
        this.imageBytesE = imageBytes;
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



}


