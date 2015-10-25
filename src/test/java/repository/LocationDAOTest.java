package repository;

import domain.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.enterprise.inject.Instance;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class LocationDAOTest {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private LocationDAO locationDAO;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("egentrening");
        entityManager = entityManagerFactory.createEntityManager();
        locationDAO = new LocationDAO(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        locationDAO.getEntityManager().close();
    }

    @Test
    public void testPersist() throws Exception {
        Location location = locationDAO.persist(new Location("TestPlace", "404"));
        assertNotNull(location);
        assertNotNull(location.getBuildning());
        assertNotNull(location.getRoom());
        assertTrue(location.getId() > 0);
    }

    @Test
    public void testUpdate() throws Exception {
        Location location = locationDAO.findById(1);
        location.setBuildning("TestPlace");
        location.setRoom("007");
        Location afterUpdateLocation = locationDAO.update(location);

        assertEquals(afterUpdateLocation.getBuildning(), location.getBuildning());
        assertEquals(afterUpdateLocation.getRoom(), location.getRoom());
    }

    @Test
    public void testFindById() throws Exception {
        Location location = locationDAO.findById(1);
        assertNotNull(location);
        assertTrue(location.getId() > 0);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Location> locationList = locationDAO.getAll();
        assertNotNull(locationList);
        assertTrue(locationList.size() > 0);
    }

    @Test
    public void testRemove() throws Exception {
        Location location = locationDAO.findById(1);
        assertTrue(locationDAO.remove(location));
        assertNull(locationDAO.findById(1));
    }

    @Before
    public void begin() {
        locationDAO.getEntityManager().getTransaction().begin();
    }
    @After
    public void commit(){
        locationDAO.getEntityManager().getTransaction().commit();
    }

}