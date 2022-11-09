package com.example.ejerciciobbddnavigation.ui.borrar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BorrarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BorrarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}