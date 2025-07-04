public class Problema1_JuegoRoles {
    static Personaje guerrero;
    static Personaje mago;
    static Personaje arquero;
    public static void main(String[] args) {
        guerrero = new Guerrero("Gigante", 3);
        mago = new Mago("Invisible", 3);
        boolean gana = guerrero.ataque(mago);
        if(gana){
            guerrero.experiencia +=1;
            guerrero.batallasGana +=1;
            mago.vidas -=1;
        }   
        else {
            mago.experiencia +=1;
            mago.batallasGana +=1;
            guerrero.vidas -=1;
        }
        System.out.println("GUERRRO: " + guerrero + " MAGO: " + mago);
    }
}
abstract class Personaje{
    public int vidas, experiencia, batallasGana;
    public Personaje(int vidas) {
        this.vidas = vidas;   }
    public abstract boolean ataque(Personaje personaje);
    public abstract int defensa();
    public String toString() {
        return "Personaje{" + "vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGana=" + batallasGana + '}';}
}
class Guerrero extends Personaje{
    public String habilidades;
    public Guerrero(String habilidades, int vidas) {
        super(vidas);
        this.habilidades = habilidades; }
    public boolean ataque(Personaje personaje) { 
        //return ((int) (Math.random() * 1) == 1) ? true : false;
        int bandera = (int) (Math.random() * 2);
        boolean gana = (bandera == 1) ? true : false;
        return gana;
    } 
    public int defensa() { return 0;}
    public String toString() {
        return "Guerrero{" + "habilidades=" + habilidades + '}' + super.toString(); }
}
class Mago extends Personaje{
    public String estrategia;
    public Mago(String estrategia, int vidas) {
        super(vidas);
        this.estrategia = estrategia;   }
    public boolean ataque(Personaje personaje) { return false;} 
    public int defensa() { return 0;}
}
class Arquero extends Personaje{
public String atributo;
    public Arquero(String atributo, int vidas) {
        super(vidas);
        this.atributo = atributo;    } 
    public boolean ataque(Personaje personaje) { return false;} 
    public int defensa() { return 0;}
}
