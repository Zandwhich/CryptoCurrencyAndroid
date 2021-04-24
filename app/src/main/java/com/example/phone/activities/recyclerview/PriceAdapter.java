package com.example.phone.activities.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone.R;
import com.example.phone.activities.CurrencyActivity;

/**
 * The adapter class that works together with the RecyclerView to display the data in a list
 */
public final class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {

    /**
     * An interface used by an activity to implement what happens when an item in the RecyclerView
     * is clicked
     */
    public interface PriceAdapterOnClickHelper {
        void onPriceAdapterClick(int position, View view);
    }//end PriceAdapterOnClickHelper

    /**
     * The simple name of the class used for displaying logs
     */
    private static final String TAG = PriceAdapter.class.getSimpleName();

    /**
     * The total number of items that this recyclerview has
     */
    private int mNumberItems;

    /**
     * The click listener (implemented by an activity) to listen to the clicks
     */
    private final PriceAdapterOnClickHelper clickHelper;

    /**
     * The activity that holds the currencies and all that data
     */
    private final CurrencyActivity currencyActivity;



    /**
     * The constructor for the PriceAdapter
     * @param numberOfItems The number of items to be displayed
     * @param clickHelper The activity that implements what happens when an item is pressed
     */
    public PriceAdapter(int numberOfItems, PriceAdapterOnClickHelper clickHelper,
                        CurrencyActivity currencyActivity) {
        this.mNumberItems = numberOfItems;
        this.clickHelper = clickHelper;
        this.currencyActivity = currencyActivity;
    }//end PriceAdapter()

    /**
     * Runs when the view holder is created? I think
     * TODO: Figure out what this does, lol
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.price_list_item, parent, false);

        return new PriceViewHolder(view);
    }//end onCrateViewHolder()

    /**
     * TODO: Figure out how to pass in actual price details
     * Will bind the {@link PriceViewHolder} with the given position
     * @param holder The {@link PriceViewHolder} that will display all of the data
     * @param position The given position of that view holder
     */
    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        Log.d(PriceAdapter.TAG, "#" + position);
        holder.bind(position);
    }//end onBindViewHolder()

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return this.mNumberItems;
    }//end getItemCount()

    /**
     * The internal ViewHolder class that will display the price data
     */
    final class PriceViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

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

            itemView.setOnClickListener(this);
        }//end PriceViewHolder()

        /**
         * The method that is called to bind this specific view holder to its parent
         * @param listIndex The given position of the view holder
         */
        public void bind(int listIndex) {
            this.mAPIName.setText(currencyActivity.getCurrencyName(listIndex));
            this.mPrice.setText(String.valueOf(currencyActivity.getCurrencyPrice(listIndex)));
        }//end bind()

        @Override
        public void onClick(View v) {
            clickHelper.onPriceAdapterClick(getAdapterPosition(), v);
        }//end onClick
    }//end PriceViewHolder
}//end PriceAdapter
