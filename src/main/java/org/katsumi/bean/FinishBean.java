package org.katsumi.bean;

import lombok.Getter;
import lombok.Setter;
import org.katsumi.dao.AnswerDao;
import org.katsumi.entity.Answer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * @author Katsumi
 * @since 15/05/03
 */
@Named
@RequestScoped
public class FinishBean
{
    @Inject
    private AnswerDao answerDao;

    @Getter
    @Setter
    private long answerTime;

    @Getter
    @Setter
    private List<Answer> answers;

    public void initView()
    {
        answers = answerDao.findByAnswerDate(new Date(answerTime));
    }
}
