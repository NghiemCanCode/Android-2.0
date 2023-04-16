package com.example.android_20.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.R;


public class InfoFragment extends Fragment {
    TextView tvDiemNvC, tvDiemLsC, tvDiemDlC, tvCauSaiC, tvMarkC;
    ArcheryDB archeryDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);
        //anh xa
        tvDiemNvC = getActivity().findViewById(R.id.tvDiemNv);
        tvDiemLsC = getActivity().findViewById(R.id.tvDiemLs);
        tvDiemDlC = getActivity().findViewById(R.id.tvDiemDl);
        tvCauSaiC = getActivity().findViewById(R.id.tvCauSai);
        tvMarkC = getActivity().findViewById(R.id.tvMark);
        return view;

    }
}