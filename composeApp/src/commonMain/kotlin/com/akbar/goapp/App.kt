package com.akbar.goapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

private val Primary = Color(0xFF006E08)
private val PrimaryContainer = Color(0xFF00AA13)
private val FoodRed = Color(0xFFE32326)
private val SurfaceBg = Color(0xFFFCF8F8)
private val SurfaceLow = Color(0xFFF6F3F2)
private val SurfaceLine = Color(0xFFE5E2E1)
private val TextDark = Color(0xFF1C1B1B)
private val TextMuted = Color(0xFF667085)
private val Blue = Color(0xFF1E63F2)
private val Orange = Color(0xFFD97706)

private enum class Screen(val label: String) {
    Home("Home"),
    Food("GoFood"),
    Ride("Ride"),
    Send("GoSend"),
    Mart("GoMart"),
    Resto("Resto"),
    Profile("Profile"),
    Login("Login"),
    Register("Register")
}

@Composable
fun App() {
    var screen by remember { mutableStateOf(Screen.Home) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Primary,
            primaryContainer = PrimaryContainer,
            secondary = FoodRed,
            background = SurfaceBg,
            surface = Color.White,
            onSurface = TextDark
        )
    ) {
        BoxWithConstraints(
            Modifier
                .fillMaxSize()
                .background(SurfaceBg),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 430.dp),
                color = SurfaceBg
            ) {
                Box(Modifier.fillMaxSize()) {
                    when (screen) {
                        Screen.Home -> HomeScreen { screen = it }
                        Screen.Food -> FoodScreen { screen = it }
                        Screen.Ride -> RideScreen { screen = it }
                        Screen.Send -> SendScreen { screen = it }
                        Screen.Mart -> MartScreen { screen = it }
                        Screen.Resto -> RestaurantDetailScreen { screen = it }
                        Screen.Profile -> ProfileScreen { screen = it }
                        Screen.Login -> LoginScreen { screen = it }
                        Screen.Register -> RegisterScreen { screen = it }
                    }

                }
            }
        }
    }
}

@Composable
private fun HomeScreen(open: (Screen) -> Unit) {
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
private fun HomeStickySearchBar(onProfile: () -> Unit, modifier: Modifier = Modifier) {
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
private fun DestinationChips() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Mau pergi ke mana?", fontSize = 21.sp, fontWeight = FontWeight.ExtraBold)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Chip("Rumah", selected = true)
            Chip("Kantor")
            Chip("Setel tujuan")
        }
    }
}

@Composable
private fun WalletSection(modifier: Modifier = Modifier) {
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
private fun ServiceGrid(open: (Screen) -> Unit) {
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
private fun PromoSection() {
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
private fun PromoCard(tag: String, title: String, subtitle: String) {
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
private fun BenefitCards() {
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        BenefitCard("Asuransi", "Proteksi perjalanan & kesehatan", Blue, Modifier.weight(1f))
        BenefitCard("Membership", "Keuntungan eksklusif GoClub", Color(0xFFEAB308), Modifier.weight(1f))
    }
}

@Composable
private fun BenefitCard(title: String, subtitle: String, color: Color, modifier: Modifier = Modifier) {
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

@Composable
private fun FoodScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(bottom = 176.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            item { FoodHeader { open(Screen.Home) } }
            item { FoodCategories() }
            item { FlashSaleSection() }
            item { RestaurantList() }
        }
        FloatingCartBar("3 items", "Dari 2 Resto", "Rp81.000", Modifier.align(Alignment.BottomCenter).padding(bottom = 82.dp, start = 16.dp, end = 16.dp))
        AppBottomBar(selected = "Home", onHome = { open(Screen.Home) }, onOrders = {}, onWallet = {}, onInbox = {}, onAccount = { open(Screen.Profile) })
    }
}

@Composable
private fun FoodHeader(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(FoodRed)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleIcon(Icons.Default.ArrowBack, Color.White.copy(alpha = 0.18f), Color.White, Modifier.clickable { onBack() })
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("GoFood", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Black)
                Text("Delivering to Home", color = Color.White.copy(alpha = 0.85f), fontWeight = FontWeight.SemiBold)
            }
            CircleIcon(Icons.Default.Favorite, Color.White.copy(alpha = 0.12f), Color.White)
            Spacer(Modifier.width(10.dp))
            CircleIcon(Icons.Default.Search, Color.White.copy(alpha = 0.12f), Color.White)
        }
        SearchPill("Mau makan apa hari ini?", Icons.Default.Restaurant)
    }
}

