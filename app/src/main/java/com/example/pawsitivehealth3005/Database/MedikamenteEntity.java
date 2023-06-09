package com.example.pawsitivehealth3005.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "medikamenteTabelle")
public class MedikamenteEntity {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
private int id;
private String mediNameString;
    private int mgAngabe;
    private boolean isChecked;
    private boolean isChecked2;
    private boolean isChecked3;

public MedikamenteEntity(){
    //public MedikamenteEntity( String mediNameString, int mgAngabe, boolean isChecked,boolean isChecked2, boolean isChecked3);
    this.mediNameString=mediNameString;
    this.mgAngabe=mgAngabe;
    this.isChecked=isChecked;
    this.isChecked2=isChecked2;
    this.isChecked3=isChecked3;
}


    ///MEDIKAMENTE werden aus Datum heute geholt
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


}
