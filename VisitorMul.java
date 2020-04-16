package com.company;
public class VisitorMul implements Visitor {
    private Scalar ans;

    @Override
    public void visit(realScalar real, realScalar real2) {
        ans=new realScalar(real.getVal()*real2.getVal());
    }

    @Override
    public void visit(realScalar real, rationalScalar ratio) {
        ans=null;
    }

    @Override
    public void visit(rationalScalar ratio, realScalar real) {
        ans=null;
    }

    @Override
    public void visit(rationalScalar ratio, rationalScalar ratio2) {
        ans = new rationalScalar(ratio.getA() * ratio2.getA(), ratio.getB() * ratio2.getB());
    }


    public Scalar getAns() {
        return this.ans;
    }
}