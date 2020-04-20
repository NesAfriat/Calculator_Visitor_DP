package com.company;

public interface Visitor {
    public void visit (RealScalar real, RealScalar real2);
    public void visit(RealScalar real, RationalScalar ratio);
    public void visit (RationalScalar ratio, RealScalar real);
    public void visit(RationalScalar ratio, RationalScalar ratio2);
}
