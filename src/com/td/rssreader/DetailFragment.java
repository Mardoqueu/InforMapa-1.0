package com.td.rssreader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

import br.com.informapa.R;
import com.td.rssreader.parser.RSSFeed;

public class DetailFragment extends Fragment {
    private int fPos;
    RSSFeed fFeed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fFeed = (RSSFeed) getArguments().getSerializable("feed");
        fPos = getArguments().getInt("pos");
    }

    @SuppressWarnings("deprecation")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.detail_fragment, container, false);

        // Initializr views
        TextView title = (TextView) view.findViewById(R.id.title);
        WebView desc = (WebView) view.findViewById(R.id.desc);

        // Enable the vertical fading edge (by default it is disabled)
        ScrollView sv = (ScrollView) view.findViewById(R.id.sv);
        sv.setVerticalFadingEdgeEnabled(true);

        // Set webview properties
        WebSettings ws = desc.getSettings();
        ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        ws.setLightTouchEnabled(false);
        ws.setPluginState(PluginState.ON);
        ws.setJavaScriptEnabled(true);

        // Set the views
        title.setText(fFeed.getItem(fPos).getTitle());
        desc.loadDataWithBaseURL("http://www.androidcentral.com/", fFeed
                .getItem(fPos).getDescription(), "text/html", "UTF-8", null);

        return view;
    }
}
