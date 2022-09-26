package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.error_feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.databinding.FragmentCoinDetailBinding
import com.nikitazamyslov.mobileupllc_trainee_test_android.databinding.FragmentErrorBinding
import com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_detail_feature.CoinDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragment : Fragment() {

    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.fragmentErrorButton.setOnClickListener {
            findNavController().navigate(R.id.action_errorFragment_to_coinListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}