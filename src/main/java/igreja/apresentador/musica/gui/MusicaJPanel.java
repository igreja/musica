package igreja.apresentador.musica.gui;

import igreja.apresentador.musica.Musica;
import igreja.apresentador.musica.MusicaControle;
import igreja.apresentador.musica.importar.LetrasImportar;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.JOptionPane;

public class MusicaJPanel extends javax.swing.JPanel
{
    private Thread thread;
    private int id;

    public MusicaJPanel()
    {
        initComponents();
    }
    
    public void setMusica( String endereco )
    {
        if( thread != null )
        {
            thread.interrupt();
            thread = null;
        }
        
        thread = new Thread( () -> {
            try
            {
                LetrasImportar letras = new LetrasImportar();
                setMusica( letras.importar( endereco ) );
            }
            catch( Exception err )
            {
                JOptionPane.showMessageDialog( null , err );
            }
        });
        
        thread.start();
    }
    
    public void setMusica( Musica musica )
    {
        if( musica == null )
        {
            musica = new Musica();
        }
        
        id = musica.getId();
        artistaJTextField.setText( musica.getArtista() );
        nomeJTextField   .setText( musica.getNome()    );
        linkJTextField   .setText( musica.getUrl()     );
        letraJTextArea   .setText( musica.getLetra()   );
    }
    
    public Musica getMusica()
    {
        Musica musica = new Musica();
        musica.setId( id );
        musica.setNome   ( nomeJTextField.getText() );
        musica.setArtista( artistaJTextField.getText() );
        musica.setUrl    ( linkJTextField.getText() );
        musica.setLetra  ( letraJTextArea.getText() );
        
        return musica;
    }
    
    private void open()
    {
        String url = linkJLabel.getText();

        try
        {
            Desktop desktop = null;

            //Primeiro verificamos se é possível a integração com o desktop
            if ( !Desktop.isDesktopSupported() )
            {
                throw new IllegalStateException("Desktop resources not supported!");
            }

            desktop = Desktop.getDesktop();

            //Agora vemos se é possível disparar o browser default.
            if (!desktop.isSupported(Desktop.Action.BROWSE))
            {
                throw new IllegalStateException("No default browser set!");
            }

            //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.
            desktop.browse( new URI( linkJLabel.getText() ) );
        }
        catch( Exception err )
        {
            try
            {
                Runtime.getRuntime().exec("cmd.exe /C start iexplore.exe " + url );
            }
            catch( Exception err02 )
            {
                JOptionPane.showMessageDialog( this , err02 );
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        artistaJTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nomeJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        letraJTextArea = new javax.swing.JTextArea();
        excluirJButton = new javax.swing.JButton();
        salvarJButton = new javax.swing.JButton();
        linkJLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        linkJTextField = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da música"));

        jLabel1.setText("Artista:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Letra");

        letraJTextArea.setColumns(20);
        letraJTextArea.setRows(5);
        jScrollPane1.setViewportView(letraJTextArea);

        excluirJButton.setText("Excluir");
        excluirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirJButtonActionPerformed(evt);
            }
        });

        salvarJButton.setText("Salvar");
        salvarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarJButtonActionPerformed(evt);
            }
        });

        linkJLabel.setText("Link:");

        linkJTextField.setForeground(new java.awt.Color(51, 51, 255));
        linkJTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkJTextFieldMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(linkJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artistaJTextField)
                            .addComponent(nomeJTextField)
                            .addComponent(linkJTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(salvarJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(artistaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(linkJLabel)
                    .addComponent(linkJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excluirJButton)
                    .addComponent(salvarJButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void linkJTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkJTextFieldMouseClicked
        if( evt.getClickCount() >= 2 )
        {
            open();
        }
    }//GEN-LAST:event_linkJTextFieldMouseClicked

    private void salvarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarJButtonActionPerformed
        int resposta = JOptionPane.showConfirmDialog( this , "Deseja salvar as alterações?" );
        if( resposta == JOptionPane.YES_OPTION )
        {
            try
            {
                Musica musica = getMusica();
                
                if( id == 0 )
                {
                    MusicaControle.getInstancia().adicionar( musica );
                }
                else
                {
                    MusicaControle.getInstancia().modificar( musica );
                }
                
                JOptionPane.showMessageDialog( this , "Musica foi salva!" );
            }
            catch( Exception err )
            {
                err.printStackTrace();
                JOptionPane.showMessageDialog( this , err );
            }
        }
    }//GEN-LAST:event_salvarJButtonActionPerformed

    private void excluirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirJButtonActionPerformed
        int resposta = JOptionPane.showConfirmDialog( this , "Deseja excluir a música?" );
        if( resposta == JOptionPane.YES_OPTION )
        {
            try
            {
                MusicaControle.getInstancia().excluir( id );
                JOptionPane.showMessageDialog( this , "Musica foi excluída!" );
            }
            catch( Exception err )
            {
                JOptionPane.showConfirmDialog( this , err );
            }
        }
    }//GEN-LAST:event_excluirJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField artistaJTextField;
    private javax.swing.JButton excluirJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea letraJTextArea;
    private javax.swing.JLabel linkJLabel;
    private javax.swing.JTextField linkJTextField;
    private javax.swing.JTextField nomeJTextField;
    private javax.swing.JButton salvarJButton;
    // End of variables declaration//GEN-END:variables
}
