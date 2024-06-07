package model;

public class Ingreso extends Movimiento {
    private Users usuario;

    public Ingreso(String cantidad, String categoria, Users usuario) {
        super(cantidad, categoria);
        this.usuario = usuario;
    }

    // Getters y setters

    public String getUsuario() {
        return usuario.getUsername();
    }

    public Object getUsuarioUsername() {
        return this.usuario.getUsername();
    }
}
