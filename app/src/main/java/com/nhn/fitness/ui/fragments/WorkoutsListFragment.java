package com.nhn.fitness.ui.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.nhn.fitness.R;
import com.nhn.fitness.data.model.GroupSectionModel;
import com.nhn.fitness.data.model.GroupViewModel;
import com.nhn.fitness.data.model.MessageEvent;
import com.nhn.fitness.ui.adapters.GroupAdapter;
import com.nhn.fitness.ui.adapters.decoration.GroupItemDecoration;
import com.nhn.fitness.ui.adapters.decoration.PreCachingLayoutManager;
import com.nhn.fitness.ui.base.BaseFragment;
import com.nhn.fitness.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;


public class WorkoutsListFragment extends BaseFragment {

    private ArrayList<GroupViewModel> data;
    private RecyclerView recyclerView;
    private GroupAdapter adapter;


    public WorkoutsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getKey().equals(MessageEvent.CHANGE_GENDER)) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workouts_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_list);
        initViews();
        initObservers();
        initEvents();
        initData();

    }

    private void initData() {
   //     com.nhn.fitness.data.add(new GroupViewModel(GroupViewModel.TYPE_LAYOUT, R.layout.guide_add_favorite_layout));
        data.add(
                new GroupViewModel(
                        GroupViewModel.TYPE_GROUP_SECTION,
                        new GroupSectionModel(
                                0,
                                getString(R.string.arm_workout),
                                new ArrayList<String>() {{
                                    add("7");
                                }}
                        )
                )
        );
//        com.nhn.fitness.data.add(
//                new GroupViewModel(
//                        GroupViewModel.TYPE_FRAGMENT,
//                        new AdsFragment()
//                )
//        );
        data.add(
                new GroupViewModel(
                        GroupViewModel.TYPE_GROUP_SECTION,
                        new GroupSectionModel(
                                0,
                                getString(R.string.butt_workout),
                                new ArrayList<String>() {{
                                    add("4");
                                    add("5");
                                    add("6");

                                }}
                        )
                )
        );

        data.add(
                new GroupViewModel(
                        GroupViewModel.TYPE_GROUP_SECTION,
                        new GroupSectionModel(
                                0,
                                getString(R.string.abs_workout),
                                new ArrayList<String>() {{
                                    add("1");
                                    add("2");
                                    add("3");

                                }}
                        )
                )
        );


        data.add(new GroupViewModel(
                GroupViewModel.TYPE_GROUP_SECTION,
                new GroupSectionModel(0, getString(R.string.thigh_workout), Arrays.asList("8", "9", "10"))
        ));

        Log.d("Log_ListData", "data: " + data.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initViews() {
        super.initViews();
        data = new ArrayList<>();
        recyclerView.setLayoutManager(new PreCachingLayoutManager(getContext(), ViewUtils.getHeightDevicePixel(getActivity()) * 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(5);
        recyclerView.addItemDecoration(new GroupItemDecoration(getContext()));
        adapter = new GroupAdapter(getChildFragmentManager(), data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter.destroy();
    }
}
