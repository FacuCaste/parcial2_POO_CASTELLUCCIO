package logicLayer;

import java.util.ArrayList;
import java.util.List;

public class CuentaCvu {
	private String cvu;
    private String alias;
    private double saldo;
    private Cliente titular;
    private List<Operacion> operaciones;

    public CuentaCvu(String cvu, String alias, double saldoInicial, Cliente titular) {
        this.cvu = cvu;
        this.alias = alias;
        this.saldo = saldoInicial;
        this.titular = titular;
        this.operaciones = new ArrayList<>();
    }

    public String getCvu() {
        return cvu;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public List<Operacion> getOperaciones() {
        return operaciones;
    }

    //depositar
    public boolean depositar(double monto) {
        if (monto <= 0) return false;
        saldo += monto;
        registrarOperacion(new Operacion(TipoOperacion.DEPOSITO, monto));
        return true;
    }
    
    
    // retirar
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) return false;
        saldo -= monto;
        registrarOperacion(new Operacion(TipoOperacion.RETIRO, monto));
        return true;
    }

    // transferencia
    public boolean transferir(CuentaCvu destino, double monto) {
        if (destino == null || monto <= 0 || monto > saldo) {
        	return false;
        }else {
        	
        	saldo -= monto;
        	destino.saldo += monto;
        	
        	registrarOperacion(new Operacion(TipoOperacion.TRANSFERENCIA, monto));
        	destino.registrarOperacion(new Operacion(TipoOperacion.TRANSFERENCIA, monto));
        	return true;
        }
    }

    // registra Operacion
    public void registrarOperacion(Operacion op) {
        operaciones.add(op);
    }

   
    // Historial
    public String verHistorial() {

        if (operaciones.isEmpty()) {
            return "No hay operaciones registradas.";
        }

        String historial = "Historial de operaciones de " 
                         + titular.getNombre() 
                         + " (" + alias + ")\n\n";

        for (Operacion operacion : operaciones) {
            historial += operacion.getFecha() + "  |  "
                      + operacion.getTipo() + "  |  $"
                      + String.format("%.2f", operacion.getMonto())
                      + "\n";
        }

        return historial;
    }

}
