package com.example.minicalculator.computable;

import com.example.minicalculator.computable.error.NumException;

public class Number implements Computable {
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

    private double value;

    public int create(String string, int i) throws NumException{
        String temp = "";
        for (; ; ) {
            if (i >= string.length()) {
                packTemp(temp);
                return i;
            }
            char tempc = string.charAt(i);
            if (tempc == '%') {
                if (temp.length() == 0) throw new NumException();
                packTemp(temp);
                value = value / 100;
                i++;
                return i;
            } else if (tempc == '!') {
                int intTemp = 0;
                try {
                    intTemp = Integer.parseInt(temp);
                } catch (Exception e) {
                    throw new NumException();
                }
                double v = 1;
                for (int j = 1; j <= intTemp; j++) {
                    v = v * j;
                }
                value = v*1;
                i++;
                return i;
            } else if (tool.charIsNum(tempc)) {
                temp += tempc;
                i++;
                continue;
            } else {
                packTemp(temp);
                return i;
            }
        }
    }

    private void packTemp(String temp) throws NumException {
        try {
            value = Double.parseDouble(temp);
        } catch (Exception e) {
            throw new NumException();
        }
    }

    public double getValue() {
        double temp = value;
        if (negative == true) temp = temp * (-1);
        return temp;
    }
}
