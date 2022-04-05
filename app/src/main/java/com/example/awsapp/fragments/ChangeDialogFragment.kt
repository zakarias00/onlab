package com.example.awsapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.awsapp.R
import com.example.awsapp.databinding.FragmentChangeDialogBinding

class ChangeDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentChangeDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_dialog, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentChangeDialogBinding.inflate(requireActivity().layoutInflater)



        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }


}