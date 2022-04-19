package com.example.awsapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.StuddySession
import com.example.awsapp.R
import com.example.awsapp.databinding.FragmentAddStudySessionBinding
import com.example.awsapp.databinding.FragmentChangeDialogBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddStudySessionFragment : DialogFragment() {

    private lateinit var binding: FragmentAddStudySessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddStudySessionBinding.inflate(requireActivity().layoutInflater)
        binding.timePicker.setIs24HourView(true)

        binding.btnDone.setOnClickListener {

            val datePicker = binding.datePicker
            Log.i("date", datePicker.year.toString() + "/" + datePicker.month.toString() + "/" + datePicker.dayOfMonth.toString())
            val date = LocalDate.of(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateFormatted = date.format(dateFormatter)
            
            val timePicker = binding.timePicker
            val time = LocalTime.of(timePicker.hour, timePicker.minute)
            val timeFormatter = DateTimeFormatter.ofPattern("hh:mm")
            val timeFormatted = time.format(timeFormatter)
            Log.i("date", timePicker.hour.toString() + ":" + timePicker.minute.toString())

            val studySession = StuddySession.builder()
                .topic(binding.etTopic.text.toString())
                .location(binding.etLocation.text.toString())
                .date(Temporal.Date(dateFormatted))
                .time(Temporal.Time(timeFormatted))
                .build()

            Amplify.DataStore.save(
                studySession,
                { success -> Log.i("study_data", "saved successfully"+ success.item().topic)
                    dismiss()
                },
                { error -> Log.e("study_data", error.toString())}

            )
        }

        binding.btnCancelSessionAdd.setOnClickListener{
            dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_study_session, container, false)
    }
}