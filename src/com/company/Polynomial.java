package com.company;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Monomial> polynom;

    public Polynomial() {
    }

    public Polynomial build(char type, String input) {
        polynom = new TreeMap<>();
        int exp = 0;
        Scalar zeroReal = new realScalar(0);
        Scalar zeroRational = new rationalScalar(0, 1);
        Monomial m;
        for (String mono : input.split(" ")) {
            if (mono == "0") {
                if (type == 'Q')
                    m = new Monomial(zeroRational, exp);
                else
                    m = new Monomial(zeroReal, exp);
                polynom.put(exp, m);
            } else {
                if (type == 'Q') {
                    rationalScalar s = new rationalScalar(stringToRatio(mono)[0], stringToRatio(mono)[1]);
                    m = new Monomial(s, exp);
                    polynom.put(exp, m);
                } else {
                    realScalar s = new realScalar(stringToDouble(mono));
                    m = new Monomial(s, exp);
                    polynom.put(exp, m);
                }
            }
            exp++;
        }
        if (type == 'Q') {
            polynom.put(-1, null); //recognition of rational scalar type polynom
        } else {
            polynom.put(-2, null);//recognition of real scalar type polynom
        }
        return this;
    }

    private double stringToDouble(String s) {
        boolean minus = false;
        double ans = Double.parseDouble(s);
        return ans;
    }

    private int[] stringToRatio(String s) {
        boolean minus = false;
        if (s.charAt(0) == '-') {
            minus = true;
            s.substring(1);
        }
        String[] ratio = s.split("/");
        int[] ans = new int[2];
        ans[0] = Integer.parseInt(ratio[0]);
        ans[1] = Integer.parseInt(ratio[0]);
        if (minus)
            ans[0] = -ans[0];
        return ans;
    }

    public TreeMap<Integer, Monomial> getPolynom() {
        return this.polynom;
    }

    public boolean isMatch(Polynomial p) {
        if (this.polynom.isEmpty() || p.getPolynom().isEmpty())
            throw new IllegalArgumentException("un-exist polynom");
        if (this.polynom.firstKey() == p.getPolynom().firstKey()) //if both of their first keys are -2 or -1 their type are the same
            return true;
        else
            return false;
    }

    public Polynomial add(Polynomial p) {
        if (!isMatch(p))
            return null;
        TreeMap<Integer, Monomial> otherP = p.getPolynom();
        int minSize = Math.min(polynom.size(), otherP.size());
        Polynomial ans = new Polynomial();
        String sum = "";
        int index = 0;
        for (int i = 0; i < minSize - 1; i++) {
            Monomial tmp = polynom.get(i).add(otherP.get(i));
            sum = sum + " " + tmp.getScalar().toString();
            index = i + 1;
        }
        while (index < polynom.size() - 1) {
            sum = sum + " " + polynom.get(index).getScalar().toString();
            index++;
        }
        while (index < otherP.size() - 1) {
            sum = sum + " " + otherP.get(index).getScalar().toString();
            index++;
        }
        ans.build(this.getType(), sum.substring(1));
        return ans;
    }

    private char getType() {
        if (polynom.containsKey(-1))
            return 'Q';
        else
            return 'R';
    }

    public Polynomial mul(Polynomial p) {
        if (!isMatch(p) || p.getPolynom().isEmpty())
            return null;
        TreeMap<Integer, Monomial> otherP = p.getPolynom();
        Polynomial ans = new Polynomial();
        ans.build(getType(), "0");
        for (int i = 0; i < polynom.size() - 1; i++)
            for (int j = 0; j < otherP.size() - 1; j++) {
                Monomial tmpMono = polynom.get(i).mul(otherP.get(j));
                Polynomial tmpPol = new Polynomial();
                String tmpAdd = "";
                for (int k = 0; k < tmpMono.getExp(); k++) {
                    tmpAdd = tmpAdd + "0" + " ";
                }
                tmpPol.build(getType(), tmpAdd + tmpMono.getScalar().toString());
                ans = ans.add(tmpPol);
            }
        return ans;
    }

    public Scalar evaluate(Scalar s)
    {
        Monomial evaluate=null;
        Scalar ans= polynom.get(0).getScalar();
        for(int i=1; i<polynom.size()-1; i++) {
            evaluate = new Monomial(polynom.get(i).evaluate(s), 1);
            ans = ans.add(evaluate.getScalar());
        }
        return ans;
    }

    public Polynomial derivative()
    {
        Polynomial ans = new Polynomial();
        Monomial tmp;
        for (int i = 0; i < ans.getPolynom().size(); i = i + 1)
        {
            tmp=this.getPolynom().get(i);
            ans.getPolynom().put(i,tmp.derivative());//maybe we need to put in i-1-החזקה הייתה 2 ובפולינום החדש תהיה 1
        }
        return ans;
    }

    public String toString() {
        String ans = "";
        Monomial m = null;
        for (int i = 0; i < polynom.size() - 1; i++) {
            m = polynom.get(i);
                if(m.getScalar().sign() == 1)
                    ans = ans + "+" +polynom.get(i).toString();
                if(m.getScalar().sign()==-1)
                ans = ans + polynom.get(i).toString();
        }
        if (ans.charAt(0)== '+')
            ans= ans.substring(1);
        return ans;
    }
}
