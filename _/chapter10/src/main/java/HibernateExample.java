import data.City;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateExample {
  public static void main(String[] args) {
    SessionFactory sessionFactory = new AnnotationConfiguration()
      .configure()
      .buildSessionFactory();

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();

    try {
      City london = new City("London", "GB", 8174100);
      session.save(london);
    }
    catch (Exception e) {
      tx.rollback();
    }
    finally {
      tx.commit();
    }
  }
}
