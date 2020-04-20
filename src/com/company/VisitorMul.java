package com.company;
public class VisitorMul implements Visitor {
    private Scalar ans;

    @Override
    public void visit(RealScalar real, RealScalar real2) {
        ans=new RealScalar(real.getVal()*real2.getVal());
    }

    @Override
    public void visit(RealScalar real, RationalScalar ratio) {
        ans=null;
    }

    @Override
    public void visit(RationalScalar ratio, RealScalar real) {
        ans=null;
    }

    @Override
    public void visit(RationalScalar ratio, RationalScalar ratio2) {
        ans = new RationalScalar(ratio.getA() * ratio2.getA(), ratio.getB() * ratio2.getB());
    }


    public Scalar getAns() {
        return this.ans;
    }
}