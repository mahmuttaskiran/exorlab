package com.mamudo.exorlab.services;

import com.mamudo.exorlab.models.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/*
    Fake stream provider. [load] method returns some example HLS file
    that got from https://ottverse.com/free-hls-m3u8-test-urls/
 */
@Singleton
public class ExampleStreamProvider implements StreamProvider {

    @Inject
    public ExampleStreamProvider() {
        // to make it injectable
    }

    private final ArrayList<Stream> storage = new ArrayList<>();

    {
        // This contents got from https://ottverse.com/free-hls-m3u8-test-urls/
        storage.add(new Stream("Tears of Steel m3u8", "", URI.create("http://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.m3u8")));
        storage.add(new Stream("Big Buck Bunny VOD m3u8", "", URI.create("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8")));
        storage.add(new Stream("Sintel VOD m3u8", "", URI.create("https://multiplatform-f.akamaihd.net/i/multi/april11/sintel/sintel-hd_,512x288_450_b,640x360_700_b,768x432_1000_b,1024x576_1400_m,.mp4.csmil/master.m3u8")));
        storage.add(new Stream("Sample from Apple", "", URI.create("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8")));
        storage.add(new Stream("fMP4 m3u8", "", URI.create("https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8")));
    }

    @Override
    public List<Stream> load() {
        return storage;
    }
}
