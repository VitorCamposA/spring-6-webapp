package guru.springframework.spring6webapp.bootstrap;


import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        Author vnnSaved = authorRepository.save(vnn);
        Book nnvSaved = bookRepository.save(nnv);

        ericSaved.getBooks().add(dddSaved);
        vnnSaved.getBooks().add(nnvSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(vnnSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

    }
}
