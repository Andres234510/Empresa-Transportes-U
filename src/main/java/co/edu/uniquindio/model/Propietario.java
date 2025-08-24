package co.edu.uniquindio.model;

public class Propietario {
    private String cedula;
    private String nombre;
    private int edad;
    private double peso;

    public Propietario(String cedula, String nombre, int edad, double peso) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    @Override
    public String toString() {
        return "Propietario" + '\'' +
                "cedula= '" + cedula + '\'' +
                ", nombre= '" + nombre + '\'' +
                ", edad= " + edad +
                ", peso= " + peso;
    }
}
