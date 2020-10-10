package com.renj.service.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   14:53
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class PersonBean implements Parcelable {
    public String userName;
    public int age;
    public int sex;

    public PersonBean() {
    }

    public PersonBean(String userName, int age, int sex) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeInt(this.age);
        dest.writeInt(this.sex);
    }

    public void readFromParcel(Parcel in) {
        this.userName = in.readString();
        this.age = in.readInt();
        this.sex = in.readInt();
    }

    protected PersonBean(Parcel in) {
        this.userName = in.readString();
        this.age = in.readInt();
        this.sex = in.readInt();
    }

    public static final Parcelable.Creator<PersonBean> CREATOR = new Parcelable.Creator<PersonBean>() {
        @Override
        public PersonBean createFromParcel(Parcel source) {
            return new PersonBean(source);
        }

        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };
}
