package userLayer;

import javax.swing.JOptionPane;
import logicLayer.*;

public class Main {
	public static void main(String[] args) {

		Banco banco = new Banco("Banco Digital");

        banco.agregarCajero(new Cajero("Cajero Centro", "101", "Av. Corrientes 1200", 0));
        banco.agregarCajero(new Cajero("Cajero Shopping", "102", "Alto Palermo", 150000));
        banco.agregarCajero(new Cajero("Cajero Terminal", "103", "Terminal Retiro", 300000));
        banco.agregarCajero(new Cajero("Cajero Barrio", "104", "Av. San Juan 800", 100000));

        // Clientes
        Cliente c1 = new Cliente("Facundo", "44556677", "facu", "1234");
        Cliente c2 = new Cliente("Juan", "40999888", "juan", "9999");

        // Cuentas
        CuentaCvu cuenta1 = new CuentaCvu("00001", "facu.alias", 10000, c1);
        CuentaCvu cuenta2 = new CuentaCvu("00002", "juan.alias", 5000, c2);

        c1.setCuenta(cuenta1);
        c2.setCuenta(cuenta2);
        banco.agregarCliente(c1);
        banco.agregarCliente(c2);

        // Login
        Cliente clienteLogueado = null;
        while (clienteLogueado == null) {

            String usuario = JOptionPane.showInputDialog("Ingrese usuario:");
            if (usuario == null) return;

            String contrasena = JOptionPane.showInputDialog("Ingrese contraseña:");
            if (contrasena == null) {
            	return;
            }

            clienteLogueado = banco.login(usuario, contrasena);

            if (clienteLogueado == null) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        }

        CuentaCvu cuentaActiva = clienteLogueado.getCuenta();

        // Menú
        String[] opciones = {
            "Depositar", "Retirar", "Transferir", "Saldo", "Historial operaciones", "Salir"
        };

        int opcion;

        do {
        	opcion = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenido " + clienteLogueado.getNombre(),
                    "Banco Digital",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {

                // Depositar
                case 0:
                    int cajeroDep = banco.elegirCajero();
                    if (cajeroDep == -1) break;

                    String sDep = JOptionPane.showInputDialog("Monto a depositar:");
                    if (sDep == null) break;

                    double dep = Double.parseDouble(sDep);

                    if (banco.depositarEnCajero(cajeroDep, cuentaActiva, dep)) {
                        JOptionPane.showMessageDialog(null, "Depósito realizado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo realizar el depósito.");
                    }
                    break;

                // Retirar
                case 1:
                    int cajeroRet = banco.elegirCajero();
                    if (cajeroRet == -1) break;

                    String sRet = JOptionPane.showInputDialog("Monto a retirar:");
                    if (sRet == null) break;

                    double ret = Double.parseDouble(sRet);

                    if (banco.retirarEnCajero(cajeroRet, cuentaActiva, ret)) {
                        JOptionPane.showMessageDialog(null, "Retiro realizado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo realizar el retiro.");
                    }
                    break;

                // Transferir
                case 2:
                    String destinoUsuario = JOptionPane.showInputDialog("Usuario destinatario:");
                    if (destinoUsuario == null) break;

                    Cliente destino = banco.buscarClientePorUsuario(destinoUsuario);
                    if (destino == null) {
                        JOptionPane.showMessageDialog(null, "Usuario destinatario no encontrado.");
                        break;
                    }

                    String sTrans = JOptionPane.showInputDialog("Monto a transferir:");
                    if (sTrans == null) break;

                    double trans = Double.parseDouble(sTrans);

                    if (banco.transferir(cuentaActiva, destino.getCuenta(), trans)) {
                        JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al transferir.");
                    }
                    break;

                // Saldo
                case 3:
                    JOptionPane.showMessageDialog(null,
                            "Saldo actual: $" + cuentaActiva.getSaldo());
                    break;

                // Historial operaciones
                case 4:
                    JOptionPane.showMessageDialog(null, cuentaActiva.verHistorial());
                    break;
            }

        } while (opcion != 5);

        JOptionPane.showMessageDialog(null, "Gracias por usar Banco Digital.\nSaliendo...");
    }
}
