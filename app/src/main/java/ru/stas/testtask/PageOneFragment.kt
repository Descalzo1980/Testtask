
package ru.stas.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.stas.adapter.FlashSaleAdapter
import ru.stas.adapter.IconAdapter
import ru.stas.adapter.LatestAdapter
import ru.stas.model.Icons
import ru.stas.testtask.databinding.FragmentPageOneBinding
import ru.stas.viewmodel.MyViewModel


class PageOneFragment : Fragment() {

    private var _binding: FragmentPageOneBinding? = null
    private val binding get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }

    private lateinit var adapterFlashSale: FlashSaleAdapter
    private lateinit var adapterLatestAdapter: LatestAdapter
    private lateinit var iconAdapter: IconAdapter

    private val viewModel: MyViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterFlashSale = FlashSaleAdapter(listOf())
        adapterLatestAdapter = LatestAdapter(listOf())
        iconAdapter = IconAdapter(Icons.values().toList())
        binding.rvFlash.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        binding.rvLatest.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        binding.rvFlash.adapter = adapterFlashSale
        binding.rvLatest.adapter = adapterLatestAdapter
        binding.rvIcons.adapter = iconAdapter
        binding.rvIcons.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)

        viewModel.latestProductsLiveData.observe(viewLifecycleOwner, Observer { latest ->
            adapterLatestAdapter.setItems(latest)
        })

        viewModel.flashSalesLiveData.observe(viewLifecycleOwner, Observer { flashSale ->
            adapterFlashSale.setItems(flashSale)
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

