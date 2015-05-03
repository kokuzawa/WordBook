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
 * @author Katsumi
 * @since 15/05/02
 */
@Named
@ViewScoped
public class CreateBean implements Serializable
{
    @Inject
    private ItemDao itemDao;

    @Getter
    @Setter
    private List<Item> items;

    @Getter
    @Setter
    @Size(min = 1, message = "入力してください。")
    private String japanese;

    @Getter
    @Setter
    @Size(min = 1, message = "入力してください。")
    private String english;

    public void initView()
    {
        items = itemDao.findAll();
    }

    public void persist()
    {
        itemDao.persist(new Item(japanese, english.toLowerCase()));
        items = itemDao.findAll();
        japanese = null;
        english = null;
    }

    public void remove(Long itemId)
    {
        itemDao.removeByItemId(itemId);
        items = itemDao.findAll();
    }
}
