package logicLayer;

public class Cajero extends Persona {

    private String ubicacion;
    private double saldoDisponible;

    public Cajero(String nombre, String dni, String ubicacion, double saldoDisponible) {
        super(nombre, dni);
        this.ubicacion = ubicacion;
        this.saldoDisponible = saldoDisponible;
    }

    public boolean retirar(CuentaCvu cuenta, double monto) {
        return cuenta.retirar(monto);
    }

    public boolean depositar(CuentaCvu cuenta, double monto) {
        return cuenta.depositar(monto);
    }
    
    // Devuelve cuánto dinero tiene actualmente el cajero
    public double getSaldoDisponible() {
    	return saldoDisponible;
    }
    
    // Valida si hay dinero suficiente en el cajero
    public boolean tieneDinero(double monto) {
    	return saldoDisponible >= monto;
    }
    
    // Resta dinero físico del cajero
    public void descontarDinero(double monto) {
    	saldoDisponible -= monto;
    }
    
    // Suma dinero físico al cajero
    public void agregarDinero(double monto) {
    	saldoDisponible += monto;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
	
}
