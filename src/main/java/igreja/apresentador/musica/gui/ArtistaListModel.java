package igreja.apresentador.musica.gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.AbstractListModel;

public class ArtistaListModel extends AbstractListModel<String>
{
    private Map<String,String> mapa;
    private List<String> lista;

    public ArtistaListModel()
    {
        mapa  = new TreeMap<>();
        lista = new LinkedList<>();
    }
    
    @Override
    public int getSize()
    {
        return lista.size();
    }

    @Override
    public String getElementAt( int index )
    {
        return lista.get( index ).toUpperCase();
    }
    
    public String getUrl( int index )
    {
        return mapa.get( lista.get( index ) );
    }
    
    public void addMapa( Map<String,String> m )
    {
        mapa.putAll( m );
        
        lista.addAll( new ArrayList<>( m.keySet() ) );
        fireIntervalAdded( m , mapa.size() - m.size() , mapa.size() - 1 );
    }
    
    public void find( String texto )
    {
        int size = getSize();
        
        fireIntervalRemoved( texto , 0 , size );
        lista.clear();
        
        for( String artista : mapa.keySet() )
        {
            if( artista.toUpperCase().contains( texto.toUpperCase() ) )
            {
                lista.add( artista );
            }
        }
        
        fireContentsChanged( lista , 0 , lista.size() );
    }
    
}