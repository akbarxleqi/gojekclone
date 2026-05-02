package com.akbar.goapp

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Chip(label: String, selected: Boolean = false) {
    Text(
        label,
        color = if (selected) Primary else Color(0xFF243123),
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) Color(0xFFDFF8E8) else Color(0xFFEFECEC))
            .border(if (selected) 1.dp else 0.dp, if (selected) Color(0xFFB8E5C2) else Color.Transparent, RoundedCornerShape(999.dp))
            .padding(horizontal = 18.dp, vertical = 12.dp)
    )
}

@Composable
fun SectionTitle(title: String, action: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
        Text(action, color = Primary, fontSize = 16.sp)
    }
}

@Composable
fun PrimaryButton(text: String, modifier: Modifier = Modifier.fillMaxWidth().height(58.dp), onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = Color.White)
    ) {
        Text(text, fontSize = 17.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
fun IconBubble(icon: ImageVector, bg: Color, tint: Color, modifier: Modifier = Modifier.size(46.dp), iconSize: Int = 24) {
    Box(modifier.clip(RoundedCornerShape(16.dp)).background(bg), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(iconSize.dp))
    }
}

@Composable
fun CircleIcon(icon: ImageVector, bg: Color, tint: Color, modifier: Modifier = Modifier.size(42.dp)) {
    Box(modifier.clip(CircleShape).background(bg), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(26.dp))
    }
}

@Composable
fun LabelChip(text: String, bg: Color, tint: Color) {
    Text(text, color = tint, fontSize = 11.sp, fontWeight = FontWeight.Black, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(bg).padding(horizontal = 10.dp, vertical = 5.dp))
}

@Composable
fun Avatar(modifier: Modifier = Modifier.size(48.dp), large: Boolean = false) {
    Box(
        modifier
            .clip(CircleShape)
            .background(Brush.linearGradient(listOf(Color(0xFFE2E8F0), Color(0xFF0F766E))))
            .border(if (large) 4.dp else 0.dp, Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text("A", color = Color.White, fontWeight = FontWeight.Black, fontSize = if (large) 38.sp else 22.sp)
    }
}

@Composable
fun SimpleTopBar(title: String, leading: ImageVector, trailing: ImageVector?, topPadding: androidx.compose.ui.unit.Dp, onBack: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = topPadding, start = 16.dp, end = 16.dp, bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(leading, null, tint = if (title.startsWith("Go")) Color(0xFF009B68) else TextDark, modifier = Modifier.clickable { onBack() }.size(28.dp))
        Spacer(Modifier.width(18.dp))
        Text(title, color = if (title.startsWith("Go")) Color(0xFF009B68) else TextDark, fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
        trailing?.let { Icon(it, null, tint = if (title == "Profile") Color(0xFF009B68) else TextMuted, modifier = Modifier.size(28.dp)) }
    }
}

@Composable
fun SearchPill(placeholder: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(999.dp))
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = Color(0xFF687565), modifier = Modifier.size(28.dp))
        Spacer(Modifier.width(14.dp))
        Text(placeholder, color = TextMuted, fontSize = 16.sp)
    }
}

@Composable
fun AppBottomBar(
    selected: String,
    onHome: () -> Unit,
    onPromos: () -> Unit,
    onOrders: () -> Unit,
    onChat: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomItem("Beranda", Icons.Outlined.Home, selected == "Beranda", onHome)
            BottomItem("Promo", Icons.Outlined.ConfirmationNumber, selected == "Promo", onPromos)
            BottomItem("Aktivitas", Icons.Outlined.ReceiptLong, selected == "Aktivitas", onOrders)
            BottomItem("Chat", Icons.Outlined.ChatBubbleOutline, selected == "Chat", onChat)
        }
    }
}

@Composable
fun BottomItem(label: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    Column(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null, tint = if (selected) Color(0xFF009B68) else Color(0xFF98A2B3), modifier = Modifier.size(24.dp))
        Text(label, color = if (selected) Color(0xFF009B68) else Color(0xFF98A2B3), fontSize = 10.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium)
    }
}

@Composable
fun FloatingCartBar(left: String, subtitle: String, right: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .shadow(14.dp, RoundedCornerShape(16.dp), ambientColor = PrimaryContainer.copy(alpha = 0.24f))
            .clip(RoundedCornerShape(16.dp))
            .background(PrimaryContainer)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.ShoppingBasket, null, tint = Color.White, modifier = Modifier.size(28.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(left, color = Color.White, fontWeight = FontWeight.Black, fontSize = 17.sp)
            Text(subtitle, color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
        }
        Text(right, color = Color.White, fontWeight = FontWeight.Black, fontSize = 17.sp)
        Icon(Icons.Default.KeyboardArrowRight, null, tint = Color.White)
    }
}

