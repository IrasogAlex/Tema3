package Exercitiul1;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parabola {
    private int a, b, c;

    // Constructor
    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Metoda pentru calcularea varfului
    public double[] calculeazaVarful() {
        double x = -b / (2.0 * a);
        double y = a * x * x + b * x + c;
        return new double[]{x, y};
    }

    // Redefinirea metodei toString
    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    // Metoda care calculeaza mijlocul segmentului intre varfuri
    public double[] calculeazaMijlocul(Parabola altaParabola) {
        double[] varf1 = this.calculeazaVarful();
        double[] varf2 = altaParabola.calculeazaVarful();
        double x = (varf1[0] + varf2[0]) / 2;
        double y = (varf1[1] + varf2[1]) / 2;
        return new double[]{x, y};
    }

    // Metoda care calculeaza lungimea segmentului intre varfuri
    public double calculeazaLungimea(Parabola altaParabola) {
        double[] varf1 = this.calculeazaVarful();
        double[] varf2 = altaParabola.calculeazaVarful();
        return Math.hypot(varf2[0] - varf1[0], varf2[1] - varf1[1]);
    }

    // Metoda statica pentru mijlocul segmentului
    public static double[] calculeazaMijloculStatic(Parabola p1, Parabola p2) {
        return p1.calculeazaMijlocul(p2);
    }

    // Metoda statica pentru lungimea segmentului
    public static double calculeazaLungimeaStatica(Parabola p1, Parabola p2) {
        return p1.calculeazaLungimea(p2);
    }
}

