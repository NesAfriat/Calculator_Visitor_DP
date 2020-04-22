package com.company;


import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Monomial> polynom;
    public Polynomial() {
        this.polynom=new TreeMap<>();
    }

    public Polynomial build(char type, String input) {
        Integer exp = 0;
        Scalar zeroReal = new RealScalar(0);
        Scalar zeroRational = new RationalScalar(0, 1);
        Monomial m;
        if (input.equals("0"))
        {
            if (type == 'Q')
                m = new Monomial(zeroRational, exp);
            else
                m = new Monomial(zeroReal, exp);
            getPolynom().put(exp, m);
            return this;
        }
        int[] ratioConvert= new int[2];
        for (String mono : input.split(" ")) {
            if (mono.equals("0")) {
                if (type == 'Q')
                    m = new Monomial(zeroRational, exp);
                else
                    m = new Monomial(zeroReal, exp);
                getPolynom().put(exp, m);
            } else {
                if (type == 'Q') {
                    ratioConvert= stringToRatio(mono);
                    RationalScalar rt = new RationalScalar(ratioConvert[0], ratioConvert[1]);
                    m = new Monomial(rt, exp);
                    getPolynom().put(exp, m);
                } else {
                    RealScalar s = new RealScalar(stringToDouble(mono));
                    m = new Monomial(s, exp);
                    getPolynom().put(exp, m);
                }
            }
            exp++;
        }
        if (type == 'Q') {
            getPolynom().put(-1, null); //recognition of rational scalar type polynom
        } else {
            getPolynom().put(-2, null);//recognition of real scalar type polynom
        }
        return this;
    }

    private double stringToDouble(String s) {
        boolean minus = false;
        double ans = Double.parseDouble(s);
        return ans;
    }

    private int[] stringToRatio(String s) {
        int[] ans = new int[2];
        if (s.contains("/")) {
            String[] numbers = s.split("/");
            ans[0] = Integer.parseInt(numbers[0]);
            ans[1] = Integer.parseInt(numbers[1]);
        }
        else {
            ans[0] = Integer.parseInt(s);
            ans[1] = 1;
        }
        return ans;
    }

    public TreeMap<Integer, Monomial> getPolynom() {
        return this.polynom;
    }

    public boolean isMatch(Polynomial p) {
        if (this.polynom.isEmpty() || p.getPolynom().isEmpty())
            throw new IllegalArgumentException("un-exist polynom");
        if (this.getType() == p.getType()) //if both of their first keys are -2 or -1 their type are the same
            return true;
        else
            return false;
    }

    public Polynomial add(Polynomial p) {
        if (!isMatch(p))
            return null;
        TreeMap<Integer, Monomial> otherP = p.getPolynom();
        int minSize = Math.min(getPolynom().size(), otherP.size());
        Polynomial ans = new Polynomial();
        String sum = "", addition;
        int index = 0;
        for (int i = 0; i < minSize - 1; i++) {
            Monomial tmp = getPolynom().get(i).add(otherP.get(i));
            addition= tmp.getScalar().toString();
            if(addition.contains("("))
                addition=addition.substring(1,addition.length()-1);
            sum = sum + " " + addition;
            index = i + 1;
        }
        while (index < getPolynom().size() - 1) {
            addition=  getPolynom().get(index).getScalar().toString();
            if(addition.contains("("))
                addition=addition.substring(1,addition.length()-1);
            sum = sum + " " + addition;
            index++;
        }
        while (index < otherP.size() - 1) {
            addition=otherP.get(index).getScalar().toString();
            if(addition.contains("("))
                addition=addition.substring(1,addition.length()-1);
            sum = sum + " " + addition;
            index++;
        }
        ans.build(this.getType(), sum.substring(1));
        return ans;
    }

    private char getType() {


        if (getPolynom().containsKey(-1))
            return 'Q';
        else
            return 'R';
    }

    public Polynomial mul(Polynomial p) {
        if (!isMatch(p) || p.getPolynom().isEmpty())
            return null;
        TreeMap<Integer, Monomial> otherP = p.getPolynom();
        Polynomial ans = new Polynomial();
        Scalar[] res;
        Scalar zero;
        int maxSize= otherP.lastKey() + getPolynom().lastKey()+1;
        if(getType()=='Q') {
            res = new RationalScalar[maxSize];
            zero = new RationalScalar(0, 1);
        }
        else {
            res = new RealScalar[maxSize];
            zero=new RealScalar(0);
        }
        for (int i = 0; i < maxSize; i++)
            res[i]=zero;
        for (int i = 0; i <getPolynom().size() - 1; i++)
            for (int j = 0; j < otherP.size() - 1; j++) {
                Monomial tmpMono = getPolynom().get(i).mul(otherP.get(j));
                res[tmpMono.getExp()]=res[tmpMono.getExp()].add(tmpMono.getScalar());
            }
        String s="",addition;
        for(int i=0; i<maxSize; i++) {
            addition = res[i].toString();
            if (addition.contains("("))
                addition= addition.substring(1,addition.length()-1);
            s = s + addition + " ";
        }
        ans.build(getType(), s);
        return ans;
    }

    public Scalar evaluate(Scalar s)
    {
        Monomial evaluate=null;
        Scalar ans= getPolynom().get(0).getScalar();
        for(int i=1; i<getPolynom().size()-1; i++) {
            evaluate = new Monomial(getPolynom().get(i).evaluate(s), 1);
            ans = ans.add(evaluate.getScalar());
        }
        return ans;
    }

    public Polynomial derivative() {
        Polynomial ans = new Polynomial();
        Monomial tmp;
        int exp;
        char type = getType();
        Scalar coe;
        String polyStr="", addition;

        for (int i = 1; i < getPolynom().size() - 1; i = i + 1) {
            tmp = this.getPolynom().get(i);
            tmp = tmp.derivative();
            exp = tmp.getExp();
            if (tmp.getScalar().sign() == 0)
                polyStr = polyStr + "0" + " ";
            else {
                addition=tmp.getScalar().toString();
                if (addition.contains("("))
                    addition= addition.substring(1,addition.length()-1);
                polyStr = polyStr + addition + " ";
            }
        }
        if(getPolynom().size()==2)
            polyStr = "0";
        ans= ans.build(type,polyStr);
        return ans;
    }

    public String toString() {
        String ans = "";
        if(getPolynom().size()==1)
            ans = getPolynom().get(0).toString();
        Monomial m;
            for (int i = 0; i < getPolynom().size() - 1; i++) {
                m = getPolynom().get(i);
                if (m.getScalar().sign() == 1)
                    ans = ans + "+" + getPolynom().get(i).toString();
                if (m.getScalar().sign() == -1)
                    ans = ans + getPolynom().get(i).toString();
            }
        while (ans.charAt(0)== '+')
            ans= ans.substring(1);
        return ans;
    }
}
