public class Problema3_JuegoDeFutbol {
    public static void main(String[] args) {
        Jugador jugador1 = new Atacante("Carlos Pérez", 10, "12345678-9", 2, 25, 5);
        Jugador jugador2 = new Defensor("Luis Díaz", 5, "23456789-0", 1, 15, 7);
        Jugador jugador3 = new Portero("Pedro Gómez", 1, "34567890-1", 0, 8);

        jugador1.mostrarEstadisticas();
        System.out.println();
        jugador2.mostrarEstadisticas();
        System.out.println();
        jugador3.mostrarEstadisticas();
    }
}

abstract class Jugador {
    protected String nombre;
    protected int dorsal;
    protected String rut;
    protected int goles;

    public Jugador(String nombre, int dorsal, String rut, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = goles;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public abstract int calcularValoracion();

    public void mostrarEstadisticas() {
        System.out.println("==========================================");
        System.out.printf("| %-40s |\n", " FICHA DEL JUGADOR");
        System.out.println("==========================================");
        System.out.printf("| %-15s: %-20s |\n", "Nombre", nombre);
        System.out.printf("| %-15s: %-20d |\n", "Dorsal", dorsal);
        System.out.printf("| %-15s: %-20s |\n", "RUT", rut);
        System.out.printf("| %-15s: %-20d |\n", "Goles", goles);
        System.out.printf("| %-15s: %-20d |\n", "Valoración", calcularValoracion());
        System.out.println("==========================================\n");
    }

}

class Atacante extends Jugador {
    private int pasesExitosos;
    private int recuperaciones;

    public Atacante(String nombre, int dorsal, String rut, int goles, int pasesExitosos, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pasesExitosos = pasesExitosos;
        this.recuperaciones = recuperaciones;
    }

    @Override
    public int calcularValoracion() {
        return goles * 30 + recuperaciones * 3;
    }
}

class Defensor extends Jugador {
    private int pasesExitosos;
    private int recuperaciones;

    public Defensor(String nombre, int dorsal, String rut, int goles, int pasesExitosos, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pasesExitosos = pasesExitosos;
        this.recuperaciones = recuperaciones;
    }

    @Override
    public int calcularValoracion() {
        return goles * 30 + recuperaciones * 4;
    }
}

class Portero extends Jugador {
    private int atajadas;

    public Portero(String nombre, int dorsal, String rut, int goles, int atajadas) {
        super(nombre, dorsal, rut, goles);
        this.atajadas = atajadas;
    }

    @Override
    public int calcularValoracion() {
        return goles * 30 + atajadas * 5;
    }
}