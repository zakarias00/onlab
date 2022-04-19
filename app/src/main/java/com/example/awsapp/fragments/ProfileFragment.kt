package com.example.awsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.User
import com.example.awsapp.R
import com.example.awsapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUserName.text = arguments?.getString("uName")
        binding.tvEmail.text = arguments?.getString("eMail")
        binding.tvPassword.text = arguments?.getString("password")

       /* val attributes = Amplify.Auth.fetchUserAttributes(
                this::onSuccess,
                this::onError
            )
*/
            /*Log.i("TAG", "User attributes = $attributes")
        } catch (error: AuthException) {
            Log.e("TAG", "Failed to fetch user attributes", error)
        }*/

            Amplify.DataStore.query(
                User::class.java,
                { items ->
                    while (items.hasNext()) {
                        val item = items.next()
                        if (item.id == Amplify.Auth.currentUser.userId) {
                            binding.tvBirthDate.text = item.birthday.toString()
                            binding.tvFName.text = item.firstName.toString()
                            binding.tvSName.text = item.secondName.toString()
                            binding.tvEmail.text = item.email.toString()
                            binding.tvSchool.text = item.school.toString()
                        }
                    }
                },

                {
                        failure -> Log.e("TAG", failure.message.toString())
                }
            )

            binding.btnModify.setOnClickListener {
                showDialog()
            }
    }

    private fun onSuccess(attributes:MutableList<AuthUserAttribute>){
        Log.i("TAG", attributes.toString())

    }

    private fun onError(e: AuthException) {
        runOnUiThread {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                .show()
            Log.e("TAG", e.toString())
        }
    }

    private fun showDialog(){
        val changeDialog = ChangeDialogFragment()
        changeDialog.show(childFragmentManager, "fragment_name")
    }
}
