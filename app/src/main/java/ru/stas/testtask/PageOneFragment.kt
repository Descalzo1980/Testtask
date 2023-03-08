
package ru.stas.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.stas.adapter.FlashSaleAdapter
import ru.stas.adapter.IconAdapter
import ru.stas.adapter.LatestAdapter
import ru.stas.model.FlashSale
import ru.stas.model.Icons
import ru.stas.testtask.databinding.FragmentPageOneBinding
import ru.stas.viewmodel.MyViewModel


class PageOneFragment : Fragment(),FlashSaleAdapter.OnItemClickListener {

    private var _binding: FragmentPageOneBinding? = null
    private val binding get() = _binding!!
    private lateinit var iconAdapter: IconAdapter
    private lateinit var adapterLatestAdapter: LatestAdapter
    private lateinit var adapterFlashSale: FlashSaleAdapter

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iconAdapter = IconAdapter(Icons.values().toList())
        adapterLatestAdapter = LatestAdapter()
        adapterFlashSale = FlashSaleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageOneBinding.inflate(inflater, container, false)
        setupRecycleViewIcon()
        setupRecycleViewLatest()
        setupRecycleViewFlash()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeFlashSale().observe(viewLifecycleOwner) { flashSale ->
            flashSale?.let {
                adapterFlashSale.submitList(it)
            }
        }

        viewModel.observeLatestProducts().observe(viewLifecycleOwner) { latestProducts ->
            latestProducts?.let {
                adapterLatestAdapter.setFlashList(it)
            }
        }

        adapterFlashSale.setOnItemClickListener(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(flashSale: FlashSale) {
        val action = PageOneFragmentDirections.actionPageOneFragmentToPageTwoFragment()
        findNavController().navigate(action)
    }


    private fun setupRecycleViewLatest() = binding.rvLatest.apply {
        adapterLatestAdapter = LatestAdapter()
        adapter = adapterLatestAdapter
        layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
    private fun setupRecycleViewFlash() = binding.rvFlash.apply {
        adapterFlashSale = FlashSaleAdapter()
        adapter = adapterFlashSale
        layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
    private fun setupRecycleViewIcon() = binding.rvIcons.apply {
        iconAdapter = IconAdapter(Icons.values().toList())
        binding.rvIcons.adapter = iconAdapter
        binding.rvIcons.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

}


