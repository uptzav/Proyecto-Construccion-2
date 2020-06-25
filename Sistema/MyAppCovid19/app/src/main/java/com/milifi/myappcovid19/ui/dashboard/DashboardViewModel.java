package com.milifi.myappcovid19.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mTexto;

    public DashboardViewModel() {
        mTexto = new MutableLiveData<>();
        mTexto.setValue("Esto es un fragmento dashboard");
    }

    public LiveData<String> getText() {
        return mTexto;
    }
}