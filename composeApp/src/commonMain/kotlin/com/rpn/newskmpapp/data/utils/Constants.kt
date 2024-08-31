package com.rpn.newskmpapp.data.utils

import com.rpn.newskmpapp.BuildKonfig
import com.rpn.newskmpapp.data.remote.dto.*
import kotlin.random.Random



object Constants {

    const val LOG_TAG = "KMP_News_App"
    const val DB_NAME = "my_news_room.db"

    val API_KEY = BuildKonfig.API_KEY
    val API_KEY_2 = BuildKonfig.API_KEY_NEWS_DATA
    const val BASE_URL = "https://newsapi.org/v2/"
    const val BASE_URL_2 = "https://newsdata.io/api/1/"

    /*
    * This keywords are prohibited
    * If any image related to this keyword shows in feed that will ber removed from list.
    * and also nobody can search related to this keyword.
    * Filtering Halal Content.
    */
    val prohibitedKeywords =
        listOf(
            "woman", "adult", "naughty", "sex", "porn", "lady", "girl", "nude",
            "bikini", "lingerie", "swimsuit", "underwear", "gay", "lesbian",
            "erotic", "provocative", "seductive", "flirt", "romance", "kissing",
            "xxx", "explicit", "hardcore",
            "scantily clad", "revealing", "intimate", "sensual", "adult film"
        )

    const val dataStoreFileName = "setting.preferences_pb"
}


