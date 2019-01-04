package es.iessaladillo.pedrojoya.pr211.views

import android.content.Context
import android.graphics.Canvas
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import es.iessaladillo.pedrojoya.pr211.R

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ClickToSelectEditText : AppCompatEditText {

    private val mHint: CharSequence
    private var onItemSelectedListener: OnItemSelectedListener? = null
    private var mSpinnerAdapter: ListAdapter? = null

    constructor(context: Context) : super(context) {
        mHint = hint
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupAttributes(context, attrs)
        mHint = hint
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupAttributes(context, attrs)
        mHint = hint
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet) {
        // Obtain a typed array of attributes
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable
                .ClickToSelectEditText, 0,
                0)
        // Extract custom attributes into member variables
        try {
            val entriesResId = a.getResourceId(R.styleable.ClickToSelectEditText_entries, 0)
            if (entriesResId != 0) {
                setAdapter(ArrayAdapter.createFromResource(context,
                        entriesResId, android.R.layout.simple_list_item_1))
            }
        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        isFocusable = true
        isClickable = true
        // User can't type text.
        inputType = InputType.TYPE_NULL
        keyListener = null
    }

    fun setAdapter(adapter: ListAdapter) {
        mSpinnerAdapter = adapter
        setupOnClickListener()
    }

    fun getAdapter() = mSpinnerAdapter

    private fun setupOnClickListener() {
        setOnClickListener { this.showDialog(it) }
        val focusListener = onFocusChangeListener
        setOnFocusChangeListener { view, hasFocus ->
            focusListener?.onFocusChange(view, hasFocus)
            if (hasFocus) {
                showDialog(view)
            }
        }
    }

    fun showDialog(view: View) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle(mHint)
        builder.setAdapter(mSpinnerAdapter) { dialogInterface, selectedIndex ->
            setText(mSpinnerAdapter!!.getItem(selectedIndex).toString())
            if (onItemSelectedListener != null) {
                onItemSelectedListener!!.onItemSelectedListener(
                        mSpinnerAdapter!!.getItem(selectedIndex), selectedIndex)
            }
        }
        builder.setPositiveButton(android.R.string.cancel, null)
        builder.create().show()
    }

    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    interface OnItemSelectedListener {
        fun onItemSelectedListener(item: Any, selectedIndex: Int)
    }

}
