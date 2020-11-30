package com.david0926.travity.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.david0926.travity.R;
import com.david0926.travity.database.DataManagerKt;
import com.david0926.travity.model.TodoModel;
import com.david0926.travity.util.UserCache;

import java.util.ArrayList;
import java.util.HashMap;

public class ChecklistFragment extends Fragment {

    private int nowPosition; // 할일인지 준비물인지

    ArrayList<TodoModel> list;

    RecyclerView recyclerView;
    String fragmentType;

    public ChecklistFragment(int pos) {
        nowPosition = pos;
        fragmentType = nowPosition == 0 ? "Todo" : "Thing";

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2_checklist, container, false);

        recyclerView = view.findViewById(R.id.main2_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DataManagerKt.getDocument("users", UserCache.getUser(getContext()).getEmail(), result -> {
            ArrayList<HashMap<String, Object>> temp = (ArrayList) result.get(fragmentType.equals("Todo") ? "todoModels" : "thingsModels");
            ArrayList<TodoModel> list = new ArrayList<>();
            try {
                for (HashMap<String, Object> item : temp) {
                    list.add(new TodoModel((String) item.get("msg"), (Boolean) item.get("finished")));
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            ChecklistAdapter adapter = new ChecklistAdapter(list, fragmentType, getContext());
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

            return null;
        });

        return view;
    }
}
