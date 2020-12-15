package gq.trtechlesson.htmlviewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_source.*
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import kotlinx.android.synthetic.main.fragment_search_dialog.view.*


class SearchDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_search_dialog, container, false)

        rootView.search_cancelBtn.setOnClickListener {
            dismiss()

        }

//        val searchText = search_EditText.editableText.toString()
//        search_searchBtn.setOnClickListener {
//
//
//            val SourceText = arguments?.getString("SourceTextToString")
//
//
//            var replacewith = "<span style='background-color:yellow'$searchText</span>"
//
//            var modifiedSearchText = SourceText?.replace(searchText, replacewith)

//
//            val Highlightscript = (" <script language=\"javascript\">" +
//                    "function highlightSelection(){" +
//                    "var userSelection = window.getSelection();" +
//                    "for(var i = 0; i < userSelection.rangeCount; i++)"
//                    + "  highlightRange(userSelection.getRangeAt(i));" +
//                    "}" +
//                    "function highlightRange(range){" +
//                    "span = document.createElement(\"span\");" +
//                    "span.appendChild(range.extractContents());" +
//                    "span.setAttribute(\"style\",\"display:block;background:#ffc570;\");" +
//                    "range.insertNode(span);}" +
//                    "</script> ")
//
//            sourceView.loadUrl("javascript:highlightSelection()");
//            sourceView.text(Html.fromHtml(modifiedSearchText))

//        }

        return rootView
    }


}