@Composable
private fun FoodCategories() {
    val cats = listOf(
        Service("Resto\nTerdekat", Icons.Default.Restaurant, Color(0xFFFFF1F2), FoodRed, Screen.Food),
        Service("Ongkir\nMurah", Icons.Default.LocalShipping, Color(0xFFFFF7ED), Color(0xFFEA580C), Screen.Food),
        Service("Rating Tinggi", Icons.Default.Star, Color(0xFFFEFCE8), Color(0xFFCA8A04), Screen.Food),
        Service("Promo Gajian", Icons.Default.Star, Color(0xFFEFF6FF), Blue, Screen.Food)
    )
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        cats.forEach {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(84.dp)) {
                IconBubble(it.icon, it.bg, it.tint, Modifier.size(56.dp), 28)
                Spacer(Modifier.height(6.dp))
                Text(it.name, textAlign = TextAlign.Center, fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun FlashSaleSection() {
    Column {
        Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Flash Sale Gajian", fontSize = 22.sp, fontWeight = FontWeight.Black)
                Text("Hanya hari ini, buruan sikat!", color = Color(0xFF334155), fontSize = 16.sp)
            }
            Text(
                "02:45:12",
                color = Color.White,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color(0xFFC30012))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            )
        }
        Spacer(Modifier.height(14.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            items(foodDeals) { item -> ProductCard(item, redPrice = true) }
        }
    }
}

@Composable
private fun RestaurantList() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Resto Terpopuler", fontSize = 22.sp, fontWeight = FontWeight.Black)
        RestaurantRow("Steakhouse Premium Central", "4.8 - 1.2km - 25 mnt", "PROMO", "FREE DELIVERY")
        RestaurantRow("Pasta Brava Italian", "4.6 - 0.8km - 15 mnt", "PROMO", null)
    }
}

@Composable
private fun RideScreen(open: (Screen) -> Unit) {
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
private fun RouteCard(modifier: Modifier = Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, SurfaceLine, RoundedCornerShape(16.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RouteLine("Pelemkerep", Primary, Icons.Default.Place)
        Divider(color = SurfaceLine, modifier = Modifier.padding(start = 32.dp))
        RouteLine("Jl. Raya Mayong", FoodRed, Icons.Default.LocationOn)
    }
}

@Composable
private fun RouteLine(text: String, color: Color, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = color, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(16.dp))
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Icon(Icons.Default.Edit, null, tint = Color(0xFF98A2B3), modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun RideSheet(modifier: Modifier = Modifier) {
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
        Divider(color = SurfaceLine, modifier = Modifier.padding(vertical = 14.dp))
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
private fun RideOption(title: String, desc: String, price: String, selected: Boolean, icon: ImageVector) {
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

@Composable
private fun SendScreen(open: (Screen) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(bottom = 124.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { SimpleTopBar("GoSend", Icons.Default.ArrowBack, Icons.Default.Help, topPadding = 0.dp) { open(Screen.Home) } }
        item { SendAddressCard() }
        item { FormSection() }
        item { SendServiceSection() }
        item { GreenBanner("Hemat Rp7.000 pakai GoPay!", "Promo otomatis terpasang untukmu.") }
        item { MapPreview() }
    }
    PaymentFooter("Total Pembayaran", "Rp15.000", "Pesan GoSend")
}

@Composable
private fun SendAddressCard() {
    Column(
        Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(14.dp)).background(Color.White).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AddressLine("Titik Jemput", "Apartemen Green Pramuka City, Tower F", "Jakarta Pusat, DKI Jakarta", Primary)
        Divider(color = SurfaceLine, modifier = Modifier.padding(start = 30.dp))
        AddressLine("Titik Antar", "Sudirman Central Business District (SCBD)", "Kebayoran Baru, Jakarta Selatan", FoodRed)
    }
}

@Composable
private fun AddressLine(label: String, title: String, subtitle: String, color: Color) {
    Row {
        Icon(Icons.Default.LocationOn, null, tint = color)
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color(0xFF334155))
            Text(title, fontSize = 17.sp, fontWeight = FontWeight.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(subtitle, fontSize = 14.sp, color = Color(0xFF334155))
        }
    }
}

@Composable
private fun FormSection() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Detail Paket", fontWeight = FontWeight.Bold)
        FormCard("Detail barang", "Contoh: Dokumen, Makanan", Icons.Default.ShoppingBasket)
        FormCard("Berat barang", "< 20kg", Icons.Default.ShoppingBasket, trailing = Icons.Default.KeyboardArrowDown)
        FormCard("Instruksi pengiriman", "Titip di resepsionis / telpon saat", Icons.Default.Edit)
    }
}

