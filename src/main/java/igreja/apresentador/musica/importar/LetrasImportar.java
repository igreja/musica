package igreja.apresentador.musica.importar;

import igreja.apresentador.musica.Musica;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class LetrasImportar implements Importar
{

    @Override
    public String getSite()
    {
        return "letras.mus.br";
    }

    @Override
    public Musica importar( String endereco ) throws Exception
    {
        System.out.println( "Se comunicando com o servidor..." );
        String conteudo = UrlUtil.getConteudoByURL( endereco );
        
        //System.out.println( conteudo );
        
        System.out.println( "Tratando os dados..." );
        Musica musica = new Musica();
        musica.setArtista( UrlUtil.getConteudoByTag( conteudo , "<h2>"  , "</h2>" ) );
        musica.setNome   ( UrlUtil.getConteudoByTag( conteudo , "<h1 >" , "</h1>" ) );
        musica.setLetra  ( UrlUtil.getConteudoByTag( conteudo , "article" ) );
        musica.setUrl    ( endereco );
        
        if( musica.getNome().isEmpty() )
        {
            musica.setNome( UrlUtil.getConteudoByTag( conteudo , "<div class=\"lyric-title g-1\"> <h1>" , "</h1>" ) );
        }
        
        System.out.println( "Limpando os dados..." );
        musica.setArtista( UrlUtil.removerTags( musica.getArtista() ) );
        musica.setNome   ( UrlUtil.removerTags( musica.getNome()    ) );
        musica.setLetra  ( UrlUtil.removerTags( musica.getLetra()   ) );
        
        return musica;
    }

    @Override
    public Map<String, String> getArtistas() throws Exception
    {
        String URL = "https://m.letras.mus.br/top.html?slug=gospelreligioso&action=artist&start=";
        Map<String, String> mapa = new TreeMap<>();
        
        for( int i = 0 ; i < 10 ; i++ )
        {
            String conteudo = UrlUtil.getConteudoByURL( URL + (i * 30) );
            
            String artistas = UrlUtil.getConteudoByTag( conteudo , "<ol" , "</ol>" );
            for( String artistaStr : artistas.split( Pattern.quote( "</li>" ) ) )
            {
                if( !artistaStr.contains( "<li>" ) 
                        || !artistaStr.contains( "<a " ) )
                {
                    continue ;
                }

                String artistaNOME = UrlUtil.getConteudoByTag( artistaStr + "</li>"   , "li"  );
                String artistaURL  = UrlUtil.getConteudoByTag( artistaStr , "href=\"" , "\">" );
                
                artistaNOME = UrlUtil.removerTags( artistaNOME );
                artistaURL  = artistaURL.substring( 6 , artistaURL.length() - 2 );

                mapa.put( artistaNOME 
                        , "http://" + getSite() + artistaURL );
            }
        }
                
        return mapa;
    }

    @Override
    public List<Musica> getMusicaByArtista( String endereco ) throws Exception
    {
        String enderecoNovo = endereco + "songlist.ssi?action=all";
        
        System.out.println( "Se comunicando com o servidor..." );
        String conteudo = UrlUtil.getConteudoByURL( enderecoNovo );
        
        //System.out.println( conteudo );
        
        System.out.println( "Tratando os dados..." );
        String artista  = endereco.substring( 0 , endereco.length() - 1 );
        artista = artista.substring( artista.lastIndexOf( "/" ) + 1 )
                         .replaceAll( "-" , " " );
        
        String musicas = UrlUtil.getConteudoByTag( conteudo , "<ul" , "</ul>" );
        musicas = UrlUtil.substituir( musicas );
        
        System.out.println( "Artista: " + artista );
        
        System.out.println( "Identificando as musicas..." );
        List<Musica> lista = new LinkedList<>();
        for( String musicaStr : musicas.split( Pattern.quote( "</li>" ) ) )
        {
            if( !musicaStr.contains( "<li>" ) 
                    || !musicaStr.contains( "<a " ) )
            {
                continue ;
            }
            
            String musicaNome = UrlUtil.getConteudoByTag( musicaStr + "</li>"   , "li"  );
            String musicaUrl  = UrlUtil.getConteudoByTag( musicaStr , "href=\"" , "\">" );
            musicaUrl = musicaUrl.substring( 6 , musicaUrl.length() - 2 );
            
            Musica musica = new Musica();
            musica.setArtista( artista );
            musica.setNome   ( UrlUtil.removerTags( musicaNome ) );
            musica.setUrl    ( "http://" + getSite() + musicaUrl );
            
            lista.add( musica );
        }
        
        return lista;
    }
    
}