package com.example.awsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amplifyframework.auth.AuthException
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.User
import com.example.awsapp.MessageListAdapter
import com.example.awsapp.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private lateinit var binding : FragmentMessageBinding
   // private lateinit var adapterView: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recycleView
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        val data = ArrayList<User>()

        Amplify.DataStore.query(
            User::class.java,
            { items ->
                while (items.hasNext()) {
                    val item = items.next()
                    data.add(item)
                   // Log.i("message", item.name)
                }

                Log.i("data", data[0].name.toString())
                /*val adapterView = MessageListAdapter(data)
                recyclerView.adapter = adapterView*/
            },
            { failure -> Log.e("TAG", failure.message.toString())
            }
        )


    }

}