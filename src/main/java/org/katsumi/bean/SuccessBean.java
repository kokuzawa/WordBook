package org.katsumi.bean;

import lombok.Getter;
import lombok.Setter;
import org.katsumi.dao.ItemDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Katsumi
 * @since 15/05/02
 */
@Named
@RequestScoped
public class SuccessBean
{
    @Inject
    private ItemDao itemDao;

    @Getter
    @Setter
    private int position;

    @Getter
    @Setter
    private long answerTime;

    public String next()
    {
        position++;
        if (position < itemDao.findAll().size()) {
            return "question?faces-redirect=true&position=" + position + "&answer-time=" + answerTime;
        }
        return "finish?faces-redirect=true&answer-time=" + answerTime;
    }
}
