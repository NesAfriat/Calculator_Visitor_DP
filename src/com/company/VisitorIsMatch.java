package com.company;

public class VisitorIsMatch implements Visitor {
private boolean isMatch;
    @Override
    public void visit(realScalar real, realScalar real2) {
        isMatch=true;
    }

    @Override
    public void visit(realScalar real, rationalScalar ratio) {
        isMatch=false;
    }

    @Override
    public void visit(rationalScalar ratio, realScalar real) {
        isMatch=false;
    }

    @Override
    public void visit(rationalScalar ratio, rationalScalar ratio2) {
        isMatch=true;
    }
    public boolean getIsMatch()
    {
        return this.isMatch;
    }
}
