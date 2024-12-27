package com.sameer.colours

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.sameer.colours.ui.theme.ColoursTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appviewmodel = ViewModelProvider(this)[AppViewModel::class.java]
        setContent {
            ColoursTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainWork(appviewmodel,applicationContext)
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainWork(appviewmodel: AppViewModel, context: Context) {

    val liveDataList = appviewmodel.list.observeAsState(emptyList()).value
    val colrlist = remember { mutableStateListOf<colors>() }
    LaunchedEffect(liveDataList) {
        colrlist.clear()
        colrlist.addAll(liveDataList.map { entity ->
            colors(colorscode = entity.ccode, date = entity.tstamp,isync = entity.insync)
        })
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Colour App")
                },
                actions = {
                    Button(modifier = Modifier.padding(end = 4.dp), onClick = {
                        Toast.makeText(context, "Syncing with Database", Toast.LENGTH_LONG).show()
                        sendColorsToFirebase(liveDataList,appviewmodel) {

                        }
                    },  colors = ButtonDefaults.buttonColors(
                        containerColor = Color(android.graphics.Color.parseColor("#FFB6B9FF")),
                    ) ) {
                        Text(
                            text =  colrlist.count { !it.isync }.toString(),
                            color = Color.White,
                            style = TextStyle(fontSize = 18.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(

                            painter = painterResource(id = R.drawable.refresh),
                            contentDescription = "Refresh",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
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

                    appviewmodel.addEntity(ModelClassEntity(0,newcol.toString(),System.currentTimeMillis().toString(),false))

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


        LazyVerticalGrid(

            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = paddingValues.calculateTopPadding()),



        ) {
            items(colrlist) { color ->
              CardCompose(color.colorscode, getdate( color.date.toLong()))

            }
        }
    }
}

fun getdate(stamp:Long): String {

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = Date(stamp)
    return dateFormat.format(date)
}


@Composable
fun CardCompose(color: String, created:String) {
    Row(


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
                    text = "#"+color, modifier = Modifier.padding(start = 3.dp), style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace
                    )
                )
                Divider(
                    modifier = Modifier
                        .width(70.dp)
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
                            fontSize = 12.sp
                        )
                    )
                    Text(
                        text = created, style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(15.dp))

    }
}
fun getnewcolor(): Any {

    val col = Random.nextInt(0xFFFFFF)
    return col.toString(16).padStart(6, '0').toUpperCase()



}
fun sendColorsToFirebase(
    colorsList: List<ModelClassEntity>,
    appviewmodel: AppViewModel,
    onCompletion: () -> Unit
) {
    val database = Firebase.database.reference.child("colors")
    colorsList.filter { !it.insync }.forEach { color ->
        val key = database.push().key ?: return@forEach
        database.child(key).setValue(color).addOnCompleteListener { task ->
            if (task.isSuccessful) {
               appviewmodel.updatestatus(color.id.toInt())
            }
        }
    }
    onCompletion()
}


