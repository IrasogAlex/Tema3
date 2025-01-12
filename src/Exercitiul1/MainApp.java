package Exercitiul1;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        List<Parabola> parabole = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("in.txt"))) {
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                String[] valori = linie.split("\\s+");
                int a = Integer.parseInt(valori[0]);
                int b = Integer.parseInt(valori[1]);
                int c = Integer.parseInt(valori[2]);
                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }

        // Afisarea parabolelor si a varfurilor
        for (Parabola parabola : parabole) {
            System.out.println(parabola);
            double[] varf = parabola.calculeazaVarful();
            System.out.printf("Varful: (%.2f, %.2f)%n", varf[0], varf[1]);
        }

        // Calcularea mijlocului si lungimii segmentului dintre primele doua parabole
        if (parabole.size() >= 2) {
            Parabola p1 = parabole.get(0);
            Parabola p2 = parabole.get(1);

            double[] mijloc = Parabola.calculeazaMijloculStatic(p1, p2);
            System.out.printf("Mijlocul segmentului: (%.2f, %.2f)%n", mijloc[0], mijloc[1]);

            double lungime = Parabola.calculeazaLungimeaStatica(p1, p2);
            System.out.printf("Lungimea segmentului: %.2f%n", lungime);
        }
    }
}

