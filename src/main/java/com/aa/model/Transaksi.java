package model;

import java.time.LocalDateTime;

public class Transaksi {
    private int id;
    private Pengguna pengguna;
    private Produk produk;
    private int jumlah;
    private double totalHarga;
    private LocalDateTime tanggal;

    public Transaksi(int id, Pengguna pengguna, Produk produk, int jumlah, double totalHarga, LocalDateTime tanggal) {
        this.id = id;
        this.pengguna = pengguna;
        this.produk = produk;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public Produk getProduk() {
        return produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }
}
