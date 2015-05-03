package org.katsumi.dao;

import org.katsumi.entity.Answer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author Katsumi
 * @since 15/05/03
 */
@ApplicationScoped
@Transactional
public class AnswerDao
{
    @PersistenceContext
    private EntityManager em;

    public void persist(Answer answer)
    {
        em.persist(answer);
    }

    public List<Answer> findByAnswerDate(Date answerDate)
    {
        return em.createQuery("SELECT A FROM Answer A WHERE A.answerDate = :answerDate", Answer.class)
                .setParameter("answerDate", answerDate, TemporalType.TIMESTAMP)
                .getResultList();
    }
}
