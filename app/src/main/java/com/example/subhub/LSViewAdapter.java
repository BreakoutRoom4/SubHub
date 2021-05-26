package com.example.subhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LSViewAdapter extends RecyclerView.Adapter<LSViewAdapter.ViewHolder> {
    private Context context;
    private List<Section> lsv;


    public LSViewAdapter(Context context, List<Section> lsv) {
        this.context = context;
        this.lsv = lsv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Section section = lsv.get(position);
        String secName = section.getSectionName();
        List<LSVideo> items = section.getSectionItems();
        holder.tvSectionName.setText(secName);
        LSVideoAdapter lsVideoAdapter = new LSVideoAdapter(context, items);
        holder.childRV.setAdapter(lsVideoAdapter);
        holder.childRV.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return lsv.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSectionName;
        private RecyclerView childRV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSectionName = itemView.findViewById(R.id.sectionTitle);
            childRV = itemView.findViewById(R.id.childrv);
        }
    }
}
