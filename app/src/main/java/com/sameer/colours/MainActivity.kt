package com.sameer.colours

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sameer.colours.ui.theme.ColoursTheme
import com.sameer.colours.ui.theme.colors
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColoursTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainWork()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainWork() {
    var colrlist = remember { mutableStateListOf(colors("D7415F"),
        colors("E4AAFF"),
        colors("7FC3E9"),
        colors("ECA02F")) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Colour App")
                },
                actions = {
                    IconButton(onClick = { /* Handle search click */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF5659A4),
                    titleContentColor = Color.White
                )
            )
        },
                floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          val newcol = getnewcolor();
                    println(""+newcol+"----------------------------")
                    colrlist.add(colors(newcol.toString()))
                },
                containerColor = Color(android.graphics.Color.parseColor("#FFB6B9FF")),
            ) {
                Row(
                    modifier = Modifier.padding(start = 10.dp,end =10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)

                ) {

                    Text(
                        text = "Add Colour",
                        color = Color(android.graphics.Color.parseColor("#FF5659A4")),
                        style = TextStyle(fontSize = 15.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                    )
                    Image(

                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add Image",
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                }
            }

        }
    ) { paddingValues ->

        val cardColor = colrlist
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(cardColor) { card ->
                CardCompose(card.colorscode)
            }
        }
    }
}

fun getnewcolor(): Any {

        val col = Random.nextInt(0xFFFFFF)
        return col.toString(16).padStart(6, '0').toUpperCase()

}

@Composable
fun CardCompose(color: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(bottom = 5.dp, top = 5.dp)
                .height(120.dp)
                .width(180.dp), shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(android.graphics.Color.parseColor("#FF" + color))
            )
        ) {
            Column(modifier = Modifier.padding(start = 2.dp, top = 2.dp)) {
                Text(
                    text = "#FF38745", modifier = Modifier.padding(start = 3.dp), style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
                Divider(
                    modifier = Modifier
                        .width(80.dp)
                        .padding(start = 3.dp),
                    color = Color.White,
                    thickness = 1.dp
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(end = 5.dp, bottom = 5.dp)) {
                Column(modifier = Modifier.align(Alignment.BottomEnd)) {
                    Text(
                        text = "Created at", style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "12/05/2023", style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(15.dp))
        Card(
            modifier = Modifier
                .padding(bottom = 5.dp, top = 5.dp)
                .height(120.dp)
                .width(180.dp), shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(android.graphics.Color.parseColor("#FF" + color))
            )
        ) {
            Column(modifier = Modifier.padding(start = 2.dp, top = 2.dp)) {
                Text(
                    text = "#FF38745", modifier = Modifier.padding(start = 3.dp), style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
                Divider(
                    modifier = Modifier
                        .width(80.dp)
                        .padding(start = 3.dp),
                    color = Color.White,
                    thickness = 1.dp
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(end = 5.dp, bottom = 5.dp)) {
                Column(modifier = Modifier.align(Alignment.BottomEnd)) {
                    Text(
                        text = "Created at", style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "12/05/2023", style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}
