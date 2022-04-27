
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        button.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        openMainActivity()

    }

    private fun openMainActivity() {
        val intent = Intent(this, EchecActivity::class.java)
        startActivity(intent)

    }
}
