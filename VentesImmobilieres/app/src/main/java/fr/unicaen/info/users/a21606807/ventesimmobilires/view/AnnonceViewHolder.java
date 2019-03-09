package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;

public class AnnonceViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    public ConstraintLayout layout_item;

    public AnnonceViewHolder(View item_view) {
        super(item_view);
        this.title = (TextView) item_view.findViewById(R.id.text_title);
        this.description = (TextView) item_view.findViewById(R.id.text_description);
        this.layout_item = (ConstraintLayout) item_view.findViewById(R.id.layout_item);
    }

    public void bind(Propriete propriete){
        this.title.setText(propriete.getTitre());
        this.description.setText(propriete.getDescription());
    }
}