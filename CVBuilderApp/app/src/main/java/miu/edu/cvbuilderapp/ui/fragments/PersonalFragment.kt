package miu.edu.cvbuilderapp.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import miu.edu.cvbuilderapp.R

class PersonalFragment : Fragment() {
    var personalView: View? = null
    private val aboutMe =
        "Technically adept full-stack developer with 9 years of experience leading development of business-critical enterprise applications. Specialize in back-end solutions, front-end development. Good at end-to-end solution, hands on coding, software design and architecture."
    private val strengths =
        "communication skills, problem soving, time management, self-motivation"
    private val hobbies = "Programming, Reading Books, Gym, Music"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        personalView = inflater.inflate(R.layout.fragment_personal, container, false)

        var txtAboutMe = personalView?.findViewById<View>(R.id.txtAboutMe) as TextView
        txtAboutMe.text = Html.fromHtml(aboutMe)

        var txtStrength = personalView?.findViewById<View>(R.id.txtStrength) as TextView
        txtStrength.text = Html.fromHtml(strengths)

        var txtHobbies = personalView?.findViewById<View>(R.id.txtHobbies) as TextView
        txtHobbies.text = Html.fromHtml(hobbies)

        return personalView
    }
}