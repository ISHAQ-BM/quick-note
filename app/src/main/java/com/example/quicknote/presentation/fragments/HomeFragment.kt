package com.example.quicknote.presentation.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentHomeBinding
import com.example.quicknote.presentation.adapters.NoteAdapter
import com.example.quicknote.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding?=null

    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter=NoteAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(it)
            findNavController().navigate(action)
        }

        binding?.recyclerView?.adapter=adapter


        lifecycle.coroutineScope.launch {
            viewModel.getAll().collect() {
                adapter.submitList(it)
            }
        }

        binding?.toolbar?.setOnMenuItemClickListener{actionSearch ->

                    val searchView=actionSearch.actionView as SearchView
                    searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            viewModel.searchNotes(p0).observe(viewLifecycleOwner) {
                                adapter.submitList(it)
                            }
                            return true
                        }

                        override fun onQueryTextChange(p0: String?): Boolean {
                            viewModel.searchNotes(p0).observe(viewLifecycleOwner) {
                                adapter.submitList(it)
                            }
                            return true
                        }

                    })

            searchView.setOnCloseListener {
                lifecycle.coroutineScope.launch {
                    viewModel.getAll().collect() {
                        adapter.submitList(it)
                    }
                }
                true
            }


            true

        }



        binding?.add?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }






    }






    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}