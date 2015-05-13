package com.dungnh8.truyen_ngon_tinh.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "book")
public class Book implements Parcelable {
    private static final String TAG = Book.class.getSimpleName();

    public static class Fields {
        public static final String ID = "id";
        public static final String AVATAR = "avatar";
        public static final String NAME = "name";
        public static final String URL = "url";
        public static final String NEW_CHAPTER_TITLE = "new_chapter_title";
        public static final String AUTHOR = "author";
        public static final String TYPE = "type";
        public static final String STATUS = "status";
    }

    @DatabaseField(id = true, canBeNull = false, columnName = Fields.ID)
    private long id;

    @DatabaseField(columnName = Fields.AVATAR)
    private String avatar;

    @DatabaseField(columnName = Fields.NAME)
    private String name;

    @DatabaseField(columnName = Fields.URL)
    private String url;

    @DatabaseField(columnName = Fields.NEW_CHAPTER_TITLE)
    private String newChapterTitle;

    @DatabaseField(columnName = Fields.AUTHOR)
    private String author;

    @DatabaseField(columnName = Fields.TYPE)
    private String type;

    @DatabaseField(columnName = Fields.STATUS)
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNewChapterTitle() {
        return newChapterTitle;
    }

    public void setNewChapterTitle(String newChapterTitle) {
        this.newChapterTitle = newChapterTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", avatar='" + avatar + '\'' +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", newChapterTitle='" + newChapterTitle + '\'' +
            ", author='" + author + '\'' +
            ", type='" + type + '\'' +
            ", status='" + status + '\'' +
            '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
