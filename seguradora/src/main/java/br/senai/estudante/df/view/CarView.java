/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.estudante.df.view;

import br.senai.estudante.df.controller.CarController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.senai.estudante.df.model.entities.Car;
import br.senai.estudante.df.util.Language;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 *
 * @author luan
 */
@Log4j2
public class CarView extends javax.swing.JFrame {

    private CarController controller;
    /**
     * Creates new form CarView
     */
    public CarView() {
        log.info("Car view");
        initComponents();
        language();
        controller = new CarController(this);
        showList();
        disableFields();
    }

    private void language() {
        lblId.setText(Language.text("id"));
        lblLicensePlate.setText(Language.text("licensePlate"));
        lblReindeer.setText(Language.text("reindeer"));
        manufacturer.setText(Language.text("manufacturer"));
        lblModel.setText(Language.text("model"));
        lblYear.setText(Language.text("year"));
        lblColor.setText(Language.text("color"));

        lblModelSearch.setText(Language.text("name"));

        btnModelSearch.setText(Language.text("search"));
        btnNew.setText(Language.text("new"));
        btnAlter.setText(Language.text("alter"));
        btnRegister.setText(Language.text("register"));
        btnDelete.setText(Language.text("delete"));
    }
    public void enableFields() {
//        txfId.setEditable(true);
        txfLicensePlate.setEditable(true);
        txfReindeer.setEditable(true);
        txfManufacturer.setEditable(true);
        txfModel.setEditable(true);
        txfYear.setEditable(true);
        txfColor.setEditable(true);

        btnAlter.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    
    public void disableFields() {
        txfId.setEditable(false);
        txfLicensePlate.setEditable(false);
        txfReindeer.setEditable(false);
        txfManufacturer.setEditable(false);
        txfModel.setEditable(false);
        txfYear.setEditable(false);
        txfColor.setEditable(false);

        btnAlter.setEnabled(false);
        btnRegister.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    
    public void cleanFields() {
        txfId.setText("");
        txfLicensePlate.setText("");
        txfReindeer.setText("");
        txfManufacturer.setText("");
        txfModel.setText("");
        txfYear.setText("");
        txfColor.setText("");
    }
    
    public void fillsFields(String id, String licensePlate, String reindeer, String manufacturer,
            String model, String year, String color) {
        txfId.setText(id);
        txfLicensePlate.setText(licensePlate);
        txfReindeer.setText(reindeer);
        txfManufacturer.setText(manufacturer);
        txfModel.setText(model);
        txfYear.setText(year);
        txfColor.setText(color);
    }

    public void showList() {
        mountTalbe(controller.showList());
    }

    public void showListSearch() {
        mountTalbe(controller.showListSearch(txfModelSearch.getText()));
    }

    private void mountTalbe(List<Car> cars) {
        String[] line = new String[]{null, null, null, null, null, null, null};

        DefaultTableModel data = (DefaultTableModel) tblCars.getModel();
        data.setNumRows(0);

        List<Car> carList = cars;
        for (int i = 0; i < carList.size(); i++) {
            data.addRow(line);
            data.setValueAt(carList.get(i).getId(), i, 0);
            data.setValueAt(carList.get(i).getLicensePlate(), i, 1);
            data.setValueAt(carList.get(i).getReindeer(), i, 2);
            data.setValueAt(carList.get(i).getManufacturer(), i, 3);
            data.setValueAt(carList.get(i).getModel(), i, 4);
            data.setValueAt(carList.get(i).getYear(), i, 5);
            data.setValueAt(carList.get(i).getColor(), i, 6);
        }
    }

    private void fillInData() {
        int selectedLine = tblCars.getSelectedRow();

        if (selectedLine != -1) {
            fillsFields(
                    tblCars.getValueAt(selectedLine, 0).toString(),
                    tblCars.getValueAt(selectedLine, 1).toString(),
                    tblCars.getValueAt(selectedLine, 2).toString(),
                    tblCars.getValueAt(selectedLine, 3).toString(),
                    tblCars.getValueAt(selectedLine, 4).toString(),
                    tblCars.getValueAt(selectedLine, 5).toString(),
                    tblCars.getValueAt(selectedLine, 6).toString()
            );
        }
        enableFields();
    }

    public boolean showConfirm(String message) {
        int res = JOptionPane.showConfirmDialog(null, message, "Confirm!", JOptionPane.YES_NO_OPTION);

        return res == JOptionPane.YES_NO_OPTION;
    }

    public boolean checksData() {
        return !txfLicensePlate.getText().equals("") && !txfReindeer.getText().equals("")
                && !txfManufacturer.getText().equals("") && txfModel.getText().equals("")
                && !txfYear.getText().equals("") && txfColor.getText().equals("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblModelSearch = new javax.swing.JLabel();
        txfModelSearch = new javax.swing.JTextField();
        btnModelSearch = new javax.swing.JButton();
        scrllpCars = new javax.swing.JScrollPane();
        tblCars = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        lblLicensePlate = new javax.swing.JLabel();
        txfLicensePlate = new javax.swing.JTextField();
        lblReindeer = new javax.swing.JLabel();
        txfReindeer = new javax.swing.JTextField();
        txfManufacturer = new javax.swing.JTextField();
        manufacturer = new javax.swing.JLabel();
        txfModel = new javax.swing.JTextField();
        lblModel = new javax.swing.JLabel();
        txfYear = new javax.swing.JTextField();
        lblYear = new javax.swing.JLabel();
        txfColor = new javax.swing.JTextField();
        lblColor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnAlter = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblModelSearch.setText("Modelo:");

        btnModelSearch.setText("Procurar");
        btnModelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModelSearchActionPerformed(evt);
            }
        });

        tblCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Placa", "Renavam", "Fabricante", "Modelo", "Ano", "Cor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarsMouseClicked(evt);
            }
        });
        scrllpCars.setViewportView(tblCars);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrllpCars)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblModelSearch)
                        .addGap(18, 18, 18)
                        .addComponent(txfModelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnModelSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblModelSearch)
                        .addComponent(txfModelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrllpCars, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblId.setText("ID:");

        lblLicensePlate.setText("Placa:");

        lblReindeer.setText("Renavam:");

        manufacturer.setText("Fabricante:");

        lblModel.setText("Modelo:");

        lblYear.setText("Ano:");

        lblColor.setText("Cor:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblModel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfModel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblLicensePlate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfLicensePlate))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(manufacturer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblColor)
                                            .addComponent(lblYear)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(lblReindeer)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfYear)
                                    .addComponent(txfColor)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txfReindeer, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfLicensePlate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLicensePlate)
                    .addComponent(txfReindeer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReindeer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manufacturer)
                    .addComponent(txfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColor)
                    .addComponent(txfModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnNew.setText("Novo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnAlter.setText("Alterar");
        btnAlter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterActionPerformed(evt);
            }
        });

        btnRegister.setText("Registrar");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Deletar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAlter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        cleanFields();
        enableFields();
        btnRegister.setEnabled(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterActionPerformed
        if (!checksData()) {
            controller.updateCar(
                    Integer.parseInt(txfId.getText()),
                    txfLicensePlate.getText(),
                    txfReindeer.getText(),
                    txfManufacturer.getText(),
                    txfModel.getText(),
                    txfYear.getText(),
                    txfColor.getText()
            );
        }
    }//GEN-LAST:event_btnAlterActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        if (!checksData()) {
            controller.insertCar(
                txfLicensePlate.getText(),
                txfReindeer.getText(),
                txfManufacturer.getText(),
                txfModel.getText(),
                txfYear.getText(),
                txfColor.getText()
            );
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = Integer.parseInt(txfId.getText());
        controller.deleteCar(id);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnModelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModelSearchActionPerformed
        showListSearch();
    }//GEN-LAST:event_btnModelSearchActionPerformed

    private void tblCarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarsMouseClicked
        log.info("Selected row from the cars table");
        fillInData();
        btnAlter.setEnabled(true);
        btnRegister.setEnabled(false);
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_tblCarsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlter;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnModelSearch;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRegister;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblLicensePlate;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblModelSearch;
    private javax.swing.JLabel lblReindeer;
    javax.swing.JLabel lblYear;
    private javax.swing.JLabel manufacturer;
    private javax.swing.JScrollPane scrllpCars;
    private javax.swing.JTable tblCars;
    private javax.swing.JTextField txfColor;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfLicensePlate;
    private javax.swing.JTextField txfManufacturer;
    private javax.swing.JTextField txfModel;
    private javax.swing.JTextField txfModelSearch;
    private javax.swing.JTextField txfReindeer;
    private javax.swing.JTextField txfYear;
    // End of variables declaration//GEN-END:variables
}
