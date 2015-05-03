package org.katsumi.dao;

import org.katsumi.entity.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Katsumi
 * @since 15/05/02
 */
@ApplicationScoped
@Transactional
public class ItemDao
{
    @PersistenceContext
    private EntityManager em;

    public Item findById(Long itemId)
    {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll()
    {
        return em.createQuery("SELECT I FROM Item I", Item.class).getResultList();
    }

    public Item findByJapanese(String japanese)
    {
        return em.createQuery("SELECT I FROM Item I WHERE I.japanese = :japanese", Item.class)
                .setParameter("japanese", japanese)
                .getSingleResult();
    }

    public void persist(Item item)
    {
        em.persist(item);
    }

    public void removeByItemId(Long itemId)
    {
        em.remove(em.find(Item.class, itemId));
    }
}
