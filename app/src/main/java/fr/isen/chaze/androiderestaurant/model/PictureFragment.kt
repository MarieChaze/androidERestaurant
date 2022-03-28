package fr.isen.chaze.androiderestaurant.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.chaze.androiderestaurant.R
import fr.isen.chaze.androiderestaurant.databinding.FragmentPictureBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PICTURE = "picture"



class PictureFragment : Fragment() {

    private var picture: String? = null
    private lateinit var binding: FragmentPictureBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            picture = it.getString(ARG_PICTURE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(picture?.ifEmpty { null }).placeholder(R.drawable.ic_launcher_foreground).into(binding.pictureView)
    }

    companion object {

        @JvmStatic
        fun newInstance(picture: String) =
            PictureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PICTURE, picture)
                }
            }
    }
}