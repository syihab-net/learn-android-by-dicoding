package com.ahmad.learnrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Hero implements Parcelable {
    private String name;
    private String description;
    private Integer photo;

    public Hero () {}

    protected Hero(Parcel in) {
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            photo = null;
        } else {
            photo = in.readInt();
        }
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        if (photo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(photo);
        }
    }
}
