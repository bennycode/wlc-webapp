package de.fhb.producer;

import de.fhb.model.Author;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AuthorProducer implements Serializable {

  private List<Author> authors;

  public AuthorProducer() {
    this.authors = mockAuthors();
  }

  private List<Author> mockAuthors() {
    List<Author> mockedAuthors = new ArrayList<>();

    Author bennyNeugebauer = new Author("Benny Neugebauer");
    Author tomWendel = new Author("Tom Wendel");
    Author thomassPreuss = new Author("Prof. Dr. Thomas Preuss");

    mockedAuthors.add(bennyNeugebauer);
    mockedAuthors.add(tomWendel);
    mockedAuthors.add(thomassPreuss);

    return mockedAuthors;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

}
