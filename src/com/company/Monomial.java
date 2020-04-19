package com.company;

public class Monomial {
    private Scalar coe;
    private int exp;

    public Monomial(Scalar s,int exp){
        this.coe=s;
        this.exp=exp;
    }

    public boolean isMatch(Monomial m)
    {
        return coe.isMatch(m.coe);
    }
    public boolean canAdd(Monomial m)
    {
        return (isMatch(m)&exp==m.exp);
    }
    public Monomial add(Monomial m)
    {
        if (!this.canAdd(m))
            return null;
        return new Monomial(this.coe.add(m.coe),exp);
    }
    public Monomial mul(Monomial m)
    {
        if (!this.isMatch(m))
            return null;
        return new Monomial(this.coe.mul(m.coe),exp*m.exp);
    }
    public Scalar evaluate(Scalar scalar)
    {
        Scalar res=scalar.power(exp);
        res=res.mul(coe);
        return res;
    }
    public Monomial derivative()
    {
        realScalar real=new realScalar(0.5);
        if (exp==0) {
            if(real.isMatch(coe))
            return new Monomial(new realScalar(0), 1);
            else return new Monomial(new rationalScalar(0,1),0);
        }
        if (exp==1)
        {
            return new Monomial(this.coe.mul(exp),0);
        }
        return new Monomial(this.coe.mul(exp),exp-1);
    }
    public int sign()
    {
        return coe.sign();
    }
    public String toString (){
        String output="";
        if (!coe.toString().equalsIgnoreCase("1")&exp!=0||(exp==0))
        {
            output=output+coe.toString();
        }
        if (exp>0) {
            output = output + "x";
            if (exp > 1) {
                output = output + "^" + exp;
            }
        }
        return output;
    }
    public Scalar getScalar()
    {
        return this.coe;
    }
    public int getExp()
    {
        return this.exp;
    }

}
