package com.thtf.leanpackage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LiShiChuang on 2018/11/29.
 */
public class Book implements Parcelable {
    private int price;
    private String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Book() {
    }

    protected Book(Parcel in) {
        price = in.readInt();
        name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ",name='" + name +
                "'}";
    }
}
