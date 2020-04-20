package com.company;

public class RationalScalar implements Scalar {
    private int a;
    private int b;

    public RationalScalar(int a, int b)
    {
        this.a=a;
        this.b=b;
    }
    public boolean isMatch (Scalar s)
    {
        VisitorIsMatch vim= new VisitorIsMatch();
        s.accept(vim, this);
        return vim.getIsMatch();
    }
    public int getA(){return this.a;}
    public int getB(){return this.b;}

    public Scalar add(Scalar s)
    {
        VisitorAdd va=new VisitorAdd();
        s.accept(va, this);
        return va.getAns();
    }
    public Scalar mul(Scalar s)
    {
        VisitorMul vm=new VisitorMul();
        s.accept(vm, this);
        return vm.getAns();
    }
    public Scalar mul(int i)
    {
        return new RationalScalar(this.a*i,this.b);
    }

    public Scalar power (int exp)
    {
        RationalScalar res;
        if (exp==0) res=new RationalScalar(1,1);
        else {
            int a1 = this.a;
            int b1 = this.b;
            for (int i = 1; i < exp; i = i + 1) {
                a1 = a1 * a;
                b1 = b1 * b;
            }
            res=new RationalScalar(a1,b1);
        }
        return res;
    }
    public int sign(){
        if ((this.a<0&this.b>0)||(this.a>0&this.b<0))
            return -1;
        else if (a==0)
            return 0;
        else return 1;
    }
    public String toString()
    {
        String output="";
        if (a%b==0)
        {
            output=output+(a/b);
        }
        else
        {
            if (this.sign()==-1)
            {
                if (b<0)
                    output=output+"(-"+a+"/"+(-b)+")";
                else output=output+"("+a+"/"+b+")";
            }
        }
        return output;
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
