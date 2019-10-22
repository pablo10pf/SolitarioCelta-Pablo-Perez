package es.upm.miw.SolitarioCelta.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Resultado implements Parcelable {
    private int id;
    private String jugador;
    private int fichasRestantes;
    private String duracionPartida;
    private String fecha;

    public Resultado(int id, String jugador, int fichasRestantes, String duracionPartida, String fecha) {
        this.id = id;
        this.jugador = jugador;
        this.fichasRestantes = fichasRestantes;
        this.duracionPartida = duracionPartida;
        this.fecha = fecha;
    }

    public Resultado(String jugador, int fichasRestantes, String duracionPartida, String fecha) {
        this.jugador = jugador;
        this.fichasRestantes = fichasRestantes;
        this.duracionPartida = duracionPartida;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getFichasRestantes() {
        return fichasRestantes;
    }

    public void setFichasRestantes(int fichasRestantes) {
        this.fichasRestantes = fichasRestantes;
    }

    public String getDuracionPartida() {
        return duracionPartida;
    }

    public void setDuracionPartida(String duracionPartida) {
        this.duracionPartida = duracionPartida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "id=" + id +
                ", jugador='" + jugador + '\'' +
                ", fichasRestantes=" + fichasRestantes +
                ", duracionPartida='" + duracionPartida + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    protected Resultado(Parcel in) {
        id = in.readInt();
        jugador = in.readString();
        fichasRestantes = in.readInt();
        duracionPartida = in.readString();
        fecha = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(jugador);
        dest.writeInt(fichasRestantes);
        dest.writeString(duracionPartida);
        dest.writeString(fecha);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Resultado> CREATOR = new Parcelable.Creator<Resultado>() {
        @Override
        public Resultado createFromParcel(Parcel in) {
            return new Resultado(in);
        }

        @Override
        public Resultado[] newArray(int size) {
            return new Resultado[size];
        }
    };
}