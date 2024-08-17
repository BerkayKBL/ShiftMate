package com.berkaykbl.shiftmate.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Detail(title: String, subtitle: String) {


    val colorScheme = MaterialTheme.colorScheme
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(colorScheme.primary).padding(0.dp, 2.dp),
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = colorScheme.secondary
        )
        
        if (subtitle.isNotEmpty()) {

            Text(
                text = subtitle,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = colorScheme.secondary
            )
        }
    }

}