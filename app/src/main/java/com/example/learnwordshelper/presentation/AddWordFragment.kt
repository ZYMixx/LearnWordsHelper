package com.example.learnwordshelper.presentation

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learnwordshelper.databinding.AddWordFragmentLayoutBinding
import com.example.learnwordshelper.domain.WordLearn

class AddWordFragment : Fragment() {

    private lateinit var viewModel: AddWordFragmentViewModel
    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }

    private var _binding: AddWordFragmentLayoutBinding? = null
    private val binding: AddWordFragmentLayoutBinding
        get() = _binding ?: throw java.lang.RuntimeException("AddWordFragmentLayoutBinding == null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpSaveWordFabClicked()
        setUpGroupChooseSpinner()
        setUpTextChangeListeners()
        mainActivity.shadowMainScreenOn()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddWordFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[AddWordFragmentViewModel::class.java]
        viewModel.errorInputWord.observe(viewLifecycleOwner) {
            if (it) {
                binding.tilWord.error = "empty word"
            } else {
                binding.tilWord.error = null
            }
        }
        viewModel.errorInputTranslate.observe(viewLifecycleOwner) {
            if (it) {
                binding.tilTranslate.error = "empty translate"
            } else {
                binding.tilTranslate.error = null
            }
        }
    }

    private fun setUpSaveWordFabClicked() {
        mainActivity.onSaveFabClicked = {
            onSaveFabClicked()
        }
    }

    private fun onSaveFabClicked(): Boolean {
        val wordText = binding.etWord.text.toString().trim()
        val translateText = binding.etTranslate.text.toString().trim()
        val groupName = binding.spinnerGroup.selectedItem.toString().trim()

        if (wordText.isBlank() && translateText.isBlank()) {
            showToast("NOTHING ADDED")
            return true
        }

        if (viewModel.validateInput(wordText, translateText)) {
            viewModel.addNewWordLearn(
                WordLearn(
                    word = wordText,
                    translation = translateText,
                    groupName = groupName
                )
            )
            showToast("word \"$wordText\" ADDED")
            return true
        }
        return false
    }

    private fun setUpGroupChooseSpinner() {
        val vm = ViewModelProvider(requireActivity())[MainScreenViewModel::class.java]
        val spinner = binding.spinnerGroup
        val groupNameList: List<String> = vm.groupWordListLD.value?.map { it.name } ?: listOf()
        val spinnerAdapter = ArrayAdapter(
            this.context ?: throw RuntimeException(),
            android.R.layout.select_dialog_item,
            groupNameList
        )
        spinner.adapter = spinnerAdapter
    }

    private fun setUpTextChangeListeners() {
        binding.etWord.filters = arrayOf<InputFilter>(LengthFilter(MAX_WORD_NAME_LENGTH))
        binding.etTranslate.filters = arrayOf<InputFilter>(LengthFilter(MAX_TRANSLATE_LENGTH))

        binding.etWord.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputWord()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etWord.length() == MAX_WORD_NAME_LENGTH) {
                    binding.tilWord.error = "too long word"
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etTranslate.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputTranslate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etTranslate.length() == MAX_TRANSLATE_LENGTH) {
                    binding.tilTranslate.error = "too long description"
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun showToast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val MAX_WORD_NAME_LENGTH = 18
        const val MAX_TRANSLATE_LENGTH = 20

        fun newInstance(): AddWordFragment {
            return AddWordFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.shadowMainScreenOff()
    }

}