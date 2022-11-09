package com.example.ejerciciobbddnavigation.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ListarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Lista");
    }

    public LiveData<String> getText() {
        return mText;
    }
}