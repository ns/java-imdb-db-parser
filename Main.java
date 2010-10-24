public class Main
{
  public static void main(String[] args) throws Exception {
    ImdbReaderImpl iri = new ImdbReaderImpl();
    ImdbParser ip = new ImdbParser(iri);

    // ip.parseMovies("/path/to/file/movies.list.gz");
    
    // this parses actors and actresses
    ip.parseActors("/path/to/file/actors.list.gz", "/path/to/file/actresses.list.gz");
    
    // ip.parseMovies("/path/to/file/movies.list.gz");
    // iri.writeMoviesXML("/path/to/file/movies.xml");
    // iri.writeActorsXML("/path/to/file/actors.xml");
    iri.writeActressesXML("/path/to/file/actresses.xml");
  }
}