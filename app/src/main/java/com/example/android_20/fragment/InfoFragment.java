package com.example.android_20.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.R;
import com.example.android_20.Utils;


public class InfoFragment extends Fragment {
    TextView tvDiemNvC, tvDiemLsC, tvDiemDlC, tvCauSaiC;
    ArcheryDB archeryDB;
    int WrongVan, WrongSu, WrongDia, TotalVan, TotalSu, TotalDia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);
        //anh xa
        tvDiemNvC = view.findViewById(R.id.tvDiemNv);
        tvDiemLsC = view.findViewById(R.id.tvDiemLs);
        tvDiemDlC = view.findViewById(R.id.tvDiemDl);

        WrongVan= archeryDB.getWrong(1,1);
        WrongSu=archeryDB.getWrong(1,1);
        WrongDia=archeryDB.getWrong(1,1);

        TotalVan = archeryDB.getViewed(1,1);
        TotalDia = archeryDB.getViewed(1,1);
        TotalSu = archeryDB.getViewed(1,1);

        tvDiemNvC.setText(String.valueOf(10*(TotalVan - WrongVan)/TotalVan));
        tvDiemLsC.setText(String.valueOf(10*(TotalSu - WrongSu)/TotalSu));
        tvDiemDlC.setText(String.valueOf( 10*(TotalDia - WrongSu)/TotalDia));

        return view;

    }
}