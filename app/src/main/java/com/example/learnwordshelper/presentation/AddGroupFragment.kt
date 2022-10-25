package com.example.learnwordshelper.presentation

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learnwordshelper.databinding.AddNewGroupFragmentLayoutBinding
import com.example.learnwordshelper.domain.GroupWord

class AddGroupFragment : Fragment() {

    private var _binding: AddNewGroupFragmentLayoutBinding? = null
    private val binding: AddNewGroupFragmentLayoutBinding
        get() = _binding ?: throw java.lang.RuntimeException("AddNewGroupFragmentBinding == null")

    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }

    private lateinit var viewModel: AddGroupFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        saveButtonClicked()
        setUpTextChangeListeners()
        mainActivity.shadowMainScreenOn()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddNewGroupFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[AddGroupFragmentViewModel::class.java]

        viewModel.errorInputGroupName.observe(viewLifecycleOwner) {
            if (it) {
                binding.tilGroupName.error = "empty group name"
            } else {
                binding.tilGroupName.error = null
            }
        }
        viewModel.errorInputGroupDescription.observe(viewLifecycleOwner) {
            if (it) {
                binding.tilDescription.error = "empty description"
            } else {
                binding.tilDescription.error = null
            }
        }
    }

    private fun saveButtonClicked() {
        (requireActivity() as MainActivity).onSaveFabClicked = {
            onSaveFabClicked()
        }
    }

    private fun onSaveFabClicked(): Boolean {
        val groupName = binding.etGroupName.text.toString().trim()
        val descriptionText = binding.etDescription.text.toString().trim()

        if (groupName.isBlank() && descriptionText.isBlank()) {
            showToast("NOTHING ADDED")
            return true
        }

        if (viewModel.validateInput(groupName, descriptionText)) {
            viewModel.addNewGroupWord(
                GroupWord(
                    name = groupName,
                    description = descriptionText,
                )
            )
            showToast("Group \"$groupName\" CREATED")
            return true
        }
        return false
    }

    private fun setUpTextChangeListeners() {
        binding.etGroupName.filters =
            arrayOf<InputFilter>(InputFilter.LengthFilter(MAX_GROUP_NAME_LENGTH))
        binding.etDescription.filters =
            arrayOf<InputFilter>(InputFilter.LengthFilter(MAX_DESCRIPTION_LENGTH))

        binding.etGroupName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputGroup()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etGroupName.length() == MAX_GROUP_NAME_LENGTH) {
                    binding.tilGroupName.error = "too long name"
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputGroupDescription()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etDescription.length() == MAX_DESCRIPTION_LENGTH) {
                    binding.tilDescription.error = "too long description"
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun showToast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val MAX_GROUP_NAME_LENGTH = 15
        const val MAX_DESCRIPTION_LENGTH = 50

        fun newInstance(): AddGroupFragment {
            return AddGroupFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.shadowMainScreenOff()
    }
}