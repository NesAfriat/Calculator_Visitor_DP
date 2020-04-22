package com.company;

import java.text.DecimalFormat;

public class RealScalar implements Scalar {
    private double v;
    public RealScalar(double v)
    {
        this.v=v;
    }
    public double getVal()
    {
        return this.v;
    }

    public boolean isMatch(Scalar s)
    {
        VisitorIsMatch isMatchVisiter= new VisitorIsMatch();
        s.accept(isMatchVisiter, this);
        return isMatchVisiter.getIsMatch();
    }
    public Scalar add(Scalar s)
    {
        VisitorAdd visiterAdd=new VisitorAdd();
        s.accept(visiterAdd, this);
        return visiterAdd.getAns();
    }
    public Scalar power(int exp)
    {
        double newV=1.0;
        for(int i=0; i<exp; i++)
            newV= newV*v;
        RealScalar ans= new RealScalar(newV);
        return ans;
    }
    public Scalar mul(Scalar s)
    {
        VisitorMul visiterMul=new VisitorMul();
        s.accept(visiterMul, this);
        return visiterMul.getAns();
    }
    public Scalar mul(int i)
    {
        return new RealScalar(this.getVal()*i);
    }

    public int sign(){
        if (this.getVal()>0)
            return 1;
        else
        if(getVal()<0)
            return -1;
        return 0;
    }

    public String toString()
    {
        String output="###.###";
        DecimalFormat df= new DecimalFormat(output);
        String format= df.format(getVal());
        return format;
    }

    @Override
    public void accept(Visitor visit, RealScalar real) {
        visit.visit(this, real);
    }

    @Override
    public void accept(Visitor visit, RationalScalar ratio) {
        visit.visit(this, ratio);
    }
}
