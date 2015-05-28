package org.katsumi.bean;

import lombok.Getter;
import lombok.Setter;
import org.katsumi.dao.ItemDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 成功画面クラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@Named
@RequestScoped
public class SuccessBean
{
    /**
     * 単語データクセスオブジェクト
     */
    @Inject
    private ItemDao itemDao;

    /**
     * 単語のポジション
     */
    @Getter
    @Setter
    private int position;

    /**
     * 回答日時
     */
    @Getter
    @Setter
    private long answerTime;

    /**
     * つぎへボタンがクリックされた場合に呼ばれます。<br/>
     * 次の単語が存在する場合は質問画面に遷移します。
     * 次の単語が存在しない場合は終了画面に遷移します。
     *
     * @return outcome
     */
    public String next()
    {
        position++;
        if (position < itemDao.findAll().size()) {
            return "question?faces-redirect=true&position=" + position + "&answer-time=" + answerTime;
        }
        return "finish?faces-redirect=true&answer-time=" + answerTime;
    }
}
