package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

class CoinListAdapter(
    private val dataSet: List<CoinPrice>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    private val priceFormatUSD: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
    private val priceFormatEUR: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "FR"))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val icon: ImageView
        private val name: TextView
        private val symbol: TextView
        private val price: TextView
        private val percentage: TextView

        init {
            view.apply {
                icon = findViewById(R.id.item_coin_list_icon)
                name = findViewById(R.id.item_coin_list_name)
                symbol = findViewById(R.id.item_coin_list_symbol)
                price = findViewById(R.id.item_coin_list_price)
                percentage = findViewById(R.id.item_coin_list_percentage)
            }
        }

        fun bind(coin: CoinPrice, clickListener: OnItemClickListener) {
            Glide.with(itemView).load(coin.image).into(icon)

            name.text = coin.name
            symbol.text = coin.symbol.uppercase(Locale.ROOT)
            price.text = when (coin.currency) {
                "USD" -> priceFormatUSD
                "EUR" -> priceFormatEUR
                else -> {
                    priceFormatUSD
                }
            }.format(coin.currentPrice)

            val percentageStr = "${if (coin.priceChangePercentage > 0) "+" else "-"} ${
                "%,.2f".format(Locale.ENGLISH, abs(coin.priceChangePercentage))
            }%"
            percentage.text = percentageStr
            percentage.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (coin.priceChangePercentage >= 0) R.color.green else R.color.red
                )
            )

            itemView.setOnClickListener {
                clickListener.onItemClicked(coin.id)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_coin_list, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position], itemClickListener)
    }

    override fun getItemCount() = dataSet.size
}
