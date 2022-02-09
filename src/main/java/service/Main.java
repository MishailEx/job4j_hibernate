package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Candidate");
            for (Object o: query.list()) {
                System.out.println(o);
            }

            Query query2 = session.createQuery("from Candidate c where c.id = :id")
                    .setParameter("id", 1);
            System.out.println(query2.uniqueResult());

            Query query3 = session.createQuery("from Candidate c where c.name = :name")
                    .setParameter("name", "ваня");
            System.out.println(query3.uniqueResult());

            session.createQuery("update Candidate c set c.name = :name, c.salary = :salary where c.id = :id")
                    .setParameter("name", "слава")
                    .setParameter("salary", "2000")
                    .setParameter("id", 2)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 3)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
