package com.example.testapplication

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class ChatActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: CardView

    private lateinit var adapter: ChatAdapter
    private val chatList = mutableListOf<ChatModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                maxOf(systemBars.bottom, imeInsets.bottom)
            )
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        setupRecyclerView()

        btnSend.setOnClickListener {

            if (etMessage.text.isEmpty()) {
                Toast.makeText(this, "Please enter a question", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                val message = etMessage.text.toString()

                addMessage(ChatModel(message,true))
                etMessage.text.clear()
                apiCall(message)
            }
        }
    }


    private fun addMessage(message: ChatModel) {

        chatList.add(message)
        adapter.notifyItemInserted(chatList.size - 1)
        recyclerView.scrollToPosition(chatList.size - 1)
    }

    private fun setupRecyclerView() {
        adapter = ChatAdapter(chatList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun apiCall(prompt: String) {

        val url = "https://api.groq.com/openai/v1/chat/completions"
        val GROQ_API_KEY = "Place_YOUR_API_KEY"

        val jsonBody = JSONObject().apply{
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role","user")
                    put("content",prompt)
                })

            })
            put("model","qwen/qwen3.8-27b")
            put("temperature",0.6)
            put("max_completion_tokens",2048)
            put("top_p",0.95)
            put("stream",false)
            put("reasoning_effort","default")
        }

        val request = object : JsonObjectRequest(
            Method.POST,
            url,
            jsonBody,
            Response.Listener{ response ->
                try{
                    val answer = response
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")

                    addMessage(ChatModel(answer,false))


                }
                catch (e: Exception){
                    addMessage(ChatModel("Error",false))
                }

        },
            Response.ErrorListener{ error ->
                addMessage(ChatModel("Error",false))

            }){

            override fun getHeaders(): MutableMap<String?, String?>? {
                return hashMapOf(
                    "Content-Type" to "application/json",
                    "Authorization" to "Bearer $GROQ_API_KEY"
                )
            }
        }
        request.retryPolicy = DefaultRetryPolicy(
            60000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        Volley.newRequestQueue(this).add(request)
    }
}