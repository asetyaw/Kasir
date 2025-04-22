package service;

import model.Pengguna;
import model.Produk;
import util.Database;

import java.sql.*;

public class TransaksiService {

    public boolean buatTransaksi(Pengguna pengguna, Produk produk, int jumlah) {
        double totalHarga = jumlah * produk.getHarga();

        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO transaksi (id_pengguna, id_produk, jumlah, total_harga) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pengguna.getId());
            stmt.setInt(2, produk.getId());
            stmt.setInt(3, jumlah);
            stmt.setDouble(4, totalHarga);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
            return false;
        }
    }
}
