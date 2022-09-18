package com.example.cashbook;

public class Kas {
    private String id_kas;
    private String total_kas;
    private String tipe_kas;
    private String keterangan;
    private String tanggal;

    public Kas(String id_kas, String tipe_kas, String total_kas, String keterangan, String tanggal) {
        this.id_kas = id_kas;
        this.tipe_kas = tipe_kas;
        this.total_kas = total_kas;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public Kas(String tipe_kas, String total_kas, String keterangan, String tanggal) {
        this.tipe_kas = tipe_kas;
        this.total_kas = total_kas;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public String getId_kas() {
        return id_kas;
    }

    public String getTotal_kas() {
        return total_kas;
    }

    public String getTipe_kas() {
        return tipe_kas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setId_kas(String id_kas) {
        this.id_kas = id_kas;
    }

    public void setTotal_kas(String total_kas) {
        this.total_kas = total_kas;
    }

    public void setTipe_kas(String tipe_kas) {
        this.tipe_kas = tipe_kas;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
