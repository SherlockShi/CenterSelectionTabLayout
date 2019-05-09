package com.sherlockshi.widget.centerselectiontablayout;

import com.sherlockshi.widget.BaseItemEntity;

/**
 * Author:      SherlockShi
 * Email:       sherlock_shi@163.com
 * Date:        2019-05-09 20:16
 * Description:
 */
public class BodyEntity implements BaseItemEntity {

    private String id;
    private String title;

    public BodyEntity(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return title: item title
     */
    @Override
    public String getItemTitle() {
        return title;
    }
}
