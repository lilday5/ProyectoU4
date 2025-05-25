/**
 *
 * @author Gualo
 */
public class Atributo {
    private String nombreAtributo;
    private String tipoAtributo;

    public Atributo() {
    }

    public Atributo(String nombreAtributo, String tipoAtributo) {
        this.nombreAtributo = nombreAtributo;
        this.tipoAtributo = tipoAtributo;
    }

    public String getNombre() {
        return nombreAtributo;
    }

    public void setNombre(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    public String getTipo() {
        return tipoAtributo;
    }

    public void setTipo(String tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }
}
