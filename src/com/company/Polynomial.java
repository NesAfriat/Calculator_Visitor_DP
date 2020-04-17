package com.company;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private Map<Integer,Monomial> polynom;
    public Polynomial()
    {
    }
    public Polynomial build(char type, String input)
    {

         this.polynom = new TreeMap<>();
        for (String mono : input.split(" ")) {

        }
    }
    public Polynomial add(Polynomial p)
    {
        return null;
    }
    public boolean isMatch(char type, String input)
    {
        return false;
    }
    public Polynomial mul(Polynomial p)
    {
        return null;
    }
    public Scalar evaluate(Scalar s)
    {
        return null;
    }
    public Polynomial derivative()
    {
        return null;
    }
    public String toString()
    {
        return null;
    }
}
