package com.example.MoRe.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.MoRe.DokumenLayout
import com.example.MoRe.LaporanLayouts
import com.example.MoRe.PemantauanLayout
import com.example.MoRe.dpToPx
import com.example.MoRe.model.LineChartEntity
import com.example.MoRe.network.model.res.laporan.*
import com.example.MoRe.ui.theme.*
import com.google.accompanist.pager.*
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun HasilTampilkan(
    laporan: List<DaftarLaporan> = getDataLaporan(),
    resLaporan: List<LaporanByName>,
    resChart: List<lapo>
){
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Log.d("Hasil Laporan Mesin : ", resLaporan.toString())
    Log.d("Hasil Laporan Mesin chart : ", resChart.toString())

        Surface(modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            ) {
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                LaporanTabs(pagerState = pagerState, scope = coroutineScope)
                Spacer(modifier = Modifier.height(20.dp))
                LaporanTabsContent(
                    pagerState = pagerState,
                    resLaporan = resLaporan,
                    resChart = resChart
                )
            }
            }
        }

//}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TableView(
    laporan: List<DaftarLaporan> = getDataLaporan(),
    resLaporan: List<LaporanByName>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Nomor",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Waktu Pencatatan",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(horizontal = 5.dp)

            ) {
                Text(
                    text = "Hasil",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        LazyColumn {
            val laporanSort = resLaporan.groupBy { it.nomor }

            laporanSort.forEach { (nomor, timestamp) ->
                stickyHeader {
                }
                items(items = timestamp) {
                    CardViewLaporan(resLaporan = it)
                }
            }

        }
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun LaporanTabsContent(
    pagerState: PagerState,
    laporan: List<DaftarLaporan> = getDataLaporan(),
    resLaporan: List<LaporanByName>,
    resChart: List<lapo>
) {
    HorizontalPager(count = 2,
        state = pagerState,
    ) { page ->
        when (page){
            0 -> {
                TableView(resLaporan = resLaporan)
            }

            1 -> {
                graphView(resChart = resChart)
            }
        }
    }
}

@Composable
fun DateStartPickerLayout(){

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val context = LocalContext.current

    var date =  remember {
        mutableStateOf("")
    }


    val datePickerDialog = DatePickerDialog(
        context, { d, year1 , month1 , day1 ->
            val month = month1 + 1
            date.value = "$day1-$month-$year1"
            SessionManager.saveStartDate(date.value)
        }, year, month, day
    )
    TextField(
        value = "${date.value}",
        onValueChange = {

        },
        label ={ Text(text = "Start Date") } ,
        readOnly = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    datePickerDialog.show()
                }
            ) {
                Image(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "DropDownArrow")
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            trailingIconColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.LightGray)
            )
            .clickable {
                datePickerDialog.show()
            },
                enabled = false

    )
}

