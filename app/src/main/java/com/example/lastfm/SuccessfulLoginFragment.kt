package com.example.lastfm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lastfm.databinding.FragmentSuccsessfulLoginBinding
import com.example.lastfm.login.LoginFragment

class SuccessfulLoginFragment : Fragment() {
    private lateinit var binding: FragmentSuccsessfulLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccsessfulLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentSuccsessfulLoginBinding.bind(view).apply {
            result.text = SUCCESS
            //add name of username
            logOut.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment.newInstance())
                    .commit()
            }
        }
    }

    companion object {
        const val SUCCESS = "You have successfully logged in!"

        @JvmStatic
        fun newInstance() = SuccessfulLoginFragment()
    }
}