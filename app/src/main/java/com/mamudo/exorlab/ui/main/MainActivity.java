package com.mamudo.exorlab.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.mamudo.exorlab.MainApplication;
import com.mamudo.exorlab.R;
import com.mamudo.exorlab.databinding.ActivityMainBinding;
import com.mamudo.exorlab.models.Stream;
import com.mamudo.exorlab.services.ExampleStreamProvider;
import com.mamudo.exorlab.ui.main.adapter.HLSAdapter;
import com.mamudo.exorlab.ui.stream.VideoStreamActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    ExampleStreamProvider exampleStreamProvider;
    HLSAdapter adapter;
    ActivityMainBinding binding;

    private final HLSAdapter.OnStreamClick streamClickListener = new HLSAdapter.OnStreamClick() {
        @Override
        public void onStreamClick(Stream stream) {
            openStreamActivityFor(stream);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((MainApplication) getApplicationContext()).appGraph.inject(this);
        initiate();
    }

    void openStreamActivityFor(Stream stream) {
        Intent intent = new Intent(getApplicationContext(), VideoStreamActivity.class);
        intent.putExtra(VideoStreamActivity.STREAM_URL, stream.getUri().toString());
        startActivity(intent);
    }

    void initiate() {
        initiateRecyclerView();
    }

    private void initiateRecyclerView() {
        adapter = new HLSAdapter(exampleStreamProvider.load());
        adapter.setOnStreamClick(streamClickListener);
        binding.recyclerView.setAdapter(adapter);
    }
}