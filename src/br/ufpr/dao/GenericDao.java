package br.ufpr.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufpr.util.HibernateFactory;

public abstract class GenericDao {

	private Session session;
    private Transaction tx;

    public void saveOrUpdate(Object obj) {
        try {
            startOperation();
            session.saveOrUpdate(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    public void delete(Object obj) {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Object find(Class clazz, Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return obj;
    }

    @SuppressWarnings("rawtypes")
	public List findAll(Class clazz) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }

    protected void handleException(HibernateException e) throws HibernateException {
        HibernateFactory.rollback(tx);
        throw e;
    }

    protected void startOperation() throws HibernateException {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }
    
    protected Session getSession() {
		return HibernateFactory.openSession();
	}

}