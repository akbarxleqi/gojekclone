package com.akbar.goapp.features.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.TextMuted

data class OrderItem(
    val type: String,
    val title: String,
    val date: String,
    val price: String,
    val status: String,
    val icon: ImageVector,
    val tint: Color
)

val historyOrders = listOf(
    OrderItem("GoFood", "Ayam Bakar Pak Kumis", "2 Mei, 12:30", "Rp45.000", "Selesai", Icons.Default.Restaurant, FoodRed),
    OrderItem("GoRide", "Stasiun Gambir", "1 Mei, 08:15", "Rp12.000", "Selesai", Icons.Default.DirectionsBike, Primary),
    OrderItem("GoFood", "Kopi Kenangan - Thamrin", "30 Apr, 15:20", "Rp28.500", "Dibatalkan", Icons.Default.Restaurant, FoodRed),
    OrderItem("GoRide", "Grand Indonesia", "29 Apr, 18:45", "Rp15.000", "Selesai", Icons.Default.DirectionsBike, Primary),
    OrderItem("GoFood", "Martabak Pecenongan", "28 Apr, 21:00", "Rp65.000", "Selesai", Icons.Default.Restaurant, FoodRed)
)

@Composable
fun OrderScreen(open: (Screen) -> Unit) {
    var selectedTab by remember { mutableStateOf("Riwayat") }

    Column(Modifier.fillMaxSize()) {
        OrderHeader()
        OrderTabs(selectedTab) { selectedTab = it }
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            if (selectedTab == "Riwayat") {
                items(historyOrders) { order ->
                    OrderCard(order)
                }
            } else {
                item {
                    EmptyOngoing()
                }
            }
        }
    }

    AppBottomBar(
        selected = "Aktivitas",
        onHome = { open(Screen.Home) },
        onPromos = { open(Screen.Promos) },
        onOrders = { open(Screen.Orders) },
        onChat = { open(Screen.Chat) }
    )
}

@Composable
fun OrderHeader() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Pesanan", fontSize = 24.sp, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
            Icon(Icons.Default.Help, null, tint = Color(0xFF687565), modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun OrderTabs(selected: String, onSelect: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        listOf("Riwayat", "Dalam Proses", "Terjadwal").forEach { tab ->
            val isSelected = selected == tab
            Column(
                Modifier
                    .weight(1f)
                    .clickable { onSelect(tab) }
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    tab,
                    color = if (isSelected) Primary else TextMuted,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    Modifier
                        .height(3.dp)
                        .fillMaxWidth(0.6f)
                        .background(if (isSelected) Primary else Color.Transparent, RoundedCornerShape(99.dp))
                )
            }
        }
    }
}

@Composable
fun OrderCard(order: OrderItem) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(order.tint.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(order.icon, null, tint = order.tint, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(order.type, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Spacer(Modifier.width(8.dp))
                    Box(Modifier.size(3.dp).clip(CircleShape).background(TextMuted))
                    Spacer(Modifier.width(8.dp))
                    Text(order.date, color = TextMuted, fontSize = 13.sp)
                }
                Text(order.title, fontWeight = FontWeight.Black, fontSize = 17.sp, maxLines = 1)
            }
            Text(
                order.status,
                color = if (order.status == "Dibatalkan") Color.Red else Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .background(if (order.status == "Dibatalkan") Color.Red.copy(0.1f) else Color(0xFFE8F5E9))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
        
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(order.price, fontWeight = FontWeight.Black, fontSize = 16.sp, modifier = Modifier.weight(1f))
            
            Row(
                Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .border(1.dp, SurfaceLine, RoundedCornerShape(99.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.History, null, tint = Primary, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Pesan lagi", color = Primary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(Modifier.width(12.dp))
            Icon(Icons.Default.MoreHoriz, null, tint = TextMuted)
        }
        
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(color = SurfaceLine)
    }
}

@Composable
fun EmptyOngoing() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Receipt, null, tint = TextMuted, modifier = Modifier.size(80.dp).alpha(0.2f))
        Spacer(Modifier.height(24.dp))
        Text("Belum ada pesanan aktif", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("Pesanan yang sedang diproses akan muncul di sini.", color = TextMuted, textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.padding(horizontal = 40.dp))
    }
}

