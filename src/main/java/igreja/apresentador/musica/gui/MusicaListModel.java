package igreja.apresentador.musica.gui;

import igreja.apresentador.musica.Musica;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;

public class MusicaListModel extends AbstractListModel<String>
{
    private List<Musica> todas;
    private List<Musica> selecionadas;

    public MusicaListModel()
    {
        todas = new LinkedList<>();
        selecionadas = new LinkedList<>();
    }
    
    @Override
    public int getSize()
    {
        return selecionadas.size();
    }

    @Override
    public String getElementAt( int index )
    {
        if( selecionadas.isEmpty() )
        {
            return "";
        }
        
        return selecionadas.get( index ).getNome().toUpperCase();
    }
    
    public Musica get( int index )
    {
        return selecionadas.get( index );
    }
    
    public void setTodas( List<Musica> l )
    {
        int size = getSize();
        fireIntervalRemoved( selecionadas , 0 , size );
        
        todas.clear();
        selecionadas.clear();
        
        if( l != null )
        {
            todas.addAll( l );
            selecionadas.addAll( l );
        }
        
        fireIntervalAdded( selecionadas , 0 , getSize() - 1 );
    }
    
    public void find( String texto )
    {
        int size = getSize();
        
        fireIntervalRemoved( texto , 0 , size );
        selecionadas.clear();
        
        for( Musica musica : todas )
        {
            if( musica.getNome().toUpperCase().contains( texto.toUpperCase() ) )
            {
                selecionadas.add( musica );
            }
        }
        
        fireContentsChanged( selecionadas , 0 , selecionadas.size() );
    }
    
}