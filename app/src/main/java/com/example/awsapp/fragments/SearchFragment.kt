package com.example.awsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.StuddySession
import com.example.awsapp.R
import com.example.awsapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = binding.sessionsListView
        val sessionsList : MutableList<String> = listOf<String>().toMutableList()

        Amplify.DataStore.query(
            StuddySession::class.java,
            {
                iterator ->
                while(iterator.hasNext()) {
                    val item = iterator.next()
                    sessionsList.add(item.topic)
                    Log.i("session", item.topic)
                }

                val adapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    sessionsList
                )

                //listView.adapter = adapter

            },
            {
                error -> Log.e("session", error.toString())
            }
        )

        binding.floatingActionButton.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog(){
        val addStudySessionDialog = AddStudySessionFragment()
        addStudySessionDialog.show(childFragmentManager, "fragment_name")
    }

}