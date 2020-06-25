/*
 * @(#)class_CovidPais.java 1.1 25/06/20
 *
 * UPT
 * Construccion de Software II.
 */

/**
 *
 * @author Luis Zavala
 * @version 1.1, 25/06/20
 * @since 1.0
 */

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

    public String getmCovidPais() {
        return mCovidPais;
    }

    public int getmCasos() {
        return mCasos;
    }

    public String getmHoyCasos() {
        return mCasosHoy;
    }

    public String getmMuertes() {
        return mMuertes;
    }

    public String getmHoyMuertes() {
        return mMuertesHoy;
    }

    public String getmRecuperados() {
        return mRecuperados;
    }

    public String getmActivos() {
        return mActivos;
    }

    public String getmCriticos() {
        return mCriticos;
    }

    public String getmBanderas() {
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
