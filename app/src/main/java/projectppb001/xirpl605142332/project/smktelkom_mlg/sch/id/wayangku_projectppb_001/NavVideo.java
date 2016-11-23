package projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class NavVideo extends AppCompatActivity {

    TextView isi;
    TextView judul;
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nav_video);

        videoView = (VideoView) findViewById(R.id.videoView);

        isi = (TextView) findViewById(R.id.textView2);
        judul = (TextView) findViewById(R.id.textView3);
// Set the media controller buttons
        if (mediaController == null) {
            mediaController = new MediaController(NavVideo.this);
// Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(videoView);
// Set MediaController for VideoView
            videoView.setMediaController(mediaController);
        }
        try {
// ID of video file.
            int id = this.getRawResIdByName("animasi");
            videoView.setVideoURI(Uri.parse("android.resource://" +
                    getPackageName() + "/" + id));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
// When the video file ready for playback.
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }
// When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new
                                                                  MediaPlayer.OnVideoSizeChangedListener() {
                                                                      @Override
                                                                      public void onVideoSizeChanged(MediaPlayer mp, int
                                                                              width, int height) {
// Re-Set the videoView that acts as the anchor for the MediaController
                                                                          mediaController.setAnchorView(videoView);
                                                                      }
                                                                  });
            }
        });

    }

    // Find ID corresponding to the name of the resource (in the directory raw).
    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();
// Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName, "raw",
                pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " +
                resID);
        return resID;
    }

    // When you change direction of phone, this method will be called.
// It store the state of video (Current position)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
// Store current position.
        savedInstanceState.putInt("CurrentPosition",
                videoView.getCurrentPosition());
        videoView.pause();
    }

    // After rotating the phone. This method is called.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
// Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
    }

}
