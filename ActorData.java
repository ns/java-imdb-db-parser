public class ActorData
{
  private final String name;
  private final RoleData[] movieRoles;
  private boolean actress = false;

  /**
   * Create container for actor data.
   * @param name
   *            name of actor
   * @param movieRoles
   *            movie roles of actor
   */
  ActorData( final String name, final RoleData[] movieRoles, boolean actress )
  {
      this.movieRoles = movieRoles;
      this.name = name;
      this.actress = actress;
  }

  public String getName()
  {
      return name;
  }

  public RoleData[] getMovieRoles()
  {
      return movieRoles;
  }
  
  public boolean isActress() {
    return this.actress;
  }
}
