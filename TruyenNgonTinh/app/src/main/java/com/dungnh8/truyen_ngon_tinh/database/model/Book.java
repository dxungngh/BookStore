package com.dungnh8.truyen_ngon_tinh.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

@DatabaseTable(tableName = "book")
public class Book implements Serializable {
    private static final String TAG = Book.class.getSimpleName();

    public static class Fields {
        public static final String ID = "id";
        public static final String SERVER_ID = "server_id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String API = "api";
        public static final String THUMBNAIL = "thumbnail";
        public static final String VIEW = "view";
        public static final String AUTHOR = "author";
        public static final String CAT_NAME = "cat_name";
        public static final String STATUS = "status";
        public static final String TOTAL_OF_CHAPS = "total_of_chaps";
        public static final String TOTAL = "total";
        public static final String PAGES = "pages";
        public static final String CURRENT_PAGE = "current_page";
        public static final String NEXT_PAGE = "next_page";
        public static final String PREV_PAGE = "prev_page";
        public static final String CHAPS = "chaps";
        public static final String CURRENT_CHAP = "current_chap";
        public static final String IS_READ = "is_read";
        public static final String IS_FAVORITE = "is_favorite";
        public static final String UPDATED_AT = "updated_at";
    }

    @DatabaseField(allowGeneratedIdInsert = true, canBeNull = false, columnName = Fields.ID,
        generatedId = true)
    private long id;

    @DatabaseField(columnName = Fields.SERVER_ID)
    private long serverId;

    @DatabaseField(columnName = Fields.TITLE)
    private String title;

    @DatabaseField(columnName = Fields.DESCRIPTION)
    private String description;

    @DatabaseField(columnName = Fields.API)
    private String api;

    @DatabaseField(columnName = Fields.THUMBNAIL)
    private String thumbnail;

    @DatabaseField(columnName = Fields.VIEW)
    private String view;

    @DatabaseField(columnName = Fields.AUTHOR)
    private String author;

    @DatabaseField(columnName = Fields.CAT_NAME)
    private String catName;

    @DatabaseField(columnName = Fields.STATUS)
    private String status;

    @DatabaseField(columnName = Fields.TOTAL_OF_CHAPS)
    private String totalOfChaps;

    @DatabaseField(columnName = Fields.TOTAL)
    private int total;

    @DatabaseField(columnName = Fields.PAGES)
    private int pages;

    @DatabaseField(columnName = Fields.CURRENT_PAGE)
    private int currentPage;

    @DatabaseField(columnName = Fields.NEXT_PAGE)
    private int nextPage;

    @DatabaseField(columnName = Fields.PREV_PAGE)
    private int prevPage;

    private List<Chap> chaps;

    @DatabaseField(columnName = Fields.CURRENT_CHAP)
    private int currentChap;

    @DatabaseField(columnName = Fields.IS_READ)
    private boolean isRead = false;

    @DatabaseField(columnName = Fields.IS_FAVORITE)
    private boolean isFavorite = false;

    @DatabaseField(columnName = Fields.UPDATED_AT)
    private long updatedAt;

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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("api")
    public String getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(String api) {
        this.api = api;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("view")
    public String getView() {
        return view;
    }

    @JsonProperty("view")
    public void setView(String view) {
        this.view = view;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("cat_name")
    public String getCatName() {
        return catName;
    }

    @JsonProperty("cat_name")
    public void setCatName(String catName) {
        this.catName = catName;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("total_of_chaps")
    public String getTotalOfChaps() {
        return totalOfChaps;
    }

    @JsonProperty("total_of_chaps")
    public void setTotalOfChaps(String totalOfChaps) {
        this.totalOfChaps = totalOfChaps;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("pages")
    public int getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(int pages) {
        this.pages = pages;
    }

    @JsonProperty("current_page")
    public int getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("current_page")
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @JsonProperty("next_page")
    public int getNextPage() {
        return nextPage;
    }

    @JsonProperty("next_page")
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("prev_page")
    public int getPrevPage() {
        return prevPage;
    }

    @JsonProperty("prev_page")
    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public List<Chap> getChaps() {
        return chaps;
    }

    public void setChaps(List<Chap> chaps) {
        this.chaps = chaps;
    }

    public int getCurrentChap() {
        return currentChap;
    }

    public void setCurrentChap(int currentChap) {
        this.currentChap = currentChap;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", serverId=" + serverId +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", api='" + api + '\'' +
            ", thumbnail='" + thumbnail + '\'' +
            ", view='" + view + '\'' +
            ", author='" + author + '\'' +
            ", catName='" + catName + '\'' +
            ", status='" + status + '\'' +
            ", totalOfChaps='" + totalOfChaps + '\'' +
            ", total=" + total +
            ", pages=" + pages +
            ", currentPage=" + currentPage +
            ", nextPage=" + nextPage +
            ", prevPage=" + prevPage +
            ", isRead=" + isRead +
            ", isFavorite=" + isFavorite +
            ", updatedAt=" + updatedAt +
            '}';
    }
}