package miu.edu.cvbuilderapp.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import miu.edu.cvbuilderapp.R
import miu.edu.cvbuilderapp.adapter.FragmentAdapter
import miu.edu.cvbuilderapp.viewmodel.CreateResumeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import miu.edu.cvbuilderapp.utilities.hideKeyboard

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val createResumeActivityJob = Job()
    override val coroutineContext = Dispatchers.Main + createResumeActivityJob

    private lateinit var createResumeViewModel: CreateResumeViewModel
    private lateinit var resumeFragmentAdapter: FragmentAdapter
    private lateinit var createResumeItem: FloatingActionButton
    private lateinit var viewPager: ViewPager

    private var addIcon: Drawable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get preference to check status login
        val sharedPref = this.getSharedPreferences("cv_builder_app", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getString("is_login", "false");
        if (isLogin !="true") { // if not login, move to login activity.
            val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        val userName = sharedPref.getString("name", "");


        setSupportActionBar(createResumeToolbar)
        if(userName.isNullOrBlank()) {
            supportActionBar?.title = resources.getString(R.string.app_name)
        } else {
            supportActionBar?.title = resources.getString(R.string.app_name)
            supportActionBar?.subtitle = "Welcome " + userName;
        }


        createResumeItem = findViewById(R.id.btnAddItem)
        viewPager = findViewById(R.id.createResumeViewpager)
        addIcon = ContextCompat.getDrawable(this, R.drawable.ic_round_add_24px)

        val intent = intent

        createResumeViewModel = ViewModelProviders
                .of(this)
                .get(CreateResumeViewModel::class.java)

        resumeFragmentAdapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = resumeFragmentAdapter
        viewPager.offscreenPageLimit = 4
        createResumeTabs.setupWithViewPager(createResumeViewpager)
        createResumeItem.hide()
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                // Do nothing
            }
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                // Do nothing
            }
            override fun onPageSelected(position: Int) {
                adjustFabBehaviour(position) // handle selected of the create button
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.info -> run {
                this@MainActivity.hideKeyboard()
                if (checkIfDetailsSaved()) {
                    val intent = Intent(this@MainActivity, InfoActivity::class.java)
                    startActivity(intent)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun adjustFabBehaviour(position: Int) {
        when (position) {
            2 -> fabBehaviourExperienceFragment()
            3 -> fabBehaviourEducationFragment()
            else -> createResumeItem.hide()
        }
    }

    private fun fabBehaviourEducationFragment() {
        createResumeItem.apply {
            hide()
            setImageDrawable(addIcon)
            show()
            setOnClickListener {
                createResumeViewModel.apply {
                    insertBlankEducation()
                    educationDetailsSaved = false
                }
            }
        }
    }

    private fun fabBehaviourExperienceFragment() {
        createResumeItem.apply {
            hide()
            setImageDrawable(addIcon)
            show()
            setOnClickListener {
                createResumeViewModel.apply {
                    insertBlankExperience()
                    experienceDetailsSaved = false
                }
            }
        }
    }

    fun displaySnackbar(text: String) {
        Snackbar.make(rootCoordinatorLayout, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun checkIfDetailsSaved(): Boolean {
        with(createResumeViewModel) {
            if (!personalDetailsSaved) {
                viewPager.setCurrentItem(0, true)
                Snackbar.make(rootCoordinatorLayout, "Personal details unsaved", Snackbar.LENGTH_SHORT).show()
                return false
            }
            if (!educationDetailsSaved) {
                viewPager.setCurrentItem(1, true)
                Snackbar.make(rootCoordinatorLayout, "Education details unsaved", Snackbar.LENGTH_SHORT).show()
                return false
            }
            if (!experienceDetailsSaved) {
                viewPager.setCurrentItem(2, true)
                Snackbar.make(rootCoordinatorLayout, "Experience details unsaved", Snackbar.LENGTH_SHORT).show()
                return false
            }
            if (!projectDetailsSaved) {
                viewPager.setCurrentItem(3, true)
                Snackbar.make(rootCoordinatorLayout, "Project details unsaved", Snackbar.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}

