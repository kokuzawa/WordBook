package org.katsumi.dao;

import org.katsumi.entity.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 単語データクセスオブジェクトクラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@ApplicationScoped
@Transactional
public class ItemDao
{
    @PersistenceContext
    private EntityManager em;

    /**
     * 単語IDから単語を取得します。
     *
     * @param itemId 単語ID
     * @return 単語
     */
    public Item findById(Long itemId)
    {
        return em.find(Item.class, itemId);
    }

    /**
     * 永続化されている単語をすべて取得します。
     *
     * @return 単語リスト
     */
    public List<Item> findAll()
    {
        return em.createQuery("SELECT I FROM Item I", Item.class).getResultList();
    }

    /**
     * 日本語から単語を取得します。
     *
     * @param japanese 日本語
     * @return 単語
     */
    public Item findByJapanese(String japanese)
    {
        return em.createQuery("SELECT I FROM Item I WHERE I.japanese = :japanese", Item.class)
                .setParameter("japanese", japanese)
                .getSingleResult();
    }

    /**
     * 単語を永続化します。
     *
     * @param item 単語
     */
    public void persist(Item item)
    {
        em.persist(item);
    }

    /**
     * 単語IDから単語を削除します。
     *
     * @param itemId 単語ID
     */
    public void removeByItemId(Long itemId)
    {
        em.remove(em.find(Item.class, itemId));
    }
}
