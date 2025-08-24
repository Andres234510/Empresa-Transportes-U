package co.edu.uniquindio.model;

public class VehiculoPasajero extends Vehiculo {
    private int capacidadPasajeros;
    private int numPasajerosDiarios;

    public VehiculoPasajero(String placa, String marca, int capacidadPasajeros) {
        super(placa, marca);
        this.capacidadPasajeros = capacidadPasajeros;
        this.numPasajerosDiarios = 0;
    }

    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }

    public int getNumPasajerosDiarios() { return numPasajerosDiarios; }
    public void setNumPasajerosDiarios(int numPasajerosDiarios) { this.numPasajerosDiarios = numPasajerosDiarios; }

    @Override
    public String toString() {
        return "VehiculoPasajero" + '\'' +
                "placa= '" + getPlaca() + '\'' +
                ", marca= '" + getMarca() + '\'' +
                ", capacidad pasajeros= " + capacidadPasajeros + '\'' +
                ", pasajeros Hoy= " + numPasajerosDiarios ;
    }
}