@Composable
fun DateEndPickerLayout() {


    val eCalendar = Calendar.getInstance()
    val eYear = eCalendar.get(Calendar.YEAR)
    val eMonth = eCalendar.get(Calendar.MONTH)
    val eDay = eCalendar.get(Calendar.DAY_OF_MONTH)

    val context = LocalContext.current

    var eDate = remember {
        mutableStateOf("")
    }



    val dateEndPickerDialog = DatePickerDialog(
        context, { d, year2, month2, day2 ->
            val endMonth = month2 + 1
            eDate.value = "$day2-$endMonth-$year2"
            SessionManager.saveStopDate(eDate.value)
        }, eYear, eMonth, eDay
    )


    TextField(
        value = "${eDate.value}",
        onValueChange = { },
        label = { Text(text = "End Date") },
        readOnly = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    dateEndPickerDialog.show()
                }
            ) {
                Image(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = "DropDownArrow"
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            trailingIconColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.LightGray)
            )
            .clickable {
                dateEndPickerDialog.show()
            },
        enabled = false
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun LaporanTabs(pagerState: PagerState,
         scope: CoroutineScope
) {
    val list = listOf(
        "ChartView",
        "TableView"
    )
    val coroutineScope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        TabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            backgroundColor = Color.Transparent,
            divider = { TabRowDefaults.Divider(color = Color.LightGray) },
            indicator = {
                    tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState,tabPositions),
                    color = BlueApp
                )
            }
        ) {
            list.forEachIndexed { index, s ->
                val selected = pagerState.currentPage == index

                Tab(selected = selected, onClick = {

                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                    enabled = true
                ) {
                    Text(text = list[index],
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = if ( selected) BlueApp
                        else Color.LightGray,
                        modifier = Modifier.padding (vertical = 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun graphView(
    resChart: List<lapo>
){
    var lineChartData2 = listOf<LineChartEntity>()
    for(index in resChart){
        lineChartData2 = lineChartData2 + LineChartEntity(index.value.toFloat(), index.nomor)
        Log.d("Loop Chart", index.toString())
    }
    Log.d("lineChartData2 : : ", lineChartData2.toString())


    val verticalAxisValues2 = listOf(100.0f, 200.0f, 300.0f, 400.0f, 500.0f, 600.0f, 700.0f, 800.0f, 900.0f, 1000.0f, 1100.0f, 1200.0f, 1300.0f, 1400.0f, 1500.0f, 1600.0f, 1700.0f, 1800.0f, 1900.0f, 2000.0f, 2100.0f, 2200.0f, 2300.0f, 2400.0f, 2500.0f, 2600.0f, 2700.0f, 2800.0f)

    LineChart(lineChartData = lineChartData2, verticalAxisValues = verticalAxisValues2, resChart=resChart)

}




@Composable
fun LineChart(
    modifier: Modifier? = Modifier
        .padding(top = 16.dp, bottom = 16.dp),
    lineChartData: List<LineChartEntity>,
    resChart: List<lapo>,
    verticalAxisValues: List<Float>,
    axisColor: Color = DefaultAxisColor,
    horizontalAxisLabelColor: Color = DefaultAxisLabelColor,
    horizontalAxisLabelFontSize: TextUnit = DefaultAxisLabelFontSize,
    verticalAxisLabelColor: Color = DefaultAxisLabelColor,
    verticalAxisLabelFontSize: TextUnit = DefaultAxisLabelFontSize,
    isShowVerticalAxis: Boolean = false,
    isShowHorizontalLines: Boolean = true,
    strokeWidth: Dp = 4.dp,
    lineColor: Color = Color.Blue,

) {

    val strokeWidthPx = dpToPx(strokeWidth)
    val axisThicknessPx = dpToPx(DefaultAxisThickness)

    Canvas(
        modifier = modifier!!
            .aspectRatio(1f)
            .horizontalScroll(rememberScrollState())
            .width(2000.dp)
            .height(300.dp)
    ) {

        val bottomAreaHeight = horizontalAxisLabelFontSize.toPx()
        val leftAreaWidth =
            (verticalAxisValues[verticalAxisValues.size - 1].toString().length * verticalAxisLabelFontSize.toPx().div(1.75)).toInt()

        val verticalAxisLength = (size.height - bottomAreaHeight)
        val horizontalAxisLength = size.width - leftAreaWidth

        val distanceBetweenVerticalAxisValues = (verticalAxisLength / (verticalAxisValues.size - 1))

        // Draw horizontal axis
        if (isShowHorizontalLines.not())
            drawRect(
                color = axisColor,
                topLeft = Offset(leftAreaWidth.toFloat(), verticalAxisLength),
                size = Size(horizontalAxisLength, axisThicknessPx)
            )

        // Draw vertical axis
        if (isShowVerticalAxis)
            drawRect(
                color = axisColor,
                topLeft = Offset(leftAreaWidth.toFloat(), 0.2f),
                size = Size(axisThicknessPx, verticalAxisLength)
            )

        // Draw vertical axis values & horizontal lines
        for (index in verticalAxisValues.indices) {

            val x = (leftAreaWidth / 2).toFloat()
            val y = verticalAxisLength - (distanceBetweenVerticalAxisValues).times(index)

            // Draw vertical axis value
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    verticalAxisValues[index].toString(),
                    x,
                    y + verticalAxisLabelFontSize.toPx() / 2,
                    Paint().apply {
                        textSize = verticalAxisLabelFontSize.toPx()
                        color = verticalAxisLabelColor.toArgb()
                        textAlign = Paint.Align.CENTER
                    }
                )
            }

            // Draw horizontal line
            if (isShowHorizontalLines)
                drawRect(
                    color = axisColor,
                    topLeft = Offset(leftAreaWidth.toFloat(), y),
                    size = Size(horizontalAxisLength, axisThicknessPx)
                )
        }

        // Draw lines and it's labels
        val barWidth =
            (drawContext.size.width - leftAreaWidth) / lineChartData.size

        val maxAxisValue = verticalAxisValues[verticalAxisValues.size - 1]

        var previousOffset: Offset? = null

        for (index in lineChartData.indices) {
            val entity = lineChartData[index]

            // Draw line
            val currentOffset = calculateOffset(
                entity.value,
                index,
                maxAxisValue,
                barWidth,
                leftAreaWidth,
                verticalAxisLength
            )

            val end = Offset(currentOffset.x + barWidth.div(2), currentOffset.y)

            drawCircle(
                color = lineColor,
                center = end,
                radius = strokeWidthPx.times(1.5f)
            )

            if (previousOffset != null) {
                val start = Offset(previousOffset.x + barWidth.div(2), previousOffset.y)
                drawLine(
                    start = start,
                    end = end,
                    color = lineColor,
                    strokeWidth = strokeWidthPx
                )
            }

            previousOffset = currentOffset

            // Draw horizontal axis label
            if (lineChartData[index].label?.isNotEmpty() == true) {
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        lineChartData[index].label!!,
                        currentOffset.x + barWidth.div(2),
                        verticalAxisLength + horizontalAxisLabelFontSize.toPx(),
                        Paint().apply {
                            textSize = bottomAreaHeight
                            color = horizontalAxisLabelColor.toArgb()
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }
    }
}

private fun calculateOffset(
    value: Float,
    index: Int,
    maxAxisValue: Float,
    barWidth: Float,
    leftAreaWidth: Int,
    verticalAxisLength: Float
): Offset {
    var x = barWidth * index
    x += leftAreaWidth

    val barHeightPercentage = (value / maxAxisValue)
    val barHeightInPixel = barHeightPercentage * verticalAxisLength
    val y = verticalAxisLength - barHeightInPixel

    return Offset(x, y)
}

