/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import view.view_barang;

/**
 *
 * @author adil steven
 */
    public class model_barang implements controller.controller_barang{
        
    @Override
    public void Tampil(view_barang mhs) throws SQLException {
       mhs.tbl_model.getDataVector().removeAllElements();
       mhs.tbl_model.fireTableDataChanged();
      
        try{
           //membuat statemen pemanggilan data pada table tblGaji dari database
          Connection c = koneksi.getKoneksi();
          Statement s = c.createStatement();
          String sql        = "Select * from tbl_barang";
          ResultSet r = s.executeQuery(sql);

           //penelusuran baris pada tabel tblGaji dari database
           while(r.next ()){
                Object[ ] obj = new Object[5];
                obj[0] = r.getString(1); 
                obj[1] = r.getString(2);
                obj[2] = r.getString(3); 
                obj[3] = r.getString(4);
                obj[4] = r.getString(5);
                mhs.tbl_model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }
}

    @Override
    public void Simpan(view_barang mhs) throws SQLException {
       try {
            Connection con = koneksi.getKoneksi();
            String sql = "INSERT tbl_barang values(?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);

            prepare.setString(1, mhs.idbarang.getText());
            prepare.setString(2, mhs.namabarang.getText());
            prepare.setString(3, mhs.harpok.getText());
            prepare.setString(4, mhs.harjul.getText());
            prepare.setString(5, mhs.stok.getText());
//          prepare.setString(6, mhs.JAM.getText());
            prepare.executeUpdate();        

            JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(mhs);
            mhs.setLebarKolom();
        }
    }

    @Override
    public void Hapus(view_barang mhs) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM tbl_barang WHERE barang_id=?";
            PreparedStatement prepare = con.prepareStatement(sql);

            prepare.setString(1, mhs.idbarang.getText());
            prepare.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasi di Hapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(mhs);
            mhs.setLebarKolom();
            Clear(mhs);
        }
    }

    @Override
    public void Edit(view_barang mhs) throws SQLException {
        Connection con = koneksi.getKoneksi();
        String sql = "UPDATE tbl_barang set barang_nama='" + mhs.namabarang.getText() + "',barang_harpok='" + mhs.harpok.getText()
                + "',barang_harjul='" + mhs.harjul.getText() + "',barang_stok= '" + mhs.stok.getText() + "'where barang_id='" + mhs.idbarang.getText() + "'";

        try {
            int validasi = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?");
            switch (validasi) {
                case JOptionPane.YES_OPTION:
                    PreparedStatement prepare = con.prepareStatement(sql);
                    prepare.executeUpdate(sql);
                    Tampil(mhs);
                    Clear(mhs);
                    JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");

                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Ubah " + e.getMessage());
        }
    }

    @Override
    public void Clear(view_barang mhs) {
        mhs.idbarang.setText("");
        mhs.namabarang.setText("");
        mhs.harpok.setText("");
        mhs.harjul.setText("");
        mhs.stok.setText("");
    }
    
    @Override
    public void klikTabel(view_barang mhs) throws SQLException {
        try {
            int pilih = mhs.tabel_barang.getSelectedRow();
            if (pilih == -1) {
                return;
            }

            mhs.idbarang.setText(mhs.tabel_barang.getValueAt(pilih, 0).toString());
            mhs.namabarang.setText(mhs.tabel_barang.getValueAt(pilih, 1).toString());
            mhs.harpok.setText(mhs.tabel_barang.getValueAt(pilih, 2).toString());
            mhs.harjul.setText(mhs.tabel_barang.getValueAt(pilih, 3).toString());
            mhs.stok.setText(mhs.tabel_barang.getValueAt(pilih, 4).toString());

        } catch (Exception e) {
        }
    }
    }
