package com.example.what_to_do.view.addtask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.what_to_do.R
import com.example.what_to_do.databinding.FragmentAddTaskBinding
import com.example.what_to_do.utils.showChar
import com.example.what_to_do.utils.showSnackBar
import com.google.android.material.snackbar.Snackbar

class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel by viewModels<AddTaskViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_add_task,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        bindUiMessage()

        setEditTaskData()


        return binding.root
    }

    private fun setEditTaskData() {
        val selectedTaskData = AddTaskFragmentArgs.fromBundle(requireArguments())
        //Log.d("TAG", "setEditTaskData: ${selectedTaskData.title}")
        binding.viewModel?.let {
            if (selectedTaskData.id != -1){
                it.title.value = selectedTaskData.title
                it.description.value = selectedTaskData.description
            }
        }
    }

    private fun bindUiMessage() {
        binding.warningTextTaskTitle.showChar(lifecycleOwner = viewLifecycleOwner, viewModel.title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSnackbar(view)
    }

    private fun setSnackbar(view: View) {
        view.showSnackBar(
            lifecycleOwner = viewLifecycleOwner,
            viewModel.snackbarMsg,
            Snackbar.LENGTH_SHORT
        )
    }


}