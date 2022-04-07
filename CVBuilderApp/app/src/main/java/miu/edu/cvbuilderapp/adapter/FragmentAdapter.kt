package miu.edu.cvbuilderapp.adapter

import miu.edu.cvbuilderapp.ui.fragments.*

class FragmentAdapter(manager : androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(manager) {

	private val listOfFragments: List<androidx.fragment.app.Fragment> = listOf(
		HomeFragment(),
		PersonalFragment(), // About me
		ExperienceFragment(),
		EducationFragment(),
		ContactFragment()
	)
	private val listOfTitles: List<String> = listOf("Home","About Me", "Work", "Education", "Contact")

	override fun getItem(position: Int): androidx.fragment.app.Fragment = listOfFragments[position]

	override fun getCount(): Int = listOfFragments.size

	override fun getPageTitle(position: Int) = listOfTitles[position]

}