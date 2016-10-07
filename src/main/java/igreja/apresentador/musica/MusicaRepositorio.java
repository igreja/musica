package igreja.apresentador.musica;

import igreja.apresentador.util.ManipulacaoSql;
import igreja.apresentador.util.SelectSql;
import igreja.apresentador.util.TableSql;
import java.sql.ResultSet;
import java.util.List;

public class MusicaRepositorio
{
    
    public MusicaRepositorio() throws Exception
    {
        new TableSql( "APP.musica" )
                .addPrimaryKey( "id" , "INT" )
                .add( "nome"    , "VARCHAR(250)" )
                .add( "artista" , "VARCHAR(250)" )
                .add( "url"     , "VARCHAR(250)" )
                .add( "letra"   , "LONG VARCHAR FOR BIT DATA" )
            .save();
    }
    
    public void adicionar( Musica musica ) throws Exception
    {
        int id = new ManipulacaoSql( "APP.musica" )
                        .add( "nome"    , musica.getNome()    )
                        .add( "artista" , musica.getArtista() )
                        .add( "url"     , musica.getUrl()     )
                        .add( "letra"   , musica.getLetra()   )
                    .insert();
        
        musica.setId( id );
    }
    
    public void modificar( Musica musica ) throws Exception
    {
        new ManipulacaoSql( "APP.musica" )
                .add( "nome"    , musica.getNome()    )
                .add( "artista" , musica.getArtista() )
                .add( "url"     , musica.getUrl()     )
                .add( "letra"   , musica.getLetra()   )
            .update( "id = " + musica.getId() );
    }
    
    public void excluir( int id ) throws Exception
    {
        new ManipulacaoSql( "APP.musica" )
            .delete( "id = " + id );
    }
    
    public Musica get( int id ) throws Exception
    {
        return new SelectSql<Musica>( "APP.musica" ){
                @Override
                public Musica converter( ResultSet rs ) throws Exception
                {
                    Musica musica = new Musica();
                    musica.setId( rs.getInt( "id" ) );
                    musica.setArtista( rs.getString( "artista" ) );
                    musica.setNome   ( rs.getString( "nome"    ) );
                    musica.setLetra  ( rs.getString( "letra"   ) );
                    musica.setUrl    ( rs.getString( "url"     ) );
                    
                    return musica;
                }
            }.getOne( "id = " + id );
    }
    
    public List<Musica> listarMusicaByArtistas( String artista ) throws Exception
    {
        return new SelectSql<Musica>( "APP.musica" ){
                @Override
                public Musica converter( ResultSet rs ) throws Exception
                {
                    Musica musica = new Musica();
                    musica.setId( rs.getInt( "id" ) );
                    musica.setArtista( rs.getString( "artista" ) );
                    musica.setNome   ( rs.getString( "nome"    ) );
                    musica.setLetra  ( rs.getString( "letra"   ) );
                    musica.setUrl    ( rs.getString( "url"     ) );
                    
                    return musica;
                }
            }.getMany( "artista = " + artista );
    }
    
    public List<String> listarArtistas() throws Exception
    {
        return new SelectSql<String>( "APP.musica" ){
                @Override
                public String converter( ResultSet rs ) throws Exception
                {
                    return rs.getString( "artista" );
                }
            }.getMany( "DISTINCT artista" 
                     , "artista != ''" );
    }
    
}