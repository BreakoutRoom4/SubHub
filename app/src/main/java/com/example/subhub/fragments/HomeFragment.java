package com.example.subhub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.subhub.LSVideo;
import com.example.subhub.LSViewAdapter;
import com.example.subhub.R;
import com.example.subhub.Section;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private List<Section> sectionList = new ArrayList<>(); //home sections
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvHome;
    protected LSViewAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getActivity());

        rvHome = view.findViewById(R.id.rvHomelive);
        LSViewAdapter mainAdapter = new LSViewAdapter(getContext(), sectionList);
        rvHome.setAdapter(mainAdapter);
        rvHome.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));


        swipeContainer = (SwipeRefreshLayout)view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //need to implement refresh / api network call
                swipeContainer.setRefreshing(false);
            }
        });

    }

    private void initData()
    {
        String liveSection = "Live now";
        String liveSoonSection = "Going Live soon";
        String offlineSection = "Offline";
        List<LSVideo> liveSectionItems = new ArrayList<>();
        List<LSVideo> liveSoonSectionItems = new ArrayList<>();
        List<LSVideo> offlineSectionItems = new ArrayList<>();

        liveSectionItems.add(new LSVideo("Chungus amongus", "Big Chungus", "When the chungus is sus", "100000"));
        liveSoonSectionItems.add(new LSVideo("SUS IMPOSTER", "Definitely not the imposter", "AMOG", "50000"));
        offlineSectionItems.add(new LSVideo("Innocent gamer", "Task master", "amogus gamerr", "0"));

        sectionList.add(new Section(liveSection, liveSectionItems));
        sectionList.add(new Section(liveSoonSection, liveSoonSectionItems));
        sectionList.add(new Section(offlineSection, offlineSectionItems));
    }

}