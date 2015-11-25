package repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by hffb on 15/10/15.
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> entityClass;

    @PersistenceContext(unitName = "egentrening")
    private EntityManager entityManager;


    public GenericDAOImpl() {
    }

    public GenericDAOImpl(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    GenericDAOImpl(EntityManager entityManager, Class<T> entityClass){
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public T persist(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public T findById(int id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT E FROM " + entityClass.getSimpleName() + " E").getResultList();
    }

    @Override
    public boolean remove(T entity) {
        if(entityManager.contains(entity)){
            entityManager.remove(entity);
        }else{
            entityManager.remove(entityManager.merge(entity));
        }

        return  !entityManager.contains(entity);
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close(){
        entityManager.close();
    }
}
