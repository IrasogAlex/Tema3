package Exercitiul2;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        List<Produs> produse = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Citirea produselor din fisier
        try (BufferedReader br = new BufferedReader(new FileReader("produse.csv"))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] valori = linie.split(",");
                String denumire = valori[0].trim();
                double pret = Double.parseDouble(valori[1].trim());
                int cantitate = Integer.parseInt(valori[2].trim());
                LocalDate dataExpirarii = LocalDate.parse(valori[3].trim(), dateFormatter);

                produse.add(new Produs(denumire, pret, cantitate, dataExpirarii));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }

        // Meniu interactiv
        boolean ruleaza = true;
        while (ruleaza) {
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Afisare produse");
            System.out.println("2. Afisare produse expirate");
            System.out.println("3. Vanzare produs");
            System.out.println("4. Afisare produse cu pretul minim");
            System.out.println("5. Salvare produse cu cantitate mica");
            System.out.println("6. Iesire");
            System.out.print("Alegeti o optiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumare newline

            switch (optiune) {
                case 1: // Afisare produse
                    produse.forEach(System.out::println);
                    break;

                case 2: // Afisare produse expirate
                    produse.stream()
                            .filter(p -> p.getDataExpirarii().isBefore(LocalDate.now()))
                            .forEach(System.out::println);
                    break;

                case 3: // Vanzare produs
                    System.out.print("Introduceti denumirea produsului: ");
                    String denumire = scanner.nextLine();
                    System.out.print("Introduceti cantitatea de vandut: ");
                    int cantitateVanduta = scanner.nextInt();

                    Optional<Produs> produsOptional = produse.stream()
                            .filter(p -> p.getDenumire().equalsIgnoreCase(denumire))
                            .findFirst();

                    if (produsOptional.isPresent()) {
                        Produs produs = produsOptional.get();
                        if (produs.getCantitate() >= cantitateVanduta) {
                            produs.setCantitate(produs.getCantitate() - cantitateVanduta);
                            Produs.actualizeazaIncasari(produs.getPret() * cantitateVanduta);

                            if (produs.getCantitate() == 0) {
                                produse.remove(produs);
                            }
                            System.out.println("Vanzarea a fost realizata cu succes.");
                        } else {
                            System.out.println("Cantitate insuficienta pe stoc.");
                        }
                    } else {
                        System.out.println("Produsul nu exista.");
                    }
                    break;

                case 4: // Afisare produse cu pretul minim
                    double pretMinim = produse.stream()
                            .mapToDouble(Produs::getPret)
                            .min()
                            .orElse(0);

                    produse.stream()
                            .filter(p -> p.getPret() == pretMinim)
                            .forEach(System.out::println);
                    break;

                case 5: // Salvare produse cu cantitate mica
                    System.out.print("Introduceti pragul pentru cantitate: ");
                    int prag = scanner.nextInt();

                    List<Produs> produseFiltrate = produse.stream()
                            .filter(p -> p.getCantitate() < prag)
                            .collect(Collectors.toList());

                    try (PrintWriter pw = new PrintWriter(new FileWriter("produse_mici.csv"))) {
                        produseFiltrate.forEach(pw::println);
                    } catch (IOException e) {
                        System.err.println("Eroare la salvarea fisierului: " + e.getMessage());
                    }
                    System.out.println("Produsele au fost salvate in fisierul produse_mici.csv.");
                    break;

                case 6: // Iesire
                    ruleaza = false;
                    break;

                default:
                    System.out.println("Optiune invalida.");
            }
        }

        scanner.close();
    }
}
