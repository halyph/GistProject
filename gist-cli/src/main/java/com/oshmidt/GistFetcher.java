package com.oshmidt;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;

/**
 * @author oshmidt
 *         <p>
 *         Class adapter for Github library. All server side exceptions throws
 *         up to caller.
 * @see org.eclipse.egit.github.core.Gist;
 * @see org.eclipse.egit.github.core.service.GistService;
 */
public class GistFetcher {
    private final GistService service;

    public GistFetcher() {
        this.service = new GistService();
    }

    public GistFetcher(GistService service) {
        this.service = service;
    }

     /**
      * local GistService instance.
      */

     /**Setting credentials for github.
      * @param user - {@link com.oshmidt.User}
      */
     private void setClientCredentials(final User user) {
          service.getClient().setCredentials(user.getLogin(), user.getPassword());
     }

     /**
      * Method for adding new gist to Github.
      * @param user
      *            - {@link com.oshmidt.User}
      * @param gist
      *            - new gist item
      * @return gist - copy added gist
      * @throws IOException
      */
     public final Gist addNewGist(final User user, final Gist gist)
               throws IOException {
          setClientCredentials(user);
          return service.createGist(gist);
     }

     /**Method load gists from Github.
      * @param user - {@link com.oshmidt.User}
      * @return list gists
      * @throws IOException
      */
     public final List<Gist> loadGists(final User user) throws IOException {
          setClientCredentials(user);
          return service.getGists(user.getLogin());
     }

     /**Method load Gist from github by gis ID.
      * @param gistId - {@link org.eclipse.egit.github.core.Gist#gistID}
      * @param user - {@link com.oshmidt.User}
      * @return Gist - {@link org.eclipse.egit.github.core.Gist}
      * @throws IOException
      */
     public final Gist loadGist(final String gistId, final User user) throws IOException {
          setClientCredentials(user);
          return service.getGist(gistId);
     }

     /**Method update gist.
      * @param user - {@link com.oshmidt.User}
      * @param gist - {@link org.eclipse.egit.github.core.Gist}
      * @return {@link org.eclipse.egit.github.core.Gist}
      * @throws IOException
      */
     public final Gist updateGist(final User user, final Gist gist) throws IOException {
          setClientCredentials(user);
          return service.updateGist(gist);
     }

     /**
      * @param user - {@link com.oshmidt.User}
      * @param gistId - {@link org.eclipse.egit.github.core.Gist#gistID}
      * @throws IOException
      */
     public final void deleteGist(final User user, final String gistId) throws IOException {
          setClientCredentials(user);
          service.deleteGist(gistId);
     }

     /**
      * @param user - {@link com.oshmidt.User}
      * @param gists - {@link org.eclipse.egit.github.core.Gist}
      * @throws IOException
      */
     public final void deleteGists(final User user, final List<Gist> gists) throws IOException {
          for (Gist gist : gists) {
               deleteGist(user, gist.getId());
          }
     }

}
