package androidquiz.app.ui.play

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.play_layout.view.*
import androidquiz.app.R
import androidquiz.app.data.database.entity.Question
import androidx.annotation.RequiresApi

class QuestionAdapter(
    private val itemClicked: (position: Int, optionSelected: Int) -> Unit,
    private val updateAnswerTrigger: (position: Int, optionSelected: Int) -> Unit,
    private val quitClicked: () -> Unit,
    private val context: Context
) : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(CALLBACK) {
    private lateinit var animation: ObjectAnimator

    //Question Image
    val questionImages = arrayListOf<String>(
        "https://upload.wikimedia.org/wikipedia/commons/b/b8/Android.JPG",
        "https://i0.wp.com/elysiumacademy.org/wp-content/uploads/2018/12/Android.jpg",
        "https://img.republicworld.com/republic-prod/stories/promolarge/xhdpi/4z0sp8gejp5jindl_1620731998.jpeg?tr=w-1200,h-900",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTq-TNZyd4IYKP6UfJq38jhx44ywo4gWB6v8Q&usqp=CAU",
        "https://sociable.co/wp-content/uploads/2018/07/android-app-1280x720.jpg",
        "https://files.betamax.raywenderlich.com/attachments/collections/127/467591fe-b1b1-4ceb-af4d-543a54163d01.png",
        "https://cdn-images-1.medium.com/max/1200/1*3tLD4Ve66pbBpuawm9Fu9Q.png",
        "https://icons.iconarchive.com/icons/papirus-team/papirus-apps/512/android-sdk-icon.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm3-87D91aeMhMLxoZoed3OsTHpEztwN0DGjkdnCUPkTs2K34Yskj07M4Wa6bZ4B3Bl_g&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZz-0m2aU_Oj8E1oUb4N40UOMuPJBs7avgo-W1g6p8VYdbLKvWT3KnH9afc9p_xOOhBzs&usqp=CAU",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_rEWaP2zy5s4hKJdadB_h2ENC3wCcwBXB_pgg60GlbUWUFSCXy5JvIagKVEMeDehOHtQ&usqp=CAU",
        "https://files.betamax.raywenderlich.com/attachments/collections/127/467591fe-b1b1-4ceb-af4d-543a54163d01.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8Uv3qdIURg57pz7HMqiQZq9CWzhK8Xx4epyEqG5GEElcg0ke_U0L8Nr6JHnd0Wz_oBZk&usqp=CAU",
        "https://d1jnx9ba8s6j9r.cloudfront.net/blog/wp-content/uploads/2019/08/Kotlin-Andriod-Tutorial-PNG.png",
        "https://pbs.twimg.com/profile_images/1395420331922231301/lEFdJlvQ_400x400.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMwWfKzzq7AxU-AcpbvQyC0eA2j8RhrlFJbiV8_I1PQf_txNImkWMrxYFU9ZcTQJIcXrs&usqp=CAU"
    )
    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<Question>() {
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.question == newItem.question
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.play_layout,
                parent,
                false
            )
        )
    }

    private fun optionClicked(
        tvOption: TextView,
        tvOptionName: TextView,
        optionCard: CardView,
        selectedOption: Int,
        correctAnswer: Int,
        position: Int
    ) {
        optionCard.isEnabled = false
        optionCard.isClickable = false
        tvOption.setTextColor(context.getColor(R.color.white))
        tvOptionName.setTextColor(context.getColor(R.color.white))
        if (correctAnswer == selectedOption)
            optionCard.backgroundTintList =
                ColorStateList.valueOf(context.getColor(R.color.lightGreen))
        else
            optionCard.backgroundTintList =
                ColorStateList.valueOf(context.getColor(R.color.darkRed1))

        updateAnswerTrigger(position, selectedOption)
        itemClicked(position, selectedOption)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val question = getItem(position)

        //Progress Bar
        animation = ObjectAnimator.ofInt(holder.pbTimer, "progress", 0, 500);
        animation.duration = 5000
        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                itemClicked(position, -1)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        animation.start()

        //Question Number
        holder.questionNoList[position].setBackgroundResource(R.drawable.circle_solid)
        holder.questionNoList[position].backgroundTintList =
            ColorStateList.valueOf(context.getColor(R.color.yellow))
        holder.questionNoList[position].setTextColor(context.getColor(R.color.white))

        //Question
        holder.tvQuestion.text = question.question
        holder.ivQuestionImage.visibility = View.VISIBLE
        Glide.with(context).load(questionImages[position]).placeholder(R.drawable.placeholder)
            .dontAnimate()
            .into(holder.ivQuestionImage)

        //Options
        holder.tvOption1.text = question.answers[0]
        holder.tvOption2.text = question.answers[1]
        holder.tvOption3.text = question.answers[2]
        holder.tvOption4.text = question.answers[3]

        holder.tvOption1.setOnClickListener {
            optionClicked(
                holder.tvOption1,
                holder.tvA,
                holder.cardOption1,
                0,
                question.correct,
                position
            )
            animation.cancel()
        }
        holder.tvOption2.setOnClickListener {
            optionClicked(
                holder.tvOption2,
                holder.tvB,
                holder.cardOption2,
                1,
                question.correct,
                position
            )
            animation.cancel()
        }
        holder.tvOption3.setOnClickListener {
            optionClicked(
                holder.tvOption3,
                holder.tvC,
                holder.cardOption3,
                2,
                question.correct,
                position
            )
            animation.cancel()
        }
        holder.tvOption4.setOnClickListener {
            optionClicked(
                holder.tvOption4,
                holder.tvD,
                holder.cardOption4,
                3,
                question.correct,
                position
            )
            animation.cancel()
        }

        //Quit Button
        holder.btnQuit.setOnClickListener {
            quitClicked()
        }
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pbTimer = itemView.pbTimer!!
        val tvQuestion = itemView.tvQuestion!!
        val ivQuestionImage = itemView.ivQuestionImage!!
        val cardOption1: CardView = itemView.card_option1!!
        val cardOption2: CardView = itemView.card_option2!!
        val cardOption3: CardView = itemView.card_option3!!
        val cardOption4: CardView = itemView.card_option4!!
        val tvA = itemView.tv_a!!
        val tvB = itemView.tv_b!!
        val tvC = itemView.tv_c!!
        val tvD = itemView.tv_d!!
        val tvOption1 = itemView.tv_option1!!
        val tvOption2 = itemView.tv_option2!!
        val tvOption3 = itemView.tv_option3!!
        val tvOption4 = itemView.tv_option4!!
        val btnQuit = itemView.btn_quit!!
        val questionNoList = listOf<TextView>(
            itemView.tv_i1!!,
            itemView.tv_i2!!,
            itemView.tv_i3!!,
            itemView.tv_i4!!,
            itemView.tv_i5!!,
            itemView.tv_i6!!,
            itemView.tv_i7!!,
            itemView.tv_i8!!,
            itemView.tv_i9!!,
            itemView.tv_i10!!,
            itemView.tv_i11!!,
            itemView.tv_i12!!,
            itemView.tv_i13!!,
            itemView.tv_i14!!,
            itemView.tv_i15!!
        )
    }
}