package com.app.multicount.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.lifecycle.flowWithLifecycle
import com.app.data.utils.DataValidator
import com.app.multicount.R
import com.app.multicount.databinding.RegisterFragmentBinding
import com.app.multicount.ui.BaseFragment
import com.app.multicount.utils.extensions.mainViewModel
import com.app.multicount.utils.extensions.observe
import com.app.multicount.utils.extensions.observeEvent
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment() {

    override val viewModel: RegisterViewModel by viewModel()

    private lateinit var binding: RegisterFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@RegisterFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewObservers()
    }

    private fun setViewObservers() {
        viewModel.apply {
            observe(email){
                updateEmailUI(validateEmail(it))
            }
            observe(password){
                updatePasswordUI(validatePassword(password.value))
                updateConfirmPasswordUI(
                    validateConfirmPassword(
                        password.value,
                        confirmPassword.value
                    )
                )
            }
            observe(confirmPassword){
                updatePasswordUI(validatePassword(password.value))
                updateConfirmPasswordUI(
                    validateConfirmPassword(
                        password.value,
                        confirmPassword.value
                    )
                )
            }
            observeEvent(registerState){
                when (it) {
                    RegisterViewModel.LoginState.Loading -> {
                        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    RegisterViewModel.LoginState.Success -> {
                        activity?.mainViewModel?.onUserAuthenticated()
                    }
                    else -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setViewListeners() {
        binding.apply {

        }
    }

    private fun updateEmailUI(isValid: Boolean) {
        binding.ilEmail.helperText =
            if (!isValid) context?.getString(R.string.invalid_email) else null
    }

    private fun updatePasswordUI(isValid: Boolean) {
        binding.ilPassword.helperText = if (!isValid) context?.getString(
            R.string.invalid_password_min_length,
            DataValidator.PASSWORD_MIN_LENGTH
        ) else null
    }

    private fun updateConfirmPasswordUI(isValid: Boolean) {
        binding.ilConfirmPassword.helperText =
            if (!isValid) context?.getString(R.string.invalid_confirm_password) else null
    }
}