package com.example.minicalculator.computable;

import com.example.minicalculator.computable.error.ComputException;
import com.example.minicalculator.computable.error.NumException;

import java.util.ArrayList;
import java.util.List;

public class Formula_1 implements Computable {   //加减法
    private boolean needBrackets = false;
    private boolean reciprocal = false;
    private boolean negative = false;
    public String speci = "";

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

    public int create(String string, int i) throws ComputException, NumException {
        String tempNegative = "";                                                                     //当前符号
        Computable tempComputable = null;                                                             //当前数或式
        for (; ; ) {                                                                                    //遍历
            if (i >= string.length()) {                                                                 //末尾结束
                if (tempComputable == null) {                                                           //当前无数字时
                    return i;
                } else {
                    if (tempNegative.equals("＋"))                                                  //录入前一次符合和结果
                        tempComputable.setNegative(false);
                    else if (tempNegative.equals("－"))
                        tempComputable.setNegative(true);
                    formula.add(tempComputable);
                    return i;
                }
            }
            char tempc = string.charAt(i);
            if (tempc == ')') {                                                                         //括号结束
                if (needBrackets) {
                    if (tempComputable == null) {                                                       //当前无数字时
                        if (!tempNegative.equals(""))
                            throw new ComputException();
                    } else if (tempNegative.equals("＋"))                                             //录入前一次符合和结果
                        tempComputable.setNegative(false);
                    else if (tempNegative.equals("－"))
                        tempComputable.setNegative(true);
                    formula.add(tempComputable);
                    i++;
                    return i;
                } else throw new ComputException();
            } else if (tool.charIsNum(tempc)) {                                                         //数字
                if (tempc == 'π') {
                    tempComputable = new PI();
                    i++;
                    continue;
                } else if (tempc == 'e') {
                    tempComputable = new E();
                    i++;
                    continue;
                } else {
                    tempComputable = new Number();
                    i = ((Number) tempComputable).create(string, i);
                    continue;
                }
            } else if (tool.charIs1(tempc)) {                                                           //加减
                if (tempComputable == null) {                                                           //当前无数字时
                    if (tempNegative.equals(""))
                        tempNegative = tempc + "";
                    i++;
                    continue;
                }
                if (tempNegative.equals("＋"))                                                       //录入前一次符合和结果
                    tempComputable.setNegative(false);
                else if (tempNegative.equals("－")) tempComputable.setNegative(true);
                //else throw new ComputException();
                formula.add(tempComputable);
                tempComputable = null;
                tempNegative = tempc + "";
                i++;
                continue;
            } else if (tool.charIs2(tempc)) {                                                           //乘除
                Computable tempBack = tempComputable;
                tempComputable = new Formula_2();
                ((Formula_2) tempComputable).add(tempBack);
                i = ((Formula_2) tempComputable).create(string, i);
                continue;
            } else if (tool.charIs3(tempc)) {                                                           //乘方
                Computable tempBack = tempComputable;
                tempComputable = new Formula_3();
                ((Formula_3) tempComputable).add(tempBack);
                i++;
                i = ((Formula_3) tempComputable).create(string, i);
                continue;
            } else if (tool.charIs4(tempc)) {                                                          //括号
                tempComputable = new Formula_1();
                ((Formula_1) tempComputable).speci = tempc + "";
                tempComputable.setNeedBrackets(true);
                i++;
                i = ((Formula_1) tempComputable).create(string, i);
                continue;
            } else throw new ComputException();
        }
    }

    public double getValue() throws ComputException {
        double temp = 0;
        String did = speci + "：";////////////////////////////////////////////////
        for (int i = 0; i < formula.size(); i++) {
            temp += formula.get(i).getValue();
            did += "加" + formula.get(i).getValue();//////////////////////
        }
        if (speci.equals("N")) temp = Math.log(temp);
        if (speci.equals("G")) temp = Math.log10(temp);
        if (speci.equals("S")) temp = Math.sin(temp);
        if (speci.equals("C")) temp = Math.cos(temp);
        if (speci.equals("T")) temp = Math.tan(temp);
        if (speci.equals("K")) temp = Math.sqrt(temp);
        System.out.println(did);///////////////////////////////////////
        return temp;
    }
}
