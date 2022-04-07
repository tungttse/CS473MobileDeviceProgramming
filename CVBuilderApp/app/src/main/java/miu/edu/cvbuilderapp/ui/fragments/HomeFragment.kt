package miu.edu.cvbuilderapp.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import miu.edu.cvbuilderapp.R


class HomeFragment : Fragment() {
    var homeView: View? = null

    private val careerNote =
        "<b>Completed</b> on-campus studies and currently taking distance education course to complete a Master's Degree in Computer Science (Available for full-time, <b>W-2</b> employment"
    private val workExp = "<b>Languages:</b> Java, Javascript, Kotlin \n" +
            "<b>Frameworks:</b> Spring (Boot, MVC, Security)\n" + "<b>Databases:</b> SQL, MySQL, NoSQL\n" +
            "<b>Cloud:</b >AWS (S3, EC2, Lambda, API Gateway), Heroku, Firebase\n" +
            "<b>Others:</b >OOP, Authorization, Authentication, CI/CD, Microservices."

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        var txtCareerNote = homeView?.findViewById<View>(R.id.txtHeadline) as TextView
        txtCareerNote.text = Html.fromHtml(careerNote)

        var txtWorkExp = homeView?.findViewById<View>(R.id.txtWorkExp) as TextView
        txtWorkExp.text = Html.fromHtml(workExp)

        return homeView
    }
}