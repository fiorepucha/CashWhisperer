package model;

public class Gasto {
        private String cantidad;
        private String categoria;

        private Users usuario;


        public Gasto(String cantidad, String categoria, Users usuario) {
            this.cantidad = "-"+cantidad;
            this.categoria = categoria;
            this.usuario = usuario;
        }

        // Getters y setters

        public String getCantidad() {
            return cantidad;
        }

        public void setCantidad(String cantidad) {
            this.cantidad = cantidad;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

    }


