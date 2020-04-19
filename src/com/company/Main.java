package com.company;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
 String p = "-2 -1 0 3";
 String p2 = "0 3.2 -5";

 Polynomial poly= new Polynomial();
 poly.build('R',p);
        Polynomial poly2= new Polynomial();
        poly2.build('R',p2);
 System.out.println(poly.mul(poly2));

    }
}
