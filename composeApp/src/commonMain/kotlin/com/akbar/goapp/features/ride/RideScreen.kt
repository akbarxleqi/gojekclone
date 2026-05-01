package com.akbar.goapp.features.ride

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.SurfaceLow
import com.akbar.goapp.TextMuted
import com.akbar.goapp.CircleIcon
import com.akbar.goapp.IconBubble
import com.akbar.goapp.PrimaryButton
import com.akbar.goapp.SimpleTopBar

@Composable
fun RideScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.White)) {
        Column(Modifier.fillMaxSize()) {
            SimpleTopBar("GoApp", leading = Icons.Default.ArrowBack, trailing = null, topPadding = 0.dp) { open(Screen.Home) }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(224.dp)
                    .background(Brush.verticalGradient(listOf(Color(0xFF173336), Color(0xFFEFF5EA))))
            ) {
                RouteCard(Modifier.align(Alignment.TopCenter).padding(16.dp))
                Text("Jl. Raya Mayong", color = Color(0xFFC40018), fontSize = 48.sp, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 44.dp))
            }
        }
        RideSheet(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun RouteCard(modifier: Modifier = Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, SurfaceLine, RoundedCornerShape(16.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RouteLine("Pelemkerep", Primary, Icons.Default.Place)
        HorizontalDivider(color = SurfaceLine, modifier = Modifier.padding(start = 32.dp))
        RouteLine("Jl. Raya Mayong", FoodRed, Icons.Default.LocationOn)
    }
}

@Composable
fun RouteLine(text: String, color: Color, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = color, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(16.dp))
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Icon(Icons.Default.Edit, null, tint = Color(0xFF98A2B3), modifier = Modifier.size(20.dp))
    }
}

@Composable
fun RideSheet(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White)
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Box(Modifier.size(width = 48.dp, height = 5.dp).clip(RoundedCornerShape(999.dp)).background(SurfaceLine))
        }
        Row(Modifier.fillMaxWidth().padding(top = 22.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Text("Buat kamu", color = Primary, fontSize = 16.sp, fontWeight = FontWeight.Black)
            Text("Hemat", color = TextMuted, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Ekstra", color = TextMuted, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(18.dp))
        RideOption("GoRide", "14.58 - Voucher hemat 2rb", "Rp14.000", selected = true, Icons.Default.DirectionsBike)
        RideOption("GoRide Comfort", "15.02 - Motor lebih baru", "Rp18.000", selected = false, Icons.Default.DirectionsBike)
        RideOption("GoCar", "15.05 - Maks. 4 orang", "Rp24.000", selected = false, Icons.Default.DirectionsCar)
        HorizontalDivider(color = SurfaceLine, modifier = Modifier.padding(vertical = 14.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("GOPAY", color = Color.White, fontWeight = FontWeight.Black, modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Blue).padding(horizontal = 10.dp, vertical = 5.dp))
            Spacer(Modifier.width(12.dp))
            Text("Rp48.200", fontSize = 21.sp, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
            CircleIcon(Icons.Default.KeyboardArrowRight, SurfaceLow, TextMuted)
        }
        Spacer(Modifier.height(18.dp))
        PrimaryButton("Cari driver")
    }
}

@Composable
fun RideOption(title: String, desc: String, price: String, selected: Boolean, icon: ImageVector) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(if (selected) Color(0xFFF0FFF8) else Color.White)
            .border(if (selected) 2.dp else 0.dp, if (selected) Color(0xFFC8F4DB) else Color.Transparent, RoundedCornerShape(18.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBubble(icon, if (selected) Color.White else SurfaceLow, if (selected) Primary else TextMuted, Modifier.size(62.dp), 32)
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Black)
            Text(desc, color = if (selected) Primary else TextMuted, fontSize = 15.sp)
        }
        Text(price, fontSize = 17.sp, fontWeight = FontWeight.Black)
    }
}
