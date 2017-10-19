package giantbing.zonlinks.com.basemvcdev.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by P on 2017/10/16.
 */

public class UserBean implements Parcelable {
   private boolean isgood;
    private String name ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isgood ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.isgood = in.readByte() != 0;
        this.name = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
