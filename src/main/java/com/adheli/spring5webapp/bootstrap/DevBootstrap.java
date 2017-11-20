package com.adheli.spring5webapp.bootstrap;

import com.adheli.spring5webapp.model.Author;
import com.adheli.spring5webapp.model.Book;
import com.adheli.spring5webapp.model.Publisher;
import com.adheli.spring5webapp.repository.AuthorRepository;
import com.adheli.spring5webapp.repository.BookRepository;
import com.adheli.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.initData();
    }

    private void initData() {
        Publisher publisher = new Publisher();
        publisher.setName("Planeta");

        this.publisherRepository.save(publisher);

        Author hugh = new Author("Hugh", "Laurie");
        Book gunSeller = new Book("The Gun Seller", "978-85-422-0350-9", publisher);
        hugh.getBooks().add(gunSeller);
        gunSeller.getAuthors().add(hugh);

        this.authorRepository.save(hugh);
        this.bookRepository.save(gunSeller);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Record");

        this.publisherRepository.save(publisher2);

        Author lauren = new Author("Lauren", "Weisberger");
        Book dwp = new Book("The Devil Wears Prada", "978-85-01-06803-9", publisher2);
        lauren.getBooks().add(dwp);
        dwp.getAuthors().add(lauren);

        this.authorRepository.save(lauren);
        this.bookRepository.save(dwp);
    }
}
