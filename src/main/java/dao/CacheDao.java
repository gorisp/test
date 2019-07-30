package dao;

import model.Users;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CacheDao {


    @PersistenceContext(unitName = "infinispanTest")
    private EntityManager em;

    public boolean foo() {
        Users user = new Users("Gor", "Ispiryan", 23);
        em.persist(user);
        return true;
    }

    public List<Users> getAllUsers(){
        return  em.createQuery("from Users",Users.class).getResultList();
    }

    public Users getUser(long id){
        return em.find(Users.class,id);
    }
}
