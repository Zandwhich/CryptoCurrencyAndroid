package com.example.phone.activities.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone.R;

/**
 * The adapter class that works together with the RecyclerView to display the data in a list
 */
public final class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {

    /**
     * The simple name of the class used for displaying logs
     */
    private static final String TAG = PriceAdapter.class.getSimpleName();

    private int mNumberItems;

    public PriceAdapter(int numberOfItems) {
        this.mNumberItems = numberOfItems;
    }//end PriceAdapter()

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.price_list_item, parent, false);

        return new PriceViewHolder(view);
    }//end onCrateViewHolder()

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        Log.d(PriceAdapter.TAG, "#" + position);
        holder.bind(position);
    }//end onBindViewHolder()

    @Override
    public int getItemCount() {
        return this.mNumberItems;
    }//end getItemCount()

    /**
     * The internal ViewHolder class that will display the price data
     */
    static final class PriceViewHolder extends RecyclerView.ViewHolder {

        /**
         * The name of the API that is called for the given item
         */
        private TextView mAPIName;

        /**
         * The price from the given API
         */
        private TextView mPrice;

        public PriceViewHolder(View itemView) {
            super(itemView);

            this.mAPIName = itemView.findViewById(R.id.tv_list_item_name);
            this.mPrice = itemView.findViewById(R.id.tv_list_item_price);
        }//end PriceViewHolder()

        /**
         * The method that is called to bind this specific view holder to its parent
         * @param listIndex The given position of the view holder
         */
        public void bind(int listIndex) {
            this.mAPIName.setText(String.valueOf(listIndex));
            this.mPrice.setText(String.valueOf(listIndex));
        }//end bind()

    }//end PriceViewHolder
}//end PriceAdapter
