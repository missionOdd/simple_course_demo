package com.wteam.ug.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author mission
 * @since 2018-11-08
 */
@TableName("tb_menu_item")
@ApiModel(value="MenuItem对象", description="")
public class MenuItem extends Model<MenuItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_item_id", type = IdType.AUTO)
    private Long menuItemId;

    private String menuItemController;

    private String menuItemIcon;

    private String menuItemName;

    private String menuItemPage;

    private String menuItemUrl;

    private Integer parentId;

    private Integer showStatus;


    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemController() {
        return menuItemController;
    }

    public void setMenuItemController(String menuItemController) {
        this.menuItemController = menuItemController;
    }

    public String getMenuItemIcon() {
        return menuItemIcon;
    }

    public void setMenuItemIcon(String menuItemIcon) {
        this.menuItemIcon = menuItemIcon;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemPage() {
        return menuItemPage;
    }

    public void setMenuItemPage(String menuItemPage) {
        this.menuItemPage = menuItemPage;
    }

    public String getMenuItemUrl() {
        return menuItemUrl;
    }

    public void setMenuItemUrl(String menuItemUrl) {
        this.menuItemUrl = menuItemUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.menuItemId;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
        "menuItemId=" + menuItemId +
        ", menuItemController=" + menuItemController +
        ", menuItemIcon=" + menuItemIcon +
        ", menuItemName=" + menuItemName +
        ", menuItemPage=" + menuItemPage +
        ", menuItemUrl=" + menuItemUrl +
        ", parentId=" + parentId +
        ", showStatus=" + showStatus +
        "}";
    }
}