@Composable
private fun FormCard(label: String, value: String, icon: ImageVector, trailing: ImageVector? = null) {
    Column(Modifier.clip(RoundedCornerShape(14.dp)).background(Color.White).padding(16.dp)) {
        Text(label, fontSize = 12.sp, color = Color(0xFF334155))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Color(0xFF687565))
            Spacer(Modifier.width(10.dp))
            Text(value, color = TextMuted, fontSize = 17.sp, modifier = Modifier.weight(1f))
            trailing?.let { Icon(it, null, tint = TextMuted) }
        }
        Divider(color = SurfaceLine, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
private fun SendServiceSection() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionTitle("Pilih Layanan", "Lihat Detail")
        ServiceChoice("Instant", "TERCEPAT", "Tiba dalam 1-2 jam", "Rp15.000", "Rp22.000", true)
        ServiceChoice("Same Day", null, "Tiba dalam 6-8 jam", "Rp10.000", null, false)
    }
}

@Composable
private fun ServiceChoice(title: String, badge: String?, desc: String, price: String, oldPrice: String?, selected: Boolean) {
    Row(
        Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(if (selected) 2.dp else 0.dp, if (selected) PrimaryContainer else Color.Transparent, RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBubble(if (selected) Icons.Default.Check else Icons.Default.Star, if (selected) Color(0xFFE9FFF6) else Color(0xFFEFF6FF), if (selected) Primary else Color(0xFF007A98))
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, fontWeight = FontWeight.Black, fontSize = 17.sp)
                if (badge != null) {
                    Spacer(Modifier.width(8.dp))
                    Text(badge, color = Primary, fontSize = 10.sp, fontWeight = FontWeight.Black, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(Color(0xFF78F26D)).padding(horizontal = 8.dp, vertical = 2.dp))
                }
            }
            Text(desc, color = Color(0xFF334155), fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(price, fontWeight = FontWeight.Black, fontSize = 17.sp)
            if (oldPrice != null) Text(oldPrice, color = TextMuted, fontSize = 12.sp, textDecoration = TextDecoration.LineThrough)
        }
    }
}

@Composable
private fun MartScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
    LazyColumn(contentPadding = PaddingValues(bottom = 132.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item { SimpleTopBar("GoMart", Icons.Default.ArrowBack, Icons.Default.Search, topPadding = 0.dp) { open(Screen.Home) } }
            item { SearchPill("Cari kebutuhan harianmu", Icons.Default.Search, Modifier.padding(horizontal = 16.dp)) }
            item { MartCategories() }
            item { GroceryBanner() }
            item { ProductRowSection("Promo Spesial", martDeals, true) }
            item { ProductRowSection("Pilihan Segar Hari Ini", freshDeals, false) }
        }
        FloatingCartBar("2 item | Rp45.000", "Dari Toko Terdekat", "Lihat Keranjang", Modifier.align(Alignment.BottomCenter).padding(bottom = 82.dp, start = 16.dp, end = 16.dp))
        AppBottomBar(selected = "Home", onHome = { open(Screen.Home) }, onOrders = {}, onWallet = {}, onInbox = {}, onAccount = { open(Screen.Profile) })
    }
}

@Composable
private fun MartCategories() {
    val items = listOf(
        Service("Sayur & Buah", Icons.Default.Storefront, Color(0xFFE9FFF6), Color(0xFF009B68), Screen.Mart),
        Service("Daging & Seafood", Icons.Default.Restaurant, Color(0xFFFFF0F1), FoodRed, Screen.Mart),
        Service("Susu & Telur", Icons.Default.ShoppingBasket, Color(0xFFEFF6FF), Blue, Screen.Mart),
        Service("Snack", Icons.Default.Star, Color(0xFFFEFCE8), Orange, Screen.Mart),
        Service("Kebutuhan Rumah", Icons.Default.Home, Color(0xFFF5F3FF), Color(0xFF9333EA), Screen.Mart),
        Service("Lainnya", Icons.Default.GridView, Color(0xFFF8FAFC), TextMuted, Screen.Mart)
    )
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text("Pilih Kategori", fontSize = 20.sp, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.height(138.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false
        ) {
            items(items) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconBubble(it.icon, it.bg, it.tint, Modifier.size(50.dp), 24)
                    Spacer(Modifier.height(6.dp))
                    Text(it.name, fontSize = 11.sp, textAlign = TextAlign.Center, lineHeight = 13.sp)
                }
            }
        }
    }
}

