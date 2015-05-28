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
 * 回答データアクセスオブジェクトクラス
 *
 * @author Katsumi
 * @since May 3, 2015
 */
@ApplicationScoped
@Transactional
public class AnswerDao
{
    @PersistenceContext
    private EntityManager em;

    /**
     * 回答を永続化します。
     *
     * @param answer 回答
     */
    public void persist(Answer answer)
    {
        em.persist(answer);
    }

    /**
     * 回答日時から回答を取得します。
     *
     * @param answerDate 回答日時
     * @return 回答リスト
     */
    public List<Answer> findByAnswerDate(Date answerDate)
    {
        return em.createQuery("SELECT A FROM Answer A WHERE A.answerDate = :answerDate", Answer.class)
                .setParameter("answerDate", answerDate, TemporalType.TIMESTAMP)
                .getResultList();
    }
}
