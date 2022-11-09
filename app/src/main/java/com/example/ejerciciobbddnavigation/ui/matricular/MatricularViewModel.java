package com.example.ejerciciobbddnavigation.ui.matricular;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatricularViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MatricularViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Matricula");
    }

    public LiveData<String> getText() {
        return mText;
    }
}