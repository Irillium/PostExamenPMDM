package edu.iesam.loginexam1eval.feature.user.presentation.logIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentLogInBinding
import edu.iesam.loginexam1eval.feature.user.domain.User
import edu.iesam.loginexam1eval.feature.user.presentation.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setubObserver()
        bing()
    }

    fun setubObserver() {
        val observer = Observer<UserViewModel.UiState> { uiState ->
            uiState.message?.let { message ->
                if(message=="Usuario guardado correctamente"){
                    findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToWelcomeFragment())
                }
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner,observer)
    }

    fun bing() {
        binding.apply {
            action.setOnClickListener {
                val name = username.text.toString()
                val password = password.text.toString()
                if(name=="" || password==""){
                    Toast.makeText(requireContext(), "Debes completar los campos", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.addUser(User(name, password))
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}