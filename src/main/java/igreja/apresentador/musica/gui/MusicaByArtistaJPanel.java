package igreja.apresentador.musica.gui;

import igreja.apresentador.musica.Musica;
import igreja.apresentador.musica.importar.LetrasImportar;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class MusicaByArtistaJPanel extends javax.swing.JPanel
{
    private MusicaJPanel musicaJPanel;
    private MusicaListModel listModel;
    private Thread thread;
    private String enderecoUltimo;

    public MusicaByArtistaJPanel( MusicaJPanel musicaJPanel )
    {
        initComponents();
        
        // ------------- //
        
        this.musicaJPanel = musicaJPanel;
        
        listModel = new MusicaListModel();
        jList1.setModel( listModel );
    }

    public void setArtista( String endereco )
    {
        pesquisarJTextField.setText( "" );
        enderecoUltimo = endereco;
        
        if( thread != null )
        {
            thread.interrupt();
            thread = null;
        }
        
        thread = new Thread( () -> {
            try
            {
                LetrasImportar letras = new LetrasImportar();
                listModel.setTodas( letras.getMusicaByArtista( endereco ) );
            }
            catch( Exception err )
            {
                JOptionPane.showMessageDialog( null , err );
            }
        });
        
        thread.start();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pesquisarJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        adicionarjLabel = new javax.swing.JLabel();
        excluirJLabel = new javax.swing.JLabel();
        atualizarJLabel = new javax.swing.JLabel();
        importarjLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Músicas"));

        pesquisarJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pesquisarJTextField.setToolTipText("Pesquisar os artistas existentes");
        pesquisarJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisarJTextFieldKeyReleased(evt);
            }
        });

        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jList1KeyReleased(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Pesquisar:");

        adicionarjLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cartorio/recursos/imagens/basic/plus_16.png"))); // NOI18N
        adicionarjLabel.setToolTipText("Adicionar uma musica referente ao artista.");
        adicionarjLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adicionarjLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarjLabelMouseClicked(evt);
            }
        });

        excluirJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cartorio/recursos/imagens/basic/delete_16.png"))); // NOI18N
        excluirJLabel.setToolTipText("Excluir uma das musicas do artista.");
        excluirJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirJLabelMouseClicked(evt);
            }
        });

        atualizarJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cartorio/recursos/imagens/basic/refresh_16.png"))); // NOI18N
        atualizarJLabel.setToolTipText("Atualizar a lista de musicas do artista.");
        atualizarJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atualizarJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atualizarJLabelMouseClicked(evt);
            }
        });

        importarjLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/cartorio/recursos/imagens/basic/down_16.png"))); // NOI18N
        importarjLabel.setToolTipText("Importar as músicas selecionadas.");
        importarjLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        importarjLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importarjLabelMouseClicked(evt);
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
                        .addComponent(adicionarjLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importarjLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(atualizarJLabel))
                    .addComponent(pesquisarJTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(excluirJLabel))
                    .addComponent(atualizarJLabel)
                    .addComponent(importarjLabel)
                    .addComponent(adicionarjLabel))
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
            case KeyEvent.VK_PLUS:
                adicionarjLabelMouseClicked( null );
                break;
            case KeyEvent.VK_DELETE:
                excluirJLabelMouseClicked( null );
                break;
            default: listModel.find( pesquisarJTextField.getText() );
        }
    }//GEN-LAST:event_pesquisarJTextFieldKeyReleased

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        int row = jList1.getSelectedIndex();
        if( row == -1 )
        {
            return ;
        }
        
        Musica musica = listModel.get( row );
        
        if( musica.getId() == 0 )
        {
            musicaJPanel.setMusica( musica.getUrl() );
        }
        else
        {
            musicaJPanel.setMusica( musica );
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void atualizarJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atualizarJLabelMouseClicked
        if( enderecoUltimo != null 
                && !enderecoUltimo.isEmpty() )
        {
            setArtista( enderecoUltimo );
        }
    }//GEN-LAST:event_atualizarJLabelMouseClicked

    private void excluirJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirJLabelMouseClicked
        int row = jList1.getSelectedIndex();
        if( row == -1 )
        {
            return ;
        }
        
        int resposta = JOptionPane.showConfirmDialog( this , "Deseja excluir a(s) música(s) selecionada(s)?" );
        if( resposta == JOptionPane.YES_OPTION )
        {
            // excluir o item do banco, se estiver salva no banco de dados, e da tela
        }
    }//GEN-LAST:event_excluirJLabelMouseClicked

    private void adicionarjLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adicionarjLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_adicionarjLabelMouseClicked

    private void jList1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyReleased
        switch( evt.getKeyCode() )
        {
            case KeyEvent.VK_F5: 
                atualizarJLabelMouseClicked( null );
                break;
            case KeyEvent.VK_PLUS:
                adicionarjLabelMouseClicked( null );
                break;
            case KeyEvent.VK_DELETE:
                excluirJLabelMouseClicked( null );
                break;
        }
    }//GEN-LAST:event_jList1KeyReleased

    private void importarjLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importarjLabelMouseClicked
        int row = jList1.getSelectedIndex();
        if( row == -1 )
        {
            return ;
        }
        
        int resposta = JOptionPane.showConfirmDialog( this , "Deseja importar a(s) música(s) selecionada(s)?" );
        if( resposta == JOptionPane.YES_OPTION )
        {
            // excluir o item do banco, se estiver salva no banco de dados, e da tela
        }
    }//GEN-LAST:event_importarjLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adicionarjLabel;
    private javax.swing.JLabel atualizarJLabel;
    private javax.swing.JLabel excluirJLabel;
    private javax.swing.JLabel importarjLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pesquisarJTextField;
    // End of variables declaration//GEN-END:variables
}
