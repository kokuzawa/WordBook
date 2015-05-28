package org.katsumi.bean;

import org.katsumi.dao.ItemDao;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * 開始画面クラス
 *
 * @author Katsumi
 * @since May 2, 2015
 */
@Named
@RequestScoped
public class IndexBean
{
    /**
     * 単語データクセスオブジェクト
     */
    @Inject
    private ItemDao itemDao;

    /**
     * テストを開始するボタンがクリックされた場合に呼ばれます。<br/>
     * 単語が登録されていない場合はエラーメッセージを設定して本画面を再表示します。
     * 単語が登録されている場合は質問画面に遷移します。
     *
     * @return outcome
     */
    public String start()
    {
        if (itemDao.findAll().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "テストが登録されていないのでテストはできません。", null));
            return null;
        }
        return "question.xhtml?faces-redirect=true&answer-time=" + new Date().getTime();
    }
}
