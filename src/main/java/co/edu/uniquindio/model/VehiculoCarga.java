package co.edu.uniquindio.model;

public class VehiculoCarga extends Vehiculo {
    private double capacidadCarga;
    private Propietario propietario;

    // Constructor sin propietario
    public VehiculoCarga(String placa, String marca, double capacidadCarga) {
        super(placa, marca);
        this.capacidadCarga = capacidadCarga;
    }

    // Constructor con propietario
    public VehiculoCarga(String placa, String marca, double capacidadCarga, Propietario propietario) {
        super(placa, marca);
        this.capacidadCarga = capacidadCarga;
        this.propietario = propietario;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "VehiculoCarga{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", capacidadCarga=" + capacidadCarga +
                ", propietario=" + (propietario != null ? propietario.getNombre() : "Sin propietario") +
                '}';
    }
}
