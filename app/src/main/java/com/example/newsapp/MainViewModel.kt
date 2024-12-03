import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.Articles
import com.example.example.NewsResponse
import com.example.newsapp.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    val apiService=RetrofitInstance.apiService
    private val _news = MutableStateFlow<NewsResponse?>(null)
    val news: StateFlow<NewsResponse?> get() = _news




    fun getNews(q:String) {
        viewModelScope.launch {

            try {
                _news.value=apiService.getNews(q=q, apiKey = "94b56b2444934387b5f55c8dd1150b99")

            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }
}



