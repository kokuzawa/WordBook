package org.katsumi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Katsumi
 * @since 15/05/03
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer
{
    @Id
    @GeneratedValue
    private Long answerId;

    private String japanese;

    private String english;

    private boolean answer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date answerDate;

    @OneToOne
    private Item item;

    public Answer(Date answerDate, String japanese, String english, boolean answer)
    {
        this.answerDate = answerDate;
        this.japanese = japanese;
        this.english = english;
        this.answer = answer;
    }
}
