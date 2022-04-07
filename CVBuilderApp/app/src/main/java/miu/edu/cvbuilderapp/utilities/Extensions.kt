package miu.edu.cvbuilderapp.utilities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import miu.edu.cvbuilderapp.repository.database.ResumeEntity


fun View.showKeyboard(context: Context?) {
	this.requestFocus()
	val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun AppCompatActivity.hideKeyboard() {
	val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	inputManager.hideSoftInputFromWindow(
			if (this.currentFocus == null) {
				null
			} else {
				this.currentFocus!!.windowToken
			}, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun <T : ResumeEntity> List<T>.isAnyItemUnsaved() : Boolean {
	for (entity in this) {
		if (!entity.saved) {
			return true
		}
	}
	return false
}

fun <T : ResumeEntity> List<T>.areAllItemsSaved() : Boolean = !this.isAnyItemUnsaved()

fun View.gone() {
	this.visibility = View.GONE
}

fun View.visible() {
	this.visibility = View.VISIBLE
}

fun View.invisible() {
	this.visibility = View.INVISIBLE
}