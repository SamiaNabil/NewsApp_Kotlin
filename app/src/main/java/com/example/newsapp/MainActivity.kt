
package com.example.newsapp

import MainViewModel
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.example.Articles
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.ai.client.generativeai.type.content

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = MainViewModel()
        setContent {
            val news = viewModel.news.collectAsState()
            val q = remember { mutableStateOf("") }

            NewsAppTheme {
                viewModel.getNews(q.value)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Column {
                        TextField(value = q.value, onValueChange = {
                            q.value = it
                        })

                        LazyColumn {
                            items(news.value?.articles ?: emptyList()) { article ->
                                // Assuming 'title' exists in GameResponse

                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier.padding(16.dp)) {

                                        AsyncImage(
                                            model = article.urlToImage,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                            contentScale = ContentScale.FillWidth
                                        )

                                        Text(
                                            text = article.content ?: "",
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}







