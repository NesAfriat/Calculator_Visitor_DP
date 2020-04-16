package com.company;

import java.awt.*;

public interface Visitor {
    public void visit (realScalar real,realScalar real2);
    public void visit(realScalar real, rationalScalar ratio);
    public void visit (rationalScalar ratio,realScalar real);
    public void visit(rationalScalar ratio, rationalScalar ratio2);
}
