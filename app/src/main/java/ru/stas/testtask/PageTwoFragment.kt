package ru.stas.testtask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.stas.adapter.FlashSaleAdapter
import ru.stas.adapter.FlashSaleItemAdapter
import ru.stas.testtask.databinding.FragmentPageOneBinding
import ru.stas.testtask.databinding.FragmentPageTwoBinding
import ru.stas.viewmodel.MyViewModel

class PageTwoFragment : Fragment() {

    private var _binding: FragmentPageTwoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by viewModels()
    private lateinit var adapterFlashSaleItemAdapter: FlashSaleItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterFlashSaleItemAdapter = FlashSaleItemAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageTwoBinding.inflate(inflater,container,false)
        setupRecycleViewFlashItem()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeFlashSaleItem().observe(viewLifecycleOwner){flashSaleItem ->
            flashSaleItem?.let {
                adapterFlashSaleItemAdapter.setFlashItem(it)
                Log.d("TAG","ответ ${adapterFlashSaleItemAdapter.setFlashItem(it)}" )
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecycleViewFlashItem() = binding.rvFlashItem.apply {
        adapterFlashSaleItemAdapter = FlashSaleItemAdapter()
        adapter = adapterFlashSaleItemAdapter
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }
}