package com.mamudo.exorlab.ui.main.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mamudo.exorlab.R;
import com.mamudo.exorlab.models.Stream;

import java.util.List;

public class HLSAdapter extends RecyclerView.Adapter<HLSItemHolder> {
    public interface OnStreamClick {
        void onStreamClick(Stream stream);
    }

    private final List<Stream> streamList;

    private OnStreamClick onStreamClick;

    public HLSAdapter(List<Stream> streamList) {
        this.streamList = streamList;
    }

    @NonNull
    @Override
    public HLSItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HLSItemHolder(View.inflate(parent.getContext(), R.layout.hls_list_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull HLSItemHolder holder, int position) {
        holder.bind(streamList.get(position));
        holder.setStreamClickListener(onStreamClick);
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }

    public void setOnStreamClick(OnStreamClick onStreamClick) {
        this.onStreamClick = onStreamClick;
    }
}
