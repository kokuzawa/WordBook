package org.katsumi.bean;

import lombok.Getter;
import lombok.Setter;
import org.katsumi.dao.AnswerDao;
import org.katsumi.dao.ItemDao;
import org.katsumi.entity.Answer;
import org.katsumi.entity.Item;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Katsumi
 * @since 15/05/02
 */
@Named("questionBean")
@ViewScoped
public class QuestionBean implements Serializable
{
    @Inject
    private ItemDao itemDao;

    @Inject
    private AnswerDao answerDao;

    @Getter
    @Setter
    private String japanese;

    @Getter
    private String english;

    @Getter
    @Setter
    @Size(min = 1, message = "答えを入れてね。")
    private String answerEnglish;

    @Getter
    @Setter
    private int position;

    @Getter
    @Setter
    private Long answerTime;

    public void initView()
    {
        final List<Item> items = itemDao.findAll();
        if (position < items.size()) {
            final Item item = items.get(position);
            japanese = item.getJapanese();
            english = item.getEnglish();
        }
    }

    public String check()
    {
        final Item item = itemDao.findByJapanese(japanese);
        if (item.getEnglish().equals(answerEnglish.toLowerCase())) {
            answerDao.persist(new Answer(new Date(answerTime), japanese, answerEnglish, true));
            return "success?faces-redirect=true&position=" + position + "&answer-time=" + answerTime
                    + "&itemId=" + item.getItemId();
        }
        else {
            answerDao.persist(new Answer(new Date(answerTime), japanese, answerEnglish, false));
            return "fail?faces-redirect=true&position=" + position + "&answer-time=" + answerTime
                    + "&itemId=" + item.getItemId();
        }
    }

    public String doNotKnow()
    {
        final Item item = itemDao.findByJapanese(japanese);
        answerDao.persist(new Answer(new Date(answerTime), japanese, "", false));
        return "fail?faces-redirect=true&position=" + position + "&answer-time=" + answerTime
                + "&itemId=" + item.getItemId();
    }

    public String quit()
    {
        return "finish?faces-redirect=true&answer-time=" + answerTime;
    }
}
