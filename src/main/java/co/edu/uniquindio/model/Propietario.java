package co.edu.uniquindio.model;

public class Propietario {
    private String nombre;
    private String cedula;
    private int edad;     // 🔹 agregado
    private double peso;  // 🔹 agregado

    public Propietario(String nombre, String cedula, int edad, double peso) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                '}';
    }
}
