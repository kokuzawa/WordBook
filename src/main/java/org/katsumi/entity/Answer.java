package org.katsumi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 回答クラス
 *
 * @author Katsumi
 * @since May 3, 2015
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer
{
    /**
     * 回答ID
     */
    @Id
    @GeneratedValue
    private Long answerId;

    /**
     * 日本語
     */
    private String japanese;

    /**
     * 英語
     */
    private String english;

    /**
     * 正解フラグ
     */
    private boolean answer;

    /**
     * 回答日時
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerDate;

    /**
     * 単語
     */
    @OneToOne
    private Item item;

    /**
     * 回答日時、日本語、英語、正解フラグを指定してインスタンスを生成します。
     *
     * @param answerDate 回答日時
     * @param japanese 日本語
     * @param english 英語
     * @param answer 正解フラグ
     */
    public Answer(Date answerDate, String japanese, String english, boolean answer)
    {
        this.answerDate = answerDate;
        this.japanese = japanese;
        this.english = english;
        this.answer = answer;
    }
}
