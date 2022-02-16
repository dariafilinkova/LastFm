package com.example.lastfm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.liveData
import com.example.lastfm.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val preference by lazy { CustomPreference(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        FragmentLoginBinding.bind(view).apply {
            login.setOnClickListener {
                val userName = username.text.toString()
                val password = password.text.toString()

                viewModel.onSignInClick(userName, password)
                /*   Toast.makeText(
                       requireContext(),
                       "Try again.Username or password is wrong",
                       Toast.LENGTH_SHORT
                   )
                       .show()*/
            }
            //}
        }
    }

    private fun observeLiveData() {
        viewModel.authenticationIsDone.observe(viewLifecycleOwner) { authenticationIsDone ->
            if (authenticationIsDone) {
                preference.password = binding.password.text.toString()
                preference.username = binding.username.text.toString()
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, SuccessfulLoginFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }

        viewModel.wrongUserNameORPassword.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}