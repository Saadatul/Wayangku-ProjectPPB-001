package projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001.Adapter.HotelAdapter;
import projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001.model.Hotel;

public class NavJenis extends AppCompatActivity {
    ArrayList<Hotel> mList = new ArrayList<>();
    HotelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_jenis);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new HotelAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        fillData();
    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.wayang);
        String[] arDeskripsi = resources.getStringArray(R.array.wayang_deskripsi);
        TypedArray a = resources.obtainTypedArray(R.array.wayang_gambar);
        Drawable[] arFoto = new Drawable[a.length()];
        for (int i = 0; i < arFoto.length; i++) {
            arFoto[i] = a.getDrawable(i);
        }
        a.recycle();

        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new Hotel(arJudul[i], arDeskripsi[i], arFoto[i]));
        }
        mAdapter.notifyDataSetChanged();
    }
}
