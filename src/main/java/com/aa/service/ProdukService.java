package service;

import model.Produk;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukService {

    public List<Produk> getAllProduk() {
        List<Produk> produkList = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM produk";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Produk produk = new Produk(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("deskripsi"),
                        rs.getDouble("harga")
                );
                produkList.add(produk);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data produk: " + e.getMessage());
        }

        return produkList;
    }
}
