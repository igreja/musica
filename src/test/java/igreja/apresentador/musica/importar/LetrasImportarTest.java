package igreja.apresentador.musica.importar;

import igreja.apresentador.musica.Musica;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static org.junit.Assert.*;

public class LetrasImportarTest
{
    
    public LetrasImportarTest()
    {
        // faz nada
    }
    
    @org.junit.Test
    public void testArtistas() throws Exception
    {
        System.out.println( "Mostrar todos os artistas gospeis do letras.mus.br..." );
        
        LetrasImportar instance = new LetrasImportar();
        Map<String,String> resultado = instance.getArtistas();
        
        for( Entry<String,String> entry : resultado.entrySet() )
        {
            System.out.print  ( entry.getKey()   + "\t" );
            System.out.println( entry.getValue() );
        }
        
        assertTrue ( !resultado.isEmpty() );
    }
    
    @org.junit.Test
    public void testMusicas() throws Exception
    {
        System.out.println( "Mostrar as musicas de DIANTE DO TRONO do letras.mus.br..." );
        String endereco = "https://www.letras.mus.br/diante-do-trono/";
        
        LetrasImportar instance = new LetrasImportar();
        List<Musica> resultado = instance.getMusicaByArtista( endereco );
        
        for( Musica musica : resultado )
        {
            System.out.print( musica.getArtista() + "\t" );
            System.out.print( musica.getNome() + "\t" );
            System.out.println( musica.getUrl() );
        }
        
        assertTrue ( !resultado.isEmpty() );
        assertEquals( "DIANTE DO TRONO" , resultado.get( 0 ).getArtista().toUpperCase() );
    }
    
    @org.junit.Test
    public void testImportar() throws Exception
    {
        System.out.println( "Importando uma musica do site letras.mus.br..." );
        String endereco = "http://www.letras.mus.br/sent-by-ravens/mean-what-you-say/";
        
        LetrasImportar instance = new LetrasImportar();
        Musica resultado = instance.importar( endereco );
        
        System.out.println( "Artista: " + resultado.getArtista().toUpperCase() );
        System.out.println( "Nome: " + resultado.getNome().toUpperCase() );
        System.out.println( "Letra: " + resultado.getLetra().toUpperCase() );
        
        assertEquals( getMusica().getArtista().toUpperCase() , resultado.getArtista().toUpperCase() );
        assertEquals( getMusica().getNome().toUpperCase()    , resultado.getNome().toUpperCase()    );
        assertEquals( getMusica().getLetra().toUpperCase()   , resultado.getLetra().toUpperCase()   );
    }
    
    public Musica getMusica()
    {
        Musica musica = new Musica();
        musica.setArtista( "Sent by Ravens"    );
        musica.setNome   ( "Mean What You Say" );
        musica.setLetra("Every inch we talk\n" +
                        "They make a sound, we walk\n" +
                        "You better mean what you say\n" +
                        "You better mean what you say\n" +
                        "Less god, more gun\n" +
                        "They aim for the heart, but we ain't got one\n" +
                        "You better mean what you say\n" +
                        "You better mean what you say\n" +
                        "\n" +
                        "I don't need to hurt with my hands\n" +
                        "I could find some value, destroy it with my mouth\n" +
                        "Hey, could you ever love again?\n" +
                        "\n" +
                        "From the womb, we're taught\n" +
                        "You make a sound, i walk\n" +
                        "You better mean what you say\n" +
                        "You better mean what you say\n" +
                        "With words like drugs\n" +
                        "We speak 'em hard like we know what one does\n" +
                        "You better mean what you say\n" +
                        "You better mean what you say\n" +
                        "\n" +
                        "I don't need to hurt with my hands\n" +
                        "I could find some value, destroy it with my mouth\n" +
                        "Hey, could you ever love again?\n" +
                        "\n" +
                        "I regret the things i've said\n" +
                        "Could you ever love again?");
        
        return musica;
    }
    
}