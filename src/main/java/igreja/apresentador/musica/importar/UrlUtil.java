package igreja.apresentador.musica.importar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil
{
    
    public static String getConteudoByURL( String endereco ) throws Exception
    {
        URL oracle = new URL( endereco );
        HttpURLConnection connection = (HttpURLConnection) oracle.openConnection();
        StringBuilder builder;
        
        // ----------- verificar
        if( connection.getResponseCode() == 302
                || connection.getResponseCode() == 301 )
        {
            //System.out.println( "Nova URL: " + connection.getHeaderField( "Location" ) );
            return getConteudoByURL( connection.getHeaderField( "Location" ) );
        }
        else if( connection.getResponseCode() != 200 )
        {
            throw new Exception( "Ocorreu um erro: " + connection.getResponseMessage() );
        }
        
        // ----------- retornar o conteudo
        try ( BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream() , "UTF-8" ) ) )
        {
            builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                builder.append( inputLine )
                        .append( "\n" );
            }
        }
        
        return builder.toString();
    }
    
    public static String getConteudoByTag( String conteudo , String tag ) throws Exception
    {
        return getConteudoByTag( conteudo 
                              , "<" + tag + ">" 
                              , "</" + tag + ">" );
    }
    
    public static String getConteudoByTag( String conteudo , String tagStart , String tagEnd ) throws Exception
    {
        int start = conteudo.indexOf( tagStart );
        
        if( start == -1 )
        {
            return "";
        }
        
        int end   = conteudo.substring( start ).indexOf( tagEnd );
        
        if( end == -1 )
        {
            return "";
        }
        else
        {
            return conteudo.substring( start , start + end ) 
                    + tagEnd;
        }
    }
    
    public static String removerTags( String conteudo ) throws UnsupportedEncodingException
    {
        String texto = "";
        
        for( int i = 0 ; i < conteudo.length() ; i++ )
        {
            if( conteudo.charAt( i ) == '<' )
            {
                String sub = conteudo.substring( i );
                
                if( sub.startsWith( "</p>" ) 
                        || sub.startsWith( "</P>" ) )
                {
                    texto += "\n\n";
                }
                else if( sub.startsWith( "<br" ) 
                        || sub.startsWith( "</br" ) 
                        || sub.startsWith( "<BR" ) )
                {
                    texto += "\n";
                }
                
                i = i + sub.indexOf( ">" );
            }
            else
            {
                texto += conteudo.charAt( i );
            }
        }
        
        while( texto.contains( "\n\n\n" ) )
        {
            texto = texto.replaceAll( "\n \n"  , "\n\n" );
            texto = texto.replaceAll( "\n\n\n" , "\n\n" );
        }
        
        return substituir( texto );
    }
    
    public static String substituir( String conteudo ) throws UnsupportedEncodingException
    {
        return java.net.URLDecoder.decode( conteudo , "UTF-8" )
                .replaceAll( "&#39;"  , "'"  )
                .replaceAll( "&quot;" , "\"" )
                .replaceAll( "&amp;"  , "&"  )
                .trim();
    }
    
}