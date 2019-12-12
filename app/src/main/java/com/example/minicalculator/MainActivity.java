package com.example.minicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Calculator 计算器;
    Button 按钮[] = new Button[34];
    EditText 显示栏;
    double width, height;

    public MainActivity() {
        if (Calculator.inside == null)
            计算器 = new Calculator();
        else 计算器 = Calculator.inside;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                dialog1.setTitle("长度换算");
                LayoutInflater li1 = getLayoutInflater();
                View v1 = li1.inflate(R.layout.menu_danwei, null);
                Danwei.create(this, v1);
                dialog1.setView(v1);
                dialog1.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog1.show();
                break;
            case R.id.item2:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                dialog2.setTitle("进制转换");
                LayoutInflater li2 = getLayoutInflater();
                View v2 = li2.inflate(R.layout.menu_jinzhi, null);
                Jinzhi.create(this, v2);
                dialog2.setView(v2);
                dialog2.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog2.show();
                break;
            case R.id.item3:
                AlertDialog.Builder dialog3 = new AlertDialog.Builder(MainActivity.this);
                dialog3.setTitle("帮助");
                dialog3.setMessage("作者：2017011301");
                dialog3.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog3.show();
                break;
            default:
                Toast.makeText(this, "错误：未知操作！", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = this.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        if (height > width) {
            setContentView(R.layout.activity_main);
            readItem();
            setActions();
            计算器.setOutPut(显示栏);
        } else {
            setContentView(R.layout.activity_main_h);
            readItem_h();
            setActions_h();
            计算器.setOutPut(显示栏);
        }
        显示栏.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        显示栏.setSingleLine(false);
        显示栏.setHorizontallyScrolling(false);
    }

    private void setActions() {
        显示栏.setShowSoftInputOnFocus(false);
        显示栏.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        显示栏.setTextIsSelectable(true);
        View.OnClickListener action = new View.OnClickListener() {
            public void onClick(View view) {
                计算器.tryInput(((Button) view).getText());
            }
        };
        for (int i = 0; i < 19; i++) {
            按钮[i].setOnClickListener(action);
        }
    }

    private void setActions_h() {
        显示栏.setShowSoftInputOnFocus(false);
        显示栏.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        显示栏.setTextIsSelectable(true);
        View.OnClickListener action = new View.OnClickListener() {
            public void onClick(View view) {
                计算器.tryInput(((Button) view).getText());
            }
        };
        for (int i = 0; i < 34; i++) {
            按钮[i].setOnClickListener(action);
        }
    }

    private void readItem_h() {
        readItem();
        按钮[19] = (Button) findViewById(R.id.阶乘);
        按钮[20] = (Button) findViewById(R.id.立方);
        按钮[21] = (Button) findViewById(R.id.tan);
        按钮[22] = (Button) findViewById(R.id.log);
        按钮[23] = (Button) findViewById(R.id.PI);
        按钮[24] = (Button) findViewById(R.id.右括号);
        按钮[25] = (Button) findViewById(R.id.平方);
        按钮[26] = (Button) findViewById(R.id.cos);
        按钮[27] = (Button) findViewById(R.id.ln);
        按钮[28] = (Button) findViewById(R.id.根号);
        按钮[29] = (Button) findViewById(R.id.左括号);
        按钮[30] = (Button) findViewById(R.id.倒数);
        按钮[31] = (Button) findViewById(R.id.sin);
        按钮[32] = (Button) findViewById(R.id.e);
        按钮[33] = (Button) findViewById(R.id.乘方);
    }

    private void readItem() {
        按钮[0] = (Button) findViewById(R.id.按钮0);
        按钮[1] = (Button) findViewById(R.id.按钮1);
        按钮[2] = (Button) findViewById(R.id.按钮2);
        按钮[3] = (Button) findViewById(R.id.按钮3);
        按钮[4] = (Button) findViewById(R.id.按钮4);
        按钮[5] = (Button) findViewById(R.id.按钮5);
        按钮[6] = (Button) findViewById(R.id.按钮6);
        按钮[7] = (Button) findViewById(R.id.按钮7);
        按钮[8] = (Button) findViewById(R.id.按钮8);
        按钮[9] = (Button) findViewById(R.id.按钮9);
        按钮[10] = (Button) findViewById(R.id.按钮百分号);
        按钮[11] = (Button) findViewById(R.id.按钮点);
        按钮[12] = (Button) findViewById(R.id.按钮加);
        按钮[13] = (Button) findViewById(R.id.按钮减);
        按钮[14] = (Button) findViewById(R.id.按钮乘);
        按钮[15] = (Button) findViewById(R.id.按钮除);
        按钮[16] = (Button) findViewById(R.id.按钮退格);
        按钮[17] = (Button) findViewById(R.id.按钮清除);
        按钮[18] = (Button) findViewById(R.id.按钮等于);
        显示栏 = (EditText) findViewById(R.id.显示栏);
    }
}
