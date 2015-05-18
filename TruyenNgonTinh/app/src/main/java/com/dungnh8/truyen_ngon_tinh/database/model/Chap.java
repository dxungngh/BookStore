package com.dungnh8.truyen_ngon_tinh.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "chap")
public class Chap implements Serializable {
    public static class Fields {
        public static final String ID = "id";
        public static final String SERVER_ID = "server_id";
        public static final String STORY_ID = "story_id";
        public static final String TITLE = "title";
        public static final String API = "api";
        public static final String CONTENT = "content";
        public static final String NEXT_CHAP = "next_chap";
        public static final String NEXT_API = "next_api";
        public static final String PREV_CHAP = "prev_chap";
        public static final String PREV_API = "prev_api";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;
    @DatabaseField(columnName = Fields.SERVER_ID)
    private long serverId;
    @DatabaseField(columnName = Fields.STORY_ID)
    private long storyId;
    @DatabaseField(columnName = Fields.TITLE)
    private String title;
    @DatabaseField(columnName = Fields.API)
    private String api;
    @DatabaseField(columnName = Fields.CONTENT)
    private String content;
    @DatabaseField(columnName = Fields.NEXT_CHAP)
    private long nextChap;
    @DatabaseField(columnName = Fields.NEXT_API)
    private String nextApi;
    @DatabaseField(columnName = Fields.PREV_CHAP)
    private long prevChap;
    @DatabaseField(columnName = Fields.PREV_API)
    private String prevApi;

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

    @JsonProperty("story_id")
    public long getStoryId() {
        return storyId;
    }

    @JsonProperty("storyId")
    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("api")
    public String getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(String api) {
        this.api = api;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    public long getNextChap() {
        return nextChap;
    }

    public void setNextChap(long nextChap) {
        this.nextChap = nextChap;
    }

    public long getPrevChap() {
        return prevChap;
    }

    public void setPrevChap(long prevChap) {
        this.prevChap = prevChap;
    }

    public String getNextApi() {
        return nextApi;
    }

    public void setNextApi(String nextApi) {
        this.nextApi = nextApi;
    }

    public String getPrevApi() {
        return prevApi;
    }

    public void setPrevApi(String prevApi) {
        this.prevApi = prevApi;
    }

    @Override
    public String toString() {
        return "Chap{" +
            "id=" + id +
            ", serverId=" + serverId +
            ", storyId=" + storyId +
            ", title='" + title + '\'' +
            ", api='" + api + '\'' +
            ", content='" + content + '\'' +
            ", nextChap=" + nextChap +
            ", nextApi='" + nextApi + '\'' +
            ", prevChap=" + prevChap +
            ", prevApi='" + prevApi + '\'' +
            '}';
    }
}