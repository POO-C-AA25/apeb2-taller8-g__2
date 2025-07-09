import java.util.ArrayList;
import java.util.List;
public class Problema5_PlataformaDeApoyo {
    public static void main(String[] args) {
        Mentor mentor1 = new Mentor("Ana Pérez", "Marketing");
        Mentor mentor2 = new Mentor("Carlos Gómez", "Contabilidad");
        Mentor mentor3 = new Mentor("Luis Martínez", "Desarrollo de Software");

        Emprendimiento empTec = new EmprendimientoTecnologico("TechLoja", "Innovar en tecnología para Loja", "techloja@mail.com");
        empTec.agregarProductoServicio("App móvil de turismo");
        empTec.agregarMentor(mentor3);

        Emprendimiento empArt = new EmprendimientoArtesanal("Artesanías Loja", "Preservar la cultura local", "artesaniasloja@mail.com");
        empArt.agregarProductoServicio("Cerámica tradicional");
        empArt.agregarMentor(mentor1);

        Emprendimiento empAgri = new EmprendimientoAgricola("AgroLoja", "Producir alimentos orgánicos", "agroloja@mail.com");
        empAgri.agregarProductoServicio("Frutas orgánicas");
        empAgri.agregarMentor(mentor2);

        empTec.evolucionar();
        empArt.evolucionar();

        System.out.println(empTec.participarEnFeria("Tecnológica"));
        System.out.println(empArt.participarEnFeria("Artesanal"));
        System.out.println(empAgri.participarEnFeria("Agrícola"));
        System.out.println();

        System.out.println(empTec);
        System.out.println(empArt);
        System.out.println(empAgri);
    }
}

abstract class Emprendimiento {
    protected String nombre;
    protected String mision;
    protected String contacto;
    protected List<String> productosServicios = new ArrayList<>();
    protected List<Mentor> mentores = new ArrayList<>();
    protected List<String> sedes = new ArrayList<>();

    public Emprendimiento(String nombre, String mision, String contacto) {
        this.nombre = nombre;
        this.mision = mision;
        this.contacto = contacto;
        this.sedes.add("Sede Principal");
    }

    public void agregarProductoServicio(String producto) {
        productosServicios.add(producto);
    }

    public void agregarMentor(Mentor mentor) {
        if (!mentores.contains(mentor)) {
            mentores.add(mentor);
        }
    }

    public void agregarSede(String sede) {
        sedes.add(sede);
    }

    public abstract String participarEnFeria(String tipoFeria);

    public void evolucionar() {
        agregarSede("Nueva Sede " + (sedes.size() + 1));
        agregarProductoServicio("Nuevo Producto " + (productosServicios.size() + 1));
    }

    @Override
    public String toString() {
        String resultado = "Emprendimiento: " + nombre + "\n";
        resultado += "Misión: " + mision + "\n";
        resultado += "Contacto: " + contacto + "\n";
        resultado += "Sedes: " + String.join(", ", sedes) + "\n";
        resultado += "Productos/Servicios:\n";

        for (String p : productosServicios) {
            resultado += "  - " + p + "\n";
        }

        if (!mentores.isEmpty()) {
            resultado += "Mentores:\n";
            for (Mentor m : mentores) {
                resultado += "  - " + m + "\n";
            }
        }

        return resultado;
    }
}

class EmprendimientoAgricola extends Emprendimiento {
    public EmprendimientoAgricola(String nombre, String mision, String contacto) {
        super(nombre, mision, contacto);
    }

    @Override
    public String participarEnFeria(String tipoFeria) {
        return nombre + " presenta productos agrícolas frescos en la feria " + tipoFeria + ".";
    }
}

class EmprendimientoArtesanal extends Emprendimiento {
    public EmprendimientoArtesanal(String nombre, String mision, String contacto) {
        super(nombre, mision, contacto);
    }

    @Override
    public String participarEnFeria(String tipoFeria) {
        return nombre + " exhibe productos artesanales en la feria " + tipoFeria + ".";
    }
}

class EmprendimientoTecnologico extends Emprendimiento {
    public EmprendimientoTecnologico(String nombre, String mision, String contacto) {
        super(nombre, mision, contacto);
    }

    @Override
    public String participarEnFeria(String tipoFeria) {
        if ("Tecnológica".equalsIgnoreCase(tipoFeria)) {
            return nombre + " participa activamente en la feria tecnológica con demostraciones y workshops.";
        } else {
            return nombre + " participa en la feria general con su oferta tecnológica.";
        }
    }
}

class Mentor {
    private String nombre;
    private String especialidad;

    public Mentor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}

class Producto {
    private String nombre;
    private String descripcion;

    public Producto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "- " + nombre + ": " + descripcion;
    }
}