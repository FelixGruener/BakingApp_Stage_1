package com.gruenerfelix.bakingapp.bakingapp.fragment;

import android.annotation.SuppressLint;
<<<<<<< HEAD
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
=======
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;
=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
<<<<<<< HEAD
import com.google.android.exoplayer2.LoadControl;
=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
<<<<<<< HEAD
import com.google.android.exoplayer2.trackselection.TrackSelector;
=======
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.gruenerfelix.bakingapp.bakingapp.R;
import com.gruenerfelix.bakingapp.bakingapp.model.Step;
<<<<<<< HEAD
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * Created by delaroy on 6/20/17.
 */
public class RecipeStepFragment extends Fragment implements ExoPlayer.EventListener{

    private static final String EXTRA_LIST_INDEX = "extra_list_index";
    private static final String EXTRA_STEP = "extra_step";
    private static final String EXTRA_PREV_ENABLED = "extra_prev_enabled";
    private static final String EXTRA_NEXT_ENABLED = "extra_next_enabled";
    private static final String EXTRA_EXO_PLAYER_POSITION = "extra_exo_player_position";

    @Nullable
    @BindView(R.id.textview_step_count)
    AppCompatTextView stepCount;

    @Nullable
    @BindView(R.id.textview_short_description)
    AppCompatTextView shortDescription;

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView playerView;

    @BindView(R.id.image_no_video)
    AppCompatImageView noVideoImage;

    @Nullable
    @BindView(R.id.textview_long_description)
    AppCompatTextView longDescription;

    @Nullable
    @BindView(R.id.button_prev)
    Button prevButton;

    @Nullable
    @BindView(R.id.button_next)
    Button nextButton;

    private int listIndex;
    private Step step;
    private boolean isPrevEnabled;
    private boolean isNextEnabled;
    private long position;

    private Unbinder unbinder;
    private StepActionListener stepActionListener;
    private TrackSelector trackSelector;
    private SimpleExoPlayer exoPlayer;

    public RecipeStepFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            listIndex = savedInstanceState.getInt(EXTRA_LIST_INDEX);
            step = savedInstanceState.getParcelable(EXTRA_STEP);
            isPrevEnabled = savedInstanceState.getBoolean(EXTRA_PREV_ENABLED);
            isNextEnabled = savedInstanceState.getBoolean(EXTRA_NEXT_ENABLED);
            position = savedInstanceState.getLong(EXTRA_EXO_PLAYER_POSITION);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_LIST_INDEX, listIndex);
        outState.putParcelable(EXTRA_STEP, step);
        outState.putBoolean(EXTRA_PREV_ENABLED, isPrevEnabled);
        outState.putBoolean(EXTRA_NEXT_ENABLED, isNextEnabled);
        outState.putLong(EXTRA_EXO_PLAYER_POSITION,position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            stepActionListener = (StepActionListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " should implements interface StepActionListener.");
=======

public class RecipeStepFragment extends Fragment {

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private static final String TAG = "PlayerActivity";

    private SimpleExoPlayer player;
    private SimpleExoPlayerView playerView;
    private ComponentListener componentListener;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    TextView recipeInstruction;
    String stepDescription;
    String videoUrl;
    Step step;

    public RecipeStepFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_step, container, false);

        Bundle bundle = this.getArguments();
        step = bundle.getParcelable("Steps");
        stepDescription = step.getDescription();

        //recipeInstruction = (TextView) rootView.findViewById(R.id.recipeInstructions);
        //recipeInstruction.setText(stepDescription);

        componentListener = new ComponentListener();
        playerView = (SimpleExoPlayerView) rootView.findViewById(R.id.video_view);

        initializePlayer();


        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            //initializePlayer();

>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
        }
    }

    @Override
