package com.milifi.myappcovid19.ui.country;

import android.os.Parcel;
import android.os.Parcelable;
public class CovidCountry implements Parcelable {
    String mCovidPais, mCasosHoy, mMuertes, mMuertesHoy, mRecuperados, mActivos, mCriticos, mBanderas;
    int mCasos;

    public mCovidPais(String mCovidPais, int mCasos, String mCasosHoy, String mMuertes, String mMuertesHoy, String mRecuperados, String mActivos, String mCriticos, String mBanderas) {
        this.mCovidPais = mCovidPais;
        this.mCasos = mCasos;
        this.mCasosHoy = mCasosHoy;
        this.mMuertes = mMuertes;
        this.mMuertesHoy = mMuertesHoy;
        this.mRecuperados = mRecuperados;
        this.mActivos = mActivos;
        this.mCriticos = mCriticos;
        this.mBanderas = mBanderas;
    }

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public int getmCases() {
        return mCases;
    }

    public String getmTodayCases() {
        return mTodayCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmTodayDeaths() {
        return mTodayDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmCritical() {
        return mCritical;
    }

    public String getmFlags() {
        return mFlags;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int banderas) {
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

    protected CovidPais(Parcel in) {
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

    public static final Creator<CovidCountry> CREATOR = new Creator<CovidCountry>() {
        @Override
        public CovidCountry createFromParcel(Parcel source) {
            return new CovidCountry(source);
        }

        @Override
        public CovidCountry[] newArray(int size) {
            return new CovidCountry[size];
        }
    };
}
