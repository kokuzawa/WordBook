package org.katsumi.bean;

import org.katsumi.dao.ItemDao;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * @author Katsumi
 * @since 15/05/02
 */
@Named
@RequestScoped
public class IndexBean
{
    @Inject
    private ItemDao itemDao;

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
