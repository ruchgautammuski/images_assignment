package com.example.galleryapp.data


data class ResponseModelClass(
    val topic_submissions: Any,
    val currentUserCollections: List<Any?>,
    val color: String,
    val sponsorship: Sponsorship,
    val createdAt: String,
    val description: String,
    val likedByUser: Boolean,
    val urls: Urls,
    val altDescription: String,
    val updatedAt: String,
    val width: Long,
    val blurHash: String,
    val links: ResponseModelClassLinks,
    val id: String,
    val user: User,
    val slug: String,
    val breadcrumbs: List<Any?>,
    val height: Long,
    val likes: Long
)

data class ResponseModelClassLinks(
    val download: String,
    val downloadLocation: String,
    val self: String,
    val html: String
)

data class Sponsorship(
    val sponsor: User,
    val taglineURL: String,
    val tagline: String,
    val impressionUrls: List<String>
)

data class User(
    val totalPhotos: Long,
    val acceptedTos: Boolean,
    val social: Social,
    val twitterUsername: String,
    val bio: String,
    val totalLikes: Long,
    val portfolioURL: String,
    val profileImage: ProfileImage,
    val updatedAt: String,
    val forHire: Boolean,
    val name: String,
    val totalPromotedPhotos: Long,
    val location: String,
    val links: UserLinks,
    val totalCollections: Long,
    val id: String,
    val firstName: String,
    val instagramUsername: String,
    val username: String
)

data class UserLinks(
    val followers: String,
    val portfolio: String,
    val following: String,
    val self: String,
    val html: String,
    val photos: String,
    val likes: String
)

data class ProfileImage(
    val small: String,
    val large: String,
    val medium: String
)

data class Social(
    val twitterUsername: String,
    val instagramUsername: String,
    val portfolioURL: String
)

data class Urls(
    val small: String,
    val smallS3: String,
    val thumb: String,
    val raw: String,
    val regular: String,
    val full: String
)