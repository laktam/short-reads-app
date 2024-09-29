import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import org.mql.laktam.shortreads.models.User

@Composable
fun ProfileHeader(user: User) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF0052CC)) // Blue background
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(16.dp)
            ) // Shadow with rounded corners
            .clip(RoundedCornerShape(16.dp)) // Clip the card to have rounded corners
            .background(Color.White) // Background color of the card
            .padding(16.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            // Profile Image
            Image(
                painter = rememberAsyncImagePainter("http://10.0.2.2/short-reads-backend/${user.profilePictureUrl}"),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(95.dp)
    //                            .padding(8.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .border(
                        3.dp,
                        Color.LightGray,
                        RoundedCornerShape(9.dp)
                    ), // Same shape for the border
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Name
            Text(
                text = user.username,
//                style = MaterialTheme.typography.headlineSmall,
//                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            // Followers count
            val followersText = if (user.followersCount == 1) {
                "Follower"
            } else {
                "Followers"
            }
            Text(
                text = "${user.followersCount} $followersText",
//                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
//                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),

            ){
                // Email
                Text(
                    text = user.email,
//                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
//                color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                // Description
                Text(
                    text = user.description,
//                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
//                color = Color.White
                )
            }

        }
    }
//    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileHeader() {
//    ProfileHeader()
//}
