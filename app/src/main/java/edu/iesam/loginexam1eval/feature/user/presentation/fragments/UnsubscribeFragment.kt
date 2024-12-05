package edu.iesam.loginexam1eval.feature.user.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.databinding.FragmentUnsubscribeBinding
import edu.iesam.loginexam1eval.feature.user.presentation.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UnsubscribeFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()

    private var _binding: FragmentUnsubscribeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsubscribeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setubObserver()
        setubView()

    }

    fun setubObserver() {
        val observer = Observer<UserViewModel.UiState> { uiState ->
            uiState.message?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    fun setubView() {
        binding.apply {
            action.setOnClickListener {
                val name = username.text.toString()
                val password = password.text.toString()
                if(name=="" || password==""){
                    Toast.makeText(requireContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.deleteUser(name, password)
                }
            }
        }
    }
}