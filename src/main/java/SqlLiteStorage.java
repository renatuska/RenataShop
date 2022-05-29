import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;

public class SqlLiteStorage implements UserStorage, StockStorage, ReportStorage {

    private SessionFactory factory;
    public SqlLiteStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Stock getStock(String id) throws Exception {
        Session session = factory.openSession();
        try {
            Stock stock = (Stock)session.createQuery("from Stock where itemid = '" + id + "'").getSingleResult();
            return stock;
        } catch (NoResultException ex) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<Stock> getAllStocks() throws FileNotFoundException, Exception {
        Session session = factory.openSession();
        ArrayList<Stock> stocks = (ArrayList<Stock>)session.createQuery("from Stock").getResultList();
        session.close();
        return stocks;
    }

    @Override
    public void addOrUpdateStock(Stock stock) throws Exception {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(stock);
        tx.commit();
        session.close();
    }

    @Override
    public User getUser(String username) {
        Session session = factory.openSession();
        try {
            User user = (User)session.createQuery("from User where username = '" + username + "'").getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        Session session = factory.openSession();
        ArrayList<User> users = (ArrayList<User>)session.createQuery("from User").getResultList();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    @Override

    public void deleteUser(String username) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(new User(username));
        tx.commit();
        session.close();
    }

    @Override
    public void addDataToReport(Log log) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(log);
        tx.commit();
        session.close();
    }

    @Override
    public ArrayList<Log> getAllData() {
        Session session = factory.openSession();
        ArrayList<Log> logs = (ArrayList<Log>)session.createQuery("from Log").getResultList();
        session.close();
        return logs;
    }
}
