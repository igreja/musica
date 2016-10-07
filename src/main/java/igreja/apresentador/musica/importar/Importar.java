package igreja.apresentador.musica.importar;

import igreja.apresentador.musica.Musica;
import java.util.List;
import java.util.Map;

public interface Importar
{
    public String getSite();
    public Map<String,String> getArtistas() throws Exception;
    public List<Musica> getMusicaByArtista( String endereco ) throws Exception;
    public Musica importar( String endereco ) throws Exception;
}