package com.android.spacex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.android.spacex.R
import com.android.spacex.adapter.SpacexAdapter
import com.android.spacex.databinding.FragmentSpaceListBinding
import com.android.spacex.viewmodel.SpaceViewModel
import kotlinx.android.synthetic.main.fragment_space_list.view.*


class SpaceListFragment : Fragment() {

    private val spaceViewModel: SpaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSpaceListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = spaceViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SpacexAdapter {
            spaceViewModel.updateSelectedSpaceX(it)
            view.findViewById<FragmentContainerView>(R.id.item_detail_nav_container)
                ?.findNavController()?.navigate(R.id.fragment_item_detail)
                ?: kotlin.run {
                    view.findNavController().navigate(R.id.show_item_detail)
                }
        }
        view.item_list.adapter = adapter
        spaceViewModel.registerForSpacexDetails().observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateItems(it)
            }
        }
    }
}