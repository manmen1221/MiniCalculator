package com.example.minicalculator.computable;

import com.example.minicalculator.computable.error.ComputException;

public interface Computable {
    boolean getNeedBrackets();//需要后括号

    void setNeedBrackets(boolean value);

    boolean getReciprocal();//倒数 乘除法

    void setReciprocal(boolean value);

    boolean getNegative();//负数 加减法

    void setNegative(boolean value);

    double getValue() throws ComputException;
}