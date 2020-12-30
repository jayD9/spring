package jd.springframework.spring5webapp.bootstrap;

import ch.qos.logback.core.net.SyslogOutputStream;
import jd.springframework.spring5webapp.domain.Author;
import jd.springframework.spring5webapp.domain.Book;
import jd.springframework.spring5webapp.domain.Publisher;
import jd.springframework.spring5webapp.repository.AuthorRepository;
import jd.springframework.spring5webapp.repository.BookRepository;
import jd.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Used to detect as spring managed component by Spring
@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher penguin = new Publisher("Penguin Random House","131 Lake street","Jersey City","NJ","07306");
        publisherRepository.save(penguin);



        Author amish = new Author("Amish","Tripathi");
        Book meluha = new Book("Imortal of melhua","1234");
        amish.getBooks().add(meluha);
        meluha.getAuthors().add(amish);
        meluha.setPublisher(penguin);
        penguin.getBooks().add(meluha);

        authorRepository.save(amish);
        bookRepository.save(meluha);
        publisherRepository.save(penguin);

        Author jkr = new Author("JK","Rowlling");
        Book hp = new Book("Harry Potter","123");
        jkr.getBooks().add(hp);
        hp.getAuthors().add(jkr);
        hp.setPublisher(penguin);
        penguin.getBooks().add(hp);

        authorRepository.save(jkr);
        bookRepository.save(hp);
        publisherRepository.save(penguin);


        System.out.println("Bootstrap stated");
        System.out.println("No of Books: "+bookRepository.count());
        System.out.println("No of Publisher: "+publisherRepository.count());
        System.out.println("No of books publisher have: "+penguin.getBooks().size());
    }
}
