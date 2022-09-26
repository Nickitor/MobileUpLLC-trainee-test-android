package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nikitazamyslov.mobileupllc_trainee_test_android.R
import com.nikitazamyslov.mobileupllc_trainee_test_android.databinding.FragmentCoinListBinding
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoinListViewModel by viewModels()

    private var currency = "usd"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        setObservers()
        viewModel.getCoinList(currency)
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

    private fun updateUi(state: ApiResponse<List<CoinPrice>>) {
        when (state) {
            is ApiResponse.Loading -> {
                binding.fragmentCoinListProgressBar.visibility = ProgressBar.VISIBLE
            }
            is ApiResponse.Success -> {
                binding.fragmentCoinListProgressBar.visibility = ProgressBar.INVISIBLE
                state.data.map { it.currency = currency }
                binding.fragmentCoinListRv.adapter =
                    CoinListAdapter(state.data, this@CoinListFragment)
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

    override fun onItemClicked(id: String) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_coinListFragment_to_coinDetailFragment, bundle)
    }
}
