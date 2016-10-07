package igreja.apresentador.musica.gui;

import igreja.apresentador.musica.importar.LetrasImportar;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class ArtistaJPanel extends javax.swing.JPanel
{
    private ArtistaListModel listModel;
    private MusicaByArtistaJPanel musicaJPanel;

    public ArtistaJPanel( MusicaByArtistaJPanel musicaJPanel )
    {
        initComponents();
        init();
        
        this.musicaJPanel = musicaJPanel;
    }

    private void init()
    {
        Thread thread = new Thread( this::listar );
        thread.start();
                
        listModel = new ArtistaListModel();
        jList1.setModel( listModel );
    }
    
    private void listar()
    {
        try
        {
            LetrasImportar letras = new LetrasImportar();
            listModel.addMapa( letras.getArtistas() );
        }
        catch( Exception err )
        {
            JOptionPane.showMessageDialog( this , err );
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pesquisarJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        atualizarJLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Artistas"));

        pesquisarJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pesquisarJTextField.setToolTipText("Pesquisar os artistas existentes");
        pesquisarJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisarJTextFieldKeyReleased(evt);
            }
        });

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Pesquisar:");

        atualizarJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cartorio/recursos/imagens/basic/refresh_16.png"))); // NOI18N
        atualizarJLabel.setToolTipText("Atualizar a lista de musicas do artista.");
        atualizarJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atualizarJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atualizarJLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(atualizarJLabel))
                    .addComponent(pesquisarJTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(atualizarJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisarJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarJTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisarJTextFieldKeyReleased
        switch( evt.getKeyCode() )
        {
            case KeyEvent.VK_F5:
                atualizarJLabelMouseClicked( null );
                break;
            default:
                listModel.find( pesquisarJTextField.getText() );
        }
    }//GEN-LAST:event_pesquisarJTextFieldKeyReleased

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        int row = jList1.getSelectedIndex();
        if( row == -1 )
        {
            return ;
        }
        
        musicaJPanel.setArtista( listModel.getUrl( row ) );
    }//GEN-LAST:event_jList1ValueChanged

    private void atualizarJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atualizarJLabelMouseClicked
        Thread thread = new Thread( this::listar );
        thread.start();
    }//GEN-LAST:event_atualizarJLabelMouseClicked

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        switch( evt.getKeyCode() )
        {
            case KeyEvent.VK_F5:
                atualizarJLabelMouseClicked( null );
                break;
        }
    }//GEN-LAST:event_jList1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel atualizarJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pesquisarJTextField;
    // End of variables declaration//GEN-END:variables
}
