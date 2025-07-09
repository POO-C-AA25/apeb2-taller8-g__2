import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Problema6_SimuladorDeConflictos {
    public static void main(String[] args) {
        Nacion usa = new NacionAvanzada("USA", 331_000_000, 21_000_000, 95, true);
        Nacion china = new NacionAvanzada("China", 1_441_000_000, 14_000_000, 90, true);
        Nacion india = new NacionEnDesarrollo("India", 1_380_000_000, 2_900_000, 70, 1_000_000);
        Nacion nigeria = new NacionEnDesarrollo("Nigeria", 206_000_000, 450_000, 50, 500_000);

        // Agregar aliados
        usa.agregarAliado(china);
        china.agregarAliado(usa);
        india.agregarAliado(nigeria);
        nigeria.agregarAliado(india);

        List<Nacion> naciones = Arrays.asList(usa, china, india, nigeria);

        Simulador simulador = new Simulador(naciones);

        simulador.simularNConflictos(5);
        simulador.reporteFinal();
    }
}

abstract class Nacion {
    protected String nombre;
    protected int poblacion;
    protected double recursos;
    protected int poderMilitar;
    protected boolean enConflicto;
    protected List<Nacion> aliados = new ArrayList<>();

    public Nacion(String nombre, int poblacion, double recursos, int poderMilitar) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.recursos = recursos;
        this.poderMilitar = Math.min(100, Math.max(1, poderMilitar));
        this.enConflicto = false;
    }

    public void agregarAliado(Nacion aliado) {
        if (aliado != this && !aliados.contains(aliado)) {
            aliados.add(aliado);
        }
    }

    public List<Nacion> getAliados() {
        return aliados;
    }

    public boolean isEnConflicto() {
        return enConflicto;
    }

    public void setEnConflicto(boolean enConflicto) {
        this.enConflicto = enConflicto;
    }

    public int getPoderMilitar() {
        return poderMilitar;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public double getRecursos() {
        return recursos;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularImpacto();

    @Override
    public String toString() {
        return "Nación: " + nombre + "\n" +
                "Población: " + poblacion + "\n" +
                "Recursos: " + recursos + "\n" +
                "Poder Militar: " + poderMilitar + "\n" +
                "En Conflicto: " + (enConflicto ? "Sí" : "No") + "\n" +
                "Aliados: " + aliados.size() + "\n";
    }

}

class NacionAvanzada extends Nacion {
    private boolean tecnologiaAvanzada;

    public NacionAvanzada(String nombre, int poblacion, double recursos, int poderMilitar, boolean tecnologiaAvanzada) {
        super(nombre, poblacion, recursos, poderMilitar);
        this.tecnologiaAvanzada = tecnologiaAvanzada;
    }

    @Override
    public double calcularImpacto() {
        double impacto = poderMilitar;
        if (tecnologiaAvanzada) {
            impacto *= 1.2;
            if (impacto > 100) impacto = 100;
        }
        if (!aliados.isEmpty()) {
            impacto *= 1 + (aliados.size() * 0.05);
            if (impacto > 100) impacto = 100;
        }
        return impacto;
    }
}

class NacionEnDesarrollo extends Nacion {
    private int habitantesPorUnidad;

    public NacionEnDesarrollo(String nombre, int poblacion, double recursos, int poderMilitar, int habitantesPorUnidad) {
        super(nombre, poblacion, recursos, poderMilitar);
        this.habitantesPorUnidad = habitantesPorUnidad;
    }

    @Override
    public double calcularImpacto() {

        double baseImpacto = poderMilitar * ((double) poblacion / habitantesPorUnidad);
        double impacto = baseImpacto * 0.8;

        if (!aliados.isEmpty()) {
            impacto *= 1 + (aliados.size() * 0.03);
        }

        if (impacto > 100) impacto = 100;
        if (impacto < 1) impacto = 1;

        return impacto;
    }
}

class Simulador {
    private List<Nacion> naciones;
    private int totalConflictos;

    public Simulador(List<Nacion> naciones) {
        this.naciones = naciones;
        this.totalConflictos = 0;
    }

    public void simularConflicto(Nacion n1, Nacion n2) {
        System.out.println("\nSimulando conflicto entre " + n1.getNombre() + " y " + n2.getNombre());

        n1.setEnConflicto(true);
        n2.setEnConflicto(true);

        double impacto1 = n1.calcularImpacto();
        double impacto2 = n2.calcularImpacto();

        System.out.printf("Impacto %s: %.2f\n", n1.getNombre(), impacto1);
        System.out.printf("Impacto %s: %.2f\n", n2.getNombre(), impacto2);

        int diffPoder = Math.abs(n1.getPoderMilitar() - n2.getPoderMilitar());

        int reduccionPoblacion = (int)(n1.getPoblacion() * 0.05 * diffPoder);

        if (impacto1 > impacto2) {

            n2.poblacion = Math.max(0, n2.poblacion - reduccionPoblacion);
            n2.recursos *= 0.9;
            n1.poblacion = Math.max(0, n1.poblacion - (int)(n1.getPoblacion() * 0.05 * diffPoder));
            System.out.println(n1.getNombre() + " gana el conflicto.");
        } else if (impacto2 > impacto1) {
            // n2 gana
            n1.poblacion = Math.max(0, n1.poblacion - reduccionPoblacion);
            n1.recursos *= 0.9;
            n2.poblacion = Math.max(0, n2.poblacion - (int)(n2.getPoblacion() * 0.05 * diffPoder));
            System.out.println(n2.getNombre() + " gana el conflicto.");
        } else {

            n1.recursos *= 0.95;
            n2.recursos *= 0.95;
            System.out.println("Conflicto empatado, ambas naciones pierden recursos.");
        }

        totalConflictos++;
        n1.setEnConflicto(false);
        n2.setEnConflicto(false);
    }

    public void simularNConflictos(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int i1 = rand.nextInt(naciones.size());
            int i2;
            do {
                i2 = rand.nextInt(naciones.size());
            } while (i1 == i2);

            simularConflicto(naciones.get(i1), naciones.get(i2));
        }
    }

    public void reporteFinal() {
        System.out.println("\n--- REPORTE FINAL DE LA SIMULACIÓN ---");
        for (Nacion n : naciones) {
            System.out.println(n);
        }
        System.out.println("Total de conflictos simulados: " + totalConflictos);
    }
}