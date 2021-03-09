package com.mamudo.exorlab.ui.main.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mamudo.exorlab.databinding.HlsListItemBinding;
import com.mamudo.exorlab.models.Stream;

public class HLSItemHolder extends RecyclerView.ViewHolder {
    public HLSItemHolder(@NonNull View itemView) {
        super(itemView);
    }

    private HLSAdapter.OnStreamClick onStreamClickListener;

    void bind(final Stream stream) {
        final HlsListItemBinding binding = HlsListItemBinding.bind(itemView);
        binding.setStream(stream);
        binding.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == binding.container) {
                    onStreamClickListener.onStreamClick(stream);
                }
            }
        });
    }

    public void setStreamClickListener(HLSAdapter.OnStreamClick callback) {
        this.onStreamClickListener = callback;
    }
}
