package org.katsumi.bean;

import lombok.Getter;
import lombok.Setter;
import org.katsumi.dao.ItemDao;
import org.katsumi.entity.Item;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Katsumi
 * @since 15/05/02
 */
@Named
@ViewScoped
public class FailBean implements Serializable
{
    @Inject
    private ItemDao itemDao;

    @Getter
    @Setter
    private int position;

    @Getter
    @Setter
    private long answerTime;

    @Getter
    @Setter
    private Long itemId;

    @Getter
    private Item item;

    @Getter
    @Setter
    @Size(min = 1, message = "正しい英語を入力してね。")
    private String answerEnglish;

    public void initView()
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "ざんねん！", null));
        item = itemDao.findById(itemId);
    }

    public String next()
    {
        if (item.getEnglish().equals(answerEnglish.toLowerCase())) {
            position++;
            if (position < itemDao.findAll().size()) {
                return "question?faces-redirect=true&position=" + position + "&answer-time=" + answerTime;
            }
            return "finish?faces-redirect=true&answer-time=" + answerTime;
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "正しい英語を入力してね。", null));
            return null;
        }
    }
}
