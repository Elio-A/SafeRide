package ca.unb.mobiledev.saferide

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgView: ImageView = itemView.findViewById(R.id.img)
    val stationTextView: TextView = itemView.findViewById(R.id.station)
    val headlineTextView: TextView = itemView.findViewById(R.id.headline)
    val statusTextView: TextView = itemView.findViewById(R.id.status)
    val headline2TextView: TextView = itemView.findViewById(R.id.headline2)
    val answerTextView: TextView = itemView.findViewById(R.id.answer)
    val licenseTextView: TextView = itemView.findViewById(R.id.license)

    fun bind(image: Int, headline:String, stationName: String, status: String, seats: Int, waitTime: Int, license: String) {
        imgView.setImageResource(image)
        stationTextView.text = stationName
        headlineTextView.text = headline
        statusTextView.text = status

        val colorResId = when (status) {
            "Available" -> R.color.green
            "On Delivery" -> R.color.red
            else -> R.color.white
        }
        statusTextView.setTextColor(ContextCompat.getColor(itemView.context, colorResId))

        if(status == "Available"){
            headline2TextView.text = "Seats Available:"
            answerTextView.text = seats.toString()
        }
        else{
            headline2TextView.text = "Wait time:"
            answerTextView.text = "${waitTime} min"
        }
        licenseTextView.text = license
    }
}
