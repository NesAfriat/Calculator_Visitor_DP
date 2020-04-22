package com.company;

public class Calculator {
    private static void printTitle(String s) {
        System.out.println("\n========== " + s + " ==========");
    }

    private static void printAdd(Polynomial p1, Polynomial p2, Polynomial res) {
        System.out.println("[" + p1 + "]+[" + p2 + "]=" + res);
    }

    private static void printMul(Polynomial p1, Polynomial p2, Polynomial res) {
        System.out.println("[" + p1 + "]*[" + p2 + "]=" + res);
    }

    private static void printEval(Polynomial p, Scalar s, Scalar res) {
        System.out.println("eval(" + p + "," + s + ")=" + res);
    }

    private static void printDerivate(Polynomial p, Polynomial d) {
        System.out.println("derivate(" + p + ")=" + d);
    }



    public static void main(String[] args) {

        Polynomial R1 = new Polynomial(); R1=R1.build('R', "0.5 1");
        Polynomial R2 = new Polynomial(); R2=R2.build('R', "1 0 -1 0 0.25");
        Polynomial R3 = new Polynomial(); R3=R3.build('R', "5 0 -2");
        Polynomial R4 = new Polynomial(); R4=R4.build('R', "5");
        Polynomial Q1 = new Polynomial(); Q1=Q1.build('Q', "2/-1");
        Polynomial Q2 = new Polynomial(); Q2=Q2.build('Q', "1/2 1 0 8");
        Polynomial Q3 = new Polynomial(); Q3=Q3.build('Q', "0 0 100");
        Polynomial Q4 = new Polynomial(); Q4=Q4.build('Q', "0 -1");
        Polynomial Q5 = new Polynomial(); Q5=Q5.build('Q', "0 -1/-2");


        //Add Tests
        printTitle("Testing Add");
        printAdd(R1, R1, R1.add(R1));
        printAdd(R1, R2, R1.add(R2));
        printAdd(R2, R3, R2.add(R3));
        printAdd(R3, R1, R3.add(R1));
        printAdd(R4, R4, R4.add(R4));
        printAdd(Q1, Q1, Q1.add(Q1));
        printAdd(Q1, Q2, Q1.add(Q2));
        printAdd(Q2, Q3, Q2.add(Q3));
        printAdd(Q3, Q1, Q3.add(Q1));
        printAdd(Q4, Q4, Q4.add(Q4));
        printAdd(Q5, Q5, Q5.add(Q5));
        printAdd(Q4, Q5, Q4.add(Q5));


        //Mul Tests
        printTitle("Testing Mul");
        printMul(R1, R1, R1.mul(R1));
        printMul(R1, R2, R1.mul(R2));
        printMul(R2, R3, R2.mul(R3));
        printMul(R3, R1, R3.mul(R1));
        printMul(R4, R4, R4.mul(R4));
        printMul(Q1, Q1, Q1.mul(Q1));
        printMul(Q1, Q2, Q1.mul(Q2));
        printMul(Q2, Q3, Q2.mul(Q3));
        printMul(Q3, Q1, Q3.mul(Q1));
        printMul(Q4, Q4, Q4.mul(Q4));
        printMul(Q5, Q5, Q5.mul(Q5));
        printMul(Q4, Q5, Q4.mul(Q5));

        //Evaluate Tests
        printTitle("Testing Evaluate");
        RealScalar r = new RealScalar(0);
        printEval(R1, r, R1.evaluate(r));
        printEval(R2, r, R2.evaluate(r));
        printEval(R3, r, R3.evaluate(r));
        printEval(R4, r, R4.evaluate(r));
        r = new RealScalar(1);
        printEval(R1, r, R1.evaluate(r));
        printEval(R2, r, R2.evaluate(r));
        printEval(R3, r, R3.evaluate(r));
        printEval(R4, r, R4.evaluate(r));
        RationalScalar q = new RationalScalar(0);
        printEval(Q1, q, Q1.evaluate(q));
        printEval(Q2, q, Q2.evaluate(q));
        printEval(Q3, q, Q3.evaluate(q));
        printEval(Q4, q, Q4.evaluate(q));
        printEval(Q5, q, Q5.evaluate(q));
        q = new RationalScalar(1);
        printEval(Q1, q, Q1.evaluate(q));
        printEval(Q2, q, Q2.evaluate(q));
        printEval(Q3, q, Q3.evaluate(q));
        printEval(Q4, q, Q4.evaluate(q));
        printEval(Q5, q, Q5.evaluate(q));

        //Derivate Tests
        printTitle("Testing derivative");
        printDerivate(R1, R1.derivative());
        printDerivate(R2, R2.derivative());
        printDerivate(R3, R3.derivative());
        printDerivate(R4, R4.derivative());
        printDerivate(Q1, Q1.derivative());
        printDerivate(Q2, Q2.derivative());
        printDerivate(Q3, Q3.derivative());
        printDerivate(Q4, Q4.derivative());
        printDerivate(Q5, Q5.derivative());

    }
}

