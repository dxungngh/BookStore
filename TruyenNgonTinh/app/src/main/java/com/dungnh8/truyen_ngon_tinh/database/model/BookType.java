package com.dungnh8.truyen_ngon_tinh.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "book_type")
public class BookType implements Serializable {
    public static class Fields {
        public static final String ID = "id";
        public static final String SERVER_ID = "server_id";
        public static final String VIEW = "view";
        public static final String TITLE = "title";
        public static final String ICON = "icon";
        public static final String API = "api";
        public static final String PARENT_ID = "parent_id";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.SERVER_ID)
    private long serverId;
    @DatabaseField(columnName = Fields.VIEW)
    private String view;
    @DatabaseField(columnName = Fields.TITLE)
    private String title;
    @DatabaseField(columnName = Fields.ICON)
    private String icon;
    @DatabaseField(columnName = Fields.API)
    private String api;
    @DatabaseField(columnName = Fields.PARENT_ID)
    private long parentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("id")
    public long getServerId() {
        return serverId;
    }

    @JsonProperty("id")
    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    @JsonProperty("view")
    public String getView() {
        return view;
    }

    @JsonProperty("view")
    public void setView(String view) {
        this.view = view;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("api")
    public String getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(String api) {
        this.api = api;
    }

    @JsonProperty("parent_id")
    public long getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "BookType{" +
            "id=" + id +
            ", serverId=" + serverId +
            ", view='" + view + '\'' +
            ", title='" + title + '\'' +
            ", icon='" + icon + '\'' +
            ", api='" + api + '\'' +
            ", parentId=" + parentId +
            '}';
    }
}