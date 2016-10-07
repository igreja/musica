package igreja.apresentador.musica;

public class Musica
{
    private int id;
    private String artista;
    private String nome;
    private String letra;
    private String url;
    
    public Musica()
    {
        // faz nada
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getArtista()
    {
        return artista;
    }

    public void setArtista( String artista )
    {
        this.artista = artista;
    }

    public String getLetra()
    {
        return letra;
    }

    public void setLetra( String letra )
    {
        this.letra = letra;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url ) 
    {
        this.url = url;
    }
    
}