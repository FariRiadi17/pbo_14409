/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpustakaan;
import java.sql.*;
import java.util.*;

/**
 *
 * @author riadi
 */
public class Perpustakaan {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/week11";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public static void main(String[] args) {
        update();
        show();
    }
    
    public static void insert() {
        int id_buku = 1;
        String judul_buku = "Buku PPKN";
        int penulis = 1;
        int stok = 7;
        int tahun_terbit = 2021;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "INSERT INTO buku (id, judul_buku, penulis, stok, tahun_terbit) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id_buku);
            ps.setString(2, judul_buku);
            ps.setInt(3, penulis);
            ps.setInt(4, stok);
            ps.setInt(5, tahun_terbit);

            ps.execute();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void update() {
        int id_buku = 1;
        String judul_buku = "Danur";
        int penulis = 3;
        int stok = 2;
        int tahun_terbit = 2010;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "UPDATE buku SET judul_buku = ?, penulis = ?, stok = ?, tahun_terbit = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, judul_buku);
            ps.setInt(2, penulis);
            ps.setInt(3, stok);
            ps.setInt(4, tahun_terbit);
            ps.setInt(5, id_buku);

            ps.execute();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        int id_buku = 1;
        String sql = "DELETE FROM buku WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_buku);

            int rowsDeleted = ps.executeUpdate();
            if(rowsDeleted > 0) {
                System.out.println("Data berhasil dihapus");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM buku");
            int i = 1;
            while (rs.next()) {
                System.out.println("Data ke-" + i);
                System.out.println("Kode buku : " + rs.getInt("id"));
                System.out.println("Judul buku : " + rs.getString("judul_buku"));
                System.out.println("Penulis : " + rs.getInt("penulis"));
                System.out.println("Stok : " + rs.getInt("stok"));
                System.out.println("Tahun Terbit : " + rs.getInt("tahun_terbit"));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