@Composable
private fun GroceryBanner() {
    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF0B3D2E), Color(0xFF2F855A), Color(0xFFF59E0B))))
            .padding(22.dp)
    ) {
        Text("Diskon s/d 50%", color = Color.White, fontSize = 16.sp, modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(FoodRed).padding(horizontal = 8.dp, vertical = 4.dp))
        Text("Belanja Mingguan\nLebih Hemat!", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.align(Alignment.BottomStart))
    }
}

@Composable
private fun ProfileScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(top = 56.dp, bottom = 108.dp), verticalArrangement = Arrangement.spacedBy(22.dp)) {
            item { SimpleTopBar("Profile", Icons.Default.ArrowBack, Icons.Default.Settings, topPadding = 0.dp) { open(Screen.Home) } }
            item { ProfileHero() }
            item { ProfileStats() }
            item { ProfileMenu() }
            item { Text("GoApp v4.52.1", color = Color(0xFFA0A7B3), fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }
        }
        AppBottomBar(selected = "Account", onHome = { open(Screen.Home) }, onOrders = {}, onWallet = {}, onInbox = {}, onAccount = {})
    }
}

@Composable
private fun ProfileHero() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Avatar(Modifier.size(104.dp), large = true)
            CircleIcon(Icons.Default.Edit, PrimaryContainer, Color.White, Modifier.align(Alignment.BottomEnd).size(38.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text("Akbar Pratama", fontSize = 25.sp, fontWeight = FontWeight.Black)
        Text("+62 812 3456 7890", fontSize = 16.sp)
        Spacer(Modifier.height(12.dp))
        Text("GOLD MEMBER", color = Color(0xFF92400E), fontWeight = FontWeight.Bold, letterSpacing = 1.sp, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(Color(0xFFFFF8C5)).border(1.dp, Color(0xFFFDE047), RoundedCornerShape(999.dp)).padding(horizontal = 18.dp, vertical = 8.dp))
    }
}

@Composable
private fun ProfileStats() {
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        StatCard("Saldo GoPay", "Rp125.000", Icons.Default.AccountBalanceWallet, Blue, Modifier.weight(1f))
        StatCard("GoPay Points", "2.450 Points", Icons.Default.Star, Color(0xFF00A680), Modifier.weight(1f))
    }
}

@Composable
private fun StatCard(label: String, value: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(14.dp)).background(Color.White).padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBubble(icon, tint.copy(alpha = 0.1f), tint, Modifier.size(40.dp), 22)
            Spacer(Modifier.width(10.dp))
            Text(label, color = TextMuted, fontSize = 14.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun ProfileMenu() {
    val rows = listOf(
        "Pesanan saya" to Icons.Default.Receipt,
        "Voucher saya" to Icons.Default.Star,
        "Metode pembayaran" to Icons.Default.CreditCard,
        "Alamat tersimpan" to Icons.Default.Place,
        "Pusat Bantuan" to Icons.Default.Help,
        "Keamanan Akun" to Icons.Default.Check
    )
    Column(Modifier.padding(horizontal = 16.dp).clip(RoundedCornerShape(18.dp)).background(Color.White)) {
        rows.forEach { (text, icon) -> MenuRow(text, icon, TextDark) }
        MenuRow("Keluar", Icons.Default.ExitToApp, FoodRed)
    }
}

@Composable
private fun MenuRow(text: String, icon: ImageVector, tint: Color) {
    Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
        IconBubble(icon, if (tint == FoodRed) Color(0xFFFFDADA) else SurfaceLow, tint, Modifier.size(44.dp), 24)
        Spacer(Modifier.width(18.dp))
        Text(text, color = tint, fontSize = 17.sp, fontWeight = if (tint == FoodRed) FontWeight.Black else FontWeight.Normal, modifier = Modifier.weight(1f))
        Icon(Icons.Default.KeyboardArrowRight, null, tint = Color(0xFFA0A7B3))
    }
    Divider(color = Color(0xFFF1F1F1))
}

