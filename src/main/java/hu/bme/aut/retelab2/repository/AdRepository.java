package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad feedback) {
        return em.merge(feedback);
    }

    public List<Ad> findAll(){ return em.createQuery("SELECT a FROM Ad a", Ad.class).getResultList(); }

    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }

    public List<Ad> findByKeyword(String keyword) {
        return em.createQuery("SELECT n FROM Ad n WHERE n.address LIKE ?1", Ad.class).setParameter(1, '%' + keyword + '%').getResultList();
    }

    public List<Ad> findPriceMinMax(int value1,int value2){
        List<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.price >= :value1 AND a.price <= :value2 ",Ad.class).setParameter("value1",value1).setParameter("value2",value2).getResultList();
        return query;
    }

    public List<Ad> findByTag(String tag){
        Query query = em.createQuery("SELECT DISTINCT a FROM Ad a WHERE :tag MEMBER OF a.tags");
        query.setParameter("tag",tag);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(long id){
        Ad ad = findById(id);
        em.remove(ad);
    }
}
