package com.akbar.goapp.features.home

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.Screen
import com.akbar.goapp.SectionTitle
import com.akbar.goapp.SurfaceLine
import com.akbar.goapp.TextMuted
import com.akbar.goapp.CircleIcon
import com.akbar.goapp.IconBubble

@Composable
fun HomeScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.White)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {
            item { TopGreenPromoArea() }
            item { GoPayFloatingBar() }
            item { ServiceGrid(open) }
            item { 
                SectionTitle("Promo yang wajib dicek", "Lihat Semua", Modifier.padding(horizontal = 16.dp))
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item { GojekPromoBanner("MURA-AAAH", "Diskon 72rb\nkode: GAJIAN", Color(0xFF00AA13)) }
                    item { GojekPromoBanner("PROMO", "Makan Enak\nDiskon 50%", FoodRed) }
                }
            }
            item { 
                SectionTitle("Resto dengan rating jempolan", "Lihat Semua", Modifier.padding(horizontal = 16.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(trendingRestos) { resto ->
                        GojekRestoCard(resto)
                    }
                }
            }
            item { MainFeaturedCard() }
        }

        // Sticky Search Header - Tetap di atas
        StickySearchHeader(onProfile = { open(Screen.Profile) })
        
        AppBottomBar(
            selected = "Beranda",
            onHome = { open(Screen.Home) },
            onPromos = { open(Screen.Promos) },
            onOrders = { open(Screen.Orders) },
            onChat = { open(Screen.Chat) }
        )
    }
}

@Composable
fun StickySearchHeader(onProfile: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF00AA13)) // Background hijau sticky
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(Color.White)
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(10.dp))
                Text("Ayam Goreng...", color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(Modifier.width(12.dp))
            CircleIcon(Icons.Default.Person, Color.White, Color(0xFF00AA13), Modifier.size(38.dp).clickable { onProfile() })
        }
    }
}

@Composable
fun TopGreenPromoArea() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF00AA13))
            .padding(top = 80.dp) // Gap for sticky header
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Box(
            Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text("MURAAAH", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
        Spacer(Modifier.height(8.dp))
        Text("Diskon\ns.d. 40 RB", color = Color.White, fontWeight = FontWeight.Black, fontSize = 28.sp, lineHeight = 32.sp)
        Text("kode promo GOWEEKEND", color = Color.White, fontSize = 13.sp)
        Spacer(Modifier.height(40.dp)) // Space for overlapping bar
    }
}

@Composable
fun GoPayFloatingBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(64.dp) // Sedikit lebih tinggi agar shadow tidak terpotong
    ) {
        // Latar belakang hijau (setengah bagian atas)
        Box(Modifier.fillMaxWidth().height(32.dp).background(Color(0xFF00AA13)))
        
        // Bar GoPay Putih
        Row(
            Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bagian Saldo
            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0081A0)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.AccountBalanceWallet, null, tint = Color.White, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Rp0", fontWeight = FontWeight.Black, fontSize = 14.sp, lineHeight = 16.sp)
                    Text("0 coins", color = Color(0xFF64748B), fontSize = 11.sp, lineHeight = 13.sp)
                }
            }
            
            // Bagian Aksi
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                GopaySmallAction(Icons.Default.ArrowUpward, "Bayar")
                GopaySmallAction(Icons.Default.History, "Riwayat")
                GopaySmallAction(Icons.Default.MoreHoriz, "Lainnya")
            }
        }
    }
}

@Composable
fun GopaySmallAction(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = Color(0xFF0081A0), modifier = Modifier.size(20.dp))
        Spacer(Modifier.height(2.dp))
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Black, color = Color(0xFF1E293B))
    }
}

@Composable
fun ServiceGrid(open: (Screen) -> Unit) {
    val services = listOf(
        GojekService("GoRide", Icons.Default.DirectionsBike, Color(0xFF00AA13), "MURAH!", Screen.Ride),
        GojekService("GoCar", Icons.Default.DirectionsCar, Color(0xFF00AA13), "-30rb", Screen.Ride),
        GojekService("GoFood", Icons.Default.Restaurant, FoodRed, "-70%", Screen.Food),
        GojekService("GoSend", Icons.Default.LocalShipping, Color(0xFF00AA13), "5RB!", Screen.Send),
        GojekService("GoMart", Icons.Default.ShoppingBasket, FoodRed, "30MINS", Screen.Mart),
        GojekService("GoPay Pinjam", Icons.Default.Payments, Blue, "35JUTA", Screen.Food),
        GojekService("Group Order", Icons.Default.Person, FoodRed, "Baru", Screen.Food),
        GojekService("Lainnya", Icons.Default.GridView, Color.Gray, null, Screen.Profile)
    )
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false
    ) {
        items(services) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { open(item.target) }
            ) {
                Box {
                    IconBubble(item.icon, item.color.copy(alpha = 0.1f), item.color, Modifier.size(52.dp), 26)
                    if (item.badge != null) {
                        Text(
                            item.badge,
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .offset(y = (-6).dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.Black)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text(item.name, fontSize = 12.sp, fontWeight = FontWeight.Medium, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }
    }
}

data class GojekService(val name: String, val icon: ImageVector, val color: Color, val badge: String?, val target: Screen)

@Composable
fun GojekRestoCard(resto: HomeResto) {
    Column(Modifier.width(180.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(resto.imageColor)
        ) {
            if (resto.promo != null) {
                Text(
                    resto.promo,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Red)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(resto.name, fontWeight = FontWeight.Black, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, null, tint = Color(0xFFEAB308), modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            Text(resto.rating, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Spacer(Modifier.width(8.dp))
            Text("${resto.distance} • ${resto.time}", color = TextMuted, fontSize = 12.sp)
        }
    }
}

@Composable
fun GojekPromoBanner(tag: String, title: String, color: Color) {
    Box(
        Modifier
            .width(320.dp)
            .height(170.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .padding(20.dp)
    ) {
        Column {
            Box(Modifier.clip(RoundedCornerShape(4.dp)).background(Color.White).padding(horizontal = 6.dp, vertical = 2.dp)) {
                Text(tag, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.Black, fontSize = 24.sp, lineHeight = 28.sp)
        }
    }
}

@Composable
fun MainFeaturedCard() {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, SurfaceLine, RoundedCornerShape(20.dp))
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF00AA13)),
            contentAlignment = Alignment.Center
        ) {
            Text("GoFood", color = Color.White.copy(0.2f), fontSize = 60.sp, fontWeight = FontWeight.Black)
            Text("Pilihan Terlaris", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black)
        }
        Column(Modifier.padding(20.dp)) {
            Text("Mau cobain Gojek pake promo?", fontWeight = FontWeight.Black, fontSize = 17.sp)
            Text("Yuk cari tahu cara pakenya! Gampang banget lho~", color = TextMuted, fontSize = 14.sp)
            Spacer(Modifier.height(16.dp))
            Text("Cek Sekarang", color = Primary, fontWeight = FontWeight.Black, fontSize = 15.sp)
        }
    }
}

val trendingRestos = listOf(
    HomeResto("Kedai Pak Zainuri, Margoyoso Kaliny...", "4.7", "7.24 km", "25-35 min", "15% off", "Ayam", Color(0xFFFDE68A)),
    HomeResto("Bebek Goreng H. Slamet, Margoyoso", "4.8", "6.70 km", "25-35 min", "40% off", "Bebek", Color(0xFFFED7AA))
)

data class HomeResto(
    val name: String,
    val rating: String,
    val distance: String,
    val time: String,
    val promo: String? = null,
    val category: String,
    val imageColor: Color
)
