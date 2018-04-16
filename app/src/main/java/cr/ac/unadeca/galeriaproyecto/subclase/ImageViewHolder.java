package cr.ac.unadeca.galeriaproyecto.subclase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;

import cr.ac.unadeca.galeriaproyecto.R;

/**
 * Created by pc on 11/4/2018.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView image;
    public ImageViewHolder(View itemView) {
        super(itemView);
        image =  itemView.findViewById(R.id.image);
    }
    public void bindTo() {
        ViewGroup.LayoutParams lp = image.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)lp;
            flexboxLp.setFlexGrow(1.0f);
        }
    }

}
