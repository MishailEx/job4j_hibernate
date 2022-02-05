package service;

import model.CarMark;
import model.CarModel;
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

            session.save(new CarModel("rav4"));
            session.save(new CarModel("chr"));
            session.save(new CarModel("tundra"));
            session.save(new CarModel("prius"));
            session.save(new CarModel("camry"));

            CarMark mark = new CarMark("toyota");
            mark.addModel(session.load(CarModel.class, 1));
            mark.addModel(session.load(CarModel.class, 2));
            mark.addModel(session.load(CarModel.class, 3));
            mark.addModel(session.load(CarModel.class, 4));
            mark.addModel(session.load(CarModel.class, 5));

            session.save(mark);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
