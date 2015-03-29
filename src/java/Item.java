package com.example.masakisakamoto.sqlitetest;

import android.widget.TextView;

/**
 * Created by masakisakamoto on 2015/03/29.
 */
public class Item {

    public String string1;
    public String string2;
    public String string3;

    public Item(String title, String sakamoto, String masaki) {
        string1 = title;
        string2 = sakamoto;
        string3 = masaki;
    }

    public String toString() {
        return string1 + " - " + string2 + string3;
    }
}
