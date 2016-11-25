package projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001.model;

import java.io.Serializable;

/**
 * Created by J on 05/11/2016.
 */
public class Hotel implements Serializable {
    public String judul;
    public String deskripsi;
    public String detail;
    public String lokasi;
    public String foto;

    public Hotel(String judul, String deskripsi, String detail, String lokasi, String foto) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.detail = detail;
        this.lokasi = lokasi;
        this.foto = foto;
    }
}
