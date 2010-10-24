import java.util.List;
	
	/**
6	 * Reads events from the {@link ImdbParser}.
7	 */
	public interface ImdbReader
	{
    /**
     * Creates new movies with specified <code>title</code> and
     * <code>year</code> from a {@link MovieData} list.
     * Every movie will be indexed.
     * @param movieList movies to create and index
     */
    void newMovies( List<MovieData> movieList );

    /**
     * Creates new actors specifying what movies the actors acted in
     * from a {@link ActorData} list.
     * Every actor will be indexed.
     * @param actorList actors to create and index
     */
    void newActors( List<ActorData> actorList );
}
