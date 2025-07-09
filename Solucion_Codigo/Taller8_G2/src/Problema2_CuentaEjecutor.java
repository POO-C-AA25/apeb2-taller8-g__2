import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Problema2_CuentaEjecutor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese nombre del cliente: ");
        String nombreCliente = sc.nextLine();

        Problema2_Cuenta cuenta = new Problema2_Cuenta(nombreCliente);

        System.out.print("¿Cuántos menús desea agregar? ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nMenú #" + (i + 1) + ":");
            System.out.println("1) A la carta");
            System.out.println("2) Menú del día");
            System.out.println("3) Menú de niños");
            System.out.println("4) Menú económico");
            System.out.print("Seleccione tipo: ");
            int tipo = sc.nextInt();
            sc.nextLine();

            System.out.print("Nombre del plato: ");
            String plato = sc.nextLine();
            System.out.print("Valor inicial del menú: ");
            double valorInicial = sc.nextDouble();

            switch (tipo) {
                case 1:
                    System.out.print("Valor porción de guarnición: ");
                    double guarnicion = sc.nextDouble();
                    System.out.print("Valor de bebida: ");
                    double bebidaC = sc.nextDouble();
                    System.out.print("Porcentaje de servicio: ");
                    double servicio = sc.nextDouble();
                    cuenta.agregarMenu(
                        new Problema2_MenuCarta(plato, valorInicial, guarnicion, bebidaC, servicio)
                    );
                    break;
                case 2:
                    System.out.print("Valor de postre: ");
                    double postre = sc.nextDouble();
                    System.out.print("Valor de bebida: ");
                    double bebidaD = sc.nextDouble();
                    cuenta.agregarMenu(
                        new Problema2_MenuDia(plato, valorInicial, postre, bebidaD)
                    );
                    break;
                case 3:
                    System.out.print("Valor porción de helado: ");
                    double helado = sc.nextDouble();
                    System.out.print("Valor porción de pastel: ");
                    double pastel = sc.nextDouble();
                    cuenta.agregarMenu(
                        new Problema2_MenuNinos(plato, valorInicial, helado, pastel)
                    );
                    break;
                case 4:
                    System.out.print("Porcentaje de descuento: ");
                    double descuento = sc.nextDouble();
                    cuenta.agregarMenu(
                        new Problema2_MenuEconomico(plato, valorInicial, descuento)
                    );
                    break;
                default:
                    System.out.println("Opción inválida, se omitirá este menú.");
            }
            sc.nextLine();
        }

        cuenta.calcularSubtotal();
        cuenta.calcularIVA();
        cuenta.calcularTotal();

        System.out.println("\n===== FACTURA =====");
        System.out.println(cuenta);

        sc.close();
    }
}

abstract class Problema2_MenuBase {
    protected String nombrePlato;
    protected double valorInicialMenu;
    protected double valorMenu;

    public Problema2_MenuBase(String nombrePlato, double valorInicialMenu) {
        this.nombrePlato = nombrePlato;
        this.valorInicialMenu = valorInicialMenu;
    }

    public abstract double calcularValorMenu();

    @Override
    public String toString() {
        return String.format(
            "%s [Plato: %s, Inicial: %.2f, Total: %.2f]",
            this.getClass().getSimpleName(),
            nombrePlato,
            valorInicialMenu,
            valorMenu
        );
    }
}

class Problema2_MenuCarta extends Problema2_MenuBase {
    private double valorPorcionGuarnicion;
    private double valorBebida;
    private double porcentajeServicio;

    public Problema2_MenuCarta(String nombrePlato,
                               double valorInicialMenu,
                               double valorPorcionGuarnicion,
                               double valorBebida,
                               double porcentajeServicio) {
        super(nombrePlato, valorInicialMenu);
        this.valorPorcionGuarnicion = valorPorcionGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeServicio = porcentajeServicio;
    }

