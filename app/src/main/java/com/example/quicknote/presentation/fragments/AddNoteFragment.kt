package com.example.quicknote.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quicknote.databinding.FragmentAddNoteBinding
import com.example.quicknote.domain.model.Note
import com.example.quicknote.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding?=null
    private val args: AddNoteFragmentArgs by navArgs()

    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.done?.setOnClickListener {
            val title=binding?.title?.text?.trim().toString()
            val subtitle=binding?.subtitle?.text?.trim().toString()
            val notes=binding?.notes?.text?.trim().toString()
            val priority=view.findViewById<RadioButton>(binding?.priorities?.checkedRadioButtonId ?: -1).text.toString()

            if (title.isEmpty()){
                binding?.inputLayoutTitle?.error="Required."
            }else if (subtitle.isEmpty()) {
                binding?.inputLayoutTitle?.error=null
                binding?.inputLayoutTitle?.clearFocus()
                binding?.inputLayoutSubtitle?.error = "Required."
            }else if (notes.isEmpty()){
                binding?.inputLayoutSubtitle?.error=null
                binding?.inputLayoutSubtitle?.clearFocus()
                binding?.inputLayoutNotes?.error = "Required."
            }else{
                binding?.inputLayoutNotes?.error = null
                binding?.inputLayoutNotes?.clearFocus()
                viewModel.addNote(
                    Note(
                        title = title,
                        subTitle = subtitle,
                        description = notes,
                        priority = priority,
                        createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    )
                )
                findNavController().popBackStack()
            }

        }


        if (args.id != -1){
            viewModel.getNote(args.id).observe(viewLifecycleOwner) {
                binding?.title?.setText(it.title)
                binding?.subtitle?.setText(it.subTitle)
                binding?.notes?.setText(it.description)
                when(it.priority){
                    "low" -> binding?.priorityLow?.isChecked=true
                    "medium" -> binding?.priorityMedium?.isChecked=true
                    "high" -> binding?.priorityHigh?.isChecked=true
                }
            }

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}