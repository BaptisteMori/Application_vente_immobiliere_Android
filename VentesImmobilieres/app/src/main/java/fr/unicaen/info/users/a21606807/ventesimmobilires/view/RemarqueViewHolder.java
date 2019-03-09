package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;

public class RemarqueViewHolder extends RecyclerView.ViewHolder {

    private TextView remarque;
    private ConstraintLayout layout_item;

    public RemarqueViewHolder(View item_view) {
        super(item_view);
        this.remarque = (TextView) item_view.findViewById(R.id.description);
        this.layout_item = (ConstraintLayout) item_view.findViewById(R.id.layout_item);
    }

    public void bind(String r){
        this.remarque.setText(r);
    }
}
