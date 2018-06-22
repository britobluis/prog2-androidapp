package prog2final.mvcbrito.Modelo;

public class Automovil {
    private int placaAutomovil;
    private String marca;
    private String modelo;

    public Automovil(int placaAutomovil, String marca, String modelo) {
        this.placaAutomovil = placaAutomovil;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getPlacaAutomovil() {
        return placaAutomovil;
    }

    public void setPlacaAutomovil(int placaAutomovil) {
        this.placaAutomovil = placaAutomovil;
    }

    public String getmarca() {
        return marca;
    }

    public void setmarca(String marca) {
        this.marca = marca;
    }

    public String getmodelo() {
        return modelo;
    }

    public void setmodelo(String modelo) {
        this.modelo = modelo;
    }

}
