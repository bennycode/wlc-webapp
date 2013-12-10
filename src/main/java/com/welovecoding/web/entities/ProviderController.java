package com.welovecoding.web.entities;

import com.welovecoding.web.entities.util.JsfUtil;
import com.welovecoding.web.entities.util.PaginationHelper;
import com.welovecoding.web.jpa.session.ProviderFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("providerController")
@SessionScoped
public class ProviderController implements Serializable {

  private Provider current;
  private DataModel items = null;
  @EJB
  private com.welovecoding.web.jpa.session.ProviderFacade ejbFacade;
  private PaginationHelper pagination;
  private int selectedItemIndex;

  public ProviderController() {
  }

  public Provider getSelected() {
    if (current == null) {
      current = new Provider();
      selectedItemIndex = -1;
    }
    return current;
  }

  private ProviderFacade getFacade() {
    return ejbFacade;
  }

  public PaginationHelper getPagination() {
    if (pagination == null) {
      pagination = new PaginationHelper(10) {

        @Override
        public int getItemsCount() {
          return getFacade().count();
        }

        @Override
        public DataModel createPageDataModel() {
          return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
        }
      };
    }
    return pagination;
  }

  public String prepareList() {
    recreateModel();
    return "List";
  }

  public String prepareView() {
    current = (Provider) getItems().getRowData();
    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    return "View";
  }

  public String prepareCreate() {
    current = new Provider();
    selectedItemIndex = -1;
    return "Create";
  }

  public String create() {
    try {
      getFacade().create(current);
      JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/AdminPages").getString("ProviderCreated"));
      return prepareCreate();
    } catch (Exception e) {
      JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/AdminPages").getString("PersistenceErrorOccured"));
      return null;
    }
  }

  public String prepareEdit() {
    current = (Provider) getItems().getRowData();
    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    return "Edit";
  }

  public String update() {
    try {
      getFacade().edit(current);
      JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/AdminPages").getString("ProviderUpdated"));
      return "View";
    } catch (Exception e) {
      JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/AdminPages").getString("PersistenceErrorOccured"));
      return null;
    }
  }

  public String destroy() {
    current = (Provider) getItems().getRowData();
    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    performDestroy();
    recreatePagination();
    recreateModel();
    return "List";
  }

  public String destroyAndView() {
    performDestroy();
    recreateModel();
    updateCurrentItem();
    if (selectedItemIndex >= 0) {
      return "View";
    } else {
      // all items were removed - go back to list
      recreateModel();
      return "List";
    }
  }

  private void performDestroy() {
    try {
      getFacade().remove(current);
      JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/AdminPages").getString("ProviderDeleted"));
    } catch (Exception e) {
      JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/AdminPages").getString("PersistenceErrorOccured"));
    }
  }

  private void updateCurrentItem() {
    int count = getFacade().count();
    if (selectedItemIndex >= count) {
      // selected index cannot be bigger than number of items:
      selectedItemIndex = count - 1;
      // go to previous page if last page disappeared:
      if (pagination.getPageFirstItem() >= count) {
        pagination.previousPage();
      }
    }
    if (selectedItemIndex >= 0) {
      current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
    }
  }

  public DataModel getItems() {
    if (items == null) {
      items = getPagination().createPageDataModel();
    }
    return items;
  }

  private void recreateModel() {
    items = null;
  }

  private void recreatePagination() {
    pagination = null;
  }

  public String next() {
    getPagination().nextPage();
    recreateModel();
    return "List";
  }

  public String previous() {
    getPagination().previousPage();
    recreateModel();
    return "List";
  }

  public SelectItem[] getItemsAvailableSelectMany() {
    return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
  }

  public SelectItem[] getItemsAvailableSelectOne() {
    return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
  }

  public Provider getProvider(java.lang.Integer id) {
    return ejbFacade.find(id);
  }

  @FacesConverter(forClass = Provider.class)
  public static class ProviderControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      ProviderController controller = (ProviderController) facesContext.getApplication().getELResolver().
              getValue(facesContext.getELContext(), null, "providerController");
      return controller.getProvider(getKey(value));
    }

    java.lang.Integer getKey(String value) {
      java.lang.Integer key;
      key = Integer.valueOf(value);
      return key;
    }

    String getStringKey(java.lang.Integer value) {
      StringBuilder sb = new StringBuilder();
      sb.append(value);
      return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
      if (object == null) {
        return null;
      }
      if (object instanceof Provider) {
        Provider o = (Provider) object;
        return getStringKey(o.getId());
      } else {
        throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Provider.class.getName());
      }
    }

  }

}
