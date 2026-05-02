package com.akbar.goapp.features.promo

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.Blue
import com.akbar.goapp.Orange
import com.akbar.goapp.Primary
import com.akbar.goapp.Screen
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.TextMuted

@Composable
fun PromoScreen(open: (Screen) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        PromoHeader()
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { PromoSummaryCard() }
            item { VoucherCategories() }
            item { FeaturedPromos() }
        }
    }

    AppBottomBar(
        selected = "Promo",
        onHome = { open(Screen.Home) },
        onPromos = { open(Screen.Promos) },
        onOrders = { open(Screen.Orders) },
        onChat = { open(Screen.Chat) }
    )
}

@Composable
fun PromoHeader() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp)
    ) {
        Text("Promo", fontSize = 24.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
fun PromoSummaryCard() {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, SurfaceLine, RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, null, tint = Orange, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("128 XP lagi buat jadi Kyuubi", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            Box(Modifier.fillMaxWidth(0.8f).height(6.dp).clip(CircleShape).background(Color(0xFFF1F5F9))) {
                Box(Modifier.fillMaxWidth(0.6f).fillMaxSize().background(Orange))
            }
        }
        Icon(Icons.Default.ChevronRight, null, tint = TextMuted)
    }
}

@Composable
fun VoucherCategories() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        VoucherBox("Voucher", "12", Icons.Default.ConfirmationNumber, Color(0xFFFFF4E6), Orange, Modifier.weight(1f))
        VoucherBox("Langganan", "3", Icons.Default.CardGiftcard, Color(0xFFE6F7FF), Blue, Modifier.weight(1f))
        VoucherBox("Misi", "2", Icons.Default.Star, Color(0xFFE9FFF6), Primary, Modifier.weight(1f))
    }
}

@Composable
fun VoucherBox(title: String, count: String, icon: androidx.compose.ui.graphics.vector.ImageVector, bg: Color, tint: Color, modifier: Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .padding(12.dp)
    ) {
        Box(Modifier.size(32.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text(count, fontWeight = FontWeight.Black, fontSize = 18.sp)
        Text(title, color = TextMuted, fontSize = 13.sp)
    }
}

@Composable
fun FeaturedPromos() {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text("Promo Menarik Buat Kamu", fontWeight = FontWeight.Black, fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))
        PromoBanner("Makan enak diskon 50%", "Pesan di GoFood sekarang!", Color(0xFFE32326))
        Spacer(Modifier.height(12.dp))
        PromoBanner("Jalan-jalan hemat 30%", "Gunakan GoRide untuk bepergian.", Primary)
    }
}

@Composable
fun PromoBanner(title: String, desc: String, color: Color) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.6f))))
            .padding(20.dp)
    ) {
        Column(Modifier.align(Alignment.CenterStart)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Black, fontSize = 20.sp)
            Text(desc, color = Color.White.copy(alpha = 0.9f), fontSize = 15.sp)
        }
    }
}
