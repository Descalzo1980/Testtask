package ru.stas.testtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.stas.adapter.FlashSaleAdapter
import ru.stas.adapter.LatestAdapter
import ru.stas.testtask.databinding.FragmentPageOneBinding
import ru.stas.viewmodel.FlashSaleViewModel
import ru.stas.viewmodel.LatestViewModel

const val TAG = "Заебало фрагмент"
class PageOneFragment : Fragment() {

    private var _binding: FragmentPageOneBinding? = null
    private val binding get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }

    private lateinit var adapterFlashSale: FlashSaleAdapter
    private lateinit var adapterLatestAdapter: LatestAdapter

    private val flashSaleViewModel: FlashSaleViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private val latestViewModel: LatestViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageOneBinding.inflate(inflater,container,false)

        adapterFlashSale = FlashSaleAdapter(listOf())
        adapterLatestAdapter = LatestAdapter(listOf())
        binding.rvFlash.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        binding.rvLatest.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        binding.rvFlash.adapter = adapterFlashSale
        binding.rvLatest.adapter = adapterLatestAdapter
        latestViewModel.getLatest()
        flashSaleViewModel.getFlashSales()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        latestViewModel.latestLiveData.observe(viewLifecycleOwner, Observer { latest ->
            adapterLatestAdapter.setItems(latest)
            adapterLatestAdapter.notifyDataSetChanged()
        })
        flashSaleViewModel.flashSaleLiveData.observe(viewLifecycleOwner, Observer { flashSale ->
            adapterFlashSale.setItems(flashSale)
            adapterFlashSale.notifyDataSetChanged()
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
