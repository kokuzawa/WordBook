package org.katsumi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Katsumi
 * @since 15/05/02
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "japanese")})
@Getter
@Setter
@NoArgsConstructor
public class Item
{
    @Id
    @GeneratedValue
    private Long itemId;

    private String japanese;

    private String english;

    public Item(String japanese, String english)
    {
        this.japanese = japanese;
        this.english = english;
    }
}
