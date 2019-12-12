package com.example.minicalculator;

import android.widget.EditText;

import com.example.minicalculator.computable.Computable;
import com.example.minicalculator.computable.Formula_1;
import com.example.minicalculator.computable.Number;
import com.example.minicalculator.computable.error.ComputException;
import com.example.minicalculator.computable.error.NumException;
import com.example.minicalculator.computable.tool;



public class Calculator {
    static Calculator inside = null;
    EditText 显示栏;
    Computable model = null;
    String frontTemp = "", afterTemp = "", numTemp = "";

    Calculator() {
        inside = this;
    }

    private int getOutIndex(int index) {
        if (index >= numTemp.length()) {
            System.out.println("index=" + index);/////////////////////////////////////////////////////
            System.out.println("numTemp.length()=" + numTemp.length());/////////////////////////////////////////////////////
            System.out.println("numTemp=" + numTemp);/////////////////////////////////////////////////////
        }
        int did = 0;
        for (int i = 0; i < index; i++) {
            char tempc = numTemp.charAt(i);
            if (tool.charIs4(tempc)) {
                if (tempc == 'N') did += 3;
                else if (tempc == 'K') did += 2;
                else if (tempc == '(') did += 1;
                else did += 4;
            } else did += 1;
        }
        return did;
    }

    private int getRealIndex(int index) {
        int did = 0;
        for (int i = 0; i < numTemp.length(); i++) {
            if (i >= index) return i;
            char tempc = numTemp.charAt(i);
            if (tool.charIs4(tempc)) {
                if (tempc == 'N') did += 3;
                else if (tempc == 'K') did += 2;
                else if (tempc == '(') did += 1;
                else did += 4;
            } else did += 1;
            if (did >= index) return i + 1;
        }
        return numTemp.length();
    }

    public void setOutPut(EditText 显示栏) {
        this.显示栏 = 显示栏;
        refreshOutput();
    }

    private double getDouble() throws ComputException {
        return model.getValue();
    }

    private String getValue() {
        return numTemp.replaceAll("N", "ln(")
                .replaceAll("S", "sin(")
                .replaceAll("C", "cos(")
                .replaceAll("T", "tan(")
                .replaceAll("G", "log(")
                .replaceAll("K", "√(");
    }

    public void refreshOutput() {
        显示栏.setText(this.getValue());
        System.out.println("长度" + frontTemp.length());/////////////////////////////////////////////////////
        System.out.println("frontTemp=" + frontTemp);/////////////////////////////////////////////////////
        System.out.println(frontTemp);
        refreshSelection(getOutIndex(frontTemp.length()));
    }

    public void createModel(String string) throws ComputException, NumException {
        model = new Formula_1();
        String temp = tool.addChengHao(string);
        System.out.println("总式：" + temp);/////////////////////////////////////////////////////
        ((Formula_1) model).create(temp, 0);
    }

    private void numCheck() throws NumException {
        String temp = tool.addChengHao(numTemp);
        for (int i = 0; i < temp.length(); ) {
            char tempc = temp.charAt(i);
            if (tempc == 'π') {
                i++;
                continue;
            } else if (tempc == 'e') {
                i++;
                continue;
            } else if (tool.charIsNum(tempc)) {
                i = new Number().create(temp, i);
                continue;
            } else {
                i++;
                continue;
            }
        }
    }

    private void input(String input) {
        try {
            frontTemp = frontTemp + input;
            numTemp = frontTemp + afterTemp;
            numCheck();
            createModel(numTemp);
        } catch (NumException e) {
            model = null;
            frontTemp = frontTemp.substring(0, frontTemp.length() - input.length());
            numTemp = frontTemp + afterTemp;
            e.printStackTrace();
        } catch (ComputException e) {
            model = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshOutput();
    }

    private void tryInputNum(String input) {
        input(input);
    }

    private void removeLastSym() {
        if (frontTemp.length() == 0) return;
        char temp = frontTemp.charAt(frontTemp.length() - 1);
        if (tool.charIs1(temp) || tool.charIs2(temp))
            frontTemp = frontTemp.substring(0, frontTemp.length() - 1);
    }

    private void refreshSelection(int i) {
        显示栏.setSelection(i);
        显示栏.requestFocus();
    }

    private void tryInputSym(String input) {
        if (input.equals("－")) {
            if (frontTemp.length() >= 1)
                if (!tool.charIs2(frontTemp.charAt(frontTemp.length() - 1)))
                    removeLastSym();
            input(input);
        } else {
            removeLastSym();
            removeLastSym();
            if (frontTemp.length() > 0) input(input);
        }
    }


    private void clear() {
        model = null;
        numTemp = "";
        frontTemp = "";
        afterTemp = "";
        refreshOutput();
        return;
    }

    public void tryInput(CharSequence input) {
        int index = getRealIndex(显示栏.getSelectionStart());
        System.out.println("光标位置" + index);/////////////////////////////////////////////////////
        frontTemp = numTemp.substring(0, index);
        int index2 = getRealIndex(显示栏.getSelectionEnd());
        afterTemp = numTemp.substring(index2);
        String temp = input.toString();
        if (temp.equals("0")) tryInputNum(temp);
        else if (temp.equals("1")) tryInputNum(temp);
        else if (temp.equals("2")) tryInputNum(temp);
        else if (temp.equals("3")) tryInputNum(temp);
        else if (temp.equals("4")) tryInputNum(temp);
        else if (temp.equals("5")) tryInputNum(temp);
        else if (temp.equals("6")) tryInputNum(temp);
        else if (temp.equals("7")) tryInputNum(temp);
        else if (temp.equals("8")) tryInputNum(temp);
        else if (temp.equals("9")) tryInputNum(temp);

        else if (temp.equals("%")) tryInputNum(temp);
        else if (temp.equals("·")) tryInputNum(".");

        else if (temp.equals("＋")) tryInputSym(temp);
        else if (temp.equals("－")) tryInputSym(temp);
        else if (temp.equals("×")) tryInputSym(temp);
        else if (temp.equals("÷")) tryInputSym(temp);

        else if (temp.equals("C")) clear();

        else if (temp.equals("（")) input("(");
        else if (temp.equals("）")) input(")");
        else if (temp.equals("X！")) input("!");
        else if (temp.equals("X^3")) input("^(3)");
        else if (temp.equals("tan")) input("T");
        else if (temp.equals("log")) input("G");
        else if (temp.equals("PI")) input("π");
        else if (temp.equals("X^2")) input("^(2)");
        else if (temp.equals("cos")) input("C");
        else if (temp.equals("ln")) input("N");
        else if (temp.equals("√")) input("K");
        else if (temp.equals("1/X")) input("^(-1)");
        else if (temp.equals("sin")) input("S");
        else if (temp.equals("e")) input("e");
        else if (temp.equals("X^Y")) input("^(");


        else if (temp.equals("＝")) {
            try {
                double temp2 = this.getDouble();
                clear();
                if (Double.toString(temp2).equals("Infinity")) throw new ComputException();
                显示栏.setText(("＝" + temp2).replaceAll("-", "－"));
            } catch (Exception e) {
                clear();
                显示栏.setText("计算错误！");
            }
        } else if (temp.equals("←")) {
            if (frontTemp.length() > 0) {
                frontTemp = frontTemp.substring(0, frontTemp.length() - 1);
                numTemp = frontTemp + afterTemp;
                input("");
                refreshOutput();
                return;
            } else refreshOutput();
        } else return;
    }
}
