package alzaichsank.com.simple_note.base

import alzaichsank.com.simple_note.R
import android.app.ProgressDialog
import android.content.Context
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    protected var loading: ProgressDialog? = null

    protected val compositeDisposable = CompositeDisposable()

    protected fun showLoadingModal(context: Context?, messageToShow: String) {
        loading = ProgressDialog.show(
            context,
            getString(R.string.progress_loading),
            messageToShow,
            false,
            false
        )
        loading?.show()
    }

    protected fun hideLoadingModal() {
        if (loading != null) {
            if (loading?.isShowing == true) {
                loading?.dismiss()
                loading = null
            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}