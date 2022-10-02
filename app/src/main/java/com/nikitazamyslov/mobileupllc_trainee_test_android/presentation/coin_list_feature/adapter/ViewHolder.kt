package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import java.util.*
import kotlin.math.abs

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val icon: ImageView = view.findViewById(R.id.item_coin_list_icon)
    private val name: TextView = view.findViewById(R.id.item_coin_list_name)
    private val symbol: TextView = view.findViewById(R.id.item_coin_list_symbol)
    private val price: TextView = view.findViewById(R.id.item_coin_list_price)
    private val percentage: TextView = view.findViewById(R.id.item_coin_list_percentage)

    fun bind(coin: CoinPrice) {
        Glide.with(itemView).load(coin.image).into(icon)

        name.text = coin.name
        symbol.text = coin.symbol.uppercase(Locale.ROOT)
        price.text = formatPrice(coin.currentPrice, coin.currency)
        percentage.text = formatPercentage(coin.priceChangePercentage)
        percentage.setTextColor(getPercentageColor(coin.priceChangePercentage))
    }

    private fun formatPrice(price: Double, currency: String?): String {
        return when (currency) {
            "USD" -> CoinListAdapter.priceFormatUSD
            "EUR" -> CoinListAdapter.priceFormatEUR
            else -> {
                throw Exception("Unknown currency")
            }
        }.format(price)
    }

    private fun formatPercentage(percentage: Double): String {
        return "${if (percentage > 0) "+" else "-"} ${
            "%,.2f".format(Locale.ENGLISH, abs(percentage))
        }%"
    }

    private fun getPercentageColor(percentage: Double): Int {
        return ContextCompat.getColor(
            itemView.context, if (percentage >= 0)
                PERCENTAGE_ABOVE_ZERO
            else
                PERCENTAGE_BELOW_ZERO
        )
    }

    companion object {
        const val PERCENTAGE_BELOW_ZERO: Int = R.color.red
        const val PERCENTAGE_ABOVE_ZERO: Int = R.color.green
    }
}