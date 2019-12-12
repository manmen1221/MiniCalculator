package com.example.minicalculator.computable;

import com.example.minicalculator.computable.error.ComputException;
import com.example.minicalculator.computable.error.NumException;

import java.util.ArrayList;
import java.util.List;

public class Formula_2 implements Computable {//乘除
    private boolean needBrackets = false;
    private boolean reciprocal = false;
    private boolean negative = false;

    public boolean getNeedBrackets() {
        return needBrackets;
    }

    public void setNeedBrackets(boolean value) {
        this.needBrackets = value;
    }

    public boolean getReciprocal() {
        return reciprocal;
    }

    public void setReciprocal(boolean value) {
        this.reciprocal = value;
    }

    public boolean getNegative() {
        return negative;
    }

    public void setNegative(boolean value) {
        this.negative = value;
    }

    private List<Computable> formula = new ArrayList();

    public void add(Computable value) {
        formula.add(value);
    }

    public double getValue() throws ComputException {
        if (formula.size() == 0) throw new ComputException();
        String did = "乘法：";////////////////////////////////
        double temp = formula.get(0).getValue();
        if (formula.get(0).getReciprocal()) did += "除";//////////////
        else did += "乘";/////////////////////////////////////////
        did += formula.get(0).getValue();///////////////////////
        for (int i = 1; i < formula.size(); i++) {
            if (!formula.get(i).getReciprocal()) {
                temp = temp * formula.get(i).getValue();
                did += "乘" + formula.get(i).getValue() + "（" + temp + "）";///////////////////////
            } else {
                try {
                    temp = temp / formula.get(i).getValue();
                    did += "除" + formula.get(i).getValue() + "（" + temp + "）";///////////////////////
                } catch (Exception e) {
                    throw new ComputException();
                }
            }
        }
        System.out.println(did);////////////////////////
        return temp;
    }

    public int create(String string, int i) throws ComputException, NumException {
        String tempReciprocal = "";                                                                   //当前符号
        Computable tempComputable = null;                                                             //当前数或式
        for (; ; ) {                                                                                    //遍历
            if (i >= string.length()) {                                                                 //末尾结束
                if (tempComputable == null) {                                                           //当前无数字时
                    return i;
                } else {
                    if (tempReciprocal.equals("×"))                                                //录入前一次符合和结果
                        tempComputable.setReciprocal(false);
                    else if (tempReciprocal.equals("÷"))
                        tempComputable.setReciprocal(true);
                    formula.add(tempComputable);
                    return i;
                }
            }
            char tempc = string.charAt(i);
            if (tempc == ')') {                                                                         //括号结束
                if (needBrackets) {
                    if (tempComputable == null) {                                                       //当前无数字时
                        if (!tempReciprocal.equals(""))
                            throw new ComputException();
                    } else if (tempReciprocal.equals("×"))                                           //录入前一次符合和结果
                        tempComputable.setReciprocal(false);
                    else if (tempReciprocal.equals("÷"))
                        tempComputable.setReciprocal(true);
                    formula.add(tempComputable);
                    i++;
                    return i;
                } else throw new ComputException();
            } else if (tempc == '－' && tool.charIs2(string.charAt(i - 1))) {
                i++;
                continue;
            } else if (tool.charIsNum(tempc)) {                                                         //数字
                boolean ne = false;
                if (i - 1 >= 0) if (string.charAt(i - 1) == '－') ne = true;
                if (tempc == 'π') {
                    tempComputable = new PI();
                    if (ne) tempComputable.setNegative(true);
                    i++;
                    continue;
                } else if (tempc == 'e') {
                    tempComputable = new E();
                    if (ne) tempComputable.setNegative(true);
                    i++;
                    continue;
                } else {
                    tempComputable = new Number();
                    if (ne) tempComputable.setNegative(true);
                    i = ((Number) tempComputable).create(string, i);
                    continue;
                }
            } else if (tool.charIs1(tempc)) {                                                           //加减
                if (tempReciprocal.equals("×"))                                                    //录入前一次符合和结果
                    tempComputable.setReciprocal(false);
                else if (tempReciprocal.equals("÷"))
                    tempComputable.setReciprocal(true);
                formula.add(tempComputable);
                return i;
            } else if (tool.charIs2(tempc)) {                                                           //乘除
                if (tempComputable == null) {                                                           //当前无数字时
                    if (tempReciprocal.equals(""))
                        tempReciprocal = tempc + "";
                    i++;
                    continue;
                }
                if (tempReciprocal.equals("×"))                                                     //录入前一次符合和结果
                    tempComputable.setReciprocal(false);
                else if (tempReciprocal.equals("÷")) tempComputable.setReciprocal(true);
                formula.add(tempComputable);
                tempComputable = null;
                tempReciprocal = tempc + "";
                i++;
                continue;
            } else if (tool.charIs3(tempc)) {                                                           //乘方
                boolean ne = false;
                if (i - 1 >= 0) if (string.charAt(i - 1) == '－') ne = true;
                Computable tempBack = tempComputable;
                tempComputable = new Formula_3();
                if (ne) tempComputable.setNegative(true);
                ((Formula_3) tempComputable).add(tempBack);
                i++;
                i = ((Formula_3) tempComputable).create(string, i);
                continue;
            } else if (tool.charIs4(tempc)) {                                                          //括号
                boolean ne = false;
                if (i - 1 >= 0) if (string.charAt(i - 1) == '－') ne = true;
                tempComputable = new Formula_1();
                if (ne) tempComputable.setNegative(true);
                ((Formula_1) tempComputable).speci = tempc + "";
                tempComputable.setNeedBrackets(true);
                i++;
                i = ((Formula_1) tempComputable).create(string, i);
                continue;
            } else throw new ComputException();
        }
    }
}
