package androidquiz.app.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.result_fragment.*
import androidquiz.app.R

class ResultFragment : Fragment() {

    companion object {
        fun newInstance() =
            ResultFragment()
    }

    private lateinit var navController: NavController
    private var score: Int = 0
    private var categoryName = "Android"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        score = ResultFragmentArgs.fromBundle(requireArguments()).correctAnswers
        categoryName =
            ResultFragmentArgs.fromBundle(requireArguments()).categoryName
        setupUI()
        setupButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        if (Build.VERSION.SDK_INT > 24) {
            val s = "Quiz Topic: <b>${categoryName.toUpperCase()}</b>"
            tv_subText.text = Html.fromHtml(s, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        } else {
            tv_subText.text = "Quiz Topic: ${categoryName.toUpperCase()}"
        }
        chip_user.text = "$score correct"
        chip_opponent.text = "${15 - score} incorrect"

        if(score == 0) {
            tv_matchStatus.text = "Oop! You can try again :)"
        }
    }

    private fun setupButton() {
        btn_analysis.setOnClickListener {
            navController.navigate(R.id.action_result_screen_to_analysis_screen)
        }

        btn_goHome.setOnClickListener {
            navController.navigate(R.id.action_result_screen_to_home_screen)
        }
        btn_history.setOnClickListener {
            navController.navigate(R.id.action_result_screen_to_history_screen)
        }

        btn_share.setOnClickListener {
            sendEmail("", "I got $score correct in Android Quiz Test!" ,
                "I feel excited to share I got $score correct in Android Quiz Test!");
        }
    }
    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, recipient)
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
    }
}
