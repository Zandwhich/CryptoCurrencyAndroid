package com.example.phone.activities.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone.R;

final public class CompatibleEndpointAdapter extends RecyclerView.Adapter<CompatibleEndpointAdapter.CompatibleEndpointHolder> {

    // TODO: Goddamn, give this a better name
    public interface ParentActivity {
        String getEndpointName(int orderInList);
    }

    private final ParentActivity activity;
    private int numberOfItems;

    public CompatibleEndpointAdapter(int numberOfItems, ParentActivity activity) {
        this.numberOfItems = numberOfItems;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CompatibleEndpointHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.supportive_endpoints, parent, false);


        return new CompatibleEndpointHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompatibleEndpointHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberOfItems;
    }

    final class CompatibleEndpointHolder
        extends RecyclerView.ViewHolder{

        private final TextView endpointName;

        public CompatibleEndpointHolder(@NonNull View itemView) {
            super(itemView);

            this.endpointName = itemView.findViewById(R.id.supportive_endpoint);
        }

        public void bind(int listIndex) {
            this.endpointName.setText(activity.getEndpointName(listIndex));
        }
    }
}
