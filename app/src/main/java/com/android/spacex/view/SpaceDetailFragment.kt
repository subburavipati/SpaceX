package com.android.spacex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.spacex.databinding.FragmentItemDetailBinding
import com.android.spacex.viewmodel.SpaceViewModel

class SpaceDetailFragment : Fragment() {

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    private val spaceViewModel: SpaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        spaceViewModel.selectedSpaceX.observe(viewLifecycleOwner) {
            binding.spaceItem = it
            binding.executePendingBindings()
        }
        binding.itemDetailContainer.visibility =
            if (spaceViewModel.selectedSpaceX.value == null) View.GONE else View.VISIBLE

        return rootView
    }
}