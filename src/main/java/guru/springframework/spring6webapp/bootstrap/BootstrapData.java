package guru.springframework.spring6webapp.bootstrap;


import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setPrimeiroNome("Eric");
        eric.setSobrenome("Evans");

        Book ddd = new Book();
        ddd.setTitulo("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);


        Author vnn = new Author();
        vnn.setPrimeiroNome("Vitor");
        vnn.setSobrenome("Campos");

        Book nnv = new Book();
        nnv.setTitulo("Nine Nights Victims");
        nnv.setIsbn("092146");

        Publisher noWay = new Publisher();
        noWay.setAdress("Saint Tome Street - Num 41");
        noWay.setPublisherName("NoWayEntertainment");
        noWay.setZip("23393");
        noWay.setState("California");
        noWay.setCity("Los Angeles");




        Author vnnSaved = authorRepository.save(vnn);
        Book nnvSaved = bookRepository.save(nnv);
        Publisher noWaySaved = publisherRepository.save(noWay);

        dddSaved.setPublisher(noWaySaved);
        nnvSaved.setPublisher(noWaySaved);

        ericSaved.getBooks().add(dddSaved);
        vnnSaved.getBooks().add(nnvSaved);
        dddSaved.getAuthors().add(ericSaved);
        nnvSaved.getAuthors().add(vnnSaved);


        authorRepository.save(ericSaved);
        authorRepository.save(vnnSaved);
        bookRepository.save(nnvSaved);
        bookRepository.save(dddSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }
}
