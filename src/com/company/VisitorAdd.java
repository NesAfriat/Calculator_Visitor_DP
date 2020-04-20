package com.company;
    public class VisitorAdd implements Visitor{
        private Scalar ans;
        @Override
        public void visit(RealScalar real, RealScalar real2) {
            ans=new RealScalar(real.getVal()+real2.getVal());
        }

        @Override
        public void visit(RealScalar real, RationalScalar ratio) {
            ans=null;
        }

        @Override
        public void visit(RationalScalar ratio, RealScalar real) {
            ans=null;
        }

        public void visit(RationalScalar s1, RationalScalar s2) {
            ans= new RationalScalar(s1.getA()*s2.getB()+s1.getB()*s2.getA(),s1.getB()*s2.getB());
        }

        public Scalar getAns()
        {
        return this.ans;
        }
    }
