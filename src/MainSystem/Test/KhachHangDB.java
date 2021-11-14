/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainSystem.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author nhp14
 */
public class KhachHangDB {
    private List<KhachHang> khList = new ArrayList<>();

    public static void add(KhachHang kh) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
//            stm = con.prepareStatement("select * from khachhang where Makh = ?");
//            stm.setString(1, kh.MaKh);
//            rs = stm.executeQuery();
            stm = con.prepareStatement("insert into customer(c_id, c_name, c_identitycard, c_address, c_phonenumber) " +
                    "VALUES (?,?,?,?,?)");
            stm.setString(1, kh.getMaKh());
            stm.setString(2, kh.getHoTen());
            stm.setString(3, kh.getCmnd());
            stm.setString(4, kh.getDiaChi());
            stm.setString(5, kh.getSdt());

            int kq = stm.executeUpdate();
            if (kq > 0) {
                JOptionPane.showMessageDialog(null, "thành công");
            } else {
                JOptionPane.showMessageDialog(null, "lỗi");
            }
//            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "lỗi");
//            } else {
//            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void update(KhachHang kh) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            stm = con.prepareStatement("update khachhang set HoTen=?, CMND=?, DiaChi=?, SDT=? where Makh =?");
            stm.setString(5, kh.getMaKh());
            stm.setString(1, kh.getHoTen());
            stm.setString(2, kh.getCmnd());
            stm.setString(3, kh.getDiaChi());
            stm.setString(4, kh.getSdt());
           

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void delete(String kh) {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            stm = con.prepareStatement("delete from khachhang where MaKh = ?");
            stm.setString(1, kh);

            int kq = stm.executeUpdate();
            if (kq > 0) {
                System.out.println("Đã xóa thành công ");
            } else {
                System.err.println("Lỗi");
            }

        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getAll() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            //lay tat ca danh sach sinh vien
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
            String sql = "select * from customer";
            stm = con.createStatement();

            rs = stm.executeQuery(sql);

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKh(rs.getString("c_id"));
                kh.setHoTen(rs.getString("c_name"));
                kh.setCmnd(rs.getString("c_identitycard"));
                kh.setSdt(rs.getString("c_phonenumber"));
                kh.setDiaChi(rs.getString("c_address"));
                khList.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<KhachHang> findKhachHangByID(String sr) {
        List<KhachHang> ls = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            stm = con.prepareStatement("select * from khachhang where hoten like ? ");
            stm.setString(1, "%" + sr + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKh(rs.getString("Makh"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setCmnd(rs.getString("CMND"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("DiaChi"));
                ls.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ls;
    }

    public List<KhachHang> getKhList() {
        return khList;
    }
}
