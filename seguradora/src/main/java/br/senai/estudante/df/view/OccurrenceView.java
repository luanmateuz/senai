/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.estudante.df.view;

import br.senai.estudante.df.controller.CarController;
import br.senai.estudante.df.controller.ClientController;
import br.senai.estudante.df.controller.OccurrenceController;
import br.senai.estudante.df.model.entities.Car;
import br.senai.estudante.df.model.entities.Client;
import br.senai.estudante.df.model.entities.Occurrence;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author luan
 */
@Log4j2
public class OccurrenceView extends javax.swing.JFrame {

    private ClientController clientController;
    private CarController carController;
    private OccurrenceController occurrenceController;
    /**
     * Creates new form OccurrenceView
     */
    public OccurrenceView() {
        log.info("Occurrence view");
        occurrenceController = new OccurrenceController(this);
        clientController = new ClientController();
        carController = new CarController();
        initComponents();
        showList();
        disableFields();
    }

    public void enableFields() {
        ftfDate.setEditable(true);
        txfLocal.setEditable(true);
        txaDesc.setEditable(true);

        btnAlter.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    public void disableFields() {
        txfId.setEditable(false);
        ftfDate.setEditable(false);
        txfLocal.setEditable(false);
        txaDesc.setEditable(false);

        btnAlter.setEnabled(false);
        btnRegister.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    public void cleanFields() {
        txfIdClient.setText("");
        txfNome.setText("");

        txfIdCar.setText("");
        txfLicensePlate.setText("");
        txfModel.setText("");

        txfId.setText("");
        ftfDate.setText("");
        txfLocal.setText("");
        txaDesc.setText("");
    }

    private boolean checksData() {
        return false;
    }

    public void showList() {
        mountOccurrencesTable(occurrenceController.showList());
        mountClientsTalbe(clientController.showList());
        mountCarsTalbe(carController.showList());
    }

    private void mountClientsTalbe(List<Client> clients) {
        String[] line = new String[]{null, null, null};

        DefaultTableModel data = (DefaultTableModel) tblClients.getModel();
        data.setNumRows(0);

        List<Client> clientList = clients;
        for (int i = 0; i < clientList.size(); i++) {
            data.addRow(line);
            data.setValueAt(clientList.get(i).getId(), i, 0);
            data.setValueAt(clientList.get(i).getName(), i, 1);
            data.setValueAt(clientList.get(i).getCpf(), i, 2);
        }
    }

    private void mountCarsTalbe(List<Car> cars) {
        String[] line = new String[]{null, null, null, null};

        DefaultTableModel data = (DefaultTableModel) tblCars.getModel();
        data.setNumRows(0);

        List<Car> carList = cars;
        for (int i = 0; i < carList.size(); i++) {
            data.addRow(line);
            data.setValueAt(carList.get(i).getId(), i, 0);
            data.setValueAt(carList.get(i).getLicensePlate(), i, 1);
            data.setValueAt(carList.get(i).getManufacturer(), i, 2);
            data.setValueAt(carList.get(i).getModel(), i, 3);
        }
    }

    private void mountOccurrencesTable(List<Occurrence> occurrences) {
        String[] line = new String[]{null, null, null, null, null, null};

        DefaultTableModel data = (DefaultTableModel) tblOccurrences.getModel();
        data.setNumRows(0);

        List<Occurrence> occurrenceList = occurrences;
        for (int i = 0; i < occurrenceList.size(); i++) {
            data.addRow(line);
            data.setValueAt(occurrenceList.get(i).getId(), i, 0);
            data.setValueAt(occurrenceList.get(i).getClient().getId(), i, 1);
            data.setValueAt(occurrenceList.get(i).getCar().getId(), i, 2);
            data.setValueAt(occurrenceList.get(i).getLocalDate(), i, 3);
            data.setValueAt(occurrenceList.get(i).getLocal(), i, 4);
            data.setValueAt(occurrenceList.get(i).getDesc(), i, 5);
        }
    }

    public void fillsFieldsClient(String id, String nome) {
        txfIdClient.setText(id);
        txfNome.setText(nome);
    }

    private void fillInDataClient() {
        int selectedLine = tblClients.getSelectedRow();

        if (selectedLine != -1) {
            fillsFieldsClient(
                    tblClients.getValueAt(selectedLine, 0).toString(),
                    tblClients.getValueAt(selectedLine, 1).toString()
            );
        }
        enableFields();
    }

    public void fillsFieldsCar(String id, String licensePlate, String model) {
        txfIdCar.setText(id);
        txfLicensePlate.setText(licensePlate);
        txfModel.setText(model);
    }

    private void fillInDataCar() {
        int selectedLine = tblCars.getSelectedRow();

        if (selectedLine != -1) {
            fillsFieldsCar(
                    tblCars.getValueAt(selectedLine, 0).toString(),
                    tblCars.getValueAt(selectedLine, 1).toString(),
                    tblCars.getValueAt(selectedLine, 3).toString()
            );
        }
        enableFields();
    }

    public void fillsFieldsOccurrence(String id, String localDate, String local, String desc) {
        txfId.setText(id);
        txfLocal.setText(local);
        ftfDate.setText(localDate);
        txaDesc.setText(desc);
    }

    public void fillsFieldsOccurrence(String idClient, String name,
                                      String idCar, String licensePlate, String model,
                                      String id, String localDate, String local, String desc) {
        txfIdClient.setText(idClient);
        txfNome.setText(name);

        txfIdCar.setText(idCar);
        txfLicensePlate.setText(licensePlate);
        txfModel.setText(model);

        txfId.setText(id);
        txfLocal.setText(local);
        ftfDate.setText(localDate);
        txaDesc.setText(desc);
    }

    private void fillInDataOccurrence() {
        // TODO Bug** To do search in database
        int selectedLine = tblOccurrences.getSelectedRow();

        if (selectedLine != -1) {
            fillsFieldsOccurrence(
                    tblClients.getValueAt(selectedLine, 0).toString(),
                    tblClients.getValueAt(selectedLine, 1).toString(),

                    tblCars.getValueAt(selectedLine, 0).toString(),
                    tblCars.getValueAt(selectedLine, 1).toString(),
                    tblCars.getValueAt(selectedLine, 3).toString(),

                    tblOccurrences.getValueAt(selectedLine, 0).toString(),
                    tblOccurrences.getValueAt(selectedLine, 3).toString(),
                    tblOccurrences.getValueAt(selectedLine, 4).toString(),
                    tblOccurrences.getValueAt(selectedLine, 5).toString()
            );
        }
        enableFields();
    }

    public boolean showConfirm(String message) {
        int res = JOptionPane.showConfirmDialog(null, message, "Confirm!", JOptionPane.YES_NO_OPTION);

        return res == JOptionPane.YES_NO_OPTION;
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
        scrllpOccurrence = new javax.swing.JScrollPane();
        tblOccurrences = new javax.swing.JTable();
        scrllpClient = new javax.swing.JScrollPane();
        tblClients = new javax.swing.JTable();
        scrllpCar = new javax.swing.JScrollPane();
        tblCars = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblIdClient = new javax.swing.JLabel();
        txfIdClient = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txfNome = new javax.swing.JTextField();
        lblIdCar = new javax.swing.JLabel();
        txfIdCar = new javax.swing.JTextField();
        lblLicensePlate = new javax.swing.JLabel();
        txfLicensePlate = new javax.swing.JTextField();
        lblModel = new javax.swing.JLabel();
        txfModel = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        lblLocal = new javax.swing.JLabel();
        txfLocal = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        ftfDate = new javax.swing.JFormattedTextField();
        lblDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDesc = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnAlter = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblOccurrences.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Carro", "Data", "Local", "Descricao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOccurrences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOccurrencesMouseClicked(evt);
            }
        });
        scrllpOccurrence.setViewportView(tblOccurrences);

        tblClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientsMouseClicked(evt);
            }
        });
        scrllpClient.setViewportView(tblClients);

        tblCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Placa", "Frabicante", "Modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        scrllpCar.setViewportView(tblCars);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scrllpClient, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrllpCar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(scrllpOccurrence, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrllpClient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrllpCar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrllpOccurrence, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIdClient.setText("ID:");

        txfIdClient.setEditable(false);

        lblNome.setText("Nome:");

        txfNome.setEditable(false);

        lblIdCar.setText("ID:");

        txfIdCar.setEditable(false);

        lblLicensePlate.setText("Placa:");

        txfLicensePlate.setEditable(false);

        lblModel.setText("Modelo:");

        txfModel.setEditable(false);

        lblId.setText("ID:");

        lblLocal.setText("Local:");

        lblDate.setText("Data:");

        ftfDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        lblDesc.setText("Descricao:");

        txaDesc.setColumns(20);
        txaDesc.setRows(5);
        jScrollPane1.setViewportView(txaDesc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfId))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblIdClient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblIdCar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfIdCar)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblLicensePlate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfLicensePlate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblDate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftfDate)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblModel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblLocal, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfModel)
                                    .addComponent(txfLocal)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdClient)
                    .addComponent(txfIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome)
                    .addComponent(txfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfIdCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdCar)
                    .addComponent(txfLicensePlate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLicensePlate)
                    .addComponent(txfModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLocal)
                    .addComponent(txfLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate)
                    .addComponent(ftfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDesc)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        cleanFields();
        enableFields();
        ftfDate.setText(LocalDate.now().toString());
        btnRegister.setEnabled(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterActionPerformed
        if (!checksData()) {
            occurrenceController.updateOccurrence(
                    Integer.parseInt(txfId.getText()),
                    Integer.parseInt(txfIdClient.getText()),
                    Integer.parseInt(txfIdCar.getText()),
                    LocalDate.parse(ftfDate.getText()),
                    txfLocal.getText(),
                    txaDesc.getText()
            );
        }
    }//GEN-LAST:event_btnAlterActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        if (!checksData()) {
            occurrenceController.insertOccurrence(
                    Integer.parseInt(txfIdClient.getText()),
                    Integer.parseInt(txfIdCar.getText()),
                    LocalDate.parse(ftfDate.getText()),
                    txfLocal.getText(),
                    txaDesc.getText()
            );
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = Integer.parseInt(txfId.getText());
        occurrenceController.deleteOccurrence(id);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientsMouseClicked
        log.info("Selected row from the customers table");
        fillInDataClient();
    }//GEN-LAST:event_tblClientsMouseClicked

    private void tblCarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarsMouseClicked
        log.info("Selected row from the cars table");
        fillInDataCar();
    }//GEN-LAST:event_tblCarsMouseClicked

    private void tblOccurrencesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOccurrencesMouseClicked
        log.info("Selected row from the occurrences table");
        fillInDataOccurrence();
        btnNew.setEnabled(false);
        btnRegister.setEnabled(false);
        btnAlter.setEnabled(true);
        btnDelete.setEnabled(true);
    }//GEN-LAST:event_tblOccurrencesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlter;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRegister;
    private javax.swing.JFormattedTextField ftfDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdCar;
    private javax.swing.JLabel lblIdClient;
    private javax.swing.JLabel lblLicensePlate;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblNome;
    private javax.swing.JScrollPane scrllpCar;
    private javax.swing.JScrollPane scrllpClient;
    private javax.swing.JScrollPane scrllpOccurrence;
    private javax.swing.JTable tblCars;
    private javax.swing.JTable tblClients;
    private javax.swing.JTable tblOccurrences;
    private javax.swing.JTextArea txaDesc;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfIdCar;
    private javax.swing.JTextField txfIdClient;
    private javax.swing.JTextField txfLicensePlate;
    private javax.swing.JTextField txfLocal;
    private javax.swing.JTextField txfModel;
    private javax.swing.JTextField txfNome;
    // End of variables declaration//GEN-END:variables
}