@Composable
private fun RestaurantDetailScreen(open: (Screen) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(bottom = 146.dp)) {
            item { SimpleTopBar("Restaurant Details", Icons.Default.ArrowBack, Icons.Default.Star, topPadding = 0.dp) { open(Screen.Food) } }
            item { RestoHeroCard() }
            item { CategoryTabs() }
            item { MenuSection("Paket Hemat", true) }
            item { GridMenuSection() }
        }
        FloatingCartBar("2 items | Rp60.000", "Mas Budi, Kemang", "View Basket", Modifier.align(Alignment.BottomCenter).padding(bottom = 82.dp, start = 16.dp, end = 16.dp))
        AppBottomBar(selected = "Orders", onHome = { open(Screen.Home) }, onOrders = {}, onWallet = {}, onInbox = {}, onAccount = { open(Screen.Profile) })
    }
}

@Composable
private fun RestoHeroCard() {
    Box(Modifier.fillMaxWidth().height(250.dp)) {
        Box(Modifier.fillMaxWidth().height(195.dp).background(Brush.verticalGradient(listOf(Color(0xFF3A2416), Color(0xFF0F1714))))) {
            Text("Ayam\nBakar", color = Color.White.copy(alpha = 0.88f), fontSize = 42.sp, fontWeight = FontWeight.Black, modifier = Modifier.align(Alignment.Center))
        }
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .padding(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Ayam Bakar Mas Budi", fontSize = 20.sp, fontWeight = FontWeight.Black)
                    Text("4.8  -  1.2km away  -  Open", color = Primary, fontSize = 16.sp)
                }
                Text("Promo", color = FoodRed, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(Color(0xFFFFE4E6)).padding(horizontal = 18.dp, vertical = 10.dp))
            }
        }
    }
}

@Composable
private fun CategoryTabs() {
    Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Chip("Paket Hemat", true)
        Chip("Ayam & Bebek")
        Chip("Minuman")
    }
}

@Composable
private fun MenuSection(title: String, listStyle: Boolean) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitle(title, "See All")
        MenuRowCard("Paket Ayam Bakar", "Nasi + Ayam Bakar + Lalapan + Sambal", "Rp25.000")
        MenuRowCard("Bebek Goreng", "Bebek Goreng Gurih + Nasi Hangat", "Rp35.000")
    }
}

@Composable
private fun MenuRowCard(title: String, desc: String, price: String) {
    Row(Modifier.clip(RoundedCornerShape(12.dp)).background(Color.White).border(1.dp, SurfaceLine, RoundedCornerShape(12.dp)).padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        FoodImage(Modifier.size(100.dp), title)
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Black, fontSize = 16.sp)
            Text(desc, fontSize = 15.sp, color = Color(0xFF334155))
            Text(price, fontWeight = FontWeight.Black, fontSize = 18.sp, color = PrimaryContainer)
        }
        CircleIcon(Icons.Default.Add, PrimaryContainer, Color.White)
    }
}

@Composable
private fun GridMenuSection() {
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Ayam & Bebek", fontSize = 22.sp, fontWeight = FontWeight.Black)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FoodGridCard("Ayam Kalasan", "Rp28.000", Modifier.weight(1f))
            FoodGridCard("Bebek Bakar", "Rp38.000", Modifier.weight(1f))
        }
    }
}

@Composable
private fun FoodGridCard(title: String, price: String, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(12.dp)).background(Color.White)) {
        FoodImage(Modifier.fillMaxWidth().aspectRatio(1f), title)
        Column(Modifier.padding(10.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(price, color = PrimaryContainer, fontWeight = FontWeight.Black, fontSize = 16.sp)
        }
    }
}

