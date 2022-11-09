package com.example.ejerciciobbddnavigation.ui.borrar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ejerciciobbddnavigation.R;
import com.example.ejerciciobbddnavigation.databinding.FragmentBorrarBinding;
import com.example.ejerciciobbddnavigation.ui.inicio.InicioViewModel;

public class BorrarFragment extends Fragment {

    private FragmentBorrarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BorrarViewModel borrarViewModel =
                new ViewModelProvider(this).get(BorrarViewModel.class);

        binding = FragmentBorrarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewBorrar;
        borrarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}