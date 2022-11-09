package com.example.ejerciciobbddnavigation.ui.matricular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ejerciciobbddnavigation.databinding.FragmentMatricularBinding;

public class MatricularFragment extends Fragment {

    private FragmentMatricularBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MatricularViewModel matricularViewModel =
                new ViewModelProvider(this).get(MatricularViewModel.class);

        binding = FragmentMatricularBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        matricularViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}