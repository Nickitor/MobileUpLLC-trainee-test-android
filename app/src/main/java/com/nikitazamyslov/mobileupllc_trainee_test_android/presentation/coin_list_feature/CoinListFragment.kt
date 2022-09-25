package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nikitazamyslov.mobileupllc_trainee_test_android.databinding.FragmentCoinListBinding
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoinListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)

        setObservers()

        return binding.root
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is ApiResponse.Loading -> {
                        }
                        is ApiResponse.Success -> {
                            binding.fragmentCoinListRv.adapter = CoinListAdapter(state.data)
                        }
                        is ApiResponse.Error -> {
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