@Composable
private fun LoginScreen(open: (Screen) -> Unit) {
    Column(Modifier.fillMaxSize().padding(top = 112.dp, start = 32.dp, end = 32.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Text("GoApp", color = Primary, fontSize = 36.sp, fontWeight = FontWeight.Black)
        Text("Satu aplikasi untuk semua kebutuhan harianmu.", color = Color(0xFF334155), fontSize = 17.sp)
        Spacer(Modifier.height(24.dp))
        Text("Nomor HP", fontWeight = FontWeight.Black)
        PhoneField()
        PrimaryButton("Lanjut")
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Divider(Modifier.weight(1f), color = Color(0xFFB8C8AE))
            Text("Atau masuk dengan", Modifier.padding(horizontal = 14.dp), color = Color(0xFF243123))
            Divider(Modifier.weight(1f), color = Color(0xFFB8C8AE))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SocialButton("Google", Modifier.weight(1f))
            SocialButton("Facebook", Modifier.weight(1f), Blue)
        }
        Spacer(Modifier.weight(1f))
        TextButton(onClick = { open(Screen.Register) }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Belum punya akun? ", color = Color(0xFF334155))
            Text("Daftar", color = Primary, fontWeight = FontWeight.Black)
        }
        Text("Dengan masuk, kamu menyetujui Ketentuan\nLayanan dan Kebijakan Privasi GoApp.", color = Color(0xFFD8D8D8), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(28.dp))
    }
}

@Composable
private fun RegisterScreen(open: (Screen) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(top = 54.dp, start = 16.dp, end = 16.dp, bottom = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { SimpleTopBar("Daftar Akun", Icons.Default.ArrowBack, null, topPadding = 0.dp) { open(Screen.Login) } }
        item {
            Box(Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(14.dp)).background(Brush.linearGradient(listOf(Color(0xFF062D27), Color(0xFF75B7A2), Color.White)))) 
        }
        item {
            Text("Mulai Perjalanan Anda", fontSize = 32.sp, fontWeight = FontWeight.Black, lineHeight = 36.sp)
            Text("Lengkapi data diri untuk menikmati layanan super app terbaik.", color = Color(0xFF334155), fontSize = 16.sp, lineHeight = 24.sp)
        }
        item { InputField("Nama Lengkap", "Contoh: Budi Santoso", Icons.Default.Person) }
        item { InputField("Alamat Email", "nama@email.com", Icons.Default.Email) }
        item { InputField("Nomor Telepon", "+62     812 3456 7890", Icons.Default.Phone) }
        item {
            Row(verticalAlignment = Alignment.Top) {
                Box(Modifier.size(24.dp).border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(8.dp)))
                Spacer(Modifier.width(14.dp))
                Text("Saya menyetujui Syarat dan Ketentuan serta Kebijakan Privasi yang berlaku.", fontSize = 17.sp, lineHeight = 24.sp)
            }
        }
        item { PrimaryButton("Daftar Sekarang") }
        item {
            TextButton(onClick = { open(Screen.Login) }, modifier = Modifier.fillMaxWidth()) {
                Text("Sudah punya akun? ", color = Color(0xFF334155))
                Text("Masuk di sini", color = Primary, fontWeight = FontWeight.Black)
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BenefitMini("Data Terenkripsi & Aman", Icons.Default.Check, Color(0xFFE9FFF6), Modifier.weight(1f))
                BenefitMini("Proses Instan 1 Menit", Icons.Default.Check, Color(0xFFF0EEEE), Modifier.weight(1f))
            }
        }
        item { Text("POWERED BY URBANCONCIERGE", color = Color(0xFFD8D8D8), letterSpacing = 3.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) }
    }
}

@Composable
private fun InputField(label: String, value: String, icon: ImageVector) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, color = Color(0xFF294129), fontWeight = FontWeight.Black)
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(14.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(value, color = TextMuted, fontSize = 16.sp, modifier = Modifier.weight(1f))
            Icon(icon, null, tint = Color(0xFF687565))
        }
    }
}

@Composable
private fun PhoneField() {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("ID  +62", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(Modifier.width(14.dp))
        Divider(Modifier.height(28.dp).width(1.dp), color = SurfaceLine)
        Spacer(Modifier.width(14.dp))
        Text("812 3456 7890", color = Color(0xFFCBD0D6), fontSize = 16.sp)
    }
}

