package com.example.space.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableTest implements Parcelable {
    private String name;
    private int age;
    private boolean stupid;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeByte(this.stupid ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.age = source.readInt();
        this.stupid = source.readByte() != 0;
    }

    public ParcelableTest() {
    }

    protected ParcelableTest(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.stupid = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ParcelableTest> CREATOR = new Parcelable.Creator<ParcelableTest>() {
        @Override
        public ParcelableTest createFromParcel(Parcel source) {
            return new ParcelableTest(source);
        }

        @Override
        public ParcelableTest[] newArray(int size) {
            return new ParcelableTest[size];
        }
    };
}
