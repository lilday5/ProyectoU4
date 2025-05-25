import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gualo
 */
public class Tabla {
    private String nombre;
    private List<Atributo> atributos;

    public Tabla() {
        atributos = new ArrayList<Atributo>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public void agregarAtributo(Atributo atributo) {
        atributos.add(atributo);
    }
}
