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
import view.view_suplier;

/**
 *
 * @author adil steven
 */
public class model_suplier implements controller.controller_suplier{
    @Override
        public void Tampil(view_suplier mhs) throws SQLException {
       mhs.tbl_model.getDataVector().removeAllElements();
       mhs.tbl_model.fireTableDataChanged();
      
        try{
           //membuat statemen pemanggilan data pada table tblGaji dari database
          Connection c = koneksi.getKoneksi();
          Statement s = c.createStatement();
          String sql        = "Select * from tbl_suplier";
          ResultSet r = s.executeQuery(sql);

           //penelusuran baris pada tabel tblGaji dari database
           while(r.next ()){
                Object[ ] obj = new Object[4];
                obj[0] = r.getString(1); 
                obj[1] = r.getString(2);
                obj[2] = r.getString(3); 
                obj[3] = r.getString(4);
                mhs.tbl_model.addRow(obj);
            }
      }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage() );
      }
}
        
@Override
    public void klikTabel(view_suplier mhs) throws SQLException {
        try {
            int pilih = mhs.tabel_suplier.getSelectedRow();
            if (pilih == -1) {
                return;
            }

            mhs.data_id.setText(mhs.tabel_suplier.getValueAt(pilih, 0).toString());
            mhs.data_nama.setText(mhs.tabel_suplier.getValueAt(pilih, 1).toString());
            mhs.data_alamat.setText(mhs.tabel_suplier.getValueAt(pilih, 2).toString());
            mhs.data_phone.setText(mhs.tabel_suplier.getValueAt(pilih, 3).toString());

        } catch (Exception e) {
        }
    }

    @Override
    public void Simpan(view_suplier mhs) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "insert tbl_suplier values(?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);

            prepare.setString(1, mhs.data_id.getText());
            prepare.setString(2, mhs.data_nama.getText());
            prepare.setString(3, mhs.data_alamat.getText());
            prepare.setString(4, mhs.data_phone.getText());
//            prepare.setString(6, mhs.JAM.getText());
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
    public void Hapus(view_suplier mhs) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "DELETE FROM tbl_suplier WHERE suplier_id=?";
            PreparedStatement prepare = con.prepareStatement(sql);

            prepare.setString(1, mhs.data_id.getText());
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
    public void Edit(view_suplier mhs) throws SQLException {
        Connection con = koneksi.getKoneksi();
        String sql = "UPDATE tbl_suplier set suplier_nama='" + mhs.data_nama.getText()
                + "',suplier_alamat='" + mhs.data_alamat.getText() + "',suplier_notelp= '" + mhs.data_phone.getText() + "'where suplier_id='" + mhs.data_id.getText() + "'";

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
    public void Clear(view_suplier mhs) {
        mhs.data_id.setText("");
        mhs.data_nama.setText("");
        mhs.data_alamat.setText("");
        mhs.data_phone.setText("");
}
    
}

    