package com.aa;

import model.Pengguna;
import model.Produk;
import service.AuthService;
import service.ProdukService;
import service.TransaksiService;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final AuthService authService = new AuthService();
    private static final ProdukService produkService = new ProdukService();
    private static final TransaksiService transaksiService = new TransaksiService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Selamat Datang di Aplikasi ===");

        Pengguna pengguna = null;

        while (pengguna == null) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Pilih opsi: ");
            int opsi = Integer.parseInt(scanner.nextLine());

            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (opsi == 1) {
                pengguna = authService.login(username, password);
                if (pengguna != null) {
                    System.out.println("Login berhasil. Selamat datang, " + pengguna.getUsername() + "!");
                } else {
                    System.out.println("Login gagal. Coba lagi.");
                }
            } else if (opsi == 2) {
                boolean success = authService.register(username, password);
                if (success) {
                    System.out.println("Registrasi berhasil. Silakan login.");
                } else {
                    System.out.println("Registrasi gagal.");
                }
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Lihat Produk");
            System.out.println("2. Beli Produk");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int pilih = Integer.parseInt(scanner.nextLine());

            switch (pilih) {
                case 1 -> tampilkanProduk();
                case 2 -> beliProduk(pengguna);
                case 3 -> {
                    System.out.println("Terima kasih. Sampai jumpa!");
                    running = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanProduk() {
        List<Produk> produkList = produkService.getAllProduk();
        System.out.println("\n=== Daftar Produk ===");
        for (Produk produk : produkList) {
            System.out.printf("ID: %d | Nama: %s | Harga: %.2f\nDeskripsi: %s\n\n",
                    produk.getId(), produk.getNama(), produk.getHarga(), produk.getDeskripsi());
        }
    }

    private static void beliProduk(Pengguna pengguna) {
        tampilkanProduk();
        System.out.print("Masukkan ID produk yang ingin dibeli: ");
        int idProduk = Integer.parseInt(scanner.nextLine());
        System.out.print("Jumlah: ");
        int jumlah = Integer.parseInt(scanner.nextLine());

        Produk produkDipilih = produkService.getAllProduk().stream()
                .filter(p -> p.getId() == idProduk)
                .findFirst()
                .orElse(null);

        if (produkDipilih != null) {
            boolean success = transaksiService.buatTransaksi(pengguna, produkDipilih, jumlah);
            if (success) {
                System.out.println("Transaksi berhasil!");
            } else {
                System.out.println("Transaksi gagal.");
            }
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }
}
