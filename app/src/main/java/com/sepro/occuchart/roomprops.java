package com.sepro.occuchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class roomprops {

    private String ROOM_NUM;
    private String TYPE;
    private boolean AVAILABLE;
    private String ALLOTTED_TO;

    public roomprops() {
    }

    public roomprops(String room_num, String type,boolean available, String alloted_to)
    {
        this.ROOM_NUM = room_num;
        this.TYPE = type;
        this.AVAILABLE = available;
        this.ALLOTTED_TO = alloted_to;
    }

    public String getRoom_num()
    {
        return ROOM_NUM;
    }

    public String getType()
    {
        return TYPE;
    }
    public boolean getAvailable()
    {
        return AVAILABLE;
    }
    public String getAlloted_to()
    {
        return ALLOTTED_TO;
    }

    public void setRoom_num(String room_num)
    {
        this.ROOM_NUM = room_num;
    }

    public void setType(String type) {
        this.TYPE = type;
    }

    public void setAvailable(boolean available) {
        this.AVAILABLE = available;
    }

    public void setAlloted_to(String alloted_to) {
        this.ALLOTTED_TO = alloted_to;
    }
}
