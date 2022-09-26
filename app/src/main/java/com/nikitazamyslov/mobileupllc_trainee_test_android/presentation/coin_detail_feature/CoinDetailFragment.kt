package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_detail_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.databinding.FragmentCoinDetailBinding
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Thread.State

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        setObservers()
        setListeners()
        viewModel.getCoinDetail(arguments?.getString("id"))
        return binding.root
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    updateUi(state)
                }
            }
        }
    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateUi(state: ApiResponse<CoinDetail>) {
        when (state) {
            is ApiResponse.Loading -> {
                binding.fragmentCoinDetailProgressBar.visibility = View.VISIBLE
                binding.fragmentCoinDetailScrollView.visibility = View.INVISIBLE
            }
            is ApiResponse.Success -> {
                binding.fragmentCoinDetailProgressBar.visibility = View.INVISIBLE
                binding.fragmentCoinDetailScrollView.visibility = View.VISIBLE

                state.data.apply {
                    Glide.with(binding.fragmentCoinDetailImage).load(image.large)
                        .into(binding.fragmentCoinDetailImage)
                    if (description.english.isNotEmpty())
                        binding.fragmentCoinDetailDescription.text = description.english
                    if (categories.isNotEmpty())
                        binding.fragmentCoinDetailCategory.text = categories.joinToString(", ")
                    binding.toolbar.title = name
                }
            }
            is ApiResponse.Error -> {
                findNavController().navigate(R.id.action_global_to_ErrorFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
