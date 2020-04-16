package com.company;
    public class VisitorAdd implements Visitor{
        private Scalar ans;
        @Override
        public void visit(realScalar real, realScalar real2) {
            ans=new realScalar(real.getVal()+real2.getVal());
        }

        @Override
        public void visit(realScalar real, rationalScalar ratio) {
            ans=null;
        }

        @Override
        public void visit(rationalScalar ratio, realScalar real) {
            ans=null;
        }

        public void visit(rationalScalar s1, rationalScalar s2) {
            ans= new rationalScalar(s1.getA()*s2.getB()+s1.getB()*s2.getA(),s1.getB()*s2.getB());
        }

        public Scalar getAns()
        {
        return this.ans;
        }
    }
