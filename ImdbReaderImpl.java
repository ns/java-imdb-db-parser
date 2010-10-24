import java.util.List;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

class ImdbReaderImpl implements ImdbReader
{
    private ArrayList<MovieData> movies = new ArrayList<MovieData>();
    private ArrayList<ActorData> actors = new ArrayList<ActorData>();
    private ArrayList<ActorData> actresses = new ArrayList<ActorData>();
    

    public void newActors( final List<ActorData> actorList )
    {
        for ( ActorData actorData : actorList )
        {
            if (!actorData.isActress()) {
              actors.add(actorData);
            }
            else {
              actresses.add(actorData);
            }
        }
    }

    public void newMovies( final List<MovieData> movieList )
    {
        for ( MovieData movieData : movieList )
        {
            movies.add(movieData);
        }
    }
    
    public void writeMoviesXML(final String path) throws Exception {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();
      Element rootElement = document.createElement("movies");
      document.appendChild(rootElement);
      
      for (MovieData m : movies) {
        String element = "movie";
        Element em = document.createElement(element);
        
        String elementTitle = "title";
        Element title = document.createElement(elementTitle);
        title.appendChild(document.createTextNode(m.getTitle()));
        
        String elementYear = "year";
        Element year = document.createElement(elementYear);
        year.appendChild(document.createTextNode(m.getYear() + ""));
        
        em.appendChild(title);
        em.appendChild(year);
        rootElement.appendChild(em);
      }
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(document);
      StreamResult result =  new StreamResult(path);
      transformer.transform(source, result);
    }
    
    public void writeActorsXML(final String path) throws Exception {
      OutputStream out = null; 
      try {
        OutputStream outFile = new FileOutputStream(path);
        out = new BufferedOutputStream(outFile);
        out.write("<actors>".getBytes());
  
  
        for (int i = 0; i < actors.size(); i++) {
          ActorData a = actors.get(i);
    
          out.write("<actor>".getBytes());
    
          out.write("<name>".getBytes());
          out.write(a.getName().getBytes());
          out.write("</name>".getBytes());
    
          out.write("<roles>".getBytes());
          for ( RoleData movieRole : a.getMovieRoles() )
          {
      
            if (movieRole == null || movieRole.getRole() == null) {
        
            }
            else {
              out.write("<role>".getBytes());
              out.write(movieRole.getRole().getBytes());
              out.write("</role>".getBytes());
            }
      
      
            out.write("<movie>".getBytes());
            out.write(movieRole.getTitle().getBytes());
            out.write("</movie>".getBytes());
          }
          out.write("</roles>".getBytes());
    
          out.write("</actor>".getBytes());
    
          int j = 1000;
          if (i % j == 0) {
            System.out.println(i + "");
          }
        }
  
        out.write("</actors>".getBytes());
      } finally {
        if (out != null) {
           out.close();
        }
      }
    }
    
    public void writeActressesXML(final String path) throws Exception {
      OutputStream out = null; 
       try {
          OutputStream outFile = new FileOutputStream(path);
          out = new BufferedOutputStream(outFile);
          
          out.write("<actresses>".getBytes());
          
          
          for (int i = 0; i < actresses.size(); i++) {
            ActorData a = actresses.get(i);
            
            out.write("<actress>".getBytes());
            
            out.write("<name>".getBytes());
            out.write(a.getName().getBytes());
            out.write("</name>".getBytes());
            
            out.write("<roles>".getBytes());
            for ( RoleData movieRole : a.getMovieRoles() )
            {
              if (movieRole == null || movieRole.getRole() == null) {
                
              }
              else {
                out.write("<role>".getBytes());
                out.write(movieRole.getRole().getBytes());
                out.write("</role>".getBytes());
              }
              
              
              out.write("<movie>".getBytes());
              out.write(movieRole.getTitle().getBytes());
              out.write("</movie>".getBytes());
            }
            out.write("</roles>".getBytes());
            
            out.write("</actress>".getBytes());
            
            int j = 1000;
            if (i % j == 0) {
              System.out.println(i + "");
            }
          }
          
          out.write("</actresses>".getBytes());
       } finally {
          if (out != null) {
             out.close();
          }
       }
    }
}