@Composable
private fun SocialButton(label: String, modifier: Modifier = Modifier, color: Color = Color(0xFFEA4335)) {
    Row(
        modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFB8C8AE), RoundedCornerShape(14.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(28.dp).clip(RoundedCornerShape(6.dp)).background(color.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
            Text(label.first().toString(), color = color, fontWeight = FontWeight.Black)
        }
        Spacer(Modifier.width(12.dp))
        Text(label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
private fun ProductRowSection(title: String, items: List<Product>, redPrice: Boolean) {
    Column {
        SectionTitle(title, "Lihat Semua", Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(12.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(items) { ProductCard(it, redPrice) }
        }
    }
}

@Composable
private fun ProductCard(item: Product, redPrice: Boolean) {
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
private fun RestaurantRow(title: String, meta: String, chip1: String, chip2: String?) {
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
private fun FoodImage(modifier: Modifier, label: String) {
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
private fun MapPreview() {
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

@Composable
private fun PaymentFooter(label: String, price: String, action: String) {
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
private fun FloatingCartBar(left: String, subtitle: String, right: String, modifier: Modifier = Modifier) {
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
private fun AppBottomBar(
    selected: String,
    onHome: () -> Unit,
    onOrders: () -> Unit,
    onWallet: () -> Unit,
    onInbox: () -> Unit,
    onAccount: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 12.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomItem("Home", Icons.Outlined.Home, selected == "Home", onHome)
            BottomItem("Orders", Icons.Outlined.ReceiptLong, selected == "Orders", onOrders)
            BottomItem("Wallet", Icons.Outlined.AccountBalanceWallet, false, onWallet)
            BottomItem("Inbox", Icons.Outlined.ChatBubbleOutline, false, onInbox)
            BottomItem("Account", Icons.Outlined.Person, selected == "Account", onAccount)
        }
    }
}

@Composable
private fun BottomItem(label: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    Column(
        Modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .background(if (selected) Color(0xFFE9FFF6) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null, tint = if (selected) Color(0xFF009B68) else Color(0xFF98A2B3))
        Text(label, color = if (selected) Color(0xFF009B68) else Color(0xFF98A2B3), fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun SimpleTopBar(title: String, leading: ImageVector, trailing: ImageVector?, topPadding: androidx.compose.ui.unit.Dp, onBack: () -> Unit) {
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
private fun SearchPill(placeholder: String, icon: ImageVector, modifier: Modifier = Modifier) {
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
private fun Chip(label: String, selected: Boolean = false) {
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
private fun SectionTitle(title: String, action: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
        Text(action, color = Primary, fontSize = 16.sp)
    }
}

@Composable
private fun PrimaryButton(text: String) {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth().height(58.dp),
        shape = RoundedCornerShape(999.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = Color.White)
    ) {
        Text(text, fontSize = 17.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun IconBubble(icon: ImageVector, bg: Color, tint: Color, modifier: Modifier = Modifier.size(46.dp), iconSize: Int = 24) {
    Box(modifier.clip(RoundedCornerShape(16.dp)).background(bg), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(iconSize.dp))
    }
}

@Composable
private fun CircleIcon(icon: ImageVector, bg: Color, tint: Color, modifier: Modifier = Modifier.size(42.dp)) {
    Box(modifier.clip(CircleShape).background(bg), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = tint, modifier = Modifier.size(26.dp))
    }
}

@Composable
private fun LabelChip(text: String, bg: Color, tint: Color) {
    Text(text, color = tint, fontSize = 11.sp, fontWeight = FontWeight.Black, modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(bg).padding(horizontal = 10.dp, vertical = 5.dp))
}

@Composable
private fun Avatar(modifier: Modifier = Modifier.size(48.dp), large: Boolean = false) {
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
private fun GreenBanner(title: String, subtitle: String) {
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
private fun BenefitMini(text: String, icon: ImageVector, bg: Color, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(18.dp)).background(bg).padding(18.dp)) {
        Icon(icon, null, tint = Primary)
        Spacer(Modifier.height(16.dp))
        Text(text, fontSize = 16.sp)
    }
}

private data class Service(val name: String, val icon: ImageVector, val bg: Color, val tint: Color, val target: Screen)
private data class Product(val title: String, val price: String, val oldPrice: String? = null, val badge: String? = null)

private val foodDeals = listOf(
    Product("Pizza Margherita", "Rp24.000", "Rp60.000", "60% OFF"),
    Product("Double Cheeseburger", "Rp35.000", "Rp70.000", "50% OFF"),
    Product("Salmon Health Bowl", "Rp42.000", "Rp70.000", "40% OFF")
)

private val martDeals = listOf(
    Product("Brokoli Segar 500g", "Rp18.000", "Rp22.500", "20% Off"),
    Product("Susu Cair Full Cream 1L", "Rp20.400", "Rp24.000", "15% Off"),
    Product("Daging Slice 250g", "Rp39.000", "Rp48.000", "19% Off")
)

private val freshDeals = listOf(
    Product("Paket Masak Sayur Asem", "Rp12.000"),
    Product("Telur Ayam Negeri 10 btr", "Rp22.500")
)
