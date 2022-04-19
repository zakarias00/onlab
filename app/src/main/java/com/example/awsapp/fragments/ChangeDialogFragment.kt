package com.example.awsapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.User
import com.example.awsapp.R
import com.example.awsapp.databinding.FragmentChangeDialogBinding

class ChangeDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentChangeDialogBinding
    private var userId = Amplify.Auth.currentUser.userId

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_dialog, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentChangeDialogBinding.inflate(requireActivity().layoutInflater)

        binding.btnSave.setOnClickListener {

            if (binding.etChangedUName.text.isNotEmpty()) {
                Amplify.DataStore.query(User::class.java, Where.id(userId),
                    { matches ->
                        if (matches.hasNext()) {
                            val original = matches.next()
                            val updated = original.copyOfBuilder()
                                .name(binding.etChangedUName.text.toString())
                                .build()

                            Amplify.DataStore.save(
                                updated,
                                {
                                    Log.i("TAG", "updated")
                                },
                                {
                                    Log.e("TAG", it.message.toString())
                                })
                        }
                    },
                    {
                        Log.e("TAG", it.message.toString())
                    }
                )

                parentFragmentManager.setFragmentResult("requestKey", bundleOf("newUName" to binding.etChangedUName.text.toString()))
            }
            if (binding.etChangedFName.text.isNotEmpty()) {
                Amplify.DataStore.query(User::class.java, Where.id(userId),
                    { matches ->
                        if (matches.hasNext()) {
                            val original = matches.next()
                            val updated = original.copyOfBuilder()
                                .firstName(binding.etChangedFName.text.toString())
                                .build()

                            Amplify.DataStore.save(
                                updated,
                                {
                                    Log.i("TAG", "updated")
                                },
                                {
                                    Log.e("TAG", it.message.toString())
                                })
                        }
                    },
                    {
                        Log.e("TAG", it.message.toString())
                    }
                )

                parentFragmentManager.setFragmentResult("requestKey", bundleOf("newFName" to binding.etChangedFName.text.toString()))

            }
            if (binding.etChangedSName.text.isNotEmpty()) {
                Amplify.DataStore.query(User::class.java, Where.id(userId),
                    { matches ->
                        if (matches.hasNext()) {
                            val original = matches.next()
                            val updated = original.copyOfBuilder()
                                .secondName(binding.etChangedSName.text.toString())
                                .build()

                            Amplify.DataStore.save(
                                updated,
                                {
                                    Log.i("TAG", "updated")
                                },
                                {
                                    Log.e("TAG", it.message.toString())
                                })
                        }
                    },
                    {
                        Log.e("TAG", it.message.toString())
                    }
                )

                parentFragmentManager.setFragmentResult("requestKey", bundleOf("newSName" to binding.etChangedSName.text.toString()))

            }
            if (binding.etChangedEmail.text.isNotEmpty()) {
                Amplify.DataStore.query(User::class.java, Where.id(userId),
                    { matches ->
                        if (matches.hasNext()) {
                            val original = matches.next()
                            val updated = original.copyOfBuilder()
                                .email(binding.etChangedEmail.text.toString())
                                .build()

                            Amplify.DataStore.save(
                                updated,
                                {
                                    Log.i("TAG", "updated")
                                },
                                {
                                    Log.e("TAG", it.message.toString())
                                })
                        }
                    },
                    {
                        Log.e("TAG", it.message.toString())
                    }
                )
                parentFragmentManager.setFragmentResult("requestKey", bundleOf("newEmail" to binding.etChangedEmail.text.toString()))
            }
            if (binding.etChangedBirthDate.text.isNotEmpty()) {
                Amplify.DataStore.query(User::class.java, Where.id(userId),
                    { matches ->
                        if (matches.hasNext()) {
                            val original = matches.next()
                            val updated = original.copyOfBuilder()
                                .birthday(binding.etChangedBirthDate.text.toString())
                                .build()

                            Amplify.DataStore.save(
                                updated,
                                {
                                    Log.i("TAG", "updated")
                                },
                                {
                                    Log.e("TAG", it.message.toString())
                                })
                        }
                    },
                    {
                        Log.e("TAG", it.message.toString())
                    }
                )

                parentFragmentManager.setFragmentResult("requestKey", bundleOf("newBirthDay" to binding.etChangedBirthDate.text.toString()))

            }
            if (binding.etChangedPwd.text.isNotEmpty()) {


            }
            if (binding.etChangedSchool.text.isNotEmpty()) {

                Amplify.DataStore.query(User::class.java, Where.id(userId),
                    { matches ->
                        if (matches.hasNext()) {
                            val original = matches.next()
                            val updated = original.copyOfBuilder()
                                .school(binding.etChangedSchool.text.toString())
                                .build()

                            Amplify.DataStore.save(
                                updated,
                                {
                                    Log.i("TAG", "updated")
                                },
                                {
                                    Log.e("TAG", it.message.toString())
                                })
                        }
                    },
                    {
                        Log.e("TAG", it.message.toString())
                    }
                )

                parentFragmentManager.setFragmentResult("requestKey", bundleOf("newSchool" to binding.etChangedSchool.text.toString()))

            }

            this.dismiss()
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }


}