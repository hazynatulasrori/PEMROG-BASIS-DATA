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
public interface controller_kasir {
    public void Simpan(view.view_barang mhs) throws SQLException;
    public void Tampil (view.view_barang mhs) throws SQLException;
    public void klikTabel (view.view_barang mhs) throws SQLException;
}
