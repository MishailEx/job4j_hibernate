package service;

import model.Author;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book book = Book.of("war and peace");
            Book book1 = Book.of("mumu");
            Book book2 = Book.of("show");
            Author author = Author.of("petr");
            Author author1 = Author.of("vlad");
            book.getAuthors().add(author);
            book.getAuthors().add(author1);
            book1.getAuthors().add(author);
            book1.getAuthors().add(author1);
            book2.getAuthors().add(author);
            book2.getAuthors().add(author1);

            session.persist(book);
            session.persist(book1);
            session.persist(book2);

            Book del = session.get(Book.class, 1);
            session.remove(del);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