    @Override
    public double calcularValorMenu() {
        valorMenu = valorInicialMenu
                  + valorPorcionGuarnicion
                  + valorBebida
                  + (valorInicialMenu * porcentajeServicio / 100.0);
        return valorMenu;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [Plato: %s, Inicial: %.2f, Guarnición: %.2f, Bebida: %.2f, Servicio: %.2f%%, Total: %.2f]",
            this.getClass().getSimpleName(),
            nombrePlato,
            valorInicialMenu,
            valorPorcionGuarnicion,
            valorBebida,
            porcentajeServicio,
            valorMenu
        );
    }
}

class Problema2_MenuDia extends Problema2_MenuBase {
    private double valorPostre;
    private double valorBebida;

    public Problema2_MenuDia(String nombrePlato,
                             double valorInicialMenu,
                             double valorPostre,
                             double valorBebida) {
        super(nombrePlato, valorInicialMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    @Override
    public double calcularValorMenu() {
        valorMenu = valorInicialMenu + valorPostre + valorBebida;
        return valorMenu;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [Plato: %s, Inicial: %.2f, Postre: %.2f, Bebida: %.2f, Total: %.2f]",
            this.getClass().getSimpleName(),
            nombrePlato,
            valorInicialMenu,
            valorPostre,
            valorBebida,
            valorMenu
        );
    }
}

class Problema2_MenuNinos extends Problema2_MenuBase {
    private double valorPorcionHelado;
    private double valorPorcionPastel;

    public Problema2_MenuNinos(String nombrePlato,
                               double valorInicialMenu,
                               double valorPorcionHelado,
                               double valorPorcionPastel) {
        super(nombrePlato, valorInicialMenu);
        this.valorPorcionHelado = valorPorcionHelado;
        this.valorPorcionPastel = valorPorcionPastel;
    }

    @Override
    public double calcularValorMenu() {
        valorMenu = valorInicialMenu + valorPorcionHelado + valorPorcionPastel;
        return valorMenu;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [Plato: %s, Inicial: %.2f, Helado: %.2f, Pastel: %.2f, Total: %.2f]",
            this.getClass().getSimpleName(),
            nombrePlato,
            valorInicialMenu,
            valorPorcionHelado,
            valorPorcionPastel,
            valorMenu
        );
    }
}

class Problema2_MenuEconomico extends Problema2_MenuBase {
    private double porcentajeDescuento;

    public Problema2_MenuEconomico(String nombrePlato,
                                   double valorInicialMenu,
                                   double porcentajeDescuento) {
        super(nombrePlato, valorInicialMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double calcularValorMenu() {
        valorMenu = valorInicialMenu
                  - (valorInicialMenu * porcentajeDescuento / 100.0);
        return valorMenu;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [Plato: %s, Inicial: %.2f, Descuento: %.2f%%, Total: %.2f]",
            this.getClass().getSimpleName(),
            nombrePlato,
            valorInicialMenu,
            porcentajeDescuento,
            valorMenu
        );
    }
}

class Problema2_Cuenta {
    private String nombreCliente;
    private List<Problema2_MenuBase> menus;
    private double subtotal;
    private double iva;
    private double total;
    private static final double IVA_PORC = 0.12;

    public Problema2_Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.menus = new ArrayList<>();
    }

    public void agregarMenu(Problema2_MenuBase m) {
        menus.add(m);
    }

    public void calcularSubtotal() {
        subtotal = 0;
        for (Problema2_MenuBase m : menus) {
            subtotal += m.calcularValorMenu();
        }
    }

    public void calcularIVA() {
        iva = subtotal * IVA_PORC;
    }

    public void calcularTotal() {
        total = subtotal + iva;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuenta Cliente: ").append(nombreCliente).append("\n");
        sb.append("----- Menús Solicitados -----\n");
        for (Problema2_MenuBase m : menus) {
            sb.append(m).append("\n");
        }
        sb.append(String.format("Subtotal: %.2f\n", subtotal));
        sb.append(String.format("IVA (%.0f%%): %.2f\n", IVA_PORC * 100, iva));
        sb.append(String.format("Total a Pagar: %.2f\n", total));
        return sb.toString();
    }
}