package Exercitiul2;
import java.time.LocalDate;

public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirarii;

    // Variabila statica pentru incasari
    private static double incasari = 0.0;

    // Constructor
    public Produs(String denumire, double pret, int cantitate, LocalDate dataExpirarii) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirarii = dataExpirarii;
    }

    // Getteri și setteri
    public String getDenumire() { return denumire; }
    public double getPret() { return pret; }
    public int getCantitate() { return cantitate; }
    public LocalDate getDataExpirarii() { return dataExpirarii; }

    public void setCantitate(int cantitate) { this.cantitate = cantitate; }

    public static double getIncasari() { return incasari; }

    // Actualizarea incasarilor
    public static void actualizeazaIncasari(double suma) {
        incasari += suma;
    }

    // Metoda toString pentru afișare
    @Override
    public String toString() {
        return String.format("Produs{denumire='%s', pret=%.2f, cantitate=%d, dataExpirarii=%s}",
                denumire, pret, cantitate, dataExpirarii);
    }
}
