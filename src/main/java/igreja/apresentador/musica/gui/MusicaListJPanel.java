package igreja.apresentador.musica.gui;

public class MusicaListJPanel extends javax.swing.JPanel
{
    private MusicaByArtistaJPanel musicasJPanel;
    private MusicaJPanel musicaJPanel;

    public MusicaListJPanel()
    {
        initComponents();
        init();
    }
    
    private void init()
    {
        musicaJPanel  = new MusicaJPanel();
        musicasJPanel = new MusicaByArtistaJPanel( musicaJPanel );
        
        artistaJScrollPane.setViewportView( new ArtistaJPanel( musicasJPanel ) );
        musicaJScrollPane .setViewportView( musicasJPanel );
        letrajScrollPane  .setViewportView( musicaJPanel  );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        artistaJScrollPane = new javax.swing.JScrollPane();
        musicaJScrollPane = new javax.swing.JScrollPane();
        letrajScrollPane = new javax.swing.JScrollPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        artistaJScrollPane.setBorder(null);
        add(artistaJScrollPane);

        musicaJScrollPane.setBorder(null);
        add(musicaJScrollPane);

        letrajScrollPane.setBorder(null);
        add(letrajScrollPane);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane artistaJScrollPane;
    private javax.swing.JScrollPane letrajScrollPane;
    private javax.swing.JScrollPane musicaJScrollPane;
    // End of variables declaration//GEN-END:variables
}