<<<<<<< HEAD
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (view.findViewById(R.id.textview_step_count) != null) {
            stepCount.setText(getString(R.string.step_count, listIndex));
            shortDescription.setText(step.getShortDescription());
            longDescription.setText(step.getDescription());
            prevButton.setEnabled(isPrevEnabled);
            nextButton.setEnabled(isNextEnabled);
        }

        if (!TextUtils.isEmpty(step.getVideoURL())) {
            noVideoImage.setVisibility(View.GONE);
            initializePlayer(Uri.parse(step.getVideoURL()));
        }
        else {
            playerView.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(step.getThumbnailURL())) {
                try {
                    Picasso.with(getActivity()).load(Uri.parse(step.getThumbnailURL())).into(noVideoImage);
                } catch (Exception ex) {
                    Picasso.with(getActivity()).load(R.drawable.no_video_available).into(noVideoImage);
                }
            } else {
                Picasso.with(getActivity()).load(R.drawable.no_video_available).into(noVideoImage);
            }
            noVideoImage.setVisibility(View.VISIBLE);
        }

        return view;
=======
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
    }

    @Override
    public void onPause() {
        super.onPause();
<<<<<<< HEAD
        position = exoPlayer != null ? exoPlayer.getCurrentPosition() : 0;
        releasePlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            // Create an instance of the ExoPlayer.
            trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            playerView.setPlayer(exoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            // exoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

            exoPlayer.seekTo(position);
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
            trackSelector = null;
        }
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public void isPrevEnabled(boolean enabled) {
        this.isPrevEnabled = enabled;
    }

    public void isNextEnabled(boolean enabled) {
        this.isNextEnabled = enabled;
    }

    @Optional
    @OnClick(R.id.button_prev)
    public void previousStep() {
        stepActionListener.onPrev();
    }

    @Optional
    @OnClick(R.id.button_next)
    public void nextStep() {
        stepActionListener.onNext();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    public interface StepActionListener {
        void onNext();

        void onPrev();
    }
}

=======
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {

        if (player == null){
            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);

            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(adaptiveTrackSelectionFactory), new DefaultLoadControl());
            player.addListener(componentListener);
            player.setVideoDebugListener(componentListener);
            player.setAudioDebugListener(componentListener);
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);

            Bundle bundle = this.getArguments();
            videoUrl = step.getVideoURL();
            if (videoUrl.isEmpty()){

                Toast.makeText(getContext(), "No Video Link", Toast.LENGTH_LONG).show();
                onStop();

            }else {

                String userAgent = Util.getUserAgent(getContext(), "Recipe Instruction");
                MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoUrl), new DefaultDataSourceFactory(
                        getContext(), userAgent), new DefaultExtractorsFactory(), null, null);

                player.prepare(mediaSource);
                player.setPlayWhenReady(true);
            }
        }


    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.removeListener(componentListener);
            player.setVideoListener(null);
            player.setVideoDebugListener(null);
            player.setAudioDebugListener(null);
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private class ComponentListener implements ExoPlayer.EventListener, VideoRendererEventListener,
            AudioRendererEventListener {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            // Do nothing.
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            // Do nothing.
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            // Do nothing.
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.d(TAG, "changed state to " + stateString + " playWhenReady: " + playWhenReady);
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            // Do nothing.
        }

        @Override
        public void onPositionDiscontinuity() {
            // Do nothing.
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            // Do nothing.
        }

        @Override
        public void onVideoEnabled(DecoderCounters counters) {

        }

        @Override
        public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

        }

        @Override
        public void onVideoInputFormatChanged(Format format) {

        }

        @Override
        public void onDroppedFrames(int count, long elapsedMs) {

        }

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {

        }

        @Override
        public void onRenderedFirstFrame(Surface surface) {

        }

        @Override
        public void onVideoDisabled(DecoderCounters counters) {

        }

        @Override
        public void onAudioEnabled(DecoderCounters counters) {

        }

        @Override
        public void onAudioSessionId(int audioSessionId) {

        }

        @Override
        public void onAudioDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

        }

        @Override
        public void onAudioInputFormatChanged(Format format) {

        }

        @Override
        public void onAudioTrackUnderrun(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {

        }

        @Override
        public void onAudioDisabled(DecoderCounters counters) {

        }
    }

}
>>>>>>> 4761b06e83e82ee85094daacc52f1e627f37e822
