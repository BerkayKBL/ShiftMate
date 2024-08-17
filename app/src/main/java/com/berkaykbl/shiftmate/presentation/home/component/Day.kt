package com.berkaykbl.shiftmate.presentation.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.berkaykbl.shiftmate.presentation.model.DayModel
import com.berkaykbl.shiftmate.presentation.ui.theme.currentDay
import com.berkaykbl.shiftmate.presentation.ui.theme.gray
import com.berkaykbl.shiftmate.presentation.ui.theme.selectedDay
import com.berkaykbl.shiftmate.util.leaveTypes


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Day(dayModel: DayModel, clickCallback: () -> Unit) {

    val colorScheme = MaterialTheme.colorScheme

    val shiftText = if (dayModel.shiftType == "overtime") {
        if (
            dayModel.shiftHour == 0
        ) "" else "${dayModel.shiftHour} hour"
    } else if (dayModel.shiftType.isNotEmpty() && leaveTypes.containsKey(dayModel.shiftType)) {
        stringResource(
            id = leaveTypes.get(dayModel.shiftType) ?: 0
        )
    } else {
        ""
    }

    val textColor = if (dayModel.isSelected) colorScheme.secondary
    else if (!dayModel.nationalHoliday.isNullOrEmpty()) Color.Red
    else colorScheme.tertiary
    Box(
        modifier = Modifier
            .padding(1.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        Box(
            modifier = Modifier
                .offset((-14).dp, (-13).dp)
                .rotate(45f)
                .size(25.dp)
                .zIndex(3f)
                .background(if (dayModel.isCurrent) currentDay else Color.Transparent),
            content = {})

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (dayModel.isSelected) selectedDay else gray)
                .clickable {
                    if (dayModel.clickable) {
                        clickCallback()
                    }
                },
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {

            Text(
                text = dayModel.text,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 18.sp,
                color = textColor
            )

            Text(
                text = shiftText,
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 12.sp,
                color = textColor,
                softWrap = false
            )

            Text(
                text = dayModel.nationalHoliday ?: "",
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp,
                color = textColor,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}