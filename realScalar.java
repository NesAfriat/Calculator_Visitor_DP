package com.company;
public class realScalar implements Scalar {
    private double v;
    public realScalar(double v)
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
    public Scalar mul(Scalar s)
    {
        VisitorMul visiterMul=new VisitorMul();
        s.accept(visiterMul, this);
        return visiterMul.getAns();
    }
    public Scalar mul(int i)
    {
         return new realScalar(this.getVal()*i);
    }
    public Scalar power (int exp)
    {
        return null;
    }
    public int sign(){
       if (this.getVal()>=0)
           return 1;
       else
           return -1;
    }

    public String toString()
    {
        String output="";
        Double v= this.getVal();
        output= v.toString();
        String[] parts = output.split(".");
        if (parts[1].length()>3);
        parts[1].substring(0,4);
        return parts[0] + parts[1];
    }

    @Override
    public void accept(Visitor visit, realScalar real) {
        visit.visit(this, real);
    }

    @Override
    public void accept(Visitor visit, rationalScalar ratio) {
        visit.visit(this, ratio);
    }
}