val articlesDto: List<ArticleDto> = listOf(
    ArticleDto(
        source = SourceDto( "Bangla Tribuna"),
        author = "আন্তর্জাতিক ডেস্ক",
        title = "রাশিয়ার কুরস্কে অগ্রগতি অব্যাহত রয়েছে, দাবি ইউক্রেনের",
        description = "রাশিয়ার কুরস্কে ইউক্রেনীয় বাহিনীর অগগ্রতি অব্যাহত রয়েছে। মঙ্গলবার (১৩ আগস্ট) রাতের নিয়মিত ভিডিও বার্তায় ইউক্রেনীয় প্রেসিডেন্ট ভলোদিমির জেলেনস্কি এমন দাবি করেছেন। তিনি বলেছেন, যুদ্ধ শুরুর পর রুশ ভূখণ্ডে ইউক্রেনের সবচেয়ে বড় হামলায় কুরস্ক অঞ্চলে ৭৪টি বসতির নিয়ন্ত্রণ নিয়েছে সেনাবাহিনী। আরও বসতির নিয়ন্ত্রণ নিতে যুদ্ধক্ষেত্রে অগ্রসর হচ্ছে সেনারা। ইউক্রেনের দাবি, গত ২৪ ঘণ্টায় রাশিয়ার এক থেকে তিন...বিস্তারিত",
        url = "https://www.banglatribune.com/foreign/asia/858067/%E0%A6%B0%E0%A6%BE%E0%A6%B6%E0%A6%BF%E0%A6%AF%E0%A6%BC%E0%A6%BE%E0%A6%B0-%E0%A6%95%E0%A7%81%E0%A6%B0%E0%A6%B8%E0%A7%8D%E0%A6%95%E0%A7%87-%E0%A6%85%E0%A6%97%E0%A7%8D%E0%A6%B0%E0%A6%97%E0%A6%A4%E0%A6%BF-%E0%A6%85%E0%A6%AC%E0%A7%8D%E0%A6%AF%E0%A6%BE%E0%A6%B9%E0%A6%A4-%E0%A6%B0%E0%A7%9F%E0%A7%87%E0%A6%9B%E0%A7%87-%E0%A6%A6%E0%A6%BE%E0%A6%AC%E0%A6%BF",
        urlToImage = "https://cdn.banglatribune.net/contents/cache/images/300x300x1/uploads/media/2024/08/14/Ukraine-be4c4f9883f51de872dd708f251edc5d.JPG?jadewits_media_id=930474",
        publishedAt = "2024-08-14 04:18:43",
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "প্রথম আলো | বাংলা নিউজ পেপার"),
        author = "শিশির মোড়ল",
        title = "বৈষম্যবিরোধী ছাত্র আন্দোলনে নিহত: লাশগুলোর দাবিদার নেই",
        description = "কর্তৃপক্ষের দেওয়া তথ্য অনুয়ায়ী, আন্দোলনকে ঘিরে সংঘর্ষ–সংঘাতের ঘটনায় ৩০টির মতো মৃতদেহ ১৮ থেকে ২২ জুলাইয়ের মধ্যে হাসপাতালে আনা হয়।",
        url = "https://www.prothomalo.com/bangladesh/omedm897ot",
        urlToImage = "https://images.prothomalo.com/prothomalo-bangla%2F2024-08-14%2Fug35btqn%2FWHATSAPP-IMAGE-2024-08-12-AT-80148-PM085627.jpg?ar=40%3A21&auto=format%2Ccompress&mode=crop&ogImage=true&overlay_position=bottom&overlay_width_pct=1&rect=0%2C169%2C1500%2C788&w=1200",
        publishedAt = "2024-08-14 04:17:10",
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "Asia Times"),
        author = "The author",
        title = "Capitalism, mass anger and 2024 elections",
        description = "In the wake of his huge defeat on June 30, 2024, when 80% of voters rejected French “centrist” President Emmanuel Macron, he said he understood the French people’s anger. In the UK, Conservative loser Rishi Sunak said the same about the British people’s anger, as Labor leader Starmer now says as the anger explodes. Of [...]The post Capitalism, mass anger and 2024 elections appeared first on Asia Times.",
        url = "https://asiatimes.com/2024/08/capitalism-mass-anger-and-2024-elections/",
        urlToImage = "https://i0.wp.com/asiatimes.com/wp-content/uploads/2024/08/US-MAGA-Right-Wing-Protesters.jpg?fit=1024%2C683&ssl=1",
        publishedAt ="2024-08-14 04:13:20",
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "Bangla Tribuna"),
        author = "স্পোর্টস ডেস্ক",
        title = "লিগস কাপ থেকে বিদায় মেসিহীন মায়ামির",
        description = "উদ্বোধনী আসরে লিওনেল মেসির হাত ধরেই ইন্টার মায়ামির ঘরে কোনও শিরোপা এসেছিল। সেটা ছিল লিগস কাপে। এবার চোটের কারণে না মেসির অনুপস্থিতি ভালো করেই টের পেয়েছে মেজর লিগ সকারের ক্লাবটি। শেষ ষোলোতে তাদের ৩-২ গোলে হারিয়ে বিদায় দিয়েছে কলম্বাস। অথচ শুরুটা মায়ামি যেভাবে করেছিল তাতে এমন পরিণতি কল্পনাও করা যাচ্ছিল না। ১০ মিনিটেই প্রথম গোল করে এগিয়ে গিয়েছিল মায়ামি। হেডে গোলটি করেন মাতিয়াস রোহাস। বিরতির...বিস্তারিত",
        url = "https://www.banglatribune.com/sport/football/858065/%E0%A6%B2%E0%A6%BF%E0%A6%97%E0%A6%B8-%E0%A6%95%E0%A6%BE%E0%A6%AA-%E0%A6%A5%E0%A7%87%E0%A6%95%E0%A7%87-%E0%A6%AC%E0%A6%BF%E0%A6%A6%E0%A6%BE%E0%A7%9F-%E0%A6%AE%E0%A7%87%E0%A6%B8%E0%A6%BF%E0%A6%B9%E0%A7%80%E0%A6%A8-%E0%A6%AE%E0%A6%BE%E0%A7%9F%E0%A6%BE%E0%A6%AE%E0%A6%BF%E0%A6%B0%C2%A0",
        urlToImage = "https://cdn.banglatribune.net/contents/cache/images/300x300x1/uploads/media/2024/08/14/-035fcb2ffec4348f2c5f221a096eedda.png?jadewits_media_id=930473",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "Risingbd"),
        author = "The author",
        title = "খুলনা আইনজীবী সমিতির ১২ কোটি টাকা আত্মসাতের অভিযোগ",
        description = "খুলনা জেলা আইনজীবী সমিতির তিনটি ব্যাংক হিসাব থেকে প্রায় ১২ কোটি টাকা আত্মসাতের অভিযোগ উঠেছে। সমিতির সদ্য সাবেক সভাপতি সাইফুল ইসলাম ও সাধারণ সম্পাদক তারিক মাহমুদ তারার স্বাক্ষরে উল্লিখিত অর্থ উত্তোলন করা হয়। সদস্যদের বিপুল এ অর্থের গরমিলের অভিযোগে সাইফুল ইসলাম ও তারিক মাহমুদ তারার সদস্যপদ স্থগিত করা হয়েছে। একই সাথে সমিতির বাকি দুইটি হিসাব যাচাইয়ে ৫ সদস্যের অডিট কমিটি গঠন ও অর্থ আত্মসাৎ এর সঙ্গে জড়িতদের বিরুদ্ধে আইনি পদক্ষেপ গ্রহণেরও সিদ্ধান্ত নেওয়া হয়েছে।",
        url = "https://www.risingbd.com/bangladesh/news/568002",
        urlToImage = "https://www.risingbd.com/media/imgAll/2024August/sk-khl-2408140410.jpg",
        publishedAt = "2024-08-14 04:10:36",
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "Bangladesh is a unitary parliamentary republic based on the Westminster system. Bengalis make up almost 99% of the total population",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "Bangladesh is a unitary parliamentary republic based on the Westminster system. Bengalis make up almost 99% of the total population",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "Bangladesh is a unitary parliamentary republic based on the Westminster system. Bengalis make up almost 99% of the total population",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "Bangladesh is a unitary parliamentary republic based on the Westminster system. Bengalis make up almost 99% of the total population",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "Bangladesh is a unitary parliamentary republic based on the Westminster system. Bengalis make up almost 99% of the total population",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "শেখ হাসিনার ক্ষমতাচ্যুতিতে যুক্তরাষ্ট্র জড়িত নয়: হোয়াইট হাউস",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "শেখ হাসিনার ক্ষমতাচ্যুতিতে যুক্তরাষ্ট্র জড়িত নয়: হোয়াইট হাউস",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "শেখ হাসিনার ক্ষমতাচ্যুতিতে যুক্তরাষ্ট্র জড়িত নয়: হোয়াইট হাউস",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "শেখ হাসিনার ক্ষমতাচ্যুতিতে যুক্তরাষ্ট্র জড়িত নয়: হোয়াইট হাউস",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "শেখ হাসিনার ক্ষমতাচ্যুতিতে যুক্তরাষ্ট্র জড়িত নয়: হোয়াইট হাউস",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticleDto(
        source = SourceDto( "My news"),
        author = "The author",
        title = "শেখ হাসিনার ক্ষমতাচ্যুতিতে যুক্তরাষ্ট্র জড়িত নয়: হোয়াইট হাউস",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    )
)
val newsResponse = NewsResponseDto(
    articlesDto,
    "dwe",
    5
)
