package com.david0926.travity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.david0926.travity.R;
import com.david0926.travity.databinding.FragmentMain1Binding;
import com.david0926.travity.model.InfoModel;
import com.david0926.travity.util.InfoAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainFragment1 extends Fragment {

    public static MainFragment1 newInstance() {
        return new MainFragment1();
    }

    private Context mContext;
    private FragmentMain1Binding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main1, container, false);

        ArrayList<InfoModel> infos = new ArrayList<>();
        infos.add(new InfoModel("https://dimg.donga.com/wps/NEWS/IMAGE/2019/07/28/96733177.3.jpg", "출국 시 반입 금지 물품 안내"));
        infos.add(new InfoModel("https://lh3.googleusercontent.com/proxy/lWQjd14N9-Jz6JnMKDaFNP3raAlzjWuzpdGhv6bBrxb3PPxSps8oepuL3sX4BwxxAdL4g8geg_ZSIVO29RifGNh6EaHSj1UG-poQ6aWlExmqGSQQ_Gd_aQ", "셀프 체크인으로 편리하게!"));

        binding.infoRecyclerview.setAdapter(new InfoAdapter(infos));
        binding.infoRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return binding.getRoot();
    }
}
