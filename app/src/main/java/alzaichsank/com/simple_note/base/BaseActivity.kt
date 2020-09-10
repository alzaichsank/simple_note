package alzaichsank.com.simple_note.base

import alzaichsank.com.simple_note.R
import alzaichsank.com.simple_note.utils.LocalizationHelper
import android.app.ProgressDialog
import android.content.Context
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {
    
    protected var loading: ProgressDialog? = null
    protected val compositeDisposable = CompositeDisposable()

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalizationHelper.applyLanguageContext(newBase))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        /*if (id == android.R.id.home) {
            super.onBackPressed()
            return true
        }*/
        return super.onOptionsItemSelected(item)
    }

    protected fun setToolbar(
        toolbar: Toolbar,
        toolbarTitle: String,
        onBackPress: (() -> Unit)? = null
    ) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = toolbarTitle
        toolbar.setNavigationOnClickListener {
            if (onBackPress != null) onBackPress.invoke() else onBackPressed()
        }
    }

    protected fun showLoadingModal(messageToShow: String) {
        loading = ProgressDialog.show(
            this,
            getString(R.string.progress_loading),
            messageToShow,
            false,
            false
        )
        if (loading?.isShowing != true) {
            loading?.show()
        }
    }

    protected fun hideLoadingModal() {
        if (loading != null) {
            if (loading?.isShowing == true) {
                loading?.dismiss()
                loading = null
            }
        }
    }
}