package com.example.ejerciciobbddnavigation.ui.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InicioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bienvenidos a la APP de nuestro centro educativo");
    }

    public LiveData<String> getText() {
        return mText;
    }
}