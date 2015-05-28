package org.katsumi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 単語クラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "japanese")})
@Getter
@Setter
@NoArgsConstructor
public class Item
{
    /**
     * 単語ID
     */
    @Id
    @GeneratedValue
    private Long itemId;

    /**
     * 日本語
     */
    private String japanese;

    /**
     * 英語
     */
    private String english;

    /**
     * 日本語と英語を指定してインスタンスを生成します。
     *
     * @param japanese 日本語
     * @param english 英語
     */
    public Item(String japanese, String english)
    {
        this.japanese = japanese;
        this.english = english;
    }
}
