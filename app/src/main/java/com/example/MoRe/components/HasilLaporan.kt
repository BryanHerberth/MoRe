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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.network.model.res.laporan.DataLaporan
import com.example.MoRe.network.model.res.laporan.LaporanByName
import com.example.MoRe.network.model.res.laporan.ResLaporanByName
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@ExperimentalFoundationApi
@Composable
fun HasilTampilkan(
    laporan: List<DaftarLaporan> = getDataLaporan(),
    resLaporan: List<LaporanByName>
){
    Log.d("Hasil Laporan Mesin : ", resLaporan.toString())
//    Card(modifier = Modifier
//        .padding(4.dp)
//        .fillMaxWidth(),
//    ) {
        Surface(modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            ) {
            Column(
                modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(text = "Nomor",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                    }
//                    Spacer(modifier = Modifier.weight(0.04f))
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
//                    Spacer(modifier = Modifier.padding(horizontal = 20.dp))

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
//                LazyColumn{
//                    val laporanSort = resLaporan.groupBy { it.nomor}
//
//                    laporanSort.forEach { (nomor, timestamp ) ->
//                        stickyHeader {
//                        }
//                        items(items = timestamp){
//                            CardViewLaporan(resLaporan = it)
//                        }
//                    }
////                    val laporanSort = laporan.groupBy { it.Nomor}
////
////                    laporanSort.forEach { (Nomor, Tanggal ) ->
////                        stickyHeader {
////                        }
////                        items(items = Tanggal){
////                            CardViewLaporan(laporan = it )
////                        }
////                    }
//
//                }

                val yStep = 50
                val random = Random
                /* to test with random points */
//                val points = (0..9).map {
//                    var num = random.nextInt(350)
//                    if (num <= 50)
//                        num += 100
//                    num.toFloat()
//                }
                /* to test with fixed points */
                val points = listOf(
                    100f,150f,200f,250f,300f,350f,400f)
                Graph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    xValues = (0..10).map { it + 1 },
                    yValues = (0..9).map { (it + 1) * yStep },
                    points = points,
                    paddingSpace = 16.dp,
                    verticalStep = yStep
                )
            }
        }
    }
//}



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
//    fun dateToTimeStamp(date: String){
//        val myDate = SimpleDateFormat("dd-MM-yyyy").parse("2-08-2022")
//        Log.d("TimeStamp End : ", Timestamp(year = 2022))
//    }



    val dateEndPickerDialog = DatePickerDialog(
        context, { d, year2, month2, day2 ->
            val endMonth = month2 + 1
            eDate.value = "$day2-$endMonth-$year2"
            SessionManager.saveStopDate(eDate.value)
            Log.d("EndDate Picker : ", eDate.value)
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
                    Log.d("EndDatePick date", eDate.value)
//                    dateToTimeStamp(eDate.value)
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

@Composable
fun Graph(
    modifier : Modifier,
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    paddingSpace: Dp,
    verticalStep: Int
) {
    val controlPoints1 = mutableListOf<PointF>()
    val controlPoints2 = mutableListOf<PointF>()
    val coordinates = mutableListOf<PointF>()
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 15.dp, vertical = 12.dp)
            .height(250.dp),
        contentAlignment = Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val xAxisSpace = (size.width - paddingSpace.toPx()) / xValues.size
            val yAxisSpace = size.height / yValues.size
            /** placing x axis points */
            /** placing x axis points */
            /** placing x axis points */
            /** placing x axis points */
            for (i in xValues.indices) {
                drawContext.canvas.nativeCanvas.drawText(
                    "${xValues[i]}",
                    xAxisSpace * (i + 1),
                    size.height - 30,
                    textPaint
                )
            }
            /** placing y axis points */
            /** placing y axis points */
            /** placing y axis points */
            /** placing y axis points */
            for (i in yValues.indices) {
                drawContext.canvas.nativeCanvas.drawText(
                    "${yValues[i]}",
                    paddingSpace.toPx() / 2f,
                    size.height - yAxisSpace * (i + 1),
                    textPaint
                )
            }
            /** placing our x axis points */
            /** placing our x axis points */
            /** placing our x axis points */
            /** placing our x axis points */
            for (i in points.indices) {
                val x1 = xAxisSpace * xValues[i]
                val y1 = size.height - (yAxisSpace * (points[i]/verticalStep.toFloat()))
                coordinates.add(PointF(x1,y1))
                /** drawing circles to indicate all the points */
                /** drawing circles to indicate all the points */
                /** drawing circles to indicate all the points */
                /** drawing circles to indicate all the points */
                drawCircle(
                    color = Color.Red,
                    radius = 10f,
                    center = Offset(x1,y1)
                )
            }
            /** calculating the connection points */
            /** calculating the connection points */
            /** calculating the connection points */
            /** calculating the connection points */
            for (i in 1 until coordinates.size) {
                controlPoints1.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i - 1].y))
                controlPoints2.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i].y))
            }
            /** drawing the path */
            /** drawing the path */
            /** drawing the path */
            /** drawing the path */
            val stroke = Path().apply {
                reset()
                moveTo(coordinates.first().x, coordinates.first().y)
                for (i in 0 until coordinates.size - 1) {
                    cubicTo(
                        controlPoints1[i].x,controlPoints1[i].y,
                        controlPoints2[i].x,controlPoints2[i].y,
                        coordinates[i + 1].x,coordinates[i + 1].y
                    )
                }
            }
            /** filling the area under the path */
            /** filling the area under the path */
            /** filling the area under the path */
            /** filling the area under the path */
            val fillPath = android.graphics.Path(stroke.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(xAxisSpace * xValues.last(), size.height - yAxisSpace)
                    lineTo(xAxisSpace, size.height - yAxisSpace)
                    close()
                }
            drawPath(
                fillPath,
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Cyan,
                        Color.Transparent,
                    ),
                    endY = size.height - yAxisSpace
                ),
            )
            drawPath(
                stroke,
                color = Color.Black,
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round
                )
            )
        }
    }
}