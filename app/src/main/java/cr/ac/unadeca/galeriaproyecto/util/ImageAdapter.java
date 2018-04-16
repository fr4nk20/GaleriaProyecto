package cr.ac.unadeca.galeriaproyecto.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cr.ac.unadeca.galeriaproyecto.R;
import cr.ac.unadeca.galeriaproyecto.activities.ImagenGrande;
import cr.ac.unadeca.galeriaproyecto.subclase.ImageViewHolder;
import cr.ac.unadeca.galeriaproyecto.subclase.RunImage;

/**
 * Created by pc on 11/4/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final List<RunImage> listRunImage;
    private final LayoutInflater inflater;
    private Context context;

    public ImageAdapter(List<RunImage> listRunImages, Context contexto) {
        this.context = contexto;
        this.inflater = LayoutInflater.from(context);

        this.listRunImage = listRunImages;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.imageholder, parent, false);
        return new ImageViewHolder(view);
    }

    public void animateTo(List<RunImage> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<RunImage> newModels) {
        for (int i = listRunImage.size() - 1; i >= 0; i--) {
            final RunImage model = listRunImage.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<RunImage> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final RunImage model = newModels.get(i);
            if (!listRunImage.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<RunImage> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final RunImage model = newModels.get(toPosition);
            final int fromPosition = listRunImage.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public RunImage removeItem(int position) {
        final RunImage model = listRunImage.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, RunImage model) {
        listRunImage.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final RunImage model = listRunImage.remove(fromPosition);
        listRunImage.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        final RunImage current = listRunImage.get(position);

        if(current.url != null){
            if(current.url.isEmpty()){
                Functions.loadImage(holder.image, context );
            }else {
                Functions.loadImage(current.url, holder.image, context);
            }
        }else {
            Functions.loadImage(holder.image, context);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImagenGrande.class);
                intent.putExtra("name", current.name);
                intent.putExtra("author", current.descripcion);
                intent.putExtra("url",current.url);
                context.startActivity(intent);
            }
        });
        holder.bindTo();
    }
    @Override
    public int getItemCount() {
        return listRunImage.size();
    }



}
