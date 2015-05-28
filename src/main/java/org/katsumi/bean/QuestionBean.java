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
 * 質問画面クラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@Named
@ViewScoped
public class QuestionBean implements Serializable
{
    /**
     * 単語データクセスオブジェクト
     */
    @Inject
    private ItemDao itemDao;

    /**
     * 回答データアクセスオブジェクト
     */
    @Inject
    private AnswerDao answerDao;

    /**
     * 日本語テキストフィールド
     */
    @Getter
    private String japanese;

    /**
     * Google翻訳の音声用英語
     */
    @Getter
    private String english;

    /**
     * 英語テキストフィールド
     */
    @Getter
    @Setter
    @Size(min = 1, message = "答えを入れてね。")
    private String answerEnglish;

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
    private Long answerTime;

    /**
     * 画面表示時に呼ばれます。<br/>
     * 単語のポジションが単語リストの末端に達していない場合は新しい単語を表示します。
     */
    public void initView()
    {
        final List<Item> items = itemDao.findAll();
        if (position < items.size()) {
            final Item item = items.get(position);
            japanese = item.getJapanese();
            english = item.getEnglish();
        }
    }

    /**
     * 確認ボタンがクリックされた場合に呼ばれて回答を検証します。<br/>
     * 入力された英語が登録している英単語と一致する場合は成功画面に遷移します。
     * 入力された英語が登録している英単語を一致しない場合は失敗画面に遷移します。
     *
     * @return outcome
     */
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

    /**
     * わからないボタンがクリックされた場合に呼ばれます。<br/>
     * 失敗画面に遷移します。
     *
     * @return outcome
     */
    public String doNotKnow()
    {
        final Item item = itemDao.findByJapanese(japanese);
        answerDao.persist(new Answer(new Date(answerTime), japanese, "", false));
        return "fail?faces-redirect=true&position=" + position + "&answer-time=" + answerTime
                + "&itemId=" + item.getItemId();
    }

    /**
     * やめるボタンがクリックされた場合に呼ばれます。<br/>
     * 終了画面に遷移します。
     *
     * @return outcome
     */
    public String quit()
    {
        return "finish?faces-redirect=true&answer-time=" + answerTime;
    }
}
