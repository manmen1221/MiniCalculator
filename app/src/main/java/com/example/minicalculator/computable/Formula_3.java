package com.example.minicalculator.computable;

import com.example.minicalculator.computable.error.ComputException;
import com.example.minicalculator.computable.error.NumException;

public class Formula_3 implements Computable {//乘方
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

    public Computable a = null, b = null;

    public void add(Computable value) {
        a = value;
    }

    public double getValue() throws ComputException {
        System.out.println("乘方：" + a.getValue() + "的" + b.getValue() + "次方");////////////////
        return Math.pow(a.getValue(), b.getValue());
    }

    public int create(String string, int i) throws ComputException, NumException {
        for (; ; ) {                                                                                    //遍历
            if (i >= string.length())
                return i;
            char tempc = string.charAt(i);
            if (tempc == '－' && b == null) {
                i++;
                continue;
            }
            if (tool.charIsNum(tempc)) {                                                              //数字
                boolean ne = false;
                if (i - 1 >= 0) if (string.charAt(i - 1) == '－') ne = true;
                if (tempc == 'π') {
                    b = new PI();
                    if (ne) b.setNegative(true);
                    i++;
                    continue;
                } else if (tempc == 'e') {
                    b = new E();
                    if (ne) b.setNegative(true);
                    i++;
                    continue;
                } else {
                    b = new Number();
                    if (ne) b.setNegative(true);
                    i = ((Number) b).create(string, i);
                    continue;
                }
            } else if (tool.charIs3(tempc)) {                                                           //乘方
                boolean ne = false;
                if (i - 1 >= 0) if (string.charAt(i - 1) == '－') ne = true;
                Computable tempBack = b;
                b = new Formula_3();
                if (ne) b.setNegative(true);
                ((Formula_3) b).add(tempBack);
                i++;
                i = ((Formula_3) b).create(string, i);
                continue;
            } else if (tool.charIs4(tempc)) {                                                          //括号
                boolean ne = false;
                if (i - 1 >= 0) if (string.charAt(i - 1) == '－') ne = true;
                b = new Formula_1();
                if (ne) b.setNegative(true);
                ((Formula_1) b).speci = tempc + "";
                b.setNeedBrackets(true);
                i++;
                i = ((Formula_1) b).create(string, i);
                return i;
            } else {
                return i;
            }
        }
    }
}
