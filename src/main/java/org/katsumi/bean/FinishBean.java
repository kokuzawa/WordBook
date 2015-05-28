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
 * 終了画面クラス
 *
 * @author Katsumi
 * @since May 3, 2015
 */
@Named
@RequestScoped
public class FinishBean
{
    /**
     * 回答データアクセスオブジェクト
     */
    @Inject
    private AnswerDao answerDao;

    /**
     * 回答日時
     */
    @Getter
    @Setter
    private long answerTime;

    /**
     * 回答リスト
     */
    @Getter
    @Setter
    private List<Answer> answers;

    /**
     * 画面表示時に呼ばれます。<br/>
     * 回答リストをデータベースから取得します。
     */
    public void initView()
    {
        answers = answerDao.findByAnswerDate(new Date(answerTime));
    }
}
