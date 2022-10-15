package com.murphyspider.instasave.api.profileInfo


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("bio_links")
    val bioLinks: List<Any>,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("biography_with_entities")
    val biographyWithEntities: BiographyWithEntities,
    @SerializedName("blocked_by_viewer")
    val blockedByViewer: Boolean,
    @SerializedName("business_address_json")
    val businessAddressJson: Any,
    @SerializedName("business_category_name")
    val businessCategoryName: Any,
    @SerializedName("business_contact_method")
    val businessContactMethod: String,
    @SerializedName("business_email")
    val businessEmail: Any,
    @SerializedName("business_phone_number")
    val businessPhoneNumber: Any,
    @SerializedName("category_enum")
    val categoryEnum: Any,
    @SerializedName("category_name")
    val categoryName: Any,
    @SerializedName("connected_fb_page")
    val connectedFbPage: Any,
    @SerializedName("country_block")
    val countryBlock: Boolean,
    @SerializedName("edge_felix_video_timeline")
    val edgeFelixVideoTimeline: EdgeFelixVideoTimeline,
    @SerializedName("edge_follow")
    val edgeFollow: EdgeFollow,
    @SerializedName("edge_followed_by")
    val edgeFollowedBy: EdgeFollowedBy,
    @SerializedName("edge_media_collections")
    val edgeMediaCollections: EdgeMediaCollections,
    @SerializedName("edge_mutual_followed_by")
    val edgeMutualFollowedBy: EdgeMutualFollowedBy,
    @SerializedName("edge_owner_to_timeline_media")
    val edgeOwnerToTimelineMedia: EdgeOwnerToTimelineMedia,
    @SerializedName("edge_saved_media")
    val edgeSavedMedia: EdgeSavedMedia,
    @SerializedName("external_url")
    val externalUrl: Any,
    @SerializedName("external_url_linkshimmed")
    val externalUrlLinkshimmed: Any,
    @SerializedName("fbid")
    val fbid: String,
    @SerializedName("followed_by_viewer")
    val followedByViewer: Boolean,
    @SerializedName("follows_viewer")
    val followsViewer: Boolean,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("group_metadata")
    val groupMetadata: Any,
    @SerializedName("guardian_id")
    val guardianId: Any,
    @SerializedName("has_ar_effects")
    val hasArEffects: Boolean,
    @SerializedName("has_blocked_viewer")
    val hasBlockedViewer: Boolean,
    @SerializedName("has_channel")
    val hasChannel: Boolean,
    @SerializedName("has_clips")
    val hasClips: Boolean,
    @SerializedName("has_guides")
    val hasGuides: Boolean,
    @SerializedName("has_requested_viewer")
    val hasRequestedViewer: Boolean,
    @SerializedName("hide_like_and_view_counts")
    val hideLikeAndViewCounts: Boolean,
    @SerializedName("highlight_reel_count")
    val highlightReelCount: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_business_account")
    val isBusinessAccount: Boolean,
    @SerializedName("is_eligible_to_view_account_transparency")
    val isEligibleToViewAccountTransparency: Boolean,
    @SerializedName("is_embeds_disabled")
    val isEmbedsDisabled: Boolean,
    @SerializedName("is_guardian_of_viewer")
    val isGuardianOfViewer: Boolean,
    @SerializedName("is_joined_recently")
    val isJoinedRecently: Boolean,
    @SerializedName("is_private")
    val isPrivate: Boolean,
    @SerializedName("is_professional_account")
    val isProfessionalAccount: Boolean,
    @SerializedName("is_supervised_by_viewer")
    val isSupervisedByViewer: Boolean,
    @SerializedName("is_supervised_user")
    val isSupervisedUser: Boolean,
    @SerializedName("is_supervision_enabled")
    val isSupervisionEnabled: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("location_transparency_country")
    val locationTransparencyCountry: Any,
    @SerializedName("overall_category_name")
    val overallCategoryName: Any,
    @SerializedName("profile_pic_url")
    val profilePicUrl: String,
    @SerializedName("profile_pic_url_hd")
    val profilePicUrlHd: String,
    @SerializedName("pronouns")
    val pronouns: List<String>,
    @SerializedName("requested_by_viewer")
    val requestedByViewer: Boolean,
    @SerializedName("restricted_by_viewer")
    val restrictedByViewer: Boolean,
    @SerializedName("should_show_category")
    val shouldShowCategory: Boolean,
    @SerializedName("should_show_public_contacts")
    val shouldShowPublicContacts: Boolean,
    @SerializedName("state_controlled_media_country")
    val stateControlledMediaCountry: Any,
    @SerializedName("transparency_label")
    val transparencyLabel: Any,
    @SerializedName("transparency_product")
    val transparencyProduct: String,
    @SerializedName("username")
    val username: String
)