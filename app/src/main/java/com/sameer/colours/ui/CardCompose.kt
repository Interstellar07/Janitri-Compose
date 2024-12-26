package com.sameer.colours.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CardCompose (){
    Card (modifier = Modifier
        .height(100.dp)
        .width(100.dp), shape = RoundedCornerShape(5.dp) ,colors = CardDefaults.cardColors(
        containerColor = Color.Cyan

    ) ){

         Column (modifier = Modifier.padding(start = 2.dp, top = 2.dp)){

             Text(text = "#FF38745", style = TextStyle(
                 color = Color.White,
                 fontFamily = FontFamily.Monospace


             )

             )
             Divider(
                 modifier = Modifier.width(80.dp), // Add some space between text and line
                 color = Color.White, // Line color
                 thickness = 1.dp // Line thickness
             )


         }
        Box (modifier = Modifier.fillMaxSize()){
            Column (modifier = Modifier.align(Alignment.BottomEnd)){
                Text(text = "Created at", style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp


                ))
                Text(text = "12/05/2023", style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp


                ))
            }
        }



    }
}