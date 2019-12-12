package com.example.minicalculator;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Jinzhi {
    //Temp↓//
    private static Spinner mod1;
    private static Spinner mod2;
    private static EditText in;
    private static EditText out;
    private static Button _do;
    //Temp↑//

    private final static String[] jinzhi = {"2", "8", "10", "16"};

    public static void create(Context c, View v) {
        mod1 = v.findViewById(R.id.jinzhi_mod1);
        mod2 = v.findViewById(R.id.jinzhi_mod2);
        in = v.findViewById(R.id.jinzhi_in);
        out = v.findViewById(R.id.jinzhi_out);
        _do = v.findViewById(R.id.jinzhi_do);
        ArrayAdapter adapter = new ArrayAdapter(c, android.R.layout.simple_spinner_item, jinzhi);
        mod1.setAdapter(adapter);
        mod1.setSelection(2);
        mod2.setAdapter(adapter);
        mod2.setSelection(2);
        in.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (out != null) out.setText("");
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        _do.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    out.setText(get(Integer.parseInt(mod1.getSelectedItem() + ""), Integer.parseInt(mod2.getSelectedItem() + ""), in.getText() + ""));
                } catch (Exception e) {
                    if (out != null) out.setText("错误！");
                }
            }
        });
    }

    public static String get(int mod1, int mod2, String in) throws Exception {
        long out = Long.valueOf(in, mod1);

        if (mod2 == 2) return Long.toBinaryString(out);
        else if (mod2 == 8) return Long.toOctalString(out);
        else if (mod2 == 10) return Long.toString(out);
        else if (mod2 == 16) return Long.toHexString(out);
        else throw new Exception();
    }
}