@Composable
fun FoodImage(modifier: Modifier, label: String) {
    val palette = when {
        label.contains("Pizza", true) -> listOf(Color(0xFF20110D), Color(0xFFE85D04), Color(0xFFFFD166))
        label.contains("Burger", true) -> listOf(Color(0xFF1F160E), Color(0xFF8B4513), Color(0xFFFDBA74))
        label.contains("Sayur", true) || label.contains("Brokoli", true) -> listOf(Color(0xFF0F5132), Color(0xFF22C55E), Color(0xFFB7E4C7))
        label.contains("Susu", true) -> listOf(Color(0xFFA7F3D0), Color.White, Color(0xFF93C5FD))
        else -> listOf(Color(0xFF101827), Color(0xFF76542A), Color(0xFFEA580C))
    }
    Box(modifier.clip(RoundedCornerShape(10.dp)).background(Brush.linearGradient(palette)), contentAlignment = Alignment.Center) {
        Text(label.take(10), color = Color.White, fontWeight = FontWeight.Black, textAlign = TextAlign.Center)
    }
}

@Composable
fun ProductRowSection(title: String, productList: List<Product>, redPrice: Boolean, modifier: Modifier = Modifier) {
    Column(modifier) {
        SectionTitle(title, "Lihat Semua", Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(12.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(productList) { ProductCard(it, redPrice) }
        }
    }
}

@Composable
fun ProductCard(item: Product, redPrice: Boolean) {
    Card(Modifier.width(160.dp), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(2.dp)) {
        Box {
            FoodImage(Modifier.fillMaxWidth().height(126.dp), item.title)
            if (item.badge != null) {
                Text(item.badge, color = Color.White, fontWeight = FontWeight.Black, modifier = Modifier.padding(8.dp).clip(RoundedCornerShape(999.dp)).background(FoodRed).padding(horizontal = 10.dp, vertical = 5.dp))
            }
        }
        Column(Modifier.padding(12.dp)) {
            Text(item.title, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(8.dp))
            Text(item.price, color = if (redPrice) FoodRed else Primary, fontWeight = FontWeight.Black, fontSize = 16.sp)
            if (item.oldPrice != null) Text(item.oldPrice, color = TextMuted, fontSize = 12.sp, textDecoration = TextDecoration.LineThrough)
        }
    }
}

@Composable
fun RestaurantRow(title: String, meta: String, chip1: String, chip2: String?) {
    Row(Modifier.clip(RoundedCornerShape(18.dp)).background(Color.White).border(1.dp, SurfaceLine, RoundedCornerShape(18.dp)).padding(16.dp)) {
        FoodImage(Modifier.size(92.dp), title)
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Black, fontSize = 17.sp, maxLines = 2)
            Text(meta, color = Color(0xFF334155), fontSize = 14.sp)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                LabelChip(chip1, Color(0xFFD7FBE3), Primary)
                if (chip2 != null) LabelChip(chip2, Color(0xFFE0ECFF), Blue)
            }
        }
    }
}

@Composable
fun GreenBanner(title: String, subtitle: String) {
    Row(
        Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(14.dp)).background(PrimaryContainer).padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Black, fontSize = 16.sp)
            Text(subtitle, color = Color.White, fontSize = 15.sp)
        }
        Icon(Icons.Default.Star, null, tint = Color.White, modifier = Modifier.size(30.dp))
    }
}

@Composable
fun BenefitMini(text: String, icon: ImageVector, bg: Color, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(18.dp)).background(bg).padding(18.dp)) {
        Icon(icon, null, tint = Primary)
        Spacer(Modifier.height(16.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun PaymentFooter(label: String, price: String, action: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .background(Color.White)
                .padding(16.dp)
                .navigationBarsPadding()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccountBalanceWallet, null, tint = PrimaryContainer)
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(label, color = TextMuted, fontSize = 12.sp)
                    Text(price, fontSize = 20.sp, fontWeight = FontWeight.Black)
                }
                LabelChip("GOPAY", Color(0xFFE1F8E8), Primary)
            }
            Spacer(Modifier.height(14.dp))
            PrimaryButton(action)
        }
    }
}

@Composable
fun MapPreview() {
    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF0E3B35), Color(0xFFE2E8F0), Color(0xFF8EC5B5))))
            .padding(16.dp)
    ) {
        Text("Estimasi 4.2 km", modifier = Modifier.align(Alignment.BottomStart).clip(RoundedCornerShape(999.dp)).background(Color.White.copy(alpha = 0.9f)).padding(horizontal = 14.dp, vertical = 8.dp))
    }
}
