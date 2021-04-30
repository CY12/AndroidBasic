package com.example.space.http;

import com.example.space.recycleview.Detail;
import com.example.space.recycleview.Party;
import com.example.space.recycleview.Salary;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 * Path是网址中的参数,例如:trades/{userId}
 * Query是问号后面的参数,例如:trades/{userId}?token={token}
 * QueryMap 相当于多个@Query
 * Field用于Post请求,提交单个数据,然后要加@FormUrlEncoded
 * Body相当于多个@Field,以对象的方式提交
 * @Streaming:用于下载大文件
 * @Header,@Headers、加请求头
 *
 * Get 请求
 *  http:ip:port/api/message/detail/121241
 *  @GET("message/detail/{id}")
 *  Observable<NoticeDetail> getNoticeDetail(@Path("id")long id);
 *
 *  http:ip:port/api/message/detail?id=121241
 *   @GET("message/detail")
 *  *  Observable<NoticeDetail> getNoticeDetail(@Query("id")long id);
 *
 *
 *
 *  初始化Retrofit
 * String BASE_URL = "http://102.10.10.132/api/";
 * Retrofit retrofit = new Retrofit.Builder()
 *         .baseUrl(BASE_URL)
 *         .build();
 * GET
 * 样式1（一个简单的get请求）
 * http://102.10.10.132/api/News
 *
 *     @GET("News")
 *     Call<NewsBean> getItem();
 * 样式2（URL中有参数）
 * http://102.10.10.132/api/News/1
 * http://102.10.10.132/api/News/{资讯id}
 *
 *     @GET("News/{newsId}")
 *     Call<NewsBean> getItem(@Path("newsId") String newsId);
 * 或
 * http://102.10.10.132/api/News/1/类型1
 * http://102.10.10.132/api/News/{资讯id}/{类型}
 *
 *     @GET("News/{newsId}/{type}")
 *     Call<NewsBean> getItem(@Path("newsId") String newsId， @Path("type") String type);
 * 样式3（参数在URL问号之后）
 * http://102.10.10.132/api/News?newsId=1
 * http://102.10.10.132/api/News?newsId={资讯id}
 *
 *     @GET("News")
 *     Call<NewsBean> getItem(@Query("newsId") String newsId);
 * 或
 * http://102.10.10.132/api/News?newsId=1&type=类型1
 * http://102.10.10.132/api/News?newsId={资讯id}&type={类型}
 *
 *     @GET("News")
 *     Call<NewsBean> getItem(@Query("newsId") String newsId， @Query("type") String type);
 * 样式4（多个参数在URL问号之后，且个数不确定）
 * http://102.10.10.132/api/News?newsId=1&type=类型1...
 * http://102.10.10.132/api/News?newsId={资讯id}&type={类型}...
 *
 *     @GET("News")
 *     Call<NewsBean> getItem(@QueryMap Map<String, String> map);
 * 也可以
 *
 *     @GET("News")
 *     Call<NewsBean> getItem(
 *               @Query("newsId") String newsId，
 *               @QueryMap Map<String, String> map);
 * POST
 * 样式1（需要补全URL，post的数据只有一条reason）
 * http://102.10.10.132/api/Comments/1
 * http://102.10.10.132/api/Comments/{newsId}
 *
 *     @FormUrlEncoded
 *     @POST("Comments/{newsId}")
 *     Call<Comment> reportComment(
 *         @Path("newsId") String commentId,
 *         @Field("reason") String reason);
 * 样式2（需要补全URL，问号后加入access_token，post的数据只有一条reason）
 * http://102.10.10.132/api/Comments/1?access_token=1234123
 * http://102.10.10.132/api/Comments/{newsId}?access_token={access_token}
 *
 *     @FormUrlEncoded
 *     @POST("Comments/{newsId}")
 *     Call<Comment> reportComment(
 *         @Path("newsId") String commentId,
 *         @Query("access_token") String access_token,
 *         @Field("reason") String reason);
 * 样式3（需要补全URL，问号后加入access_token，post一个body（对象））
 * http://102.10.10.132/api/Comments/1?access_token=1234123
 * http://102.10.10.132/api/Comments/{newsId}?access_token={access_token}
 *
 *     @POST("Comments/{newsId}")
 *     Call<Comment> reportComment(
 *         @Path("newsId") String commentId,
 *         @Query("access_token") String access_token,
 *         @Body CommentBean bean);
 * DELETE
 * 样式1（需要补全URL）
 * http://102.10.10.132/api/Comments/1
 * http://102.10.10.132/api/Comments/{commentId}
 *
 *     @DELETE("Comments/{commentId}")
 *     Call<ResponseBody> deleteNewsCommentFromAccount(
 *         @Path("commentId") String commentId);
 * 样式2（需要补全URL，问号后加入access_token）
 * http://102.10.10.132/api/Comments/1?access_token=1234123
 * http://102.10.10.132/api/Comments/{commentId}?access_token={access_token}
 *
 *     @DELETE("Comments/{commentId}")
 *     Call<ResponseBody> deleteNewsCommentFromAccount(
 *         @Path("commentId") String commentId，
 *         @Query("access_token") String access_token);
 * 样式3（带有body）
 * http://102.10.10.132/api/Comments
 *
 * @HTTP(method = "DELETE",path = "Comments",hasBody = true)
 * Call<ResponseBody> deleteCommont(
 *             @Body CommentBody body
 *     );
 * CommentBody：需要提交的内容，与Post中的Body相同
 *
 * PUT（这个请求很少用到，例子就写一个）
 * http://102.10.10.132/api/Accounts/1
 * http://102.10.10.132/api/Accounts/{accountId}
 *
 *     @PUT("Accounts/{accountId}")
 *     Call<ExtrasBean> updateExtras(
 *         @Path("accountId") String accountId,
 *         @Query("access_token") String access_token,
 *         @Body ExtrasBean bean);
 * 总结
 * @Path：所有在网址中的参数（URL的问号前面），如：
 * http://102.10.10.132/api/Accounts/{accountId}
 * @Query：URL问号后面的参数，如：
 * http://102.10.10.132/api/Comments?access_token={access_token}
 * @QueryMap：相当于多个@Query
 * @Field：用于POST请求，提交单个数据
 * @Body：相当于多个@Field，以对象的形式提交
 * Tips
 *
 * Tips1
 * 使用@Field时记得添加@FormUrlEncoded
 * Tips2
 * 若需要重新定义接口地址，可以使用@Url，将地址以参数的形式传入即可。如
 *     @GET
 *     Call<List < Activity>> getActivityList(
 *             @Url String url,
 *             @QueryMap Map<String, String> map);
 *
 *     Call<List<Activity>> call = service.getActivityList(
 *                 "http://115.159.198.162:3001/api/ActivitySubjects", map);
 *
 * https://www.jianshu.com/p/7687365aa946
 */
public interface Http {

    @POST("list")
    Call<Salary> getSalary(@Body RequestBody body);

    @POST("detail")
    Call<Detail> getDetail(@Body RequestBody body);

    @POST("party/list")
    Call<Party> getParty(@Body RequestBody body);
}
