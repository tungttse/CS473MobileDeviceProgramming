package miu.edu.cvbuilderapp.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import miu.edu.cvbuilderapp.R


class InfoActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_info)

		val mToolbar: Toolbar = findViewById(R.id.top_bar)
		setSupportActionBar(mToolbar)

		if(supportActionBar !=  null) {
			supportActionBar!!.setHomeButtonEnabled(true)
			supportActionBar!!.setDisplayHomeAsUpEnabled(true)
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		super.onBackPressed()
		return true
	}

	override fun onContextItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			android.R.id.home -> {
				finish()
				return true
			}
		}
		return super.onContextItemSelected(item)
	}
}
