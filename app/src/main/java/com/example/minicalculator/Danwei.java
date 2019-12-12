package com.example.minicalculator;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Danwei {
    //Temp↓//
    private static EditText[] in = new EditText[5];
    private static Button[] _do = new Button[5];
    private static double value;
    private static double[] bili = {1, 3, 1852, 482.8032, 2.8382e+15};

    //Temp↑//
    public static void create(Context c, View v) {
        in[0] = v.findViewById(R.id.danwei_in1);
        in[1] = v.findViewById(R.id.danwei_in2);
        in[2] = v.findViewById(R.id.danwei_in3);
        in[3] = v.findViewById(R.id.danwei_in4);
        in[4] = v.findViewById(R.id.danwei_in5);
        _do[0] = v.findViewById(R.id.danwei_bt1);
        _do[1] = v.findViewById(R.id.danwei_bt2);
        _do[2] = v.findViewById(R.id.danwei_bt3);
        _do[3] = v.findViewById(R.id.danwei_bt4);
        _do[4] = v.findViewById(R.id.danwei_bt5);
        for (int i = 0; i < 5; i++) {
            _do[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int num = -1;
                    for (int i = 0; i < 5; i++) {
                        if (_do[i] == view) {
                            num = i;
                            break;
                        }
                    }
                    if (num == -1) return;
                    try {
                        value = Double.parseDouble(in[num].getText().toString()) * bili[num];
                    } catch (Exception e) {
                        return;
                    }
                    outPut();
                }
            });
        }
    }

    private static void outPut() {
        for (int i = 0; i < 5; i++) {
            in[i].setText(value / bili[i] + "");
        }
    }
}
