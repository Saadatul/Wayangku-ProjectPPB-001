package projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class NavVideo extends AppCompatActivity {

    TextView isi;
    TextView judul;
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;
    //Mendefinisikan variabel
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nav_video);

        // Menginisiasi Toolbar dan mensetting sebagai actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()) {
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        Intent a = new Intent(getApplicationContext(), NavHome.class);
                        startActivity(a);
                        return true;
                    case R.id.navigation2:
                        Intent b = new Intent(getApplicationContext(), NavProfil.class);
                        startActivity(b);
                        return true;
                    case R.id.navigation3:
                        Intent c = new Intent(getApplicationContext(), NavJenis.class);
                        startActivity(c);
                        return true;
                    case R.id.navigation4:
                        Intent d = new Intent(getApplicationContext(), NavCerita.class);
                        startActivity(d);
                        return true;
                    case R.id.navigation5:
                        Intent e = new Intent(getApplicationContext(), NavVideo.class);
                        startActivity(e);
                        return true;
                    case R.id.navigation6:
                        Intent f = new Intent(getApplicationContext(), NavAbout.class);
                        startActivity(f);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

