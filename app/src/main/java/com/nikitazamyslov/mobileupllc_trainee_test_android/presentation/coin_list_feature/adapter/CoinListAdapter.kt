package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

class CoinListAdapter : ListAdapter<CoinPrice, ViewHolder>(CoinItemDiffCallback()) {

    var onItemClickListener: ((id: String) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_coin_list, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            val coin = getItem(position)
            bind(coin)
            itemView.setOnClickListener {
                onItemClickListener?.invoke(coin.id)
            }
        }
    }

    companion object {
        val priceFormatUSD: NumberFormat =
            NumberFormat.getCurrencyInstance(Locale("es", "MX"))
        val priceFormatEUR: NumberFormat =
            NumberFormat.getCurrencyInstance(Locale("en", "FR"))
    }
}
