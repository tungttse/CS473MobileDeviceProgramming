package miu.edu.cvbuilderapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.fragment.app.Fragment
import miu.edu.cvbuilderapp.R


class ContactFragment : Fragment() {
	var contactView: View? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		super.onCreateView(inflater, container, savedInstanceState)
		contactView = inflater.inflate(R.layout.fragement_contact, container, false)

//		implicit intent specifies
		var phoneBtn = contactView?.findViewById<TableRow>(R.id.phone)
		phoneBtn?.setOnClickListener {
			val dialIntent = Intent(Intent.ACTION_DIAL)
			dialIntent.data = Uri.parse("tel:" + "6418191317")
			startActivity(dialIntent)
		}

//		implicit intent specifies
		val emailBtn = contactView?.findViewById<TableRow>(R.id.mail)
		emailBtn?.setOnClickListener {
			sendEmail("tungttse@gmail.com", "Contact From CV Builder App", "")
		}

//		implicit intent specifies
		val devProfileRow = contactView?.findViewById<TableRow>(R.id.devProfileRow)
		devProfileRow?.setOnClickListener {
			val url = "https://www.github.com/tungttse"
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(url)
			startActivity(intent)
		}

//		implicit intent specifies
		val linkedInBtn = contactView?.findViewById<TableRow>(R.id.linkedIn)
		linkedInBtn?.setOnClickListener {
			val url = "https://www.linkedin.com/in/tungttse/"
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(url)
			startActivity(intent)
		}

		return contactView;
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