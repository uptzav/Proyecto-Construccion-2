package com.milifi.myappcovid19.ui.country;

import android.os.Parcel;
import android.os.Parcelable;


public class class_CovidPais implements Parcelable {
    String mCovidPais, mCasosHoy, mMuertes, mMuertesHoy, mRecuperados, mActivos, mCriticos, mBanderas;
    int mCasos;

    public class_CovidPais(String mCovidPais, int mCasos, String mTodayCases, String mDeaths, String mTodayDeaths, String mRecovered, String mActive, String mCritical, String mFlags) {
        this.mCovidPais = mCovidPais;
        this.mCasos = mCasos;
        this.mCasosHoy = mTodayCases;
        this.mMuertes = mDeaths;
        this.mMuertesHoy = mTodayDeaths;
        this.mRecuperados = mRecovered;
        this.mActivos = mActive;
        this.mCriticos = mCritical;
        this.mBanderas = mFlags;
    }

    public String getmCovidCountry() {
        return mCovidPais;
    }

    public int getmCases() {
        return mCasos;
    }

    public String getmTodayCases() {
        return mCasosHoy;
    }

    public String getmDeaths() {
        return mMuertes;
    }

    public String getmTodayDeaths() {
        return mMuertesHoy;
    }

    public String getmRecovered() {
        return mRecuperados;
    }

    public String getmActive() {
        return mActivos;
    }

    public String getmCritical() {
        return mCriticos;
    }

    public String getmFlags() {
        return mBanderas;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCovidPais);
        dest.writeInt(this.mCasos);
        dest.writeString(this.mCasosHoy);
        dest.writeString(this.mMuertes);
        dest.writeString(this.mMuertesHoy);
        dest.writeString(this.mRecuperados);
        dest.writeString(this.mActivos);
        dest.writeString(this.mCriticos);
        dest.writeString(this.mBanderas);
    }

    protected class_CovidPais(Parcel in) {
        this.mCovidPais = in.readString();
        this.mCasos = in.readInt();
        this.mCasosHoy = in.readString();
        this.mMuertes = in.readString();
        this.mMuertesHoy = in.readString();
        this.mRecuperados = in.readString();
        this.mActivos = in.readString();
        this.mCriticos = in.readString();
        this.mBanderas = in.readString();
    }

    public static final Creator<class_CovidPais> CREATOR = new Creator<class_CovidPais>() {
        @Override
        public class_CovidPais createFromParcel(Parcel source) {
            return new class_CovidPais(source);
        }

        @Override
        public class_CovidPais[] newArray(int size) {
            return new class_CovidPais[size];
        }
    };
}
