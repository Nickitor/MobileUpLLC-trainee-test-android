package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import java.util.*

class CoinListAdapter(private val dataSet: List<CoinPrice>) :
    RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView
        val name: TextView
        val symbol: TextView
        val price: TextView
        val percentage: TextView

        init {
            icon = view.findViewById(R.id.item_coin_list_icon)
            name = view.findViewById(R.id.item_coin_list_name)
            symbol = view.findViewById(R.id.item_coin_list_symbol)
            price = view.findViewById(R.id.item_coin_list_price)
            percentage = view.findViewById(R.id.item_coin_list_percentage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coin_list, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Glide.with(viewHolder.icon)
            .load(dataSet[position].image)
            .into(viewHolder.icon)

        viewHolder.name.text = dataSet[position].name
        viewHolder.symbol.text = dataSet[position].symbol.uppercase(Locale.ROOT)
        viewHolder.price.text = dataSet[position].currentPrice.toString()
        viewHolder.percentage.text = dataSet[position].priceChangePercentage.toString()
    }

    override fun getItemCount() = dataSet.size
}