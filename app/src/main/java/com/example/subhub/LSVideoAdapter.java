package com.example.subhub;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class LSVideoAdapter extends RecyclerView.Adapter<LSVideoAdapter.ViewHolder> {

    private Context context;
    private List<LSVideo> livestreams;

    public LSVideoAdapter(Context context, List<LSVideo> items)
    {
        this.context = context;
        livestreams = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_channel_live, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            LSVideo vid = livestreams.get(position);
            holder.bind(vid);
    }

    @Override
    public int getItemCount() {
        return livestreams.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView lsTitle;
        private ImageView ivImage;
        private TextView channelName;
        private TextView lsWatchers;
        private TextView lsDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lsTitle =itemView.findViewById(R.id.lsTitle);
            ivImage = itemView.findViewById(R.id.ivImage);
            channelName = itemView.findViewById(R.id.channelName);
            lsWatchers = itemView.findViewById(R.id.lsWatchers);
            lsDescription = itemView.findViewById(R.id.lsDescription);
        }

        public void bind(LSVideo vid)
        {
            lsTitle.setText(vid.getLivestreamTitle());
            channelName.setText(vid.getChannelName());
            lsWatchers.setText(vid.getCurrentWatchers());
            lsDescription.setText(vid.getLivestreamDescription());
            ivImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background));
            /*Image img = vid.getThumbnail();
            if(img != null)
                Glide.with(context).load(img).into(ivImage);*/
        }
    }
}
