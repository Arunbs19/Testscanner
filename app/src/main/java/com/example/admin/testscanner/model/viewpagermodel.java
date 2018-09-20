package com.example.admin.testscanner.model;

import android.os.Parcel;
import android.os.Parcelable;

public class viewpagermodel implements Parcelable{
    String id;
    String imgurl;
    String title;
    String subtitle;
    int resource;

    public viewpagermodel(Parcel in) {
        this.id = in.readString();
        this.imgurl = in.readString();
        this.title = in.readString();
        this.subtitle = in.readString();
        this.resource = in.readInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public viewpagermodel(String id, String imgurl, String title, String subtitle, int resource) {
        this.id = id;
        this.imgurl = imgurl;
        this.title = title;
        this.subtitle = subtitle;
        this.resource = resource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(imgurl);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeLong(resource);

    }

    public static final Creator<viewpagermodel> CREATOR = new Creator<viewpagermodel>() {
        @Override
        public viewpagermodel createFromParcel(Parcel in) {
            return new viewpagermodel(in);
        }

        @Override
        public viewpagermodel[] newArray(int size) {
            return new viewpagermodel[size];
        }
    };
}
