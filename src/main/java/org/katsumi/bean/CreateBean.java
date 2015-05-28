package org.katsumi.bean;

import lombok.Getter;
import lombok.Setter;
import org.katsumi.dao.ItemDao;
import org.katsumi.entity.Item;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 単語登録画面クラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@Named
@ViewScoped
public class CreateBean implements Serializable
{
    /**
     * 単語データアクセスオブジェクト
     */
    @Inject
    private ItemDao itemDao;

    /**
     * 単語リスト
     */
    @Getter
    @Setter
    private List<Item> items;

    /**
     * 日本語テキストフィールドの値
     */
    @Getter
    @Setter
    @Size(min = 1, message = "入力してください。")
    private String japanese;

    /**
     * 英語テキストフィールドの値
     */
    @Getter
    @Setter
    @Size(min = 1, message = "入力してください。")
    private String english;

    /**
     * 画面表示時に呼ばれます。<br/>
     * 単語リストをデータベースから取得します。
     */
    public void initView()
    {
        items = itemDao.findAll();
    }

    /**
     * 登録ボタンがクリックされた場合に呼ばれます。<br/>
     * 入力された単語を登録します。
     * 登録後に入力された情報をクリアします。
     */
    public void persist()
    {
        itemDao.persist(new Item(japanese, english.toLowerCase()));
        items = itemDao.findAll();
        japanese = null;
        english = null;
    }

    /**
     * 削除ボタンがクリックされた場合に呼ばれます。<br/>
     * 指定された単語を削除します。
     *
     * @param itemId 単語ID
     */
    public void remove(Long itemId)
    {
        itemDao.removeByItemId(itemId);
        items = itemDao.findAll();
    }
}
