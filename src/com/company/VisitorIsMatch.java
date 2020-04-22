package com.company;

public class VisitorIsMatch implements Visitor{
    private boolean isMatch;
    @Override
    public void visit(RealScalar real, RealScalar real2) {
        isMatch=true;
    }

    @Override
    public void visit(RealScalar real, RationalScalar ratio) {
        isMatch=false;
    }

    @Override
    public void visit(RationalScalar ratio, RealScalar real) {
        isMatch=false;
    }

    @Override
    public void visit(RationalScalar ratio, RationalScalar ratio2) {
        isMatch=true;
    }
    public boolean getIsMatch()
    {
        return this.isMatch;
    }
}
