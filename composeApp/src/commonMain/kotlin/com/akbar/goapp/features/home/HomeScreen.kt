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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akbar.goapp.Blue
import com.akbar.goapp.FoodRed
import com.akbar.goapp.Primary
import com.akbar.goapp.PrimaryContainer
import com.akbar.goapp.Screen
import com.akbar.goapp.Service
import com.akbar.goapp.TextMuted
import com.akbar.goapp.AppBottomBar
import com.akbar.goapp.CircleIcon
import com.akbar.goapp.IconBubble
import com.akbar.goapp.SectionTitle
import com.akbar.goapp.Chip

@Composable
fun HomeScreen(open: (Screen) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        HomeStickySearchBar(onProfile = { open(Screen.Profile) })
        LazyColumn(
            contentPadding = PaddingValues(top = 12.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { WalletSection(Modifier.padding(horizontal = 16.dp)) }
            item { ServiceGrid(open) }
            item { PromoSection() }
            item { BenefitCards() }
        }
    }
    AppBottomBar(
        selected = "Home",
        onHome = { open(Screen.Home) },
        onOrders = { open(Screen.Food) },
        onWallet = { open(Screen.Send) },
        onInbox = { open(Screen.Ride) },
        onAccount = { open(Screen.Profile) }
    )
}

@Composable
fun HomeStickySearchBar(onProfile: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .weight(1f)
                .height(48.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(999.dp))
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, null, tint = Color(0xFF687565), modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(10.dp))
            Text(
                "Cari layanan, makanan & resto",
                color = TextMuted,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.width(10.dp))
        CircleIcon(
            icon = Icons.Default.Person,
            bg = Color(0xFFE9FFF6),
            tint = Color(0xFF009B68),
            modifier = Modifier
                .size(40.dp)
                .clickable { onProfile() }
        )
    }
}

@Composable
fun WalletSection(modifier: Modifier = Modifier) {
    Row(
        modifier
            .shadow(10.dp, RoundedCornerShape(24.dp), ambientColor = PrimaryContainer.copy(alpha = 0.18f))
            .clip(RoundedCornerShape(24.dp))
            .background(PrimaryContainer)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBubble(Icons.Default.AccountBalanceWallet, Color.White.copy(alpha = 0.15f), Color.White, Modifier.size(42.dp), 22)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("Saldo GoPay", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, lineHeight = 14.sp)
            Text("Rp125.000", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black, lineHeight = 22.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Points", color = Color.White, fontSize = 11.sp, lineHeight = 13.sp)
            Text("2.450", color = Color.White, fontWeight = FontWeight.Black, fontSize = 15.sp, lineHeight = 17.sp)
        }
        Spacer(Modifier.width(10.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(999.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Primary),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 8.dp)
        ) { Text("Top Up", fontSize = 14.sp) }
    }
}

@Composable
fun ServiceGrid(open: (Screen) -> Unit) {
    val services = listOf(
        Service("GoRide", Icons.Default.DirectionsBike, Color(0xFFE9FFF6), Color(0xFF009B68), Screen.Ride),
        Service("GoCar", Icons.Default.DirectionsCar, Color(0xFFE9FFF6), Color(0xFF009B68), Screen.Ride),
        Service("GoFood", Icons.Default.Restaurant, Color(0xFFFFF0F1), FoodRed, Screen.Food),
        Service("GoSend", Icons.Default.LocalShipping, Color(0xFFE9FFF6), Color(0xFF009B68), Screen.Send),
        Service("GoMart", Icons.Default.ShoppingBasket, Color(0xFFFFF0F1), FoodRed, Screen.Mart),
        Service("GoTagihan", Icons.Default.Receipt, Color(0xFFEFF6FF), Blue, Screen.Send),
        Service("GoPlay", Icons.Default.PlayCircle, Color(0xFFFDF2F8), Color(0xFFDB2777), Screen.Food),
        Service("Lainnya", Icons.Default.GridView, Color(0xFFF3F4F6), Color(0xFF4B5563), Screen.Profile)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .height(172.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false
    ) {
        items(services) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { open(item.target) }
            ) {
                IconBubble(item.icon, item.bg, item.tint, Modifier.size(50.dp), 26)
                Spacer(Modifier.height(6.dp))
                Text(item.name, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
fun PromoSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitle("Promo yang wajib dicek", "Lihat Semua", Modifier.padding(horizontal = 16.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                PromoCard("PROMO", "Diskon s.d. 50rb di resto favorit!", "Berlaku khusus pembayaran pakai GoPay")
            }
            item {
                PromoCard("HEMAT", "Langganan makin murah", "Solusi harian untuk semua perjalanan")
            }
        }
    }
}

@Composable
fun PromoCard(tag: String, title: String, subtitle: String) {
    Card(
        Modifier.width(340.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(128.dp)
                .background(Brush.linearGradient(listOf(Color(0xFF0E3B35), Color(0xFFB45309), Color(0xFF111827))))
        ) {
            Text(
                tag,
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(14.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(FoodRed)
                    .padding(horizontal = 14.dp, vertical = 7.dp)
            )
            Text("Mie\nKriuk", color = Color.White.copy(alpha = 0.9f), fontSize = 30.sp, fontWeight = FontWeight.Black, modifier = Modifier.align(Alignment.Center))
        }
        Column(Modifier.padding(12.dp)) {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Black)
            Text(subtitle, color = Color(0xFF334155), fontSize = 14.sp)
        }
    }
}

@Composable
fun BenefitCards() {
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        BenefitCard("Asuransi", "Proteksi perjalanan & kesehatan", Blue, Modifier.weight(1f))
        BenefitCard("Membership", "Keuntungan eksklusif GoClub", Color(0xFFEAB308), Modifier.weight(1f))
    }
}

@Composable
fun BenefitCard(title: String, subtitle: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color.copy(alpha = 0.08f))
            .border(1.dp, color.copy(alpha = 0.18f), RoundedCornerShape(24.dp))
            .padding(18.dp)
    ) {
        IconBubble(Icons.Default.Star, color.copy(alpha = 0.12f), color)
        Spacer(Modifier.height(12.dp))
        Text(title, fontWeight = FontWeight.Black, fontSize = 16.sp)
        Text(subtitle, color = color.copy(alpha = 0.8f), fontSize = 14.sp)
    }
}
