package model;

import model.Users;

import java.time.LocalDate;

public class Gasto extends Movimiento{

        private Users usuario;



        public Gasto(String cantidad, String categoria, Users usuario) {
            super(cantidad, categoria);
            this.usuario = usuario;
        }

        @Override
        public String getCantidad() {
            return super.getCantidad();
        }

        // Getters y setters


        public String getUsuario() {
            return usuario.getUsername();
        }

    public Object getUsuarioUsername() {
            return this.usuario.getUsername();
    }
}


