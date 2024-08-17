package com.berkaykbl.shiftmate.presentation.menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItem(icon: Int, title: String, clickCallback: () -> Unit) {

    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(colorScheme.secondary)
            .clickable {
                clickCallback()
            }
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = colorScheme.primary,
            modifier = Modifier.size(45.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            color = colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}