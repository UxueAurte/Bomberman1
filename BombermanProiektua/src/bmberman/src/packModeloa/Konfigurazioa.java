package bmberman.src.packModeloa;

public class Konfigurazioa {
    private static Konfigurazioa instance;
    
    private String tipoMapa;
    private String tipoBomberman;

    // Constructor privado para evitar instanciación directa
    private Konfigurazioa() {}

    // Obtener la instancia de JuegoConfiguracion (Singleton)
    public static Konfigurazioa getInstance() {
        if (instance == null) {
            instance = new Konfigurazioa();
        }
        return instance;
    }

    // Métodos setters y getters
    public void setConfiguracion(String tipoMapa, String tB) {
        this.tipoMapa = tipoMapa;
        this.tipoBomberman = tB;
    }

    public String getTipoMapa() {
        return tipoMapa;
    }

   public String getTipoBomberman() {
	   return tipoBomberman;
   }
}

