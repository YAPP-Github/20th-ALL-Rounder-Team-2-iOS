package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.remote.model.OnboardRequest
import kr.co.knowledgerally.remote.model.OnboardedResponse
import kr.co.knowledgerally.remote.model.ProviderTokenRequest
import kr.co.knowledgerally.remote.model.SignUpResponse
import kr.co.knowledgerally.remote.model.UserResponseWrapper
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

internal interface ApiService {

    @POST("user/signup")
    suspend fun signUp(
        @Body providerToken: ProviderTokenRequest
    ): SignUpResponse

    @DELETE("user/me")
    suspend fun withdrawal()

    @GET("user/me")
    suspend fun getUser(): UserResponseWrapper

    @GET("user/user-onboarded") // 임시 endpoint, 확정 후 수정 필요
    suspend fun isOnboarded(): OnboardedResponse

    @POST("user/image")
    @Multipart
    suspend fun uploadUserImage(@Part part: MultipartBody.Part)

    @POST("user/onboard")
    suspend fun submitOnboard(@Body onboard: OnboardRequest)

    @FormUrlEncoded
    @PATCH("user/setting/push")
    suspend fun updatePushActive(@Field("pushActive") active: Boolean)
}
