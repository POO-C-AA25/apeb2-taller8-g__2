public class Problema4_SitemaDeMonitoreo {
    public static void main(String[] args) {
        DispositivoMonitoreo costa = new DispositivoCosta("Guayaquil", 34.5, 10, 80, 15);
        DispositivoMonitoreo sierra = new DispositivoSierra("Quito", 18.0, 120, 50, 60);
        DispositivoMonitoreo oriente = new DispositivoOriente("Tena", 29.0, 60, 180, 70);

        System.out.println(costa);
        costa.procesarDatos();

        System.out.println(sierra);
        sierra.procesarDatos();

        System.out.println(oriente);
        oriente.procesarDatos();
    }
}

abstract class DispositivoMonitoreo {
    protected String ubicacion;
    protected double temperatura;
    protected double precipitacion;
    protected double calidadAire;
    protected double humedadSuelo;

    public DispositivoMonitoreo(String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        this.ubicacion = ubicacion;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.calidadAire = calidadAire;
        this.humedadSuelo = humedadSuelo;
    }

    public abstract void procesarDatos();

    @Override
    public abstract String toString();
}

class DispositivoSierra extends DispositivoMonitoreo {

    public DispositivoSierra(String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        super(ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos para dispositivo en la SIERRA...");
        if (precipitacion > 100) {
            System.out.println("Riesgo de DESLIZAMIENTO detectado en " + ubicacion);
        }
    }

    @Override
    public String toString() {
        return "Dispositivo Sierra [" + ubicacion + "]\n" +
               "- Temperatura: " + temperatura + "°C\n" +
               "- Precipitación: " + precipitacion + " mm\n" +
               "- Humedad del Suelo: " + humedadSuelo + "%\n" +
               "- Calidad del Aire: " + calidadAire + " AQI\n";
    }
}

class DispositivoOriente extends DispositivoMonitoreo {

    public DispositivoOriente(String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        super(ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos para dispositivo en el ORIENTE...");
        if (calidadAire > 150) {
            System.out.println("Riesgo de CONTAMINACIÓN DEL AIRE detectado en " + ubicacion);
        }
    }

    @Override
    public String toString() {
        return "Dispositivo Oriente [" + ubicacion + "]\n" +
               "- Temperatura: " + temperatura + "°C\n" +
               "- Precipitación: " + precipitacion + " mm\n" +
               "- Humedad del Suelo: " + humedadSuelo + "%\n" +
               "- Calidad del Aire: " + calidadAire + " AQI\n";
    }
}

class DispositivoCosta extends DispositivoMonitoreo {

    public DispositivoCosta(String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        super(ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public void procesarDatos() {
        System.out.println("Procesando datos para dispositivo en la COSTA...");
        if (humedadSuelo < 20 && temperatura > 30) {
            System.out.println("Riesgo de SEQUÍA detectado en " + ubicacion);
        }
    }

    @Override
    public String toString() {
        return "Dispositivo Costa [" + ubicacion + "]\n" +
               "- Temperatura: " + temperatura + "°C\n" +
               "- Precipitación: " + precipitacion + " mm\n" +
               "- Humedad del Suelo: " + humedadSuelo + "%\n" +
               "- Calidad del Aire: " + calidadAire + " AQI\n";
    }
}