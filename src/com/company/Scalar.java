package com.company;
public interface Scalar {
    public boolean isMatch(Scalar s);
    public Scalar add(Scalar s);
    public Scalar mul(int i);
    public Scalar mul(Scalar s);
    public Scalar power(int exp);
    public int sign();
    public String toString();
    public void accept(Visitor visit, RealScalar real);
    public void accept(Visitor visit, RationalScalar ratio);
}
