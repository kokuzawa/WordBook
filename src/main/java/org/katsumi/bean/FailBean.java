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
 * 不正解画面クラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@Named
@ViewScoped
public class FailBean implements Serializable
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
     * 単語ID
     */
    @Getter
    @Setter
    private Long itemId;

    /**
     * 単語
     */
    @Getter
    private Item item;

    /**
     * 正しい英語テキストフィールド
     */
    @Getter
    @Setter
    @Size(min = 1, message = "正しい英語を入力してね。")
    private String answerEnglish;

    /**
     * 画面表示時に呼ばれます。<br/>
     * 指定された単語IDから単語を取得します。
     * 単語は画面表示時にしか生成しませんが、ViewScopedなので別画面に切り替わるまで保持されます。
     */
    public void initView()
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "ざんねん！", null));
        item = itemDao.findById(itemId);
    }

    /**
     * 次へボタンがクリックされた場合に呼ばれます。<br/>
     * 入力された正しい英語と単語として登録されている英語が一致する場合は次の問題を表示します。
     * 一致しない場合はエラーメッセージを設定して本画面を再表示します。
     *
     * @return outcome
     */
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
