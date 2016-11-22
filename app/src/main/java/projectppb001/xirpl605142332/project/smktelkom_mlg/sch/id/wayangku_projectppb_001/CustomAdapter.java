package projectppb001.xirpl605142332.project.smktelkom_mlg.sch.id.wayangku_projectppb_001;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ACER E5-471 on 22/11/2016.
 */
public class CustomAdapter extends PagerAdapter {
    private int[] images = {R.drawable.pict1, R.drawable.pict2, R.drawable.pict3};
    private Context ctx;
    private LayoutInflater inflater;

    public CustomAdapter (Context ctx){
        this.ctx=ctx;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject (View view, Object object){
        return (view ==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.swipe, container,false);
        ImageView img = (ImageView)view.findViewById(R.id.imageView);
        TextView tv = (TextView)view.findViewById(R.id.textView);
        img.setImageResource(images[position]);
        tv.setText("Image :"+position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.refreshDrawableState();
    }
}
