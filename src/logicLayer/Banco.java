package logicLayer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Banco {

    private String nombre;
    private List<Cliente> clientes;
    private List<Cajero> cajeros = new ArrayList<>();

    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }


    // valida el usuario y contraseña, con las ingresadas
    public Cliente login(String usuario, String contrasena) {
        for (Cliente cliente : clientes) {
            if (cliente.validarAcceso(usuario, contrasena)) {
                return cliente;
            }
        }
        return null;
    }
    
    // Busca un cliente por su nombre de usuario.
    public Cliente buscarClientePorUsuario(String usuario) {
        for (Cliente cliente : clientes) {
            if (cliente.getUsuario() != null && cliente.getUsuario().equals(usuario)) {
                return cliente;
            }
        }
        return null;
    }

    // Realiza la transferencia con logica de CuentaCvu.transferir
     
    public boolean transferir(CuentaCvu origen, CuentaCvu destino, double monto) {
        if (origen == null || destino == null) {
        	return false;
        }else {
        	return origen.transferir(destino, monto);
        }
    }
    
    public void agregarCajero(Cajero c) {
        cajeros.add(c);
    }

    public List<Cajero> getCajeros() {
        return cajeros;
    }

    // Devuelve un String de los cajeros
    public String mostrarCajeros() {
        if (cajeros.isEmpty()) return "No hay cajeros disponibles.";

        String s = "Seleccione un cajero:\n\n";
        for (int i = 0; i < cajeros.size(); i++) {
            s += (i + 1) + ") " + cajeros.get(i).getUbicacion() + "\n";
        }
        return s;
    }

    public Cajero seleccionarCajero(int opcion) {
        if (opcion < 1 || opcion > cajeros.size()) {
        	return null;
        }else {
        	return cajeros.get(opcion - 1);        	
        }
    }

    public boolean depositarEnCajero(int numeroCajero, CuentaCvu cuenta, double monto) {
        Cajero c = seleccionarCajero(numeroCajero);
        if (c == null) {
        	return false;
        }
        // Depositar en la cuenta
        if (c.depositar(cuenta, monto)) {
            // El cajero recibe dinero físico
            c.agregarDinero(monto);
            return true;
        }
        return false;
    }

    public boolean retirarEnCajero(int numeroCajero, CuentaCvu cuenta, double monto) {
        Cajero cajero = seleccionarCajero(numeroCajero);
        if (cajero == null) return false;

        // Validar si el cajero tiene dinero suficiente
        if (!cajero.tieneDinero(monto)) {
            JOptionPane.showMessageDialog(null, "El cajero no tiene suficiente dinero.");
            return false;
        }
        // Retiro desde cuenta
        if (cajero.retirar(cuenta, monto)) {
            // descuento dinero del cajero
        	cajero.descontarDinero(monto);
            return true;
        }
        return false;
    }
    
    
    public int elegirCajero() {

        String oCajero = JOptionPane.showInputDialog(mostrarCajeros() + "\nIngrese número de cajero:");
        if (oCajero == null) {
            return -1;
        }
        int opcion = Integer.parseInt(oCajero);
        if (opcion < 1 || opcion > cajeros.size()) {
            JOptionPane.showMessageDialog(null, "Cajero inválido.");
            return -1;
        }
        return opcion;
    }
    
}
