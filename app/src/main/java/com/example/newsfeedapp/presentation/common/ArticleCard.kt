package com.example.newsfeedapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsfeedapp.R
import com.example.newsfeedapp.domain.model.Article
import com.example.newsfeedapp.domain.model.Source
import com.example.newsfeedapp.presentation.Dimens.ArticleCardSize
import com.example.newsfeedapp.presentation.Dimens.ExtraSmallPadding
import com.example.newsfeedapp.presentation.Dimens.ExtraSmallPadding2
import com.example.newsfeedapp.presentation.Dimens.SmallIconSize
import com.example.newsfeedapp.presentation.Dimens.SmallPadding
import com.example.newsfeedapp.ui.theme.NewsFeedAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier, article: Article, onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier = modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                )

                Spacer(modifier = Modifier.width(SmallPadding))

                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )

            }
        }

    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsFeedAppTheme {
        Surface {
            ArticleCard(
                article = Article(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "3 hours",
                    source = Source(id = "", name = "BBC News"),
                    title = "If ain't broken, don't fix! But do the work to mitigate the future risk.",
                    url = "",
                    urlToImage = "https://img.freepik.com/free-photo/abstract-autumn-beauty-multi-colored-leaf-vein-pattern-generated-by-ai_188544-9871.jpg"
                )
            ) {}
        }
    }
}