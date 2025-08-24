package co.edu.uniquindio.model;

public class VehiculoCarga extends Vehiculo {
    private double capacidadCargaKg;
    private Propietario propietario;

    public VehiculoCarga(String placa, String marca, double capacidadCargaKg, Propietario propietario) {
        super(placa, marca);
        this.capacidadCargaKg = capacidadCargaKg;
        this.propietario = propietario;
    }

    public VehiculoCarga(String placa, String marca, double capacidadCargaKg) {
        this(placa, marca, capacidadCargaKg, null);
    }

    public double getCapacidadCargaKg() { return capacidadCargaKg; }
    public void setCapacidadCargaKg(double capacidadCargaKg) { this.capacidadCargaKg = capacidadCargaKg; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    @Override
    public String toString() {
        return "VehiculoCarga: " +'\'' +
                "placa= '" + getPlaca() + '\'' +
                ", marca= '" + getMarca() + '\'' +
                ", capacidad Carga en Kg= " + capacidadCargaKg +
                ", propietario =" + (propietario != null ? propietario.getNombre() + " (" + propietario.getCedula() + ")"
                : "Sin propietario");
    }
}
