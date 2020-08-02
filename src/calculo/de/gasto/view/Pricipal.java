package calculo.de.gasto.view;

import calculo.de.gasto.model.Tabela_de_Gasto;
import calculo.de.gasto.model.BancodeDado;
import calculo.de.gasto.controller.Calcular;
import calculo.de.gasto.controller.Registro_de_gasto;
import calculo.de.gasto.controller.Verificador;
import calculo.de.gasto.controller.Class_de_hora_data;
import calculo.de.gasto.controller.Excecao;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neviton viana
 */
public class Pricipal extends javax.swing.JFrame {

    BancodeDado conectarbaca = new BancodeDado();
    Tabela_de_Gasto tabela_gasto = new Tabela_de_Gasto(conectarbaca);
    Calcular calcular = new Calcular();
    Registro_de_gasto registra = new Registro_de_gasto();
    Verificador verificarNumero = new Verificador();
    Class_de_hora_data data = new Class_de_hora_data();
    Excecao excecao = new Excecao();
    
    
    public Pricipal() {
        initComponents();
        try{
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icons/livro_gasto.png")).getImage());
        }catch(Exception e){
            excecao.Aviso("icon não encontrado "+ e);
        }
    }
    
    public String gasto() {
        String ultimaInsesao = null;
        tabela_gasto.tabela_gasto();
        LabelGasto.setText("" + calcular.Soma_Gasto());

        if (calcular.Soma_saldo() < 0) {
            LabelSaldo.setForeground(Color.red);
            Labelnomesaldo.setText("Seu Saldo é Insuficiente");
           
        }else{
            Labelnomesaldo.setText("Saldo atual");
            LabelSaldo.setForeground(Color.black);
        }
            LabelSaldo.setText("" + calcular.Soma_saldo());
        ResultSet resultaset = null;
        Statement statment = null;
        try {
            conectarbaca.conectar();

            String query = "SELECT * FROM tb_de_gasto";
            statment = conectarbaca.criarStatement();
            DefaultTableModel modelo = (DefaultTableModel) Lista_Gasto.getModel();
            modelo.setNumRows(0);

            resultaset = statment.executeQuery(query);
            while (resultaset.next()) {
                modelo.addRow(new Object[]{resultaset.getString("nome"),
                    resultaset.getString("valor_gasto"),
                    resultaset.getString("valor_deposito"),
                    resultaset.getString("data")});
                ultimaInsesao = resultaset.getString("nome");
            }
            resultaset.close();
            statment.close();
        } catch (SQLException e) {
            excecao.Error("Não foi posivel lista os \n dados do Banco de Dados \n"+ e);
        } finally {
            try {
                resultaset.close();
                statment.close();
                conectarbaca.desconectar();
            } catch (SQLException e) {
                excecao.Aviso("Não foi posivel fechr o Banco de Dados \n"+ e);
            }
        }
        return ultimaInsesao;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        Seletor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lista_Gasto = new javax.swing.JTable();
        Labeltaltadodegasto = new javax.swing.JLabel();
        Labelnomesaldo = new javax.swing.JLabel();
        LabelSaldo = new javax.swing.JLabel();
        LabelGasto = new javax.swing.JLabel();
        btnexcluir = new javax.swing.JButton();
        btnatualizar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GERENCIADOR DE GASTOS");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));

        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(234, 213, 73));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome");
        jLabel1.setFocusTraversalPolicyProvider(true);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Seletor.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Seletor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gasto", "Saldo" }));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Valor");
        jLabel2.setFocusTraversalPolicyProvider(true);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtValor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSalvar.setBackground(new java.awt.Color(151, 226, 55));
        btnSalvar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(153, 255, 255));
        jLabel3.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("GERENCIADOR DE GASTOS");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Seletor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Seletor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Salvar", new javax.swing.ImageIcon(getClass().getResource("/icons/disquete.png")), jPanel1); // NOI18N

        jPanel2.setBackground(new java.awt.Color(234, 213, 73));

        Lista_Gasto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Gasto", "Saldo", "Data"
            }
        ));
        jScrollPane1.setViewportView(Lista_Gasto);

        Labeltaltadodegasto.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        Labeltaltadodegasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labeltaltadodegasto.setText("Total Dos Gastos ");
        Labeltaltadodegasto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Labelnomesaldo.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        Labelnomesaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelnomesaldo.setText("Saldo atual");
        Labelnomesaldo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LabelSaldo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        LabelSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelSaldo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LabelSaldo.setPreferredSize(getPreferredSize());

        LabelGasto.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        LabelGasto.setForeground(new java.awt.Color(153, 0, 0));
        LabelGasto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelGasto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LabelGasto.setPreferredSize(getPreferredSize());

        btnexcluir.setBackground(new java.awt.Color(208, 47, 25));
        btnexcluir.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnexcluir.setText("Excluir");
        btnexcluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexcluirActionPerformed(evt);
            }
        });

        btnatualizar.setBackground(new java.awt.Color(74, 246, 65));
        btnatualizar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnatualizar.setText("Atualizar");
        btnatualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnatualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnatualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(354, 354, 354)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LabelGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Labeltaltadodegasto))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Labelnomesaldo)
                    .addComponent(LabelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnatualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(LabelGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Labeltaltadodegasto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Labelnomesaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(LabelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Lista De Gastos", new javax.swing.ImageIcon(getClass().getResource("/icons/dinheiro.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatualizarActionPerformed
        gasto();
    }//GEN-LAST:event_btnatualizarActionPerformed

    private void btnexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexcluirActionPerformed
        Icon iconverificado = new javax.swing.ImageIcon(getClass().getResource("/icons/verificado.png"));
        Icon iconexcluir = new javax.swing.ImageIcon(getClass().getResource("/icons/excluir.png"));
        Icon iconerro = new javax.swing.ImageIcon(getClass().getResource("/icons/error.png"));
        String ultimaInsesao = gasto();
        
        if (ultimaInsesao != null) {
            int conficamar = JOptionPane.showConfirmDialog(this, "Tem Certeza Que Deseja\n Excluir  \"" + ultimaInsesao + "\"", "EXCLUIR", 0, 0, iconexcluir);
            if (conficamar == 0) {
                registra.exluir_ultimo_item(ultimaInsesao);
                JOptionPane.showMessageDialog(this, "Excluido Com Susseso", "EXCLUSÃO CONFICMADO",JOptionPane.INFORMATION_MESSAGE, iconverificado);
                gasto();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não Ha Nenhum Informação", "DADOS", JOptionPane.ERROR_MESSAGE, iconerro);

        }
    }//GEN-LAST:event_btnexcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Icon icon = new javax.swing.ImageIcon(getClass().getResource("/icons/salvar.png"));
        float valor = verificarNumero.Verificado(txtValor.getText().replace(",", "."));
        if (valor == 0) {
            JOptionPane.showMessageDialog(this, "SOMENTE NÚMEROS NO CAMPO DE VALOR", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            String campobox = (String) Seletor.getSelectedItem();
            if (campobox.equalsIgnoreCase("gasto")) {
                float saldo = calcular.Calculo_gasto(valor);
                registra.Inserir_gasto(txtNome.getText(), valor, saldo, data.Data());
            } else {
                float saldo = calcular.Calcula_Saldo(valor);
                registra.Inserir_gasto(txtNome.getText(), 0, saldo, data.Data());
            }
            JOptionPane.showMessageDialog(this, "SALVO COM SUCESSO", "SALVO", JOptionPane.PLAIN_MESSAGE, icon);
            txtNome.setText("");
            txtValor.setText("");
            gasto();
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pricipal().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelGasto;
    private javax.swing.JLabel LabelSaldo;
    private javax.swing.JLabel Labelnomesaldo;
    private javax.swing.JLabel Labeltaltadodegasto;
    private javax.swing.JTable Lista_Gasto;
    private javax.swing.JComboBox<String> Seletor;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnatualizar;
    private javax.swing.JButton btnexcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
