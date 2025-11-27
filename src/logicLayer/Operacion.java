package logicLayer;

import java.time.LocalDateTime;

public class Operacion {

    private TipoOperacion tipo;
    private double monto;
    private LocalDateTime fecha;

    public Operacion(TipoOperacion tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public TipoOperacion getTipo() { 
    	return tipo; 
    }
    public double getMonto() { 
    	return monto; 
    }
    public LocalDateTime getFecha() { 
    	return fecha; 
    }

}
