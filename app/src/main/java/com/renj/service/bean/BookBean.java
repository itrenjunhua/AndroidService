package com.renj.service.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-28   10:02
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BookBean implements Parcelable {
    public String bookName;
    public String bookAuthor;
    public double bookPrice;

    public BookBean() {
    }

    public BookBean(String bookName, String bookAuthor, double bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookName);
        dest.writeString(this.bookAuthor);
        dest.writeDouble(this.bookPrice);
    }

    public void readFromParcel(Parcel in) {
        this.bookName = in.readString();
        this.bookAuthor = in.readString();
        this.bookPrice = in.readDouble();
    }

    protected BookBean(Parcel in) {
        this.bookName = in.readString();
        this.bookAuthor = in.readString();
        this.bookPrice = in.readDouble();
    }

    public static final Parcelable.Creator<BookBean> CREATOR = new Parcelable.Creator<BookBean>() {
        @Override
        public BookBean createFromParcel(Parcel source) {
            return new BookBean(source);
        }

        @Override
        public BookBean[] newArray(int size) {
            return new BookBean[size];
        }
    };
}
