package entidades;

public class Sala {

    private int idSala;
    private int nroSala;
    private boolean apta3D;
    private int capacidad;
    private boolean estado;

    public Sala() {
    }

    public Sala(int idSala, int nroSala, boolean apta3D, int capacidad, boolean estado) {
        this.idSala = idSala;
        this.nroSala = nroSala;
        this.apta3D = apta3D;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    //  Getters  //
    public int getIdSala() {
        return idSala;
    }

    public int getNroSala() {
        return nroSala;
    }

    public boolean isApta3D() {
        return apta3D;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    //  Setters  //
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public void setApta3D(boolean apta3D) {
        this.apta3D = apta3D;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // toString //
    @Override
    public String toString() {
        return String.valueOf(nroSala);
    }
}
