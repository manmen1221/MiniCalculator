package com.example.minicalculator.computable;

public class tool {
    public static String addChengHao(String string) {
        String fin = "";
        for (int i = 0; i < string.length(); i++) {
            char temp = string.charAt(i);
            if (charIsChang(temp) || charIs4(temp)) {
                if (i - 1 >= 0) {
                    char before = string.charAt(i - 1);
                    if (!(charIs1(before) || charIs2(before) || charIs3(before) || charIs4(before)))
                        fin += "×";
                }
            }
            fin += temp;
            if (charIsChang(temp) || temp == ')' || temp == '%') {
                if (i + 1 < string.length()) {
                    char after = string.charAt(i + 1);
                    if (!(charIs1(after) || charIs2(after) || charIs3(after) || after == ')'))
                        fin += "×";
                }
            }
        }
        return fin;
    }

    public static boolean charIsChang(char temp) {
        if (temp == 'π') return true;
        else if (temp == 'e') return true;
        else return false;
    }

    public static boolean charIsNum(char temp) {
        if (temp == '0') return true;
        else if (temp == '1') return true;
        else if (temp == '2') return true;
        else if (temp == '3') return true;
        else if (temp == '4') return true;
        else if (temp == '5') return true;
        else if (temp == '6') return true;
        else if (temp == '7') return true;
        else if (temp == '8') return true;
        else if (temp == '9') return true;
        else if (temp == '.') return true;
        else if (temp == 'π') return true;
        else if (temp == 'e') return true;
        else if (temp == '%') return true;
        else return false;
    }

    public static boolean charIs1(char temp) {
        if (temp == '＋') return true;
        else if (temp == '－') return true;
        else return false;
    }

    public static boolean charIs2(char temp) {
        if (temp == '×') return true;
        else if (temp == '÷') return true;
        else return false;
    }

    public static boolean charIs3(char temp) {
        if (temp == '^') return true;
        else return false;
    }

    public static boolean charIs4(char temp) {
        if (temp == '(') return true;
        else if (temp == 'N') return true;
        else if (temp == 'G') return true;
        else if (temp == 'S') return true;
        else if (temp == 'C') return true;
        else if (temp == 'T') return true;
        else if (temp == 'K') return true;
        else return false;
    }
}