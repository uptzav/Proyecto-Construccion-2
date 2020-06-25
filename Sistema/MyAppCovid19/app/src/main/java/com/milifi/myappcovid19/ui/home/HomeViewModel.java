/*
 * @(#)HomeViewModel.java 1.1 25/06/20
 *
 * UPT
 * Construccion de Software II.
 */

/**
 *
 * @author Fiorella Salamanca
 * @version 1.1, 25/06/20
 * @since 1.0
 */

package com.milifi.myappcovid19.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento de Inicio");
    }

    public LiveData<String> getText() {
        return mText;
    }
}