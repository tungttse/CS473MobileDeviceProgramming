package miu.edu.cvbuilderapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import miu.edu.cvbuilderapp.R
import miu.edu.cvbuilderapp.repository.database.Education
import miu.edu.cvbuilderapp.ui.activities.MainActivity
import miu.edu.cvbuilderapp.viewmodel.CreateResumeViewModel
import kotlinx.android.synthetic.main.fragment_education.*
import miu.edu.cvbuilderapp.utilities.areAllItemsSaved
import miu.edu.cvbuilderapp.utilities.invisible
import miu.edu.cvbuilderapp.utilities.visible

class EducationFragment : Fragment() {

    private lateinit var educationAdapter: miu.edu.cvbuilderapp.adapter.EducationAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var createResumeViewModel: CreateResumeViewModel
    private lateinit var educationRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        educationAdapter =
            miu.edu.cvbuilderapp.adapter.EducationAdapter(
                { item: Education ->
                    // On Save Button Clicked
                    item.saved = true
                    createResumeViewModel.updateEducation(item)
                },
                { item: Education ->
                    // On Delete Button Clicked
                    createResumeViewModel.deleteEducation(item)
                    (activity as MainActivity).displaySnackbar("Education deleted.")
                },
                { item: Education ->
                    // On Edit Button Clicked
                    item.saved = false
                    createResumeViewModel.updateEducation(item)
                })

        linearLayoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_education, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            createResumeViewModel = ViewModelProviders
                    .of(it)
                    .get(CreateResumeViewModel::class.java)
        }

        createResumeViewModel.educationList
                .observe(viewLifecycleOwner, Observer {
                    educationAdapter.updateEducationList(it ?: emptyList())
                    createResumeViewModel.educationDetailsSaved = it == null || it.isEmpty() || it.areAllItemsSaved()
                    toggleNoEducationLayout(it?.size ?: 0)
                })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        educationRecyclerView = view.findViewById(R.id.educationRecyclerView)

        educationRecyclerView.apply {
            adapter = educationAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun toggleNoEducationLayout(size: Int) {
        if (size > 0) {
            educationRecyclerView.visible()
            noEducationView.invisible()
        } else {
            educationRecyclerView.invisible()
            noEducationView.visible()
        }
    }
}