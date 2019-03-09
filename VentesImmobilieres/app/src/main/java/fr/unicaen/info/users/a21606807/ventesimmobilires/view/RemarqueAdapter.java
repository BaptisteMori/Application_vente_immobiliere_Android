package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;

public class RemarqueAdapter extends RecyclerView.Adapter<RemarqueViewHolder> {

    private List<String> list;

    public RemarqueAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RemarqueViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_annonce_list_remarque, viewGroup,false);
        return new RemarqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RemarqueViewHolder remarque_view_holder, final int position) {
        String remarque = list.get(position);
        remarque_view_holder.bind(remarque);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
