package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice

class CoinItemDiffCallback : DiffUtil.ItemCallback<CoinPrice>() {
    override fun areItemsTheSame(oldItem: CoinPrice, newItem: CoinPrice): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CoinPrice, newItem: CoinPrice): Boolean {
        return oldItem == newItem
    }
}