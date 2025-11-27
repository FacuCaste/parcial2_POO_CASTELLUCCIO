package logicLayer;

public class Cliente extends Persona{

    private String usuario;
    private String contrasenia;
    private CuentaCvu cuenta;

    public Cliente(String nombre, String dni, String usuario, String contrasena) {
        super(nombre, dni);
        this.usuario = usuario;
        this.contrasenia = contrasena;
    }

   // valida Usuario
    public boolean validarAcceso(String usuario, String contra) {
        if (usuario == null || contra == null) {
        	return false;
        }else {	
        	return this.usuario.equals(usuario) && this.contrasenia.equals(contra);
        }
    }
    

    public void setCuenta(CuentaCvu cuenta) { 
    	this.cuenta = cuenta; 
    }
    public CuentaCvu getCuenta() {
    	return cuenta; 
    }

    public String getUsuario() {
        return usuario;
    }
}
