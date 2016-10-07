package igreja.apresentador.musica;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MusicaControle
{
    private static MusicaControle instancia;
    private MusicaRepositorio banco;
    
    private MusicaControle() throws Exception
    {
        banco = new MusicaRepositorio();
    }

    public static MusicaControle getInstancia() throws Exception
    {
        if( instancia == null )
        {
            instancia = new MusicaControle();
        }
        
        return instancia;
    }
    
    public void adicionar( Musica musica ) throws Exception
    {
        banco.adicionar( musica );
    }
    
    public void modificar( Musica musica ) throws Exception
    {
        banco.modificar( musica );
    }
    
    public void excluir( int id ) throws Exception
    {
        banco.excluir( id );
    }
    
    public Musica get( int id ) throws Exception
    {
        return banco.get( id );
    }
    
    public List<Musica> listarMusicaByArtista( String artista ) throws Exception
    {
        return banco.listarMusicaByArtistas( artista );
    }
    
    public Map<String,String> listar() throws Exception
    {
        Map<String,String> mapa = new TreeMap<>();
        for( String artista : banco.listarArtistas() )
        {
            mapa.put( artista , "" );
        }
        
        return mapa;
    }
    
}