package bhanu.ktor_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.ContentType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var httpClient:HttpClient
    private val base_url = "https://reqres.in/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        httpClient = HttpClient()
    }

    override fun onResume() {
        super.onResume()
        getRequest()
        postRequest()
        patchRequest()
    }
    private fun getRequest(){

        runBlocking {
            withContext(Dispatchers.IO){
                try {
                    val response = httpClient.get<String>("${base_url}users?page=2"){
                        header("Authorization","Auth key here")
                    }
                    Log.d("Http","get request response is:$response")
                } catch (e: Exception) {
                    Log.e("Http","Exception:${e.message}")
                }
            }
        }
    }
    private fun postRequest(){
        runBlocking {
            withContext(Dispatchers.IO){
                try {
                    val response = httpClient.post<String>(base_url+"users"){
                        header("Authorization","Auth key here")
                        body = JSONObject().apply {
                            put("name","bhanu")
                            put("job","developer")
                        }.toString()
                    }
                    Log.d("Http","post request response is:$response")
                } catch (e: Exception) {
                    Log.e("Http","Exception:${e.message}")
                }
            }
        }
    }
    private fun patchRequest(){
        runBlocking {
            withContext(Dispatchers.IO){
                try {
                    val response = httpClient.patch<String>(base_url+"users/2"){
                        header("Authorization","Auth key here")
                        body = JSONObject().apply {
                            put("name","bhanu")
                            put("job","developer")
                        }.toString()
                    }
                    Log.d("Http","patch request response is:$response")
                } catch (e: Exception) {
                    Log.e("Http","Exception:${e.message}")
                }
            }
        }
    }
}
