/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;

/**
 *
 * @author adil steven
 */
public interface controller_barang {
    public void Simpan(view.view_barang mhs) throws SQLException;
    public void Hapus(view.view_barang mhs) throws SQLException;
    public void Edit(view.view_barang mhs) throws SQLException;
    public void Clear(view.view_barang mhs);
    public void Tampil (view.view_barang mhs) throws SQLException;
    public void klikTabel (view.view_barang mhs) throws SQLException;
}
