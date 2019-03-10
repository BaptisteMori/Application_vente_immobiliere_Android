package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;

public class AnnonceViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView title;
    private TextView description;
    private TextView ville;
    public ConstraintLayout layout_item;

    public AnnonceViewHolder(View item_view) {
        super(item_view);
        this.image = (ImageView) item_view.findViewById(R.id.icon_list_annonce);
        this.title = (TextView) item_view.findViewById(R.id.text_title);
        this.description = (TextView) item_view.findViewById(R.id.text_description);
        this.ville = (TextView) item_view.findViewById(R.id.text_ville);
        this.layout_item = (ConstraintLayout) item_view.findViewById(R.id.layout_item);
    }

    public void bind(Propriete propriete){
        if (propriete.getImages().size() > 0) {
            Picasso.get().load(
                    propriete.getImages().get(0)
            ).into(this.image);
        }
        this.title.setText(propriete.getTitre());
        this.description.setText(propriete.getDescription());
        this.ville.setText(propriete.getVille());
    }
}